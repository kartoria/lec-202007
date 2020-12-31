package kr.or.ddit.board.sevice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.CustomException;
import kr.or.ddit.board.dao.AttachDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttachDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.SecurityUtils;
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
	
	private File saveFolder;
	{
		saveFolder = new File("d:/saveFiles");
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}
	
	private void encodePassword(BoardVO board) {
		String encoded = SecurityUtils.encryptSha512(board.getBo_pass());
		board.setBo_pass(encoded);
	}

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public ServiceResult createBoard(BoardVO board) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			encodePassword(board);
			int cnt = boardDAO.insertBoard(board, sqlSession);
			if(cnt > 0) {
				cnt += processAttaches(board, sqlSession);
			}
			ServiceResult result = null;
			if(cnt > 0) {
				result = ServiceResult.OK;
				sqlSession.commit();
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
		}
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
		BoardVO board = boardDAO.selectBoard(bo_no);
		if(board == null)
			throw new CustomException("해당 글을 찾을 수 없습니다.");
		return board;
	}
	
	@Override
	public AttachVO download(int att_no) {
		return null;
	}
	
	private int processAttaches(BoardVO board, SqlSession sqlSession) {
		List<AttachVO> attachList = board.getAttachList();
		int cnt = 0;
		if(attachList != null && !attachList.isEmpty() ) {
			cnt += attachDAO.insertAttaches(board, sqlSession);
			try {
				for(AttachVO attach : attachList) {
					attach.saveTo(saveFolder);
				}
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return cnt;
	}
	

}
