package xxx.protocol.command.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

/**
 * @author xin
 * @date 2019-10-04 23:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupRequestPacket extends Packet {
  private String groupId;

  @Override
  public Byte getCommand() {
    return Commands.JOIN_GROUP_REQUEST;
  }
}
