package pkg01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    
    String id = request.getParameter("id");
    String pw1 = request.getParameter("pw1");
    String pw2 = request.getParameter("pw2");
    String name = request.getParameter("name");
    String birth = request.getParameter("year") + "년" + request.getParameter("date") + "월" + request.getParameter("day") + "일";
    String gender = request.getParameter("gender");
    String email = request.getParameter("email");
    String phone = request.getParameter("con-num") + " " + request.getParameter("phone");

    
    response.setContentType("text/html; charset=UTF-8");

    PrintWriter out = response.getWriter();
    if(id.equals("")) {
      out.println("<script>");
      out.println("alert(\'아이디를 입력하세요')");
      out.println("</script>");
      return;
    }else if(!pw1.equals(pw2) || pw1.equals("")) {
      out.println("<script>");
      out.println("alert(\'비밀번호를 확인하세요\')");
      out.println("</script>");
      return;
    }
    
    out.println("<ul>");
    out.println("<li>아이디 : " + id + "</li>");
    out.println("<li>비밀번호 : " + pw1 + "</li>");
    out.println("<li>이름 : " + name + "</li>");
    out.println("<li>생년월일 : " + birth + "</li>");
    out.println("<li>성별 : " + gender + "</li>");
    out.println("<li>이메일 : " + email + "</li>");
    out.println("<li>휴대전화 : " + phone + "</li>");

    out.flush();
    out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
