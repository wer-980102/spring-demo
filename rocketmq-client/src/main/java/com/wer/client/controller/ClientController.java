package com.wer.client.controller;

import com.wer.client.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @RequestMapping("/clientInfo")
    public String getClientInfo(@RequestParam("message")String message)throws Exception{
        String clientInfo = clientService.getClientInfo(message);
        System.out.println("推送时间:"+new Date());
        return clientInfo;
    }
}
