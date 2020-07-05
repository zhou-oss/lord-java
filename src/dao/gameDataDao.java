package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class gameDataDao.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class gameDataDao {
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new game data dao.
	 */
	public gameDataDao() {
		conn = JDBConnectionDao.getConnection();
	}
	
	/**
	 *��ѯ����.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
	//��ѯ�û���Ϣ�����ؽ���� 
	public ResultSet queryUserInfo(String sql) {
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}
}
