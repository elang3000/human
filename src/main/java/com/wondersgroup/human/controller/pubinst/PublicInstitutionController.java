package com.wondersgroup.human.controller.pubinst;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.organization.service.OrganizationService;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.pubinst.PtExperience;
import com.wondersgroup.human.bo.pubinst.PtRewardAndPunish;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtExperienceService;
import com.wondersgroup.human.service.pubinst.PtRewardAndPunishService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.util.ImgPicUtil;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * 
 * @ClassName: PublicInstitutionController
 * @Description:事业单位人员管理控制器
 * @author: lyf
 * @date: 2018年8月20日 下午4:08:59
 * @version [版本号, YYYY-MM-DD]  @see       [相关类/方法] @since     [产品/
 *                            模块版本] @Copyright: 2018 万达信息股份有限公司 Inc. All rights
 *                            reserved.
 */
@Controller
@RequestMapping("/publicInstitution")
public class PublicInstitutionController extends GenericController {

	// 跳转路径
	/**
	 * 在职人员列表
	 */
	private static final String VIEW_INSTITUTION_LIST = "models/publicInstitution/publicInstitutionList";
	/**
	 * 其它人员列表
	 */
	private static final String VIEW_INSTITUTION_LIST_Other = "models/publicInstitution/publicInstitutionListOther";

	/**
	 * 登记表
	 */
	private static final String VIEW_INSTITUTION_Public = "models/publicInstitution/pubinstMain";
	private static final String VIEW_INSTITUTION_VIEW_Public = "models/publicInstitution/pubinstMainView";

	/**
	 * 基本信息集
	 */
	private static final String VIEW_INSTITUTION_EIDT_Public = "models/publicInstitution/pubinstMainEdit";// 信息展示表
	private static final String VIEW_INSTITUTION_EIDT_Personal = "models/publicInstitution/pubinstMainEditAdd";// 信息编辑表
	private static final String VIEW_INSTITUTION_EIDT_VIEW_Public = "models/publicInstitution/pubinstMainEditView";

	/**
	 * 其他子集
	 */
	private static final String VIEW_INSTITUTION_EXTEND_LIST_Public = "models/publicInstitution/pubinstExtendList";
	private static final String VIEW_INSTITUTION_EXTEND_LIST_VIEW_Public = "models/publicInstitution/pubinstExtendListView";

	@Autowired
	private PublicInstitutionService publicInstitutionService;

	@Autowired
	private UserService userService;

	@Autowired
	private DictableService dictableService;

	@Autowired
	private OrganizationService organizationService;

	// 简历
	@Autowired
	private PtExperienceService experienceService;

	@Autowired
	private PtRewardAndPunishService rewardAndPunishService;

	/**
	 * @Title: causelist
	 * @Description: 事业人员信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/list")
	public String publicInstitutionlist() {
		return VIEW_INSTITUTION_LIST;
	}

	/**
	 * @Title:
	 * @Description: 事业单位人员登记表
	 * @param Id
	 * @return: String
	 */
	@RequestMapping("/main")
	public String PubMain(String id, Model model) {

		PublicInstitution institution = publicInstitutionService.get(id);
		// 如果最高学历或者学位不为空再做判断
		if (StringUtils.isNotBlank(institution.getTopEducation())) {
			CodeInfo eduinfo = dictableService.loadCodeInfoById(institution.getTopEducation());
			institution.setTopEducation(eduinfo.getName());
		}
		if (StringUtils.isNotBlank(institution.getTopDegree())) {
			CodeInfo greeinfo = dictableService.loadCodeInfoById(institution.getTopDegree());
			institution.setTopDegree(greeinfo.getName());
		}

		model.addAttribute("id", id);
		model.addAttribute("pubinst", institution);
		installInfoList(id, model);
		return VIEW_INSTITUTION_Public;
	}

