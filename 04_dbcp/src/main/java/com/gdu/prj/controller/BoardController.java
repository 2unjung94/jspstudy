package com.gdu.prj.controller;

import java.io.IOException;

import com.gdu.prj.service.BoardService;
import com.gdu.prj.service.BoardServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * view - controller - service - dao - db
 * 
 * controller 는 service 만 호출한다
 */

// 테이블당 하나의 컨트롤러(Servlet)을 만드는게 좋다
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	// controller 는 service 를 호출한다
	private BoardService boradService = new BoardServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
