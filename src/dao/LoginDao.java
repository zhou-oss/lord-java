package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    
// TODO: Auto-generated Javadoc
/**
 * The Class LoginDao.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class LoginDao {
	
	/** The stmt. */
	private Statement stmt;
	
	/** The pstmt. */
	private PreparedStatement pstmt;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conn. */
	private Connection conn = null;

	/**
	 * Instantiates a new login dao.
	 */
	public LoginDao() {
		conn = JDBConnectionDao.getConnection();
	}
	
	/**
	 * 查询方法.
	 *
	 * @param sql the sql
	 * @return the result set
	 */
	//查询用户信息，返回结果集 
	public ResultSet queryUserInfo(String sql) {
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 插入方法.
	 *
	 * @param sql the sql
	 * @param user the user
	 * @param paw the paw
	 * @return the int
	 */
	// 保存用户信息
		public int saveUserInfo(String sql, String user,String paw) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user);
				pstmt.setString(2, paw);
				pstmt.executeUpdate();
				return 1;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}finally {
				
			}
		}
}
