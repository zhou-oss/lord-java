package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.gameDataDao;

public class gameDataDaoTest {
	ResultSet rs=null;
	String sql="select * from user";
	gameDataDao dao=new gameDataDao();
	@Test
	public void queryUserInfo() {
		rs=dao.queryUserInfo(sql);
		try {
			rs.last();
			int rows=rs.getRow();
			assertEquals(2, rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
