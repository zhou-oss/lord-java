package util;
// TODO: Auto-generated Javadoc

/**
 * 判断工具.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class StringUtil {
	
	/**
	 * 检查是否为空.
	 *
	 * @param 传入字符
	 * @return 空返回 true
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查是否为空.
	 *
	 * @param 传入字符
	 * @return 空返回 true
	 */
	public static boolean isNotEmpty(String str) {
		if ((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

}