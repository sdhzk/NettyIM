package io.starsky.im.protocol.request;

import io.starsky.im.protocol.Packet;

import static io.starsky.im.protocol.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
