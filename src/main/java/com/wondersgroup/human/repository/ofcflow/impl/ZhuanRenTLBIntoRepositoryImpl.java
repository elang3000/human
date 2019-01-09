/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenTLBIntoRepositoryImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: 同类别转任 调入情况信息 知识库实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月1日 上午10:58:18 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月1日 上午10:58:18 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto;
import com.wondersgroup.human.repository.ofcflow.ZhuanRenTLBIntoRepository;

/** 
 * @ClassName: ZhuanRenTLBIntoRepositoryImpl 
 * @Description: 同类别转任 转入情况信息 知识库实现类
 * @author: tzy
 * @date: 2018年8月1日 上午10:58:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class ZhuanRenTLBIntoRepositoryImpl extends GenericRepositoryImpl<ZhuanRenTLBInto> implements ZhuanRenTLBIntoRepository{

	public Class<ZhuanRenTLBInto> getEntityClass() {
		return ZhuanRenTLBInto.class;
	}
	public ProcedureOutputs executeStoreProcedure(String storeProcedureName,Map<String,String> params,List<String> backList){
		ProcedureCall procedureCall = currentSession().createStoredProcedureCall(storeProcedureName);
		//设置输入参数
		for(Entry<String, String> entry:params.entrySet()){
			procedureCall.registerParameter(entry.getKey(), String.class,  ParameterMode.IN).bindValue(entry.getValue());
		}
		
		//设置输出参数
		for(String back : backList){
			procedureCall.registerParameter(back, String.class,  ParameterMode.OUT);
		}
		return procedureCall.getOutputs();
	}
}
