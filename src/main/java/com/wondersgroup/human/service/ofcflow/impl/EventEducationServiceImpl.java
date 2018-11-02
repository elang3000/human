/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: EventEducationServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 流程信息 学历 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月2日 下午3:12:37 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月2日 下午3:12:37 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.EventEducation;
import com.wondersgroup.human.service.ofcflow.EventEducationService;
import com.wondersgroup.human.vo.ofcflow.EventEducationVO;

/** 
 * @ClassName: EventEducationServiceImpl 
 * @Description: 流程信息 学历 服务实现类
 * @author: tzy
 * @date: 2018年8月2日 下午3:12:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class EventEducationServiceImpl extends GenericServiceImpl<EventEducation> implements EventEducationService{
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 返回VO的分页查询
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<EventEducationVO>
	 */
	public Page<EventEducationVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<EventEducation> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<EventEducationVO> voList = new ArrayList<>();
		for(EventEducation s:temppage.getResult()){
			EventEducationVO vo = new EventEducationVO(s);
			voList.add(vo);
		}
		Page<EventEducationVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
}