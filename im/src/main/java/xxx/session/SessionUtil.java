package xxx.session;

import io.netty.channel.Channel;
import xxx.session.Session;
import xxx.utils.Attributes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xin
 * @date 2019-10-03 22:53
 */
public class SessionUtil {
  // userId -> channel的映射
  private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

  public static void bindSession(Session session, Channel channel) {
    userIdChannelMap.put(session.getUserId(), channel);
    channel.attr(Attributes.SESSION).set(session);
  }

  public static void unBindSession(Channel channel) {
    if (hasLogin(channel)) {
      userIdChannelMap.remove(getSession(channel).getUserId());
      channel.attr(Attributes.SESSION).set(null);
    }
  }

  public static boolean hasLogin(Channel channel) {
    return channel.hasAttr(Attributes.SESSION);
  }

  public static Session getSession(Channel channel) {
    return channel.attr(Attributes.SESSION).get();
  }

  public static Channel getChannel(String userId) {
    return userIdChannelMap.get(userId);
  }
}
