package com.wer.parking.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 出入口信息对象 pak_passage
 * 
 * @author wsp
 * @date 2020-12-23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PakPassage
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long passageId;

    /** 所属停车场id */
    private Long parkingId;

    /** 通道编码 */
    private String passageCode;

    /** 通道IP地址 */
    private String passageIp;

    /** 出入口名称 */
    private String passageName;

    /** 出入口类型 1出口 2入口 */
    private String passageType;

    /** 请求唯一标识 */
    private String reqid;

    /** 出入口状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

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
