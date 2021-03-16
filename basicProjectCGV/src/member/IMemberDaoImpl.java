package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * !!!!!! 시작전에 url, id, pw, driver 체크하고 실행 !!!!!!
 * @author 신광진
 */
public class IMemberDaoImpl implements IMemberDao{
	
	private static IMemberDao memdao;

	private IMemberDaoImpl(){
		
	}
	
	public static IMemberDao getInstance() {
		if(memdao == null){
			memdao = new IMemberDaoImpl();			
		}
		return memdao;
	}

	
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String id = "PJT";
	private String pw = "java";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	@Override
	public String logIn(Map<String, String> params) {
		
		String mem_id = params.get("mem_id");
		String mem_pass = params.get("mem_pass");
		
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		
		String login_id = null;
		
		try{
			
			//1. 
			Class.forName(driver);
			
			//2.
			con = DriverManager.getConnection(url, id, pw);
			
			//3.
			state = con.createStatement();
			String sql = "SELECT MEM_ID "
						+"FROM 	  MEMBER "
						+"WHERE  MEM_ID = '" + mem_id + "'"
						+"AND     MEM_PW = '" + mem_pass + "'"
						+"AND     MEM_DELETE = 0";
			
			//4.
			rs = state.executeQuery(sql);
			
			while(rs.next()) {
				login_id = rs.getString("MEM_ID");
			}
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("로딩실패");
		} catch(SQLException e) {
			System.out.println("접속실패");
		} finally {
			
			try{
				if(rs != null) {
					//객체가 생성되지 않았다면 close()를 할 필요가 없다.
					rs.close();
				}
				
				if(state != null) {
					state.close();
				}
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println("자원반환 실패");
			}
		}
		
		return login_id;
	}

