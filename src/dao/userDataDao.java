package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dao.JDBConnectionDao;

// TODO: Auto-generated Javadoc
/**
 * The Class userDataDao.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class userDataDao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new user data dao.
	 */
	public userDataDao() {
		conn = JDBConnectionDao.getConnection();
	}

	/**
	 * 插入.
	 *
	 * @param sql the sql
	 * @param user the user
	 * @param name the name
	 * @param sum the sum
	 * @param livel the livel
	 * @param score the score
	 * @return the int
	 */
	// 保存用户信息
	public int saveUserInfo(String sql, String user, String name, 
			String sum, String livel,String score) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, name);
			pstmt.setString(3, sum);
			pstmt.setString(4, livel);
			pstmt.setString(5, score);
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
