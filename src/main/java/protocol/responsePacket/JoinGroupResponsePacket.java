package protocol.responsePacket;

import static protocol.command.Command.JOIN_GROUP_RESPONSE;

import lombok.Data;
import protocol.Packet;

@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_RESPONSE;
    }
}
