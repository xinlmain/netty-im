package xxx.session;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xin
 * @date 2019-10-03 22:55
 */
@Data
@AllArgsConstructor
public class Session {
  private String userId;
  private String userName;

  @Override
  public String toString() {
    return userId + ":" + userName;
  }
}
