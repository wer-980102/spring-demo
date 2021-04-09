package com.wer.parking.ws.handler;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author : chenxilin
 * @date : 2020-05-19 18:09:24
 * description : 通道池
 */
public class AlarmChannelHandlerPool {
    public AlarmChannelHandlerPool(){}

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
