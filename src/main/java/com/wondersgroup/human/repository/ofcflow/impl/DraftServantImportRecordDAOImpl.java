/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantImportRecordDAOImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow.impl 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:21:55 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:21:55 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantImportRecord;
import com.wondersgroup.human.repository.ofcflow.DraftServantImportRecordDAO;

/** 
 * @ClassName: DraftServantImportRecordDAOImpl 
 * @Description: TODO
 * @author: GP
 * @date: 2018年6月26日 上午10:21:55
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class DraftServantImportRecordDAOImpl extends GenericRepositoryImpl<DraftServantImportRecord> implements DraftServantImportRecordDAO{

	/** (non Javadoc) 
	 * @Title: getEntityClass
	 * @Description: TODO
	 * @return 
	 * @see com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl#getEntityClass() 
	 */
	@Override
	public Class<DraftServantImportRecord> getEntityClass() {
		return DraftServantImportRecord.class;
	}

	@Override
	public void updateCollectStatusByImportId(String id) {
		StringBuilder hql=new StringBuilder();
		hql.append("update DraftServant  set status= ");
		hql.append(DraftServant.STATUS_EMPLOY_COLLECT);
		hql.append(" where importRecordId=:importId and removed=:removed");
		Query q = currentSession().createQuery(hql.toString());
		q.setString("importId", id);
		q.setBoolean("removed", false);
		q.executeUpdate();
	}
	
}
