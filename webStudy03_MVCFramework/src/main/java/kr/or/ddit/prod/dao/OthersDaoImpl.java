package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LProdVO;
import kr.or.ddit.vo.PagingVO;

public class OthersDaoImpl implements IOthersDao{
	private static IOthersDao self;
	private SqlSessionFactory SqlSessionFactory;
	private OthersDaoImpl() {
		SqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	}
	public static IOthersDao getInstance() {
		if(self == null) self = new OthersDaoImpl();
		return self;
	}

	@Override
	public List<BuyerVO> selectBuyerList() {
		try(SqlSession sqlSession = SqlSessionFactory.openSession()){
			IOthersDao mapper = sqlSession.getMapper(IOthersDao.class);
			return mapper.selectBuyerList();
		}
	}
	@Override
	public List<Map<String, Object>> selectLProdList() {
		try(SqlSession sqlSession = SqlSessionFactory.openSession()){
			IOthersDao mapper = sqlSession.getMapper(IOthersDao.class);
			return mapper.selectLProdList();
		}
	}

}
