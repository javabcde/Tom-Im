package client;

import static server.ImServer.PORT;

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
import java.util.concurrent.TimeUnit;
import session.SessionUtil;

/**
 * Created by TOM
 * On 2020/4/8 14:15
 */
public class ImClient {



  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    ChannelFuture sync = connectServer(bootstrap,nioEventLoopGroup);
    sync.addListener(listener -> {
      if (listener.isSuccess()) {
        Channel channel = sync.channel();
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
          while (!Thread.interrupted()) {
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
        }).start();
      }
    });
  }

  public static ChannelFuture connectServer(Bootstrap bootstrap,EventLoopGroup eventLoopGroup){
    ChannelFuture channelFuture = null;
    if (bootstrap!=null){
      bootstrap.group(eventLoopGroup)
          .channel(NioSocketChannel.class)
          .option(ChannelOption.SO_KEEPALIVE, true)
          .option(ChannelOption.TCP_NODELAY, true)
          .handler(new ImClientInitializer());
      channelFuture = bootstrap.connect("127.0.0.1", PORT).addListener((ChannelFuture futureListener) -> {
        final EventLoop eventLoop = futureListener.channel().eventLoop();
        if (!futureListener.isSuccess()) {
          System.out.println("与服务端断开连接!在3s之后准备尝试重连!");
          eventLoop.schedule(() -> connectServer(new Bootstrap(), eventLoop),3, TimeUnit.SECONDS);
        }
      });
    }
    return channelFuture;
  }
}
