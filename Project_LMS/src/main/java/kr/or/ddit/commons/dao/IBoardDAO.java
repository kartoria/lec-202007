package kr.or.ddit.commons.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.dash.vo.AttChartVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IBoardDAO {

	int selectBoardCount(PagingVO<BoardVO> pagingVO);
	List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	int insertBoard(BoardVO board);
	BoardVO selectBoard(BoardVO board);
	int updateBoard(BoardVO board);
	int incrementHit(BoardVO board);
	List<BoardVO> selectDataList(BoardVO board);
	int deleteBoard(BoardVO board);

		
	// Q&A
	int insertQna(BoardVO board);
	BoardVO selectQna(BoardVO board);
	List<BoardVO> selectQnaList(PagingVO<BoardVO> pagingVO);
	int selectQnaCount(PagingVO<BoardVO> pagingVO);
	MemberVO passCheck(MemberVO memberVO);
	int updateQna(BoardVO board);
	int deleteQna(BoardVO board);
	BoardVO retrieveRep(BoardVO board);
	List<BoardVO> retrieveRepList(List<BoardVO> boardList);
	List<BoardVO> retrieveRecentNoty(BoardVO board);
	AttChartVO retrieveAttChart();
	BoardVO previousQna(BoardVO realBoard);
	BoardVO nextQna(BoardVO realBoard);
	List<BoardVO> retrieveQnaList(BoardVO board);
}
