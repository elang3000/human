/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TimeWarningTaskImpl.java 
 * 工程名: smart-core2
 * 包名: com.wondersgroup.framework.bpms.engine.logic.time.service.impl 
 * 描述: 预警预告定时任务管理器
 * 创建人: tzy   
 * 创建时间: 2018年11月5日 下午2:31:56 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年11月5日 下午2:31:56 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.system.warn.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.announcement.dto.AnnouncementEventData;
import com.wondersgroup.framework.announcement.event.SystemAnnouncementEvent;
import com.wondersgroup.framework.announcement.util.AnnouncementManger;
import com.wondersgroup.framework.console.bo.FrameWorkResource;
import com.wondersgroup.framework.console.service.FrameWorkService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.system.warn.bo.ProgramInfo;
import com.wondersgroup.system.warn.bo.Warning;
import com.wondersgroup.system.warn.service.WarningService;

/** 
 * @ClassName: TimeWarningTaskImpl 
 * @Description: 预警预告定时任务管理器
 * @author: tzy
 * @date: 2018年11月5日 下午2:31:56
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TimeWarningTaskImpl{
	
	@Autowired
	private WarningService warningService;
	
	@Autowired
	private WorkflowService workFlowService;
	
	@Autowired
	private OrganNodeService organNodeService;

	@Autowired
	FrameWorkService frameWorkService;
	
	public void run() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Warning.class);
		criteria.add(Restrictions.eq("removed", false));
		List<Warning> list = warningService.findByCriteria(criteria);
		DetachedCriteria c = DetachedCriteria.forClass(FrameWorkResource.class);
		c.add(Restrictions.eq("code", "PROGRAMINFO_NEWS"));
		List<FrameWorkResource>  f = frameWorkService.findByCriteria(c);//根据权限代码查询权限ID
		for(Warning w : list){
			ProgramInfo info = w.getProgramInfo();//方案信息
			
			if(info!=null){
				//发送通知
				OrganNode organNode = null;
				String title = "";
				String programType = "预告";
				if(ProgramInfo.WARN.equals(info.getProgramType())){//预警
					programType = "预警";
				}
				if(ProgramInfo.WARN.equals(info.getProgramCode())){//个人信息
					title = "关于"+w.getServant().getName()+info.getName()+programType+"的通知！";
					
					organNode = organNodeService.get(w.getServant().getDepartId());//获取人员所在单位信息
				}else if(ProgramInfo.PREVIEW.equals(info.getProgramCode())){//单位信息
					title = "关于"+w.getOrgInfo().getUnitBasicSimpleName()+info.getName()+programType+"的通知！";
					
					organNode = w.getOrgInfo().getOrgan();//获取cf单位信息
				}
				//查询单位下有权限人员
				if(f!=null&&f.size()>0){
					List<SecurityUser>  a = workFlowService.queryAuthUser(organNode.getId(), f.get(0).getId());//查询单位下有权限人员
					
					if(a!=null&&a.size()>0){
						//发送通知
						AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, a.get(0).getId(), title, info.getNewsContent(), "")));
						warningService.delete(w);//消息发送成功后删除数据
					}
				}
			}
		}
	}
}
