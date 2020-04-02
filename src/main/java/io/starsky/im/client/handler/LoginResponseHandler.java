package io.starsky.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.starsky.im.protocol.response.LoginResponsePacket;
import io.starsky.im.session.Session;
import io.starsky.im.util.SessionUtils;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        String userName = loginResponsePacket.getUserName();
        if (loginResponsePacket.isSuccess()) {
            String userId = loginResponsePacket.getUserId();
            System.out.println("[" + userName + "]登录成功，userId：" + userId);
            SessionUtils.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println("[" + userName + "]登录失败，原因: " + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
