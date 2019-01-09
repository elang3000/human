/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: InstitutionOrgFormationHistoryService.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service
 * 描述: 事业单位编制历史调整 服务接口
 * 创建人: jiang
 * 创建时间: 2018年12月5日15:31:47
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月5日15:31:50
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.organization;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormationHistory;
import com.wondersgroup.human.vo.organization.InstitutionOrgFormationHistoryVO;

/**
 * @ClassName: InstitutionOrgFormationHistoryService
 * @Description: 事业单位编制历史调整 服务接口
 * @author: jiang
 * @date: 2018年9月12日15:03:00
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface InstitutionOrgFormationHistoryService extends GenericService<InstitutionOrgFormationHistory> {
	
	Page<InstitutionOrgFormationHistoryVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit);
	
}
