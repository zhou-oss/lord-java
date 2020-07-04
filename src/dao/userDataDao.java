package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dao.JDBConnectionDao;

public class userDataDao {
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	private Connection conn = null;

	public userDataDao() {
		conn = JDBConnectionDao.getConnection();
	}

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
