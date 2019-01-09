/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: FormationControlController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 单位机构 编控 控制器
 * 创建人: jiang
 * 创建时间: 2018年10月25日14:43:11
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年10月25日14:43:14
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.organization;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.bo.organization.InstitutionOrgFormation;
import com.wondersgroup.human.bo.organization.OrgFormation;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.service.organization.InstitutionOrgFormationService;
import com.wondersgroup.human.service.organization.OrgFormationService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.organization.FormationControlVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: FormationControlController
 * @Description: 单位编控 控制器
 * @author: jiang
 * @date: 2018年10月25日14:45:15
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/formationControl")
@Controller
public class FormationControlController extends GenericController {
	
	@Autowired
	private FormationControlService formationControlService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	@Autowired
	private OrgInfoService orgInfoService;
	
	@Autowired
	private OrgFormationService orgFormationService;
	
	@Autowired
	private InstitutionOrgFormationService institutionOrgFormationService;
	
	@Autowired
	private DictableService dictableService;
	
	/**
	 * 返回视图路径
	 */
	private static final String FORMATION_CONTROLLER_LIST = "models/organization/formationControlList";
	
	private static final String FORMATION_CONTROLLER_EDIT = "models/organization/formationControlEdit";
	
	/**
	 * @Title: list
	 * @Description: 单位编控信息 列表
	 * @return: String
	 */
	@RequestMapping("/list")
	public String List() {
		
		return FORMATION_CONTROLLER_LIST;
	}
	
	/**
	 * @Title: edit
	 * @Description: 编控设置
	 * @return: String
	 */
	@RequestMapping("/edit")
	public String edit(String organId, Model model) {
		
		boolean isDisabledLowToHigh = true;
		// 查询出所属编控信息
		FormationControl formationControl = formationControlService.findUniqueBy("organ.id", organId);
		if (formationControl == null) {
			formationControl = new FormationControl();
			CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
			formationControl.setIsLowToHigh(noCodeInfo);
		}
		// 组织节点信息
		OrganNode organNode = organNodeService.get(organId);
		formationControl.setOrgan(organNode);
		if (!CommonConst.ROOT_ORGAN_CODE.equals(organNode.getCode())) {
			OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", organId);
			if (orgInfo == null) {
				throw new BusinessException("单位基础信息为空，无法操作编控设置业务！");
			} else {
				// 判断机构的类型为行政或事业单位
				if (CommonConst.ORGAN_TYPE_D_CLASS_CODE.equals(orgInfo.getOrgan().getOrganNodeType().getCode())) {
					OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", orgInfo.getId());
					isDisabledLowToHigh = false;
					if (orgFormation == null)
						throw new BusinessException("单位编制信息为空，无法操作编控设置业务！");
				} else if (CommonConst.ORGAN_TYPE_UNIT_CODE.equals(orgInfo.getOrgan().getOrganNodeType().getCode())) {
					InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
							orgInfo.getId());
					if (orgFormation == null)
						throw new BusinessException("单位编制信息为空，无法操作编控设置业务！");
				} else {
					throw new BusinessException("单位类型有误，无法操作编控设置业务！");
				}
				
			}
		}else{
			isDisabledLowToHigh = false;
		}
		
		model.addAttribute("formationControl", formationControl);
		model.addAttribute("isDisabledLowToHigh", isDisabledLowToHigh);
		return FORMATION_CONTROLLER_EDIT;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 机构编控列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<FormationControlVO>
	 */
	@Log(title = "查询机构编控信息列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<FormationControlVO> pageList(Integer limit, Integer page, String organId) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FormationControl.class);
		
		if (StringUtils.isNotBlank(organId)) {// organ下的单位
			// 查询本节点下所有单位
			List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(organId);
			List<String> organList = new ArrayList<String>();
			for (OrganNode org : orgNodeList) {
				organList.add(org.getId());
			}
			
			detachedCriteria.add(Restrictions.in("organ.id", organList));
			
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<FormationControlVO> pageInfo = formationControlService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 编控设置保存功能
	 * @param temp 编控设置信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑机构编控信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(FormationControl temp) {
		
		AjaxResult result = new AjaxResult(true);
		
		// 组织节点信息
		OrganNode organNode = organNodeService.get(temp.getOrgan().getId());
		// 根据溢出规则计算溢出人数
		if (StringUtils.isNotBlank(temp.getOverflowRule())
				&& !CommonConst.ROOT_ORGAN_CODE.equals(organNode.getCode())) {
			// 判断是否有%号存在
			if (temp.getOverflowRule().indexOf("%") != -1) {
				// 存在%获取，当前单位的定编人数
				OrgInfo orgInfo = orgInfoService.findUniqueBy("organ.id", temp.getOrgan().getId());
				// 判断机构的类型为行政或事业单位
				if (CommonConst.ORGAN_TYPE_D_CLASS_CODE.equals(orgInfo.getOrgan().getOrganNodeType().getCode())) {
					OrgFormation orgFormation = orgFormationService.findUniqueBy("orgInfo.id", orgInfo.getId());
					// 定编数
					Integer unitPlanningTotal = orgFormation.getUnitPlanningTotal();
					// 溢出比例
					String scaleStr = temp.getOverflowRule().split("%")[0];
					Double scale = Double.parseDouble(scaleStr) / 100;
					Double flowNum = Math.ceil(unitPlanningTotal * scale);
					temp.setOverflowNum(flowNum.intValue());
				} else if (CommonConst.ORGAN_TYPE_UNIT_CODE.equals(orgInfo.getOrgan().getOrganNodeType().getCode())) {
					InstitutionOrgFormation orgFormation = institutionOrgFormationService.findUniqueBy("orgInfo.id",
							orgInfo.getId());
					// 定编数
					Integer unitPlanningTotal = orgFormation.getUnitPlanningTotal();
					// 溢出比例
					String scaleStr = temp.getOverflowRule().split("%")[0];
					Double scale = Double.parseDouble(scaleStr) / 100;
					Double flowNum = Math.ceil(unitPlanningTotal * scale);
					temp.setOverflowNum(flowNum.intValue());
				} else {
					throw new BusinessException("单位类型有误，无法操作编控设置业务！");
				}
				
			} else {
				temp.setOverflowNum(Integer.parseInt(temp.getOverflowRule()));
			}
		} else {
			// 如果没有配置规则，默认人数为0
			temp.setOverflowNum(0);
		}
		
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				FormationControl formationControl = formationControlService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, formationControl);
				DictUtils.operationCodeInfo(formationControl);// 将CodeInfo中id为空的属性 设置为null
				formationControlService.saveOrUpdate(formationControl);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				formationControlService.saveOrUpdate(temp);
			}
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
}
