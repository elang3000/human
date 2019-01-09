/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: OfcFlowNumberServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 流程信息打印编号 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年12月3日 上午9:47:04 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月3日 上午9:47:04 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.ofcflow.OfcFlowNumber;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;

/** 
 * @ClassName: OfcFlowNumberServiceImpl 
 * @Description: 流程信息打印编号 服务实现类
 * @author: tzy
 * @date: 2018年12月3日 上午9:47:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class OfcFlowNumberServiceImpl extends GenericServiceImpl<OfcFlowNumber> implements OfcFlowNumberService{
	/**
	 * @Title: executeNumber 
	 * @Description: 根据业务类型和业务ID获取流程编号 规则：如果该业务没有对应的编号，生成该业务的编号，最新编号加1，如果年度更换，年度增加，编号重置为001
	 * @param busType
	 * @param busId
	 * @return year:年度    number:编号
	 * @return: Map<String,String>
	 */
	public Map<String,String> executeNumber(String busType,String busId){
		if(StringUtils.isBlank(busType)||StringUtils.isBlank(busId)){
			throw new BusinessException("业务类型和业务ID不能为空！");
		}
		Map<String,String> map = new HashMap<>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OfcFlowNumber.class);
		detachedCriteria.add(Restrictions.eq("busType", busType));
		detachedCriteria.add(Restrictions.eq("busId", busId));
		List<OfcFlowNumber> bus = this.findByCriteria(detachedCriteria);//根据业务类型和ID查询，如果存在，直接返回，如果不存在，根据规则新增并返回
		if(bus!=null&&bus.size()>0){//存在，直接返回
			int flowNumber = bus.get(0).getFlowNumber();
			String number = String.valueOf(flowNumber);
			if(flowNumber<100){//不满100的在前面拼接0，显示最少三位数
				number = "000"+number;
				number = number.substring(number.length()-3);
			}
			map.put("year", String.valueOf(bus.get(0).getFlowYear()));
			map.put("number", number);
			return map;
		}else{//不存在，根据规则新增并返回
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			DetachedCriteria criteria = DetachedCriteria.forClass(OfcFlowNumber.class);
			criteria.add(Restrictions.eq("flowYear", year));
			criteria.addOrder(Order.desc("flowNumber"));
			List<OfcFlowNumber> yearList = this.findByCriteria(criteria);//根据当前年度查询最新的一条数据
			if(yearList!=null&&yearList.size()>0){//当前年度已经有数据，保存当前年度编号，将当前年度编号加1
				int flowNumber = yearList.get(0).getFlowNumber();
				int y = yearList.get(0).getFlowYear();
				//保存新的编号
				OfcFlowNumber o = new OfcFlowNumber();
				o.setBusId(busId);
				o.setBusType(busType);
				o.setFlowYear(y);
				o.setFlowNumber(flowNumber+1);
				this.save(o);
				
				String number = String.valueOf(o.getFlowNumber());
				if(o.getFlowNumber()<100){//不满100的在前面拼接0，显示最少三位数
					number = "000"+number;
					number = number.substring(number.length()-3);
				}
				map.put("year", String.valueOf(y));
				map.put("number", number);
				return map;
			}else{//新增当前年度数据
				//保存新的编号
				OfcFlowNumber o = new OfcFlowNumber();
				o.setBusId(busId);
				o.setBusType(busType);
				o.setFlowYear(year);
				o.setFlowNumber(1);//重置为1
				this.save(o);
				
				String number = String.valueOf(o.getFlowNumber());
				if(o.getFlowNumber()<100){//不满100的在前面拼接0，显示最少三位数
					number = "000"+number;
					number = number.substring(number.length()-3);
				}
				map.put("year", String.valueOf(o.getFlowYear()));
				map.put("number", number);
				return map;
			}
		}
	}
}
