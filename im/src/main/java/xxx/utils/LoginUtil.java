package xxx.utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author xin
 * @date 2019-10-03 16:43
 */
public class LoginUtil {
  public static void markAsLogin(Channel channel) {
    channel.attr(Attributes.LOGIN).set(true);
  }

  public static boolean hasLogin(Channel channel) {
    Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
    return attr.get() != null;
  }
}
