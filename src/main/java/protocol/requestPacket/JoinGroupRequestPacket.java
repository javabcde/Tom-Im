package protocol.requestPacket;


import static protocol.command.Command.JOIN_GROUP_REQUEST;

import lombok.Data;
import protocol.Packet;


@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_REQUEST;
    }
}
