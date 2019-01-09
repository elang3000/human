package com.wondersgroup.human.controller.ofc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
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
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.Experience;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofc.ServantBasicInfo;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.service.ofc.ExperienceService;
import com.wondersgroup.human.service.ofc.RewardAndPunishService;
import com.wondersgroup.human.service.ofc.ServantBasicInfoService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.util.ImgPicUtil;
import com.wondersgroup.human.vo.ofc.ServantVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: OfcController
 * @Description: 信息维护控制器
 * @author: GP
 * @date: 2018年5月8日 下午3:29:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("/ofc")
public class OfcController extends GenericController {
	
	// 跳转路径
	/**
	 * 人员列表
	 */
	private static final String VIEW_OFC_LIST = "models/ofc/infoMainten/ofcList";
	
	/**
	 * 公务员登记表
	 */
	private static final String VIEW_OFC_MAIN = "models/ofc/infoMainten/ofcMain";
	
	private static final String VIEW_OFC_MAIN_VIEW = "models/ofc/infoMainten/ofcMainView";
	
	/**
	 * 公务员基本信息集
	 */
	private static final String VIEW_OFC_MAIN_EIDT = "models/ofc/infoMainten/ofcMainEdit";
	
	private static final String VIEW_OFC_MAIN_EIDT_VIEW = "models/ofc/infoMainten/ofcMainEditView";
	
	/**
	 * 其他子集
	 */
	private static final String VIEW_OFC_EXTEND_LIST = "models/ofc/infoMainten/ofcExtendList";
	
	private static final String VIEW_OFC_EXTEND_LIST_VIEW = "models/ofc/infoMainten/ofcExtendListView";
	
	private static final String VIEW_OFC_VALIDATE = "models/ofc/validateServatBasicInfo";
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private ExperienceService experienceService;
	
	@Autowired
	private RewardAndPunishService rewardAndPunishService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	ServantBasicInfoService servantBasicInfoService;
	
	@Autowired
	OrganNodeService organNodeService;
	
