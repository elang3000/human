/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ZhuanRenKLBOutMgrServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 跨类别转任 转出情况信息 服务实现类
 * 创建人: tzy   
 * 创建时间: 2018年9月20日 下午3:00:34 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年9月20日 下午3:00:34 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBOutMgr;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.ZhuanRenKLBOutMgrService;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenKLBOutMgrVO;

/** 
 * @ClassName: ZhuanRenKLBOutMgrServiceImpl 
 * @Description: 跨类别转任 转出情况信息 服务实现类
 * @author: tzy
 * @date: 2018年9月20日 下午3:00:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class ZhuanRenKLBOutMgrServiceImpl extends GenericServiceImpl<ZhuanRenKLBOutMgr> implements ZhuanRenKLBOutMgrService{

	@Autowired
	private ServantService servantService;
	@Autowired
	private DictableService dictableService;
	@Autowired
	private OutMgrService outMgrService;
	/**
	 * @Title: findbyHQLforVO 
	 * @Description: 转换为VO的分页列表
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @return: Page<ZhuanRenKLBIntoMgrVO>
	 */
	public Page<ZhuanRenKLBOutMgrVO> findbyHQLforVO(String hql,List<QueryParameter> listqueryparametry,Integer pageNo,Integer pagesize){
		Page<ZhuanRenKLBOutMgr> temppage=this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ZhuanRenKLBOutMgrVO> voList = new ArrayList<>();
		for(ZhuanRenKLBOutMgr s:temppage.getResult()){
			ZhuanRenKLBOutMgrVO vo = new ZhuanRenKLBOutMgrVO(s);
			voList.add(vo);
		}
		Page<ZhuanRenKLBOutMgrVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	/**
	 * @Title: saveFlow 
	 * @Description: 审批转出流程
	 * @param temp
	 * @return: void
	 */
	public void saveFlow(ZhuanRenKLBOutMgr temp){
		if(temp.getStatus()==ZhuanRenKLBOutMgr.STATUS_ZHUANCHU_CONFIRM){
			//修改原数据状态为调出
			Servant oldServant = servantService.get(temp.getServant().getId());
			CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
			oldServant.setIsOnHold(outer);//调出状态
			servantService.update(oldServant);
			//转出子集信息
			OutMgr out = new OutMgr();
			out.setServant(oldServant);//人员基本信息
			out.setCategory(temp.getCategory());//调出本单位类别
			out.setReasonType(temp.getReasonType());//调动原因
			out.setOutDate(temp.getOutDate());//调出本单位日期
			out.setGotoUnitName(temp.getGotoUnitName());//调往单位名称
			out.setProposeType(temp.getProposeType());//提出调动类型
			out.setRemark(temp.getRemark());//调出备注
			outMgrService.save(out);
		}
		temp.setStatus(temp.getStatus()+1);//流程状态加1，到下一个节点
		update(temp);
	}
}
