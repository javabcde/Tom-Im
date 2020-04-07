package protocol.requestPacket;


import static protocol.command.Command.QUIT_GROUP_REQUEST;

import lombok.Data;
import protocol.Packet;

@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}
