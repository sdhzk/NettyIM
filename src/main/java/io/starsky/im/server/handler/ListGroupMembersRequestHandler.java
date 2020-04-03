package io.starsky.im.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.starsky.im.protocol.request.ListGroupMembersRequestPacket;
import io.starsky.im.protocol.response.ListGroupMembersResponsePacket;
import io.starsky.im.session.Session;
import io.starsky.im.util.SessionUtils;

import java.util.List;
import java.util.stream.Collectors;
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();
    private ListGroupMembersRequestHandler() {

    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket request) {
        String groupId = request.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        ListGroupMembersResponsePacket response = new ListGroupMembersResponsePacket();
        response.setGroupId(groupId);
        if(channelGroup != null){
            List<Session> sessionList = channelGroup.stream().map(SessionUtils::getSession).collect(Collectors.toList());
            response.setSuccess(true);
            response.setSessionList(sessionList);
        }else{
            response.setSuccess(false);
            response.setReason("群组["+groupId+"]不存在！");
        }
        ctx.writeAndFlush(response);
    }
}
