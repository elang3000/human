/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProgramInfoServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.service.impl 
 * 描述: 预警预告：查询方案 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年10月29日 下午5:49:17 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月29日 下午5:49:17 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.system.warn.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.system.warn.bo.ProgramInfo;
import com.wondersgroup.system.warn.controller.QuartzJobController;
import com.wondersgroup.system.warn.dao.ProgramInfoRepository;
import com.wondersgroup.system.warn.service.ProgramInfoService;
import com.wondersgroup.system.warn.util.QuartzManager;
import com.wondersgroup.system.warn.vo.ProgramInfoVO;

/** 
 * @ClassName: ProgramInfoServiceImpl 
 * @Description: 预警预告：查询方案 服务实现类
 * @author: tzy
 * @date: 2018年10月29日 下午5:49:17
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ProgramInfoServiceImpl extends GenericServiceImpl<ProgramInfo> implements ProgramInfoService{
	
	@Autowired
	private QuartzManager quartzManager;
	
	@Autowired
	private ProgramInfoRepository programInfoRepository;
	
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ProgramInfoVO>
	 */
	public Page<ProgramInfoVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<ProgramInfo> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ProgramInfoVO> voList = new ArrayList<>();
		for(ProgramInfo s:temppage.getResult()){
			ProgramInfoVO vo = new ProgramInfoVO(s);
			voList.add(vo);
		}
		Page<ProgramInfoVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	
	/**
	 * @Title: initJob 
	 * @Description: 启动预警预告定时器
	 * @return: void
	 */
	public void getJob(){
		DetachedCriteria c = DetachedCriteria.forClass(ProgramInfo.class);
		c.add(Restrictions.eq("removed", false));
		c.add(Restrictions.eq("programState", ProgramInfo.WARN));//查询出启动状态的定时器
		List<ProgramInfo> list = programInfoRepository.findByCriteria(c);
		
		if(list!=null&&list.size()>0){
			for(ProgramInfo info:list){
				//启动定时器
				quartzManager.addJob(info.getName(), info.getId(), info.getName(),info.getId(), QuartzJobController.class, info.getProgramTime());
			}
		}
	}
}
