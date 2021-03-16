package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IDaoImpl implements IDao{
	
	
	
	// 로그인
	public String logIn(Map<String, String> params){
		String mem_id = params.get("mem_id");
		String mem_pass = params.get("mem_pass");
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String logIn_id = null;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "SELECT MEM_ID "
					   + "FROM   MEMBER2 "
					   + "WHERE  MEM_ID     = '" + mem_id + "' "
					   + "AND    MEM_PASS   = '" + mem_pass + "' "
					   + "AND    MEM_DELETE = '0' ";
			
//			결과
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				logIn_id = rs.getString("MEM_ID");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
				if(cn != null){
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		
		return logIn_id;
	}

	@Override
	public boolean insertMember(MemberVO mv) {
		String mem_id = mv.getMem_id();
		String mem_pass = mv.getMem_pass();
		String mem_name = mv.getMem_name();
		String mem_regno1 = mv.getMem_regno1();
		String mem_regno2 = mv.getMem_regno2();
		char mem_delete = '0';
		
		Connection cn = null;
		Statement st = null;
		
		try{
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "INSERT INTO MEMBER2 (MEM_ID, MEM_PASS, MEM_NAME, MEM_REGNO1, MEM_REGNO2, MEM_DELETE) "
					+ "	  VALUES ('" + mem_id + "', '" + mem_pass + "', '" + mem_name + "', '" + mem_regno1 + "', '" + mem_regno2 + "', '" + mem_delete + "')";
			
			int r = st.executeUpdate(sql);
			
			if(r > 0){
				return true;
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {
				if(st != null){
					st.close();
				}
				if(cn != null){
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		return false;
	}

	@Override
	public Map<Integer, String> showMemList() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		Map<Integer, String> params = null;
		try{
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
			st = cn.createStatement();
			String sql = "SELECT * "
					+ "   FROM   MEMBER2 "
					+ "   WHERE  MEM_DELETE = '0' ";
			rs = st.executeQuery(sql);
			
			int i = 1;
			params = new HashMap<>();
			while(rs.next()){
				params.put(i, rs.getString("MEM_ID"));
				System.out.println(i + ". " + rs.getString("MEM_ID"));
				i++;
			}
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
				if(cn != null){
					cn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		return params;
	}

	@Override
	public boolean infoMember(String mem_id) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
			st = cn.createStatement();
			String sql = "SELECT * "
					+ "   FROM   MEMBER2 "
					+ "   WHERE  MEM_ID = '" + mem_id + "'";
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				System.out.println("회원 이름 : " + rs.getString("MEM_NAME"));
				System.out.println("회원 주민번호 : "  + rs.getString("MEM_REGNO1") + " - " + rs.getString("MEM_REGNO2"));
			}
			return true;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
				if(cn != null){
					cn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		return false;
	}

	@Override
	public boolean changePass(Map<String, String> params) {
		String mem_id = params.get("mem_id");
		String mem_pass = params.get("mem_pass");
		
		Connection cn = null;
		Statement st = null;
		
		try{
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "UPDATE MEMBER2 "
					+ "   SET    MEM_PASS = '" + mem_pass + "' "
					+ "   WHERE  MEM_ID   = '" + mem_id + "'";
					
			int r = st.executeUpdate(sql);
			
			if(r > 0){
				return true;
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {
				if(st != null){
					st.close();
				}
				if(cn != null){
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		return false;
	}
	
	@Override
	public boolean changeName(Map<String, String> params) {
		String mem_id = params.get("mem_id");
		String mem_name = params.get("mem_name");
		
		Connection cn = null;
		Statement st = null;
		
		try{
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "UPDATE MEMBER2 "
					+ "   SET    MEM_NAME = '" + mem_name + "' "
					+ "   WHERE  MEM_ID   = '" + mem_id + "'";
					
			int r = st.executeUpdate(sql);
			
			if(r > 0){
				return true;
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {
				if(st != null){
					st.close();
				}
				if(cn != null){
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		return false;
	}
	
	@Override
	public boolean changeRegno(Map<String, String> params) {
		String mem_id = params.get("mem_id");
		String mem_regno1 = params.get("mem_regno1");
		String mem_regno2 = params.get("mem_regno2");
		
		Connection cn = null;
		Statement st = null;
		
		try{
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "UPDATE MEMBER2 "
					+ "   SET    MEM_REGNO1 = '" + mem_regno1 + "', "
					+ "	         MEM_REGNO2 = '" + mem_regno2 + "' "
					+ "   WHERE  MEM_ID   = '" + mem_id + "'";
					
			int r = st.executeUpdate(sql);
			
			if(r > 0){
				return true;
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {
				if(st != null){
					st.close();
				}
				if(cn != null){
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		return false;
	}

	@Override
	public boolean deleteMember(String mem_id) {
		Connection cn = null;
		Statement st = null;
		try{
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			String url = "jdbc:oracle:thin:@localhost:1521/XE";
			String id = "SUN";
			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "UPDATE MEMBER2 "
					+ "   SET    MEM_DELETE = '" + '1' + "' "
					+ "   WHERE  MEM_ID   = '" + mem_id + "'";
			
			int r = st.executeUpdate(sql);
			
			if(r > 0){
				return true;
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {
				if(st != null){
					st.close();
				}
				if(cn != null){
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}
		return false;
	}
	
}
