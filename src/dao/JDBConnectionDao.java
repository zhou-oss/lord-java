package dao;

import java.sql.Connection;
import java.sql.DriverManager;

// TODO: Auto-generated Javadoc
/**
 * 连接数据库.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class JDBConnectionDao {
	
	/** The driver name. */
	static String driverName = "com.mysql.jdbc.Driver";
	
	/** The uri. */
	static String uri = "jdbc:mysql://localhost:3306/ddz?user=root&password=12345&useSSL=true";
	
	/**
	 * 获取连接.
	 *
	 * @return 连接对象
	 */
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
