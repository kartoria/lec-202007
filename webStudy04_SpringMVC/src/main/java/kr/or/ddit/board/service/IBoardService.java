package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {
	/**
	 * @param board
	 * @return OK, FAILED
	 */
	public ServiceResult createBoard(BoardVO board);
	public int retrieveBoardCount(PagingVO<BoardVO> paging);
	/**
	 * @param paging
	 * @return 검색조건에 맞는 글이 없으면, size == 0
	 */
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> paging);
	/**
	 * @param bo_no
	 * @return 존재하지 않으면, CustomException 발생
	 */
	public BoardVO retrieveBoard(int bo_no);
	/**
	 * @param board
	 * @return INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyBoard(BoardVO board);
	/**
	 * @param board
	 * @return INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeBoard(BoardVO board);
	public ServiceResult incrementRecCnt(int bo_no);
	
	public AttatchVO download(int att_no);
}









