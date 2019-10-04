package xxx.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xin
 * @date 2019-10-04 22:34
 */
public interface ConsoleCommand {
  void exec(Scanner scanner, Channel channel);
}
