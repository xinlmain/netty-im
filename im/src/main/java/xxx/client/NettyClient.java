package xxx.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import xxx.client.console.ConsoleCommandManager;
import xxx.client.console.LoginConsoleCommand;
import xxx.client.handler.CreateGroupResponseHandler;
import xxx.client.handler.JoinGroupResponseHandler;
import xxx.client.handler.LoginResponseHandler;
import xxx.client.handler.MessageResponseHandler;
import xxx.session.SessionUtil;
import xxx.utils.encode.PacketDecoder;
import xxx.utils.encode.PacketEncoder;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author xin
 * @date 2019-10-02 22:30
 */
public class NettyClient {
  private static final int MAX_RETRY = 5;
  private static final String HOST = "127.0.0.1";
  private static final int PORT = 8000;

  public static void main(String[] args) {
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    Bootstrap bootstrap = new Bootstrap();
    bootstrap
        .group(workerGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.TCP_NODELAY, true)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          public void initChannel(SocketChannel ch) {
            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
            ch.pipeline().addLast(new PacketDecoder());
            ch.pipeline().addLast(new LoginResponseHandler());
            ch.pipeline().addLast(new MessageResponseHandler());
            ch.pipeline().addLast(new CreateGroupResponseHandler());
            ch.pipeline().addLast(new JoinGroupResponseHandler());
            ch.pipeline().addLast(new PacketEncoder());
          }
        });

    connect(bootstrap, HOST, PORT, MAX_RETRY);
  }

  @SuppressWarnings("Duplicates")
  private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
    bootstrap.connect(host, port).addListener(future -> {
      if (future.isSuccess()) {
        System.out.println(new Date() + ": 连接成功!");
        Channel channel = ((ChannelFuture) future).channel();
        // 连接成功后，启动控制台线程
        startConsoleThread(channel);
      } else if (retry == 0) {
        System.err.println("重试次数已用完，放弃连接！");
      } else {
        // 第几次重连
        int order = (MAX_RETRY - retry) + 1;
        // 本次重连的间隔
        int delay = 1 << order;
        System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
        bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
            .SECONDS);
      }
    });
  }

  private static void startConsoleThread(Channel channel) {
    Scanner sc = new Scanner(System.in);
    ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
    LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

    new Thread(() -> {
      while (!Thread.interrupted()) {
        if (!SessionUtil.hasLogin(channel)) {
          loginConsoleCommand.exec(sc, channel);
        } else {
          consoleCommandManager.exec(sc, channel);
        }
      }
    }).start();
  }
}
