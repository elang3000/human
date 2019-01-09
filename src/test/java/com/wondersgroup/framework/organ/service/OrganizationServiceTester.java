
package com.wondersgroup.framework.organ.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.test.AbstractTest;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheInitializer;
import com.wondersgroup.framework.organization.service.OrganTreeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.organization.vo.OrganNodeVO;

public class OrganizationServiceTester extends AbstractTest {
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	OrganCacheInitializer organCacheInitializer;
	
	@Autowired
	OrganTreeService organTreeService;
	
	public void getCurrentUnitOrganForOrganNode() {
		
		OrganNode organNode = organizationService.getCurrentUnitOrganForOrganNodeCode("shenxizhongxin_ceshi");
		logger.info(">>>" + organNode.toString());
	}
	
	// @Test
	public void getJuniorOrganNodeForOrganNodeInHrRelation() {
		
		Long startTime = Calendar.getInstance().getTimeInMillis();
		List<OrganNode> organNodes = organizationService.getJuniorOrganNodeForOrganNodeInAdminRelation("312");
		for (OrganNode organNode : organNodes) {
			logger.info(">>>" + organNode.getAllName());
		}
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		
		startTime = Calendar.getInstance().getTimeInMillis();
		organNodes = organizationService.getJuniorOrganNodeForOrganNodeInAdminRelation("312");
		for (OrganNode organNode : organNodes) {
			logger.info(">>>" + organNode.getAllName());
		}
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
	}
	
	//@Test
	public void organCacheProvider() {
		
	/*	Long startTime = Calendar.getInstance().getTimeInMillis();
		organCacheInitializer.initialize();
		logger.info("异步执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));
		startTime = Calendar.getInstance().getTimeInMillis();
		organNode = OrganCacheProvider.getOrganNodeInGovNode(2765l);
		logger.info("执行时间：" + (Calendar.getInstance().getTimeInMillis() - startTime));*/
	}
	
	//@Test
	public void queryAvailabledOrganNode() {
		/*List<OrganTree> organTreees = organTreeService.queryAvailabledOrganTree();
		logger.debug("结果：" + organTreees.size());*/
/*		List<OrganNodeVO> organNodes = organizationService.queryAvailabledDepartmentOrganNode("394e21fa-1eb6-42ee-ba32-50655fa16517", null);
		logger.debug("结果：" + organNodes.size());*/
	}
	
	@Test
	public void queryOrganNodeByType() {
		List<String> organNodeTypeCode = new ArrayList<String>();
		organNodeTypeCode.add(CommonConst.ORGAN_TYPE_D_CLASS_CODE);
		organNodeTypeCode.add(CommonConst.ORGAN_TYPE_UNIT_CODE);
		Page<OrganNodeVO> pages =organizationService.queryOrganNodeByType("394e21fa-1eb6-42ee-ba32-50655fa16517", "",organNodeTypeCode, 1, 10);
		logger.debug("测试结果：" + pages.getResult().size());
	}
}
