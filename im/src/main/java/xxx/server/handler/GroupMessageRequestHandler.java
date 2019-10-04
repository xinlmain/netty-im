package xxx.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import xxx.protocol.command.impl.GroupMessageRequestPacket;
import xxx.protocol.command.impl.GroupMessageResponsePacket;
import xxx.session.SessionUtil;

/**
 * @author xin
 * @date 2019-10-04 23:42
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {
    // 1.拿到 groupId 构造群聊消息的响应
    String groupId = msg.getToGroupId();
    GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
    responsePacket.setFromGroupId(groupId);
    responsePacket.setMessage(msg.getMessage());
    responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()).getUserName());


    // 2. 拿到群聊对应的 channelGroup，写到每个客户端
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
    channelGroup.writeAndFlush(responsePacket);
  }
}
