package kr.or.ddit.board.sevice;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {
	public ServiceResult createBoard(BoardVO board);
	public ServiceResult modifyBoard(BoardVO board);
	public ServiceResult removeBoard(BoardVO board);
	
	public int retrieveBoardCount(PagingVO<BoardVO> paging);
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> paging);
	public BoardVO retrieveBoard(int bo_no);
	
	public AttachVO download(int att_no);
	public ServiceResult incrementRecCnt(int bo_no);
}
