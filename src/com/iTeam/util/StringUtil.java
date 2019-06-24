package com.iTeam.util;

/**
 * 字符串工具类
 * @author XieZhiHao
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if(str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 格式化字符串
	 * @param str
	 * @return
	 */
	public static String formatString(String str) {
		if(StringUtil.isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}
}
