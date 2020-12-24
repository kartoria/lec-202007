package kr.or.ddit.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDaoImpl implements IEmployeeDao{
	public Set<EmployeeVO> selectEmployee() {
		String sql = "SELECT EMP_NAME, MANAGER_ID FROM EMPLOYEES"; 
		EmployeeVO empVO = null;
		Set<EmployeeVO> set = new HashSet<EmployeeVO>();
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();	
		){
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				empVO = EmployeeVO.builder()
						.emp_name(rs.getString("EMP_NAME"))
						.manager_id(rs.getString("MANAGER_ID"))
						.build();
				set.add(empVO);
			}
			return set;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