	/**
	 * @Title: ofclist
	 * @Description: 在职人员信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/list")
	public String ofclist() {
		
		return VIEW_OFC_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 信息维护 人员信息列表，查询在职和试用期人员
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查询在职和试用期人员信息列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/ofcPageList")
	public Page<ServantVO> ofcPageList(Servant servant, Integer limit, Integer page) {
		
		String organNodeId = this.getRequest().getParameter("organNodeId");
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		
		if (StringUtils.isBlank(organNodeId)) {
			if (organNode.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
				// 如果是长宁人事局，可查看长宁下所有单位
				organNodeId = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE).getId();
			} else {
				organNodeId = organNode.getId();
			}
		}
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
		// 查询本节点下所有单位
		List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(organNodeId);
		List<String> departList = new ArrayList<>();
		for (OrganNode org : orgNodeList) {
			departList.add(org.getId());
		}
		detachedCriteria.add(Restrictions.in("departId", departList));
		
		if (StringUtils.isNotBlank(servant.getName())) {
			detachedCriteria.add(Restrictions.like("name", servant.getName(), MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(servant.getCardNo())) {// 身份证
			detachedCriteria.add(Restrictions.eq("cardNo", servant.getCardNo()));
		}
		if (servant.getSex() != null && StringUtils.isNotBlank(servant.getSex().getId())) {// 性别
			detachedCriteria.add(Restrictions.eq("sex.id", servant.getSex().getId()));
		}
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		CodeInfo probation = dictableService.getCodeInfoByCode("6", DictTypeCodeContant.CODE_HUMAN_STATUS);// 试用期人员CODE
		detachedCriteria.add(Restrictions.in("isOnHold.id", isOnHold.getId(),probation.getId()));
		detachedCriteria.addOrder(Order.desc("reportDate"));
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<ServantVO> pageInfo = servantService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
	/**
	 * @Title: pageList
	 * @Description: 在职人员信息列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查询在职人员信息列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<ServantVO> pageList(Servant servant, Integer limit, Integer page,String type) {
		
		String organNodeId = this.getRequest().getParameter("organNodeId");
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		
		if (StringUtils.isBlank(organNodeId)) {
			if (organNode.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
				// 如果是长宁人事局，可查看长宁下所有单位
				organNodeId = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE).getId();
			} else {
				organNodeId = organNode.getId();
			}
		}
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
		// 查询本节点下所有单位
		List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(organNodeId);
		List<String> departList = new ArrayList<>();
		for (OrganNode org : orgNodeList) {
			departList.add(org.getId());
		}
		detachedCriteria.add(Restrictions.in("departId", departList));
		
		if (StringUtils.isNotBlank(servant.getName())) {
			detachedCriteria.add(Restrictions.like("name", servant.getName(), MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(servant.getCardNo())) {// 身份证
			detachedCriteria.add(Restrictions.eq("cardNo", servant.getCardNo()));
		}
		if (servant.getSex() != null && StringUtils.isNotBlank(servant.getSex().getId())) {// 性别
			detachedCriteria.add(Restrictions.eq("sex.id", servant.getSex().getId()));
		}
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
		detachedCriteria.addOrder(Order.desc("reportDate"));
		detachedCriteria.add(Restrictions.eq("removed", false));
		
		if("1".equals(type)){
			CodeInfo director =dictableService.getCodeInfoByCode(FormationControl.DIRECTOR, "GBT_12407_2008");//正处
			CodeInfo deputyDirector =dictableService.getCodeInfoByCode(FormationControl.DEPUTY_DIRECTOR, "GBT_12407_2008");//副处
			detachedCriteria.add(Restrictions.not(Restrictions.in("nowJobLevel", director,deputyDirector)));//不能处理处级人员的数据
		}
		Page<ServantVO> pageInfo = servantService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: oldPageList
	 * @Description: 历史人员信息列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查询历史人员信息列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/oldPageList")
	public Page<ServantVO> oldPageList(Servant servant, Integer limit, Integer page) {
		
		String organNodeId = this.getRequest().getParameter("organNodeIdOld");
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		
		if (StringUtils.isBlank(organNodeId)) {
			if (organNode.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
				// 如果是长宁人事局，可查看长宁下所有单位
				organNodeId = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE).getId();
			} else {
				organNodeId = organNode.getId();
			}
		}
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
		// 查询本节点下所有单位
		List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(organNodeId);
		List<String> departList = new ArrayList<>();
		for (OrganNode org : orgNodeList) {
			departList.add(org.getId());
		}
		detachedCriteria.add(Restrictions.in("departId", departList));
		
		if (StringUtils.isNotBlank(servant.getName())) {
			detachedCriteria.add(Restrictions.like("name", servant.getName(), MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(servant.getCardNo())) {// 身份证
			detachedCriteria.add(Restrictions.eq("cardNo", servant.getCardNo()));
		}
		if (servant.getSex() != null && StringUtils.isNotBlank(servant.getSex().getId())) {// 性别
			detachedCriteria.add(Restrictions.eq("sex.id", servant.getSex().getId()));
		}
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", "DM200");// 在职CODE
		CodeInfo probation = dictableService.getCodeInfoByCode("6", DictTypeCodeContant.CODE_HUMAN_STATUS);// 试用期人员CODE
		detachedCriteria.add(Restrictions.not(Restrictions.in("isOnHold.id", isOnHold.getId(),probation.getId())));
		detachedCriteria.addOrder(Order.desc("reportDate"));
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<ServantVO> pageInfo = servantService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: saveServant
	 * @Description: 人员基本信息保存功能
	 * @param servant
	 * @return: AjaxResult
	 */
	@Log(title = "编辑人员基本信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveServant")
	public AjaxResult saveServant(Servant temp, String photostr) {
		
		// 保存头像
		temp.setPhotoPath(ImgPicUtil.savePic(photostr));
		
		AjaxResult result = new AjaxResult(true);
		try {
			Servant servant = servantService.get(temp.getId());
			BeanUtils.copyPropertiesIgnoreNull(temp, servant);
			DictUtils.operationCodeInfo(servant);// 将CodeInfo中id为空的属性 设置为null
			servantService.saveOrUpdate(servant);// 保存
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: ofcMain
	 * @Description: 公务员登记表
	 * @param servantId
	 * @return: String
	 */
	@Log(title = "查询公务员登记表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/main")
	public String ofcMain(String id, Model model) {
		
		Servant servant = servantService.get(id);
		// System.out.println(id);
		model.addAttribute("id", id);
		model.addAttribute("servant", servant);
		installInfoList(id, model);
		return VIEW_OFC_MAIN;
	}
	
	/**
	 * @Title: ofcMain
	 * @Description: 公务员登记表（展示）
	 * @param servantId
	 * @return: String
	 */
	@RequestMapping("/main/view")
	public String ofcMainView(String id, Model model) {
		
		Servant servant = servantService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("servant", servant);
		installInfoList(id, model);
		return VIEW_OFC_MAIN_VIEW;
	}
	
	/**
	 * @Title: servantEdit
	 * @Description: 公务员基本信息集
	 * @param servantId
	 * @return: String
	 */
	@Log(title = "进入人员基本信息编辑页面", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/servant_edit")
	public String servantEdit(String id, Model model) {
		
		Servant servant = servantService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("servant", servant);
		return VIEW_OFC_MAIN_EIDT;
	}
	
	/**
	 * @Title: servantEdit
	 * @Description: 公务员基本信息集（信息展示）
	 * @param servantId
	 * @return: String
	 */
	@RequestMapping("/servant_edit/view")
	public String servantEditView(String id, Model model) {
		
		Servant servant = servantService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("servant", servant);
		return VIEW_OFC_MAIN_EIDT_VIEW;
	}
	
	/**
	 * @Title: extendList
	 * @Description:其他子集
	 * @param id
	 * @return: String
	 */
	@Log(title = "查询其他子集信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("extend_list")
	public String extendList(String id, Model model) {
		
		model.addAttribute("id", id);
		return VIEW_OFC_EXTEND_LIST;
	}
	
	/**
	 * @Title: extendList
	 * @Description:其他子集（信息展示）
	 * @param id
	 * @return: String
	 */
	@RequestMapping("extend_list/view")
	public String extendListView(String id, Model model) {
		
		model.addAttribute("id", id);
		return VIEW_OFC_EXTEND_LIST_VIEW;
	}
	
	/**
	 * @Title: installInfoList
	 * @Description: 组装登记表显示字段
	 * @param servantId
	 * @param model
	 * @return: void
	 */
	private void installInfoList(String servantId, Model model) {
		
		// 查询简历集合（按照起始时间倒序）
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Experience.class);
		// DetachedCriteria criteria = detachedCriteria.createAlias("servant", "s");
		// criteria.setFetchMode("servant", FetchMode.JOIN);
		detachedCriteria.add(Restrictions.eq("servant.id", servantId));
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.addOrder(Order.asc("startDate"));
		detachedCriteria.addOrder(Order.asc("endDate"));
		List<Experience> experienceList = experienceService.findByCriteria(detachedCriteria);
		// 拼装简历信息
		List<String> experienceInfos = new ArrayList<>();
		if (!experienceList.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			for (Experience e : experienceList) {
				StringBuilder info = new StringBuilder();
				info.append(sdf.format(e.getStartDate()) + "~");
				if (e.getEndDate() == null) {
					info.append("至今");
				} else {
					info.append(sdf.format(e.getEndDate()));
				}
				info.append("&nbsp;&nbsp;");
				info.append(e.getFormerUnit());
				info.append("&nbsp;&nbsp;");
				info.append(StringUtils.isNotBlank(e.getFormerJob()) ? e.getFormerJob() : "");
				experienceInfos.add(info.toString());
			}
		} else {
			experienceInfos.add("无");
		}
		model.addAttribute("experienceInfos", experienceInfos);
		
		// 查询奖励处分集合（按照起始时间倒序）
		
		detachedCriteria = DetachedCriteria.forClass(RewardAndPunish.class);
		detachedCriteria.add(Restrictions.eq("servant.id", servantId));
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("type", RewardAndPunish.TYPE_OF_REWARD));
		detachedCriteria.addOrder(Order.asc("rewardApprovalDate"));
		
		List<RewardAndPunish> rewardList = rewardAndPunishService.findByCriteria(detachedCriteria);
		
		detachedCriteria = DetachedCriteria.forClass(RewardAndPunish.class);
		detachedCriteria.add(Restrictions.eq("servant.id", servantId));
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("type", RewardAndPunish.TYPE_OF_PUNISH));
		detachedCriteria.addOrder(Order.asc("punishApprovalDate"));
		
		List<RewardAndPunish> punishList = rewardAndPunishService.findByCriteria(detachedCriteria);
		
		// 拼装奖惩信息
		List<String> rewardAndPunishInfos = new ArrayList<>();
		if (!rewardList.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (RewardAndPunish r : rewardList) {
				StringBuilder info = new StringBuilder();
				if (r.getRewardApprovalDate() != null) {
					info.append("在&nbsp;&nbsp;" + sdf.format(r.getRewardApprovalDate()) + "&nbsp;&nbsp;");
				}
				info.append("获得");
				info.append("&nbsp;&nbsp;&nbsp;");
				info.append(
						StringUtils.isNotBlank(r.getHonoraryName()) ? r.getHonoraryName() + "&nbsp;&nbsp;&nbsp;" : "");
				info.append(r.getRewardName());
				rewardAndPunishInfos.add(info.toString());
			}
		}
		
		if (!punishList.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (RewardAndPunish r : punishList) {
				StringBuilder info = new StringBuilder();
				info.append("在&nbsp;&nbsp;" + sdf.format(r.getPunishApprovalDate()));
				info.append("&nbsp;&nbsp;接受");
				info.append("&nbsp;&nbsp;&nbsp;");
				info.append(r.getPunishName());
				rewardAndPunishInfos.add(info.toString());
			}
		}
		
		model.addAttribute("rewardAndPunishInfos", rewardAndPunishInfos);
	}
	
	@RequestMapping("/validate")
	public String validateServantBasicInfo(Model model) {
		
		model.addAttribute("organTreeId", organizationService.getDefaultOrganTree().getId());
		return VIEW_OFC_VALIDATE;
	}
	
	@RequestMapping("/query/basic/info")
	@ResponseBody
	public Page<ServantBasicInfo> queryServantBasicInfo(String organNodeId, Integer limit, Integer page) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ServantBasicInfo.class);
		if (StringUtils.isNotBlank(organNodeId)) {
			detachedCriteria.add(Restrictions.eq("departId", organNodeId));
		}
		return servantBasicInfoService.findByCriteria(detachedCriteria, page, limit);
	}
	
	/**
	 * @Title: pageList
	 * @Description: 在职人员信息列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<ServantVO>
	 */
	@ResponseBody
	@RequestMapping("/pageListByNodeTree")
	public Page<ServantVO> pageListByNodeTree(Servant servant, Integer limit, Integer page) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
		if (StringUtils.isNotBlank(servant.getDepartId())) {// 单位下人员
			// 查询本节点下所有单位
			List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(servant.getDepartId());
			List<String> departList = new ArrayList<>();
			for (OrganNode org : orgNodeList) {
				departList.add(org.getId());
			}
			detachedCriteria.add(Restrictions.in("departId", departList));
		}
		if (StringUtils.isNotBlank(servant.getName())) {
			detachedCriteria.add(Restrictions.like("name", servant.getName(), MatchMode.ANYWHERE));
			
		}
		if (StringUtils.isNotBlank(servant.getCardNo())) {// 身份证
			detachedCriteria.add(Restrictions.like("cardNo", servant.getCardNo(), MatchMode.ANYWHERE));
		}
		if (servant.getSex() != null && StringUtils.isNotBlank(servant.getSex().getId())) {// 性别
			detachedCriteria.add(Restrictions.eq("sex.id", servant.getSex().getId()));
		}
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
		detachedCriteria.addOrder(Order.desc("reportDate"));
		detachedCriteria.add(Restrictions.eq("removed", false));
		Page<ServantVO> pageInfo = servantService.getPage(detachedCriteria, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 在职人员信息列表根据传过来的人员id返回带复选框的数据
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @param	type  1 人社局查看所有人员
	 * @param	busClass  排除业务数据实体类路径  示例：com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto
	 * @param	busCol  排除业务数据字段名	   示例：zhuanRenTLBIntoBatch.id
	 * @param	busId  排除业务数据ID值
	 * @param	busParentClass  排除业务数据批次状态为-1（业务中止）的  示例：ZhuanRenTLBInto类中批次变量zhuanRenTLBOutBatch
	 * @return: Page<ServantVO>
	 */
	@ResponseBody
	@RequestMapping("/pageListWithCheckBox")
	public Page<ServantVO> pageListWithCheckBox(Servant servant, Integer limit, Integer page,String ids,String type,String busClass,String busCol,String busId,String busParentClass) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
		OrganNode organNode = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		if("1".equals(type)){
			if (StringUtils.isNotBlank(servant.getDepartId())) {// 单位下人员
				String organNodeId = "";
				if (organNode.getCode().equals(CommonConst.HR_ROOT_ORGAN_CODE)) {
					// 如果是长宁人事局，可查看长宁下所有单位
					organNodeId = organNodeService.loadOrganNodeByCode(CommonConst.ROOT_ORGAN_CODE).getId();
				} else {
					organNodeId = servant.getDepartId();
				}
				// 查询本节点下所有单位
				List<OrganNode> orgNodeList = organizationService.getAllChildOrganNode(organNodeId);
				List<String> departList = new ArrayList<>();
				for (OrganNode org : orgNodeList) {
					departList.add(org.getId());
				}
				detachedCriteria.add(Restrictions.in("departId", departList));
			}
		}else{
			detachedCriteria.add(Restrictions.eq("departId", servant.getDepartId()));
		}
		if (StringUtils.isNotBlank(servant.getName())) {
			detachedCriteria.add(Restrictions.like("name", servant.getName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(servant.getCardNo())) {// 身份证
			detachedCriteria.add(Restrictions.eq("cardNo", servant.getCardNo()));
		}
		if (servant.getSex() != null && StringUtils.isNotBlank(servant.getSex().getId())) {// 性别
			detachedCriteria.add(Restrictions.eq("sex.id", servant.getSex().getId()));
		}
		CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		detachedCriteria.add(Restrictions.eq("isOnHold.id", isOnHold.getId()));
		detachedCriteria.addOrder(Order.desc("reportDate"));
		detachedCriteria.add(Restrictions.eq("removed", false));
		
		CodeInfo director =dictableService.getCodeInfoByCode(FormationControl.DIRECTOR, "GBT_12407_2008");//正处
		CodeInfo deputyDirector =dictableService.getCodeInfoByCode(FormationControl.DEPUTY_DIRECTOR, "GBT_12407_2008");//副处
		detachedCriteria.add(Restrictions.not(Restrictions.in("nowJobLevel", director,deputyDirector)));//不能处理处级人员的数据
		
		if(StringUtils.isNoneBlank(busClass)){//排除业务表中数据
			DetachedCriteria dc = DetachedCriteria.forEntityName(busClass);
			//添加TrainPeople实体的查询条件
			if(StringUtils.isNotBlank(busCol)){
				dc.add(Restrictions.eq(busCol, busId));
			}
			if(StringUtils.isNotBlank(busParentClass)){
				DetachedCriteria t = dc.createAlias(busParentClass, "t");
				t.add(Restrictions.ne("t.status", FlowRecord.BUS_STOP));
			}
			dc.add(Restrictions.eq("removed", false));
			dc.add(Restrictions.isNotNull("servant.id"));
			//设置这个表的查询结果。注意servant.id为实体映射文件中对应的字段，不是数据库中的列名
			dc.setProjection(Property.forName("servant.id"));
			//关联两个实体的关系
			detachedCriteria.add(Property.forName("id").notIn(dc));
		}
		Page<ServantVO> pageInfo = servantService.getPage(detachedCriteria,page,limit,ids);
		return pageInfo;
	}
}
