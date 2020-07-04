package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class gameDataDao {
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	private Connection conn = null;

	public gameDataDao() {
		conn = JDBConnectionDao.getConnection();
	}
	//查询用户信息，返回结果集 
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
