/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月13日 下午2:58:23 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月13日 下午2:58:23 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.AbroadServant;
import com.wondersgroup.human.vo.ofcflow.AbroadServantVO;

/** 
 * @ClassName: AbroadServantService 
 * @Description: TODO
 * @author: lihao
 * @date: 2018年12月13日 下午2:58:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface AbroadServantService extends GenericService<AbroadServant>{

	/** 
	 * @Title: pageList 
	 * @Description: TODO
	 * @param abroadServant
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<AbroadServantVO>
	 */
	Page<AbroadServantVO> pageList(AbroadServant abroadServant, Integer page, Integer limit);

	/** 
	 * @Title: saveFlow 
	 * @Description: TODO
	 * @param abroadServant
	 * @param opinion
	 * @param r
	 * @return: void
	 */
	void saveFlow(AbroadServant abroadServant, String opinion, String r);

	/** 
	 * @Title: savePeople 
	 * @Description: TODO
	 * @param abroadServant
	 * @param servantIds
	 * @return: void
	 */
	void savePeople(AbroadServant abroadServant, String servantIds);

}
