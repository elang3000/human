/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PostService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc 
 * 描述: 人员信息子表-职务 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年5月30日 下午5:35:22 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月30日 下午5:35:22 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.pubinst;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.pubinst.PtPost;
import com.wondersgroup.human.vo.pubinst.PtPostVO;

/** 
 * @ClassName: PostService 
 * @Description: 人员信息子表-职务 服务接口
 * @author: tzy
 * @date: 2018年5月30日 下午5:35:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface PtPostService extends GenericService<PtPost>{
	/**
	 * 
	 * @Title: getPage 
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return
	 * @return: Page<PostVO>
	 */
	public Page<PtPostVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3);
	
	/**
	 * 
	 * @Title: executeRestAllTopPostFlag 
	 * @Description: 将改用户下所有的最高职位标识重置为0，否。
	 * @return: void
	 */
	void executeRestAllTopPostFlag(String servantId);
	
	/**
	 * 
	 * @Title: executeRestAllNowPostFlag 
	 * @Description: 将改用户下所有的现任职位标识重置为0，否。
	 * @return: void
	 */
	void executeRestAllNowPostFlag(String servantId);
	
	/**
	 * 
	 * @Title: delete
	 * @Description: 删除职务
	 * @param entity 
	 * @see com.wondersgroup.framework.core.service.GenericService#delete(com.wondersgroup.framework.core.bo.GenericEntity)
	 */
	void delete(PtPost post);
}
