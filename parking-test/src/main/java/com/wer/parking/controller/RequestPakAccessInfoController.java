package com.wer.parking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;
import com.wer.parking.mapper.source1.PakAccessInfoMapper;
import com.wer.parking.mapper.source1.PakParkingPayInfoMapper;
import com.wer.parking.mapper.source2.PakAccessInfoTwoMapper;
import com.wer.parking.mapper.source2.PakParkingPayInfoTwoMapper;
import com.wer.parking.model.PakAccessInfo;
import com.wer.parking.model.PakParking;
import com.wer.parking.model.PakParkingPayInfo;
import com.wer.parking.model.PakPassage;
import com.wer.parking.model.es.param.ParkingEsAccessInfoParam;
import com.wer.parking.model.es.param.ParkingEsPayInfoParam;
import com.wer.parking.model.param.ParkAccessInfoParam;
import com.wer.parking.utlis.ElasticSearchSqlUtil;
import com.wer.parking.utlis.ElasticSearchUtil;
import com.wer.parking.utlis.TimeUtils;
import com.wer.parking.utlis.enums.ParkingIndex;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/request/pakaccessinfo")
public class RequestPakAccessInfoController {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElasticSearchUtil esUtil;

    @Autowired
    private ElasticSearchSqlUtil esSqlUtil;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired(required=true)
    private PakAccessInfoMapper source1AccessInfoMapper;
    @Autowired
    private PakParkingPayInfoMapper pakParkingPayInfoMapper;
    @Autowired(required=true)
    private PakAccessInfoTwoMapper source2AccessInfoMapper;
    @Autowired
    private PakParkingPayInfoTwoMapper pakParkingPayInfoTwoMapper;

