package io.starsky.im.client.console;

import io.netty.channel.Channel;
import io.starsky.im.protocol.request.QuitGroupRequestPacket;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入groupId，退出群聊：");
        String groupId = scanner.next();
        channel.writeAndFlush(new QuitGroupRequestPacket(groupId));
    }
}
