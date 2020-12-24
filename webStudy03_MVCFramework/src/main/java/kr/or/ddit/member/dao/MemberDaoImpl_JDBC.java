package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDaoImpl_JDBC implements IMemberDao{
	private static IMemberDao memDao;
	private MemberDaoImpl_JDBC() {}
	public static IMemberDao getInstance() {
		if(memDao == null) memDao = new MemberDaoImpl_JDBC();
		return memDao;
	}
	
	@Override
	public MemberVO selectMember(String mem_id) {
		StringBuffer sql = new StringBuffer();                             
		sql.append(" SELECT MEM_ID, MEM_PASS, MEM_NAME, MEM_REGNO1, MEM_REGNO2,     ");
		sql.append(" TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR, MEM_MILEAGE,			");
		sql.append(" MEM_ZIP, MEM_ADD1, MEM_ADD2, MEM_HOMETEL, MEM_COMTEL, MEM_HP,  ");
		sql.append(" MEM_MAIL, MEM_JOB, MEM_LIKE, MEM_MEMORIAL, MEM_DELETE, 		");
		sql.append(" TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY			");
		sql.append(" FROM  MEMBER                                        			");
		sql.append(" WHERE MEM_ID = ? 												");
		MemberVO member = null;
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());	
		){
			stmt.setString(1, mem_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				member = MemberVO.builder()
						.mem_id(rs.getString("MEM_ID"))
						.mem_pass(rs.getString("MEM_PASS"))
						.mem_name(rs.getString("MEM_NAME"))
						.mem_regno1(rs.getString("MEM_REGNO1"))
						.mem_regno2(rs.getString("MEM_REGNO2"))
						.mem_bir(rs.getString("MEM_BIR"))
						.mem_zip(rs.getString("MEM_ZIP"))
						.mem_add1(rs.getString("MEM_ADD1"))
						.mem_add2(rs.getString("MEM_ADD2"))
						.mem_hometel(rs.getString("MEM_HOMETEL"))
						.mem_comtel(rs.getString("MEM_COMTEL"))
						.mem_hp(rs.getString("MEM_HP"))
						.mem_mail(rs.getString("MEM_MAIL"))
						.mem_job(rs.getString("MEM_JOB"))
						.mem_like(rs.getString("MEM_LIKE"))
						.mem_memorial(rs.getString("MEM_MEMORIAL"))
						.mem_memorialday(rs.getString("MEM_MEMORIALDAY"))
						.mem_mileage(rs.getInt("MEM_MILEAGE"))
						.mem_delete(rs.getString("MEM_DELETE"))
						.build();
			}
			return member;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO MEMBER (");
		sql.append("MEM_ID,       MEM_PASS,        MEM_NAME,");
		sql.append("MEM_REGNO1,   MEM_REGNO2,      MEM_BIR,");
		sql.append("MEM_ZIP,      MEM_ADD1,        MEM_ADD2,");
		sql.append("MEM_HOMETEL,  MEM_COMTEL,      MEM_HP,");
		sql.append("MEM_MAIL,     MEM_JOB,         MEM_LIKE,");
		sql.append("MEM_MEMORIAL, MEM_MEMORIALDAY, MEM_MILEAGE");
		sql.append(") VALUES (");
		sql.append("?,       ?,        ?,");
		sql.append("?,       ?,        ?,");
		sql.append("?,       ?,        ?,");
		sql.append("?,       ?,        ?,");
		sql.append("?,       ?,        ?,");
		sql.append("?, TO_DATE(?, 'YYYY-MM-DD'), 3000");
		sql.append(")");
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());	
		){
			stmt.setString(1, member.getMem_id());
			stmt.setString(2, member.getMem_pass());
			stmt.setString(3, member.getMem_name());
			stmt.setString(4, member.getMem_regno1());
			stmt.setString(5, member.getMem_regno2());
			stmt.setString(6, member.getMem_bir());
			stmt.setString(7, member.getMem_zip());
			stmt.setString(8, member.getMem_add1());
			stmt.setString(9, member.getMem_add2());
			stmt.setString(10, member.getMem_hometel());
			stmt.setString(11, member.getMem_comtel());
			stmt.setString(12, member.getMem_hp());
			stmt.setString(13, member.getMem_mail());
			stmt.setString(14, member.getMem_job());
			stmt.setString(15, member.getMem_like());
			stmt.setString(16, member.getMem_memorial());
			stmt.setString(17, member.getMem_memorialday());
			
			return stmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	

	@Override
	public int updateMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE MEMBER              "); 
		sql.append(" SET    MEM_PASS        = ? ");
		sql.append("      , MEM_NAME        = ? ");
		sql.append("      , MEM_REGNO1      = ? ");
		sql.append("      , MEM_REGNO2      = ? ");
		sql.append("      , MEM_BIR         = ? ");
		sql.append("      , MEM_ZIP         = ? ");
		sql.append("      , MEM_ADD1        = ? ");
		sql.append("      , MEM_ADD2        = ? ");
		sql.append("      , MEM_HOMETEL     = ? ");
		sql.append("      , MEM_COMTEL      = ? ");
		sql.append("      , MEM_HP          = ? ");
		sql.append("      , MEM_MAIL        = ? ");
		sql.append("      , MEM_JOB         = ? ");
		sql.append("      , MEM_LIKE        = ? ");
		sql.append("      , MEM_MEMORIAL    = ? ");
		sql.append("      , MEM_MEMORIALDAY = TO_DATE(?, 'YYYY-MM-DD'), ");
		sql.append("      , MEM_MILEAGE     = ? ");
		sql.append("      , MEM_DELETE      = ? ");
		sql.append(" WHERE  MEM_ID = ?");
		
		int i = 1;
	    try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString())){
			stmt.setString(i++, member.getMem_pass()       );
			stmt.setString(i++, member.getMem_name()       );
			stmt.setString(i++, member.getMem_regno1()     );
			stmt.setString(i++, member.getMem_regno2()     );
			stmt.setString(i++, member.getMem_bir()        );
			stmt.setString(i++, member.getMem_zip()        );
			stmt.setString(i++, member.getMem_add1()       );
			stmt.setString(i++, member.getMem_add2()       );
			stmt.setString(i++, member.getMem_hometel()    );
			stmt.setString(i++, member.getMem_comtel()     );
			stmt.setString(i++, member.getMem_hp()         );
			stmt.setString(i++, member.getMem_mail()       );
			stmt.setString(i++, member.getMem_job()        );
			stmt.setString(i++, member.getMem_like()       );
			stmt.setString(i++, member.getMem_memorial()   );
			stmt.setLong  (i++, member.getMem_mileage()    );
			stmt.setString(i++, member.getMem_delete()     );
			stmt.setString(i++, member.getMem_id()         );
			
			return stmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE MEMBER           "); 
		sql.append(" SET    MEM_DELETE = 'Y' ");
		sql.append(" WHERE  MEM_ID     = ?   ");
		 
	    try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString())){
			stmt.setString(1, mem_id);
			return stmt.executeUpdate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private int makeWhere(PagingVO pagingVO, StringBuffer sql) {
		int paramIdx = 0;
		if(StringUtils.isNotBlank(pagingVO.getSearchVO().getSearchWord())) {
			sql.append("WHERE ");
			if("name".equals(pagingVO.getSearchVO().getSearchType())) {
				sql.append(" INSTR(MEM_NAME, ?) > 0 ");
			}else if("address".equals(pagingVO.getSearchVO().getSearchType())) {
				paramIdx++;
				sql.append(" INSTR(MEM_ADD1, ?) > 0 ");
			}else {
				paramIdx++;
				sql.append(" INSTR(MEM_NAME, ?) > 0 ");
				paramIdx++;
				sql.append(" OR INSTR(MEM_ADD1, ?) > 0 ");
			}
		}
		return paramIdx;
	}
	
	@Override
	public int selectMemberCount(PagingVO pagingVO) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*)");
		sql.append("FROM   MEMBER");
		int paramIdx = makeWhere(pagingVO, sql);
		try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString())){
			if(paramIdx>1) {
				for(int i=1; i<paramIdx; i++) {
					stmt.setString(i, pagingVO.getSearchVO().getSearchWord());
				}
			}
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<MemberVO> selectMemberList(PagingVO pagingVO) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT B.*		                  							 ");
		sql.append("FROM(SELECT A.*, ROWNUM RNUM		                  		 ");
		sql.append("	 FROM (SELECT MEM_ID, MEM_PASS, MEM_NAME,                ");
	    sql.append("	              MEM_REGNO1, MEM_REGNO2, MEM_BIR,           ");
	    sql.append("                  MEM_ZIP, MEM_ADD1, MEM_ADD2,               ");
	    sql.append("                  MEM_HOMETEL, MEM_COMTEL, MEM_HP,           ");
	    sql.append("                  MEM_MAIL, MEM_JOB, MEM_LIKE,               ");
	    sql.append("                  MEM_MEMORIAL, MEM_MEMORIALDAY, MEM_MILEAGE,");
	    sql.append("                  MEM_DELETE                                 ");
	    sql.append("           FROM   MEMBER   	                                 ");
	    int paramIdx = makeWhere(pagingVO, sql);
	    sql.append("           ORDER BY ROWID DESC                               ");    
	    sql.append("     ) A 				                            	     ");    
	    sql.append(") B				                                    		 ");    
	    sql.append("WHERE RNUM >= ? AND RNUM <= ? 				                 ");    
		
	    MemberVO member = null; 
	    try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString())){
	    	int i = 1;
    		if(paramIdx>1) {
    			for(i = 1; i<paramIdx; i++) {
    				stmt.setString(i, pagingVO.getSearchVO().getSearchWord());
    			}
    		}
	    	stmt.setInt(i++, pagingVO.getStartRow());
	    	stmt.setInt(i++, pagingVO.getEndRow());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				member = MemberVO.builder()
						.mem_id(rs.getString("MEM_ID"))
						.mem_pass(rs.getString("MEM_PASS"))
						.mem_name(rs.getString("MEM_NAME"))
						.mem_regno1(rs.getString("MEM_REGNO1"))
						.mem_regno2(rs.getString("MEM_REGNO2"))
						.mem_bir(rs.getString("MEM_BIR"))
						.mem_zip(rs.getString("MEM_ZIP"))
						.mem_add1(rs.getString("MEM_ADD1"))
						.mem_add2(rs.getString("MEM_ADD2"))
						.mem_hometel(rs.getString("MEM_HOMETEL"))
						.mem_comtel(rs.getString("MEM_COMTEL"))
						.mem_hp(rs.getString("MEM_HP"))
						.mem_mail(rs.getString("MEM_MAIL"))
						.mem_job(rs.getString("MEM_JOB"))
						.mem_like(rs.getString("MEM_LIKE"))
						.mem_memorial(rs.getString("MEM_MEMORIAL"))
						.mem_memorialday(rs.getString("MEM_MEMORIALDAY"))
						.mem_mileage(rs.getInt("MEM_MILEAGE"))
						.mem_delete(rs.getString("MEM_DELETE"))
						.build();
				memList.add(member);
			}
			return memList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
