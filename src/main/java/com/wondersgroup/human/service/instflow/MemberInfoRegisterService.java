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

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.instflow.MemberInfoRegister;
import com.wondersgroup.human.dto.instflow.InfoRegisterQueryParam;
import com.wondersgroup.human.dto.ofcflow.ResignServantQueryParam;
import com.wondersgroup.human.vo.instflow.MemberInfoRegisterVO;

/** 
 * @ClassName: MemberInfoRegisterService 
 * @Description: TODO
 * @author: lyf
 * @date: 2018年5月28日 上午11:12:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface MemberInfoRegisterService extends GenericService<MemberInfoRegister>{

	/**
	 * 根据主表外键id查询
	 * @param id
	 */
	public MemberInfoRegister findMemberInfoRegisterByPid(String id);
	
	
	/**
	 * 
	 * @Title: findByFilterVO 
	 * @Description: 列表分页
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @return: Page<RecruitEmployPlanVO>
	 */
	Page<MemberInfoRegisterVO> findByFilterVO(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);
	
	
	/** 
	 * @Title: pageList 
	 * @Description: 辞职信息页面内bo转成vo
	 * @param resignVO
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ResignVO>
	 */
	Page<MemberInfoRegisterVO> pageList(InfoRegisterQueryParam param, Integer page, Integer limit);
}
