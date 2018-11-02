/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EventFamilyRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 流程信息 家庭信息 知识库实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月2日 下午3:01:29 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月2日 下午3:01:29 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.EventFamily;
import com.wondersgroup.human.repository.ofcflow.EventFamilyRepository;

/** 
 * @ClassName: EventFamilyRepositoryImpl 
 * @Description: 流程信息 家庭信息 知识库实现类
 * @author: tzy
 * @date: 2018年8月2日 下午3:01:29
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class EventFamilyRepositoryImpl extends GenericRepositoryImpl<EventFamily> implements EventFamilyRepository{

	public Class<EventFamily> getEntityClass() {
		return EventFamily.class;
	}
	
}
