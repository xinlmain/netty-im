package xxx.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xxx.protocol.command.impl.LoginRequestPacket;
import xxx.protocol.command.impl.LoginResponsePacket;
import xxx.session.Session;
import xxx.session.SessionUtil;
import xxx.utils.LoginUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author xin
 * @date 2019-10-03 20:12
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
    // 登录流程
    System.out.println(new Date() + ": 客户端开始登录……");

    LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
    loginResponsePacket.setVersion(msg.getVersion());
    loginResponsePacket.setUsername(msg.getUsername());

    if (valid(msg)) {
      // client 和 server的channel attribute无关，服务端需要自己设置
      LoginUtil.markAsLogin(ctx.channel());
      loginResponsePacket.setSuccess(true);

      String userId = randomUserId();
      loginResponsePacket.setUserId(userId);
      SessionUtil.bindSession(new Session(userId, msg.getUsername()), ctx.channel());

      System.out.println(new Date() + ": 登录成功!");
    } else {
      loginResponsePacket.setReason("账号密码校验失败");
      loginResponsePacket.setSuccess(false);
      System.out.println(new Date() + ": 登录失败!");
    }

    // 登录响应
    ctx.channel().writeAndFlush(loginResponsePacket);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) {
    SessionUtil.unBindSession(ctx.channel());
  }

  private String randomUserId() {
    return UUID.randomUUID().toString().split("-")[0];
  }

  private boolean valid(LoginRequestPacket loginRequestPacket) {
    return true;
  }
}
