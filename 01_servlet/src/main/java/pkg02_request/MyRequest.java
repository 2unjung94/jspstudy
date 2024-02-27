package pkg02_request;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyRequest
 */
public class MyRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  // 이 부분 매우매우 중요
		// 1. 요청 UTF-8 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  // 2. 요청 파라미터 (모든 파라미터는 문자열(String)이다) 
	  String strNumber = request.getParameter("number"); // 10은 문자열로 저장됨
	  int number = Integer.parseInt(strNumber);    // 문자열 10을 int 로 변환
	  System.out.println(number);
	  
	  String strNumber2 = request.getParameter("number2");
	  double number2 = Double.parseDouble(strNumber2);
	  System.out.println(number2);
	  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
