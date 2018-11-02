/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ResignServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月20日 下午4:46:08 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月20日 下午4:46:08 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.ResignServant;
import com.wondersgroup.human.dto.ofcflow.ResignServantQueryParam;
import com.wondersgroup.human.vo.ofcflow.ResignVO;

/** 
 * @ClassName: ResignServantService 
 * @Description: 辞职服务接口
 * @author: lihao
 * @date: 2018年6月20日 下午4:46:08
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ResignServantService  extends GenericService<ResignServant>{

	/** 
	 * @Title: saveResign 
	 * @Description: 保存辞职信息，创建辞职流程
	 * @param temp
	 * @return: void
	 */
	void saveResign(ResignServant temp,String opinion,String r);

	/** 
	 * @Title: pageList 
	 * @Description: 辞职信息页面bo转vo
	 * @param resignVO
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ResignVO>
	 */
	Page<ResignVO> pageList(ResignServantQueryParam param, Integer page, Integer limit);

}
