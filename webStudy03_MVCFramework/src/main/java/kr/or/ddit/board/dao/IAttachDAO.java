package kr.or.ddit.board.dao;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

public interface IAttachDAO {
	public int insertAttaches(BoardVO board);
	public int deleteAttaches(BoardVO board);
	
	public AttachVO selectAttach(int att_no);
}
