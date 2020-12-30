package kr.or.ddit.commons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ZipVO;

public class SearchZipDaoImpl implements ISearchZipDao{
	private static ISearchZipDao zipDao;
	
	private SearchZipDaoImpl(){} //싱글톤 비워두기
	
	public static ISearchZipDao getInstance() {
		if(zipDao == null) zipDao = new SearchZipDaoImpl();
		return zipDao;
	}
	
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int selectZipCount(PagingVO<ZipVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){		
			ISearchZipDao mapper = sqlSession.getMapper(ISearchZipDao.class);
			return mapper.selectZipCount(pagingVO);
		}
	}
	
	@Override
	public List<ZipVO> selectZipList(PagingVO<ZipVO> pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){		
			ISearchZipDao mapper = sqlSession.getMapper(ISearchZipDao.class);
			return mapper.selectZipList(pagingVO);
		}
	}

}
