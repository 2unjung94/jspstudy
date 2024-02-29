package pkg06_upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

// fileSizeThreshold ; 메모리 사용값, maxFileSize ; 최대 파일크기 넘어가면 예외 발생, maxRequestSize ; 요청값의 크기가 이 값보다 크면 예외 발생
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 5,
                 maxRequestSize = 1024 * 1024 * 50)

public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  // 업로드 경로 (톰캣 내부 경로)
	  // getServletContext() : 서비스 시작부터 끝까지 가지고 있는 값??
	  String uploadPath = request.getServletContext().getRealPath("upload");
	  
	  File uploadDir = new File(uploadPath);
	  if(!uploadDir.exists()) {
	    uploadDir.mkdirs();
	  }
	  
	  String originalFilename = null;
	  String filesystemName = null;


	  // 첨부된 파일 정보
	  // getParts : form 태그로 전송된 input들..? 현재 상황에서는 text와 file. Collection은 for문 가능
	  Collection<Part> parts = request.getParts();
	  for(Part part : parts) {
	    // title, null, 8, null (null - 파일일때 갖는 값이므로 텍스트여서 null)
	    // file, 파일 타입, 463394, 파일이름
	    // System.out.println(part.getName() + ", " + part.getContentType() + ", " + part.getSize() + ", " + part.getSubmittedFileName());
	    
	    // header 값 (content-disposition : 콘텐츠 기질 -> 콘텐츠 타입을 의미)
	    // System.out.println(part.getHeader("Content-Disposition"));

	    if(part.getHeader("Content-Disposition").contains("filename")) {
	      if(part.getSize() > 0) {
	        originalFilename = part.getSubmittedFileName();	        
	      }
	    }
	    if(originalFilename != null) {
	      int point = originalFilename.lastIndexOf(".");
	      String extName = originalFilename.substring(point);  // .jpg
	      String fileName = originalFilename.substring(0, point);  // animal1
	      filesystemName = fileName + "_" + System.currentTimeMillis() + extName;
	    }
	    if(filesystemName != null) {
	      part.write(uploadPath + File.separator + filesystemName);
	    }
	  }
	  
	  // 응답
	  response.setContentType("text/html; charset=UTF-8");
	  PrintWriter out = response.getWriter();
	  out.println("<div><a href=\"/servlet/pkg06_upload/NewFile.html\">입력폼으로 돌아가기</a></div>");
	  out.println("<hr>");
	  out.println("<div>첨부파일명 : " + originalFilename + "</div>" );
	  out.println("<div>저장파일명 : " + filesystemName + "</div>");
	  out.println("<div>저장경로 : " + uploadPath + "</div>");
	  out.println("<hr>");
	  
	  File[] files = uploadDir.listFiles();
	  for(File file : files) {
	    // file.getName();  // 파일명_1234567890.jpg
	    String fileName1 = file.getName();
	    String ext = file.getName().substring(fileName1.lastIndexOf("."));
	    String fileName2 = file.getName().substring(0, fileName1.lastIndexOf("_"));
	    out.println("<div><a href=\"/servlet/download?filename=" + URLEncoder.encode(fileName1, "UTF-8") + "\">" + fileName2 + ext +"</a></div>");
	    
	  }
	  
//	  String[] savefileName = uploadDir.list();
//	  for( int i = 0 ; i < savefileName.length; i++ ) {
//	    
//	    out.println("<div><a href=\"servlet/download/filename=" + savefileName[i] + "\">"+ savefileName[i] + "</a></div>");
//	  }
//	  
	  out.flush();
	  out.close();
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
