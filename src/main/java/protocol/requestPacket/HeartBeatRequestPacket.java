package protocol.requestPacket;


import static protocol.command.Command.HEARTBEAT_REQUEST;

import protocol.Packet;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
