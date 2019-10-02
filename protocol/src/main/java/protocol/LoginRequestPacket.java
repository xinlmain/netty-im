package protocol;

import lombok.Data;

/**
 * @author xin
 * @date 2019-10-02 21:04
 */
@Data
public class LoginRequestPacket extends Packet {
  private Integer userId;
  private String username;
  private String password;

  @Override
  public Byte getCommand() {
    return Commands.LOGIN_REQUEST;
  }
}
