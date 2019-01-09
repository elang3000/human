/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年11月13日 上午11:14:49 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年11月13日 上午11:14:49 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.TrainServant;
import com.wondersgroup.human.vo.ofcflow.TrainServantVO;

/** 
 * @ClassName: TrainService 
 * @Description: 培训事宜
 * @author: lihao
 * @date: 2018年11月13日 上午11:14:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface TrainServantService extends GenericService<TrainServant>{

	/** 
	 * @Title: pageList 
	 * @Description: 培训信息列表
	 * @param trainServant
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<TrainServantVO>
	 */
	Page<TrainServantVO> pageList(TrainServant trainServant, Integer page, Integer limit);

	/** 
	 * @Title: savePlan 
	 * @Description: 保存培训信息
	 * @param trainServant
	 * @param opinion
	 * @param r
	 * @return: void
	 */
	public void savePlan(TrainServant trainServant, String opinion, String r);

	/** 
	 * @Title: savePeople 
	 * @Description: 保存人员
	 * @param trainServant
	 * @param servantIds
	 * @return: void
	 */
	void savePeople(TrainServant trainServant, String servantIds);

}
