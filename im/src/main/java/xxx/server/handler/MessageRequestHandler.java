package xxx.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.PacketCodec;
import xxx.protocol.command.impl.MessageRequestPacket;
import xxx.protocol.command.impl.MessageResponsePacket;

import java.util.Date;

/**
 * @author xin
 * @date 2019-10-03 20:21
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
    System.out.println(new Date() + ": 收到客户端消息：" + msg.getMessage());

    MessageResponsePacket responsePacket = new MessageResponsePacket();
    responsePacket.setMessage("服务端回复【" + msg.getMessage() + "】");
    ByteBuf byteBuf = PacketCodec.INSTANCE.encode(ctx.alloc(), responsePacket);
    ctx.channel().writeAndFlush(byteBuf);
  }
}
