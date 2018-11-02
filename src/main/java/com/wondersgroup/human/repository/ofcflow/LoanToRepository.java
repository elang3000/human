/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: LoanToRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: 借调 知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年9月25日 下午3:00:40 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月25日 下午3:00:40 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.LoanTo;

/** 
 * @ClassName: LoanToRepository 
 * @Description: 借调 知识库接口
 * @author: tzy
 * @date: 2018年9月25日 下午3:00:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface LoanToRepository extends GenericRepository<LoanTo>{
	
}
