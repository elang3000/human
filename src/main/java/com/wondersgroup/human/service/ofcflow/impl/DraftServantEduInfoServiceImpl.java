/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantEduInfoServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:15:13 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:15:13 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.DraftServantEduInfo;
import com.wondersgroup.human.service.ofcflow.DraftServantEduInfoService;

/** 
 * @ClassName: DraftServantEduInfoServiceImpl 
 * @Description: TODO
 * @author: GP
 * @date: 2018年6月26日 上午10:15:13
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DraftServantEduInfoServiceImpl extends GenericServiceImpl<DraftServantEduInfo> implements DraftServantEduInfoService{

	/** (non Javadoc) 
	 * @Title: saveBatch
	 * @Description: TODO
	 * @param list 
	 * @see com.wondersgroup.human.service.ofcflow.DraftServantEduInfoService#saveBatch(java.util.List) 
	 */
	@Override
	public void saveBatch(List<DraftServantEduInfo> list) {
		
		for (DraftServantEduInfo draftServantEduInfo : list) {
			save(draftServantEduInfo);
		}
		
	}
	
}
