package test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.modifiDao;

public class modifiDaoTest {
	modifiDao dao=new modifiDao();
	String sql="update user set name='000',sex='ÄÐ' where user='111'";;
	@Test
	public void updataUserInfo() {
		int a=dao.updataUserInfo(sql);
		assertEquals(1,a);
	}

	@Test
	public void deleteUserInfo() {
		int a=dao.deleteUserInfo(sql);
		assertEquals(1,a);
	}
}
