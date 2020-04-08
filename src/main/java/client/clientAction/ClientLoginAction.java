package client.clientAction;

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
public class ClientLoginAction implements  ClientAction{

  @Override
  public void clientExec(Channel channel) {
    //进行登录操作
    Scanner scanner = new Scanner(System.in);
    new Thread(() -> {
      boolean threadRunFlag = false;
      while (!Thread.interrupted() && !threadRunFlag) {
        if (!SessionUtil.checkLogin(channel)) {
          //用户名#密码
          System.out.println("请输入用户名#密码");
          String[] userNameAndPass = scanner.next().split("#");
          LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
          loginRequestPacket.setUserName(userNameAndPass[0]);
          loginRequestPacket.setPassword(userNameAndPass[1]);
          channel.writeAndFlush(loginRequestPacket);
          Future<Boolean> booleanFuture = SessionUtil.checkCheckLogin(channel);
          try {
            threadRunFlag = booleanFuture.get(50, TimeUnit.SECONDS);
          } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }
}