	@Override
	public List<MemberVO> readAllMember() {
		// TODO Auto-generated method stub
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		try {
			//1. Driver loading
			Class.forName(driver);
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT * "
					+ "	  FROM MEMBER";
			
			//4. result
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setMem_id(rs.getString("MEM_ID"));
				member.setMem_pass(rs.getString("MEM_PW"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_regno1(rs.getString("mem_regno1"));
				member.setMem_regno2(rs.getString("mem_regno2"));
				member.setMem_add1(rs.getString("mem_add1"));
				member.setMem_add2(rs.getString("mem_add2"));
				member.setMem_hp(rs.getString("mem_hp"));
				member.setMem_mileage(rs.getString("mem_mileage"));
				member.setMem_mail(rs.getString("mem_mail"));
				member.setMem_delete(rs.getString("mem_delete"));
				memberList.add(member);
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
		} finally {
			
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(st != null) {
					st.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		
		return memberList;
	}

	@Override
	public MemberVO readMemInfo(String login_id) {
		
		MemberVO member = new MemberVO();
		
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		
		try{
			//1. driver loading
			Class.forName(driver);
		
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			state = con.createStatement();
			String sql = "SELECT *"
					+ "	  FROM   MEMBER"
					+ "   WHERE  MEM_ID = '" + login_id + "'";
		
			rs = state.executeQuery(sql);
			MemberVO memeber = new MemberVO();

			while(rs.next()) {
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_regno1(rs.getString("mem_regno1"));
				member.setMem_regno2(rs.getString("mem_regno2"));
				member.setMem_add1(rs.getString("mem_add1"));
				member.setMem_add2(rs.getString("mem_add2"));
				member.setMem_hp(rs.getString("mem_hp"));
				member.setMem_mail(rs.getString("mem_mail"));
				member.setMem_mileage(rs.getString("mem_mileage"));
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패");
		} catch(SQLException e) {
			System.out.println("데이터베이스 접속 실패");
		} finally {
			try {
				if(rs != null) {
					con.close();
				}
				
				if(state != null) {
					state.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				System.out.println("자원반납 실패");
			}
		}
		//2. DB connection
		
		
		return member;
	}

	@Override
	public int createMember(MemberVO newMember) {

		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		try {
			
			// loading
			Class.forName(driver);
			
			// DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			// query
			stmt = con.createStatement();
			
			String sql = "INSERT INTO MEMBER (MEM_ID, MEM_PW, MEM_NAME, MEM_REGNO1, "
													+ "MEM_REGNO2, MEM_ADD1, MEM_ADD2, MEM_HP, "
													+ "MEM_MAIL, MEM_MILEAGE, MEM_DELETE) "
													+ "VALUES ('" + newMember.getMem_id() + "', '" + newMember.getMem_pass()
														 + "', '" + newMember.getMem_name() + "', '" + newMember.getMem_regno1()
														 + "', '" + newMember.getMem_regno2() + "', '" + newMember.getMem_add1()
														 + "', '" + newMember.getMem_add2() + "', '" + newMember.getMem_hp()
														 + "', '" + newMember.getMem_mail() + "', '" + newMember.getMem_mileage()
														 + "', '" + newMember.getMem_delete() + "')";

			rs = stmt.executeUpdate(sql);
			System.out.println("System Log : [Update : " + rs + " column]");

		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return 0;
		} finally {
			
			try {
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		return rs;
	}

	@Override
	public int updateMemHp(Map<String, String> info) {
		
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			
			String new_hp = info.get("new_hp");
			String mem_id = info.get("mem_id");
			
			stmt = con.createStatement();
			String sql = "UPDATE MEMBER "
					+	 "   SET MEM_HP = '" + new_hp + "'"
					+    " WHERE MEM_ID = '" + mem_id + "'";
			
			rs = stmt.executeUpdate(sql);
			System.out.println("System Log : [Update : " + rs + " column]");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return 0;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return 0;
			
		} finally {
			
			try {
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		
		return rs;
	}

	@Override
	public String checkDupId(String mem_id) {
	
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String checkId = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			
			stmt = con.createStatement();
			String sql = "SELECT MEM_ID "
					   + "  FROM MEMBER "
					   + " WHERE MEM_ID = '" + mem_id + "'";
			
			rs = stmt.executeQuery(sql);
		
			while(rs.next()){
				checkId = rs.getString("mem_id");
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return null;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return null;
			
		} finally {
			
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		
		return checkId;
	}

	@Override
	public int updateMemAdd(Map<String, String> info) {
		
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		
		String new_add1 = info.get("new_add1");
		String new_add2 = info.get("new_add2");
		String mem_id = info.get("mem_id");
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			
			stmt = con.createStatement();
			String sql = "UPDATE MEMBER "
					   + "   SET MEM_ADD1 = '" + new_add1 + "',"  
					   + "       MEM_ADD2 = '" + new_add2 + "'"
					   + " WHERE MEM_ID = '" + mem_id + "'";
			
			rs = stmt.executeUpdate(sql);
			System.out.println("System Log : [Update : " + rs + " column]");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return 0;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return 0;
			
		} finally {
			
			try {
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		return rs;
	}

	@Override
	public int updateMemMail(Map<String, String> info) {
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		
		String new_mail = info.get("new_mail");
		String mem_id = info.get("mem_id");
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			
			stmt = con.createStatement();
			String sql = "UPDATE MEMBER "
					   + "   SET MEM_MAIL = '" + new_mail + "'"  
					   + " WHERE MEM_ID = '" + mem_id + "'";
			
			rs = stmt.executeUpdate(sql);
			System.out.println("System Log : [Update : " + rs + " column]");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return 0;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return 0;
			
		} finally {
			
			try {
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		return rs;
	}

	@Override
	public int deleteMember(String mem_id) {
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			
			stmt = con.createStatement();
			String sql = "UPDATE MEMBER "
					   + "   SET MEM_DELETE = CASE WHEN MEM_DELETE = 0 THEN 1 "
					   + "                    ELSE 1 END "
					   + " WHERE MEM_ID = '" + mem_id +"'";
			
			rs = stmt.executeUpdate(sql);
			System.out.println("System Log : [Update : " + rs + " column]");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return 0;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return 0;
			
		} finally {
			
			try {
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		return rs;
	}

	@Override
	public int updateMemName(Map<String, String> info) {
		
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		
		String mem_name = info.get("mem_name");
		String mem_id = info.get("mem_id");
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			
			stmt = con.createStatement();
			String sql = "UPDATE MEMBER "
					   + "       SET MEM_NAME = '" + mem_name + "'"
					   + " WHERE MEM_ID = '" + mem_id +"'";
			
			rs = stmt.executeUpdate(sql);
			System.out.println("System Log : [Update : " + rs + " column]");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return 0;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return 0;
			
		} finally {
			
			try {
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		return rs;
	}

	@Override
	public int updateMemPw(Map<String, String> info) {
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		
		String mem_pass = info.get("mem_pass");
		String mem_id = info.get("mem_id");
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			
			stmt = con.createStatement();
			String sql = "UPDATE MEMBER "
					   + "       SET MEM_PW = '" + mem_pass + "'"
					   + " WHERE MEM_ID = '" + mem_id +"'";
			
			rs = stmt.executeUpdate(sql);
			System.out.println("System Log : [Update : " + rs + " column]");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return 0;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return 0;
			
		} finally {
			
			try {
				
				if(stmt != null) {
					stmt.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반납 실패");
			}
		}
		return rs;
	}
	
	
	
	
}
