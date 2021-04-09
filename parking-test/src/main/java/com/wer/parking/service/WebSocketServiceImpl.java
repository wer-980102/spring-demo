package com.wer.parking.service;

import com.wer.parking.service.impl.IWebSocketService;
import com.wer.parking.ws.handler.AlarmWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements IWebSocketService {
    @Autowired(required=true)
    private AlarmWebSocketHandler alarmWebSocketHandler;

    /**
     * WebSocket推送大屏
     * @param data
     * @param state
     * @param tenantId
     * @param channelNumber
     * @throws Exception
     */
    @Override
    public void sendMessageByTenantId(String data, String state, String tenantId, String channelNumber) {
        try{
            alarmWebSocketHandler.sendMessageByTenantId(data,state,tenantId,channelNumber);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