	/**
	 * @Title: listother
	 * @Description: 事业人员其它人员信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/listOther")
	public String publicInstitutionlistOther() {
		return VIEW_INSTITUTION_LIST_Other;
	}

	/**
	 * @Title: pageList
	 * @Description: 本单位在职人员信息列表
	 * @param servant
	 *            查询条件
	 * @param limit
	 *            页大小
	 * @param page
	 *            页码
	 * @return: Page<PublicInstitutionVO>
	 * @throws Exception
	 */
	@Log(title = "查询在职人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY, isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PublicInstitutionVO> pageList(PublicInstitution publicInstitution, Integer limit, Integer page)
			throws Exception {
		//// System.out.println(SecurityUtils.getUserId());
		// SecurityUser user =
		//// userService.load(SecurityUtils.getUserId());//当前登录人
		OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Sorts sort = new Sorts();// 排序规则
		sort.add("reportDate", false);// 降序 默认为按照某一个属性的升序。即属性为true

		/**
		 * 
		 * 本单位下的所有人员
		 */
		if (organ != null && StringUtils.isNotBlank(organ.getId())) {// 单位下人员
			Predicate p = new Predicate("departId", Operator.EQ, organ.getId(), "");
			filter.add(p);
		}

		// 所有在职人员信息
		CodeInfo codeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		if (StringUtils.isNotBlank(codeInfo.getId())) {// 姓名
			Predicate p = new Predicate("isOnHold.id", Operator.EQ, codeInfo.getId(), "");
			filter.add(p);
		}
		String name = publicInstitution.getName();
		if (StringUtils.isNotBlank(name)) {// 姓名
			Predicate p = new Predicate("name", Operator.LIKE, name, "");
			filter.add(p);
		}
		if (StringUtils.isNotBlank(publicInstitution.getCardNo())) {// 身份证
			Predicate p = new Predicate("cardNo", Operator.LIKE, publicInstitution.getCardNo().trim(), "");
			filter.add(p);
		}
		if (publicInstitution.getSex() != null && StringUtils.isNotBlank(publicInstitution.getSex().getId())) {// 性别
			Predicate p = new Predicate("sex.id", Operator.EQ, publicInstitution.getSex().getId(), "");
			filter.add(p);
		}

		Page<PublicInstitutionVO> pageInfo = publicInstitutionService.getPage(filter, sort, page, limit);
		return pageInfo;
	}

	/**
	 * @Title: pageAllList
	 * @Description: 在职人员信息列表
	 * @param servant
	 *            查询条件
	 * @param limit
	 *            页大小
	 * @param page
	 *            页码
	 * @return: Page<PublicInstitutionVO>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/pageAllList")
	public Page<PublicInstitutionVO> pageAllList(PublicInstitution publicInstitution, Integer limit, Integer page,
			HttpServletRequest request) throws Exception {
		//// System.out.println(SecurityUtils.getUserId());
		// SecurityUser user =
		//// userService.load(SecurityUtils.getUserId());//当前登录人
		String conditionquery = request.getParameter("conditionquery");

		OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Sorts sort = new Sorts();// 排序规则
		sort.add("reportDate", false);// 降序 默认为按照某一个属性的升序。即属性为true

		if (conditionquery == null) { // conditionquery=1标识点击搜索过来的
			if (organ != null && StringUtils.isNotBlank(organ.getId())) {// 单位下人员
				Predicate p = new Predicate("departId", Operator.EQ, organ.getId(), "");
				filter.add(p);
			}
		}
		// 所有在职人员信息
		CodeInfo codeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		if (StringUtils.isNotBlank(codeInfo.getId())) {// 姓名
			Predicate p = new Predicate("isOnHold.id", Operator.EQ, codeInfo.getId(), "");
			filter.add(p);
		}
		String name = publicInstitution.getName();
		if (StringUtils.isNotBlank(name)) {// 姓名
			Predicate p = new Predicate("name", Operator.LIKE, name, "");
			filter.add(p);
		}
		if (StringUtils.isNotBlank(publicInstitution.getCardNo())) {// 身份证
			Predicate p = new Predicate("cardNo", Operator.LIKE, publicInstitution.getCardNo().trim(), "");
			filter.add(p);
		}
		if (publicInstitution.getSex() != null && StringUtils.isNotBlank(publicInstitution.getSex().getId())) {// 性别
			Predicate p = new Predicate("sex.id", Operator.EQ, publicInstitution.getSex().getId(), "");
			filter.add(p);
		}

		Page<PublicInstitutionVO> pageInfo = publicInstitutionService.getPage(filter, sort, page, limit);
		return pageInfo;
	}

	/**
	 * @Title: pageList
	 * @Description: 在职人员信息列表
	 * @param servant
	 *            查询条件
	 * @param limit
	 *            页大小
	 * @param page
	 *            页码
	 * @return: Page<PublicInstitutionVO>
	 * @throws Exception
	 */
	@Log(title = "应用查询", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY, isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageListOther")
	public Page<PublicInstitutionVO> pageListOther(PublicInstitution publicInstitution, Integer limit, Integer page)
			throws Exception {
		OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Sorts sort = new Sorts();// 排序规则
		sort.add("reportDate", false);// 降序 默认为按照某一个属性的升序。即属性为true
		/**
		 * 
		 * 本单位下的所有人员
		 */
		if (organ != null && StringUtils.isNotBlank(organ.getId())) {// 单位下人员
			Predicate p = new Predicate("departId", Operator.EQ, organ.getId(), "");
			filter.add(p);
		}

		CodeInfo codeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_HUMAN_STATUS);// 在职CODE
		if (StringUtils.isNotBlank(codeInfo.getId())) {// 姓名
			Predicate p = new Predicate("isOnHold.id", Operator.NE, codeInfo.getId(), "");
			filter.add(p);
		}
		String name = publicInstitution.getName();
		if (StringUtils.isNotBlank(name)) {// 姓名
			Predicate p = new Predicate("name", Operator.LIKE, name, "");
			filter.add(p);
		}
		if (StringUtils.isNotBlank(publicInstitution.getCardNo())) {// 身份证
			Predicate p = new Predicate("cardNo", Operator.LIKE, publicInstitution.getCardNo().trim(), "");
			filter.add(p);
		}
		if (publicInstitution.getSex() != null && StringUtils.isNotBlank(publicInstitution.getSex().getId())) {// 性别
			Predicate p = new Predicate("sex.id", Operator.EQ, publicInstitution.getSex().getId(), "");
			filter.add(p);
		}
		Page<PublicInstitutionVO> pageInfo = publicInstitutionService.getPage(filter, sort, page, limit);
		return pageInfo;
	}

	/**
	 * 
	 * @Title: installInfoList
	 * @Description: 组装登记表显示字段
	 * @param Id
	 * @param model
	 * @return: void
	 */
	private void installInfoList(String Id, Model model) {

		// 查询简历集合（按照起始时间倒序）
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PtExperience.class);
		// DetachedCriteria criteria = detachedCriteria.createAlias("servant",
		// "s");
		// criteria.setFetchMode("servant", FetchMode.JOIN);
		detachedCriteria.add(Restrictions.eq("publicInstitution.id", Id));
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.addOrder(Order.asc("startDate"));
		List<PtExperience> experienceList = experienceService.findByCriteria(detachedCriteria);
		// 拼装简历信息
		List<String> experienceInfos = new ArrayList<>();
		if (!experienceList.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			for (PtExperience e : experienceList) {
				StringBuilder info = new StringBuilder();
				if (e.getStartDate() != null) {
					info.append(sdf.format(e.getStartDate()) + "~");
				} else {
					info.append("~");
				}
				if (e.getEndDate() == null) {
					info.append("至今");
				} else {
					info.append(sdf.format(e.getEndDate()));
				}
				info.append("&nbsp;&nbsp;");
				info.append(e.getFormerUnit());
				info.append("&nbsp;&nbsp;");
				info.append(e.getFormerJob());
				experienceInfos.add(info.toString());
			}
		} else {
			experienceInfos.add("无");
		}
		model.addAttribute("experienceInfos", experienceInfos);

		// 查询奖励处分集合（按照起始时间倒序）

		detachedCriteria = DetachedCriteria.forClass(PtRewardAndPunish.class);
		detachedCriteria.add(Restrictions.eq("publicInstitution.id", Id));
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("type", RewardAndPunish.TYPE_OF_REWARD));
		detachedCriteria.addOrder(Order.desc("rewardApprovalDate"));

