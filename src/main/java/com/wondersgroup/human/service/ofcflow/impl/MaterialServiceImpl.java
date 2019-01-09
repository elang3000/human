/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: MaterialServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 材料 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年12月25日 下午5:12:21 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月25日 下午5:12:21 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.Material;
import com.wondersgroup.human.service.ofcflow.MaterialService;
import com.wondersgroup.human.vo.ofcflow.MaterialVO;

/** 
 * @ClassName: MaterialServiceImpl 
 * @Description: 材料 服务实现类
 * @author: tzy
 * @date: 2018年12月25日 下午5:12:21
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class MaterialServiceImpl extends GenericServiceImpl<Material> implements MaterialService{

	/**
	 * @see com.wondersgroup.human.service.ofcflow.MaterialService#getPage(com.wondersgroup.human.bo.ofcflow.Material, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public Page<MaterialVO> getPage(Material material, Integer page, Integer limit) {
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(Material.class);
		
		if(StringUtils.isNotBlank(material.getBusType())){//查询业务类型
			detachedcriteria.add(Restrictions.eq("busType", material.getBusType()));
		}
		
		if(StringUtils.isNotBlank(material.getName())){//查询材料名
			detachedcriteria.add(Restrictions.like("name", material.getName(),MatchMode.ANYWHERE));
		}
		
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.addOrder(Order.desc("createTime"));
		Page<Material> matePage = this.findByCriteria(detachedcriteria, page, limit);
		List<MaterialVO> voList = new ArrayList<>();
		for (Material m : matePage.getResult()) {
			MaterialVO vo = new MaterialVO(m);
			voList.add(vo);
		}
		Page<MaterialVO> result = new Page<>(matePage.getStart(), matePage.getCurrentPageSize(),
				matePage.getTotalSize(), matePage.getPageSize(), voList);
		return result;
	}
	
}
