/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignPlanService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月20日 上午11:04:41 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月20日 上午11:04:41 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ResignPlan;
import com.wondersgroup.human.vo.ofcflow.ResignPlanVO;

/** 
 * @ClassName: ResignPlanService 
 * @Description: 辞职批次服务接口
 * @author: lihao
 * @date: 2018年12月20日 上午11:04:41
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ResignPlanService extends GenericService<ResignPlan>{

	/** 
	 * @Title: savePeople 
	 * @Description: 添加辞职人员
	 * @param resignPlan
	 * @param servantIds
	 * @return: void
	 */
	public void savePeople(ResignPlan resignPlan, String servantIds);

	/** 
	 * @Title: resignList 
	 * @Description: 辞职批次vo列表
	 * @param resignPlan
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ResignPlanVO>
	 */
	public Page<ResignPlanVO> resignList(String resignName,Integer numLow,Integer numHigh, Integer page, Integer limit);

	/** 
	 * @Title: saveFlow 
	 * @Description: 辞职流程
	 * @param temp
	 * @param opinion
	 * @param r
	 * @return: void
	 */
	public void saveFlow(ResignPlan temp, String opinion, String r);

}