		List<PtRewardAndPunish> rewardList = rewardAndPunishService.findByCriteria(detachedCriteria);

		detachedCriteria = DetachedCriteria.forClass(PtRewardAndPunish.class);
		detachedCriteria.add(Restrictions.eq("publicInstitution.id", Id));
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.add(Restrictions.eq("type", RewardAndPunish.TYPE_OF_PUNISH));
		detachedCriteria.addOrder(Order.desc("punishApprovalDate"));

		List<PtRewardAndPunish> punishList = rewardAndPunishService.findByCriteria(detachedCriteria);

		// 拼装奖惩信息
		List<String> rewardAndPunishInfos = new ArrayList<>();
		if (!rewardList.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (PtRewardAndPunish r : rewardList) {
				StringBuilder info = new StringBuilder();
				info.append("在&nbsp;&nbsp;" + sdf.format(r.getRewardApprovalDate()));
				info.append("&nbsp;&nbsp;获得");
				info.append("&nbsp;&nbsp;&nbsp;");
				info.append(r.getCategory().getName());
				info.append("&nbsp;&nbsp;&nbsp;");
				info.append(r.getRewardName());
				rewardAndPunishInfos.add(info.toString());
			}
		}

		if (!rewardList.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (PtRewardAndPunish r : punishList) {
				StringBuilder info = new StringBuilder();
				info.append("在&nbsp;&nbsp;" + sdf.format(r.getPunishApprovalDate()));
				info.append("&nbsp;&nbsp;接受");
				info.append("&nbsp;&nbsp;&nbsp;");
				info.append(r.getCategory().getName());
				info.append("&nbsp;&nbsp;");
				info.append(r.getPunishName());
				rewardAndPunishInfos.add(info.toString());
			}
		}

