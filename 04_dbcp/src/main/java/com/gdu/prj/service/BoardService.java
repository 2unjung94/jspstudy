package com.gdu.prj.service;

import com.gdu.prj.common.ActionForward;

import jakarta.servlet.http.HttpServletRequest;

public interface BoardService {
  ActionForward addBoard(HttpServletRequest request);
  ActionForward getBoardList(HttpServletRequest request);   // 리스트보기
  ActionForward getBoardByNo(HttpServletRequest request);   // 상세보기
  ActionForward editBoard(HttpServletRequest request);      // 편집 화면(편집기) 보여주는 서비스
  ActionForward modifyBoard(HttpServletRequest request);    // 편집 후 화면 보여주는 서비스
  ActionForward removeBoard(HttpServletRequest request);
}
