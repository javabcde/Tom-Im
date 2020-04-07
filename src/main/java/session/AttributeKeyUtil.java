package session;

import io.netty.util.AttributeKey;

/**
 * Created by TOM
 * On 2020/4/7 20:31
 */
public class AttributeKeyUtil {
  public static final AttributeKey<Session> Session = AttributeKey.newInstance("Session");

}