		model.addAttribute("rewardAndPunishInfos", rewardAndPunishInfos);
	}

	/**
	 * @Title: servantEdit
	 * @Description: 基本信息集
	 * @param servantId
	 * @return: String
	 */
	@Log(title = "编辑基本信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE, isSaveRequestData = true)
	@RequestMapping("/edit")
	public String servantEdit(String id, Model model) {

		PublicInstitution publicInstitution = publicInstitutionService.get(id);
		// 如果最高学历或者学位不为空再做判断
		if (StringUtils.isNotBlank(publicInstitution.getTopEducation())) {
			CodeInfo eduinfo = dictableService.loadCodeInfoById(publicInstitution.getTopEducation());
			publicInstitution.setTopEducation(eduinfo.getName());
		}
		if (StringUtils.isNotBlank(publicInstitution.getTopDegree())) {
			CodeInfo greeinfo = dictableService.loadCodeInfoById(publicInstitution.getTopDegree());
			publicInstitution.setTopDegree(greeinfo.getName());
		}

		model.addAttribute("id", id);
		model.addAttribute("pubinst", publicInstitution);
		return VIEW_INSTITUTION_EIDT_Public;
	}

	/**
	 * @Title: PersonalEdit
	 * @Description: 基本信息编辑
	 * @param id
	 * @return: String
	 */
	@RequestMapping("/editPersonal")
	public String PersonalEdit(String id, Model model) {

		PublicInstitution publicInstitution = publicInstitutionService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("pubinst", publicInstitution);
		return VIEW_INSTITUTION_EIDT_Personal;
	}

	/**
	 * @Title: servantEdit
	 * @Description: 公务员基本信息集（信息展示）
	 * @param servantId
	 * @return: String
	 */
	@Log(title = "更新基本信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE, isSaveRequestData = true)
	@RequestMapping("/servant_edit/view")
	public String servantEditView(String id, Model model) {

		PublicInstitution publicInstitution = publicInstitutionService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("servant", publicInstitution);
		return VIEW_INSTITUTION_EIDT_VIEW_Public;
	}

	/**
	 * @Title: extendList
	 * @Description:其他子集
	 * @param id
	 * @return: String
	 */
	@Log(title = "更新基本信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE, isSaveRequestData = true)
	@RequestMapping("/extendlist")
	public String extendList(String id, Model model) {

		model.addAttribute("id", id);
		return VIEW_INSTITUTION_EXTEND_LIST_Public;
	}

	/**
	 * @Title: extendList
	 * @Description:其他子集（信息展示）
	 * @param id
	 * @return: String
	 */
	@Log(title = "更新基本信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE, isSaveRequestData = true)
	@RequestMapping("extend_list/view")
	public String extendListView(String id, Model model) {

		model.addAttribute("id", id);
		return VIEW_INSTITUTION_EXTEND_LIST_VIEW_Public;
	}

	/**
	 * @Description: 人员基本信息保存功能
	 */
	@Log(title = "编辑人员基本信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE, isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/savePublicInst")
	public AjaxResult savePublicInst(PublicInstitution temp) {

		AjaxResult result = new AjaxResult(true);

		try {
			PublicInstitution publicInstitution = publicInstitutionService.get(temp.getId());
			//// System.out.println(publicInstitution.getGrowPlace().toString());
			BeanUtils.copyPropertiesIgnoreNull(temp, publicInstitution);
			DictUtils.operationCodeInfo(publicInstitution);// 将CodeInfo中id为空的属性
															// 设置为null
			if (StringUtils.isNotBlank(temp.getPhotoPath())) {
				publicInstitution.setPhotoPath(ImgPicUtil.savePic(temp.getPhotoPath()));
			}
			publicInstitutionService.saveOrUpdate(publicInstitution);// 保存
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
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
	 * @param	busParentClass  排除业务数据批次状态为-1（业务中止）的  示例：com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBInto类中批次变量zhuanRenTLBOutBatch
	 * @return: Page<ServantVO>
	 */
	@ResponseBody
	@RequestMapping("/pageListWithCheckBox")
	public Page<PublicInstitutionVO> pageListWithCheckBox(PublicInstitution servant, Integer limit, Integer page,String ids,String busClass,String busCol,String busId,String busParentClass) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PublicInstitution.class);
		detachedCriteria.add(Restrictions.eq("departId", servant.getDepartId()));
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
		Page<PublicInstitutionVO> pageInfo = publicInstitutionService.getPage(detachedCriteria,page,limit,ids);
		return pageInfo;
	}
}
