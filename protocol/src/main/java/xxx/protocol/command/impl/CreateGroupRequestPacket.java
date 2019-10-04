package xxx.protocol.command.impl;

import lombok.Data;
import xxx.protocol.command.Commands;
import xxx.protocol.command.Packet;

import java.util.List;

/**
 * @author xin
 * @date 2019-10-04 22:45
 */
@Data
public class CreateGroupRequestPacket extends Packet {
  private List<String> userIds;

  @Override
  public Byte getCommand() {
    return Commands.CREATE_GROUP_REQUEST;
  }
}
