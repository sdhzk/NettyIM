package io.starsky.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.starsky.im.protocol.request.GroupMessageRequestPacket;
import io.starsky.im.protocol.response.GroupMessageResponsePacket;
import io.starsky.im.util.SessionUtils;
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();
    private GroupMessageRequestHandler() {

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket request) throws Exception {
        String groupId = request.getGroupId();
        GroupMessageResponsePacket response = new GroupMessageResponsePacket();
        response.setFromGroupId(groupId);
        response.setFromUser(SessionUtils.getSession(ctx.channel()).getUserName());
        response.setMessage(request.getMessage());
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.writeAndFlush(response);
    }
}
