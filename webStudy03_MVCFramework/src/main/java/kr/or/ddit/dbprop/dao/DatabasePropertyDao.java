package kr.or.ddit.dbprop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DatabasePropertyVO;

public class DatabasePropertyDao {
	public List<DatabasePropertyVO> selectDBProperties(DatabasePropertyVO paramVO) {
		List<DatabasePropertyVO> list = new ArrayList<>();
		try(
			// 3. Connection 생성
			Connection conn = ConnectionFactory.getConnection();
				
			// 4. Query 객체 생성
			// 	1) Statement
			// 	2) PreparedStatement
			// 	3) CallableStatement
			Statement stmt = conn.createStatement();
		){
			// 5. Query 실행
			// 	1) ResultSet executeQuery()
			// 	2) int executeUpdate()
			StringBuffer sql = new StringBuffer("SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES");
			StringBuffer conditions = new StringBuffer(""); 
			if(StringUtils.isNotBlank(paramVO.getProperty_name())) {
				conditions.append("OR INSTR(PROPERTY_NAME, '"+ paramVO.getProperty_name() +"') > 0 ");
			}
			if(StringUtils.isNotBlank(paramVO.getProperty_name())) {
				conditions.append("OR INSTR(PROPERTY_VALUE, '"+ paramVO.getProperty_value() +"') > 0 ");
			}
			if(StringUtils.isNotBlank(paramVO.getProperty_name())) {
				conditions.append("OR INSTR(DESCRIPTION, '"+ paramVO.getDescription() +"') > 0 ");
			}
			if(conditions.length() >0) {
				sql.append(" WHERE ");
				int index = conditions.indexOf("OR");
				conditions.delete(index, index+2);
				sql.append(conditions);
			}
			ResultSet rs = stmt.executeQuery(sql.toString());
			
			// 	6. ResultSet 활용
			while(rs.next()){
				DatabasePropertyVO dbpVO = new DatabasePropertyVO();
				dbpVO.setProperty_name(rs.getString("PROPERTY_NAME"));
				dbpVO.setProperty_value(rs.getString("PROPERTY_VALUE"));
				dbpVO.setDescription(rs.getString("DESCRIPTION"));
				list.add(dbpVO);
				System.out.println(list);
			}
			return list;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
