package util;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * 获得当前系统时间类.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class getTime {
	
	/**
	 * 获得系统时间.
	 *
	 * @return String类型时间
	 */
	public static String nowTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String s = simpleDateFormat.format(date);
		return s;
	}
}
