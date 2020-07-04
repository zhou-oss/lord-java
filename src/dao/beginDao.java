package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class beginDao {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection conn = null;

	public beginDao() {
		conn = JDBConnectionDao.getConnection();
	}

	// �����û���Ϣ
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

	// ��ѯ�û���Ϣ�����ؽ����
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
