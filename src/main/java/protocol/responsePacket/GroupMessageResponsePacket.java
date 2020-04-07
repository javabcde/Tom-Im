package protocol.responsePacket;


import static protocol.command.Command.GROUP_MESSAGE_RESPONSE;

import lombok.Data;
import protocol.Packet;
import session.Session;


@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {

        return GROUP_MESSAGE_RESPONSE;
    }
}
