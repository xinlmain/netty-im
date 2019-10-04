package xxx.client.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xin
 * @date 2019-10-04 22:35
 * 保存所有的指令
 */
public class ConsoleCommandManager implements ConsoleCommand {
  private Map<String, ConsoleCommand> consoleCommandMap;

  public ConsoleCommandManager() {
    this.consoleCommandMap = new HashMap<>();
    consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
    consoleCommandMap.put("logout", new LogoutConsoleCommand());
    consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
    consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
  }

  @Override
  public void exec(Scanner scanner, Channel channel) {
    // 获取第一个指令
    String command = scanner.next();

    ConsoleCommand consoleCommand = consoleCommandMap.get(command);
    if (consoleCommand != null) {
      consoleCommand.exec(scanner, channel);
    } else {
      System.out.println("无法识别[" + command +"]指令，请重新输入！");
    }
  }
}
