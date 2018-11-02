/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RetireServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月25日 上午11:29:38 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月25日 上午11:29:38 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.Map;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.RetireServant;
import com.wondersgroup.human.vo.ofcflow.RetireServantVO;

/**
 * @ClassName: RetireServantService
 * @Description: 退休服务层接口
 * @author: lihao
 * @date: 2018年6月25日上午11:29:38 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
public interface RetireServantService extends GenericService<RetireServant> {

	/** 
	 * @Title: queryRetireServant 
	 * @Description: 退休列表bo转vo分页查询
	 * @param filter  查询条件
	 * @param page	 页码
	 * @param limit	页大小
	 * @return
	 * @return: Page<RetireServantVO>
	 */
	Page<RetireServantVO> queryRetireServant(Map<String, Object> filter, Integer page, Integer limit);

}
