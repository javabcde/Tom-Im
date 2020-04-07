package session;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by TOM
 * On 2020/4/7 15:32
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

  /**
   * 用户唯一Id
   */
  private String userId;

  private String userName;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Session session = (Session) o;

    return Objects.equals(userName, session.userName);
  }

  @Override
  public int hashCode() {
    int result = userId != null ? userId.hashCode() : 0;
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    return result;
  }
}
