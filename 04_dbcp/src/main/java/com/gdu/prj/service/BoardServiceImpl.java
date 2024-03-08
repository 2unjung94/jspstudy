package com.gdu.prj.service;

import java.util.List;
import java.util.Optional;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dao.BoardDaoImpl;
import com.gdu.prj.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;

/*                                  **      **
 * view - [filter] - controller - service - dao - db   
 */

public class BoardServiceImpl implements BoardService {
  
  // service 는 dao 를 호출한다. (singleton 패턴으로 dao 를 만들었기 때문에 .getInstance() 메소드 호출)
  private BoardDao boardDao = BoardDaoImpl.getInstance(); 

  @Override
  public ActionForward addBoard(HttpServletRequest request) {
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    BoardDto board = BoardDto.builder()
                          .title(title)
                          .contents(contents).build();
    int insertCount = boardDao.insertBoard(board);
    
    // redirect 경로는 URLMapping 으로 작성한다. 파일 경로 아님 (암기)
    String view = null;
    if(insertCount == 1) {
      view = request.getContextPath() + "/board/list.brd";
    } else if(insertCount == 0) {
      view = request.getContextPath() + "/main.brd";
    }
    // INSERT 이후 이동은 redirect (암기)
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward getBoardList(HttpServletRequest request) {
    int boardCount = boardDao.getBoardCount();
    List<BoardDto> boardList = boardDao.selectBoardList(null);
    request.setAttribute("boardCount", boardCount);
    request.setAttribute("boardList", boardList);
    return new ActionForward("/board/list.jsp", false);
  }

  // 상세보기
  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {
    
    // forward 일 때 request 에 데이터를 저장하고 .jsp 로 이동
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    
    // db 결과 값 받아오기
    BoardDto board = boardDao.selectBoardByNo(board_no);      // board 가 null 이면 해당되는 게시글 번호가 없다는 뜻이다.
    String view = null;
    if(board != null) {
      view = "/board/detail.jsp";
      request.setAttribute("board", board);
    } else {
      view = "/index.jsp";
    }
    return new ActionForward(view, false);
  }

  // editBoard : 편집 화면으로 넘어가는 메소드 -> select 필요
  @Override
  public ActionForward editBoard(HttpServletRequest request) {
    // board_no 의 null 가능성은 없고, 빈문자열은 가능.
    String param = request.getParameter("board_no");
    
    int board_no = 0;
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    }
    BoardDto board = boardDao.selectBoardByNo(board_no);
    
    String view = null;
    if(board != null) {
      view = "/board/edit.jsp";
      request.setAttribute("board", board);   // 수정 전 board 보냄
    }else {
      view = "/index.jsp";
    }
    
    return new ActionForward(view, false);
  }

  @Override
  public ActionForward modifyBoard(HttpServletRequest request) {
    
    int board_no = Integer.parseInt(request.getParameter("board_no"));
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    
    // db 결과 값 받아오기
    BoardDto board = BoardDto.builder()
                        .title(title)
                        .contents(contents)
                        .board_no(board_no).build();
    
    int updateCount = boardDao.updateBoard(board);
    
    String view = null;
    if(updateCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/detail.brd?board_no=" + board_no;
    }
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward removeBoard(HttpServletRequest request) {
    
    String param = request.getParameter("board_no");
    int board_no = 0;
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    }
    int deleteCount = boardDao.deleteBoard(board_no);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);
  }
  
  @Override
  public ActionForward removeBoards(HttpServletRequest request) {
    String param = request.getParameter("param");
    int deleteCount = boardDao.deleteBoards(param);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);
  }

}
