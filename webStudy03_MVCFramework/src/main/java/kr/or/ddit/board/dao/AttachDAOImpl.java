package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;

public class AttachDAOImpl implements IAttachDAO{
	private SqlSessionFactory sqlSessionFactory;
	private AttachDAOImpl() {
		sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	}
	private static IAttachDAO self;
	public static IAttachDAO getInstance() {
		if(self==null) self = new AttachDAOImpl();
		return self;
	}
	
	
	@Override
	public int insertAttaches(BoardVO board) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			IAttachDAO mapper = sqlSession.getMapper(IAttachDAO.class);
			return mapper.insertAttaches(board);
		}
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
