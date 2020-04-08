package server;

import codec.SpliterPacket;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import server.serverHandler.AuthHandler;
import server.serverHandler.HeartBeatRequestHandler;
import codec.ImIdleHandler;
import server.serverHandler.LoginRequestHandler;
import server.serverHandler.PacketCodecHandler;

/**
 * Created by TOM
 * On 2020/4/7 16:21
 */
public class ImServerInitializer extends ChannelInitializer<NioSocketChannel> {

  @Override
  protected void initChannel(NioSocketChannel ch) throws Exception {
    //todo 后续这里业务改成异步
    ChannelPipeline pipeline = ch.pipeline();
    //监测空连接 这个源码还没看 我只是会用.....
    pipeline.addLast(new ImIdleHandler());
    //拆包
    pipeline.addLast(new SpliterPacket());
    //转成对应的实体
    pipeline.addLast(PacketCodecHandler.Instance);
    //以下进行各种业务前处理
    //登录请求处理
    pipeline.addLast(LoginRequestHandler.Instance);
    pipeline.addLast(HeartBeatRequestHandler.Instance);
    //验证是否有权限进行后续逻辑操作
    pipeline.addLast(AuthHandler.Instance);
    //todo 业务处理

  }
}
