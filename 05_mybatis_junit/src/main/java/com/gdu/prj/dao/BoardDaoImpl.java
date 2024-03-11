package com.gdu.prj.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.prj.dto.BoardDto;

public class BoardDaoImpl implements BoardDao {

  // SqlSession (Connection/PreparedStatement/ResultSet 처리) 만드는 SqlSessionFactory 객체 선언
  private SqlSessionFactory factory = null;
  
  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl();
  private BoardDaoImpl() {
    // mybatis-config.xml 파일을 이용한 SqlSessionFactory 객체 생성
    try {
      String resource = "com/gdu/prj/config/mybatis-config.xml";  // .으로 구분하지 말고 / 로 구분할 것
      InputStream in = Resources.getResourceAsStream(resource);
      factory = new SqlSessionFactoryBuilder().build(in);   // 입력스트림(mybatis-config.xml 을 읽은 것)을 기반으로 SqlSessionFactory 짓기
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static BoardDao getInstance() {
    return boardDao;
  }
  
  @Override
  public int insertBoard(BoardDto board) {
    SqlSession sqlSession = factory.openSession(false);   // autoCommit 을 하지 않는다.(false)
    int insertCount = sqlSession.insert("com.gdu.prj.dao.board_t.insertBoard", board);    // insert(com.gdu.prj.dao.board_t(=namespace).insertBoard(=id), 전달값)
    if(insertCount == 1) {
      sqlSession.commit();  // commit 직접 실행
    }
    sqlSession.close();     // close();
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    SqlSession sqlSession = factory.openSession(false);
    int updateCount = sqlSession.update("com.gdu.prj.dao.board_t.updateBoard", board);
    if(updateCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {
    SqlSession sqlSession = factory.openSession(false);
    int deleteCount = sqlSession.delete("com.gdu.prj.dao.board_t.deleteBoard", board_no);
    if(deleteCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return deleteCount;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> params) {
    SqlSession sqlSession = factory.openSession();
    // selectOne() : select 결과가 1개 일 때
    // selectList() : select 결과가 1개 이상일 때
    List<BoardDto> boardList = sqlSession.selectList("com.gdu.prj.dao.board_t.selectBoardList", params);
    sqlSession.close();
    return boardList;
  }

  @Override
  public int getBoardCount() {
    SqlSession sqlSession = factory.openSession();
    int total = sqlSession.selectOne("com.gdu.prj.dao.board_t.getBoardCount");
    sqlSession.close();
    return total;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {
    SqlSession sqlSession = factory.openSession();
    BoardDto board = sqlSession.selectOne("com.gdu.prj.dao.board_t.selectBoardByNo", board_no);
    sqlSession.close();
    return board;
  }

}
