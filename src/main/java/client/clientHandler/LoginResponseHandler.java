package client.clientHandler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.responsePacket.LoginResponsePacket;
import session.Session;
import session.SessionUtil;

/**
 * 登录响应处理
 * Created by TOM
 * On 2020/4/8 15:36
 */
@Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

  public static LoginResponseHandler Instance;

  static {
    Instance = new LoginResponseHandler();
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
  protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
    if (msg.isSuccess()) {
      System.out.println("客户端处理响应登录成功" + JSON.toJSONString(msg));
      //成功
      SessionUtil.bindSession(new Session(msg.getUserId(), msg.getUserName()), ctx.channel());

    } else {
      //失败
      System.out.println(msg.getVersion());
      //todo 先断连
      ctx.channel().close();
    }
  }
}
