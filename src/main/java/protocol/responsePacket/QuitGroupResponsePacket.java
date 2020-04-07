package protocol.responsePacket;


import static protocol.command.Command.QUIT_GROUP_RESPONSE;

import lombok.Data;
import protocol.Packet;


@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }
}
