package xxx.utils;

import io.netty.util.AttributeKey;

/**
 * @author xin
 * @date 2019-10-03 16:41
 */
public interface Attributes {
  AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
