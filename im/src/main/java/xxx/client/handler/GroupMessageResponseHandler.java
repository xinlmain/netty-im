package xxx.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.impl.GroupMessageResponsePacket;

/**
 * @author xin
 * @date 2019-10-05 0:50
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {

  }
}
