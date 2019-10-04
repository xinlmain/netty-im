package xxx.protocol.command.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

/**
 * @author xin
 * @date 2019-10-03 16:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestPacket extends Packet {
  private String toUserId;
  private String message;

  @Override
  public Byte getCommand() {
    return Commands.MESSAGE_REQUEST;
  }
}
