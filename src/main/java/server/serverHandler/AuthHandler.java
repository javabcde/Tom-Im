package server.serverHandler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import session.SessionUtil;

/**
 * Created by TOM
 * On 2020/4/8 13:47
 */
@Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

  public static AuthHandler Instance;

  static {
    Instance = new AuthHandler();
  }
  /**
   * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
   * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
   *
   * Sub-classes may override this method to change behavior.
   *
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (SessionUtil.checkLogin(ctx.channel())){
      System.out.println("用户已经登录");
      super.channelRead(ctx,msg);
    }else {
      //nothing todo 准备进行关闭连接
    }
  }
}
