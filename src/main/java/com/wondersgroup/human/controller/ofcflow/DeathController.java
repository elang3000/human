package com.wondersgroup.human.controller.ofcflow;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
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
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DeathServant;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.dto.ofcflow.DeathServantQueryParam;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DeathServantService;
import com.wondersgroup.human.vo.ofc.ServantVO;
import com.wondersgroup.human.vo.ofcflow.DeathVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * 死亡流程控制类
 * 
 * @ClassName: DeathController
 * @Description: 死亡流程控制类
 * @author: GP
 * @date: 2018年5月15日上午10:12:07 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */

@Controller
@RequestMapping("ofcflow/death")
public class DeathController extends GenericController {

	@Autowired
	DeathServantService deathServantService;

	@Autowired
	ServantService servantService;
	
	@Autowired
	private FlowRecordService flowRecordService;
	
	@Autowired
	private DictableService dictableService;

	// 页面路径--人员列表
	private final String DEATH_INDEX = "models/ofcflow/death/servantList";

	// 页面路径--死亡人员填写
	private final String DEATH = "models/ofcflow/death/death";
	
	// 页面路径--死亡人员流程页面
	private final String DEATH_EMPLOYPLAN_FLOW = "models/ofcflow/employPlan/flow";
	
	// 页面路径--死亡人员流程页面
	private final String DEATH_FLOW = "models/ofcflow/death/deathFlow";
	
	// 页面路径--死亡人员流程页面
	private final String DEATH_LIST = "models/ofcflow/death/index";

	// 页面路径--死亡人员流程页面
	private final String DEATH_DETAIL = "models/ofcflow/death/deathDetail";
		

	/**
	 * 死亡列表
	 * 
	 * @Title: DeathList
	 * @Description: 死亡列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantList")
	public String index(Model model) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		model.addAttribute("oragn", x);
		return DEATH_INDEX;
	}

	/**
	 * 死亡详情
	 * 
	 * @Title: death
	 * @Description: 死亡详情页面
	 * @return
	 * @return: String
	 */
	@Log(title = "新增死亡信息", operatorType = OperatorType.BUSINESS, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@RequestMapping("/death")
	public String death(String servantId, Model model) {

		Servant servant = servantService.get(servantId);
		model.addAttribute("id", servantId);
		model.addAttribute("servant", servant);
		return DEATH;
	}

	/**
	 * @Title: save
	 * @Description: 死亡保存
	 * @param temp死亡信息
	 * @return: AjaxResult
	 */
	@Log(title = "审批死亡流程", operatorType = OperatorType.BUSINESS, businessType = BusinessType.APPROVAL,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/operationFlow")
	public AjaxResult operationFlow(DeathServant temp, HttpServletRequest request) {
		AjaxResult result = new AjaxResult(true);
		String opinion = request.getParameter("opinion");//审批意见
		String r = request.getParameter("result");//审批结果
		try {
			if(StringUtils.isBlank(r)||(!FlowRecord.PASS.equals(r)&&!FlowRecord.NOPASS.equals(r))){
				throw new BusinessException("审批结果信息不正确！");
			}
			if (StringUtils.isBlank(temp.getId())) {
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setId(null);
				deathServantService.saveDeath(temp,opinion,r);
			} else {
				DeathServant deathServant = deathServantService.load(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp,deathServant);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				deathServantService.saveDeath(deathServant,opinion,r);
			}
			result.setSuccess(true);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: flow 
	 * @Description: 流程审批页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/flow")
	public String flow(Model model) {
		model.addAttribute("busType","DeathServant");
		return DEATH_EMPLOYPLAN_FLOW;
	}
	
	
	/**
	 * @Title: DeathFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/deathFlow")
	public String DeathFlow(Model model,String id) {
		FlowRecord flow = flowRecordService.load(id);
		DeathServant d = deathServantService.get(flow.getBusId());
		Servant s = servantService.get(d.getServant().getId());
		model.addAttribute("deathServant", d);
		model.addAttribute("servant", s);
		return DEATH_FLOW;
	}

	/**
	 * 死亡人员列表
	 * 
	 * @Title: deathList
	 * @Description: 死亡人员列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String deathList() {
		return DEATH_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 死亡申请列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<DeathVO>
	 */
	@Log(title = "查询死亡列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<DeathVO> pageList(DeathServantQueryParam param, Integer limit, Integer page) {
		Page<DeathVO> pageInfo = deathServantService.pageList(param, page, limit);
		return pageInfo;
	}
	
	/**
	 * 死亡人员详情
	 * 
	 * @Title: DeathDetail
	 * @Description: 死亡人员列表
	 * @return
	 * @return: String
	 */
	@Log(title = "查看死亡详情", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/deathDetail")
	public String DeathDetail(String id, Model model) {
		DeathServant d = deathServantService.get(id);
		Servant servant = servantService.get(d.getServant().getId());
		model.addAttribute("servant", servant);
		model.addAttribute("deathServant", d);
		return DEATH_DETAIL;
	}
	
	/**
	 */
	@RequestMapping("/validate")
	@ResponseBody
	public AjaxResult valiateExistEmployPlan(Model model, String servantId) {
		AjaxResult result = new AjaxResult(true);
		try {
			DeathServant DeathServant = deathServantService.getByServantId(servantId);
			if(DeathServant!=null){
				throw new BusinessException("该用户已进入流程！");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("发起失败！");
		}
		return result;
	}
	
	/**
	 * @Title: getServantList
	 * @Description: 死亡选择人员列表
	 * @return: Page<ServantVO>
	 */
	@Log(title = "查询人员列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/getServantList")
	public Page<ServantVO> pageListWithCheckBox(Servant servant, Integer limit, Integer page) {
		OrganNode x = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Servant.class);
		detachedCriteria.add(Restrictions.eq("departId", x.getId()));//登录人单位
		
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
		
		DetachedCriteria dc = DetachedCriteria.forClass(DeathServant.class);
		//添加DeathServant实体的查询条件
		dc.add(Restrictions.eq("removed", false));
		//设置这个表的查询结果。注意servant.id为实体映射文件中对应的字段，不是数据库中的列名
		dc.setProjection(Property.forName("servant.id"));
		//关联两个实体的关系
		detachedCriteria.add(Property.forName("id").notIn(dc));
		
		Page<ServantVO> pageInfo = servantService.getPage(detachedCriteria,page,limit);
		return pageInfo;
	}
}
