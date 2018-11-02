/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantImportRecordService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:20:30 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:20:30 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.DraftServantImportRecord;
import com.wondersgroup.human.vo.ofcflow.DraftServantImportRecordVO;

/** 
 * @ClassName: DraftServantImportRecordService 
 * @Description: TODO
 * @author: GP
 * @date: 2018年6月26日 上午10:20:30
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DraftServantImportRecordService extends GenericService<DraftServantImportRecord>{
	
	public DraftServantImportRecord saveImportRecord(MultipartFile file,int year,int servantType) throws BusinessException;
	
	public Page<DraftServantImportRecordVO> getdraftServantImportRecordVOPage(String hql,List<QueryParameter> listqueryparameter,Integer pageNo,Integer pagesize);
	
	public void updaterecordandServantInfo(String importId,Integer recordYear);
	/**
	 * @Title: removeDraftServantImportRecord 
	 * @Description: 删除导入记录数据
	 * @param id
	 * @return: void
	 */
	public void removeDraftServantImportRecord(String id);
	/**
	 * @Title: savePublish 
	 * @Description: 发布
	 * @param id
	 * @param user
	 * @return: void
	 */
	public void savePublish(String id);

	/**
	 * 
	 * @Title: updateCollectStatusByImportId 
	 * @Description: 通过文件导入id,批量修改导入人员汇总状态
	 * @param id
	 * @return: void
	 */
	public void updateCollectStatusByImportId(String id);
}
