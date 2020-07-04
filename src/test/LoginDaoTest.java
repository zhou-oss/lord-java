package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.LoginDao;

public class LoginDaoTest {
	ResultSet rs = null;
	String sql = "select * from user";
	LoginDao dao = new LoginDao();

	@Test
	public void queryUserInfo() {
		rs = dao.queryUserInfo(sql);
		try {
			rs.last();
			int rows = rs.getRow();
			assertEquals(2, rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	String sql1="insert into login values(?,?)";
	String c="222";
	String b="222";
	
	@Test
	public void saveUserInfo() {
		int a=dao.saveUserInfo(sql1, c, b);
		assertEquals(1,a);
	}
}
