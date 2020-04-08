package codec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import protocol.Packet;
import protocol.command.Command;

/**
 * Created by TOM
 * On 2020/4/8 16:52
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BussinessCodec extends Packet {

  private String reason;
  private Integer code;
  @Override
  public Byte getCommand() {
    return Command.BUSSINESS_CODEC;
  }
}
