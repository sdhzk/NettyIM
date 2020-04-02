package io.starsky.im.protocol.response;

import io.starsky.im.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static io.starsky.im.protocol.command.Command.LOGIN_RESPONSE;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponsePacket extends Packet {
    private String userId;
    private String userName;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
