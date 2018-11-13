/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: LoanToServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 借调 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年9月25日 下午3:06:40 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月25日 下午3:06:40 
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
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.human.bo.ofc.Experience;
import com.wondersgroup.human.bo.ofcflow.LoanTo;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.record.ServantHumamKeepRecordEvent;
import com.wondersgroup.human.service.ofc.ExperienceService;
import com.wondersgroup.human.service.ofcflow.LoanToService;
import com.wondersgroup.human.vo.ofcflow.LoanToVO;

/** 
 * @ClassName: LoanToServiceImpl 
 * @Description: 借调 服务实现类
 * @author: tzy
 * @date: 2018年9月25日 下午3:06:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class LoanToServiceImpl extends GenericServiceImpl<LoanTo> implements LoanToService{
	@Autowired
	private ExperienceService experienceService;
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<LoanToVO>
	 */
	public Page<LoanToVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<LoanTo> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<LoanToVO> voList = new ArrayList<>();
		for(LoanTo s:temppage.getResult()){
			LoanToVO vo = new LoanToVO(s);
			voList.add(vo);
		}
		Page<LoanToVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 借调信息备案
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(LoanTo temp){
		saveOrUpdate(temp);//保存基本信息
		//增加简历子集信息
		Experience e = new Experience();
		e.setServant(temp.getServant());//人员信息
		e.setFormerUnit(temp.getTargetOrgan());//所在单位
		e.setStartDate(temp.getStartDate());//开始时间
		experienceService.save(e);
		//备案管理
		HumankeepRecordDTO dto2 = new HumankeepRecordDTO(temp.getServant().getId(),HumanKeepRecord.KEEP_JD);
		ServantHumamKeepRecordEvent event2 = new ServantHumamKeepRecordEvent(dto2);	
		EventManager.send(event2);
	}
}
