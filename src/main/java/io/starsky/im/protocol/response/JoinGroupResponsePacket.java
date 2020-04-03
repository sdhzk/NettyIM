package io.starsky.im.protocol.response;

import io.starsky.im.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static io.starsky.im.protocol.command.Command.JOIN_GROUP_RESPONSE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JoinGroupResponsePacket extends Packet {

    private String groupId;
    private boolean success;
    private String message;
    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
