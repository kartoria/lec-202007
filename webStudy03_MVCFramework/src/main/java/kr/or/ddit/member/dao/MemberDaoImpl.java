package kr.or.ddit.member.dao;

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
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDaoImpl implements IMemberDao{
	private static IMemberDao self;
	private MemberDaoImpl() {}
	public static IMemberDao getInstance() {
		if(self == null) self = new MemberDaoImpl();
		return self;
	}
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); 
	
	@Override
	public int insertMember(MemberVO member) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession() ){		
			IMemberDao mapper = sqlSession.getMapper(IMemberDao.class);
			int rowcnt = mapper.insertMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}
	@Override
	public int selectMemberCount(PagingVO pagingVO) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession() ){		
			IMemberDao mapper = sqlSession.getMapper(IMemberDao.class);
			return mapper.selectMemberCount(pagingVO);
		}
	}
	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession() ){		
			IMemberDao mapper = sqlSession.getMapper(IMemberDao.class);
			return mapper.selectMemberList(pagingVO);
		}
	}
	@Override
	public MemberVO selectMember(String mem_id) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession() ){
//			return sqlSession.selectOne("kr.or.ddit.member.dao.IMemberDao.selectMember", mem_id);
			IMemberDao mapper = sqlSession.getMapper(IMemberDao.class);
			return mapper.selectMember(mem_id);
		}
	}
	@Override
	public int updateMember(MemberVO member) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession() ){
			IMemberDao mapper = sqlSession.getMapper(IMemberDao.class);
			int rowcnt = mapper.updateMember(member);
			sqlSession.commit();
			return mapper.updateMember(member);
		}
	}
	@Override
	public int deleteMember(String mem_id) {
		try(SqlSession sqlSession = sqlSessionFactory.openSession() ){		
				IMemberDao mapper = sqlSession.getMapper(IMemberDao.class);
				int rowcnt = mapper.deleteMember(mem_id);
				sqlSession.commit();
				return rowcnt;
		}
	}
}
