package client.clientAction;


import io.netty.channel.Channel;

/**
 * Created by TOM
 * On 2020/4/8 21:10
 */
public interface ClientAction {

  void clientExec(Channel channel);
}
