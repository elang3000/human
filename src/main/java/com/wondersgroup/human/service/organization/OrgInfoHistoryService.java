/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OrgInfoHistoryService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service 
 * 描述: 单位信息历史调整 服务接口
 * 创建人: jiang 
 * 创建时间: 2018年9月20日18:51:07
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月20日18:51:12
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.organization;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.organization.OrgInfoHistory;
import com.wondersgroup.human.vo.organization.OrgInfoHistoryVO;
import com.wondersgroup.human.vo.organization.OrgInfoVO;

/** 
 * @ClassName: OrgInfoHistoryService 
 * @Description: 单位信息历史调整 服务接口
 * @author: jiang
 * @date: 2018年9月20日18:51:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface OrgInfoHistoryService extends GenericService<OrgInfoHistory>{

	Page<OrgInfoHistoryVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit);
	
}
