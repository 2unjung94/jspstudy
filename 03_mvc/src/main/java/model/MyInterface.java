package model;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface MyInterface {
  // public, abstract 생략 가능
  //뷰에서 컨트롤러에게 요청한 request 객체를 통째로 모델에게 전달 
  
  ActionForward getDate(HttpServletRequest request);   
  ActionForward getTime(HttpServletRequest request);
  ActionForward getDateTime(HttpServletRequest request);
}
