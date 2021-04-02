package com.wer.parking.mapper.source2;

import com.wer.parking.model.PakAccessInfo;

import java.util.List;


/**
 * 车辆出入记录Mapper接口
 * 
 * @author wsp
 * @date 2020-12-21
 */
public interface PakAccessInfoTwoMapper
{
    /**
     * 查询车辆出入记录
     * 
     * @param pakAccessInfo 车辆出入记录ID
     * @return 车辆出入记录
     */
     PakAccessInfo selectPakAccessInfoById(PakAccessInfo pakAccessInfo);


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


}
