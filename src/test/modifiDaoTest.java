package test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.modifiDao;

// TODO: Auto-generated Javadoc
/**
 *modifiDao²âÊÔ.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class modifiDaoTest {
	
	/** The dao. */
	modifiDao dao=new modifiDao();
	
	/** The sql. */
	String sql="update user set name='000',sex='ÄÐ' where user='111'";;
	
	/**
	 *¸üÐÂ·½·¨²âÊÔ.
	 */
	@Test
	public void updataUserInfo() {
		int a=dao.updataUserInfo(sql);
		assertEquals(1,a);
	}

	/**
	 * É¾³ý²Ù×÷²âÊÔ.
	 */
	@Test
	public void deleteUserInfo() {
		int a=dao.deleteUserInfo(sql);
		assertEquals(1,a);
	}
}
