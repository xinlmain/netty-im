package xxx.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import xxx.protocol.command.impl.JoinGroupRequestPacket;
import xxx.protocol.command.impl.JoinGroupResponsePacket;
import xxx.session.SessionUtil;

/**
 * @author xin
 * @date 2019-10-04 23:30
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
    // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
    String groupId = msg.getGroupId();
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
    channelGroup.add(ctx.channel());

    // 2. 构造加群响应发送给客户端
    JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();

    responsePacket.setSuccess(true);
    responsePacket.setGroupId(groupId);
    ctx.channel().writeAndFlush(responsePacket);
  }
}
