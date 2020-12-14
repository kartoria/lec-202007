package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Factory Object[Method] Pattern 
 *
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	private static DataSource dataSource;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("/kr/or/ddit/db/dbinfo");
		String driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		int initialSize = Integer.parseInt(bundle.getString("initialSize"));
		long maxWait = Long.parseLong(bundle.getString("maxActive"));
		int maxActive = Integer.parseInt(bundle.getString("maxActive"));
		
		BasicDataSource ds = new BasicDataSource();
		dataSource = ds;
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setInitialSize(5);
		ds.setMaxWaitMillis(maxWait);
		ds.setMaxTotal(maxActive);
	}
	public static Connection getConnection() throws SQLException {
//		Connection conn = DriverManager.getConnection(url, user, password);
		Connection conn = dataSource.getConnection();
		return conn;
	}
}
