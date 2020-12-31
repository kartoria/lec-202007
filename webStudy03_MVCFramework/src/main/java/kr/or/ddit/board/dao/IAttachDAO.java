package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

public interface IAttachDAO {
	public int insertAttaches(BoardVO board, SqlSession sqlSession);
	public int deleteAttaches(BoardVO board);
	
	public AttachVO selectAttach(int att_no);
}
