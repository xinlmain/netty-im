package xxx;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xin
 * @date 2019-10-01 16:50
 */
public class NettyClient {
  private static final int MAX_RETRY = 5;
  private static final String HOST = "127.0.0.1";
  private static final int PORT = 8000;

  public static void main(String[] args) throws InterruptedException {
    Bootstrap bootstrap = new Bootstrap();
    NioEventLoopGroup group = new NioEventLoopGroup();

    bootstrap.group(group)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<Channel>() {
          @Override
          protected void initChannel(Channel channel) throws Exception {
            //channel.pipeline().addLast(new StringEncoder());

            channel.pipeline().addLast(new FirstClientHandler());
          }
        });

//    Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
//
//    while (true) {
//      System.out.println("writing...");
//      channel.writeAndFlush(new Date() + ": hello netty!");
//      Thread.sleep(2000);
//    }

    connect(bootstrap, HOST, PORT, MAX_RETRY);
  }

  private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
    bootstrap.connect(host, port).addListener(future -> {
      if (future.isSuccess()) {
        System.out.println("连接成功!");
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

}
