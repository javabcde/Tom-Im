package protocol.responsePacket;

import static protocol.command.Command.LOGIN_RESPONSE;

import lombok.Data;
import protocol.Packet;

@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGIN_RESPONSE;
    }
}
