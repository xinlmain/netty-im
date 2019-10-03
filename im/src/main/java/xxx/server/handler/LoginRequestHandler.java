package xxx.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.PacketCodec;
import xxx.protocol.command.impl.LoginRequestPacket;
import xxx.protocol.command.impl.LoginResponsePacket;

import java.util.Date;

/**
 * @author xin
 * @date 2019-10-03 20:12
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
    // 登录流程
    System.out.println(new Date() + ": 客户端开始登录……");

    LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
    loginResponsePacket.setVersion(msg.getVersion());
    if (valid(msg)) {
      loginResponsePacket.setSuccess(true);
      System.out.println(new Date() + ": 登录成功!");
    } else {
      loginResponsePacket.setReason("账号密码校验失败");
      loginResponsePacket.setSuccess(false);
      System.out.println(new Date() + ": 登录失败!");
    }
    // 登录响应
    ByteBuf responseByteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
    ctx.channel().writeAndFlush(responseByteBuf);
  }

  private boolean valid(LoginRequestPacket loginRequestPacket) {
    return true;
  }
}
