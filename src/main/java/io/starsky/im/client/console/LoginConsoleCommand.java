package io.starsky.im.client.console;

import io.netty.channel.Channel;
import io.starsky.im.protocol.request.LoginRequestPacket;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请入用户名登录：");
        String userName = scanner.next();
        channel.writeAndFlush(new LoginRequestPacket(userName, "123"));
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
