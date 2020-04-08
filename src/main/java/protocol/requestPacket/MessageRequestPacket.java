package protocol.requestPacket;


import static protocol.command.Command.MESSAGE_REQUEST;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import protocol.Packet;


@Getter
@Setter
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
