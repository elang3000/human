/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainingHoursRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月13日 下午5:07:57 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月13日 下午5:07:57 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.pubinst;

import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.pubinst.PtTrainingHours;
import com.wondersgroup.human.vo.pubinst.PtTrainingHoursVO;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/** 
 * @ClassName: TrainingHoursRepository 
 * @Description: 培训 知识库接口
 * @author: lihao
 * @date: 2018年6月13日 下午5:07:57
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface PtTrainingHoursRepository  extends GenericRepository<PtTrainingHours>{

	/** 
	 * @Title: queryTrainingHours 
	 * @Description: 培训列表
	 * @param filter
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @return: Page<TrainingHoursVO>
	 */
	Page<PtTrainingHoursVO> queryTrainingHours(Map<String, Object> filter, Integer pageNumber, Integer pageSize);
	
	/** 
	 * @Title: queryTrainingHours 
	 * @Description: 根据培训id查询培训人员
	 * @param trainId
	 * @return
	 * @return: Page<TrainingHoursVO>
	 */
	Page<PtTrainingHoursVO> queryTrainingHours(String trainId);

	/** 
	 * @Title: queryTrainingHoursServantList 
	 * @Description: 培训人员选择列表
	 * @param params
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ServantVO>
	 */
	Page<PublicInstitutionVO> queryTrainingHoursServantList(Map<String, Object> filter, Integer page, Integer limit);
}
