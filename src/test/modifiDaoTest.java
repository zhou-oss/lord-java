package test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.modifiDao;

// TODO: Auto-generated Javadoc
/**
 *modifiDao����.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class modifiDaoTest {
	
	/** The dao. */
	modifiDao dao=new modifiDao();
	
	/** The sql. */
	String sql="update user set name='000',sex='��' where user='111'";;
	
	/**
	 *���·�������.
	 */
	@Test
	public void updataUserInfo() {
		int a=dao.updataUserInfo(sql);
		assertEquals(1,a);
	}

	/**
	 * ɾ����������.
	 */
	@Test
	public void deleteUserInfo() {
		int a=dao.deleteUserInfo(sql);
		assertEquals(1,a);
	}
}
