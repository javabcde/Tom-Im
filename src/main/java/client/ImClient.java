package client;

import cn.hutool.core.exceptions.ExceptionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import protocol.requestPacket.LoginRequestPacket;
import session.SessionUtil;

/**
 * Created by TOM
 * On 2020/4/8 14:15
 */
public class ImClient {

  private static Integer PORT = 8888;

  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(nioEventLoopGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.TCP_NODELAY, true)
        .handler(new ImClientInitializer());

    AtomicBoolean connectFlag = new AtomicBoolean(true);
    ChannelFuture sync = bootstrap.connect("127.0.0.1", PORT).addListener(listener -> {
      if (listener.isSuccess()) {
        System.out.println("连接服务器成功!!!");
        connectFlag.set(true);
      } else {
        //todo 失败逻辑后面再做
        connectFlag.set(false);
      }
    }).sync();
    if (connectFlag.get()) {
      Channel channel = sync.channel();
      if (SessionUtil.checkLogin(channel)) {
        //todo 登陆了 进行业务指令操作
      } else {
        //进行登录操作
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
          while (!Thread.interrupted()) {
            if (!SessionUtil.checkLogin(channel)) {
              //用户名#密码
              System.out.println("请输入用户名#密码");
              String[] userNameAndPass = scanner.next().split("#");
              LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
              loginRequestPacket.setUserName(userNameAndPass[0]);
              loginRequestPacket.setPassword(userNameAndPass[1]);
              channel.writeAndFlush(loginRequestPacket);
            }
          }
        }).start();
      }
    }
  }
}
