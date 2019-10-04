package xxx.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import xxx.protocol.command.impl.CreateGroupRequestPacket;
import xxx.protocol.command.impl.CreateGroupResponsePacket;
import xxx.session.SessionUtil;
import xxx.utils.IDUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xin
 * @date 2019-10-04 22:57
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
    List<String> userIds = msg.getUserIds();

    // 1. 创建一个 ChannelGroup，超厉害的，可以实现群发！
    ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

    // 2. 筛选出待加入群聊的用户的channel和username
    List<String> userNameList = new ArrayList<>();
    for (String userId : userIds) {
      Channel channel = SessionUtil.getChannel(userId);
      if (channel != null) {
        channelGroup.add(channel);
        userNameList.add(SessionUtil.getSession(channel).getUserName());
      }
    }

    // 3. 创建群聊创建结果的响应
    CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
    responsePacket.setSuccess(true);
    responsePacket.setGroupId(IDUtil.randomId());
    responsePacket.setUsernameList(userNameList);

    // 4. 给每个客户端发送拉群通知
    channelGroup.writeAndFlush(responsePacket);

    System.out.print("群聊创建成功，id为[" + responsePacket.getGroupId() + "], ");
    System.out.println("群里面有：" + responsePacket.getUsernameList());
  }
}
