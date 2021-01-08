package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@Repository
public interface IAttatchDAO {
	public int insertAttaches(BoardVO board);
	public int deleteAttatches(BoardVO board);
	public AttatchVO selectAttach(int att_no);
	public int incrementDownCount(int att_no);
}
