package io.starsky.im.protocol.response;

import io.starsky.im.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static io.starsky.im.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupId;
    private String fromUser;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
