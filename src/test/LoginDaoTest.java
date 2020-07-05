package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.LoginDao;

// TODO: Auto-generated Javadoc
/**
 * LoginDao  Test.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class LoginDaoTest {
	
	/** The rs. */
	ResultSet rs = null;
	
	/** The sql. */
	String sql = "select * from user";
	
	/** The dao. */
	LoginDao dao = new LoginDao();

	/**
	 * 查询方法测试.
	 */
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
	
	/** The sql 1. */
	String sql1="insert into login values(?,?)";
	
	/** The c. */
	String c="222";
	
	/** The b. */
	String b="222";
	
	/**
	 * 插入方法测试.
	 */
	@Test
	public void saveUserInfo() {
		int a=dao.saveUserInfo(sql1, c, b);
		assertEquals(1,a);
	}
}
