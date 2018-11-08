/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProbationServantRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: 试用期管理	知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年7月25日 下午3:14:26 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月25日 下午3:14:26 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.ProbationServant;

import java.util.Map;

/** 
 * @ClassName: ProbationServantRepository 
 * @Description: 试用期管理	知识库接口
 * @author: tzy
 * @date: 2018年7月25日 下午3:14:26
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ProbationServantRepository extends GenericRepository<ProbationServant>{

    public Map<String, Integer> getUnitProbationCount();
	
}
