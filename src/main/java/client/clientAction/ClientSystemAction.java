package client.clientAction;


import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * Created by TOM
 * On 2020/4/8 21:10
 */
public interface ClientSystemAction {

  void clientExec(Scanner scanner, Channel channel) throws InterruptedException;
}
