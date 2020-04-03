package io.starsky.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.starsky.im.protocol.response.QuitGroupResponsePacket;

public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket response) {
        if (response.isSuccess()) {
            System.out.println("退出群[" + response.getGroupId() + "]成功");
        } else {
            System.out.println("退出群[" + response.getGroupId() + "]失败，原因：" + response.getReason());
        }

    }
}
