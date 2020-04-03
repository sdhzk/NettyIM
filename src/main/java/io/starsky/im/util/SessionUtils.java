package io.starsky.im.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.starsky.im.attribute.Attributes;
import io.starsky.im.session.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SessionUtils {
    private static final Map<String, Channel> USERID_CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> GROUPID_CHANNELGROUP_MAP = new ConcurrentHashMap<>();

    private SessionUtils() {

    }

    public static void bindSession(Session session, Channel channel) {
        USERID_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            USERID_CHANNEL_MAP.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session + " 退出登录");
        }
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return USERID_CHANNEL_MAP.get(userId);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        GROUPID_CHANNELGROUP_MAP.put(groupId, channelGroup);
    }

    public static void unBindChannelGroup(String groupId) {
        GROUPID_CHANNELGROUP_MAP.remove(groupId);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return GROUPID_CHANNELGROUP_MAP.get(groupId);
    }
}
