package dao;

import java.sql.Connection;
import java.sql.DriverManager;

// TODO: Auto-generated Javadoc
/**
 * �������ݿ�.
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
	 * ��ȡ����.
	 *
	 * @return ���Ӷ���
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
