package xxx.utils;

import io.netty.util.AttributeKey;
import xxx.session.Session;

/**
 * @author xin
 * @date 2019-10-03 16:41
 */
public interface Attributes {
  AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
  AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
