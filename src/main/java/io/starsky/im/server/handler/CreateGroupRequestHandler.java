package io.starsky.im.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.starsky.im.protocol.request.CreateGroupRequestPacket;
import io.starsky.im.protocol.response.CreateGroupResponsePacket;
import io.starsky.im.session.Session;
import io.starsky.im.util.IdUtils;
import io.starsky.im.util.SessionUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        Session currentSession = SessionUtils.getSession(ctx.channel());
        userNameList.add(currentSession.getUserName());
        channelGroup.add(ctx.channel());
        userIdList.remove(currentSession.getUserId());
        for (String userId : userIdList) {
            Channel channel = SessionUtils.getChannel(userId);
            if(channel != null){
                Session session = SessionUtils.getSession(channel);
                if(session != null){
                    userNameList.add(session.getUserName());
                    channelGroup.add(channel);
                }
            }
        }
        String groupId = IdUtils.randomId();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUserNameList(userNameList);

        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.print("群创建成功，id为["+groupId+"]，");
        System.out.println("群里面有："+userNameList);

        SessionUtils.bindChannelGroup(groupId, channelGroup);

    }
}




