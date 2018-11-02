/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RegistrationService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc 
 * 描述: 人员信息子表-登记 服务接口
 * 创建人: jiang   
 * 创建时间: 2018年8月22日15:39:04
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年8月22日15:39:07
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofc.Registration;
import com.wondersgroup.human.vo.ofc.RegistrationVO;

/** 
 * @ClassName: RegistrationService 
 * @Description: 人员信息子表-登记服务接口
 * @author: jiang
 * @date: 2018年8月22日15:39:00
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RegistrationService extends GenericService<Registration>{

	Page<RegistrationVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit);

}
