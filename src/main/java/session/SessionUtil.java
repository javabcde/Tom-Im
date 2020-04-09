package session;

import codec.BussinessCodec;
import io.netty.channel.Channel;
import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by TOM
 * On 2020/4/7 17:58
 */
public class SessionUtil {

  /**
   * 超时 空闲连接 由idle去做
   */
  private static ConcurrentMap<String, Channel> sessionMap = PlatformDependent.newConcurrentHashMap();

  public static ConcurrentMap<Channel, Integer> countLogin = PlatformDependent.newConcurrentHashMap();


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
      //肯定是没有登录
      channel.attr(AttributeKeyUtil.Session).set(session);
      sessionMap.put(session.getUserId(), channel);
      countLogin.put(channel, 1);
    } else if (localSession != session) {
      BussinessCodec bussinessCodec = new BussinessCodec("客户端已经登录成功 请不要重新登录", -1);
      channel.writeAndFlush(bussinessCodec);
      throw new Exception("localSession != session 客户端已经登录成功 但是强行多次登录 断开连接");
    }
  }

  /**
   * 解除绑定
   *
   * @param channel socket
   */
  public static void unBindSession(Channel channel) {
    Session session = channel.attr(AttributeKeyUtil.Session).get();
    sessionMap.remove(session.getUserId());
    channel.attr(AttributeKeyUtil.Session).set(null);
    countLogin.remove(channel);
  }

  /**
   * @param channel
   * @return ture 登陆了 false 没登陆
   */
  public static Boolean checkLogin(Channel channel) {
    return channel.attr(AttributeKeyUtil.Session).get() != null;
  }

  public static Channel getChannelBySessionUserId(String sessionUserId) {
    return sessionMap.get(sessionUserId);
  }

}
