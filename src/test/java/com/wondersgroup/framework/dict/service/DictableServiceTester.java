package com.wondersgroup.framework.dict.service;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.wondersgroup.framework.core.test.AbstractTest;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.provider.DictCacheProvider;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;


public class DictableServiceTester extends AbstractTest {
	
	@Test
	public void dictCacheProvider() {
	
		Long startTime = Calendar.getInstance().getTimeInMillis();
		List<CodeInfo> codeInfos = DictCacheProvider.getUnclesByCodeTypeCodeAndCodeInfoCode("EMPLOY_TYPE", null);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		 codeInfos = DictCacheProvider.getUnclesByCodeTypeCodeAndCodeInfoCode("EMPLOY_TYPE", null);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		codeInfos = DictCacheProvider.getUnclesByCodeTypeCodeAndCodeInfoCode("EMPLOY_TYPE", null);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		codeInfos = DictCacheProvider.getUnclesByCodeTypeCodeAndCodeInfoCode("EMPLOY_TYPE", null);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		codeInfos = DictCacheProvider.getUnclesByCodeTypeCodeAndCodeInfoCode("EMPLOY_TYPE", null);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		codeInfos = DictCacheProvider.getUnclesByCodeTypeCodeAndCodeInfoCode("EMPLOY_TYPE", null);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		codeInfos = DictCacheProvider.getUnclesByCodeTypeCodeAndCodeInfoCode("EMPLOY_TYPE", null);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
	}
}
