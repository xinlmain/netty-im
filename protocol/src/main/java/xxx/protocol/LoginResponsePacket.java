package xxx.protocol;

import lombok.Data;

/**
 * @author xin
 * @date 2019-10-02 22:42
 */
@Data
public class LoginResponsePacket extends Packet {
  private boolean success;
  private String reason;

  @Override
  public Byte getCommand() {
    return Commands.LOGIN_RESPONSE;
  }
}
