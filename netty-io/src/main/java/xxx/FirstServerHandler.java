package xxx;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author xin
 * @date 2019-10-01 22:41
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf byteBuf = (ByteBuf) msg;
    System.out.println(new Date() + ": 服务端读到数据 -> "
        + byteBuf.toString(Charset.forName("utf-8")));

//    System.out.println(new Date() + ": 服务端写出数据");
//    ByteBuf buf = getByteBuf(ctx);
//    ctx.channel().writeAndFlush(buf);
  }

  private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
    ByteBuf buf = ctx.alloc().buffer();
    byte[] bytes = "你好，我是xxx，欢迎关注我的微信公众号！".getBytes(StandardCharsets.UTF_8);
    buf.writeBytes(bytes);
    return buf;
  }

//  @Override
//  public void channelActive(ChannelHandlerContext ctx) throws Exception {
//    System.out.println(new Date() + ": 服务端写出hello");
//    //ByteBuf buf = getByteBuf(ctx);
//    ctx.channel().writeAndFlush("hello, 你来啦！");
//  }
}
