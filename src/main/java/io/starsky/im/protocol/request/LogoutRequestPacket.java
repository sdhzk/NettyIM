package io.starsky.im.protocol.request;

import io.starsky.im.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static io.starsky.im.protocol.command.Command.LOGOUT_REQUEST;

public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
