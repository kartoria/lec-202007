package show;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import member.MemberVO;

public class IShowDaoImpl implements IShowDao{
	
	private static IShowDao showDao;
	
	private IShowDaoImpl(){
		
	}
	
	public static IShowDao getInstance() {
		if(showDao == null){
			showDao = new IShowDaoImpl();
		}
		return showDao;
	}
	
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String id = "MOVIEDB_2";
	private String pw = "java";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private ResultSet rs;

	
	
	
	@Override
	public List<ReadshowVO> readShow() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<ReadshowVO> readshowlist = new ArrayList<>();
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT A.SHOW_ID, " +
								 "A.SHOW_START_TIME, " +
								 "A.SHOW_DELETE, " +
								 "B.MOVIE_NAME, " +
								 "C.CINEMA_NAME, " +
								 "A.SHOW_DATE " +
							"FROM SHOW_SC A, MOVIE B, CINEMA C " +
							"WHERE A.MOVIE_ID = B.MOVIE_ID " +
							"AND A.CINEMA_ID = C.CINEMA_ID";
			
			//4. result
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				ReadshowVO readshowvo = new ReadshowVO();
				readshowvo.setShow_id(rs.getInt("SHOW_ID"));
				readshowvo.setShow_start_time(rs.getString("SHOW_START_TIME"));
				readshowvo.setShow_delete(rs.getString("SHOW_DELETE"));
				readshowvo.setMovie_name(rs.getString("MOVIE_NAME"));
				readshowvo.setCinema_name(rs.getString("CINEMA_NAME"));
				readshowvo.setShow_date(rs.getString("SHOW_DATE"));
				readshowlist.add(readshowvo);
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
		
