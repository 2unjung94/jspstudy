package pkg05_redirect;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Destination1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
    /*
     * redirect
     * <첫번째 요청 : 글작성(insert) 해주세요>
     * <두번째 요청 : 글 작성(insert) 목록(select) 페이지 보여줌>
     * 
     * 1. 이동할 때 요청과 응답 모두 전달되지 않는다.
     * 2. 이동 경로를 작성할 때 contextPath 부터 작성해야 한다.
     * 3. 클라이언트는 redirect 경로를 확인할 수 있다. 
     * 4. redirect 를 사용하는 경우
     *    1) 쿼리 insert
     *    2) 쿼리 update
     *    3) 쿼리 delete
     *    
     *   <redirect 예시>
     *    사용자     ->    회사    ->     부서
     *    1) 사용자가 회사 "대표번호"로 요구사항 요청              : 대표번호 - contextPath + URLMapping
     *    2) 회사에서 요구사항에 관련된 부서의 "부서번호"를 응답   : 부서번호 - contextPath + URLMapping1
     *    3) 사용자가 전달받은 "부서번호"로 재요청                 : 부서번호 - contextPath + URLMapping1
     */
	  request.setCharacterEncoding("UTF-8");
	  String luggage = request.getParameter("luggage");
	  
	// redirect 예시중 2번. luggage 가 한글(김치)이어서 URLEncoder 사용해야 함
	  response.sendRedirect("/servlet/destination2?luggage=" + URLEncoder.encode(luggage, "UTF-8"));
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
