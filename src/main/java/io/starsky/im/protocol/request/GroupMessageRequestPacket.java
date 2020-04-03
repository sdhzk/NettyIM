package io.starsky.im.protocol.request;

import io.starsky.im.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static io.starsky.im.protocol.command.Command.GROUP_MESSAGE_REQUEST;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupMessageRequestPacket extends Packet {
    private String groupId;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
