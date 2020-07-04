package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    
public class LoginDao {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn = null;

	public LoginDao() {
		conn = JDBConnectionDao.getConnection();
	}
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