    /**
     * 入场信息
     * @param param
     */
    @GetMapping("/getAccessInfo")
    public String getAccessInfo(@RequestBody ParkAccessInfoParam param){

        List<PakAccessInfo> pakAccessInfo = source1AccessInfoMapper.selectPakAccessInfoById(param);

        if(null != pakAccessInfo && pakAccessInfo.size()>0){

            pakAccessInfo.stream().forEach(dto->{
                System.out.println("xx:"+dto);
                PakAccessInfo pakAccessinfo = PakAccessInfo.builder()
                        .carPlate(dto.getCarPlate())
                        .parkingId(dto.getParkingId())
                        .entryPassageId(dto.getEntryPassageId())
                        .entryPhoto(dto.getEntryPhoto())
                        .carPlateType(dto.getCarPlateType())
                        .carType(dto.getCarType())
                        .entryTime(dto.getEntryTime())
                        .entryOperator(dto.getEntryOperator())
                        .entryType(dto.getEntryType())
                        .quitRemark(dto.getQuitRemark())
                        .parkSourceData(param.getParkSourceData())
                        .entryReqid(dto.getEntryReqid()).build();
                 source2AccessInfoMapper.insertPakAccessInfo(pakAccessinfo);

                //通道信息
                PakPassage pakPassage = source1AccessInfoMapper.selectPakPassageByName(PakPassage.builder().passageId(dto.getEntryPassageId()).parkingId(dto.getParkingId()).build());
                //车场信息
                PakParking pakParking = source1AccessInfoMapper.selectPakParkingById(dto.getParkingId());
                //插入Es
                ParkingEsAccessInfoParam assessInfo = ParkingEsAccessInfoParam.builder().access_id(String.valueOf(pakAccessinfo.getAccessId()))
                        .car_plate(dto.getCarPlate())
                        .parking_id(dto.getParkingId().toString())
                        .parking_name(pakParking.getParkingName())
                        .total_stall(pakParking.getTotalStall())
                        .free_stall(pakParking.getFreeStall())
                        .entry_passage_id(String.valueOf(pakPassage.getPassageId()))
                        .entry_passage_name(pakPassage.getPassageName())
                        .entry_photo(dto.getEntryPhoto())
                        .entry_operator(dto.getEntryOperator())
                        .entry_time(dto.getEntryTime())
                        .car_plate_type(dto.getCarType().toString())
                        .entry_type(dto.getEntryType())
                        .car_type(String.valueOf(dto.getCarType()))
                        .entry_reqId(dto.getEntryReqid())
                        .quit_passage_id(pakPassage.getPassageId().toString())
                        .quit_passage_name(pakPassage.getPassageName())
                        .quit_type(dto.getQuitType())
                        .quit_time(TimeUtils.getDate(TimeUtils.getCurrentTime("3")))
                        .quit_photo(dto.getQuitPhoto())
                        .quit_operator("admin")
                        .quit_remark(dto.getQuitRemark())
                        .quit_reqId(dto.getQuitReqid())
                        .park_source_data(param.getParkSourceData()).build();

                try{
                    //创建请求
                    IndexRequest request = new IndexRequest(ParkingIndex.ACCESS_INFO.getIndex());
                    //Id根据出入主键设置
                    request.id(String.valueOf(pakAccessinfo.getAccessId()));
                    //数据放入json
                    request.source(mapper.writeValueAsString(assessInfo), XContentType.JSON);
                    //客户端发送请求，获取响应结果
                    IndexResponse index = client.index(request, RequestOptions.DEFAULT);
                    System.out.println("Es数据插入："+index.getResult().toString()+"      "+ index.status().toString());



                 /*   //出场数据
                    if(getEsIndexInfo(pakAccessinfo.getAccessId().toString())){
                        UpdateByQueryRequest request1 = new UpdateByQueryRequest(ParkingIndex.ACCESS_INFO.getIndex());
                        request1.setQuery(new TermQueryBuilder("access_id", pakAccessinfo.getAccessId().toString()));
                        String script = "ctx._source['quit_passage_id']=params.exitgateId;ctx._source['quit_passage_name']=params.passageName;ctx._source['quit_type']=params.releaseType;ctx._source['quit_time']=params.exitTime;ctx._source['quit_photo']=params.outimgPicture;ctx._source['quit_operator']=params.operator;ctx._source['quit_remark']=params.remark;ctx._source['quit_reqId']=params.recordId;";
                        Map<String,Object> scriptParam = new HashMap<>();
                        scriptParam.put("exitgateId",pakPassage.getPassageId());
                        scriptParam.put("passageName",pakPassage.getPassageName());
                        scriptParam.put("releaseType",dto.getQuitType());
                        scriptParam.put("exitTime",dto.getQuitTime());
                        scriptParam.put("outimgPicture",dto.getQuitPhoto());
                        scriptParam.put("operator","admin");
                        scriptParam.put("remark",dto.getQuitRemark());
                        scriptParam.put("recordId",dto.getQuitReqid());
                        request1.setScript(new Script(ScriptType.INLINE,Script.DEFAULT_SCRIPT_LANG,script,scriptParam));

                        client.updateByQuery(request1, RequestOptions.DEFAULT);
                    }*/
                }catch (IOException e){
                    e.printStackTrace();
                }
            });

        }else{
           return "暂时没有数据！";
        }
        return "插入成功！";
    }

