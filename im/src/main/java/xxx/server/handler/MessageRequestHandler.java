package xxx.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.impl.MessageRequestPacket;
import xxx.protocol.command.impl.MessageResponsePacket;
import xxx.session.SessionUtil;
import xxx.session.Session;

import java.util.Date;

/**
 * @author xin
 * @date 2019-10-03 20:21
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
    System.out.println(new Date() + ": 收到客户端消息：" + msg.getMessage());

    // 1. 拿到消息发送方的会话信息
    Session session = SessionUtil.getSession(ctx.channel());

    // 2. 通过消息发送方的会话信息构造要发送的消息
    MessageResponsePacket responsePacket = new MessageResponsePacket();
    responsePacket.setFromUserId(session.getUserId());
    responsePacket.setFromUsername(session.getUserName());
    responsePacket.setMessage(msg.getMessage());

    // 3. 拿到消息接受方的channel
    Channel toUserChannel = SessionUtil.getChannel(msg.getToUserId());

    // 4. 将消息发送给消息接收方
    if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
      toUserChannel.writeAndFlush(responsePacket);
    } else {
      System.out.println("[" + msg.getToUserId() + "] 不在线，发送失败！");
    }
  }
}
