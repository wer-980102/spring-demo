package com.wer.parking.mapper.source1;

import com.wer.parking.model.PakParkingPayInfo;
import com.wer.parking.model.param.ParkAccessInfoParam;

import java.util.List;


/**
 * 停车缴费记录Mapper接口
 * 
 * @author wsp
 * @date 2020-12-23
 */
public interface PakParkingPayInfoMapper 
{
    /**
     * 查询停车缴费记录
     * 
     * @param payId 停车缴费记录ID
     * @return 停车缴费记录
     */
    public PakParkingPayInfo selectPakParkingPayInfoById(Long payId);

    /**
     * 查询停车缴费记录列表
     * 
     * @param pakParkingPayInfo 停车缴费记录
     * @return 停车缴费记录集合
     */
    public List<PakParkingPayInfo> selectPakParkingPayInfoList(PakParkingPayInfo pakParkingPayInfo);

    /**
     * 缴费信息
     * @param param
     * @return
     */
    List<PakParkingPayInfo> getPayInfo(ParkAccessInfoParam param);
    /**
     * 新增停车缴费记录
     * 
     * @param pakParkingPayInfo 停车缴费记录
     * @return 结果
     */
     int insertPakParkingPayInfo(PakParkingPayInfo pakParkingPayInfo);

    /**
     * 修改停车缴费记录
     * 
     * @param pakParkingPayInfo 停车缴费记录
     * @return 结果
     */
     int updatePakParkingPayInfo(PakParkingPayInfo pakParkingPayInfo);

    /**
     * 修改停车找零缴费记录
     *
     * @param pakParkingPayInfo 停车缴费记录
     * @return 结果
     */
    int updatePakParkingLooseChange(PakParkingPayInfo pakParkingPayInfo);

    /**
     * 删除停车缴费记录
     * 
     * @param payId 停车缴费记录ID
     * @return 结果
     */
    public int deletePakParkingPayInfoById(Long payId);

    /**
     * 批量删除停车缴费记录
     * 
     * @param payIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePakParkingPayInfoByIds(Long[] payIds);

}
