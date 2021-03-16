package review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class IReviewDaoImpl implements IReviewDao{

	String url = "jdbc:oracle:thin:@localhost:1521/XE";
	String id = "MOVIEDB_2";
	String pw = "java";
	
	private static IReviewDao reviewDao;
	
	private IReviewDaoImpl() {
	
	}
	
	public static IReviewDao getInstance() {
		if(reviewDao == null){
			reviewDao = new IReviewDaoImpl();
		}
		return reviewDao;
	}

	@Override
	public boolean selectMovieReview(String movie_name) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			cn = DriverManager.getConnection(url,id,pw);
			
			
//			3.질의
			st = cn.createStatement();
			String sql = "SELECT A.REVIEW_NO, A.REVIEW_TITLE, A.REVIEW_COMMENT, E.MEM_NAME"
					+ "   FROM   REVIEW A, RESERVE B, SHOW_SC C, MOVIE D, MEMBER E "
					+ "   WHERE  A.RES_NO = B.RES_NO "
					+ "   AND    B.SHOW_ID = C.SHOW_ID "
					+ "   AND    C.MOVIE_ID = D.MOVIE_ID"
					+ "   AND    D.MOVIE_NAME = '" + movie_name +"' " 
					+ "   AND    B.MEM_ID = E.MEM_ID";
		
//			4.결과
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				System.out.println("No. " + rs.getString("REVIEW_NO"));
				System.out.print("제목 : "+ rs.getString("REVIEW_TITLE"));
				System.out.println("\t작성자 : " + rs.getString("MEM_NAME"));
				System.out.println("내용 : "+ rs.getString("REVIEW_COMMENT"));
				System.out.println("────────────────────────────────────");
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
		return true;
	}

	@Override
	public boolean deleteMovieReview(int review_no) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		boolean deleteCheck = false;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String printSql = "SELECT A.REVIEW_NO, A.REVIEW_TITLE, A.REVIEW_COMMENT, B.MEM_ID"
					+ "        FROM   REVIEW A, RESERVE B"
					+ "        WHERE  A.RES_NO = B.RES_NO";
					
			rs = st.executeQuery(printSql);
			
			while(rs.next()){
				System.out.println("No. " + rs.getString("REVIEW_NO"));
				System.out.println("제목 : " + rs.getString("REVIEW_TITLE"));
				System.out.println("작성자 : " + rs.getString("MEM_ID"));
				System.out.println("내용 : " + "REVIEW_COMMENT");
				System.out.println("────────────────────────────────────");
			}
					
			String sql = "DELETE FROM REVIEW "
					+ "   WHERE  REVIEW_NO = " + review_no;
		
			if (0 < st.executeUpdate(sql)){
				deleteCheck = true;
			}
			
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
		return deleteCheck;
	}
	
	@Override
	public boolean printAllReview() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		boolean printCheck = false;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			
			cn = DriverManager.getConnection(url,id,pw);
			
//			3.질의
			st = cn.createStatement();
			String sql = "SELECT A.REVIEW_NO, A.REVIEW_TITLE, A.REVIEW_COMMENT, B.MEM_ID, C.MOVIE_ID"
					+ "   FROM   REVIEW A, RESERVE B, SHOW_SC C"
					+ "   WHERE  A.RES_NO = B.RES_NO"
					+ "   AND    B.SHOW_ID = C.SHOW_ID";
			
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				printCheck = true;
				System.out.println("┌――――――――――――――――――――――――――――――――┐");
				System.out.println("\t\tNo. " + rs.getString("REVIEW_NO"));
				System.out.println("\t\t영화 : " + rs.getString("MOVIE_ID"));
				System.out.println("\t\t제목 : " + rs.getString("REVIEW_TITLE"));
				System.out.println("\t\t작성자 : " + rs.getString("MEM_ID"));
				System.out.println("\t\t내용 : " + "REVIEW_COMMENT");
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
	public boolean writeReview(Map<String, String> writeReviewMap) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String title = writeReviewMap.get("title");
		String comment = writeReviewMap.get("content"); 
		String res_no = writeReviewMap.get("res_no");
		
		int review_no = 0;
		int result = 0;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			
			cn = DriverManager.getConnection(url,id,pw);
			
			st = cn.createStatement();
			
			String countSql = "SELECT COUNT(ROWNUM)"
					+ "         FROM   REVIEW";
			rs = st.executeQuery(countSql);
			while(rs.next()){
				review_no = rs.getInt("COUNT(ROWNUM)") + 1;
			}
			
			String sql = "INSERT INTO REVIEW (REVIEW_NO, REVIEW_TITLE, REVIEW_COMMENT, RES_NO) "
                       +" VALUES ("+ review_no + ", '" + title + "', '" + comment + "', " + res_no + ")";
			
			result = st.executeUpdate(sql);
			
			
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
		if(result > 0){
			return true;
		}
		return false;
	}
	
	

	

}
