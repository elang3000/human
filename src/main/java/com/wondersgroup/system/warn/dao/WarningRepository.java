/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: WarningRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.dao 
 * 描述: 预警预告：定时执行任务 知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年10月30日 上午11:02:57 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月30日 上午11:02:57 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.system.warn.dao;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.system.warn.bo.Warning;

/** 
 * @ClassName: WarningRepository 
 * @Description: 预警预告：定时执行任务 知识库接口
 * @author: tzy
 * @date: 2018年10月30日 上午11:02:57
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface WarningRepository extends GenericRepository<Warning>{
	
}
