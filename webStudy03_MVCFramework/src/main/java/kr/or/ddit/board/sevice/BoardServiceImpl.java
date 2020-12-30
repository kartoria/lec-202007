package kr.or.ddit.board.sevice;

import java.util.List;

import kr.or.ddit.board.dao.AttachDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttachDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService{
	private static IBoardService self;
	private IBoardDAO boardDAO;
	private IAttachDAO attachDAO;
	private BoardServiceImpl() {
		boardDAO = BoardDAOImpl.getInstance(); 
		attachDAO = AttachDAOImpl.getInstance(); 
	}
	public static IBoardService getInstance() {
		if(self==null) self = new BoardServiceImpl();
		return self;
	}
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		return null;
	}
	
	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		return null;
	}
	
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		return null;
	}
	
	@Override
	public int retrieveBoardCount(PagingVO<BoardVO> paging) {
		return boardDAO.selectBoardCount(paging);
	}
	
	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> paging) {
		return boardDAO.selectBoardList(paging);
	}
	
	@Override
	public BoardVO retrieveBoard(int bo_no) {
		return boardDAO.selectBoard(bo_no);
	}
	
	@Override
	public AttachVO download(int att_no) {
		return null;
	}

}