    /**
     * 缴费信息
     * @return
     */
    @GetMapping("/getOutAccessInfo")
    public String getOutAccessInfo(@RequestBody ParkAccessInfoParam param){
        List<PakParkingPayInfo> parkPayInfo = pakParkingPayInfoMapper.getPayInfo(param);
        parkPayInfo.stream().forEach(PakParkingPayInfo->{
            PakParkingPayInfo payInfo = PakParkingPayInfo.builder()
                    .payId(PakParkingPayInfo.getPayId())
                    .carPlate(PakParkingPayInfo.getCarPlate())
                    .payOrderNum(PakParkingPayInfo.getPayOrderNum())
                    .payTime(PakParkingPayInfo.getPayTime())
                    .paySource(PakParkingPayInfo.getPaySource())
                    .payClient(PakParkingPayInfo.getPayClient())
                    .payChanel(PakParkingPayInfo.getPayChanel())
                    .payment(PakParkingPayInfo.getPayment())
                    .actualPayment(PakParkingPayInfo.getActualPayment())
                    .freeTime(PakParkingPayInfo.getFreeTime())
                    .freeMoney(PakParkingPayInfo.getFreeMoney())
                    .parkSourceData(param.getParkSourceData())
                    .parkingOrderNum(PakParkingPayInfo.getParkingOrderNum())
                    .reqid(PakParkingPayInfo.getReqid()).build();
            int i = pakParkingPayInfoTwoMapper.insertPakParkingPayInfo(payInfo);

            //出入场信息
            PakAccessInfo pakAccessInfoById = source1AccessInfoMapper.getPakAccessInfoById(PakAccessInfo.builder().parkingId(PakParkingPayInfo.getParkingId()).carPlate(PakParkingPayInfo.getCarPlate()).build());
            //车场信息
            PakParking pakParking = source1AccessInfoMapper.selectPakParkingById(PakParkingPayInfo.getParkingId());
            if(pakAccessInfoById != null){
                //es插入
                ParkingEsPayInfoParam esPayInfo = ParkingEsPayInfoParam.builder().pay_id(String.valueOf(payInfo.getPayId()))
                        .parking_id(PakParkingPayInfo.getParkingId().toString())
                        .parking_name(pakParking.getParkingName())
                        .car_plate(PakParkingPayInfo.getCarPlate())
                        .car_plate_type("0")
                        .quit_type(pakAccessInfoById.getQuitType())
                        .entry_time(pakAccessInfoById.getEntryTime())
                        .quit_time(pakAccessInfoById.getQuitTime())
                        .pay_order_num(PakParkingPayInfo.getPayOrderNum())
                        .pay_time(PakParkingPayInfo.getPayTime())
                        .pay_source(PakParkingPayInfo.getPaySource())
                        .pay_client(PakParkingPayInfo.getPayClient())
                        .pay_chanel(PakParkingPayInfo.getPayChanel())
                        .payment(Double.valueOf(PakParkingPayInfo.getPayment()))
                        .actual_payment(Double.valueOf(PakParkingPayInfo.getActualPayment()))
                        .free_time(PakParkingPayInfo.getFreeTime())
                        .free_money(Double.valueOf(PakParkingPayInfo.getFreeMoney()))
                        .reqId(PakParkingPayInfo.getReqid())
                        .park_source_data(param.getParkSourceData()).build();

                try {
                    //创建请求
                    IndexRequest payRequest = new IndexRequest(ParkingIndex.PAY_INFO.getIndex());
                    //Id根据出入主键设置
                    payRequest.id(String.valueOf(payInfo.getPayId()));
                    //数据放入json
                    payRequest.source(mapper.writeValueAsString(esPayInfo), XContentType.JSON);
                    //客户端发送请求，获取响应结果
                    IndexResponse index = client.index(payRequest, RequestOptions.DEFAULT);
                    System.out.println("Es数据插入：" + index.getResult().toString() + "-----------" + index.status().toString());

                    //修改出入es的关联缴费Id
                    UpdateByQueryRequest request = new UpdateByQueryRequest(ParkingIndex.ACCESS_INFO.getIndex());
                    request.setQuery(new TermQueryBuilder("access_id", pakAccessInfoById.getAccessId().toString()));

                    String script = "ctx._source['pay_id']=params.payId;";
                    Map<String,Object> scriptParam = new HashMap<>();
                    scriptParam.put("payId",payInfo.getPayId());

                    request.setScript(new Script(ScriptType.INLINE,Script.DEFAULT_SCRIPT_LANG,script,scriptParam));
                    client.updateByQuery(request, RequestOptions.DEFAULT);
                    //修改进出表缴费ID
                    source2AccessInfoMapper.updatePakAccessInfo(PakAccessInfo.builder().payId(payInfo.getPayId().toString()).accessId(pakAccessInfoById.getAccessId()).build());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        return "";
    }



    /**
     * 查询es存不存在该id
     * @param id
     * @return
     * @throws IOException
     */
    public boolean getEsIndexInfo(String id) throws IOException {
        //创建请求
        GetRequest request = new GetRequest(ParkingIndex.ACCESS_INFO.getIndex(),id);
        //不获取返回的_source上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        return exists;
    }
}
