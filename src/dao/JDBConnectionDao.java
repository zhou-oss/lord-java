package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBConnectionDao {
	static String driverName = "com.mysql.jdbc.Driver";
	static String uri = "jdbc:mysql://localhost:3306/ddz?user=root&password=12345&useSSL=true";
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(driverName);
			conn=DriverManager.getConnection(uri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		return conn;
		}
	}
}
