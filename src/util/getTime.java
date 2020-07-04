package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class getTime {
	public static String nowTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String s = simpleDateFormat.format(date);
		return s;
	}
}
