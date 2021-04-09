package com.wer.parking.service.impl;


public interface IWebSocketService {

    /**
     * 停车场数据发送给大屏
     * param: data:数据，tenantId：租户，channelNumber:通道Ip
     * @return
     */
     void sendMessageByTenantId(String data,String state, String tenantId,String channelNumber) ;
}
