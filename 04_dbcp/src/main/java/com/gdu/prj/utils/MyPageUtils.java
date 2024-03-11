package com.gdu.prj.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MyPageUtils {
  
  private int total;        // 전체 게시글 개수                      (DB에서 구한다.)
  private int display;      // 한 페이지에 표시할 게시글 개수        (요청 파라미터로 받는다.ex) 10개씩 보기, 20개씩 보기 등)
  private int page;         // 현재 페이지 번호                      (요청 파라미터로 받는다, 파라미터 전달이 없을때는 1페이지)
  private int begin;        // 한 페이지에 표시할 게시글의 시작 번호 (계산한다. ex. 1페이지는 1번부터 20번 까지일 때 1번, 번호는 ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) )
  private int end;          // 한 페이지에 표시할 게시글의 종료 번호 (계산한다. ex. 1페이지는 1번부터 20번 까지일 때 20번, 번호는 ROW_NUMBER() OVER(ORDER BY BOARD_NO DES) )
  
  private int pagePerBlock = 10;    // 한 블록에 표시할 페이지 링크의 개수      (임의로 결정한다.)
  private int totalPage;            // 전체 페이지 개수                         (계산한다.)
  private int beginPage;            // 한 블록에 표시할 페이지 링크의 시작 번호 (계산한다.)
  private int endPage;              // 한 블록에 표시할 페이지 링크의 종료 번호 (계산한다.)
  
  public void setPaging(int total, int display, int page) {
    
    /*
     *  total = 1002
     *  display = 20
     *  
     *  page=1 ; 1  20
     *  page=2 ; 21 40
     *  page=3 ; 41 60
     *  ...
     *  page=50 ; 981 1000
     *  page=51 ; 1001 1020 <- 여기서 잘못된 계산. 보정이 꼭 필요하진 않다. (between 1001 and 1020 과 between 1001 and 1002 결과는 같다)
     * 
     */
    this.total = total;
    this.display = display;
    this.page = page;
    
    // begin, end 계산
    begin = (page - 1) * display + 1;
    end = begin + display - 1;
    
    
    /*
     * 1block 1 - 10
     * 2block 11 - 20
     * ...
     * 5block 41 - 50
     * 
     * totalPage = total / display
     * display 가 20 이고, total 이 1001 - 1019 면 totalPage 가 51이 되어야 함
     * 1001 / 20 = 50.05, ... 1019 / 20 = 50.95 이므로 올림처리 해야 함
     * total / display 를 double 로 변환 -> 올림(ceil) -> 다시 int 형변환
     * 
     * pagePerBlock - 한 블록에 생성할 페이지 링크의 개수
     * 
     * beginPage 는 pagePerBlock 과 관련
     * 1 - 10 페이지까지는 1블록이 나와야 함. ( (1 - 1 / 10) * 10 + 1 )
     * 
     * endPage 보정 필요
     * 1001 데이터는 51페이지 까지만 생성되어야 하는데 (endPage = beginPage + pagePerBlock - 1;)는 60까지 생성됨
     * totalPage 와 endPage 중 작은 것을 사용해야 함
     * 따라서 Math.min(totalPage, beginPage + pagePerBlock - 1)
     */
    // totalPage, begin_page, end_page 계산
    totalPage = (int)Math.ceil((double)total / display);
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
    endPage = Math.min(totalPage, beginPage + pagePerBlock - 1);
    
  }
  
  public String getPaging(String requestURI, String sort, int display) {
    
    StringBuilder builder = new StringBuilder();
    
    // < : 이전 블록
    if(beginPage == 1) {
      builder.append("<div class=\"dont-click\">&lt;</div>");      
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (beginPage - 1) + "&sort=" + sort + "&display=" + display + "\">&lt;</a></div>");
    }
    
    // 1 2 3 4 5 6 7 8 9 10 : 페이지
    for(int p = beginPage; p <= endPage ; p++) {
      if(p == page) {
        builder.append("<div><a class=\"current-page\" href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display +"\">" + p + "</a></div>");
      }else {
        builder.append("<div><a href=\"" + requestURI + "?page=" + p + "&sort=" + sort + "&display=" + display + "\">" + p + "</a></div>");        
      }
    }
    
    // > : 다음 블록
    if(endPage == totalPage) {
      builder.append("<div class=\"dont-click\">&gt;</div>");      
    } else {
      builder.append("<div><a href=\"" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "&display=" + display + "\">&gt;</a></div>");      
    }
       
    return builder.toString();
  }
}
