package pkg04_EL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder    // 객체를 생성하는 방법
/*
 * @Builder 의 의미
 * Book.builder()
 *     .title()
 *     .author()
 *     .price()
 *     .build();
 */

public class Book {
  private String title;
  private String author;
  private int price;
}
