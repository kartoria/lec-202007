package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IBoardDAO {
	public int insertBoard(BoardVO board);
	public int selectBoardCount(PagingVO<BoardVO> paging);
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> paging);
	public BoardVO selectBoard(int bo_no);
	public int incrementHit(int bo_no);
	public int updateBoard(BoardVO board);
	public int deleteBoard(int bo_no);
	public int incrementRecCnt(int bo_no);
}

















