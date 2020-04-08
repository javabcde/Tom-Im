package codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

/**
 * Created by TOM
 * On 2020/4/7 16:35
 */
public class ImIdleHandler extends IdleStateHandler {

  private static final Long READER_IDLE_TIME = 15L;

  /**
   * 这些参数源码里面都有解释
   */
  public ImIdleHandler() {
    super(READER_IDLE_TIME, 0L, 0L, TimeUnit.SECONDS);
  }

  /**
   * Is called when an {@link IdleStateEvent} should be fired. This implementation calls
   * {@link ChannelHandlerContext#fireUserEventTriggered(Object)}.
   *
   */
  @Override
  protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
    System.out.println(READER_IDLE_TIME + "SECONDS 没有读取任何数据-断开连接");
    ctx.channel().close();
  }
}
