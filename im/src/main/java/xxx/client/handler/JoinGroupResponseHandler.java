package xxx.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.impl.JoinGroupResponsePacket;

/**
 * @author xin
 * @date 2019-10-04 23:33
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
    if (msg.isSuccess()) {
      System.out.println("加入群[" + msg.getGroupId() + "]成功!");
    } else {
      System.err.println("加入群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
    }
  }
}
