/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:12:04 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:12:04 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.instflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.instflow.AlternatingRotationQueryParam;
import com.wondersgroup.human.dto.instflow.InfoRegisterQueryParam;
import com.wondersgroup.human.vo.instflow.AlternatingRotationVO;
import com.wondersgroup.human.vo.instflow.MemberInfoRegisterVO;

/** 
 * @ClassName: AlternatingRotationService 
 * @Description: TODO
 * @author: lyf
 * @date: 2018年5月28日 上午11:12:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface AlternatingRotationService extends GenericService<AlternatingRotation>{

	/**
	 * 根据主表外键id查询
	 * @param id
	 */
	public AlternatingRotation findAlternatingRotationByPid(String id);
	
	
	/**
	 * 操作流程
	 * @param temp
	 * @param opinion
	 * @param r
	 * @param planState
	 */
	public void executeAlternatingRotation(AlternatingRotation temp, String opinion, String r, String planState);


	/**
	 * 判断当前人员是否在流程处理中
	 * @param publicInstitution  人员主表信息
	 * @return  boolean  是否在流程处理中
	 */
	public boolean operationAlterFlag(PublicInstitution publicInstitution);
	
	/** 
	 * @Title: pageList 
	 * @param resignVO
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ResignVO>
	 */
	Page<AlternatingRotationVO> pageList(AlternatingRotationQueryParam param, Integer page, Integer limit);
	
	
}
