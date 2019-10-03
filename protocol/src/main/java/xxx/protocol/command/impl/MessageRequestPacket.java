package xxx.protocol.command.impl;

import lombok.Data;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

/**
 * @author xin
 * @date 2019-10-03 16:36
 */
@Data
public class MessageRequestPacket extends Packet {
  private String message;

  @Override
  public Byte getCommand() {
    return Commands.MESSAGE_REQUEST;
  }
}
