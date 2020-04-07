package session;

import io.netty.channel.Channel;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by TOM
 * On 2020/4/7 17:58
 */
public class SessionUtil {

  /**
   * 超时 空闲连接 由idle去做
   */
  private static ConcurrentHashMap<Session, Channel> sessionMap = new ConcurrentHashMap<>();


  /**
   * 绑定session
   *
   * @param session
   * @param channel
   */
  public static void bindSession(Session session, Channel channel) throws Exception {
    //原子保证 get()
    Session localSession = channel.attr(AttributeKeyUtil.Session).get();
    if (localSession == null) {
      channel.attr(AttributeKeyUtil.Session).set(session);
      sessionMap.put(session, channel);
    } else if (localSession != session) {
      throw new Exception("localSession != session");
    }
  }

  /**
   * 解除绑定
   *
   * @param channel socket
   */
  public static void unBindSession(Channel channel) {
    Session session = channel.attr(AttributeKeyUtil.Session).get();
    sessionMap.remove(session);
    channel.attr(AttributeKeyUtil.Session).set(null);
  }

  /**
   *
   * @param channel
   * @return ture 登陆了 false 没登陆
   */
  public static Boolean checkLogin(Channel channel) {
    return channel.attr(AttributeKeyUtil.Session).get() != null;
  }

  public static Channel getChannelBySession(Session session) {
    return sessionMap.get(session);
  }

}
