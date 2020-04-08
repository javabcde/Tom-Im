package client.clientHandler;

import client.clientAction.ClientUserAction;
import io.netty.channel.Channel;
import java.util.Scanner;
import protocol.requestPacket.MessageRequestPacket;

/**
 * Created by TOM
 * On 2020/4/8 21:47
 */
public class MessageToUserHandler implements ClientUserAction {

  @Override
  public void userAction(Scanner scanner, Channel channel) {
    System.out.println("请输入目标userId:");
    String userId = scanner.next();
    System.out.println("请输入想说的话:");
    String message = scanner.next();
    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
    messageRequestPacket.setToUserId(userId);
    messageRequestPacket.setMessage(message);
    channel.writeAndFlush(messageRequestPacket);
  }
}
