package test;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import dao.gameDataDao;

// TODO: Auto-generated Javadoc
/**
 * gameDataDao ≤‚ ‘
 * 
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class gameDataDaoTest {
	
	/** The rs. */
	ResultSet rs=null;
	
	/** The sql. */
	String sql="select * from user";
	
	/** The dao. */
	gameDataDao dao=new gameDataDao();
	
	/**
	 * ≤È—Ø∑Ω∑®≤‚ ‘.
	 */
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
