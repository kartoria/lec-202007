package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardDAO {
	public int insertBoard(BoardVO board, SqlSession sqlSession);
	public int updateBoard(BoardVO board);
	public int deleteBoard(int bo_no);
	
	public int selectBoardCount(PagingVO<BoardVO> paging);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> paging);
	public BoardVO selectBoard(int bo_no);
	
	public int incrementRecCnt(int bo_no);
}
