package xxx.protocol.command;

import lombok.Data;

/**
 * @author xin
 * @date 2019-10-02 21:01
 * IM应用的数据包
 */
@Data
public abstract class Packet {
  // 版本号
  private Byte version = 1;

  // 命令号
  public abstract Byte getCommand();
}
