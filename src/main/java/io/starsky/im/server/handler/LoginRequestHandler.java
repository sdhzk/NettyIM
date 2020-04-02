package io.starsky.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.starsky.im.protocol.request.LoginRequestPacket;
import io.starsky.im.protocol.response.LoginResponsePacket;
import io.starsky.im.session.Session;
import io.starsky.im.util.IdUtils;
import io.starsky.im.util.SessionUtils;

import java.util.Date;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        String userName = loginRequestPacket.getUserName();
        loginResponsePacket.setUserName(userName);
        if (valid(loginRequestPacket)) {
            String userId = IdUtils.randomId();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setSuccess(true);
            SessionUtils.bindSession(new Session(userId, userName), ctx.channel());
            System.out.println("["+userName+"]登录成功");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() + ": 登录失败");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtils.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket packet) {
        return true;
    }
}
