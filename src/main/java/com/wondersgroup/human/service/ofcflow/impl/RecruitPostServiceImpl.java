/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPlanServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 职务简章-职位信息 服务实现类
 * 创建人: wangzhanfei   
 * 创建时间: 2018年5月28日 上午11:13:23 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年5月28日 上午11:13:23 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.RecruitPost;
import com.wondersgroup.human.repository.ofcflow.RecruitEmployPlanDAO;
import com.wondersgroup.human.service.ofcflow.RecruitPostService;
import com.wondersgroup.human.vo.ofcflow.RecruitPostVO;

/** 
 * @ClassName: RecruitEmployPlanServiceImpl 
 * @Description: 职务简章-职位信息 服务实现类
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午11:13:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class RecruitPostServiceImpl  extends GenericServiceImpl<RecruitPost> implements RecruitPostService{
	
	@Autowired
	RecruitEmployPlanDAO recruitEmployPlanDAO;
	
	/**
	 * 	(non Javadoc) 
	 * @Title: findByFilterVO
	 * @Description:  列表查询
	 * @param planid 组织招录计划id
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return 
	 * @see com.wondersgroup.human.service.ofcflow.RecruitEmployPlanService#findByFilterVO(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	  @Override
	  public  Page<RecruitPostVO> findByFilterVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		  Page<RecruitPost> temppage=findByHQL(hql, listqueryparametry, pageNo, pagesize);
			List<RecruitPostVO> voList = new ArrayList<>();
			for(RecruitPost s:temppage.getResult()){
				RecruitPostVO vo = new RecruitPostVO(s);
				voList.add(vo);
			}
			Page<RecruitPostVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
			return page;
	  };
	  
   
}
