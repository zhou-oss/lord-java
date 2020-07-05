package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.RegisterDao;
import dao.beginDao;

// TODO: Auto-generated Javadoc
/**
 * RegisterDao 测试.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class RegisterDaoTest {
	
	/** The rs. */
	ResultSet rs=null;
	
	/** The sql. */
	String sql="select * from user";
	
	/** The sql 1. */
	String sql1="insert into user values(?,?,?,?,?)";
	
	/** The dao. */
	RegisterDao dao=new RegisterDao();
	
	/**
	 * 插入方法测试.
	 */
	@Test
	public void saveUserInfo() {
		int rows=dao.saveUserInfo(sql1, "000", "000", "000", "男", "223");
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
			assertEquals(6, rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
