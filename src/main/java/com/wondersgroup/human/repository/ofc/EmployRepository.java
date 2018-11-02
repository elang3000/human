/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EmployRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: 人员信息子表-录用 知识库接口
 * 创建人: jiang   
 * 创建时间: 2018年6月1日10:06:52
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月1日10:06:55
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofc;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofc.Employ;

/**
 * 
 * @ClassName: EmployRepository 
 * @Description: 人员信息子表-录用 知识库接口
 * @author: jiang
 * @date: 2018年6月1日 上午9:25:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface EmployRepository extends GenericRepository<Employ> {
	
}
