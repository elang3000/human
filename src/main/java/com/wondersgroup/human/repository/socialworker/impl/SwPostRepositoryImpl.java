/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PostDAOImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc.impl 
 * 描述: 人员信息子表-职务 知识库实现类
 * 创建人: tzy   
 * 创建时间: 2018年5月30日 下午5:32:12 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月30日 下午5:32:12 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.socialworker.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.socialworker.SrPost;
import com.wondersgroup.human.repository.socialworker.SwPostRepository;

/** 
 * @ClassName: PostDAOImpl 
 * @Description: 人员信息子表-职务 知识库实现类
 * @author: tzy
 * @date: 2018年5月30日 下午5:32:12
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class SwPostRepositoryImpl extends GenericRepositoryImpl<SrPost> implements SwPostRepository{
	public Class<SrPost> getEntityClass() {
		return SrPost.class;
	}

	/**
	 * 根据id更新基本信息表的最高职务标识
	 */
	@Override
	public void updateAllPostTopTipBySid(String Id, CodeInfo codeInfo) {
		
		Query query = this.currentSession().createQuery("update com.wondersgroup.human.bo.socialworker.SrPost set highestPostSign = :highestPostSign where socialWorker.id = :Id");
		query.setParameter("highestPostSign", codeInfo);
		query.setParameter("Id",Id);
		query.executeUpdate();
		
	}
	
	@Override
	public void updateAllPostNowTipBySid(String Id, CodeInfo codeInfo) {
		
		Query query = this.currentSession().createQuery("update com.wondersgroup.human.bo.socialworker.SrPost set tenureStatus = :nowPostSign where socialWorker.id = :Id");
		query.setParameter("nowPostSign", codeInfo);
		query.setParameter("Id",Id);
		query.executeUpdate();
		
	}
}
