package xxx.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import xxx.protocol.command.impl.LoginRequestPacket;
import xxx.protocol.command.impl.LoginResponsePacket;
import xxx.protocol.command.Packet;
import xxx.protocol.command.PacketCodec;
import xxx.protocol.command.impl.MessageRequestPacket;
import xxx.protocol.command.impl.MessageResponsePacket;


import java.util.Date;

/**
 * @author xin
 * @date 2019-10-03 11:17
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {

    ByteBuf requestByteBuf = (ByteBuf) msg;

    // 先解码出实际的IM应用数据包
    Packet packet = PacketCodec.INSTANCE.decode(requestByteBuf);

    if (packet instanceof LoginRequestPacket) {
      // 登录流程
      System.out.println(new Date() + ": 客户端开始登录……");
      LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

      LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
      loginResponsePacket.setVersion(packet.getVersion());
      if (valid(loginRequestPacket)) {
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
    } else if (packet instanceof MessageRequestPacket) {
      // 处理消息
      MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
      System.out.println(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());

      MessageResponsePacket responsePacket = new MessageResponsePacket();
      responsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
      ByteBuf byteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), responsePacket);
      ctx.channel().writeAndFlush(byteBuf);
    }
  }

  private boolean valid(LoginRequestPacket loginRequestPacket) {
    return true;
  }
}
