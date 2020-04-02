package io.starsky.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.starsky.im.protocol.response.JoinGroupResponsePacket;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket response) {
        if(response.isSuccess()){
            System.out.println(response.getMessage());
        }else{
            System.out.println("加入群["+response.getGroupId()+"]失败，原因："+response.getReason());
        }
    }
}
