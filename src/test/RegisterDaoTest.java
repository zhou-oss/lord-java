package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.RegisterDao;
import dao.beginDao;

public class RegisterDaoTest {
	ResultSet rs=null;
	String sql="select * from user";
	String sql1="insert into user values(?,?,?,?,?)";
	RegisterDao dao=new RegisterDao();
	@Test
	public void saveUserInfo() {
		int rows=dao.saveUserInfo(sql1, "000", "000", "000", "ÄÐ", "223");
		assertEquals(1,rows);
	}
	@Test
	public void queryUserInfo() {
		rs=dao.queryUserInfo(sql);
		try {
			rs.last();
			int rows=rs.getRow();
			assertEquals(6, rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
