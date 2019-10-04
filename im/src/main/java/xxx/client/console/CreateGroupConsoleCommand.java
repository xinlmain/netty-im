package xxx.client.console;

import io.netty.channel.Channel;
import xxx.protocol.command.impl.CreateGroupRequestPacket;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xin
 * @date 2019-10-04 22:41
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
  private static final String USER_ID_SPLITTER = ",";

  @Override
  public void exec(Scanner scanner, Channel channel) {
    CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
    System.out.println("若想【发起群聊】，请输入 userId 列表，userId之间用 , 隔开：");
    String userIds = scanner.next();
    createGroupRequestPacket.setUserIds(Arrays.asList(userIds.split(USER_ID_SPLITTER)));
    channel.writeAndFlush(createGroupRequestPacket);
  }
}
