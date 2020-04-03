package io.starsky.im.protocol.response;

import io.starsky.im.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static io.starsky.im.protocol.command.Command.LOGOUT_RESPONSE;

@Data
@EqualsAndHashCode(callSuper = true)
public class LogoutResponsePacket extends Packet {
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
