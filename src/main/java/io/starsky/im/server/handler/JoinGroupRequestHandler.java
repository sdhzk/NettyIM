package io.starsky.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.starsky.im.protocol.request.JoinGroupRequestPacket;
import io.starsky.im.protocol.response.JoinGroupResponsePacket;
import io.starsky.im.session.Session;
import io.starsky.im.util.SessionUtils;

@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket request) {
        String groupId = request.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        JoinGroupResponsePacket response = new JoinGroupResponsePacket();
        response.setGroupId(groupId);
        if (channelGroup != null) {
            Session session = SessionUtils.getSession(ctx.channel());
            channelGroup.add(ctx.channel());
            response.setSuccess(true);
            response.setMessage(session.getUserName() + "，加入群聊[" + groupId + "]");
            channelGroup.writeAndFlush(response);
        } else {
            response.setSuccess(false);
            response.setReason("群组[" + groupId + "]不存在！");
            ctx.writeAndFlush(response);
        }
    }
}
