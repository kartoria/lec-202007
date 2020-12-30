package kr.or.ddit.board.dao;

import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

public class AttachDAOImpl implements IAttachDAO{
	private AttachDAOImpl() {}
	private static IAttachDAO self;
	public static IAttachDAO getInstance() {
		if(self==null) self = new AttachDAOImpl();
		return self;
	}
	
	@Override
	public int insertAttaches(BoardVO board) {
		return 0;
	}
	@Override
	public int deleteAttaches(BoardVO board) {
		return 0;
	}
	@Override
	public AttachVO selectAttach(int att_no) {
		return null;
	}

}
