package protocol.responsePacket;


import static protocol.command.Command.MESSAGE_RESPONSE;

import lombok.Data;
import protocol.Packet;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
