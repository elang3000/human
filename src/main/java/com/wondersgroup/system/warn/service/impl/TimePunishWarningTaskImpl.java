/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TimePunishWarningTaskImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.service.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月23日 上午9:38:59 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月23日 上午9:38:59 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.system.warn.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.PunishServant;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.PunishServantService;

/** 
 * @ClassName: TimePunishWarningTaskImpl 
 * @Description: 处分预警定时器
 * @author: lihao
 * @date: 2018年11月23日 上午9:38:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class TimePunishWarningTaskImpl {

	@Autowired
	private PunishServantService punishServantService;
	
	@Autowired
	private WorkflowService workFlowService;
	
	@Autowired
	private ServantService servantService;

	@Autowired
	FrameWorkService frameWorkService;
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			DetachedCriteria c = DetachedCriteria.forClass(FrameWorkResource.class);
			c.add(Restrictions.eq("code", "PUNISH_WARN"));
			List<FrameWorkResource>  f = frameWorkService.findByCriteria(c);//根据权限代码查询权限ID
			
			//处分预警通知
			Date d = sdf.parse(sdf.format(new Date()));
			DetachedCriteria dc = DetachedCriteria.forClass(PunishServant.class);
			dc.add(Restrictions.eq("warnDate", d));//处分结束时间
			dc.add(Restrictions.eq("removed", false));
			dc.add(Restrictions.eq("status", PunishServant.PUNISH_SERVANT_PASS));
			List<PunishServant> l = punishServantService.findByCriteria(dc);
			
			for(int i=0;i<l.size();i++){
				PunishServant p = l.get(i);
				Servant s = servantService.get(p.getServant().getId());
				if(f!=null&&f.size()>0){
					List<SecurityUser>  a = workFlowService.queryAuthUser(s.getDepartId(), f.get(0).getId());//查询单位下有权限人员
					String title = "关于（"+s.getName()+"）同志，处分编号："+p.getPunishFileName()+"处分预警通知";
					String content = "关于（"+s.getName()+"）同志，处分编号："+p.getPunishFileName()+"处分还有一个月到期";
					
					if(a!=null&&a.size()>0){
						//发送通知
						AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, a.get(0).getId(), title, content, "")));
					}
				}
			}
			
			//处分结束通知并解除处分
			Date date = sdf.parse(sdf.format(new Date()));
			DetachedCriteria criteria = DetachedCriteria.forClass(PunishServant.class);
			criteria.add(Restrictions.le("punishApprovalEndDate", date));//处分结束时间
			criteria.add(Restrictions.eq("removed", false));
			criteria.add(Restrictions.eq("status", PunishServant.PUNISH_SERVANT_PASS));
			criteria.add(Restrictions.eq("sign", PunishServant.PUNISH_SIGN));
			List<PunishServant> list = punishServantService.findByCriteria(criteria);
			
			for(int i=0;i<list.size();i++){
				PunishServant p = list.get(i);
				Servant s = servantService.get(p.getServant().getId());
				if(f!=null&&f.size()>0){
					List<SecurityUser>  a = workFlowService.queryAuthUser(s.getDepartId(), f.get(0).getId());//查询单位下有权限人员
					String title = "关于（"+s.getName()+"）同志，处分编号："+p.getPunishFileName()+"处分结束通知";
					String content = "关于（"+s.getName()+"）同志，处分编号："+p.getPunishFileName()+"处分到期，已自动解除";
					
					if(a!=null&&a.size()>0){
						//发送通知
						AnnouncementManger.send(new SystemAnnouncementEvent(new AnnouncementEventData(true, a.get(0).getId(), title, content, "")));
					}
					
					p.setSign(PunishServant.PUNISH_SIGN_PASS);
					punishServantService.update(p);
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
