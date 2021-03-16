package cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import member.MemberVO;

public class ICinemaDaoImpl implements ICinemaDao{

	private static ICinemaDao screenDao;
	
	private ICinemaDaoImpl(){
		
	}
	
	public static ICinemaDao getInstance() {
		if(screenDao == null){
			screenDao = new ICinemaDaoImpl();
		}
		return screenDao;
	}

	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String id = "PJT";
	private String pw = "java";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public List<CinemaVO> readAllCinema() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<CinemaVO> cinemaList = new ArrayList<CinemaVO>();
		
		try {
			//1. Driver loading
			Class.forName(driver);
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT *"
					   + "FROM CINEMA "
					   + "WHERE CINEMA_DELETE = \'0\'";
				
			//4. result
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				CinemaVO cinema = new CinemaVO();
				cinema.setCinema_id(rs.getInt("Cinema_id"));
				cinema.setCinema_name(rs.getString("Cinema_name"));
				cinema.setCinema_price(rs.getInt("Cinema_price"));
				cinema.setSeat_number(rs.getInt("Seat_number"));
				cinema.setIsDelete(rs.getInt("CINEMA_DELETE"));
				cinemaList.add(cinema);
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
		return cinemaList;
	}

	@Override
	public boolean addCinema(Map<String, Object> cinema) {
		
		Connection con = null;
		Statement st = null;
		String def = "\'0\'";
		
		try {
			//1. Driver loading
			Class.forName(driver);
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "INSERT INTO CINEMA (CINEMA_ID, CINEMA_NAME, SEAT_NUMBER, CINEMA_PRICE, CINEMA_DELETE) "
					   + "VALUES (" + cinema.get("CINEMA_ID") + ", '" + cinema.get("CINEMA_NAME") + "' ," 
					                + cinema.get("SEAT_NUMBER") + ", " 
					                + cinema.get("CINEMA_PRICE") + ", " + " \'0\')";
			//4. result
			int result = st.executeUpdate(sql);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
			return false;
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 접속실패");
			return false;
		} finally {
			
			try {
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
		return true;
	}

	@Override
	public boolean checkID(int cinema_id) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		try {
			//1. Driver loading
			Class.forName(driver);
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT CINEMA_ID "
					   + "FROM CINEMA "
					   + "WHERE CINEMA_ID = " + cinema_id;
			
			rs = st.executeQuery(sql);
			
			//4. result
			while(rs.next()){
				if(cinema_id == rs.getInt("CINEMA_ID")){
					result =  true;
				}
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
		return result;
	}

	@Override
	public int deleteCinema(int cinema_id) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			//1. Driver loading
			Class.forName(driver);
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "UPDATE CINEMA "
					   + "SET CINEMA_DELETE = 1 "
					   + "WHERE CINEMA_ID = " + cinema_id;
		
			result = st.executeUpdate(sql);
			
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
		return result;
	}

	@Override
	public int reviseCinema(Map<String, Object> cinema) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			//1. Driver loading
			Class.forName(driver);
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "UPDATE CINEMA "
					   + "SET " + cinema.get("COL") + " = '" + cinema.get(cinema.get("COL")) + "' "
					   + "WHERE CINEMA_ID = " + cinema.get("CINEMA_ID");
			result = st.executeUpdate(sql);
			
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
		return result;
	}
	
	@Override
	public int cinemaSales(int cinema_id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			//1. Driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT SUM(C.CINEMA_PRICE) AS PRICE "
					   + "FROM RESERVE A, SHOW_SC B, CINEMA C "
					   + "WHERE A.SHOW_ID = B.SHOW_ID(+) "
					   + "AND B.CINEMA_ID = C.CINEMA_ID "
					   + "AND C.CINEMA_ID = " + cinema_id;
					
			rs = st.executeQuery(sql);
			
			//4. result
			while(rs.next()){
				result = rs.getInt("PRICE");
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
		return result;
	}

	@Override
	public int allSales() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			//1. Driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT SUM(C.CINEMA_PRICE) AS PRICE "
					   + "FROM RESERVE A, SHOW_SC B, CINEMA C, MOVIE D "
					   + "WHERE A.SHOW_ID = B.SHOW_ID "
					   + "AND B.CINEMA_ID = C.CINEMA_ID "
					   + "AND B.MOVIE_ID = D.MOVIE_ID ";
					  
			rs = st.executeQuery(sql);
			
			//4. result
			while(rs.next()){
				result = rs.getInt("PRICE");
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
		return result;
	}

}
