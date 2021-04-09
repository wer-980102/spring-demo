package com.wer.parking.mapper.source1;

import java.util.List;

import com.wer.parking.model.PakParking;

/**
 * 停车场信息Mapper接口
 * 
 * @author wsp
 * @date 2020-12-21
 */
public interface PakParkingMapper 
{
    /**
     * 查询停车场信息
     * 
     * @param parkingId 停车场信息ID
     * @return 停车场信息
     */
     PakParking selectPakParkingById(Long parkingId);

    /**
     * 查询停车场信息列表
     * 
     * @param pakParking 停车场信息
     * @return 停车场信息集合
     */
     List<PakParking> selectPakParkingList(PakParking pakParking);

    /**
     * 新增停车场信息
     * 
     * @param pakParking 停车场信息
     * @return 结果
     */
     int insertPakParking(PakParking pakParking);

    /**
     * 修改停车场信息
     * 
     * @param pakParking 停车场信息
     * @return 结果
     */
     int updatePakParking(PakParking pakParking);

    /**
     * 删除停车场信息
     * 
     * @param parkingId 停车场信息ID
     * @return 结果
     */
    public int deletePakParkingById(Long parkingId);

    /**
     * 批量删除停车场信息
     * 
     * @param parkingIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePakParkingByIds(Long[] parkingIds);

}
