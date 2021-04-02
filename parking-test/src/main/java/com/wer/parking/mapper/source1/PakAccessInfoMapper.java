package com.wer.parking.mapper.source1;

import com.wer.parking.model.PakAccessInfo;
import com.wer.parking.model.PakParking;
import com.wer.parking.model.PakPassage;
import com.wer.parking.model.param.ParkAccessInfoParam;

import java.util.List;



/**
 * 车辆出入记录Mapper接口
 * 
 * @author wsp
 * @date 2020-12-21
 */
public interface PakAccessInfoMapper 
{
    /**
     * 查询车辆出入记录
     * 
     * @param param 车辆出入记录ID
     * @return 车辆出入记录
     */
     List<PakAccessInfo> selectPakAccessInfoById(ParkAccessInfoParam param);

    /**
     * 出入信息
     * @param pakAccessInfo
     * @return
     */
    PakAccessInfo getPakAccessInfoById(PakAccessInfo pakAccessInfo);
    /**
     * 查询车辆的入场唯一id
     *
     * @param carPlate 车牌号
     * @return 车辆出入记录
     */
     String getEntryReqId(String carPlate);

    /**
     * 查询车辆出入记录列表
     * 
     * @param pakAccessInfo 车辆出入记录
     * @return 车辆出入记录集合
     */
     List<PakAccessInfo> selectPakAccessInfoList(PakAccessInfo pakAccessInfo);

    /**
     * 新增车辆出入记录
     * 
     * @param pakAccessInfo 车辆出入记录
     * @return 结果
     */
     int insertPakAccessInfo(PakAccessInfo pakAccessInfo);

    /**
     * 修改车辆出入记录
     * 
     * @param pakAccessInfo 车辆出入记录
     * @return 结果
     */
     int updatePakAccessInfo(PakAccessInfo pakAccessInfo);

    /**
     * 修改车辆出入记录
     *
     * @param pakAccessInfo 车辆出场记录
     * @return 结果
     */
     int updatePakAccessOutInfo(PakAccessInfo pakAccessInfo);

    /**
     * 删除车辆出入记录
     * 
     * @param accessId 车辆出入记录ID
     * @return 结果
     */
     int deletePakAccessInfoById(Long accessId);

    /**
     * 批量删除车辆出入记录
     * 
     * @param accessIds 需要删除的数据ID
     * @return 结果
     */
     int deletePakAccessInfoByIds(Long[] accessIds);

    /**
     * 查询停车场信息
     *
     * @param parkingId 停车场信息ID
     * @return 停车场信息
     */
    PakParking selectPakParkingById(Long parkingId);

    /**
     * 查询出入口信息
     *
     * @param pakPassage 出入口信息名称
     * @return 出入口信息
     */
    PakPassage selectPakPassageByName(PakPassage pakPassage);
}
