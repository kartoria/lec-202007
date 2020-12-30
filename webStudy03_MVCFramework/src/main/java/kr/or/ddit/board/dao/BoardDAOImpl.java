package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardDAOImpl implements IBoardDAO{
	private SqlSessionFactory sqlSessionFactory; 
	private BoardDAOImpl() {
		sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); 
	}
	private static IBoardDAO self;
	public static IBoardDAO getInstance() {
		if(self==null) self = new BoardDAOImpl();
		return self;
	}

	@Override
	public int insertBoard(BoardVO board) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			IBoardDAO mapper = sqlSession.getMapper(IBoardDAO.class);
			return mapper.insertBoard(board);
		}
	}

	@Override
	public int updateBoard(BoardVO board) {
		return 0;
	}

	@Override
	public int deleteBoard(int bo_no) {
		return 0;
	}

	@Override
	public int selectBoardCount(PagingVO<BoardVO> paging) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			IBoardDAO mapper = sqlSession.getMapper(IBoardDAO.class);
			return mapper.selectBoardCount(paging);
		}
	}

	@Override
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> paging) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			IBoardDAO mapper = sqlSession.getMapper(IBoardDAO.class);
			return mapper.selectBoardList(paging);
		}
	}

	@Override
	public BoardVO selectBoard(int bo_no) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			IBoardDAO mapper = sqlSession.getMapper(IBoardDAO.class);
			return mapper.selectBoard(bo_no);
		}
	}

}
