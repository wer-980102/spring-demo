package com.wer.parking.model.es.param;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ParkingEsAccessInfoParam implements Serializable {

    /** 出入主键Id **/
    private String access_id;

    /** 车牌号 **/
    private String car_plate;

    /** 车场Id  **/
    private String parking_id;

    /** 车场名称 **/
    private String parking_name;

    /** 总车位数 **/
    private String total_stall;

    /** 剩余车位数 **/
    private String free_stall;

    /** 业态 **/
    private String format;

    /** 入口Id **/
    private String entry_passage_id;

    /** 入口名称 **/
    private String entry_passage_name;

    /** 入场抓拍 **/
    private String entry_photo;

    /** 入场操作人 **/
    private String entry_operator;

    /** 入场时间 **/
    private Date entry_time;

    /** 出口Id **/
    private String quit_passage_id;

    /** 出口名称 **/
    private String quit_passage_name;

    /** 出就抓拍 **/
    private String quit_photo;

    /** 出场操作人 **/
    private String quit_operator;

    /** 出场时间 **/
    private Date quit_time;

    /** 车牌类型 **/
    private String car_plate_type;

    /** 入场放行类型 0.正常放行1.免费放行2.异常放行3.遥控放行4.跟车放行5.虚拟放行6.长抬放行7.临时车异常放行 **/
    private String entry_type;

    /** 出场放行类型 0.正常放行1.免费放行2.异常放行3.遥控放行4.跟车放行5.虚拟放行6.长抬放行7.临时车异常放行 **/
    private String quit_type;

    /** 车辆类型 **/
    private String car_type;

    /** 放行说明 **/
    private String quit_remark;

    /** 入场请求唯一标识 **/
    private String entry_reqId;

    /** 出场请求唯一标识 **/
    private String quit_reqId;

    /** 厂家 **/
    private String park_source_data;

    /** 缴费Id **/
    private String pay_id;

}
