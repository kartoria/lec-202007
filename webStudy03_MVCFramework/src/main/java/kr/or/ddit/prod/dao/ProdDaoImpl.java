package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDaoImpl implements IProdDao{
	private static IProdDao self;
	private SqlSessionFactory SqlSessionFactory;
	private ProdDaoImpl() {
		SqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	}
	
	public static IProdDao getInstance() {
		if(self == null) self = new ProdDaoImpl();
		return self;
	}
	
	
	@Override
	public int insertProd(ProdVO prod, SqlSession sqlSession) {
		return sqlSession.insert("kr.or.ddit.prod.dao.IProdDao.insertProd", prod);
	}

	@Override
	public int selectProdCount(PagingVO<ProdVO> pagingVO) {
		try(SqlSession sqlSession = SqlSessionFactory.openSession()){
			IProdDao mapper = sqlSession.getMapper(IProdDao.class);
			return mapper.selectProdCount(pagingVO);
		}
	}

	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO) {
		try(SqlSession sqlSession = SqlSessionFactory.openSession()){
			IProdDao mapper = sqlSession.getMapper(IProdDao.class);
			return mapper.selectProdList(pagingVO);
		}
	}

	@Override
	public ProdVO selectProd(String prod_id) {
		try(SqlSession sqlSession = SqlSessionFactory.openSession()){
			IProdDao mapper = sqlSession.getMapper(IProdDao.class);
			return mapper.selectProd(prod_id);
		}
	}

	@Override
	public int updateProd(ProdVO prod) {
		try(SqlSession sqlSession = SqlSessionFactory.openSession()){
			IProdDao mapper = sqlSession.getMapper(IProdDao.class);
			return mapper.updateProd(prod);
		}
	}

}
