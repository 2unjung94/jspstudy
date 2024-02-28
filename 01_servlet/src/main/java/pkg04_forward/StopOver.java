package pkg04_forward;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class StopOver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    /*
     * forward
     * 
     * 1. 이동할 때 요청과 응답 모두 전달한다.
     * 2. 이동 경로를 작성할 때 contextPath는 제외하고 작성해야 한다. (URLMapping만 작성한다.)
     * 3. 클라이언트는 forward 경로를 확인할 수 없다. (주소창에서 확인 불가) 
     * 4. forward 를 사용하는 경우
     *    1) 단순 이동 
     *    2) 쿼리 select
     *    
     *   <forward 예시>
     *    사용자     ->    회사    ->     부서
     *    1) 사용자가 회사 "대표번호"로 요구사항 요청              : 대표번호 - contextPath + URLMapping
     *    2) 회사에서 요구사항에 관련된 부서를 "내선번호"로 요청   : 내선번호 - URLMapping
     *    3) 사용자는 내선번호를 모름
     *    4) 사용자 -> 회사 : 프론트 부분
     *    5) 회사 -> 부서 : 백엔드 부분
     */
    
    request.getRequestDispatcher("/destination").forward(request, response); 
    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
