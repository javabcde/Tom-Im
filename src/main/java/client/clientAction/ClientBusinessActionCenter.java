package client.clientAction;

import client.clientHandler.MessageToUserHandler;
import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import protocol.command.Command;

/**
 * Created by TOM
 * On 2020/4/8 21:18
 */
public class ClientBusinessActionCenter implements ClientSystemAction {

  private static Map<String, ClientUserAction> chooseMap = new HashMap<>(16);

  static {
    chooseMap.put(Command.MESSAGE_REQUEST.toString(), new MessageToUserHandler());
    chooseMap.put(Command.LOGOUT_REQUEST.toString(),new LoginoutAction());
  }


  @Override
  public void clientExec(Scanner scanner, Channel channel) {
    System.out.println("请输入对应的指令进行操作:");
    String command = scanner.next();
    chooseMap.get(command).userAction(scanner, channel);
  }
}
