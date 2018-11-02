/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantEduInfoService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:14:46 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:14:46 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;

import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.DraftServantEduInfo;

/** 
 * @ClassName: DraftServantEduInfoService 
 * @Description: TODO
 * @author: GP
 * @date: 2018年6月26日 上午10:14:46
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface DraftServantEduInfoService extends GenericService<DraftServantEduInfo>{
	
	public void saveBatch(List<DraftServantEduInfo> list);
}
