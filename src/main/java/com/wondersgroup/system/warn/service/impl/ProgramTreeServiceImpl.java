/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProgramTreeServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.service.impl 
 * 描述: 预警预告：字段信息 tree 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年10月31日 下午2:06:26 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月31日 下午2:06:26 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.system.warn.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.service.impl.GenericTreeServiceImpl;
import com.wondersgroup.system.warn.bo.ProgramTree;
import com.wondersgroup.system.warn.service.ProgramInfoService;
import com.wondersgroup.system.warn.service.ProgramTreeService;

/** 
 * @ClassName: ProgramTreeServiceImpl 
 * @Description: 预警预告：字段信息 tree 服务实现类
 * @author: tzy
 * @date: 2018年10月31日 下午2:06:26
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ProgramTreeServiceImpl extends GenericTreeServiceImpl<ProgramTree> implements ProgramTreeService{
	
	@Autowired
	private ProgramInfoService programInfoService;
	
	/**
	 * @Title: init 
	 * @Description: 系统启动成功之后进入该方法：将预警预告的定时器启动
	 * @return: void
	 */
	@PostConstruct
	public void init() {
		programInfoService.getJob();
	}
}
