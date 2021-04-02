package com.wer.parking.model.es.param;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ParkingEsPayInfoParam implements Serializable {

    /** 出入主键Id **/
    private String pay_id;

    /** 车场Id  **/
    private String parking_id;

    /** 车场名称 **/
    private String parking_name;

    /** 业态 **/
    private String format;

    /** 车牌号 **/
    private String car_plate;

    /** 车牌类型：0.临时车 1.VIP车 2.月租车 3.充值车 4.时租车 5.产权车 6.计次车 7.贵宾卡 8.员工卡 9.大客车 100.预约车 101.其他 **/
    private String car_plate_type;

    /** 出场放行类型 0.正常放行1.免费放行2.异常放行3.遥控放行4.跟车放行5.虚拟放行6.长抬放行7.临时车异常放行 **/
    private String quit_type;

    /** 入场时间 **/
    private Date entry_time;

    /** 出场时间 **/
    private Date quit_time;

    /** 支付订单号 **/
    private String pay_order_num;

    /** 支付时间 **/
    private Date pay_time;


    /** 支付来源 **/
    private String pay_source;

    /** 支付终端 1现金 2 电子现金 3 微信 4 支付宝5 线上银联 6 ETC支付 8.其它  **/
    private String pay_client;

    /** 支付方式/支付渠道 1现金 2 电子现金 3 微信 4 支付宝5 线上银联 6 ETC支付 8.其它 **/
    private String pay_chanel;

    /** 应付金额 **/
    private double payment;

    /** 实付金额 **/
    private double actual_payment;

    /** 应找零 **/
    private String change;

    /** 实找零 **/
    private String actual_change;

    /** 找零方式/找零渠道 1现金 2 电子现金 3 微信 4 支付宝5 线上银联 6 ETC支付 8.其它 **/
    private Date change_chanel;

    /** 优免时间 **/
    private String free_time;

    /** 优免金额 **/
    private double free_money;

    /** 车场订单号 **/
    private String parking_order_num;

    /** 请求Id  **/
    private String reqId;

    /** 厂家 **/
    private String park_source_data;
}
