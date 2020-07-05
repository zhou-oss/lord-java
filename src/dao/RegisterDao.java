package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterDao.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class RegisterDao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new register dao.
	 */
	public RegisterDao() {
		conn = JDBConnectionDao.getConnection();
	}

	/**
	 * 插入.
	 *
	 * @param sql the sql
	 * @param user the user
	 * @param paw the paw
	 * @param name the name
	 * @param sex the sex
	 * @param logtime the logtime
	 * @return the int
	 */
	// 注册用户信息
	public int saveUserInfo(String sql, String user,String paw,
							 String name,String sex,String logtime) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, paw);
			pstmt.setString(3, name);
			pstmt.setString(4, sex);
			pstmt.setString(5, logtime);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 查询.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
	// 查询用户信息，返回结果集
	public ResultSet queryUserInfo(String sql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;

	}
}
