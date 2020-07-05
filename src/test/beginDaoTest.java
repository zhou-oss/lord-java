package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.beginDao;
import view.LoginUI;

// TODO: Auto-generated Javadoc
/**
 *   beginDao测试.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class beginDaoTest {
	
	/** The rs. */
	ResultSet rs=null;
	
	/** The sql. */
	String sql="select * from user";
	
	/** The sql 1. */
	String sql1="insert into game values(?,?,?,?,?)";
	
	/** The number. */
	String number="67";
	
	/** The getscore. */
	String getscore="9000";
	
	/** The begin time. */
	String begin_time="2020-07-03 16:14:06";
	
	/** The over time. */
	String over_time="2020-07-03 16:24:06";
	
	/** The user. */
	String user="xxx";
	
	/** The dao. */
	beginDao dao=new beginDao();
	
	/**
	 * 插入方法测试.
	 */
	@Test
	public void saveUserInfo() {
		int rows=dao.saveUserInfo(sql1, number, getscore, begin_time, over_time, user);
		assertEquals(1,rows);
	}
	
	/**
	 * 查询方法测试.
	 */
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
