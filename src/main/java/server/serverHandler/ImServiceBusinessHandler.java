package server.serverHandler;

import static protocol.command.Command.MESSAGE_REQUEST;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.HashMap;
import java.util.Map;
import protocol.Packet;
import utils.BusinessThreadUtil;

/**
 * 业务处理
 * Created by TOM
 * On 2020/4/8 13:55
 */
@Sharable
public class ImServiceBusinessHandler extends SimpleChannelInboundHandler<Packet> {

  public static ImServiceBusinessHandler Instance;

  private static Map<Byte, SimpleChannelInboundHandler<? extends Packet>> businessMap = new HashMap<>(16);

  static {
    Instance = new ImServiceBusinessHandler();
    businessMap.put(MESSAGE_REQUEST, new MessageRequestHandler());
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
  protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
    BusinessThreadUtil.ImBusinessExec.execute(() -> {
      try {
        businessMap.get(msg.getCommand()).channelRead(ctx, msg);
      } catch (Exception e) {
        System.out.println("channelReadException");
      }
    });
  }
}
