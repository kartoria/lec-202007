package j_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_01 {
	public static void main(String[] args){
		Connection conn = null; // 연결
		Statement stmt = null;	// 질의
		ResultSet rs = null;	// 결과
		
		try{
//			1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2. 접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			
			conn = DriverManager.getConnection(url, id, pw);
			
//			3. 질의
			stmt = conn.createStatement();
			String mem_id = "a001";
			String sql = "SELECT * "
					   + "FROM   MEMBER "
					   + "WHERE  MEM_ID = '" + mem_id + "'";
			
//			4.결과
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String mem_name = rs.getString("MEM_NAME");
				System.out.print(mem_name);
				String mem_add1 = rs.getString("MEM_ADD1");
				System.out.println("  " + mem_add1);
			}
			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try{
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		
	}
}
