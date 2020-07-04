package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.beginDao;
import view.LoginUI;

public class beginDaoTest {
	ResultSet rs=null;
	String sql="select * from user";
	String sql1="insert into game values(?,?,?,?,?)";
	String number="67";
	String getscore="9000";
	String begin_time="2020-07-03 16:14:06";
	String over_time="2020-07-03 16:24:06";
	String user="xxx";
	beginDao dao=new beginDao();
	@Test
	public void saveUserInfo() {
		int rows=dao.saveUserInfo(sql1, number, getscore, begin_time, over_time, user);
		assertEquals(1,rows);
	}
	@Test
	public void queryUserInfo() {
		rs=dao.queryUserInfo(sql);
		try {
			rs.last();
			int rows=rs.getRow();
			assertEquals(1, rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
