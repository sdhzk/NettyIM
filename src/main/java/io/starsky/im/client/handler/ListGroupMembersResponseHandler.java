package io.starsky.im.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.starsky.im.protocol.response.ListGroupMembersResponsePacket;

import java.util.List;
import java.util.stream.Collectors;

public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket response) {
        if (response.isSuccess()) {
            List<String> userList = response.getSessionList().stream()
                    .map(session -> session.getUserId() + ":" + session.getUserName())
                    .collect(Collectors.toList());
            System.out.println("群[" + response.getGroupId() + "]中的人包括：" + userList);
        } else {
            System.out.println(response.getReason());
        }
    }
}
