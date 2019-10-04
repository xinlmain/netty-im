package xxx.protocol.command.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

import java.util.List;

/**
 * @author xin
 * @date 2019-10-04 23:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupResponsePacket extends Packet {
  private boolean success;
  private List<String> usernameList;
  private String groupId;

  @Override
  public Byte getCommand() {
    return Commands.CREATE_GROUP_RESPONSE;
  }
}
