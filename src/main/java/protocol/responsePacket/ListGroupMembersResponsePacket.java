package protocol.responsePacket;

import static protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

import java.util.List;
import lombok.Data;
import protocol.Packet;
import session.Session;


@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
