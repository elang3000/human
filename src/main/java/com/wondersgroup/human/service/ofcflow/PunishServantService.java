/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PunishServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年9月25日 上午11:29:49 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年9月25日 上午11:29:49 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.PunishServant;
import com.wondersgroup.human.dto.ofcflow.PunishServantQueryParam;
import com.wondersgroup.human.vo.ofc.PunishVO;
import com.wondersgroup.human.vo.ofcflow.PunishServantVO;

/** 
 * @ClassName: PunishServantService 
 * @Description: 处分服务接口
 * @author: lihao
 * @date: 2018年9月25日 上午11:29:49
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface PunishServantService extends GenericService<PunishServant> {
	
	/** 
	 * @Title: pageList 
	 * @Description: 处分列表bo转vo
	 * @param param
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<PunishServantVO>
	 */
	public Page<PunishServantVO> pageList(PunishServantQueryParam param, Integer page, Integer limit);

	/** 
	 * @Title: savePunish 
	 * @Description: TODO
	 * @param punishServant
	 * @param opinion
	 * @param r
	 * @return: void
	 */
	public void savePunish(PunishServant punishServant, String opinion, String r);

}
