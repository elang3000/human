/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OfcFlowNumberService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 流程信息打印编号 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年12月3日 上午9:39:31 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月3日 上午9:39:31 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.Map;

import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.OfcFlowNumber;

/** 
 * @ClassName: OfcFlowNumberService 
 * @Description: 流程信息打印编号 服务接口
 * @author: tzy
 * @date: 2018年12月3日 上午9:39:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface OfcFlowNumberService extends GenericService<OfcFlowNumber>{
	/**
	 * @Title: executeNumber 
	 * @Description: 根据业务类型和业务ID获取流程编号 规则：如果该业务没有对应的编号，生成该业务的编号，最新编号加1，如果年度更换，年度增加，编号重置为001
	 * @param busType
	 * @param busId
	 * @return year:年度    number:编号
	 * @return: Map<String,String>
	 */
	public Map<String,String> executeNumber(String busType,String busId);
}
