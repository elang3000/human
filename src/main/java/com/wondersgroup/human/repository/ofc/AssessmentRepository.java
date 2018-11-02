/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AssessmentRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: 人员信息子表-考核 知识库接口
 * 创建人: jiang   
 * 创建时间: 2018年7月2日10:25:12
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日10:25:15
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofc;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofc.Assessment;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * 
 * @ClassName: AssessmentRepository 
 * @Description: 人员信息子表-考核 知识库接口
 * @author: jiang
 * @date: 2018年7月2日10:25:25
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface AssessmentRepository extends GenericRepository<Assessment> {
	/**
	 * 
	 * @Title: isRecent3YearsFine 
	 * @Description: 查询人员近三年是否都优秀
	 * @param servant
	 * @return
	 * @return: boolean
	 */
	public boolean isRecent3YearsFine(Servant servant,Integer year);

	/**
	 * 
	 * @Title: isCurrentYearSeasonFine 
	 * @Description: 查询当年季度考核是否优秀
	 * @param servant
	 * @param year
	 * @return
	 * @return: boolean
	 */
	public boolean isCurrentYearSeasonFine(Servant servant,Integer year);
}
