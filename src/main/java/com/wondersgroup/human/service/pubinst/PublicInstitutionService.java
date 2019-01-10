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
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.businesspersonel.BusinessParam;
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
	 * @Title: getPage 
	 * @Description: 数据转换为VO的分页查询 DetachedCriteria
	 * @param detachedCriteria
	 * @param page
	 * @param limit
	 * @param ids
	 * @return
	 * @return: Page<PublicInstitutionVO>
	 */
	public Page<PublicInstitutionVO> getPage(DetachedCriteria detachedCriteria, Integer page, Integer limit,String ids);

	/**
	 * @Title: saveRegister 
	 * @Description: 审批人员登记
	 * @param temp  前台临时对象
	 * @param planState 
	 * @param mid 
	 * @return: void
	 */
	public void saveRegister(PublicInstitution temp, String opinion, String r, String planState, String mid);
	
	
	/**
	 * 通知人员人员信息
	 * @param id 类型表中对应的id值
	 * @param type  类型业务表
	 * @param title 通知标题
	 * @param content 通知内容
	 * @return 是否通知到人
	 */
	public boolean getPublicQuLeadersToNotice(String id, String type, String title, String content);
	
	/**
	 * 进行控编
	 * @param nowJobLevel  职级
	 * @return
	 */
	public boolean saveFormationControl(CodeInfo nowJobLevel);

	
	
	
	/**************************************事业单位综合查询*************************************************************************/
	 
	
	/**
	 * 
	 * @Title: getAllActiveBusiness 
	 * @Description: 获取所有在职人员
	 * @return
	 * @return: List<Servant>
	 */
	List<PublicInstitution> getAllActiveBusiness();

	/** 
	 * @param m 
	 * @Title: queryBusinessInfoBySeniorCondation 
	 * @Description: 综合查询
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ServantVO>
	 */
	Page<PublicInstitutionVO> queryBusinessInfoBySeniorCondation(List<BusinessParam> spList, Map<String, String> m, Integer page, Integer limit);
	/**
	 * 通过姓名和身份证号查询对应的servant
	 * @param cardNo
	 * @return
	 */
	public List<PublicInstitution> getBusinessByCardNo(String cardNo);
	
	
	
}
