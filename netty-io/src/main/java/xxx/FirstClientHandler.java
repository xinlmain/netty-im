package xxx;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author xin
 * @date 2019-10-01 22:33
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println(new Date() + ": 客户端写出数据");

    ByteBuf buffer = getByteBuf(ctx);

    ctx.channel().writeAndFlush(buffer);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf byteBuf = (ByteBuf) msg;

    System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
  }

  private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
    ByteBuf buffer = ctx.alloc().buffer();
    byte[] bytes = "你好, netty!".getBytes(Charset.forName("utf-8"));
    buffer.writeBytes(bytes);

    return buffer;
  }
}
