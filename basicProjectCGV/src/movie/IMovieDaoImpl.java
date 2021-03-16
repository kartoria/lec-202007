package movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class IMovieDaoImpl implements IMovieDao{

	String url = "jdbc:oracle:thin:@localhost:1521/XE";
	String id = "PJT";
	String pw = "java";
	
	private static IMovieDao movieDao;
	
	private IMovieDaoImpl(){
		
	}
	
	public static IMovieDao getInstance() {
		if(movieDao == null){
			movieDao = new IMovieDaoImpl();
		}
		return movieDao;
	}
	
	

	
	//영화들의 제목을 맵에 담아줌
	public Map<Integer, String> readMovieName(){
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		Map<Integer,String> movieNameMap = new HashMap<>();
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			cn = DriverManager.getConnection(url,id,pw);
			
			
//			3.질의
			st = cn.createStatement();
			String sql = "SELECT MOVIE_NAME "
					+ "   FROM   MOVIE";
			
//			4.결과
			rs = st.executeQuery(sql);
			
			
			int i = 0;
			while(rs.next()){
				i++;
				movieNameMap.put(i, rs.getString("MOVIE_NAME"));
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
		return movieNameMap;
	}
	

	public Map<String, String> readMovieInfo(String movie_name) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		Map<String, String> movieInfo = new HashMap<>();
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "SELECT A.MOVIE_ID"
					+ "			 A.MOVIE_NAME, "
					+ "          A.MOVIE_DIRECTOR, "
					+ "          A.MOVIE_RUNT, "
					+ "          A.MOVIE_ACTOR, "
					+ "          A.MOVIE_OPEN, "
					+ "          C.GRADE_LEVEL,"
					+ "          B.GENRE_NAME"
					+ "   FROM   MOVIE A, GENRE B, GRADE C "
					+ "   WHERE  A.MOVIE_NAME = '" + movie_name + "'"
					+ "   AND    A.GRADE_ID = C.GRADE_ID"
					+ "   AND    A.GENRE_ID = B.GENRE_ID";
		
//			4.결과
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				movieInfo.put("movie_id", "MOVIE_ID");
				movieInfo.put("movie_name", rs.getString("MOVIE_NAME"));
				movieInfo.put("movie_director", rs.getString("MOVIE_DIRECTOR"));
				movieInfo.put("movie_runt", rs.getString("MOVIE_RUNT"));
				movieInfo.put("movie_actor", rs.getString("MOVIE_ACTOR"));
				movieInfo.put("movie_open", rs.getString("MOVIE_OPEN"));
				movieInfo.put("grade_level", rs.getString("GRADE_LEVEL"));
				movieInfo.put("genre_name", rs.getString("GENRE_NAME"));				
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
		return movieInfo;
	}
	
	public int createMovie(MovieVO mv){
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		int result = 0;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			cn = DriverManager.getConnection(url,id,pw);
			
			
//			3.질의
			st = cn.createStatement();
			String countSql = "SELECT COUNT(ROWNUM)"
					+ "        FROM   MOVIE";
			rs = st.executeQuery(countSql);
			int count = 0;
			while(rs.next()){
				count = rs.getInt("COUNT(ROWNUM)") + 1;
			}
			System.out.println(count);
			
			String sql = "INSERT INTO MOVIE"
					+ "   (MOVIE_ID, MOVIE_NAME, MOVIE_DIRECTOR, MOVIE_RUNT, MOVIE_ACTOR, MOVIE_OPEN, GRADE_ID, GENRE_ID, MOVIE_DELETE) "
					+ "   VALUES "
					+ "   ('" + count + "', '" + mv.getMovie_name() + "', '" + mv.getMovie_director() + "', " + mv.getMovie_runt() + ",'" + mv.getMovie_actor() + "', '" + mv.getMovie_open() + "', " + mv.getGrade_id() + ", " + mv.getGenre_id() + ", '0')";
			
			result = st.executeUpdate(sql);
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
		return result;
	}

	@Override
	public boolean printAllMovie() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		boolean printCheck = false;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
//			String url = "jdbc:oracle:thin:@localhost:1521/XE";
//			String id = "PJT";
//			String pw = "java";
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "SELECT A.MOVIE_ID, A.MOVIE_NAME, A.MOVIE_ACTOR, A.MOVIE_DIRECTOR, "
					+ "          A.MOVIE_RUNT, A.MOVIE_OPEN, B.GRADE_LEVEL, C.GENRE_NAME"
					+ "   FROM   MOVIE A, GRADE B, GENRE C"
					+ "   WHERE  MOVIE_DELETE = 0"
					+ "   AND    B.GRADE_ID = A.GRADE_ID"
					+ "	  AND    C.GENRE_ID = A.GENRE_ID"
					+ "   ORDER BY 1";
		
//			4.결과
			rs = st.executeQuery(sql);
			while(rs.next()){
				printCheck = true;
				System.out.println();
				System.out.println("┌――――――――――――――――――――――――――――――――┐");
				System.out.print("\t" + rs.getString("MOVIE_ID") + ". ");
				System.out.println(rs.getString("MOVIE_NAME"));
				System.out.print("\t등급 : " + rs.getString("GRADE_LEVEL"));
				System.out.print("\t러닝타임 : " + rs.getString("MOVIE_RUNT"));
				System.out.println("\t장르 : " + rs.getString("GENRE_NAME"));
				System.out.println("\t감독 : " + rs.getString("MOVIE_DIRECTOR"));
				System.out.println("\t주연 : " + rs.getString("MOVIE_ACTOR"));
				System.out.println("\t개봉일자 : " + rs.getString("MOVIE_OPEN"));
				System.out.println("└―――――――――━――――――――――――――――――――――┘");
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
		return printCheck;
	}

	@Override
	public boolean deleteMovie(int movie_id) {
		Connection cn = null;
		Statement st = null;
		int rs = 0;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "UPDATE MOVIE"
					+ "   SET MOVIE_DELETE = '1'"
					+ "   WHERE  MOVIE_ID = " + movie_id;
		
//			4.결과
			rs = st.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
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
		if(rs > 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean movieCheck(int movie_id) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		try {
			//1. Driver loading
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT MOVIE_ID "
					   + "FROM MOVIE "
					   + "WHERE MOVIE_ID = " + movie_id;
			
			rs = st.executeQuery(sql);
			
			//4. result
			while(rs.next()){
				if(movie_id == rs.getInt("MOVIE_ID")){
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
	public int movieSales(int movie_id) {
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
					   + "AND D.MOVIE_ID = B.MOVIE_ID "
					   + "AND D.MOVIE_ID = " + movie_id;
			
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
