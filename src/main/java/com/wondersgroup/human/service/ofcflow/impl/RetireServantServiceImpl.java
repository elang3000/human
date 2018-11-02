/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RetireServantServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月25日 上午11:30:55 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月25日 上午11:30:55 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.RetireServant;
import com.wondersgroup.human.repository.ofcflow.RetireServantRepository;
import com.wondersgroup.human.service.ofcflow.RetireServantService;
import com.wondersgroup.human.vo.ofcflow.RetireServantVO;

/**
 * @ClassName: RetireServantServiceImpl
 * @Description: 退休服务层接口实现
 * @author: lihao
 * @date: 2018年6月25日上午11:30:55 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Service
public class RetireServantServiceImpl extends GenericServiceImpl<RetireServant> implements RetireServantService {

	@Autowired
	private RetireServantRepository retireServantRepository;
	
	/** 
	 * @see com.wondersgroup.human.service.ofcflow.RetireServantService#queryRetireServant(java.util.Map, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<RetireServantVO> queryRetireServant(Map<String, Object> filter, Integer page, Integer limit) {
		return retireServantRepository.queryServantKeepRecord(filter, page, limit);
	}

}
