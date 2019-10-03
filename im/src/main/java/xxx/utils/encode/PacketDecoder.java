package xxx.utils.encode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import xxx.protocol.command.PacketCodec;

import java.util.List;

/**
 * @author xin
 * @date 2019-10-03 18:24
 */
public class PacketDecoder extends ByteToMessageDecoder {
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
    out.add(PacketCodec.INSTANCE.decode(in));
  }
}
