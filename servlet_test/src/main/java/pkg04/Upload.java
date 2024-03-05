package pkg04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  request.setCharacterEncoding("UTF-8");
	  
	  String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	  String writer = request.getParameter("writer");
	  String title = request.getParameter("title");
	  String contents = request.getParameter("contents");
	  
    String uploadName = now + "-" + writer + "-" + title + ".txt";
    
	  // String uploadPath = request.getServletContext().getRealPath("upload");
	  
	  
	  File uploadDir = new File("\\upload");
	   if(!uploadDir.exists()) {
	     uploadDir.mkdirs();
	   }
	   
	   // 업로드 파일 객체
	   File uploadFile = new File(uploadDir, uploadName);
	   
	   // 문자 출력 스트림 생성
	   BufferedWriter fileWriter = new BufferedWriter(new FileWriter(uploadFile));
	   
	   // 문자 출력 스트림으로 데이터 내보내기
	   fileWriter.write(contents + "\n");
	   fileWriter.flush();
	   fileWriter.close();
	   
     response.setContentType("text/html; charset=UTF-8");
     PrintWriter out = response.getWriter();
     out.println("<script>");
     out.println("alert(\'" + uploadName + "파일이 생성되었습니다.\')");
     out.println("</script>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
