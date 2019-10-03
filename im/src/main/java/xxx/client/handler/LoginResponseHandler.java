package xxx.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.PacketCodec;
import xxx.protocol.command.impl.LoginRequestPacket;
import xxx.protocol.command.impl.LoginResponsePacket;
import xxx.utils.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author xin
 * @date 2019-10-03 18:29
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    System.out.println(new Date() + ": 客户端开始登录");

    // 创建登录对象
    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
    loginRequestPacket.setUserId(UUID.randomUUID().toString());
    loginRequestPacket.setUsername("flash");
    loginRequestPacket.setPassword("pwd");

    // 编码
    ByteBuf buffer = PacketCodec.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

    // 写数据
    ctx.channel().writeAndFlush(buffer);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
    if (msg.isSuccess()) {
      System.out.println(new Date() + ": 客户端登录成功");
      LoginUtil.markAsLogin(ctx.channel());
    } else {
      System.out.println(new Date() + ": 客户端登录失败，原因：" + msg.getReason());
    }
  }
}
