package io.starsky.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.starsky.im.protocol.request.LoginRequestPacket;
import io.starsky.im.protocol.response.LoginResponsePacket;
import io.starsky.im.session.Session;
import io.starsky.im.util.IdUtils;
import io.starsky.im.util.SessionUtils;

import java.util.Date;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket request) {
        LoginResponsePacket response = new LoginResponsePacket();
        String userName = request.getUserName();
        response.setUserName(userName);
        if (valid(request)) {
            String userId = IdUtils.randomId();
            response.setUserId(userId);
            response.setSuccess(true);
            SessionUtils.bindSession(new Session(userId, userName), ctx.channel());
            System.out.println("[" + userName + "]登录成功");
        } else {
            response.setSuccess(false);
            response.setReason("账号密码校验失败");
            System.out.println(new Date() + ": 登录失败");
        }

        ctx.writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtils.unBindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket packet) {
        return true;
    }
}
