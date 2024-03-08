package com.gdu.prj.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardFilter extends HttpFilter implements Filter {

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    // HttpServletRequest / HttpServletResponse 으로 ServletRequest, ServeletResponse 를 다운캐스팅
    HttpServletRequest req = (HttpServletRequest)request;
    HttpServletResponse res = (HttpServletResponse)response;
    
    // 요청 UTF-8 인코딩
    req.setCharacterEncoding("UTF-8");

    // 요청 방식 + 요청 주소 확인
    System.out.print(String.format("%-4s", req.getMethod()));
    System.out.print(" | ");
    System.out.println(req.getRequestURI());
    
    // 요청 파라미터 확인 (String, String[] 은 약속되어 있음)  
    Map<String, String[]> params = req.getParameterMap();
    for(Entry<String, String[]> param : params.entrySet()) {
      System.out.print(String.format("%7s", " "));
      System.out.print(String.format("%-10s", param.getKey())+ " : ");
      System.out.println(Arrays.toString(param.getValue()));
    }
    
    // response 뒤에 페이지들이 동작하지 않으려면 여기서 응답을 만들어야 한다. (like 로그인이 필요한 페이지들)
		chain.doFilter(request, response);
		
	}

	
}
