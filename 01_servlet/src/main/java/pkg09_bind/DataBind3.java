package pkg09_bind;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 데이터저장
       |
       |-----------------------------|
한번만저장                       여러번저장
HttpServletRequest                   |
(forward 사용)                       |
                                     |--------------------------|
                                 클라이언트관점              서버관점
                                 Cookie                      Session
 */
public class DataBind3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
    //  web 은 stateless(값을 저장할 수 없다). bind : 데이터를 저장하는 의미

    /*
     * 데이터 저장 영역 (Session, ServletRequest 두개 위주로 공부)
     * 
     * 1. ServletContext       : Context 종료(=애플리케이션 실행 종료) 전까지 데이터를 유지한다.
     * 2. HttpSession          : Session 종료(=웹 브라우저 종료) 전까지 데이터를 유지한다.
     * 3. HttpServletRequest   : 요청 종료(=응답) 전까지 데이터를 유지한다.
     */
    
    /*
     * 데이터 처리 메소드
     * 1. setAttribute(속성, 값) : Object 타입의 값을 저장한다.
     * 2. getAttribute(속성)     : Object 타입의 값을 반환한다. (캐스팅이 필요할 수 있다)
     * 3. removeAttribute(속성)  : 속성을 제거한다.
     */
    
    // 암기 : request 에 저장하고 나서 forward 로 연결할 것
    // HttpServletRequest에 데이터 저장하기
    request.setAttribute("c", "일반데이터");
    
    // 데이터 확인 페이지로 이동하기
    // response.sendRedirect("/servlet/dataConfirm"); // 페이지 이동은 되나 데이터는 저장되지 않는다.
    request.getRequestDispatcher("/dataConfirm").forward(request, response);    // forward 는 request 를 유지한 채 페이지 이동하는 것
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }


}
