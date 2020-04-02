package io.starsky.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.starsky.im.protocol.response.GroupMessageResponsePacket;

public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket response) throws Exception {
        String fromGroupId = response.getFromGroupId();
        String fromUser = response.getFromUser();
        System.out.println("收到群["+fromGroupId+"]中["+fromUser+"]发来的消息："+response.getMessage());
    }
}
