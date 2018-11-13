/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: HumanKeepRecordVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.record.Impl
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月29日 下午5:17:20
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月29日 下午5:17:20
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.record.Impl;

import org.springframework.stereotype.Repository;
import com.wondersgroup.framework.core.dao.impl.GenericRepositoryImpl;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.repository.record.HumanKeepRecordDAO;

@Repository
public class HumanKeepRecordDAOImpl extends GenericRepositoryImpl<HumanKeepRecord> implements HumanKeepRecordDAO {

	@Override
	public Class<HumanKeepRecord> getEntityClass() {

		return HumanKeepRecord.class;
	}
}
