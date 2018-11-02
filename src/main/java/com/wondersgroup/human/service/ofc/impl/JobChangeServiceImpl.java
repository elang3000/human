/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: JobChangeServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl 
 * 描述: 人员信息子表-调出情况 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年7月30日 上午10:40:37 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月30日 上午10:40:37 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofc.JobChange;
import com.wondersgroup.human.repository.ofc.JobChangeRepository;
import com.wondersgroup.human.service.ofc.JobChangeService;

/**
 * 
 * @ClassName: JobChangeServiceImpl 
 * @Description: 职务变动
 * @author: youyd
 * @date: 2018年9月18日 上午10:49:54
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class JobChangeServiceImpl extends GenericServiceImpl<JobChange> implements JobChangeService{
	@Autowired
	private JobChangeRepository JobChangeRepository;
	
	
}
