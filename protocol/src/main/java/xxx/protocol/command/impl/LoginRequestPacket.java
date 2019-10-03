package xxx.protocol.command.impl;

import lombok.Data;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

/**
 * @author xin
 * @date 2019-10-02 21:04
 */
@Data
public class LoginRequestPacket extends Packet {
  private String userId;
  private String username;
  private String password;

  @Override
  public Byte getCommand() {
    return Commands.LOGIN_REQUEST;
  }
}
