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
