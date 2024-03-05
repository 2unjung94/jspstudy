package pkg03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Date extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    
    String reque = request.getParameter("date");
    
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    if(reque.equals("now-time")) {
      String nowTime = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
      out.println("<script>");
      out.println("alert(\'요청 결과는 " + nowTime + "입니다.\')");
      out.println("</script>");
      
    }else if(reque.equals("now-date")) {
      String nowDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
      out.println("<script>");
      out.println("alert(\'요청 결과는 " + nowDate + "입니다.\')");
      out.println("</script>");
      
    }
    out.flush();
    out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
