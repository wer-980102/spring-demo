package com.wer.parking.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 停车场信息对象 pak_parking
 * 
 * @author wsp
 * @date 2020-12-21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PakParking
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long parkingId;

    /** 停车场Code */
    private Long parkingCode;

    /** 停车场名称 */
    private String parkingName;

    /** 地址 */
    private String address;

    /** 车场所在经度 */
    private String lon ;

    /** 车场所在纬度 */
    private String lat ;

    /** 总车位数 */
    private String totalStall;

    /** 空闲车位数 */
    private String freeStall;

    /** 请求唯一标识 */
    private String reqid;

    /** 停车场状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 租户id */
    private Long tenantId;

    /** 数据来源 */
    private String parkSourceData;

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
}
