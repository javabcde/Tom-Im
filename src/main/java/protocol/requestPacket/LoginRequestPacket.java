package protocol.requestPacket;

import static protocol.command.Command.LOGIN_REQUEST;

import lombok.Data;
import protocol.Packet;


@Data
public class LoginRequestPacket extends Packet {
    private String userName;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}
