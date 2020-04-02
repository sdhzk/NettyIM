package io.starsky.im.protocol.response;

import io.starsky.im.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static io.starsky.im.protocol.command.Command.CREATE_GROUP_RESPONSE;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateGroupResponsePacket extends Packet {

    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
