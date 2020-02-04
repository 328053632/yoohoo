package com.yoohoo.en.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 正则工具类
 * 
 * @author Ray
 * 
 *         2013年8月25日
 */
public class RegexUtils {
	/**
	 * 邮箱正则
	 */
	public static final String emalRegex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
	/**
	 * 手机正则
	 */
	public static final String mobilePhoneRegex = "^0?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$";
	/**
	 * 是否为数字
	 */
	public static final String numberRegex = "^\\d\\d*$";

	/**
	 * 正则匹配
	 * 
	 * @param input
	 * @param regex
	 * @return
	 */
	public static boolean matcher(String input, String regex) {
		if (StringUtils.isBlank(input))
			return false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}
