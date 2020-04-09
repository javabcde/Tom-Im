package server.serverHandler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.requestPacket.MessageRequestPacket;
import protocol.responsePacket.MessageResponsePacket;
import session.AttributeKeyUtil;
import session.Session;
import session.SessionUtil;

/**
 * Created by TOM
 * On 2020/4/9 10:49
 */
@Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

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
  protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
    Session fromSession = ctx.channel().attr(AttributeKeyUtil.Session).get();
    System.out.println("服务端收到的消息为:" + JSON.toJSONString(msg));
    MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
    messageResponsePacket.setFromUserId(fromSession.getUserId());
    messageResponsePacket.setFromUserName(fromSession.getUserName());
    messageResponsePacket.setMessage(msg.getMessage());
    SessionUtil.getChannelBySessionUserId(msg.getToUserId()).writeAndFlush(messageResponsePacket);
  }
}
