package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class modifiDao.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class modifiDao {
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new modifi dao.
	 */
	public modifiDao() {
		conn = JDBConnectionDao.getConnection();
	}

	/**
	 * ����.
	 *
	 * @param sql the sql
	 * @return the int
	 */
	// �����û���Ϣ
	public int updataUserInfo(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * ɾ��.
	 *
	 * @param sql the sql
	 * @return the int
	 */
	// �����û���Ϣ
		public int deleteUserInfo(String sql) {
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				return 1;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
}
