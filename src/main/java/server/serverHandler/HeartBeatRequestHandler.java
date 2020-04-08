package server.serverHandler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.requestPacket.HeartBeatRequestPacket;
import protocol.responsePacket.HeartBeatResponsePacket;

/**
 * 业务心跳监测
 * Created by TOM
 * On 2020/4/7 22:39
 */
@Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

  public static HeartBeatRequestHandler Instance ;

  static {
    Instance = new HeartBeatRequestHandler();
  }

  /**
   * <strong>Please keep in mind that this method will be renamed to
   * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
   *
   * Is called for each message of type {@link I}.
   *
   * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
   *            belongs to
   * @param msg the message to handle
   * @throws Exception is thrown if an error occurred
   */
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
    ctx.writeAndFlush(new HeartBeatResponsePacket());
  }
}
