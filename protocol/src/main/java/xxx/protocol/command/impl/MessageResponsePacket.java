package xxx.protocol.command.impl;

import lombok.Data;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

/**
 * @author xin
 * @date 2019-10-03 16:36
 */
@Data
public class MessageResponsePacket extends Packet {
  private String message;
  private String fromUserId;
  private String fromUsername;

  @Override
  public Byte getCommand() {
    return Commands.MESSAGE_RESPONSE;
  }
}
