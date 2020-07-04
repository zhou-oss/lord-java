package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modifiDao {
	private Statement stmt;
	private ResultSet rs;
	private Connection conn = null;

	public modifiDao() {
		conn = JDBConnectionDao.getConnection();
	}

	// 保存用户信息
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
	
	// 保存用户信息
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
