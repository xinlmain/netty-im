package xxx.protocol.command.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

/**
 * @author xin
 * @date 2019-10-04 23:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupResponsePacket extends Packet {
  private boolean success;
  private String groupId;
  private String reason;

  @Override
  public Byte getCommand() {
    return Commands.JOIN_GROUP_RESPONSE;
  }
}
