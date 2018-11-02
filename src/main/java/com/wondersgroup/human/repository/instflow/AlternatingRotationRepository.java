/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantDao.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository 
 * 描述: 人员信息维护知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年5月21日 上午11:20:27 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月21日 上午11:20:27 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.instflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;

/** 
 * @ClassName: AlternatingRotationRepository 
 * @Description: 事业单位     交流轮岗
 * @author: lyf
 * @date: 2018年9月14日 上午11:20:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface AlternatingRotationRepository extends GenericRepository< AlternatingRotation>{

	public  AlternatingRotation findAlternatingRotationByPid(String id);

	/**
	 * 判断当前人是否已在流程主表中处理
	 * @param publicInstitution 主表信息
	 * @return AlternatingRotation
	 */
	public AlternatingRotation operationAlterFlag(PublicInstitution publicInstitution);
	
}
