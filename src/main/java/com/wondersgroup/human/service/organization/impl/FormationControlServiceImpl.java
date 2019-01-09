/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OrgFormationServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl
 * 描述: 编控 信息维护服务接口实现类
 * 创建人: jiang
 * 创建时间: 2018年10月25日14:39:17
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年10月25日14:39:20
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.organization.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.repository.organization.FormationControlRepository;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.organization.FormationControlVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/**
 * @ClassName: OrgFormationServiceImpl
 * @Description: 编控 信息维护服务接口实现类
 * @author: jiang
 * @date: 2018年10月25日14:39:08
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class FormationControlServiceImpl extends GenericServiceImpl<FormationControl>
		implements FormationControlService {
	
	@Autowired
	private FormationControlRepository formationControlRepository;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private OrgFormationService orgFormationService;
	
	@Autowired
	private InstitutionOrgFormationService institutionOrgFormationService;
	
	@Autowired
	private InstitutionOrgFormationService instOrgFormationService;
	
	@Autowired
	private FormationControlService formationControlService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	private DictableService dictableService;
	
	@Value("#{system['system.organ.formation.control.enabled']}")
	public Boolean enabledFormationControl = true;
	
	@Override
	public Page<FormationControlVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit) {
		
		Page<FormationControl> formationControlPage = formationControlRepository.findByCriteria(detachedCriteria,
				pageNo, limit);
		List<FormationControlVO> voList = new ArrayList<>();
		for (FormationControl s : formationControlPage.getResult()) {
			FormationControlVO vo = new FormationControlVO(s);
			voList.add(vo);
		}
		Page<FormationControlVO> page = new Page<>(formationControlPage.getStart(),
				formationControlPage.getCurrentPageSize(), formationControlPage.getTotalSize(),
				formationControlPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public boolean queryJudgeFormationNum(String organId) throws BusinessException {
		
		return queryJudgeFormationNum(organId, 1);
	}
	
	@Override
	public boolean queryJudgeFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		if (enabledFormationControl == false)
			return true;
		int realNum;
		OrgFormation orgFormation = this.getFormation(organId);
		FormationControl formationControl = this.getFormationControl(organId);
		// 根据编控信息，进行编控 实控=定编数-在编数-处级领导空缺数-供给关系尚未调入人员+允许溢出人数
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		
		// 定编数
		int unitPlanningTotal = orgFormation.getUnitPlanningTotal() == null ? 0 : orgFormation.getUnitPlanningTotal();
		// 在编数
		int actualNumber = orgFormation.getActualNumber() == null ? 0 : orgFormation.getActualNumber();
		// 处级领导空缺数
		int vacancyChuNum = orgFormation.getApproveChuNum() - orgFormation.getRealChuNum();
		// 供给关系尚未调入人员
		int notIntoNum = orgFormation.getNotIntoNum();
		
		// 判断该机构是否进行编控
		if (formationControl.getIsOpenControl().getId().equals(yesCodeInfo.getId())) {
			// 判断编控信息是否部门独有
			if (!formationControl.getOrgan().getCode().equals(CommonConst.ROOT_ORGAN_CODE)) {
				// 独有
				realNum = unitPlanningTotal - actualNumber - vacancyChuNum - notIntoNum
						+ formationControl.getOverflowNum();
			} else {
				// 非独有,必须根据长宁区的编控规则计算允许溢出人数
				int flowNum = 0;
				// 判断是否有%号存在
				if (formationControl.getOverflowRule().indexOf("%") != -1) {
					// 溢出比例
					String scaleStr = formationControl.getOverflowRule().split("%")[0];
					Double scale = Double.parseDouble(scaleStr) / 100;
					Double flowNumD = Math.ceil(orgFormation.getUnitPlanningTotal() * scale);
					flowNum = flowNumD.intValue();
				} else {
					flowNum = Integer.parseInt(formationControl.getOverflowRule());
				}
				
				realNum = unitPlanningTotal - actualNumber - vacancyChuNum - notIntoNum + flowNum;
			}
			if (realNum - humanNum >= 0) {
				return true;
			} else {
				throw new BusinessException("实际编控已超编，请联系管理员");
			}
		} else {
			// 如果不进行编控直接返回true
			return true;
		}
	}
	
	@Override
	public JudgePostResult queryJudgePostNum(String organId, String postLvlCode, Integer isLeader)
			throws BusinessException {
		
		return queryJudgePostNum(organId, postLvlCode, isLeader, 1);
	}
	
	@Override
	public JudgePostResult queryJudgePostNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException {
		
		if (enabledFormationControl == false)
			return new JudgePostResult(true, postLvlCode,isLeader);
		OrgFormation orgFormation = this.getFormation(organId);
		FormationControl formationControl = this.getFormationControl(organId);
		// 业务判断
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		
		// 首先判断是否编控
		if (formationControl.getIsOpenControl().getId().equals(yesCodeInfo.getId())) {
			int realNum = 0;
			switch (postLvlCode) {
				case FormationControl.DIRECTOR:
					// 处级进入
					realNum = orgFormation.getApproveChuNum() - orgFormation.getRealChuNum();
					if (realNum - humanNum >= 0) {
						return new JudgePostResult(true, postLvlCode,isLeader);
					} else {
						throw new BusinessException("处级人数已超编，请联系管理员");
					}
				case FormationControl.DEPUTY_DIRECTOR:
					// 处级进入
					realNum = orgFormation.getApproveChuNum() - orgFormation.getRealChuNum();
					if (realNum - humanNum >= 0) {
						return new JudgePostResult(true, postLvlCode,isLeader);
					} else {
						throw new BusinessException("处级人数已超编，请联系管理员");
					}
				case FormationControl.SECTION_CHIEF:
					// 乡科级正职进入，区分领导或非领导，区分是否高职低配实职虚用。
					if(isLeader==CommonConst.YES){
						//乡科级正职领导
						realNum = orgFormation.getApprovePlusKeLeaderNum()-orgFormation.getRealPlusKeLeaderNum()
								- orgFormation.getNotIntoPlusKeLeaderNum();
						if (realNum - humanNum >= 0) {
							return new JudgePostResult(true, FormationControl.SECTION_CHIEF,CommonConst.YES);
						} else {
							throw new BusinessException("乡科级正职领导编制数已超编，请联系管理员");
						}
					}else if(isLeader==CommonConst.NO){
						//乡科级正职非领导
						if(formationControl.getIsLowToHigh().getId().equals(yesCodeInfo.getId())){
							//适用于高职低配，实职虚用
							realNum = orgFormation.getApprovePlusKeNoLeaderNum()-orgFormation.getRealPlusKeNoLeaderNum()
									- orgFormation.getNotIntoPlusKeNoLeaderNum();
							if (realNum - humanNum >= 0) {
								return new JudgePostResult(true, FormationControl.SECTION_CHIEF,CommonConst.NO);
							} else {
								realNum = orgFormation.getApprovePlusKeLeaderNum()-orgFormation.getRealPlusKeLeaderNum()
										- orgFormation.getNotIntoPlusKeLeaderNum();
								if (realNum - humanNum >= 0) {
									return new JudgePostResult(true, FormationControl.SECTION_CHIEF,CommonConst.YES);
								} else {
									throw new BusinessException("乡科级正职领导与非领导编制数都已超编，请联系管理员");
								}
							}
						}else{
							//不使用高职低配，实职虚用
							realNum = orgFormation.getApprovePlusKeNoLeaderNum()-orgFormation.getRealPlusKeNoLeaderNum()
									- orgFormation.getNotIntoPlusKeNoLeaderNum();
							if (realNum - humanNum >= 0) {
								return new JudgePostResult(true, FormationControl.SECTION_CHIEF,CommonConst.NO);
							} else {
								throw new BusinessException("乡科级正职非领导编制数已超编，请联系管理员");
							}
						}
					}else{
						throw new BusinessException("是否是领导参数错误，请联系管理员");
					}
				case FormationControl.DEPUTY_SECTION_CHIEF:	
					// 乡科级副职进入，区分领导或非领导，区分是否高职低配实职虚用。
					if(isLeader==CommonConst.YES){
						//乡科级副职领导
						if(formationControl.getIsLowToHigh().getId().equals(yesCodeInfo.getId())){
							//适用于高职低配，实职虚用
							realNum = orgFormation.getApproveMinusKeLeaderNum()-orgFormation.getRealMinusKeLeaderNum()
									- orgFormation.getNotIntoMinusKeLeaderNum();
							if (realNum - humanNum >= 0) {
								return new JudgePostResult(true, FormationControl.DEPUTY_SECTION_CHIEF,CommonConst.YES);
							} else {
								realNum = orgFormation.getApprovePlusKeLeaderNum()-orgFormation.getRealPlusKeLeaderNum()
										- orgFormation.getNotIntoPlusKeLeaderNum();
								if (realNum - humanNum >= 0) {
									return new JudgePostResult(true, FormationControl.SECTION_CHIEF,CommonConst.YES);
								} else {
									throw new BusinessException("乡科级正职与副职领导编制数都已超编，请联系管理员");
								}
							}
						}else{
							//不使用高职低配，实职虚用
							realNum = orgFormation.getApproveMinusKeLeaderNum()-orgFormation.getRealMinusKeLeaderNum()
									- orgFormation.getNotIntoMinusKeLeaderNum();
							if (realNum - humanNum >= 0) {
								return new JudgePostResult(true, FormationControl.DEPUTY_SECTION_CHIEF,CommonConst.YES);
							} else {
								throw new BusinessException("乡科级副职领导编制数已超编，请联系管理员");
							}
						}
					}else if(isLeader==CommonConst.NO){
						//乡科级副职非领导
						if(formationControl.getIsLowToHigh().getId().equals(yesCodeInfo.getId())){
							//适用于高职低配，实职虚用
							//高职低配规则：副科非领导-》副科领导-》正科非领导-》正科领导
							realNum = orgFormation.getApproveMinusKeNoLeaderNum()-orgFormation.getRealMinusKeNoLeaderNum()
									- orgFormation.getNotIntoMinusKeNoLeaderNum();
							if (realNum - humanNum >= 0) {
								return new JudgePostResult(true, FormationControl.DEPUTY_SECTION_CHIEF,CommonConst.NO);
							} else {
								realNum = orgFormation.getApproveMinusKeLeaderNum()-orgFormation.getRealMinusKeLeaderNum()
										- orgFormation.getNotIntoMinusKeLeaderNum();
								if (realNum - humanNum >= 0) {
									return new JudgePostResult(true, FormationControl.DEPUTY_SECTION_CHIEF,CommonConst.YES);
								} else {
									realNum = orgFormation.getApprovePlusKeNoLeaderNum()-orgFormation.getRealPlusKeNoLeaderNum()
											- orgFormation.getNotIntoPlusKeNoLeaderNum();
									if (realNum - humanNum >= 0) {
										return new JudgePostResult(true, FormationControl.SECTION_CHIEF,CommonConst.NO);
									} else {
										realNum = orgFormation.getApprovePlusKeLeaderNum()-orgFormation.getRealPlusKeLeaderNum()
												- orgFormation.getNotIntoPlusKeLeaderNum();
										if (realNum - humanNum >= 0) {
											return new JudgePostResult(true, FormationControl.SECTION_CHIEF,CommonConst.YES);
										} else {
											throw new BusinessException("乡科级正副职领导与非领导编制数都已超编，请联系管理员");
										}
									}
								}
							}
						}else{
							//不使用高职低配，实职虚用
							realNum = orgFormation.getApproveMinusKeNoLeaderNum()-orgFormation.getRealMinusKeNoLeaderNum()
									- orgFormation.getNotIntoMinusKeNoLeaderNum();
							if (realNum - humanNum >= 0) {
								return new JudgePostResult(true, FormationControl.DEPUTY_SECTION_CHIEF,CommonConst.NO);
							} else {
								throw new BusinessException("乡科级副职非领导编制数已超编，请联系管理员");
							}
						}
					}else{
						throw new BusinessException("是否是领导参数错误，请联系管理员");
					}
				case FormationControl.CLERK:
					return new JudgePostResult(true, postLvlCode,isLeader);
				case FormationControl.C_CLERK:
					return new JudgePostResult(true, postLvlCode,isLeader);
				default:
					throw new BusinessException("职级信息异常，请联系管理员");
			}
		} else {
			return new JudgePostResult(true, postLvlCode,isLeader);
		}
	}
	
	private OrgFormation getFormation(String organId) {
		
		// 先判断是否存在单位信息和编制信息
		OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", organId);
		if (orgInfo == null)
			throw new BusinessException("未获取到该单位基础信息，请联系管理员");
		OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", orgInfo.getId());
		if (orgFormation == null)
			throw new BusinessException("未获取到该单位编制信息，请联系管理员");
		return orgFormation;
	}
	
	private InstitutionOrgFormation getInstFormation(String organId) {
		
		// 先判断是否存在事业单位信息和编制信息
		OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", organId);
		if (orgInfo == null)
			throw new BusinessException("未获取到该事业单位基础信息，请联系管理员");
		InstitutionOrgFormation orgFormation = instOrgFormationService.findUniqueBy("orgInfo.id", orgInfo.getId());
		if (orgFormation == null)
			throw new BusinessException("未获取到该事业单位编制信息，请联系管理员");
		return orgFormation;
	}
	
	private FormationControl getFormationControl(String organId) {
		
		// 判断是否存在编控信息，若编控信息不存在，直接查询长宁区基础编控信息
		FormationControl formationControl = formationControlService.findUniqueBy("organ.id", organId);
		if (formationControl == null) {
			// 如果该单位没有编控信息，按照长宁区编控信息进行控制。
			OrganNode changNingNode = organNodeService.findUniqueBy("code", CommonConst.ROOT_ORGAN_CODE);
			formationControl = formationControlService.findUniqueBy("organ.id", changNingNode.getId());
			if (formationControl == null)
				throw new BusinessException("未获取到该单位或长宁区基础编制信息，请联系管理员");
		}
		return formationControl;
	}
	
	@Override
	public void executeLockIntoFormationNum(String organId) throws BusinessException {
		
		executeLockIntoFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeLockIntoFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		OrgFormation orgFormation = this.getFormation(organId);
		orgFormation.setNotIntoNum(orgFormation.getNotIntoNum() + humanNum);
		orgFormationService.update(orgFormation);
	}
	
	@Override
	public void executeUnlockIntoFormationNum(String organId) throws BusinessException {
		
		executeUnlockIntoFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeUnlockIntoFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		OrgFormation orgFormation = this.getFormation(organId);
		orgFormation.setNotIntoNum(orgFormation.getNotIntoNum() - humanNum);
		orgFormationService.update(orgFormation);
	}
	
	@Override
	public void executeLockOutFormationNum(String organId) throws BusinessException {
		
		executeLockOutFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeLockOutFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		OrgFormation orgFormation = this.getFormation(organId);
		orgFormation.setNotOutNum(orgFormation.getNotOutNum() + humanNum);
		orgFormationService.update(orgFormation);
	}
	
	@Override
	public void executeUnlockOutFormationNum(String organId) throws BusinessException {
		
		executeUnlockOutFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeUnlockOutFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		OrgFormation orgFormation = this.getFormation(organId);
		orgFormation.setNotOutNum(orgFormation.getNotOutNum() - humanNum);
		orgFormationService.update(orgFormation);
		
	}
	
	@Override
	public void executeLockPostIntoNum(String organId, String PostCode, Integer isLeader) throws BusinessException {
		
		executeLockPostIntoNum(organId, PostCode, isLeader, 1);
		
	}
	
	@Override
	public void executeLockPostIntoNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException {
		
		OrgFormation orgFormation = this.getFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.DIRECTOR:
				return;
			case FormationControl.DEPUTY_DIRECTOR:
				return;	
			case FormationControl.SECTION_CHIEF:
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setNotIntoPlusKeLeaderNum(orgFormation.getNotIntoPlusKeLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setNotIntoPlusKeNoLeaderNum(orgFormation.getNotIntoPlusKeNoLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.DEPUTY_SECTION_CHIEF:	
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setNotIntoMinusKeLeaderNum(orgFormation.getNotIntoMinusKeLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setNotIntoMinusKeNoLeaderNum(orgFormation.getNotIntoMinusKeNoLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.CLERK:
				return;
			case FormationControl.C_CLERK:
				return;	
			default:
				throw new BusinessException("职级信息异常，请联系管理员");	
		}
	}
	
	@Override
	public void executeUnlockPostIntoNum(String organId, String PostCode, Integer isLeader)
			throws BusinessException {
		
		executeUnlockPostIntoNum(organId, PostCode, isLeader, 1);
		
	}
	
	@Override
	public void executeUnlockPostIntoNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException {
		
		OrgFormation orgFormation = this.getFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.DIRECTOR:
				return;
			case FormationControl.DEPUTY_DIRECTOR:
				return;	
			case FormationControl.SECTION_CHIEF:
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setNotIntoPlusKeLeaderNum(orgFormation.getNotIntoPlusKeLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setNotIntoPlusKeNoLeaderNum(orgFormation.getNotIntoPlusKeNoLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.DEPUTY_SECTION_CHIEF:	
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setNotIntoMinusKeLeaderNum(orgFormation.getNotIntoMinusKeLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setNotIntoMinusKeNoLeaderNum(orgFormation.getNotIntoMinusKeNoLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.CLERK:
				return;
			case FormationControl.C_CLERK:
				return;	
			default:
				throw new BusinessException("职级信息异常，请联系管理员");	
		}
	}
	
	@Override
	public void executeLockPostOutNum(String organId, String postLvlCode, Integer isLeader) throws BusinessException {
		
		executeLockPostOutNum(organId, postLvlCode, isLeader, 1);
		
	}
	
	@Override
	public void executeLockPostOutNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException {
		
		OrgFormation orgFormation = this.getFormation(organId);

		switch (postLvlCode) {
			case FormationControl.DIRECTOR:
				return;
			case FormationControl.DEPUTY_DIRECTOR:
				return;	
			case FormationControl.SECTION_CHIEF:
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setNotOutPlusKeLeaderNum(orgFormation.getNotOutPlusKeLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setNotOutPlusKeNoLeaderNum(orgFormation.getNotOutPlusKeNoLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.DEPUTY_SECTION_CHIEF:	
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setNotOutMinusKeLeaderNum(orgFormation.getNotOutMinusKeLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setNotOutMinusKeNoLeaderNum(orgFormation.getNotOutMinusKeNoLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.CLERK:
				return;
			case FormationControl.C_CLERK:
				return;	
			default:
				throw new BusinessException("职级信息异常，请联系管理员");	
		}
	}
	
	@Override
	public void executeUnlockPostOutNum(String organId, String PostCode, Integer isLeader) throws BusinessException {
		
		executeUnlockPostOutNum(organId, PostCode, isLeader, 1);
		
	}
	
	@Override
	public void executeUnlockPostOutNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException {
		
		OrgFormation orgFormation = this.getFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.DIRECTOR:
				return;
			case FormationControl.DEPUTY_DIRECTOR:
				return;	
			case FormationControl.SECTION_CHIEF:
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setNotOutPlusKeLeaderNum(orgFormation.getNotOutPlusKeLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setNotOutPlusKeNoLeaderNum(orgFormation.getNotOutPlusKeNoLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.DEPUTY_SECTION_CHIEF:	
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setNotOutMinusKeLeaderNum(orgFormation.getNotOutMinusKeLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setNotOutMinusKeNoLeaderNum(orgFormation.getNotOutMinusKeNoLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.CLERK:
				return;
			case FormationControl.C_CLERK:
				return;	
			default:
				throw new BusinessException("职级信息异常，请联系管理员");	
		}
	}
	
	@Override
	public void executeIntoFormation(String organId) {
		
		executeIntoFormation(organId, 1);
		
	}
	
	@Override
	public void executeIntoFormation(String organId, Integer humanNum) {
		
		OrgFormation orgFormation = this.getFormation(organId);
		// 操作 实有人数、缺（超）编人数
		orgFormation.setActualNumber(orgFormation.getActualNumber() + humanNum);
		orgFormation.setVacancyExcessNumber(orgFormation.getUnitPlanningTotal() - orgFormation.getActualNumber());
		orgFormationService.update(orgFormation);
		
	}
	
	@Override
	public void executeOutFormation(String organId) {
		
		executeOutFormation(organId, 1);
		
	}
	
	@Override
	public void executeOutFormation(String organId, Integer humanNum) {
		
		OrgFormation orgFormation = this.getFormation(organId);
		// 操作 实有人数、缺（超）编人数
		orgFormation.setActualNumber(orgFormation.getActualNumber() - humanNum);
		orgFormation.setVacancyExcessNumber(orgFormation.getUnitPlanningTotal() - orgFormation.getActualNumber());
		orgFormationService.update(orgFormation);
		
	}
	
	@Override
	public void executeIntoPost(String organId, String PostCode, Integer isLeader) {
		
		executeIntoPost(organId, PostCode, isLeader, 1);
		
	}
	
	@Override
	public void executeIntoPost(String organId, String postLvlCode, Integer isLeader, Integer humanNum) {
		
		OrgFormation orgFormation = this.getFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.DIRECTOR:
				orgFormation.setRealChuNum(orgFormation.getRealChuNum() + humanNum);
				orgFormationService.update(orgFormation);
				return;	
			case FormationControl.DEPUTY_DIRECTOR:
				orgFormation.setRealChuNum(orgFormation.getRealChuNum() + humanNum);
				orgFormationService.update(orgFormation);
				return;	
			case FormationControl.SECTION_CHIEF:
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setRealPlusKeLeaderNum(orgFormation.getRealPlusKeLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setRealPlusKeNoLeaderNum(orgFormation.getRealPlusKeNoLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.DEPUTY_SECTION_CHIEF:	
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setRealMinusKeLeaderNum(orgFormation.getRealMinusKeLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setRealMinusKeNoLeaderNum(orgFormation.getRealMinusKeNoLeaderNum() + humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.CLERK:
				return;
			case FormationControl.C_CLERK:
				return;	
			default:
				throw new BusinessException("职级信息异常，请联系管理员");	
		}
	}
	
	@Override
	public void executeOutPost(String organId, String PostCode, Integer isLeader) {
		
		executeOutPost(organId, PostCode, isLeader, 1);
		
	}
	
	@Override
	public void executeOutPost(String organId, String postLvlCode, Integer isLeader, Integer humanNum) {
		
		OrgFormation orgFormation = this.getFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.DIRECTOR:
				orgFormation.setRealChuNum(orgFormation.getRealChuNum() - humanNum);
				orgFormationService.update(orgFormation);
				return;
			case FormationControl.DEPUTY_DIRECTOR:
				orgFormation.setRealChuNum(orgFormation.getRealChuNum() - humanNum);
				orgFormationService.update(orgFormation);
				return;	
			case FormationControl.SECTION_CHIEF:
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setRealPlusKeLeaderNum(orgFormation.getRealPlusKeLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setRealPlusKeNoLeaderNum(orgFormation.getRealPlusKeNoLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.DEPUTY_SECTION_CHIEF:	
				//判断领导与非领导
				if(isLeader == CommonConst.YES){
					orgFormation.setRealMinusKeLeaderNum(orgFormation.getRealMinusKeLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else if(isLeader == CommonConst.NO){
					orgFormation.setRealMinusKeNoLeaderNum(orgFormation.getRealMinusKeNoLeaderNum() - humanNum);
					orgFormationService.update(orgFormation);
					return;
				}else {
					throw new BusinessException("是否是领导参数错误，请联系管理员");
				}
			case FormationControl.CLERK:
				return;
			case FormationControl.C_CLERK:
				return;	
			default:
				throw new BusinessException("职级信息异常，请联系管理员");	
		}
	}
	
	@Override
	public void queryValidateFormationAndPostLvlNum(String organId,List<JudgePostResult> sourceList) throws BusinessException {
	
		//正科级领导
		int plusKeLeaderNum = 0;
		//正科级非领导
		int plusKeNoLeaderNum = 0;
		//副科级领导
		int minusKeLeaderNum = 0;
		//副科级非领导
		int minusKeNoLeaderNum = 0;
		//科员级
		int clerkNum = 0;
		//办事员级
		int c_cLerkNum = 0;
		
		// 分解人数
		for (JudgePostResult target : sourceList) {
			if(FormationControl.SECTION_CHIEF.equals(target.postLvlCode) && target.isLeader == CommonConst.YES){
				plusKeLeaderNum = target.human; continue;
			}else if(FormationControl.SECTION_CHIEF.equals(target.postLvlCode) && target.isLeader == CommonConst.NO){
				plusKeNoLeaderNum = target.human;continue;
			}else if(FormationControl.DEPUTY_SECTION_CHIEF.equals(target.postLvlCode) && target.isLeader == CommonConst.YES){
				minusKeLeaderNum = target.human;continue;
			}else if(FormationControl.DEPUTY_SECTION_CHIEF.equals(target.postLvlCode) && target.isLeader == CommonConst.NO){
				minusKeNoLeaderNum = target.human;continue;
			}else if(FormationControl.CLERK.equals(target.postLvlCode)){
				clerkNum = target.human;continue;
			}else if(FormationControl.C_CLERK.equals(target.postLvlCode)){
				c_cLerkNum = target.human;continue;
			}else {
				throw new BusinessException("职级参数异常，请联系管理员");	
			}
		}
		
		//首先判断编制数是否通过
		this.queryJudgeFormationNum(organId , plusKeLeaderNum + plusKeNoLeaderNum + minusKeLeaderNum + minusKeNoLeaderNum + clerkNum + c_cLerkNum);
		//判断职数，准备职数缺编数量
		OrgFormation orgFormation = this.getFormation(organId);
		
		//正科级领导缺编数
		int vacancyPlusKeLeaderNum = orgFormation.getApprovePlusKeLeaderNum() - orgFormation.getRealPlusKeLeaderNum();
		vacancyPlusKeLeaderNum = vacancyPlusKeLeaderNum - orgFormation.getNotIntoPlusKeLeaderNum();
		//正科级非领导缺编数
		int vacancyPlusKeNoLeaderNum = orgFormation.getApprovePlusKeNoLeaderNum() - orgFormation.getRealPlusKeNoLeaderNum();
		vacancyPlusKeNoLeaderNum = vacancyPlusKeNoLeaderNum - orgFormation.getNotIntoPlusKeNoLeaderNum();
		//副科级领导缺编数
		int vacancyMinusKeLeaderNum = orgFormation.getApproveMinusKeLeaderNum() - orgFormation.getRealMinusKeLeaderNum();
		vacancyMinusKeLeaderNum = vacancyMinusKeLeaderNum - orgFormation.getNotIntoMinusKeLeaderNum();
		//副科级非领导缺编数
		int vacancyMinusKeNoLeaderNum = orgFormation.getApproveMinusKeNoLeaderNum() - orgFormation.getRealMinusKeNoLeaderNum();
		vacancyMinusKeNoLeaderNum = vacancyMinusKeNoLeaderNum - orgFormation.getNotIntoMinusKeNoLeaderNum();
		
		//判断顺序 正科级领导-》正科级非领导-》副科级领导-》副科级非领导
		
		FormationControl formationControl = this.getFormationControl(organId);
		// 业务判断
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		
		// 首先判断是否编控
		if (formationControl.getIsOpenControl().getId().equals(yesCodeInfo.getId())) {
			
			if(formationControl.getIsLowToHigh().getId().equals(yesCodeInfo.getId())){
				
				if(plusKeLeaderNum > vacancyPlusKeLeaderNum){
					throw new BusinessException("乡科级正职领导编制数已超编，请联系管理员");	
				}
				if(plusKeNoLeaderNum > vacancyPlusKeNoLeaderNum ){
					if(plusKeNoLeaderNum > (vacancyPlusKeLeaderNum + vacancyPlusKeNoLeaderNum - plusKeLeaderNum)){
						throw new BusinessException("乡科级正职非领导编制数已超编，请联系管理员");
					}
				}
				if(minusKeLeaderNum > vacancyMinusKeLeaderNum){
					if(plusKeNoLeaderNum <= vacancyPlusKeNoLeaderNum){
						if(minusKeLeaderNum > (vacancyPlusKeLeaderNum + vacancyMinusKeLeaderNum - plusKeLeaderNum)){
							throw new BusinessException("乡科级副职领导编制数已超编，请联系管理员");
						}
					}else{
						if(minusKeLeaderNum > (vacancyPlusKeLeaderNum + vacancyMinusKeLeaderNum - plusKeLeaderNum -(plusKeNoLeaderNum - vacancyPlusKeNoLeaderNum))){
							throw new BusinessException("乡科级副职领导编制数已超编，请联系管理员");	
						}
					}
				}
				if(minusKeNoLeaderNum > vacancyMinusKeNoLeaderNum){
					if((plusKeLeaderNum + plusKeNoLeaderNum + minusKeLeaderNum + minusKeNoLeaderNum) > (vacancyPlusKeLeaderNum + vacancyPlusKeNoLeaderNum + vacancyMinusKeLeaderNum + vacancyMinusKeNoLeaderNum)){
						throw new BusinessException("乡科级副职非领导编制数已超编，请联系管理员");	
					}
				}
				
			}else{
				if(plusKeLeaderNum > vacancyPlusKeLeaderNum){
					throw new BusinessException("乡科级正职领导编制数已超编，请联系管理员");	
				}
				if(plusKeNoLeaderNum > vacancyPlusKeNoLeaderNum){
					throw new BusinessException("乡科级正职非领导编制数已超编，请联系管理员");	
				}
				if(minusKeLeaderNum > vacancyMinusKeLeaderNum){
					throw new BusinessException("乡科级副职领导编制数已超编，请联系管理员");	
				}
				if(minusKeNoLeaderNum > vacancyMinusKeNoLeaderNum){
					throw new BusinessException("乡科级副职非领导编制数已超编，请联系管理员");	
				}
			}
		}
	}
	
	@Override
	public List<JudgePostResult> executeValidateAndLockFormation(String organId, List<JudgePostResult> sourceList)
			throws BusinessException {
		List<JudgePostResult> targetList = new ArrayList<>();
		
		for (JudgePostResult source : sourceList) {
			//先验证编制数
			this.queryJudgeFormationNum(organId);
			//锁住编制数
			this.executeLockIntoFormationNum(organId);
			//验证职数
			JudgePostResult result = this.queryJudgePostNum(organId, source.postLvlCode, source.isLeader);
			//锁住职数
			this.executeLockPostIntoNum(organId, result.postLvlCode, result.isLeader);
			targetList.add(result);
		}
		
		return targetList;
	}
	
	
	@Override
	public void executeLockOutFormationAndPost(String organId, List<JudgePostResult> sourceList)
			throws BusinessException {
		
		for (JudgePostResult source : sourceList) {
			//占编 未调出编制数
			this.executeLockOutFormationNum(organId);
			//占编 未调出职数
			this.executeLockPostOutNum(organId, source.postLvlCode, source.isLeader);
		}
		
	}
	
	@Override
	public void executeUnLockIntoFormationAndPost(String organId, List<JudgePostResult> sourceList)
			throws BusinessException {
		
		for (JudgePostResult source : sourceList) {
			//返回未调入编制数
			this.executeUnlockIntoFormationNum(organId);
			//返回未调入职数
			this.executeUnlockPostIntoNum(organId, source.postLvlCode, source.isLeader);
		}
		
	}
	@Override
	public void executeUnLockOutFormationAndPost(String organId, List<JudgePostResult> sourceList)
			throws BusinessException {
		
		for (JudgePostResult source : sourceList) {
			//返回未调出编制数
			this.executeUnlockOutFormationNum(organId);
			//返回未调出职数
			this.executeUnlockPostOutNum(organId, source.postLvlCode, source.isLeader);
		}
		
	}
	// ------------------事业单位编制业务---------------------
	
	@Override
	public boolean queryJudgeInstFormationNum(String organId) throws BusinessException {
		
		return queryJudgeInstFormationNum(organId, 1);
	}
	
	@Override
	public boolean queryJudgeInstFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		if (enabledFormationControl == false)
			return true;
		int realNum;
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		
		// 判断是否存在编控信息，若编控信息不存在，直接查询长宁区基础编控信息
		FormationControl formationControl = this.getFormationControl(organId);
		// 根据编控信息，进行编控 实控=定编数-在编数-供给关系尚未调入人员+允许溢出人数
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		
		// 定编数
		int unitPlanningTotal = orgFormation.getUnitPlanningTotal() == null ? 0 : orgFormation.getUnitPlanningTotal();
		// 在编数
		int actualNumber = orgFormation.getActualNumber() == null ? 0 : orgFormation.getActualNumber();
		// 供给关系尚未调入人员
		int notIntoNum = orgFormation.getNotIntoNum() == null ? 0 : orgFormation.getNotIntoNum();
		
		// 判断该机构是否进行编控
		if (formationControl.getIsOpenControl().getId().equals(yesCodeInfo.getId())) {
			// 判断该编控信息是否部门单独所有
			if (!formationControl.getOrgan().getCode().equals(CommonConst.ROOT_ORGAN_CODE)) {
				// 独有
				realNum = unitPlanningTotal - actualNumber - notIntoNum + formationControl.getOverflowNum();
			} else {
				// 非独有,必须根据长宁区的编控规则计算允许溢出人数
				int flowNum = 0;
				// 判断是否有%号存在
				if (formationControl.getOverflowRule().indexOf("%") != -1) {
					// 溢出比例
					String scaleStr = formationControl.getOverflowRule().split("%")[0];
					Double scale = Double.parseDouble(scaleStr) / 100;
					Double flowNumD = Math.ceil(orgFormation.getUnitPlanningTotal() * scale);
					flowNum = flowNumD.intValue();
				} else {
					flowNum = Integer.parseInt(formationControl.getOverflowRule());
				}
				
				realNum = unitPlanningTotal - actualNumber - notIntoNum + flowNum;
			}
			if (realNum - humanNum >= 0) {
				return true;
			} else {
				throw new BusinessException("实际编控已超编，请联系管理员");
			}
		} else {
			// 如果不进行编控直接返回true
			return true;
		}
	}
	
	@Override
	public boolean queryJudgeInstPostNum(String organId, String postLvlCode) throws BusinessException {
		
		return queryJudgeInstPostNum(organId, postLvlCode, 1);
	}
	
	@Override
	public boolean queryJudgeInstPostNum(String organId, String postLvlCode, Integer humanNum)
			throws BusinessException {
		
		if (enabledFormationControl == false)
			return true;
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		
		// 判断是否存在编控信息，若编控信息不存在，直接查询长宁区基础编控信息
		FormationControl formationControl = this.getFormationControl(organId);
		// 业务判断
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		
		// 首先判断是否编控
		if (formationControl.getIsOpenControl().getId().equals(yesCodeInfo.getId())) {
			int realNum = 0;
			switch (postLvlCode) {
				case FormationControl.SYDW_MGR_I:
					// 实控=定编数-在编数-未调入人员
					realNum = orgFormation.getApproveMgrLevelINum() - orgFormation.getRealMgrLevelINum()
							- orgFormation.getNotIntoMgrLevelINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理一级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_II:
					realNum = orgFormation.getApproveMgrLevelIINum() - orgFormation.getRealMgrLevelIINum()
							- orgFormation.getNotIntoMgrLevelIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理二级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_III:
					realNum = orgFormation.getApproveMgrLevelIIINum() - orgFormation.getRealMgrLevelIIINum()
							- orgFormation.getNotIntoMgrLevelIIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理三级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_IV:
					realNum = orgFormation.getApproveMgrLevelIVNum() - orgFormation.getRealMgrLevelIVNum()
							- orgFormation.getNotIntoMgrLevelIVNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理四级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_V:
					realNum = orgFormation.getApproveMgrLevelVNum() - orgFormation.getRealMgrLevelVNum()
							- orgFormation.getNotIntoMgrLevelVNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理五级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_VI:
					realNum = orgFormation.getApproveMgrLevelVINum() - orgFormation.getRealMgrLevelVINum()
							- orgFormation.getNotIntoMgrLevelVINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理六级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_VII:
					realNum = orgFormation.getApproveMgrLevelVIINum() - orgFormation.getRealMgrLevelVIINum()
							- orgFormation.getNotIntoMgrLevelVIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理七级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_VIII:
					realNum = orgFormation.getApproveMgrLevelVIIINum() - orgFormation.getRealMgrLevelVIIINum()
							- orgFormation.getNotIntoMgrLevelVIIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理八级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_IX:
					realNum = orgFormation.getApproveMgrLevelIXNum() - orgFormation.getRealMgrLevelIXNum()
							- orgFormation.getNotIntoMgrLevelIXNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理九级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_MGR_X:
					realNum = orgFormation.getApproveMgrLevelXNum() - orgFormation.getRealMgrLevelXNum()
							- orgFormation.getNotIntoMgrLevelXNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位管理十级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_I:
					realNum = orgFormation.getApproveTechLevelINum() - orgFormation.getRealTechLevelINum()
							- orgFormation.getNotIntoTechLevelINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技一级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_II:
					realNum = orgFormation.getApproveTechLevelIINum() - orgFormation.getRealTechLevelIINum()
							- orgFormation.getNotIntoTechLevelIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技二级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_III:
					realNum = orgFormation.getApproveTechLevelIIINum() - orgFormation.getRealTechLevelIIINum()
							- orgFormation.getNotIntoTechLevelIIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技三级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_IV:
					realNum = orgFormation.getApproveTechLevelIVNum() - orgFormation.getRealTechLevelIVNum()
							- orgFormation.getNotIntoTechLevelIVNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技四级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_V:
					realNum = orgFormation.getApproveTechLevelVNum() - orgFormation.getRealTechLevelVNum()
							- orgFormation.getNotIntoTechLevelVNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技五级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_VI:
					realNum = orgFormation.getApproveTechLevelVINum() - orgFormation.getRealTechLevelVINum()
							- orgFormation.getNotIntoTechLevelVINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技六级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_VII:
					realNum = orgFormation.getApproveTechLevelVIINum() - orgFormation.getRealTechLevelVIINum()
							- orgFormation.getNotIntoTechLevelVIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技七级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_VIII:
					realNum = orgFormation.getApproveTechLevelVIIINum() - orgFormation.getRealTechLevelVIIINum()
							- orgFormation.getNotIntoTechLevelVIIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技八级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_IX:
					realNum = orgFormation.getApproveTechLevelIXNum() - orgFormation.getRealTechLevelIXNum()
							- orgFormation.getNotIntoTechLevelIXNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技九级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_X:
					realNum = orgFormation.getApproveTechLevelXNum() - orgFormation.getRealTechLevelXNum()
							- orgFormation.getNotIntoTechLevelXNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技十级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_XI:
					realNum = orgFormation.getApproveTechLevelXINum() - orgFormation.getRealTechLevelXINum()
							- orgFormation.getNotIntoTechLevelXINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技十一级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_XII:
					realNum = orgFormation.getApproveTechLevelXIINum() - orgFormation.getRealTechLevelXIINum()
							- orgFormation.getNotIntoTechLevelXIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技十二级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_TECH_XIII:
					realNum = orgFormation.getApproveTechLevelXIIINum() - orgFormation.getRealTechLevelXIIINum()
							- orgFormation.getNotIntoTechLevelXIIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位专技十三级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_WORK_I:
					realNum = orgFormation.getApproveWorkLevelINum() - orgFormation.getRealWorkLevelINum()
							- orgFormation.getNotIntoWorkLevelINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位工勤技术工一级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_WORK_II:
					realNum = orgFormation.getApproveWorkLevelIINum() - orgFormation.getRealWorkLevelIINum()
							- orgFormation.getNotIntoWorkLevelIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位工勤技术工二级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_WORK_III:
					realNum = orgFormation.getApproveWorkLevelIIINum() - orgFormation.getRealWorkLevelIIINum()
							- orgFormation.getNotIntoWorkLevelIIINum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位工勤技术工三级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_WORK_IV:
					realNum = orgFormation.getApproveWorkLevelIVNum() - orgFormation.getRealWorkLevelIVNum()
							- orgFormation.getNotIntoWorkLevelIVNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位工勤技术工四级编制数已超编，请联系管理员");
					}
				case FormationControl.SYDW_WORK_V:
					realNum = orgFormation.getApproveWorkLevelVNum() - orgFormation.getRealWorkLevelVNum()
							- orgFormation.getNotIntoWorkLevelVNum();
					if (realNum - humanNum >= 0) {
						return true;
					} else {
						throw new BusinessException("事业单位工勤技术工五级编制数已超编，请联系管理员");
					}
				default:
					throw new BusinessException("职级信息异常，请联系管理员");
					
			}
		} else {
			return true;
		}
	}
	
	@Override
	public void executeLockIntoInstFormationNum(String organId) throws BusinessException {
		
		executeLockIntoInstFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeLockIntoInstFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		orgFormation.setNotIntoNum(orgFormation.getNotIntoNum() + humanNum);
		institutionOrgFormationService.update(orgFormation);
		
	}
	
	@Override
	public void executeUnlockIntoInstFormationNum(String organId) throws BusinessException {
		
		executeUnlockIntoInstFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeUnlockIntoInstFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		orgFormation.setNotIntoNum(orgFormation.getNotIntoNum() - humanNum);
		institutionOrgFormationService.update(orgFormation);
		
	}
	
	@Override
	public void executeLockOutInstFormationNum(String organId) throws BusinessException {
		
		executeLockOutInstFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeLockOutInstFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		orgFormation.setNotOutNum(orgFormation.getNotOutNum() + humanNum);
		institutionOrgFormationService.update(orgFormation);
		
	}
	
	@Override
	public void executeUnlockOutInstFormationNum(String organId) throws BusinessException {
		
		executeUnlockOutInstFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeUnlockOutInstFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		orgFormation.setNotOutNum(orgFormation.getNotOutNum() - humanNum);
		institutionOrgFormationService.update(orgFormation);
	}
	
	@Override
	public void executeLockInstPostIntoNum(String organId, String postLvlCode) throws BusinessException {
		
		executeLockInstPostIntoNum(organId, postLvlCode, 1);
		
	}
	
	@Override
	public void executeLockInstPostIntoNum(String organId, String postLvlCode, Integer humanNum)
			throws BusinessException {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.SYDW_MGR_I:
				orgFormation.setNotIntoMgrLevelINum(orgFormation.getNotIntoMgrLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_II:
				orgFormation.setNotIntoMgrLevelIINum(orgFormation.getNotIntoMgrLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_III:
				orgFormation.setNotIntoMgrLevelIIINum(orgFormation.getNotIntoMgrLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IV:
				orgFormation.setNotIntoMgrLevelIVNum(orgFormation.getNotIntoMgrLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_V:
				orgFormation.setNotIntoMgrLevelVNum(orgFormation.getNotIntoMgrLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VI:
				orgFormation.setNotIntoMgrLevelVINum(orgFormation.getNotIntoMgrLevelVINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VII:
				orgFormation.setNotIntoMgrLevelVIINum(orgFormation.getNotIntoMgrLevelVIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VIII:
				orgFormation.setNotIntoMgrLevelVIIINum(orgFormation.getNotIntoMgrLevelVIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IX:
				orgFormation.setNotIntoMgrLevelIXNum(orgFormation.getNotIntoMgrLevelIXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_X:
				orgFormation.setNotIntoMgrLevelXNum(orgFormation.getNotIntoMgrLevelXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_I:
				orgFormation.setNotIntoTechLevelINum(orgFormation.getNotIntoTechLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_II:
				orgFormation.setNotIntoTechLevelIINum(orgFormation.getNotIntoTechLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_III:
				orgFormation.setNotIntoTechLevelIIINum(orgFormation.getNotIntoTechLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IV:
				orgFormation.setNotIntoTechLevelIVNum(orgFormation.getNotIntoTechLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_V:
				orgFormation.setNotIntoTechLevelVNum(orgFormation.getNotIntoTechLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VI:
				orgFormation.setNotIntoTechLevelVINum(orgFormation.getNotIntoTechLevelVINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VII:
				orgFormation.setNotIntoTechLevelVIINum(orgFormation.getNotIntoTechLevelVIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VIII:
				orgFormation.setNotIntoTechLevelVIIINum(orgFormation.getNotIntoTechLevelVIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IX:
				orgFormation.setNotIntoTechLevelIXNum(orgFormation.getNotIntoTechLevelIXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_X:
				orgFormation.setNotIntoTechLevelXNum(orgFormation.getNotIntoTechLevelXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XI:
				orgFormation.setNotIntoTechLevelXINum(orgFormation.getNotIntoTechLevelXINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XII:
				orgFormation.setNotIntoTechLevelXIINum(orgFormation.getNotIntoTechLevelXIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XIII:
				orgFormation.setNotIntoTechLevelXIIINum(orgFormation.getNotIntoTechLevelXIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_I:
				orgFormation.setNotIntoWorkLevelINum(orgFormation.getNotIntoWorkLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_II:
				orgFormation.setNotIntoWorkLevelIINum(orgFormation.getNotIntoWorkLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_III:
				orgFormation.setNotIntoWorkLevelIIINum(orgFormation.getNotIntoWorkLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_IV:
				orgFormation.setNotIntoWorkLevelIVNum(orgFormation.getNotIntoWorkLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_V:
				orgFormation.setNotIntoWorkLevelVNum(orgFormation.getNotIntoWorkLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			default:
				throw new BusinessException("职级信息异常，请联系管理员");
		}
	}
	
	@Override
	public void executeUnlockInstPostIntoNum(String organId, String postLvlCode) throws BusinessException {
		
		executeUnlockInstPostIntoNum(organId, postLvlCode, 1);
		
	}
	
	@Override
	public void executeUnlockInstPostIntoNum(String organId, String postLvlCode, Integer humanNum)
			throws BusinessException {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.SYDW_MGR_I:
				orgFormation.setNotIntoMgrLevelINum(orgFormation.getNotIntoMgrLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_II:
				orgFormation.setNotIntoMgrLevelIINum(orgFormation.getNotIntoMgrLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_III:
				orgFormation.setNotIntoMgrLevelIIINum(orgFormation.getNotIntoMgrLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IV:
				orgFormation.setNotIntoMgrLevelIVNum(orgFormation.getNotIntoMgrLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_V:
				orgFormation.setNotIntoMgrLevelVNum(orgFormation.getNotIntoMgrLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VI:
				orgFormation.setNotIntoMgrLevelVINum(orgFormation.getNotIntoMgrLevelVINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VII:
				orgFormation.setNotIntoMgrLevelVIINum(orgFormation.getNotIntoMgrLevelVIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VIII:
				orgFormation.setNotIntoMgrLevelVIIINum(orgFormation.getNotIntoMgrLevelVIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IX:
				orgFormation.setNotIntoMgrLevelIXNum(orgFormation.getNotIntoMgrLevelIXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_X:
				orgFormation.setNotIntoMgrLevelXNum(orgFormation.getNotIntoMgrLevelXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_I:
				orgFormation.setNotIntoTechLevelINum(orgFormation.getNotIntoTechLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_II:
				orgFormation.setNotIntoTechLevelIINum(orgFormation.getNotIntoTechLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_III:
				orgFormation.setNotIntoTechLevelIIINum(orgFormation.getNotIntoTechLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IV:
				orgFormation.setNotIntoTechLevelIVNum(orgFormation.getNotIntoTechLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_V:
				orgFormation.setNotIntoTechLevelVNum(orgFormation.getNotIntoTechLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VI:
				orgFormation.setNotIntoTechLevelVINum(orgFormation.getNotIntoTechLevelVINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VII:
				orgFormation.setNotIntoTechLevelVIINum(orgFormation.getNotIntoTechLevelVIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VIII:
				orgFormation.setNotIntoTechLevelVIIINum(orgFormation.getNotIntoTechLevelVIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IX:
				orgFormation.setNotIntoTechLevelIXNum(orgFormation.getNotIntoTechLevelIXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_X:
				orgFormation.setNotIntoTechLevelXNum(orgFormation.getNotIntoTechLevelXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XI:
				orgFormation.setNotIntoTechLevelXINum(orgFormation.getNotIntoTechLevelXINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XII:
				orgFormation.setNotIntoTechLevelXIINum(orgFormation.getNotIntoTechLevelXIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XIII:
				orgFormation.setNotIntoTechLevelXIIINum(orgFormation.getNotIntoTechLevelXIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_I:
				orgFormation.setNotIntoWorkLevelINum(orgFormation.getNotIntoWorkLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_II:
				orgFormation.setNotIntoWorkLevelIINum(orgFormation.getNotIntoWorkLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_III:
				orgFormation.setNotIntoWorkLevelIIINum(orgFormation.getNotIntoWorkLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_IV:
				orgFormation.setNotIntoWorkLevelIVNum(orgFormation.getNotIntoWorkLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_V:
				orgFormation.setNotIntoWorkLevelVNum(orgFormation.getNotIntoWorkLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			default:
				throw new BusinessException("职级信息异常，请联系管理员");
		}
		
	}
	
	@Override
	public void executeLockInstPostOutNum(String organId, String postLvlCode) throws BusinessException {
		
		executeLockInstPostOutNum(organId, postLvlCode, 1);
		
	}
	
	@Override
	public void executeLockInstPostOutNum(String organId, String postLvlCode, Integer humanNum)
			throws BusinessException {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.SYDW_MGR_I:
				orgFormation.setNotOutMgrLevelINum(orgFormation.getNotOutMgrLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_II:
				orgFormation.setNotOutMgrLevelIINum(orgFormation.getNotOutMgrLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_III:
				orgFormation.setNotOutMgrLevelIIINum(orgFormation.getNotOutMgrLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IV:
				orgFormation.setNotOutMgrLevelIVNum(orgFormation.getNotOutMgrLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_V:
				orgFormation.setNotOutMgrLevelVNum(orgFormation.getNotOutMgrLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VI:
				orgFormation.setNotOutMgrLevelVINum(orgFormation.getNotOutMgrLevelVINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VII:
				orgFormation.setNotOutMgrLevelVIINum(orgFormation.getNotOutMgrLevelVIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VIII:
				orgFormation.setNotOutMgrLevelVIIINum(orgFormation.getNotOutMgrLevelVIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IX:
				orgFormation.setNotOutMgrLevelIXNum(orgFormation.getNotOutMgrLevelIXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_X:
				orgFormation.setNotOutMgrLevelXNum(orgFormation.getNotOutMgrLevelXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_I:
				orgFormation.setNotOutTechLevelINum(orgFormation.getNotOutTechLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_II:
				orgFormation.setNotOutTechLevelIINum(orgFormation.getNotOutTechLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_III:
				orgFormation.setNotOutTechLevelIIINum(orgFormation.getNotOutTechLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IV:
				orgFormation.setNotOutTechLevelIVNum(orgFormation.getNotOutTechLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_V:
				orgFormation.setNotOutTechLevelVNum(orgFormation.getNotOutTechLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VI:
				orgFormation.setNotOutTechLevelVINum(orgFormation.getNotOutTechLevelVINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VII:
				orgFormation.setNotOutTechLevelVIINum(orgFormation.getNotOutTechLevelVIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VIII:
				orgFormation.setNotOutTechLevelVIIINum(orgFormation.getNotOutTechLevelVIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IX:
				orgFormation.setNotOutTechLevelIXNum(orgFormation.getNotOutTechLevelIXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_X:
				orgFormation.setNotOutTechLevelXNum(orgFormation.getNotOutTechLevelXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XI:
				orgFormation.setNotOutTechLevelXINum(orgFormation.getNotOutTechLevelXINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XII:
				orgFormation.setNotOutTechLevelXIINum(orgFormation.getNotOutTechLevelXIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XIII:
				orgFormation.setNotOutTechLevelXIIINum(orgFormation.getNotOutTechLevelXIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_I:
				orgFormation.setNotOutWorkLevelINum(orgFormation.getNotOutWorkLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_II:
				orgFormation.setNotOutWorkLevelIINum(orgFormation.getNotOutWorkLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_III:
				orgFormation.setNotOutWorkLevelIIINum(orgFormation.getNotOutWorkLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_IV:
				orgFormation.setNotOutWorkLevelIVNum(orgFormation.getNotOutWorkLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_V:
				orgFormation.setNotOutWorkLevelVNum(orgFormation.getNotOutWorkLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			default:
				throw new BusinessException("职级信息异常，请联系管理员");
		}
		
	}
	
	@Override
	public void executeUnlockInstPostOutNum(String organId, String postLvlCode) throws BusinessException {
		
		executeUnlockInstPostOutNum(organId, postLvlCode, 1);
		
	}
	
	@Override
	public void executeUnlockInstPostOutNum(String organId, String postLvlCode, Integer humanNum)
			throws BusinessException {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.SYDW_MGR_I:
				orgFormation.setNotOutMgrLevelINum(orgFormation.getNotOutMgrLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_II:
				orgFormation.setNotOutMgrLevelIINum(orgFormation.getNotOutMgrLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_III:
				orgFormation.setNotOutMgrLevelIIINum(orgFormation.getNotOutMgrLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IV:
				orgFormation.setNotOutMgrLevelIVNum(orgFormation.getNotOutMgrLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_V:
				orgFormation.setNotOutMgrLevelVNum(orgFormation.getNotOutMgrLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VI:
				orgFormation.setNotOutMgrLevelVINum(orgFormation.getNotOutMgrLevelVINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VII:
				orgFormation.setNotOutMgrLevelVIINum(orgFormation.getNotOutMgrLevelVIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VIII:
				orgFormation.setNotOutMgrLevelVIIINum(orgFormation.getNotOutMgrLevelVIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IX:
				orgFormation.setNotOutMgrLevelIXNum(orgFormation.getNotOutMgrLevelIXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_X:
				orgFormation.setNotOutMgrLevelXNum(orgFormation.getNotOutMgrLevelXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_I:
				orgFormation.setNotOutTechLevelINum(orgFormation.getNotOutTechLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_II:
				orgFormation.setNotOutTechLevelIINum(orgFormation.getNotOutTechLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_III:
				orgFormation.setNotOutTechLevelIIINum(orgFormation.getNotOutTechLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IV:
				orgFormation.setNotOutTechLevelIVNum(orgFormation.getNotOutTechLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_V:
				orgFormation.setNotOutTechLevelVNum(orgFormation.getNotOutTechLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VI:
				orgFormation.setNotOutTechLevelVINum(orgFormation.getNotOutTechLevelVINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VII:
				orgFormation.setNotOutTechLevelVIINum(orgFormation.getNotOutTechLevelVIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VIII:
				orgFormation.setNotOutTechLevelVIIINum(orgFormation.getNotOutTechLevelVIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IX:
				orgFormation.setNotOutTechLevelIXNum(orgFormation.getNotOutTechLevelIXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_X:
				orgFormation.setNotOutTechLevelXNum(orgFormation.getNotOutTechLevelXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XI:
				orgFormation.setNotOutTechLevelXINum(orgFormation.getNotOutTechLevelXINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XII:
				orgFormation.setNotOutTechLevelXIINum(orgFormation.getNotOutTechLevelXIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XIII:
				orgFormation.setNotOutTechLevelXIIINum(orgFormation.getNotOutTechLevelXIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_I:
				orgFormation.setNotOutWorkLevelINum(orgFormation.getNotOutWorkLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_II:
				orgFormation.setNotOutWorkLevelIINum(orgFormation.getNotOutWorkLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_III:
				orgFormation.setNotOutWorkLevelIIINum(orgFormation.getNotOutWorkLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_IV:
				orgFormation.setNotOutWorkLevelIVNum(orgFormation.getNotOutWorkLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_V:
				orgFormation.setNotOutWorkLevelVNum(orgFormation.getNotOutWorkLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			default:
				throw new BusinessException("职级信息异常，请联系管理员");
		}
		
	}
	
	@Override
	public void executeIntoInstFormation(String organId) {
		
		executeIntoInstFormation(organId, 1);
		
	}
	
	@Override
	public void executeIntoInstFormation(String organId, Integer humanNum) {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		// 操作 实有人数、缺（超）编人数
		orgFormation.setActualNumber(orgFormation.getActualNumber() + humanNum);
		orgFormation.setVacancyExcessNumber(orgFormation.getUnitPlanningTotal() - orgFormation.getActualNumber());
		institutionOrgFormationService.update(orgFormation);
		
	}
	
	@Override
	public void executeOutInstFormation(String organId) {
		
		executeOutInstFormation(organId, 1);
		
	}
	
	@Override
	public void executeOutInstFormation(String organId, Integer humanNum) {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		// 操作 实有人数、缺（超）编人数
		orgFormation.setActualNumber(orgFormation.getActualNumber() - humanNum);
		orgFormation.setVacancyExcessNumber(orgFormation.getUnitPlanningTotal() - orgFormation.getActualNumber());
		institutionOrgFormationService.update(orgFormation);
		
	}
	
	@Override
	public void executeIntoInstPost(String organId, String postLvlCode) {
		
		executeIntoInstPost(organId, postLvlCode, 1);
		
	}
	
	@Override
	public void executeIntoInstPost(String organId, String postLvlCode, Integer humanNum) {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.SYDW_MGR_I:
				orgFormation.setRealMgrLevelINum(orgFormation.getRealMgrLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_II:
				orgFormation.setRealMgrLevelIINum(orgFormation.getRealMgrLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_III:
				orgFormation.setRealMgrLevelIIINum(orgFormation.getRealMgrLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IV:
				orgFormation.setRealMgrLevelIVNum(orgFormation.getRealMgrLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_V:
				orgFormation.setRealMgrLevelVNum(orgFormation.getRealMgrLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VI:
				orgFormation.setRealMgrLevelVINum(orgFormation.getRealMgrLevelVINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VII:
				orgFormation.setRealMgrLevelVIINum(orgFormation.getRealMgrLevelVIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VIII:
				orgFormation.setRealMgrLevelVIIINum(orgFormation.getRealMgrLevelVIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IX:
				orgFormation.setRealMgrLevelIXNum(orgFormation.getRealMgrLevelIXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_X:
				orgFormation.setRealMgrLevelXNum(orgFormation.getRealMgrLevelXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_I:
				orgFormation.setRealTechLevelINum(orgFormation.getRealTechLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_II:
				orgFormation.setRealTechLevelIINum(orgFormation.getRealTechLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_III:
				orgFormation.setRealTechLevelIIINum(orgFormation.getRealTechLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IV:
				orgFormation.setRealTechLevelIVNum(orgFormation.getRealTechLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_V:
				orgFormation.setRealTechLevelVNum(orgFormation.getRealTechLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VI:
				orgFormation.setRealTechLevelVINum(orgFormation.getRealTechLevelVINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VII:
				orgFormation.setRealTechLevelVIINum(orgFormation.getRealTechLevelVIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VIII:
				orgFormation.setRealTechLevelVIIINum(orgFormation.getRealTechLevelVIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IX:
				orgFormation.setRealTechLevelIXNum(orgFormation.getRealTechLevelIXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_X:
				orgFormation.setRealTechLevelXNum(orgFormation.getRealTechLevelXNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XI:
				orgFormation.setRealTechLevelXINum(orgFormation.getRealTechLevelXINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XII:
				orgFormation.setRealTechLevelXIINum(orgFormation.getRealTechLevelXIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XIII:
				orgFormation.setRealTechLevelXIIINum(orgFormation.getRealTechLevelXIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_I:
				orgFormation.setRealWorkLevelINum(orgFormation.getRealWorkLevelINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_II:
				orgFormation.setRealWorkLevelIINum(orgFormation.getRealWorkLevelIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_III:
				orgFormation.setRealWorkLevelIIINum(orgFormation.getRealWorkLevelIIINum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_IV:
				orgFormation.setRealWorkLevelIVNum(orgFormation.getRealWorkLevelIVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_V:
				orgFormation.setRealWorkLevelVNum(orgFormation.getRealWorkLevelVNum() + humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			default:
				throw new BusinessException("职级信息异常，请联系管理员");
		}
		
	}
	
	@Override
	public void executeOutInstPost(String organId, String postLvlCode) {
		
		executeOutInstPost(organId, postLvlCode, 1);
		
	}
	
	@Override
	public void executeOutInstPost(String organId, String postLvlCode, Integer humanNum) {
		
		InstitutionOrgFormation orgFormation = this.getInstFormation(organId);
		
		switch (postLvlCode) {
			case FormationControl.SYDW_MGR_I:
				orgFormation.setRealMgrLevelINum(orgFormation.getRealMgrLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_II:
				orgFormation.setRealMgrLevelIINum(orgFormation.getRealMgrLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_III:
				orgFormation.setRealMgrLevelIIINum(orgFormation.getRealMgrLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IV:
				orgFormation.setRealMgrLevelIVNum(orgFormation.getRealMgrLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_V:
				orgFormation.setRealMgrLevelVNum(orgFormation.getRealMgrLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VI:
				orgFormation.setRealMgrLevelVINum(orgFormation.getRealMgrLevelVINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VII:
				orgFormation.setRealMgrLevelVIINum(orgFormation.getRealMgrLevelVIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_VIII:
				orgFormation.setRealMgrLevelVIIINum(orgFormation.getRealMgrLevelVIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_IX:
				orgFormation.setRealMgrLevelIXNum(orgFormation.getRealMgrLevelIXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_MGR_X:
				orgFormation.setRealMgrLevelXNum(orgFormation.getRealMgrLevelXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_I:
				orgFormation.setRealTechLevelINum(orgFormation.getRealTechLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_II:
				orgFormation.setRealTechLevelIINum(orgFormation.getRealTechLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_III:
				orgFormation.setRealTechLevelIIINum(orgFormation.getRealTechLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IV:
				orgFormation.setRealTechLevelIVNum(orgFormation.getRealTechLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_V:
				orgFormation.setRealTechLevelVNum(orgFormation.getRealTechLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VI:
				orgFormation.setRealTechLevelVINum(orgFormation.getRealTechLevelVINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VII:
				orgFormation.setRealTechLevelVIINum(orgFormation.getRealTechLevelVIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_VIII:
				orgFormation.setRealTechLevelVIIINum(orgFormation.getRealTechLevelVIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_IX:
				orgFormation.setRealTechLevelIXNum(orgFormation.getRealTechLevelIXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_X:
				orgFormation.setRealTechLevelXNum(orgFormation.getRealTechLevelXNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XI:
				orgFormation.setRealTechLevelXINum(orgFormation.getRealTechLevelXINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XII:
				orgFormation.setRealTechLevelXIINum(orgFormation.getRealTechLevelXIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_TECH_XIII:
				orgFormation.setRealTechLevelXIIINum(orgFormation.getRealTechLevelXIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_I:
				orgFormation.setRealWorkLevelINum(orgFormation.getRealWorkLevelINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_II:
				orgFormation.setRealWorkLevelIINum(orgFormation.getRealWorkLevelIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_III:
				orgFormation.setRealWorkLevelIIINum(orgFormation.getRealWorkLevelIIINum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_IV:
				orgFormation.setRealWorkLevelIVNum(orgFormation.getRealWorkLevelIVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			case FormationControl.SYDW_WORK_V:
				orgFormation.setRealWorkLevelVNum(orgFormation.getRealWorkLevelVNum() - humanNum);
				institutionOrgFormationService.update(orgFormation);
				break;
			default:
				throw new BusinessException("职级信息异常，请联系管理员");
		}
		
	}
	
}
