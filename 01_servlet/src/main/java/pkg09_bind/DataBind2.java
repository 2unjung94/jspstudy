package pkg09_bind;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DataBind2 extends HttpServlet {
	
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
	  
	  // HttpSession에 데이터 저장하기
	  HttpSession session = request.getSession();
	  session.setAttribute("b", "로그인정보");
	  
	  // 세션 유지시간 설정하기 (디폴트 30분)
	  session.setMaxInactiveInterval(60 * 60);   // 초 단위 (60*60 = 1시간), -1과 같은 음수를 전달하면 무한으로 활용 가능(=세션 유지시간이 없어짐) 
	  /*
	   * web.xml 파일을 이용한 세션 유지시간 설정하기 (30분)
	   * 
	   * <web-app xmlns="" version="">
	   *   <session-config>
	   *     <session-timeout>30</session-timeout>
	   *   </session-config>
	   * </web-app>
	   */
	  
	  // 세션 정보 삭제하기
	  // session.invalidate();
	  
	  
	  // 데이터 확인 페이지로 이동하기
	  response.sendRedirect("/servlet/dataConfirm");
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
