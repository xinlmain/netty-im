package xxx.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import xxx.protocol.command.Packet;
import xxx.protocol.command.PacketCodec;

/**
 * @author xin
 * @date 2019-10-03 18:32
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
  @Override
  protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) {
    out = PacketCodec.INSTANCE.encode(ctx.alloc(),msg);
  }
}
