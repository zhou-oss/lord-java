package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterDao {
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	private Connection conn = null;

	public RegisterDao() {
		conn = JDBConnectionDao.getConnection();
	}

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
