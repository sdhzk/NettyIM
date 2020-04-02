package io.starsky.im.client.console;

import io.netty.channel.Channel;
import io.starsky.im.protocol.request.JoinGroupRequestPacket;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId，加入群聊：");
        String groupId = scanner.next();
        channel.writeAndFlush(new JoinGroupRequestPacket(groupId));
    }
}
