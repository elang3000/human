/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantDAOImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.impl 
 * 描述: 人员信息维护知识库接口实现类
 * 创建人: tzy   
 * 创建时间: 2018年5月21日 上午11:22:54 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月21日 上午11:22:54 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.pubinst.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.instflow.AlternatingRotation;
import com.wondersgroup.human.bo.pubinst.PtAppointDismissDuty;
import com.wondersgroup.human.repository.pubinst.PtAppointDismissDutyRepository;

/** 
 * @ClassName: AlternatingRotationRepositoryImpl
 * @Description: 拟任拟免职务 dao 实现类
 * @author: lyf
 * @date: 2018年9月14日 上午11:22:54
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Repository
public class PtAppointDismissDutyRepositoryImpl extends GenericRepositoryImpl<PtAppointDismissDuty> implements PtAppointDismissDutyRepository{

	public Class<PtAppointDismissDuty> getEntityClass() {
		return PtAppointDismissDuty.class;
	}

}
