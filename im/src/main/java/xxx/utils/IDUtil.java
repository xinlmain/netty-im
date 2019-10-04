package xxx.utils;

import java.util.UUID;

/**
 * @author xin
 * @date 2019-10-04 23:10
 */
public class IDUtil {
  public static String randomId() {
    return UUID.randomUUID().toString().split("-")[0];
  }
}
