package io.starsky.im.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.starsky.im.protocol.request.MessageRequestPacket;
import io.starsky.im.protocol.response.MessageResponsePacket;
import io.starsky.im.session.Session;
import io.starsky.im.util.SessionUtils;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        Session session = SessionUtils.getSession(ctx.channel());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        Channel toUserChannel = SessionUtils.getChannel(messageRequestPacket.getToUserId());
        if(toUserChannel != null && SessionUtils.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
        }else{
            System.err.println("["+messageRequestPacket.getToUserId()+"]不在线，发送失败！");
        }
    }
}
