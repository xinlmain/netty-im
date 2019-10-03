package xxx.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.impl.MessageResponsePacket;

import java.util.Date;

/**
 * @author xin
 * @date 2019-10-03 18:30
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
    System.out.println(new Date() + ": 收到服务端的消息：" + msg.getMessage());
  }
}
