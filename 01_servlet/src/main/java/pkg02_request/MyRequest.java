package pkg02_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

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
	  
	  // 2. 요청 파라미터
	  //   1) 모든 파라미터는 String 타입이다.
	  //   2) 파라미터가 없으면 null 이다.
	  //   3) 파라미터 값이 없으면 ""(빈 문자열) 이다.
	  
	  /* if 문을 이용한 [ null & 빈 문자열 ] 처리 */
	  String strNumber = request.getParameter("number"); // 10은 문자열로 저장됨
	  int number = 0;
	  if(strNumber != null && !strNumber.isEmpty())   // null 과 빈문자열 조건 
	    number = Integer.parseInt(strNumber);    // 문자열 10을 int 로 변환
	  System.out.println(number);	    
	  
	  
	  /* Optional<T> 클래스를 이용한 [ null & 빈 문자열 ] 처리 */
	  String strNumber2 = request.getParameter("number2");
	  Optional<String> opt = Optional.ofNullable(strNumber2);
	  double number2 = Double.parseDouble(opt.orElse("0").isEmpty() ? "0" : opt.orElse("0"));    //strNumber2 가 null, 빈문자열이면 0 이다.
	  System.out.println(number2);
	  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // <form>의 POST 전송
	  // 1. 요청 UTF-8 인코딩 (가장 먼저)
	  request.setCharacterEncoding("UTF-8");
	  
	  // 2. 요청 파라미터
	  //   1) name 속성이 파라미터이다.
	  //   2) 동일한 name 속성을 가진 입력 요소들은 다음과 같이 처리한다.
	  //     (1) type="radio" : 변수 처리한다. (radio 는 하나만 선택 가능하기 때문)
	  //     (2) 이외의 경우  : 배열 처리한다.
	  //   3) radio, checkbox 는 value 속성이 필요하다 (value 속성이 없으면 on, off 값이 전달됨)
	  
	  String name = request.getParameter("name");
	  String email = request.getParameter("email");
	  String gender = request.getParameter("gender");
	  
	  String[] hobbies = request.getParameterValues("hobbies");
	  String[] mobile = request.getParameterValues("mobile");
	  
	  System.out.println(name);      // 입력란 텍스트 출력 - 입력이 없으면 빈문자열
	  System.out.println(email);     // 입력란 텍스트 출력 - 입력이 없으면 빈문자열
	  System.out.println(gender);    // value 속성 값 출력 - 선택이 없다(=전달이 없다) null
	  System.out.println(Arrays.toString(hobbies));    // value 속성 값 출력 - 선택이 없다(=전달이 없다) null
	  System.out.println(Arrays.toString(mobile));    // [value 속성 값, 입력란 텍스트 출력] - [1, 빈문자열] <option> 태그의 내부 택스트가 넘어옴, value 속성이 있으면 value 속성값
	}

}
