package com.wondersgroup.human.repository.instflow;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.instflow.RecordableRecord;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;


/** 
 * @ClassName: ServantDao 
 * @Description: 人员信息维护备案借口
 * @author: tzy
 * @date: 2018年5月21日 上午11:20:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RecordableRecordRepository extends GenericRepository<RecordableRecord>{
	
	public RecordableRecord findRecordableRecordByPid(String id);


	public RecordableRecord operationAlterFlag(PublicInstitution publicInstitution);

}
