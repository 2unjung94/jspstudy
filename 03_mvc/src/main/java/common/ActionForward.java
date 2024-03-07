package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
// @Data 에는 getter, setter, toString 등 들어있다.
@Data
@Builder

public class ActionForward {
  private String view;
  private boolean isRedirect;     // boolean 의 초기화 값은 false(=0)
}
