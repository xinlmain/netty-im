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
  protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) {
    String fromUserId = msg.getFromUserId();
    String fromUserName = msg.getFromUsername();
    System.out.println(fromUserId + ":" + fromUserName + " -> " + msg.getMessage());
  }
}
