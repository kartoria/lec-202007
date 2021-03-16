package notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class INoticeDaoImpl implements INoticeDao {

	private static INoticeDao noticeDao;

	private INoticeDaoImpl() {

	}

	public static INoticeDao getInstance() {
		if (noticeDao == null) {
			noticeDao = new INoticeDaoImpl();
		}
		return noticeDao;
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private String id = "PJT";
	private String pw = "java";
	private String driver = "oracle.jdbc.driver.OracleDriver";

	@Override
	public List<NoticeVO> readNotice() {

		List<NoticeVO> list = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			// 1.
			Class.forName(driver);

			// 2.
			con = DriverManager.getConnection(url, id, pw);

			// 3.
			stmt = con.createStatement();
			String sql = "SELECT * "
					+ "     FROM  NOTICE";
			
			rs = stmt.executeQuery(sql);
			
			while( rs.next() ) {
				NoticeVO notice = new NoticeVO();
				notice.setAdmin_id(rs.getString("ADMIN_ID"));
				notice.setNotice_commit(rs.getString("NOTICE_COMMENT"));
				notice.setNotice_no(rs.getString("NOTICE_NO"));
				notice.setNotice_title(rs.getString("NOTICE_TITLE"));
				list.add(notice);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("로딩실패");
		} catch (SQLException e) {
			System.out.println("접속실패");
		} finally {

			try {
				if (rs != null) {
					// 객체가 생성되지 않았다면 close()를 할 필요가 없다.
					rs.close();
				}

				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반환 실패");
			}
		}
		return list;

	}

	@Override
	public int updateNoticeTitle(Map<String, String> info) {
		
		Connection con = null;
		Statement stmt = null;

		String notice_title = info.get("notice_title");
		String notice_no = info.get("notice_no");
		int rs = 0;
		
		try {

			// 1.
			Class.forName(driver);

			// 2.
			con = DriverManager.getConnection(url, id, pw);

			// 3.
			stmt = con.createStatement();
			String sql = "UPDATE NOTICE "
					  	 + "	  SET NOTICE_TITLE = '" + notice_title + "'"
					  	 + "WHERE  NOTICE_NO = '" + notice_no + "'";
			
			rs = stmt.executeUpdate(sql);


		} catch (ClassNotFoundException e) {
			System.out.println("로딩실패");
		} catch (SQLException e) {
			System.out.println("접속실패");
		} finally {

			try {

				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반환 실패");
			}
		}
		return rs;
	}

	@Override
	public int updateNoticeComment(Map<String, String> info) {
		
		Connection con = null;
		Statement stmt = null;

		String notice_comment = info.get("notice_comment");
		String notice_no = info.get("notice_no");
		int rs = 0;
		
		try {

			// 1.
			Class.forName(driver);

			// 2.
			con = DriverManager.getConnection(url, id, pw);

			// 3.
			stmt = con.createStatement();
			String sql = "UPDATE NOTICE "
					  	 + "	  SET NOTICE_COMMENT = '" + notice_comment + "'"
					  	 + "WHERE  NOTICE_NO = '" + notice_no + "'";
			
			rs = stmt.executeUpdate(sql);


		} catch (ClassNotFoundException e) {
			System.out.println("로딩실패");
		} catch (SQLException e) {
			System.out.println("접속실패");
		} finally {

			try {

				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반환 실패");
			}
		}
		return rs;
	}

	@Override
	public int deleteNotice(String notice_no) {
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		
		try {

			// 1.
			Class.forName(driver);

			// 2.
			con = DriverManager.getConnection(url, id, pw);

			// 3.
			stmt = con.createStatement();
			String sql = "DELETE FROM NOTICE "
						 + "WHERE NOTICE_NO = '" + notice_no + "'";

			rs = stmt.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			System.out.println("로딩실패");
		} catch (SQLException e) {
			System.out.println("접속실패");
		} finally {

			try {

				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반환 실패");
			}
		}
		return rs;
	}

	@Override
	public int createNotice(Map<String, String> info) {
		
		Connection con = null;
		Statement stmt = null;
		int rs = 0;
		String notice_title = info.get("notice_title");
		String notice_comment = info.get("notice_comment");
		String admin = "admin";
		
		try {

			// 1.
			Class.forName(driver);

			// 2.
			con = DriverManager.getConnection(url, id, pw);

			// 3.
			stmt = con.createStatement();
			String sql = "INSERT INTO NOTICE (NOTICE_NO, NOTICE_TITLE, NOTICE_COMMENT, ADMIN_ID)"
					+ " VALUES (NOTICE_SEQ.NEXTVAL, '" + notice_title + "', '" + notice_comment + "', '" + admin + "')";

			rs = stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {

			try {

				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("자원반환 실패");
			}
		}
		return rs;
	}
}
