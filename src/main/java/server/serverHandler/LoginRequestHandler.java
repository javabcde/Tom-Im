package server.serverHandler;

import cn.hutool.core.util.IdUtil;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocol.requestPacket.LoginRequestPacket;
import protocol.responsePacket.LoginResponsePacket;
import session.Session;
import session.SessionUtil;

/**
 * 登录处理类
 * Created by TOM
 * On 2020/4/7 17:26
 */
@Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

  public static LoginRequestHandler Instance;

  static {
    Instance = new LoginRequestHandler();
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
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
    LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
    loginResponsePacket.setUserName(msg.getUserName());
    loginResponsePacket.setVersion(msg.getVersion());
    if (vailid(msg) || !SessionUtil.checkLogin(ctx.channel())) {
      loginResponsePacket.setUserId(IdUtil.fastUUID().split("-")[0]);
      loginResponsePacket.setSuccess(true);
      SessionUtil.bindSession(new Session(loginResponsePacket.getUserId(),loginResponsePacket.getUserName()),ctx.channel());
      System.out.println("用户[" + msg.getUserName() + "]登录成功!!!!");
    } else {
      loginResponsePacket.setSuccess(false);
      loginResponsePacket.setReason("用户名密码不对 或者 用户已经登录了");
    }
    //直接可以通知 缩短流程
    ctx.writeAndFlush(loginResponsePacket);
  }

  private boolean vailid(LoginRequestPacket msg) {
    //todo 没有处理逻辑
    return true;
  }
}
