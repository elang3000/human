/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPeopleRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月11日 下午4:52:01 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月11日 下午4:52:01 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.AbroadPeople;

/** 
 * @ClassName: AbroadPeopleRepository 
 * @Description: 因公出国人员知识库接口
 * @author: lihao
 * @date: 2018年12月11日 下午4:52:01
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface AbroadPeopleRepository extends GenericRepository<AbroadPeople>{
	
	/**
	 * 
	 * @Title: selectAbroadTimeByServantId 
	 * @Description: 查询该人员是否在其他批件时间内
	 * @param id
	 * @return: int
	 */
	public int selectAbroadTimeByServantId(String id,String abroadYearId);

}