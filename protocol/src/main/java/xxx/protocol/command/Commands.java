package xxx.protocol.command;

/**
 * @author xin
 * @date 2019-10-02 21:05
 * 工具类，记录命令号
 */
public interface Commands {
  Byte LOGIN_REQUEST = 1;
  Byte LOGIN_RESPONSE = 2;
  Byte MESSAGE_REQUEST = 3;
  Byte MESSAGE_RESPONSE = 4;
}
