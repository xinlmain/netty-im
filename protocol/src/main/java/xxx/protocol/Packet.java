package xxx.protocol;

import lombok.Data;

/**
 * @author xin
 * @date 2019-10-02 21:01
 */
@Data
public abstract class Packet {
  private Byte version = 1;

  public abstract Byte getCommand();
}
