/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DeathServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月22日 上午9:52:47 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月22日 上午9:52:47 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.DeathServant;
import com.wondersgroup.human.dto.ofcflow.DeathServantQueryParam;
import com.wondersgroup.human.vo.ofcflow.DeathVO;

/**
 * @ClassName: DeathServantService
 * @Description: 死亡服务接口
 * @author: lihao
 * @date: 2018年6月22日上午9:52:47 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public interface DeathServantService extends GenericService<DeathServant> {

	/** 
	 * @Title: saveDeath 
	 * @Description: 死亡备案流程
	 * @param temp
	 * @param opinion
	 * @param r
	 * @return: void
	 */
	public void saveDeath(DeathServant temp, String opinion, String r);

	/** 
	 * @Title: pageList 
	 * @Description: 死亡列表bo转vo
	 * @param param
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<DeathVO>
	 */
	public Page<DeathVO> pageList(DeathServantQueryParam param, Integer page, Integer limit);

	/** 
	 * @Title: getByServantId 
	 * @Description: 校验是否在流程
	 * @param servantId
	 * @return
	 * @return: DeathServant
	 */
	DeathServant getByServantId(String servantId);

}
