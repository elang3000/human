/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OrgFormationRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository 
 * 描述: 单位编制信息维护知识库接口
 * 创建人: jiang  
 * 创建时间: 2018年9月19日11:01:24
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年9月19日11:01:27
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.organization;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.organization.OrgFormation;

/** 
 * @ClassName: OrgFormationRepository 
 * @Description: 单位编制信息维护知识库接口
 * @author: jiang
 * @date: 2018年9月19日11:01:30
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface OrgFormationRepository extends GenericRepository<OrgFormation>{
	
}
