package com.gdu.prj.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj.dto.BoardDto;

public interface BoardDao {
  int insertBoard(BoardDto board);
  int updateBoard(BoardDto board);
  int deleteBoard(int board_no);    // 삭제할 게시글 번호만 필요
  int deleteBoards(String param);    // 삭제할 게시글 번호들 IN(3,2,1)에서 3,2,1 을 말하는 것이 String board_no
  List<BoardDto> selectBoardList(Map<String, Object> map);    // 게시글 리스트
  int getBoardCount();          // 게시글 총 개수
  BoardDto selectBoardByNo(int board_no);   // 상세보기
  void close();   // close 들 모아놓은 메소드
}
