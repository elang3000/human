/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: QuartzJobController.java 
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.controller 
 * 描述: Quartz定时器
 * 创建人: tzy   
 * 创建时间: 2018年10月30日 上午10:43:26 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月30日 上午10:43:26 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.system.warn.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.system.warn.bo.ProgramInfo;
import com.wondersgroup.system.warn.bo.Warning;
import com.wondersgroup.system.warn.service.ProgramInfoService;
import com.wondersgroup.system.warn.service.WarningService;

/** 
 * @ClassName: QuartzJobController 
 * @Description: Quartz定时器
 * @author: tzy
 * @date: 2018年10月30日 上午10:43:26
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class QuartzJobController implements Job{
	
	@Autowired
	private ProgramInfoService programInfoService;
	@Autowired
	private ServantService servantService;
	@Autowired
	private WarningService warningService;
	@Autowired
	private OrganNodeService organNodeService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		String id = context.getTrigger().getKey().getGroup();//预警预告id
		
		ProgramInfo info = programInfoService.get(id);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Warning.class);
		
		criteria.add(Restrictions.eq("removed", false));
		criteria.add(Restrictions.eq("programInfo.id", id));
		
		List<Warning> list=warningService.findByCriteria(criteria);
		
		if(list.size()>0){
			for(Warning w:list){
				warningService.delete(w);
			}
		}
		if(ProgramInfo.WARN.equals(info.getProgramCode())){
			
			List<Servant> servants = servantService.findBySQL(info.getResultSql(), null,Servant.class);//执行方案生成的sql
			
			for (Servant servant : servants) {
				DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = new Date();
				String time=dateFormat.format(date);
				
				Warning warning = new Warning();
				
				warning.setDataNum(servants.size()+"");
				warning.setBulidDate(time);
				warning.setServant(servant);//人员信息
				warning.setProgramInfo(info);//方案信息
				
				warningService.save(warning);
			}
		}else if(ProgramInfo.PREVIEW.equals(info.getProgramCode())){
			
			List<OrgInfo> org =organNodeService.findBySQL(info.getResultSql(), null,OrgInfo.class);
			
			for (OrgInfo map : org) {
				
				DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = new Date();
				String time=dateFormat.format(date);
				
				Warning warning = new Warning();
				warning.setProgramInfo(info);//方案信息
				warning.setOrgInfo(map);//单位信息
				warning.setBulidDate(time);
				warningService.save(warning);
			}
			
		}
	}
}
