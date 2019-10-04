package xxx.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.impl.CreateGroupResponsePacket;

/**
 * @author xin
 * @date 2019-10-04 23:23
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
    System.out.print("群创建成功，id 为[" + msg.getGroupId() + "], ");
    System.out.println("群里面有：" + msg.getUsernameList());
  }
}
