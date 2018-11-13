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
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.repository.organization.FormationControlRepository;
import com.wondersgroup.human.service.organization.FormationControlService;
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
		OrgFormation orgFormation = validateFormation(organId);
		
		// 判断是否存在编控信息，若单位不存在，直接查询长宁区基础编控信息
		FormationControl formationControl = formationControlService.findUniqueBy("organ.id", organId);
		if (formationControl == null) {
			// 如果该单位没有编控信息，按照长宁区编控信息进行控制。
			OrganNode changNingNode = organNodeService.findUniqueBy("code", CommonConst.ROOT_ORGAN_CODE);
			formationControl = formationControlService.findUniqueBy("organ.id", changNingNode.getId());
			if (formationControl == null)
				throw new BusinessException("未获取到该单位或长宁区基础编制信息，请联系管理员");
		}
		// 根据编控信息，进行编控 实控=定编数-在编数-处级领导空缺数-供给关系尚未调入人员+允许溢出人数
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		
		// 定编数
		int unitPlanningTotal = orgFormation.getUnitPlanningTotal() == null ? 0 : orgFormation.getUnitPlanningTotal();
		// 在编数
		int actualNumber = orgFormation.getActualNumber() == null ? 0 : orgFormation.getActualNumber();
		// 处级领导空缺数
		int vacancyDivisionChiefLevelNumber = orgFormation.getVacancyDivisionChiefLevelNumber() == null ? 0
				: orgFormation.getVacancyDivisionChiefLevelNumber();
		// 供给关系尚未调入人员
		int notIntoNum = orgFormation.getNotIntoNum() == null ? 0 : orgFormation.getNotIntoNum();
		// 供给关系尚未调出人员
		int notOutNum = orgFormation.getNotOutNum() == null ? 0 : orgFormation.getNotOutNum();
		
		// 判断该机构是否进行编控
		if (formationControl.getIsOpenControl().getId().equals(yesCodeInfo.getId())) {
			// 判断编控信息是否部门独有
			if (!formationControl.getOrgan().getCode().equals(CommonConst.ROOT_ORGAN_CODE)) {
				// 独有
				realNum = unitPlanningTotal - actualNumber - vacancyDivisionChiefLevelNumber - notIntoNum
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
				
				realNum = unitPlanningTotal - actualNumber - vacancyDivisionChiefLevelNumber - notIntoNum + flowNum;
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
	public JudgePostResult queryJudgePostNum(String organId, String postLvlCode) throws BusinessException {
		
		return queryJudgePostNum(organId, postLvlCode, 1);
	}
	
	@Override
	public JudgePostResult queryJudgePostNum(String organId, String postLvlCode, Integer humanNum)
			throws BusinessException {
		
		if (enabledFormationControl == false)
			return new JudgePostResult(true, false);
		OrgFormation orgFormation = validateFormation(organId);
		
		// 判断是否存在编控信息，若单位不存在，直接查询长宁区基础编控信息
		FormationControl formationControl = formationControlService.findUniqueBy("organ.id", organId);
		if (formationControl == null) {
			// 如果该单位没有编控信息，按照长宁区编控信息进行控制。
			OrganNode changNingNode = organNodeService.findUniqueBy("code", CommonConst.ROOT_ORGAN_CODE);
			formationControl = formationControlService.findUniqueBy("organ.id", changNingNode.getId());
			if (formationControl == null)
				throw new BusinessException("未获取到该单位或长宁区基础编制信息，请联系管理员");
		}
		
		// 业务判断
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		
		// 首先判断职级
		if (postLvlCode.equals(FormationControl.SECTION_CHIEF)) {
			// 正科级进入
			int realNum;
			// 实控=定编数-在编数-正科未调入人员
			realNum = orgFormation.getApproveSectionChiefLevelNumber() - orgFormation.getSectionChiefLevelNumber()
					- orgFormation.getNotIntoSectionChiefNum();
			if (realNum - humanNum >= 0) {
				return new JudgePostResult(true, false);
			} else {
				throw new BusinessException("实际科级编制数已超编，请联系管理员");
			}
		} else if (postLvlCode.equals(FormationControl.DEPUTY_SECTION_CHIEF)) {
			// 副科级进入
			// 判断是否高职低配（副科长(科级非领导)没位子，就去正科占用）
			if (formationControl.getIsLowToHigh().getId().equals(yesCodeInfo.getId())) {
				// 先判断副科长(科级非领导)职数
				int realNum;
				// 实控=定编数-在编数-副科长(科级非领导)未调入人员
				realNum = orgFormation.getApproveNonLeaderSectionChiefLevelNumber()
						- orgFormation.getNonLeaderSectionChiefLevelNumber()
						- orgFormation.getNotIntoDeputySectionChiefNum();
				if (realNum - humanNum >= 0) {
					return new JudgePostResult(true, false);
				} else {
					// 如果不够，则判断正科级职数
					// 实控=定编数-在编数-正科未调入人员
					realNum = orgFormation.getApproveSectionChiefLevelNumber()
							- orgFormation.getSectionChiefLevelNumber() - orgFormation.getNotIntoSectionChiefNum();
					if (realNum - humanNum >= 0) {
						// 高职低配
						return new JudgePostResult(true, true);
					} else {
						throw new BusinessException("实际科级编制数已超编，请联系管理员");
					}
				}
			} else {
				// 直接判断副科长(科级非领导)职数
				int realNum;
				// 实控=定编数-在编数-副科长(科级非领导)未调入人员
				realNum = orgFormation.getApproveNonLeaderSectionChiefLevelNumber()
						- orgFormation.getNonLeaderSectionChiefLevelNumber()
						- orgFormation.getNotIntoDeputySectionChiefNum();
				if (realNum - humanNum >= 0) {
					return new JudgePostResult(true, false);
				} else {
					throw new BusinessException("实际科级编制数已超编，请联系管理员");
				}
			}
		} else if (postLvlCode.equals(FormationControl.DIRECTOR)
				|| postLvlCode.equals(FormationControl.DEPUTY_DIRECTOR)) {
			// 正副处长级进入
			int realNum;
			// 实控=定编数-在编数
			realNum = orgFormation.getApproveDivisionChiefLevelNumber() - orgFormation.getDivisionChiefLevelNumber();
			if (realNum - humanNum >= 0) {
				return new JudgePostResult(true, false);
			} else {
				throw new BusinessException("实际处级编制数已超编，请联系管理员");
			}
		} else if (postLvlCode.equals(FormationControl.CLERK) || postLvlCode.equals(FormationControl.C_CLERK)) {
			return new JudgePostResult(true, false);
		} else {
			throw new BusinessException("职级信息异常，请联系管理员");
		}
	}
	
	private OrgFormation validateFormation(String organId) {
		
		// 先判断是否存在单位信息和编制信息
		OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", organId);
		if (orgInfo == null)
			throw new BusinessException("未获取到该单位基础信息，请联系管理员");
		OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", orgInfo.getId());
		if (orgFormation == null)
			throw new BusinessException("未获取到该单位编制信息，请联系管理员");
		return orgFormation;
	}
	
	@Override
	public void executeLockIntoFormationNum(String organId) throws BusinessException {
		
		executeLockIntoFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeLockIntoFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			orgFormation.setNotIntoNum(orgFormation.getNotIntoNum() + humanNum);
			orgFormationService.update(orgFormation);
		}
	}
	
	@Override
	public void executeUnlockIntoFormationNum(String organId) throws BusinessException {
		
		executeUnlockIntoFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeUnlockIntoFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			orgFormation.setNotIntoNum(orgFormation.getNotIntoNum() - humanNum);
			orgFormationService.update(orgFormation);
		}
	}
	
	@Override
	public void executeLockOutFormationNum(String organId) throws BusinessException {
		
		executeLockOutFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeLockOutFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			orgFormation.setNotOutNum(orgFormation.getNotOutNum() + humanNum);
			orgFormationService.update(orgFormation);
		}
	}
	
	@Override
	public void executeUnlockOutFormationNum(String organId) throws BusinessException {
		
		executeUnlockOutFormationNum(organId, 1);
		
	}
	
	@Override
	public void executeUnlockOutFormationNum(String organId, Integer humanNum) throws BusinessException {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			orgFormation.setNotOutNum(orgFormation.getNotOutNum() - humanNum);
			orgFormationService.update(orgFormation);
		}
	}
	
	@Override
	public void executeLockPostIntoNum(String organId, String PostCode, Boolean isLowToHigh) throws BusinessException {
		
		executeLockPostIntoNum(organId, PostCode, 1, isLowToHigh);
		
	}
	
	@Override
	public void executeLockPostIntoNum(String organId, String postLvlCode, Integer humanNum, Boolean isLowToHigh)
			throws BusinessException {
		
		if (enabledFormationControl == true) {
			
			OrgFormation orgFormation = validateFormation(organId);
			
			// 首先判断职级
			if (postLvlCode.equals(FormationControl.SECTION_CHIEF)) {
				// 正科级进入
				// 直接处理占编业务
				orgFormation.setNotIntoSectionChiefNum(orgFormation.getNotIntoSectionChiefNum() + humanNum);
				orgFormationService.update(orgFormation);
			} else if (postLvlCode.equals(FormationControl.DEPUTY_SECTION_CHIEF)) {
				// 副科级进入
				// 判断高职低配情况
				if (isLowToHigh) {
					// 如果是，将占用正科尚未调入数
					orgFormation.setNotIntoSectionChiefNum(orgFormation.getNotIntoSectionChiefNum() + humanNum);
					orgFormationService.update(orgFormation);
				} else {
					// 如果否，占用副科尚未调入数
					orgFormation
							.setNotIntoDeputySectionChiefNum(orgFormation.getNotIntoDeputySectionChiefNum() + humanNum);
					orgFormationService.update(orgFormation);
				}
			} else if (postLvlCode.equals(FormationControl.CLERK) || postLvlCode.equals(FormationControl.C_CLERK)) {
				// 科员级跳过
			} else {
				throw new BusinessException("职级信息异常，请联系管理员");
			}
		}
	}
	
	@Override
	public void executeUnlockPostIntoNum(String organId, String PostCode, Boolean isLowToHigh)
			throws BusinessException {
		
		executeUnlockPostIntoNum(organId, PostCode, 1, isLowToHigh);
		
	}
	
	@Override
	public void executeUnlockPostIntoNum(String organId, String postLvlCode, Integer humanNum, Boolean isLowToHigh)
			throws BusinessException {
		
		if (enabledFormationControl == true) {
			
			OrgFormation orgFormation = validateFormation(organId);
			
			// 首先判断职级
			if (postLvlCode.equals(FormationControl.SECTION_CHIEF)) {
				// 正科级进入
				// 直接处理解锁未调入业务
				orgFormation.setNotIntoSectionChiefNum(orgFormation.getNotIntoSectionChiefNum() - humanNum);
				orgFormationService.update(orgFormation);
			} else if (postLvlCode.equals(FormationControl.DEPUTY_SECTION_CHIEF)) {
				// 副科级进入
				// 判断高职低配情况
				if (isLowToHigh) {
					// 如果是，将解锁正科尚未调入数
					orgFormation.setNotIntoSectionChiefNum(orgFormation.getNotIntoSectionChiefNum() - humanNum);
					orgFormationService.update(orgFormation);
				} else {
					// 如果否，解锁副科尚未调入数
					orgFormation
							.setNotIntoDeputySectionChiefNum(orgFormation.getNotIntoDeputySectionChiefNum() - humanNum);
					orgFormationService.update(orgFormation);
				}
			} else if (postLvlCode.equals(FormationControl.CLERK) || postLvlCode.equals(FormationControl.C_CLERK)) {
				// 科员级跳过
			} else {
				throw new BusinessException("职级信息异常，请联系管理员");
			}
		}
	}
	
	@Override
	public void executeLockPostOutNum(String organId, String PostCode, Boolean isLowToHigh) throws BusinessException {
		
		executeLockPostOutNum(organId, PostCode, 1, isLowToHigh);
		
	}
	
	@Override
	public void executeLockPostOutNum(String organId, String postLvlCode, Integer humanNum, Boolean isLowToHigh)
			throws BusinessException {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			
			// 首先判断职级
			if (postLvlCode.equals(FormationControl.SECTION_CHIEF)) {
				// 正科级进入
				// 直接处理锁定尚未调出业务
				orgFormation.setNotOutSectionChiefNum(orgFormation.getNotOutSectionChiefNum() + humanNum);
				orgFormationService.update(orgFormation);
			} else if (postLvlCode.equals(FormationControl.DEPUTY_SECTION_CHIEF)) {
				// 副科长进入
				// 判断高职低配情况
				if (isLowToHigh) {
					// 如果是，将锁定正科尚未出人数
					orgFormation.setNotOutSectionChiefNum(orgFormation.getNotOutSectionChiefNum() + humanNum);
					orgFormationService.update(orgFormation);
				} else {
					// 如果否，锁定副科尚未出人数
					orgFormation
							.setNotOutDeputySectionChiefNum(orgFormation.getNotOutDeputySectionChiefNum() + humanNum);
					orgFormationService.update(orgFormation);
				}
			} else if (postLvlCode.equals(FormationControl.CLERK) || postLvlCode.equals(FormationControl.C_CLERK)) {
				// 科员级跳过
			} else {
				throw new BusinessException("职级信息异常，请联系管理员");
			}
		}
	}
	
	@Override
	public void executeUnlockPostOutNum(String organId, String PostCode, Boolean isLowToHigh) throws BusinessException {
		
		executeUnlockPostOutNum(organId, PostCode, 1, isLowToHigh);
		
	}
	
	@Override
	public void executeUnlockPostOutNum(String organId, String postLvlCode, Integer humanNum, Boolean isLowToHigh)
			throws BusinessException {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			
			// 首先判断职级
			if (postLvlCode.equals(FormationControl.SECTION_CHIEF)) {
				// 正科级进入
				// 直接处理解锁尚未调出业务
				orgFormation.setNotOutSectionChiefNum(orgFormation.getNotOutSectionChiefNum() - humanNum);
				orgFormationService.update(orgFormation);
			} else if (postLvlCode.equals(FormationControl.DEPUTY_SECTION_CHIEF)) {
				// 副科级进入
				// 判断高职低配情况
				if (isLowToHigh) {
					// 如果是，将解锁正科尚未调入数
					orgFormation.setNotOutSectionChiefNum(orgFormation.getNotOutSectionChiefNum() - humanNum);
					orgFormationService.update(orgFormation);
				} else {
					// 如果否，解锁副科尚未调入数
					orgFormation
							.setNotOutDeputySectionChiefNum(orgFormation.getNotOutDeputySectionChiefNum() - humanNum);
					orgFormationService.update(orgFormation);
				}
			} else if (postLvlCode.equals(FormationControl.CLERK) || postLvlCode.equals(FormationControl.C_CLERK)) {
				// 科员级跳过
			} else {
				throw new BusinessException("职级信息异常，请联系管理员");
			}
		}
	}
	
	@Override
	public void executeIntoFormation(String organId) {
		
		executeIntoFormation(organId, 1);
		
	}
	
	@Override
	public void executeIntoFormation(String organId, Integer humanNum) {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			// 操作 实有人数、缺（超）编人数
			orgFormation.setActualNumber(orgFormation.getActualNumber() + humanNum);
			orgFormation.setVacancyExcessNumber(orgFormation.getVacancyExcessNumber() - humanNum);
			orgFormationService.update(orgFormation);
		}
	}
	
	@Override
	public void executeOutFormation(String organId) {
		
		executeOutFormation(organId, 1);
		
	}
	
	@Override
	public void executeOutFormation(String organId, Integer humanNum) {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			// 操作 实有人数、缺（超）编人数
			orgFormation.setActualNumber(orgFormation.getActualNumber() - humanNum);
			orgFormation.setVacancyExcessNumber(orgFormation.getVacancyExcessNumber() + humanNum);
			orgFormationService.update(orgFormation);
		}
	}
	
	@Override
	public void executeIntoPost(String organId, String PostCode, Boolean isLowToHigh) {
		
		executeIntoPost(organId, PostCode, isLowToHigh, 1);
		
	}
	
	@Override
	public void executeIntoPost(String organId, String postLvlCode, Boolean isLowToHigh, Integer humanNum) {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			// 首先判断职级
			if (postLvlCode.equals(FormationControl.SECTION_CHIEF)) {
				// 正科长职级进入
				orgFormation.setSectionChiefLevelNumber(orgFormation.getSectionChiefLevelNumber() + humanNum);
				orgFormation
						.setVacancySectionChiefLevelNumber(orgFormation.getVacancySectionChiefLevelNumber() - humanNum);
				orgFormationService.update(orgFormation);
			} else if (postLvlCode.equals(FormationControl.DEPUTY_SECTION_CHIEF)) {
				// 副科长进入
				// 判断高职低配情况
				if (isLowToHigh) {
					// 如果是，调整科级领导数
					orgFormation.setSectionChiefLevelNumber(orgFormation.getSectionChiefLevelNumber() + humanNum);
					orgFormation.setVacancySectionChiefLevelNumber(
							orgFormation.getVacancySectionChiefLevelNumber() - humanNum);
					orgFormationService.update(orgFormation);
				} else {
					// 如果否，调整科级非领导数
					orgFormation.setNonLeaderSectionChiefLevelNumber(
							orgFormation.getNonLeaderSectionChiefLevelNumber() + humanNum);
					orgFormation.setVacancyNonLeaderSectionChiefLevelNumber(
							orgFormation.getVacancyNonLeaderSectionChiefLevelNumber() - humanNum);
					orgFormationService.update(orgFormation);
				}
			} else if (postLvlCode.equals(FormationControl.DIRECTOR)
					|| postLvlCode.equals(FormationControl.DEPUTY_DIRECTOR)) {
				// 正副处长职级进入
				orgFormation.setDivisionChiefLevelNumber(orgFormation.getDivisionChiefLevelNumber() + humanNum);
				orgFormation.setVacancyDivisionChiefLevelNumber(
						orgFormation.getVacancyDivisionChiefLevelNumber() - humanNum);
				orgFormationService.update(orgFormation);
			} else if (postLvlCode.equals(FormationControl.CLERK) || postLvlCode.equals(FormationControl.C_CLERK)) {
				// 科员级跳过
			} else {
				throw new BusinessException("职级信息异常，请联系管理员");
			}
		}
	}
	
	@Override
	public void executeOutPost(String organId, String PostCode, Boolean isLowToHigh) {
		
		executeOutPost(organId, PostCode, isLowToHigh, 1);
		
	}
	
	@Override
	public void executeOutPost(String organId, String postLvlCode, Boolean isLowToHigh, Integer humanNum) {
		
		if (enabledFormationControl == true) {
			OrgFormation orgFormation = validateFormation(organId);
			// 首先判断职级
			if (postLvlCode.equals(FormationControl.SECTION_CHIEF)) {
				// 正科级进入
				orgFormation.setSectionChiefLevelNumber(orgFormation.getSectionChiefLevelNumber() - humanNum);
				orgFormation
						.setVacancySectionChiefLevelNumber(orgFormation.getVacancySectionChiefLevelNumber() + humanNum);
				orgFormationService.update(orgFormation);
			} else if (postLvlCode.equals(FormationControl.DEPUTY_SECTION_CHIEF)) {
				// 副科级进入
				// 判断高职低配情况
				if (isLowToHigh) {
					// 如果是，调整科级领导数
					orgFormation.setSectionChiefLevelNumber(orgFormation.getSectionChiefLevelNumber() - humanNum);
					orgFormation.setVacancySectionChiefLevelNumber(
							orgFormation.getVacancySectionChiefLevelNumber() + humanNum);
					orgFormationService.update(orgFormation);
				} else {
					// 如果否，调整科级非领导数
					orgFormation.setNonLeaderSectionChiefLevelNumber(
							orgFormation.getNonLeaderSectionChiefLevelNumber() - humanNum);
					orgFormation.setVacancyNonLeaderSectionChiefLevelNumber(
							orgFormation.getVacancyNonLeaderSectionChiefLevelNumber() + humanNum);
					orgFormationService.update(orgFormation);
				}
			} else if (postLvlCode.equals(FormationControl.DIRECTOR)
					|| postLvlCode.equals(FormationControl.DEPUTY_DIRECTOR)) {
				// 正副处长职级进入
				orgFormation.setDivisionChiefLevelNumber(orgFormation.getDivisionChiefLevelNumber() - humanNum);
				orgFormation.setVacancyDivisionChiefLevelNumber(
						orgFormation.getVacancyDivisionChiefLevelNumber() + humanNum);
				orgFormationService.update(orgFormation);
			} else if (postLvlCode.equals(FormationControl.CLERK) || postLvlCode.equals(FormationControl.C_CLERK)) {
				// 科员级跳过
			} else {
				throw new BusinessException("职级信息异常，请联系管理员");
			}
		}
	}
}
