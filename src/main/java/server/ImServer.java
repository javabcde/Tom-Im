package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Im 服务端
 * Created by TOM
 * On 2020/4/7 16:16
 */
public class ImServer {

  public static Integer PORT = 8888;

  public static void main(String[] args) {
    NioEventLoopGroup bossLoopGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerLoopGroup = new NioEventLoopGroup();
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    serverBootstrap
        .group(bossLoopGroup, workerLoopGroup)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG, 1024)
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childOption(ChannelOption.TCP_NODELAY, true)
        .childHandler(new ImServerInitializer());

    bind(serverBootstrap);
  }

  private static void bind(ServerBootstrap serverBootstrap) {
    serverBootstrap.bind(PORT).addListener(listener -> {
      if (listener.isSuccess()) {
        System.out.println("绑定端口[" + PORT + "]成功!!!!!");
      } else {
        PORT++;
        System.out.println("正在重新绑定端口[" + PORT + "]");
        bind(serverBootstrap);
      }
    });
  }
}
