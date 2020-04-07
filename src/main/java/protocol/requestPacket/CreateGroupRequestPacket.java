package protocol.requestPacket;


import static protocol.command.Command.CREATE_GROUP_REQUEST;

import java.util.List;
import lombok.Data;
import protocol.Packet;


@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
