package com.wer.parking.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PakAccessInfo implements Serializable
{

    /** 主键id */
    private Long accessId;

    /** 车牌号 （无牌则填入场取票号） */
    private String carPlate;

    /** 停车场id */
    private Long parkingId;

    /** 入口id */
    private Long entryPassageId;

    /** 入场抓拍 */
    private String entryPhoto;

    /** 入场操作人 */
    private String entryOperator;

    /** 入场时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date entryTime;

    /** 出口id */
    private Long quitPassageId;

    /** 出场抓拍 */
    private String quitPhoto;

    /** 出场操作人 */
    private String quitOperator;

    /** 出场时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date quitTime;

    /** 入场放行类型 0.正常放行1.免费放行2.异常放行3.遥控放行4.跟车放行5.虚拟放行6.长抬放行7.临时车异常放行 */
    private String entryType;

    /** 出场放行类型 0.正常放行1.免费放行2.异常放行3.遥控放行4.跟车放行5.虚拟放行6.长抬放行7.临时车异常放行 */
    private String quitType;

    /** 车辆类型：0.临时车 1.VIP车 2.月租车 3.充值车 4.时租车 5.产权车 6.计次车 7.贵宾卡 8.员工卡 9.大客车 100.预约车 101.其他 */
    private Integer carType;

    /** 放行说明 */
    private String quitRemark;

    /** 出场缴费id */
    private Long quitPayId;

    /** 入场请求唯一标识 */
    private String entryReqid;

    /** 出场请求唯一标识 */
    private String quitReqid;

    /** 车牌类型 */
    private Integer carPlateType;
    /** 数据来源 */
    private String parkSourceData;
    /** 缴费Id */
    private String payId;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;


/*
    @Override
    public String toString() {
        return "PakAccessInfo{" +
                "accessId=" + accessId +
                ", carPlate='" + carPlate + '\'' +
                ", parkingId=" + parkingId +
                ", entryPassageId=" + entryPassageId +
                ", entryPhoto='" + entryPhoto + '\'' +
                ", entryOperator='" + entryOperator + '\'' +
                ", entryTime=" + entryTime +
                ", quitPassageId=" + quitPassageId +
                ", quitPhoto='" + quitPhoto + '\'' +
                ", quitOperator='" + quitOperator + '\'' +
                ", quitTime=" + quitTime +
                ", entryType='" + entryType + '\'' +
                ", quitType='" + quitType + '\'' +
                ", carType=" + carType +
                ", quitRemark='" + quitRemark + '\'' +
                ", quitPayId=" + quitPayId +
                ", entryReqid='" + entryReqid + '\'' +
                ", quitReqid='" + quitReqid + '\'' +
                ", carPlateType=" + carPlateType +
                ", parkSourceData='" + parkSourceData + '\'' +
                ", payId='" + payId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Long getAccessId() {
        return accessId;
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public Long getEntryPassageId() {
        return entryPassageId;
    }

    public void setEntryPassageId(Long entryPassageId) {
        this.entryPassageId = entryPassageId;
    }

    public String getEntryPhoto() {
        return entryPhoto;
    }

    public void setEntryPhoto(String entryPhoto) {
        this.entryPhoto = entryPhoto;
    }

    public String getEntryOperator() {
        return entryOperator;
    }

    public void setEntryOperator(String entryOperator) {
        this.entryOperator = entryOperator;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Long getQuitPassageId() {
        return quitPassageId;
    }

    public void setQuitPassageId(Long quitPassageId) {
        this.quitPassageId = quitPassageId;
    }

    public String getQuitPhoto() {
        return quitPhoto;
    }

    public void setQuitPhoto(String quitPhoto) {
        this.quitPhoto = quitPhoto;
    }

    public String getQuitOperator() {
        return quitOperator;
    }

    public void setQuitOperator(String quitOperator) {
        this.quitOperator = quitOperator;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getQuitType() {
        return quitType;
    }

    public void setQuitType(String quitType) {
        this.quitType = quitType;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getQuitRemark() {
        return quitRemark;
    }

    public void setQuitRemark(String quitRemark) {
        this.quitRemark = quitRemark;
    }

    public Long getQuitPayId() {
        return quitPayId;
    }

    public void setQuitPayId(Long quitPayId) {
        this.quitPayId = quitPayId;
    }

    public String getEntryReqid() {
        return entryReqid;
    }

    public void setEntryReqid(String entryReqid) {
        this.entryReqid = entryReqid;
    }

    public String getQuitReqid() {
        return quitReqid;
    }

    public void setQuitReqid(String quitReqid) {
        this.quitReqid = quitReqid;
    }

    public Integer getCarPlateType() {
        return carPlateType;
    }

    public void setCarPlateType(Integer carPlateType) {
        this.carPlateType = carPlateType;
    }

    public String getParkSourceData() {
        return parkSourceData;
    }

    public void setParkSourceData(String parkSourceData) {
        this.parkSourceData = parkSourceData;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }*/
}
