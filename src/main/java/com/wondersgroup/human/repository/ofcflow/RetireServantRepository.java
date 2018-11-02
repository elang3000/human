/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RetireServantRepository.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月25日 上午11:32:43 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月25日 上午11:32:43 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.repository.ofcflow;

import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofcflow.RetireServant;
import com.wondersgroup.human.vo.ofcflow.RetireServantVO;

/** 
 * @ClassName: RetireServantRepository 
 * @Description: 退休知识库接口
 * @author: lihao
 * @date: 2018年6月25日 上午11:32:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface RetireServantRepository extends GenericRepository<RetireServant>{

	/** 
	 * @Title: queryServantKeepRecord 
	 * @Description: 退休列表知识库查询接口
	 * @param filter 条件
	 * @param page	 页码
	 * @param limit 页大小
	 * @return
	 * @return: Page<ServantVO>
	 */
	Page<RetireServantVO> queryServantKeepRecord(Map<String, Object> filter, Integer page, Integer limit);

}
