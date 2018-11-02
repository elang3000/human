/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DictUtils.java
 * 工程名: human
 * 包名: com.wondersgroup.framework.utils
 * 描述: 数据字典工具类
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月28日 下午2:30:07
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月28日 下午2:30:07
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.framework.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;

/**
 * @ClassName: DictUtils
 * @Description: 数据字典工具类
 * @author: Wonders-Rain
 * @date: 2018年5月28日 下午2:30:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class DictUtils {
	
	@Autowired
	private DictableService dictableService;
	
	/**
	 * 
	 * @Title: assemblyThreeLevelName 
	 * @Description: 组装二级下拉显示名称
	 * @param thirdCodeId
	 * @return
	 * @return: String
	 */
	public String assemblyTwoLevelName(String secondCodeId) {
		
		CodeInfo codeInfo = dictableService.loadWithLazy(secondCodeId, "parent");
		String secondName = dictableService.loadCodeInfoById(secondCodeId).getName().trim();
		String firstName = dictableService.loadCodeInfoById(codeInfo.getParent().getId()).getName().trim();
		String fullName = "";
		if (secondName.equals(firstName)) {
			fullName = firstName;
		} else {
			fullName = firstName + secondName;
		}
		
		return fullName;
	}
	
	/**
	 * 
	 * @Title: assemblyThreeLevelName 
	 * @Description: 组装三级下拉显示名称
	 * @param thirdCodeId
	 * @return
	 * @return: String
	 */
	public String assemblyThreeLevelName(String thirdCodeId) {
		
		CodeInfo codeInfo = dictableService.loadWithLazy(thirdCodeId, "parent");
		String thirdName = dictableService.loadCodeInfoById(thirdCodeId).getName().trim();
		String secondName = dictableService.loadCodeInfoById(codeInfo.getParent().getId()).getName().trim();
		String firstName = dictableService.loadCodeInfoById(codeInfo.getParent().getParent().getId()).getName().trim();
		String fullName = "";
		if (thirdName.equals(secondName)) {
			fullName = firstName;
		} else {
			fullName = firstName + secondName;
		}
		
		if (!fullName.equals(thirdName)) {
			fullName = fullName + thirdName;
		}
		return fullName;
	}
	
	/**
	 * @Title: operationCodeInfo
	 * @Description: BO对象中CodeInfo属性id如果为null或空字符串，将该属性值设置为null
	 * @param source BO对象
	 * @return: void
	 */
	public static void operationCodeInfo(Object source) {
		
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue instanceof CodeInfo) {
				CodeInfo c = (CodeInfo) srcValue;
				if (c.getId() == null || "".equals(c.getId())) {
					src.setPropertyValue(pd.getName(), null);
				}
			}
		}
	}
}
