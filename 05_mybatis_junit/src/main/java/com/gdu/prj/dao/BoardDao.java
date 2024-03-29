package com.gdu.prj.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj.dto.BoardDto;

public interface BoardDao {
  int insertBoard(BoardDto board);
  int updateBoard(BoardDto board);
  int deleteBoard(int board_no);    // 삭제할 게시글 번호만 필요
  List<BoardDto> selectBoardList(Map<String, Object> params);    // 게시글 리스트
  int getBoardCount();          // 게시글 총 개수
  BoardDto selectBoardByNo(int board_no);   // 상세보기
}
