package kr.or.ddit.myclassroom.discuss.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.IBoardDAO;
import kr.or.ddit.commons.dao.IReplyDAO;
import kr.or.ddit.commons.service.BaseService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.LectureVO;

@Service
public class DiscussService extends BaseService{
	@Inject
	IBoardDAO boardDAO;
	
	public List<BoardVO> selectDiscussList(BoardVO board){
		return boardDAO.selectDataList(board);
	}

	public BoardVO selectDiscusss(BoardVO board) {
		board = boardDAO.selectBoard(board);
		boardDAO.incrementHit(board);
		return board;
	}
	
	public void insertDiscuss(BoardVO board) throws Exception{
		boardDAO.insertBoard(board);
	}
	
	public void modifyDiscuss(BoardVO board) throws Exception{
		boardDAO.updateBoard(board);
	}

	public void disDelete(BoardVO board) throws Exception{
		boardDAO.deleteQna(board);
	}
}
