package protocol.responsePacket;

import static protocol.command.Command.LOGOUT_RESPONSE;

import lombok.Data;
import protocol.Packet;


@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
    }
}
