package util;
// TODO: Auto-generated Javadoc

/**
 * �жϹ���.
 *
 * @author zhouguangyu
 * @version  v1.0
 * @date 2020-7-5
 */
public class StringUtil {
	
	/**
	 * ����Ƿ�Ϊ��.
	 *
	 * @param �����ַ�
	 * @return �շ��� true
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ����Ƿ�Ϊ��.
	 *
	 * @param �����ַ�
	 * @return �շ��� true
	 */
	public static boolean isNotEmpty(String str) {
		if ((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

}