package client.clientHandler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.responsePacket.MessageResponsePacket;

/**
 * Created by TOM
 * On 2020/4/9 11:41
 */
@Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

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
  protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
    System.out.println("收到来自用户[" + msg.getFromUserId() + "]的消息[" + msg.getMessage() + "]");
  }
}
