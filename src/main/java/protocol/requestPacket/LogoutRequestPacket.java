package protocol.requestPacket;


import static protocol.command.Command.LOGOUT_REQUEST;

import lombok.Data;
import protocol.Packet;


@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
