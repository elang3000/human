/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PostDAO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc 
 * 描述: 人员信息子表-职务 知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年5月30日 下午5:31:07 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月30日 下午5:31:07 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.socialworker;

import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.socialworker.SrPost;

/** 
 * @ClassName: PostDAO 
 * @Description: 人员信息子表-职务 知识库接口
 * @author: tzy
 * @date: 2018年5月30日 下午5:31:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface SwPostRepository extends GenericRepository<SrPost>{

	/**
	 * 
	 * @Title: updateServantAllPostTopTipBySid 
	 * @Description: 根据公务员ID，修改该公务员职务子集下所有的 最高职务标识
	 * @param servantId
	 * @param codeInfo
	 * @return: void
	 */
	void updateAllPostTopTipBySid(String servantId, CodeInfo codeInfo);
	
	/**
	 * 
	 * @Title: updateServantAllPostNowTipBySid 
	 * @Description: 根据公务员ID，修改该公务员职务子集下所有的 现任职务标识
	 * @param servantId
	 * @param codeInfo
	 * @return: void
	 */
	void updateAllPostNowTipBySid(String servantId, CodeInfo codeInfo);
	
}
