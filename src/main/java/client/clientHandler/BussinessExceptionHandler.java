package client.clientHandler;

import codec.BussinessCodec;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by TOM
 * On 2020/4/8 16:59
 */
@Sharable
public class BussinessExceptionHandler extends SimpleChannelInboundHandler<BussinessCodec> {
  public static BussinessExceptionHandler Instance;
  static {
    Instance = new BussinessExceptionHandler();
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
  protected void channelRead0(ChannelHandlerContext ctx, BussinessCodec msg) throws Exception {
    System.out.println(JSON.toJSONString(msg));
    ctx.channel().close();
  }
}
