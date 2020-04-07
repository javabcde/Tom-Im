package session;

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
  private Long userId;

  private String userName;
}
