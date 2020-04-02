package io.starsky.im.client.console;

import io.netty.channel.Channel;
import io.starsky.im.protocol.request.ListGroupMembersRequestPacket;

import java.util.Scanner;

public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId，获取群成员列表：");
        String groupId = scanner.next();
        channel.writeAndFlush(new ListGroupMembersRequestPacket(groupId));
    }
}
