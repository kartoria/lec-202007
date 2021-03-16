package genre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.MemberVO;

public class IGenreDaoImpl implements IGenreDao{

	private static IGenreDao genreDao;
	
	private IGenreDaoImpl(){
		
	}
	
	public static IGenreDao getInstance() {
		if(genreDao == null) {
			genreDao = new IGenreDaoImpl();
		}
		return genreDao;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String id = "PJT";
	private String pw = "java";
	private String driver = "oracle.jdbc.driver.OracleDriver";

	
	@Override
	public List<GenreVO> readGenre() {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<GenreVO> genList = new ArrayList<GenreVO>();
		
		try {
			//1. Driver loading
			Class.forName(driver);
			
			//2. DB connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT * "
					+ "	      FROM GENRE";
			
			//4. result
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				GenreVO gen = new GenreVO();
				gen.setGenre_id(rs.getInt("GENRE_ID"));
				gen.setGenre_name(rs.getString("GENRE_NAME"));
				genList.add(gen);
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
		
		return genList;
	}

}
