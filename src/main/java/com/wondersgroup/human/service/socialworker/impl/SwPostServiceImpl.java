/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: PostServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-职务 服务实现类
 * 创建人: tzy
 * 创建时间: 2018年5月30日 下午5:37:47
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月30日 下午5:37:47
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.socialworker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.bo.socialworker.SrPost;
import com.wondersgroup.human.repository.socialworker.SwPostRepository;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwPostService;
import com.wondersgroup.human.vo.socialworker.SwPostVO;

/**
 * @ClassName: PostServiceImpl
 * @Description: 人员信息子表-职务 服务实现类
 * @author: tzy
 * @date: 2018年5月30日 下午5:37:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class SwPostServiceImpl extends GenericServiceImpl<SrPost> implements SwPostService {
	
	@Autowired
	private SwPostRepository swpostRepository;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private SocialWorkerService socialWorkerService;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0 查询条件
	 * @param arg1 排序规则
	 * @param arg2 页码
	 * @param arg3 页大小
	 * @return
	 * @see com.wondersgroup.human.service.ofc.publicInstitutionService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<SwPostVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		
		Page<SrPost> postPage = swpostRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<SwPostVO> voList = new ArrayList<>();
		for (SrPost s : postPage.getResult()) {
			SwPostVO vo = new SwPostVO(s);
			voList.add(vo);
		}
		Page<SwPostVO> page = new Page<>(postPage.getStart(), postPage.getCurrentPageSize(), postPage.getTotalSize(),
				postPage.getPageSize(), voList);
		return page;
	}

	/**
	 * 重置更新最高职务标记
	 */
	@Override
	public void executeRestAllTopPostFlag(String Id) {
		
		CodeInfo codeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		swpostRepository.updateAllPostTopTipBySid(Id, codeInfo);
	}
	
	/**
	 * 重置更新现任职务的其它标记
	 */
	@Override
	public void executeRestAllNowPostFlag(String Id) {
		
		CodeInfo codeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		swpostRepository.updateAllPostNowTipBySid(Id, codeInfo);
	}
	
	@Override
	public void delete(SrPost entity) {
		
		// 如果删除的是最高职位的标识，更新公务员信息表中最高职位字段
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		if (entity.getHighestPostSign().getId().equals(yesCodeInfo.getId())) {
			SocialWorker pubinst = socialWorkerService.get(entity.getSocialWorker().getId());
			pubinst.setHighestPostEd(null);
			socialWorkerService.update(pubinst);
		}
		// 如果删除的是现任职位的标识，更新公务员信息表中现任职位字段getNowPostSign
		if (entity.getNowPostSign().getId().equals(yesCodeInfo.getId())) {
			SocialWorker pubinst = socialWorkerService.get(entity.getSocialWorker().getId());
			pubinst.setNowPostName(null);
			pubinst.setNowPostCode(null);
			pubinst.setNowPostAttribute(null);
			socialWorkerService.update(pubinst);
		}
		super.delete(entity);
	}
	@Override
	public void saveOrUpdate(SrPost entity) {

		// 若最高职务标识为1，则需要更新属性显示
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		SocialWorker pubinst = socialWorkerService.get(entity.getSocialWorker().getId());
		//现任职务标记为1，是
		if (entity.getNowPostSign().getId().equals(yesCodeInfo.getId())) {
			// 先重置其他所有职务现任标识设为0
			this.executeRestAllNowPostFlag(entity.getSocialWorker().getId());
			// 修改C01表中最高学职务的输出显示
			pubinst.setNowPostName(entity.getPostName());
			pubinst.setNowPostCode(entity.getPostCode());
			pubinst.setNowPostAttribute(entity.getAttribute());
			pubinst.setNowJobLevel(entity.getNowJobLevel());
			socialWorkerService.update(pubinst);
		}
		//最高职务标记为1，是
		 if(entity.getHighestPostSign().getId().equals(yesCodeInfo.getId())) {
			// 先重置其他所有职务最高标识设为0
			this.executeRestAllTopPostFlag(entity.getSocialWorker().getId());
			// 修改C01表中最高学职务的输出显示
			pubinst.setNowPostName(entity.getPostName());
			pubinst.setNowPostCode(entity.getPostCode());
			pubinst.setNowPostAttribute(entity.getAttribute());
			pubinst.setNowJobLevel(entity.getNowJobLevel());
			socialWorkerService.update(pubinst);
		}
//		 if(entity.getNowPostSign().getId().equals(noCodeInfo.getId())&&entity.getHighestPostSign().getId().equals(noCodeInfo.getId())){
//			publicInstitutionService.update(pubinst);
//		}else{
//			//从现任职位是1变为0
//			pubinst.setNowPostName("");
//			pubinst.setNowPostCode(null);
//			pubinst.setNowPostAttribute(null);
//		}
		
		super.saveOrUpdate(entity);
	}
	

}
