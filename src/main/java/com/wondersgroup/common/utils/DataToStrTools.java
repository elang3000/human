/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DataToStrTools.java 
 * 工程名: human
 * 包名: com.wondersgroup.common.utils 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年5月30日 下午3:22:12 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年5月30日 下午3:22:12 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.common.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/** 
 * @ClassName: DataToStrTools 
 * @Description: TODO
 * @author: GP
 * @date: 2018年5月30日 下午3:22:12
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class DataToStrTools {
	
	/**
	 * 时间转字符串
	 * @Title: DateToStr 
	 * @Description: TODO
	 * @param dateTime
	 * @param 字符串格式；默认yyyy-mm-dd; 
	 * @return
	 * @return: String
	 */
	public static String DateToStr(Date dateTime, String formatterstr) {
		if (dateTime==null) {
			return "";
		}
		else{
			SimpleDateFormat formatter;
			if (StringUtils.isNotBlank(formatterstr))
				formatter = new SimpleDateFormat(formatterstr);
			else formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(dateTime);
			return dateString;
		}
	}
	
	
	/**
	 * 时间字符串转日期 字符串格式yyyy-MM-dd HH:mm:ss
	 * @Title: StrToDate 
	 * @Description: TODO
	 * @param dateStr
	 * @return
	 * @return: Date
	 */
	public static Date StrToDate(String dateStr){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date strtodate = formatter.parse(dateStr, pos);
			return strtodate;
		} catch (Exception e) {
			return new Date();
		}
		
	}
	
	/**
	 * 日期字符串转日期 字符串格式yyyy-MM-dd
	 * @Title: strToDate 
	 * @Description: TODO
	 * @param strDate
	 * @return
	 * @return: Date
	 */
	public static Date strToDate(String strDate) {
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ParsePosition pos = new ParsePosition(0);
			Date strtodate = formatter.parse(strDate, pos);
			return strtodate;
		} catch (Exception e) {
			return new Date();
		}
		
	}
}
