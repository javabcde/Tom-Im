package client;

import static server.ImServer.PORT;
import static utils.BusinessThreadUtil.ImBusinessExec;

import client.clientAction.ClientBusinessActionCenter;
import client.clientAction.ClientLoginAction;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import session.SessionUtil;

/**
 * Created by TOM
 * On 2020/4/8 14:15
 */
public class ImClient {
  //true run;false norun
  private static boolean actionThreadFlag = true;

  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    ChannelFuture channelFuture = connectServer(bootstrap, nioEventLoopGroup,false);
    //阻塞
    channelFuture.channel().closeFuture().sync();
  }

  private static void UserActionThread(ChannelFuture channelFuture) {
    Channel channel = channelFuture.channel();
    Scanner scanner = new Scanner(System.in);
    ImBusinessExec.execute(() -> {
      System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId());
      while (actionThreadFlag) {
        if (SessionUtil.checkLogin(channel)) {
          //登陆了 进行业务指令操作
          ClientBusinessActionCenter clientBusinessAction = new ClientBusinessActionCenter();
          clientBusinessAction.clientExec(scanner, channel);
        } else {
          System.out.println("没有登录请进行登录");
          ClientLoginAction clientLoginAction = new ClientLoginAction();
          try {
            clientLoginAction.clientExec(scanner, channel);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }


  public static ChannelFuture connectServer(Bootstrap bootstrap, EventLoopGroup eventLoopGroup,boolean reConnect){
    ChannelFuture channelFuture = null;
    if (bootstrap != null) {
      bootstrap.group(eventLoopGroup)
          .channel(NioSocketChannel.class)
          .option(ChannelOption.SO_KEEPALIVE, true)
          .option(ChannelOption.TCP_NODELAY, true)
          .handler(new ImClientInitializer());
      channelFuture = bootstrap.connect("127.0.0.1", PORT).addListener((ChannelFuture futureListener) -> {
        final EventLoop eventLoop = futureListener.channel().eventLoop();
        if (!futureListener.isSuccess()) {
          System.out.println("与服务端断开连接!在3s之后准备尝试重连!");
          actionThreadFlag = !reConnect;
          eventLoop.schedule(() -> connectServer(new Bootstrap(), eventLoop,true), 3, TimeUnit.SECONDS);
        } else {
          System.out.println("连接服务器成功!!!");
          actionThreadFlag = true;
          UserActionThread(futureListener);
        }
      });
    }
    return channelFuture;
  }
}
