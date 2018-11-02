package com.wondersgroup.human.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wondersgroup.framework.util.StringUtils;

/**
 * 日期类型转换类
 * @author lyf
 *
 */
public class DateFormat {

	/**
	 * 转成相应类型的时间格式
	 * @param content  文件内容
	 * @param format  指定的格式
	 * @return Date
	 */
	public static Date parseToDateFormat(String content, String format){
		
		String contentFormat = getContentFormat(content);
		
		if (content != null && contentFormat != null) {
			SimpleDateFormat sd = new SimpleDateFormat(contentFormat);
			SimpleDateFormat sd1 = new SimpleDateFormat(format);
			try {
				Date birthday = sd.parse(content.trim());
				if (StringUtils.isNotBlank(format)) {
					String result = sd1.format(birthday);
					return sd1.parse(result);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		
			
		return null;
	}

	/**
	 * 判断时期格式
	 * @param content  文件内容
	 * @return String
	 */
	private static String getContentFormat(String content) {
		if (StringUtils.isNotBlank(content)) {
			if (content.contains(".") && content.length() == 7 ) {
				return "yyyy.MM";
			}
			if (content.contains("/") && content.length() >= 8 ) {
				return "yyyy/MM/dd";
			}
			if (content.contains("-") && content.length() >= 8 ) {
				return "yyyy-MM-dd";
			}
			if (isNumeric(content) && content.length() == 6 ) {
				return "yyyyMM";
			}
			
		}
		return null;
	}
	
	
	/**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
	       Pattern pattern = Pattern.compile("[0-9]*");
	       Matcher isNum = pattern.matcher(str);
	       if( !isNum.matches() ){
	           return false;
	       }
	       return true;
    }
}
