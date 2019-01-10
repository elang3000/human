/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantDao.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository 
 * 描述: 人员信息维护知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年5月21日 上午11:20:27 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月21日 上午11:20:27 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.pubinst;

import java.util.List;
import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.dto.businesspersonel.BusinessParam;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/** 
 * @ClassName: ServantDao 
 * @Description: 人员信息维护知识库接口
 * @author: tzy
 * @date: 2018年5月21日 上午11:20:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface PublicInstitutionRepository extends GenericRepository<PublicInstitution>{
	
	Map<String, Integer> statistBusinesstTopEducation(List<String> organNodeIds,String isHold);
	
	/**
	 * 事业单位综合查询
	 * @param spList
	 * @param m
	 * @param page
	 * @param limit
	 * @return
	 */
	Page<PublicInstitutionVO> queryBusinesstInfoBySeniorCondation(List<BusinessParam> spList, Map<String, String> m, Integer page, Integer limit);

	
	
}
