package com.wer.parking.model;

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
public class PakParkingPayInfo
{

    /** 主键id */
    private Long payId;

    private Long parkingId;
    /** 车牌号 */
    private String carPlate;

    /** 支付订单号 */
    private String payOrderNum;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /** 支付来源 */
    private String paySource;

    /** 支付终端 4.微信平台 5.支付宝平台 6.APP 7.无需缴费 8.其他 */
    private String payClient;

    /** 支付方式/支付渠道 1现金 2 银行卡 3 微信 4 支付宝5 城市E通卡6 充值车卡7.优惠券 8.其它 */
    private String payChanel;

    /** 应付金额 */
    private String payment;

    /** 实付金额 */
    private String actualPayment;

    /** 应找零 */
    private String change;

    /** 实找零 */
    private String actualChange;

    /** 找零方式/找零渠道 1现金 2 银行卡 3 微信 4 支付宝5 城市E通卡6 充值车卡7.优惠券 8.其它 */
    private String changeChenel;

    /** 优惠抵扣时长 */
    private String freeTime;

    /** 优惠抵扣金额 */
    private String freeMoney;

    /** 优惠券id */
    private Long couponId;

    /** 停车场订单号 */
    private String parkingOrderNum;

    /** 请求唯一标识 */
    private String reqid;

    private String createName;

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
