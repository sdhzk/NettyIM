package io.starsky.im.protocol.response;

import io.starsky.im.protocol.Packet;
import io.starsky.im.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static io.starsky.im.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;
    private boolean success;
    private String reason;
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
