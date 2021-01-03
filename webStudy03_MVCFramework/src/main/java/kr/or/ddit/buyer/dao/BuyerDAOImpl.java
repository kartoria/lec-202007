package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerDAOImpl implements IBuyerDAO{
	private BuyerDAOImpl() { }
	private static BuyerDAOImpl self;
	public static BuyerDAOImpl getInstance() {
		if(self==null) self = new BuyerDAOImpl();
		return self;
	}
	
	private SqlSessionFactory sqlSessionFactory = 
				CustomSqlSessionFactoryBuilder.getSqlSessionFactory();


	@Override
	public int selectBuyerCount(PagingVO<BuyerVO> pagingVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
		){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerCount(pagingVO);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
		){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyerList(pagingVO);
		}
	}

	@Override
	public BuyerVO selectBuyer(String buyer_id) {
		try(
				SqlSession session = sqlSessionFactory.openSession();
		){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.selectBuyer(buyer_id);
		}
	}

	@Override
	public int insertBuyer(BuyerVO buyer) {
		try(
				SqlSession session = sqlSessionFactory.openSession(true);
		){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.insertBuyer(buyer);
		}
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		try(
				SqlSession session = sqlSessionFactory.openSession(true);
		){
			IBuyerDAO mapper = session.getMapper(IBuyerDAO.class);
			return mapper.updateBuyer(buyer);
		}
	}

}
