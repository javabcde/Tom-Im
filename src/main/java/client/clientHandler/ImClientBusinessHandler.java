package client.clientHandler;

import static protocol.command.Command.LOGIN_RESPONSE;
import static protocol.command.Command.MESSAGE_RESPONSE;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.HashMap;
import java.util.Map;
import protocol.Packet;
import utils.BusinessThreadUtil;

/**
 * Created by TOM
 * On 2020/4/9 17:52
 */
@Sharable
public class ImClientBusinessHandler extends SimpleChannelInboundHandler<Packet> {

  public static ImClientBusinessHandler Instance;
  private static Map<Byte, SimpleChannelInboundHandler<? extends Packet>> businessMap = new HashMap<>();

  static {
    Instance = new ImClientBusinessHandler();
    businessMap.put(MESSAGE_RESPONSE, new MessageResponseHandler());
    businessMap.put(LOGIN_RESPONSE, new LoginResponseHandler());
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
        businessMap.get(msg.getCommand()).channelRead(ctx,msg);
      } catch (Exception e) {
        System.out.println("客户端业务线程报错"+e);
      }
    });
  }
}
