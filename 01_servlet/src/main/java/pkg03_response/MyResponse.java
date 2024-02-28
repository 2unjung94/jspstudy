package pkg03_response;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
 	  // 1. 응답 Content-Type 설정 + UTF-8 인코딩
 	  //    1) HTML : text/html
 	  //    2) XML  : application/xml
 	  //    3) JSON : application/json
 	  response.setContentType("text/html; charset=UTF-8");    // 세미콜론(;)으로 구분
 	  
 	  // 2. 응답 출력 스트림 알아내기(문자 출력 스트림)(Java 에서 데이터를 내보낸다 : 출력스트림)
 	  PrintWriter out = response.getWriter();   // getWriter()는 PrintWriter 객체 반환. PrintWriter는 예외 회피가 되어있어서 try-catch 필요 없다.
 	  
 	  // 3. 응답 만들기
 	  // Java in HTML(Servlet) : Java에서 HTML 태그를 문자열로 작성
 	  // HTML in Java(JSP)     : HTML 태그 안에서 Java 코드 작성
 	  out.println("<!DOCTYPE html>");
 	  out.println("<html lang=\"ko\">");
 	  out.println("<head>");
 	  out.println("<meta charset=\"UTF-8\">");
 	  out.println("<title>Insert title here</title>");
 	  out.println("</head>");
 	  out.println("<body>");
 	  out.println("<h1>안녕하세요</h1>");
 	  out.println("</body>");
 	  out.println("</html>");
 	  out.flush();
 	  out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
