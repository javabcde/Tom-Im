package protocol.responsePacket;


import static protocol.command.Command.HEARTBEAT_RESPONSE;

import protocol.Packet;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
