package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class beginDao.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class beginDao {
	
	/** The stmt. */
	private Statement stmt;
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new begin dao.
	 */
	public beginDao() {
		conn = JDBConnectionDao.getConnection();
	}

	/**
	 * 插入操作.
	 *
	 * @param sql the sql
	 * @param number the number
	 * @param getScore the get score
	 * @param bgame the bgame
	 * @param ogame the ogame
	 * @param user the user
	 * @return the int
	 */
	// 保存用户信息
	public int saveUserInfo(String sql, String number, String getScore, String bgame, String ogame, String user) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			pstmt.setString(2, getScore);
			pstmt.setString(3, bgame);
			pstmt.setString(4, ogame);
			pstmt.setString(5, user);
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
		}
	}

	/**
	 * 查询操作.
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
