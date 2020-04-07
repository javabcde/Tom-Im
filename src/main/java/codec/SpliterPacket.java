package codec;

import static protocol.PacketCodec.MAGIC_NUMBER;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * Created by TOM
 * On 2020/4/7 16:46
 */
public class SpliterPacket extends LengthFieldBasedFrameDecoder {

  private static final Integer LENGTH_FIELD_OFFSET = 7;
  private static final Integer LENGTH_FIELD_LENGTH = 4;

  /**
   * Creates a new instance.
   *
   * @param maxFrameLength    the maximum length of the frame.  If the length of the frame is
   *                          greater than this value, {@link TooLongFrameException} will be
   *                          thrown.
   * @param lengthFieldOffset the offset of the length field
   * @param lengthFieldLength
   */
  public SpliterPacket() {
    super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
  }

  /**
   * Create a frame out of the {@link ByteBuf} and return it.
   *
   * @param ctx the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
   * @param in  the {@link ByteBuf} from which to read data
   * @return frame           the {@link ByteBuf} which represent the frame or {@code null} if no frame could
   * be created.
   */
  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    //最开始的位置 读个魔数
    if (in.getInt(in.readerIndex()) != MAGIC_NUMBER) {
      ctx.channel().writeAndFlush("不是此协议的请求 拒接接受");
      ctx.channel().close();
      return null;
    }
    return super.decode(ctx, in);
  }
}
