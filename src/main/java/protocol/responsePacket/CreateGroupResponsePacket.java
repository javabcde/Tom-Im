package protocol.responsePacket;


import static protocol.command.Command.CREATE_GROUP_RESPONSE;

import java.util.List;
import lombok.Data;
import protocol.Packet;


@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_RESPONSE;
    }
}
