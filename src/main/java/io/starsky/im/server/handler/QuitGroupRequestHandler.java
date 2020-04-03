package io.starsky.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.starsky.im.protocol.request.QuitGroupRequestPacket;
import io.starsky.im.protocol.response.QuitGroupResponsePacket;
import io.starsky.im.util.SessionUtils;

@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket request) {
        String groupId = request.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        QuitGroupResponsePacket response = new QuitGroupResponsePacket();
        response.setGroupId(groupId);
        System.out.println(channelGroup);
        if (channelGroup != null) {
            channelGroup.remove(ctx.channel());
            response.setSuccess(true);

            if (channelGroup.isEmpty()) {
                SessionUtils.unBindChannelGroup(groupId);
            }
        } else {
            response.setSuccess(false);
            response.setReason("群组[" + groupId + "]不存在！");
        }
        ctx.writeAndFlush(response);
    }
}
