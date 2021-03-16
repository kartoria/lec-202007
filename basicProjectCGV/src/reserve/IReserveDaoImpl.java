package reserve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IReserveDaoImpl implements IReserveDao{

	String url = "jdbc:oracle:thin:@localhost:1521/XE";
	String id = "MOVIEDB_2";
	String pw = "java";
	
	private static IReserveDao ReserveDao;
	
	private IReserveDaoImpl(){
		
	}
	
	public static IReserveDao getInstance() {
		if(ReserveDao == null){
			ReserveDao = new IReserveDaoImpl();
		}
		return ReserveDao;
	}

	@Override
	public int[] readResSeat(int cinema_id, int show_id) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		int[] resSeat = null;
		int count = 0;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			cn = DriverManager.getConnection(url,id,pw);
			
			
//			3.질의
			st = cn.createStatement();
			
			//행개수 세기
			String countSql = "SELECT COUNT(ROWNUM) "
					+ "   FROM   RESERVE A, SHOW_SC B, CINEMA C "
					+ "   WHERE  A.RESERVE_DELETE = '0' "
					+ "   AND    A.SHOW_ID = B.SHOW_ID "
					+ "   AND    C.CINEMA_ID = B.CINEMA_ID "
					+ "   AND    C.CINEMA_ID = " + cinema_id;
			
			rs = st.executeQuery(countSql);
			
			while(rs.next()){
				count = rs.getInt("COUNT(ROWNUM)");
			}
			
			//좌석가져오기
			String sql = "SELECT A.SEAT_NO "
					+ "   FROM   RESERVE A, SHOW_SC B, CINEMA C "
					+ "   WHERE  A.RESERVE_DELETE = '0' "
					+ "   AND    A.SHOW_ID = B.SHOW_ID "
					+ "   AND    C.CINEMA_ID = B.CINEMA_ID "
					+ "   AND    C.CINEMA_ID = " + cinema_id
					+ "   AND    B.SHOW_ID = " + show_id;
			
			rs = st.executeQuery(sql);
			
			resSeat = new int[count];
			int i = 0;
			while(rs.next()){
				resSeat[i] = rs.getInt("SEAT_NO");
				i++;
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
		return resSeat;
	}

	@Override
	public boolean createReserve(Map<String, String> map) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String seat_no = map.get("seat_no");
		String cinema_id = map.get("cinema_id");
		String mem_id = map.get("mem_id");
		String show_id = map.get("show_id");
		boolean seatCheck = true; //선택한 좌석이 비어있으면 true 예약되어있으면 false
		int count = 0; // res_no 입력할때 필요한거
		int result = 0;
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			cn = DriverManager.getConnection(url,id,pw);
			
			
//			3.질의
			st = cn.createStatement();
			
			//회원이 좌석을 선택 -> seat_no랑 cinema_id를 Map으로 가지고 Dao로 감
			//-> 예약테이블에서 seat_no와 cinema_id가 일치하는 좌석이 있으면 -> 이미 예약된 테이블이다
			//-> 다시선택하게 만듬
			String sql = "SELECT A.SEAT_NO, C.CINEMA_ID"
					+ "   FROM   RESERVE A, SHOW_SC B, CINEMA C "
					+ "   WHERE  A.RESERVE_DELETE = '0'"
					+ "   AND    A.SHOW_ID = B.SHOW_ID"
					+ "   AND    B.CINEMA_ID = C.CINEMA_ID"
					+ "   AND    C.CINEMA_ID = " + cinema_id
					+ "   AND    A.SHOW_ID = " + show_id;
			
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				if(seat_no.equals(rs.getString("SEAT_NO"))){
					seatCheck = false;
					break;
				}
			}
			
			String sql1 = "SELECT COUNT(ROWNUM)"
					+ "    FROM   RESERVE";
			rs = st.executeQuery(sql1);
			while(rs.next()){
				count = rs.getInt(1) + 1;
			}
			
			
			if(seatCheck){
				String sql2 = "INSERT INTO RESERVE "
						+ "          (RES_NO, SEAT_NO, MEM_ID, SHOW_ID, RESERVE_DELETE)"
						+ "    VALUES "
						+ "          (" + count + ", " + seat_no + ", '" + mem_id + "', " + show_id + ", '0')";
				
				result = st.executeUpdate(sql2);
				
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
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	
	// 내 아이디로 예매한 정보를 들고와야함
	@Override
	public Map<String, String> readMyReserve(String login_id) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		int i = 0;
		Map<String, String> map = new HashMap<>();
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			cn = DriverManager.getConnection(url,id,pw);
			
			
//			3.질의
			st = cn.createStatement();
			String sql = "SELECT DISTINCT C.MOVIE_ID, C.MOVIE_NAME"
					+ "   FROM   RESERVE A, SHOW_SC B, MOVIE C "
					+ "   WHERE  A.RESERVE_DELETE = '0'"
					+ "   AND    A.SHOW_ID = B.SHOW_ID"
					+ "   AND    B.MOVIE_ID = C.MOVIE_ID"
					+ "   AND    A.MEM_ID = '" + login_id + "'"
					+ "   ORDER BY 1";
			
			rs = st.executeQuery(sql);
			i = 0;
			while(rs.next()){
				i++;
				map.put("movie_id" + Integer.toString(i), rs.getString("MOVIE_ID"));
				map.put("movie_name" + Integer.toString(i), rs.getString("MOVIE_NAME"));
			}
			map.put("count", Integer.toString(i));
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
		
		
		return map;
	}

	@Override
	public String readResNO(Map<String, String> writeReviewMap) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		String res_no = "";
		String mem_id = writeReviewMap.get("mem_id");
		String movie_id = writeReviewMap.get("movie_id");
		try {
//			1.드라이버로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2.접속
			cn = DriverManager.getConnection(url,id,pw);
			
			
//			3.질의
			st = cn.createStatement();
			String sql = "SELECT DISTINCT C.MOVIE_ID, C.MOVIE_NAME, A.RES_NO"
					+ "   FROM   RESERVE A, SHOW_SC B, MOVIE C"
					+ "   WHERE  RESERVE_DELETE = '0'"
					+ "   AND    A.SHOW_ID = B.SHOW_ID"
					+ "   AND    B.MOVIE_ID = C.MOVIE_ID"
					+ "   AND    A.MEM_ID = '" + mem_id + "'"
					+ "   AND    C.MOVIE_ID = " + movie_id
					+ "   AND    ROWNUM = 1";
			
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				res_no = rs.getString("RES_NO");
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
		return res_no;
	}
	
	@Override
	public List<ReserveVO> readAllReserve() {
		// TODO Auto-generated method stub
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			
			List<ReserveVO> reserveList = new ArrayList<ReserveVO>();
			
			try {
				//1. Driver loading
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2. DB connection
				con = DriverManager.getConnection(url, id, pw);
				
				//3. query
				st = con.createStatement();
				String sql = "SELECT A.*, "
						   +        "B.CINEMA_ID, "
						   +        "B.CINEMA_NAME, "
						   +        "B.CINEMA_PRICE, "
						   +        "C.SHOW_START_TIME "
						   + "FROM RESERVE A, CINEMA B, SHOW_SC C "
						   + "WHERE A.SHOW_ID = C.SHOW_ID "
						   + "  AND B.CINEMA_ID = C.CINEMA_ID ";
				//4. result
				rs = st.executeQuery(sql);
				
				while(rs.next()) {
					ReserveVO reserve = new ReserveVO();
					reserve.setRes_no(rs.getInt("RES_NO"));
					reserve.setCinema_id(rs.getInt("CINEMA_ID"));
					reserve.setCinema_name(rs.getString("CINEMA_NAME"));
					reserve.setMem_id(rs.getString("MEM_ID"));
					reserve.setCinema_price(rs.getInt("CINEMA_PRICE"));
					reserve.setShow_start(rs.getString("SHOW_START_TIME"));
					reserve.setSeat_no(rs.getInt("SEAT_NO"));
					reserve.setShow_id(rs.getInt("SHOW_ID"));
					reserve.setReserve_delete(rs.getInt("RESERVE_DELETE"));
					reserveList.add(reserve);
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
			return reserveList;
	}
	
	
}
