package xxx;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author xin
 * @date 2019-10-01 16:34
 */
public class NettyServer {
  public static void main(String[] args) {
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    NioEventLoopGroup boss = new NioEventLoopGroup();
    NioEventLoopGroup worker = new NioEventLoopGroup();
    serverBootstrap
        .group(boss, worker)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<NioSocketChannel>() {
          @Override
          protected void initChannel(NioSocketChannel ch) throws Exception {
//            ch.pipeline().addLast(new StringDecoder());
//            ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//              @Override
//              protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
//                System.out.println(s);
//              }
//            });
            ch.pipeline().addLast(new LineBasedFrameDecoder(Integer.MAX_VALUE));
            ch.pipeline().addLast(new FirstServerHandler());
          }
        })
        .bind(8000);
  }
}
