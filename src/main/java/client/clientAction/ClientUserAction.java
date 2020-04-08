package client.clientAction;

import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * Created by TOM
 * On 2020/4/8 21:42
 */
public interface ClientUserAction {

  void userAction(Scanner scanner, Channel channel);
}
