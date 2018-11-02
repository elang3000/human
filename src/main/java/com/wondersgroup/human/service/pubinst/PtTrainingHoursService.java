/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainingHoursService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月13日 下午5:04:25 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月13日 下午5:04:25 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.pubinst;

import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.pubinst.PtTrainingHours;
import com.wondersgroup.human.vo.pubinst.PtTrainingHoursVO;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/**
 * @ClassName: TrainingHoursService
 * @Description: 培训服务接口
 * @author: lihao
 * @date: 2018年6月13日下午5:04:25 @version [版本号, YYYY-MM-DD] @see       [相关类/方法] @since     [产品/模块版本]
 */
public interface PtTrainingHoursService extends GenericService<PtTrainingHours> {

	/**
	 * @Title: queryTrainingHours
	 * @Description: 查询培训学时列表数据转换为VO的分页查询
	 * @param filter查询条件
	 * @param page页码
	 * @param limit页大小
	 * @return
	 * @return: Page<TrainingHoursVO>
	 */
	Page<PtTrainingHoursVO> queryTrainingHours(Map<String, Object> filter, Integer pageNumber, Integer pageSize);

	/**
	 * @Title: getPage
	 * @Description: 查询已选择人员培训学时列表数据转换为VO
	 * @param trainId
	 * @return: List<TrainingHoursVO>
	 */
	Page<PtTrainingHoursVO> getPage(String trainId);

	/**
	 * @Title: getPage
	 * @Description: 根据培训ID查询不在该培训人员数据转换为VO的分页查询
	 * @param params查询条件
	 * @param page页码
	 * @param limit页大小
	 * @return: Page<ServantVO>
	 */
	Page<PublicInstitutionVO> getPage(Map<String, Object> filter, Integer page, Integer limit);

}
