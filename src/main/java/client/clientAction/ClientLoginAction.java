package client.clientAction;

import static session.SessionUtil.countLogin;

import io.netty.channel.Channel;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import protocol.requestPacket.LoginRequestPacket;
import session.SessionUtil;

/**
 * Created by TOM
 * On 2020/4/8 21:13
 */
public class ClientLoginAction implements ClientSystemAction {

  @Override
  public void clientExec(Scanner scanner, Channel channel) throws InterruptedException {
    //进行登录操作
    //用户名#密码
    System.out.println("请输入用户名#密码");
    String[] userNameAndPass = scanner.next().split("#");
    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
    loginRequestPacket.setUserName(userNameAndPass[0]);
    loginRequestPacket.setPassword(userNameAndPass[1]);
    countLogin.put(channel, 0);
    channel.writeAndFlush(loginRequestPacket);
    int retry = 5;
    while (retry > 0) {
      if (countLogin.get(channel) == 1) {
        return;
      } else {
        Thread.sleep(50);
        retry--;
      }
    }
  }
}
