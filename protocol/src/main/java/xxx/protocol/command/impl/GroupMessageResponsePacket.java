package xxx.protocol.command.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

/**
 * @author xin
 * @date 2019-10-04 23:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageResponsePacket extends Packet {
  private String fromGroupId;
  private String fromUser;
  private String message;

  @Override
  public Byte getCommand() {
    return Commands.GROUP_MESSAGE_RESPONSE;
  }
}
