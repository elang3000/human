/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TransferringExchangesServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 选调交流：单位招聘详情 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年8月13日 上午10:11:43 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月13日 上午10:11:43 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.TransferringExchanges;
import com.wondersgroup.human.bo.ofcflow.TransferringSummery;
import com.wondersgroup.human.bo.ofcflow.TransferringSummeryInfo;
import com.wondersgroup.human.repository.ofcflow.TransferringExchangesRepository;
import com.wondersgroup.human.service.ofcflow.TransferringExchangesService;
import com.wondersgroup.human.service.ofcflow.TransferringSummeryInfoService;
import com.wondersgroup.human.service.ofcflow.TransferringSummeryService;
import com.wondersgroup.human.vo.ofcflow.TransferringExchangesVO;

/** 
 * @ClassName: TransferringExchangesServiceImpl 
 * @Description: 选调交流：单位招聘详情 服务实现类
 * @author: tzy
 * @date: 2018年8月13日 上午10:11:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TransferringExchangesServiceImpl extends GenericServiceImpl<TransferringExchanges> implements TransferringExchangesService{
	@Autowired
	private TransferringExchangesRepository exchangesRepository;
	@Autowired
	private TransferringSummeryService summeryService;
	@Autowired
	private TransferringSummeryInfoService infoService;
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0 查询条件
	 * @param arg1 排序规则
	 * @param arg2 页码
	 * @param arg3 页大小
	 * @return
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<TransferringExchangesVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		Page<TransferringExchanges> postPage = exchangesRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<TransferringExchangesVO> voList = new ArrayList<>();
		for (TransferringExchanges s : postPage.getResult()) {
			TransferringExchangesVO vo = new TransferringExchangesVO(s);
			voList.add(vo);
		}
		Page<TransferringExchangesVO> page = new Page<>(postPage.getStart(), postPage.getCurrentPageSize(), postPage.getTotalSize(),
				postPage.getPageSize(), voList);
		return page;
	}
	/**
	 * (non Javadoc) 
	 * @Title: summary
	 * @Description: 提交汇总
	 * @param id
	 * @param status 
	 * @see com.wondersgroup.human.service.ofcflow.TransferringExchangesService#summary(java.lang.String, int)
	 */
	public void summary(String id,int status){
		String[] ids ;
		//1.修改选调计划表数据状态
		if(id.contains(",")){
			ids = id.split(",");
		}else{
			ids = new String[]{id};
		}
		Integer allowWeaveNum = 0;//核定编制数
		Integer realNum = 0;//实有人数
		Integer thisYearLackWeaveNum = 0;//本年度缺编人数
		Integer planCutNum = 0;//计划减员人数
		Integer planEmployNum = 0;//计划选调人数
		TransferringExchanges t = null;
		List<TransferringSummeryInfo> infoList = new ArrayList<>();
		for (int i=0;i<ids.length;i++) {
			TransferringExchanges ds = load(ids[i]);
			if(i==0){
				t = ds;
			}
			ds.setStatus(status);
			update(ds);//修改选调计划表状态
			//生成选调计划和汇总 关系表数据
			TransferringSummeryInfo info = new TransferringSummeryInfo();
			info.setPlan(ds);
			infoList.add(info);
			//各选调计划的人数相加
			allowWeaveNum += ds.getAllowWeaveNum();
			realNum += ds.getRealNum();
			thisYearLackWeaveNum += ds.getThisYearLackWeaveNum();
			planCutNum += ds.getPlanCutNum();
			planEmployNum += ds.getPlanEmployNum();
		}
		//2.根据选调计划的选调机关查询该选调机关是否有待提交数据，如果有，合并到查询出来的数据中，没有则新增
		List<Predicate> filter = new ArrayList<>();//查询条件
		filter.add(new Predicate("recruitOrgan.id", Operator.EQ,t.getRecruitOrgan().getId(), ""));//选调机关
		filter.add(new Predicate("status", Operator.EQ,TransferringSummery.RECRUIT_EMPLOY_PLAN_STATE_POST, ""));//待提交状态
		List<TransferringSummery> s = summeryService.findByFilter(filter);
		//3.保存选择的选调计划到 汇总表
		TransferringSummery summery;
		if(s.size()>0){
			summery = s.get(0);
			summery.setAllowWeaveNum(allowWeaveNum+summery.getAllowWeaveNum());
			summery.setRealNum(realNum+summery.getRealNum());
			summery.setThisYearLackWeaveNum(thisYearLackWeaveNum+summery.getThisYearLackWeaveNum());
			summery.setPlanCutNum(planCutNum+summery.getPlanCutNum());
			summery.setPlanEmployNum(planEmployNum+summery.getPlanEmployNum());
		}else{
			summery = new TransferringSummery();
			summery.setRecruitOrgan(t.getRecruitOrgan());
			summery.setAllowWeaveNum(allowWeaveNum);
			summery.setRealNum(realNum);
			summery.setThisYearLackWeaveNum(thisYearLackWeaveNum);
			summery.setPlanCutNum(planCutNum);
			summery.setPlanEmployNum(planEmployNum);
		}
		summery.setRecuritType(t.getRecuritType());
		summeryService.saveOrUpdate(summery);
		//4.保存选调计划和汇总的 关系表
		for(TransferringSummeryInfo info:infoList){
			info.setSummery(summery);
			infoService.save(info);
		}
		
	}
}
