package client;

import codec.SpliterPacket;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import codec.ImIdleHandler;
import server.serverHandler.PacketCodecHandler;

/**
 * Created by TOM
 * On 2020/4/8 15:21
 */
public class ImClientInitializer extends ChannelInitializer<NioSocketChannel> {

  /**
   * This method will be called once the {@link Channel} was registered. After the method returns this instance
   * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
   *
   * @param ch the {@link Channel} which was registered.
   * @throws Exception is thrown if an error occurs. In that case it will be handled by
   *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
   *                   the {@link Channel}.
   */
  @Override
  protected void initChannel(NioSocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    //监测空连接 这个源码还没看 我只是会用.....
    pipeline.addLast(new ImIdleHandler());
    //拆包
    pipeline.addLast(new SpliterPacket());
    //转成对应的实体
    pipeline.addLast(PacketCodecHandler.Instance);
    //处理登录响应


  }
}