		return readshowlist;
	}

	@Override
	public List<DayShowListVO> dayShowList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<DayShowListVO> dayshowList = new ArrayList<DayShowListVO>();
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT * FROM V_MEMBER_MOVIE_LIST";
			
			//4. result
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				DayShowListVO show = new DayShowListVO();
				show.setMoviename(rs.getString("영화이름"));
				show.setCinemaname(rs.getString("상영관"));
				show.setShowtime(rs.getString("상영관별상영시간"));
				show.setSeatnum(rs.getInt("좌석수"));
				dayshowList.add(show);
				
				System.out.println("-----------------");
				System.out.println(rs.getString("영화이름"));
				System.out.println(rs.getString("상영관"));
				System.out.println(rs.getString("상영관별상영시간"));
				System.out.println("-----------------");
				
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
		
		return dayshowList;
	}
	

	public void screeningMovie(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT DISTINCT B.MOVIE_NAME, " +
								"B.MOVIE_ID " +
							" FROM SHOW_SC A, MOVIE B" +
							" WHERE A.MOVIE_ID = B.MOVIE_ID "
							+ "ORDER BY 2";
			
			//4. result
			rs = st.executeQuery(sql);
			
			System.out.println();
			System.out.println("┌―――――――――――――――――――┐");
			System.out.println("\t\t상영중인 영화 목록");
			System.out.println("└―――――――――━―――――――――┘");
			while(rs.next()) {
				System.out.println("┌―――――――――――――――――――――――――――――――――――┐");
				System.out.println("\t영화 제목 : " + rs.getString("MOVIE_NAME"));
				System.out.println("\t영화 등록 ID : " + rs.getInt("MOVIE_ID"));
				System.out.println("└―――――――――━―――――――――――――――――――――――――┘");
				
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
	}
	
	
	
	public Map<Integer, String> movieTimeList(String movie_name){
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<String> selectmovieTime = new ArrayList<>();
		Map<Integer, String> movieTimeList = new HashMap<>();
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT  C.CINEMA_NAME, "+
									"A.SHOW_START_TIME, "+
									"C.SEAT_NUMBER "+
								"FROM SHOW_SC A, MOVIE B, CINEMA C "+
								"WHERE A.MOVIE_ID = B.MOVIE_ID "+
								"AND A.CINEMA_ID = C.CINEMA_ID "+
								"AND B.MOVIE_NAME = '"+ movie_name + "'";
			
			//4. result
			rs = st.executeQuery(sql);
			int i = 1;
			
			while(rs.next()) {
				i++;
				movieTimeList.put(0, rs.getString("CINEMA_NAME"));
				movieTimeList.put(1, rs.getString("SEAT_NUMBER"));
				movieTimeList.put(i, rs.getString("SHOW_START_TIME"));
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
		return movieTimeList;
	}

	@Override
	public boolean disabledShow(int show_id) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		System.out.println();
		System.out.println("┌―――――――――――――――――――┐");
		System.out.println(show_id + "번 상영시간이 비활성화 됩니다.");
		System.out.println("└―――――――――━―――――――――┘");

		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "UPDATE SHOW_SC SET SHOW_DELETE = '1' " +
							"WHERE SHOW_ID = '" + show_id + "'";
			
			//4. result
			rs = st.executeQuery(sql);
			
			
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
		return true;
	}

	@Override
	public boolean liveshow(int show_id) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		System.out.println("선택하신 " + show_id + "번의 상영시간을 활성화 시킵니다.");
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "UPDATE SHOW_SC SET SHOW_DELETE = '0' " +
							"WHERE SHOW_ID = '" + show_id + "'";
			
			//4. result
			rs = st.executeQuery(sql);
			
			
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
		
		return false;
	}
	
	//아직 안만듬
	public List<String> movieTIMEList(Map<String, Integer> time){
		List<String> times = new ArrayList<>();
		
		int movie_id = time.get("MOVIE_ID");
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
	
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT C.MOVIE_NAME, " +
			        			"B.CINEMA_NAME, " +
			        			"A.SHOW_START_TIME, " +
			        			"A.SHOW_DATE " +
			        		"FROM SHOW_SC A, CINEMA B, MOVIE C "+
			        		"WHERE A.CINEMA_ID = B.CINEMA_ID "+
			        		"AND A.MOVIE_ID = C.MOVIE_ID "+
			        		"AND A.MOVIE_ID = " + movie_id;
			
			//4. result
			rs = st.executeQuery(sql);
			while(rs.next()){
				times.add(rs.getString("MOVIE_NAME"));
				times.add(rs.getString("CINEMA_NAME"));
				times.add(rs.getString("SHOW_DATE"));
				times.add(rs.getString("SHOW_START_TIME"));
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
		
		return times;
	}
	
	/**
	 * 상영시간 추가해주는거
	 */
	public boolean add_Show_Sc(AddshowVO addshow){
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "INSERT INTO SHOW_SC(SHOW_ID, SHOW_START_TIME, SHOW_DELETE, MOVIE_ID, CINEMA_ID, SHOW_DATE) " +
						  "VALUES(SEQ_SHOW_SC_ID.NEXTVAL, '" + addshow.getShow_start_time()  + 
						  			"', '0'," + addshow.getMovie_id() +", " + addshow.getCinema_id() + ", TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
			
			//4. result
			rs = st.executeQuery(sql);
			System.out.println("상영시간 추가완료.");
			System.out.println();
			
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
		return true;
	}
	
	
	/**
	 * 상영시간이 있는건지 없는건지 확인하는거
	 * @return
	 */
	public boolean checkshow_sc(String show_start_time){
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT SHOW_START_TIME "+
							"FROM SHOW_SC "+
							"WHERE SHOW_START_TIME = '"+
							show_start_time + "'";
			
			//4. result
			rs = st.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("SHOW_START_TIME").equals(show_start_time)){
					System.out.println("같은번호가 있습니다.");
					return false;
				} else {
					return true;
				}				
			}
			
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
		return true;
	}
	
	
	/**
	 * 영화id 번호를 받고 해당 id의 영화 이름을 문자열로 받아오는 메서드
	 * 
	 * @author 이상헌
	 * @since 2020-09-10
	 * @return string
	 */
	public String selectMovieId_returnString(int movie_id){
		String movie_name = null;
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT  MOVIE_NAME " +
							"FROM MOVIE " +
							"WHERE MOVIE_ID =" +  movie_id;
			
			//4. result
			rs = st.executeQuery(sql);
			while(rs.next()){
				movie_name = rs.getString("MOVIE_NAME");				
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
		
		return movie_name;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	//선준
	///////
	
	public Map<Integer, String> screeningMovieName(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Map<Integer, String> map = new HashMap<>();
		int i = 0;
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = "SELECT DISTINCT B.MOVIE_NAME" +
							" FROM SHOW_SC A, MOVIE B"  +
							" WHERE A.MOVIE_ID = B.MOVIE_ID";
			
			//4. result
			rs = st.executeQuery(sql);
			
			System.out.println();
			System.out.println("상영중인 영화");
			
			while(rs.next()) {
				i++;
				map.put(i, rs.getString("MOVIE_NAME"));
				
				System.out.println("┌―――――――――――――――――――――――――――――――┐");
				System.out.println("\t" + i + ". " + rs.getString("MOVIE_NAME"));
				System.out.println("└―――――――――――――――――――――━―――――――――┘");
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
		return map;
	}
	
	
	
	public Map<String, String> readMovieTimeList(String movie_name){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Map<String, String> movieTimeList = new HashMap<>();
		
		try {
			//1.driver loading
			Class.forName(driver);
			
			//2. db connection
			con = DriverManager.getConnection(url, id, pw);
			
			//3. query
			st = con.createStatement();
			String sql = " SELECT  C.CINEMA_NAME, "
					+ "            A.SHOW_START_TIME, "
					+ "            A.SHOW_ID, "
					+ "            C.SEAT_NUMBER, "
					+ "            C.CINEMA_ID "
					+ "    FROM    SHOW_SC A, MOVIE B, CINEMA C"
					+ "    WHERE   A.MOVIE_ID = B.MOVIE_ID"
					+ "    AND     A.CINEMA_ID = C.CINEMA_ID"
					+ "    AND     B.MOVIE_NAME = '" + movie_name + "'";
			
			//4. result
			rs = st.executeQuery(sql);
			
			int i = 0;
			while(rs.next()) {
				i++;
				movieTimeList.put("cinema_name", rs.getString("CINEMA_NAME"));
				movieTimeList.put("cinema_id", rs.getString("CINEMA_ID"));
				movieTimeList.put("seat_number", rs.getString("SEAT_NUMBER"));
				movieTimeList.put("show_id" + i, rs.getString("SHOW_ID"));
				movieTimeList.put("show_start_time" + i, rs.getString("SHOW_START_TIME"));
			}
			movieTimeList.put("count", Integer.toString(i));
			
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
		return movieTimeList;
	}
}
