package util;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * ��õ�ǰϵͳʱ����.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class getTime {
	
	/**
	 * ���ϵͳʱ��.
	 *
	 * @return String����ʱ��
	 */
	public static String nowTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String s = simpleDateFormat.format(date);
		return s;
	}
}
