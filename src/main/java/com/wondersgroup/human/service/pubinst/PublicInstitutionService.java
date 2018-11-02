/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service 
 * 描述: 人员信息维护服务接口
 * 创建人: tzy   
 * 创建时间: 2018年5月21日 上午11:01:25 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月21日 上午11:01:25 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.pubinst;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/** 
 * @ClassName: PublicInstitutionService 
 * @Description: 人员信息维护服务接口
 * @author: tzy
 * @date: 2018年5月21日 上午11:01:25
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface PublicInstitutionService extends GenericService<PublicInstitution>{
	/**
	 * 
	 * @Title: getPage 
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return
	 * @return: Page<ServantVO>
	 */
	public Page<PublicInstitutionVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);

	/**
	 * @Title: saveRegister 
	 * @Description: 审批人员登记
	 * @param temp  前台临时对象
	 * @param planState 
	 * @param mid 
	 * @return: void
	 */
	public void saveRegister(PublicInstitution temp, String opinion, String r, String planState, String mid);
}
