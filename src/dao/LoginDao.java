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
	 * ��ѯ����.
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * ���뷽��.
	 *
	 * @param sql the sql
	 * @param user the user
	 * @param paw the paw
	 * @return the int
	 */
	// �����û���Ϣ
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
