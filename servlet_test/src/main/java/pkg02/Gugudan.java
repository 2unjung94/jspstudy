package pkg02;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Gugudan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  
	  System.out.println(request.getParameter("data-a"));
//	  int a = Integer.parseInt(request.getParameter("data-a"));
//	  int b = Integer.parseInt(request.getParameter("data-b"));
//	  int result = a * b;
//	  int answer = Integer.parseInt(request.getParameter("answer"));
//	  
//    response.setContentType("text/html; charset=UTF-8");
//    PrintWriter out = response.getWriter();
//	  if(result == answer) {
//	     out.println("<script>");
//	     out.println("alert(\'정답입니다.\')");
//	     out.println("</script>");
//	  } else {
//	    out.println("<script>");
//	    out.println("alert(\'오답입니다.\')");
//	    out.println("</script>");    
//	  }
//	  out.flush();
//	  out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
