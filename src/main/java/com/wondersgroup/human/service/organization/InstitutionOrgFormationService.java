/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: InstitutionOrgFormationService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service 
 * 描述: 事业单位信息维护服务接口
 * 创建人: jiang 
 * 创建时间: 2018年12月5日15:27:44
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年12月5日15:27:46
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.organization;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;

/** 
 * @ClassName: InstitutionOrgFormationService 
 * @Description: 事业单位信息维护服务接口
 * @author: jiang
 * @date: 2018年12月5日15:26:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface InstitutionOrgFormationService extends GenericService<InstitutionOrgFormation>{


    public Page<InstitutionOrgFormation> getOrgFormationByName(String orgName,Integer limit,Integer page);

}
