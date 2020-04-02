package io.starsky.im.client.console;

import io.netty.channel.Channel;
import io.starsky.im.protocol.request.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("【拉人群聊】输入userId列表，userId之间用英文逗号隔开：");
        String userIds = scanner.next();
        channel.writeAndFlush(new CreateGroupRequestPacket(Arrays.asList(userIds.split(","))));
    }
}
