package xxx.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.impl.LoginResponsePacket;
import xxx.session.Session;
import xxx.session.SessionUtil;

import java.util.Date;

/**
 * @author xin
 * @date 2019-10-03 18:29
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) {
    if (msg.isSuccess()) {
      System.out.println(new Date() + ": 客户端登录成功");
      SessionUtil.bindSession(new Session(msg.getUserId(), msg.getUsername()), ctx.channel());
    } else {
      System.out.println(new Date() + ": 客户端登录失败，原因：" + msg.getReason());
    }
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    System.out.println("客户端连接被关闭");
  }
}
