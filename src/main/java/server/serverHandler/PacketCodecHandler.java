package server.serverHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.io.IOException;
import java.util.List;
import protocol.Packet;
import protocol.PacketCodec;

/**
 * 解码以及编码处理
 * Created by TOM
 * On 2020/4/7 17:03
 */
@Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {
  public static PacketCodecHandler Instance;

  static {
    Instance = new PacketCodecHandler();
  }

  /**
   * @param ctx
   * @param msg
   * @param out
   * @see MessageToMessageEncoder#encode(ChannelHandlerContext, Object, List)
   */
  @Override
  protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
    //ctx.alloc().ioBuffer()#ctx.channel().alloc().ioBuffer()这两个看了下源码 好像是一样的
    ByteBuf buffer = ctx.channel().alloc().ioBuffer();
    PacketCodec.INSTANCE.encode(buffer, msg);
    out.add(buffer);
  }

  /**
   * @param ctx
   * @param msg
   * @param out
   */
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
    //经过拆包了 这肯定是一个对象
    out.add(PacketCodec.INSTANCE.decode(msg));
  }

  /**
   * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
   * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
   *
   * Sub-classes may override this method to change behavior.
   *
   * @param ctx
   * @param cause
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    if (cause instanceof IOException){
      System.out.println(cause.getMessage());
    }
  }
}
