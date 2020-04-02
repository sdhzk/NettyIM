package io.starsky.im.protocol.response;

import io.starsky.im.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static io.starsky.im.protocol.command.Command.MESSAGE_RESPONSE;

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageResponsePacket extends Packet {

    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
