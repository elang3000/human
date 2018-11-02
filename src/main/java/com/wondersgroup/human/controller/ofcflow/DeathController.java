package com.wondersgroup.human.controller.ofcflow;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DeathServant;
import com.wondersgroup.human.dto.ofcflow.DeathServantQueryParam;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DeathServantService;
import com.wondersgroup.human.vo.ofcflow.DeathVO;

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

	// 页面路径--人员列表
	private final String DEATH_INDEX = "models/ofcflow/death/index";

	// 页面路径--死亡人员填写
	private final String DEATH = "models/ofcflow/death/death";
	
	// 页面路径--死亡人员流程页面
	private final String DEATH_EMPLOYPLAN_FLOW = "models/ofcflow/death/flow";
	
	// 页面路径--死亡人员流程页面
	private final String DEATH_FLOW = "models/ofcflow/death/deathFlow";
	
	// 页面路径--死亡人员流程页面
	private final String DEATH_LIST = "models/ofcflow/death/deathList";

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
	@RequestMapping("/index")
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
	@RequestMapping("/death")
	public String death(String servantId, Model model) {

		Servant servant = servantService.get(servantId);
		model.addAttribute("id", servantId);
		model.addAttribute("servant", servant);
		return DEATH;
	}

	/**
	 * @Title: save
	 * @Description: 辞职保存
	 * @param temp辞职信息
	 * @return: AjaxResult
	 */
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
	 * @Title: resignFlow 
	 * @Description: 审批详情页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("/deathFlow")
	public String resignFlow(Model model,String id) {
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
	 * @Description: 死亡人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/list")
	public String deathList() {
		return DEATH_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 辞职申请列表
	 * @param params查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ResignVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<DeathVO> pageList(DeathServantQueryParam param, Integer limit, Integer page) {
		Page<DeathVO> pageInfo = deathServantService.pageList(param, page, limit);
		return pageInfo;
	}
	
	/**
	 * 辞职人员详情
	 * 
	 * @Title: ResignDetail
	 * @Description: 辞职人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/deathDetail")
	public String resignDetail(String id, Model model) {
		DeathServant d = deathServantService.get(id);
		Servant servant = servantService.get(d.getServant().getId());
		model.addAttribute("servant", servant);
		model.addAttribute("deathServant", d);
		return DEATH_DETAIL;
	}
}
