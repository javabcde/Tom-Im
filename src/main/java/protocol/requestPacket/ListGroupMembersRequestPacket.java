package protocol.requestPacket;


import static protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

import lombok.Data;
import protocol.Packet;


@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
