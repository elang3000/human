package com.wondersgroup.human.controller.ofcflow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DeathServant;
import com.wondersgroup.human.bo.ofcflow.RetireServant;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.dto.ofcflow.RetireServantQueryParam;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerOutRecordEvent;
import com.wondersgroup.human.event.record.ServantHumamKeepRecordEvent;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.RetireServantService;
import com.wondersgroup.human.util.EventManager;
import com.wondersgroup.human.vo.ofc.ServantVO;
import com.wondersgroup.human.vo.ofcflow.RetireServantVO;
import com.wondersgroup.human.vo.record.HumanKeepRecordVO;

/**
 * 退休控制类
 * 
 * @ClassName: RetireController
 * @Description: 退休控制器
 * @author: GP
 * @date: 2018年5月18日下午3:31:12 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("ofcflow/retire")
public class RetireController extends GenericController {

	@Autowired
	RetireServantService retireServantService;

	@Autowired
	ServantService servantService;

	@Autowired
	PostService postService;

	@Autowired
	DictableService dictableService;
	// 页面路径--退休人员列表
	private final String RETIRE_LIST = "models/ofcflow/retire/retireList";

	// 页面路径--退休人员详情
	private final String RETIRE_DETAIL = "models/ofcflow/retire/retireDetail";
	
	// 页面路径--退休人员信息信息填写
	private final String APPLY_RETIRE = "models/ofcflow/retire/applyRetire";
	
	// 人员不再任CODE
	private final String IS_NOT_ON_HOLD = "030020de-bb61-42c7-ad39-001933daed3d";

	/**
	 * 
	 * @Title: RetireList
	 * @Description: 退休人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/retireList")
	public String RetireList() {

		return RETIRE_LIST;
	}

	/**
	 * 
	 * @Title: RetireDetail
	 * @Description: 退休人员信息详细页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/retireDetail")
	public String RetireDetail(String servantId,Model model) {
		Servant servant = servantService.get(servantId);
		model.addAttribute("id", servantId);
		model.addAttribute("servant", servant);
		return RETIRE_DETAIL;
	}
	
	/**
	 * 
	 * @Title: RetireDetail
	 * @Description: 退休人员填写页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/applyRetire")
	public String applyRetire(String ids,Model model) {
		model.addAttribute("ids", ids);
		return APPLY_RETIRE;
	}
	
	/**
	 * @Title: save
	 * @Description: 退休保存
	 * @param temp死亡务信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(RetireServant temp, String ids) {
		AjaxResult result = new AjaxResult(true);
		try {
			DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
			String[] servantId = ids.split(",");
			for(String s : servantId){
				temp.setHumanId(s.toString());
				retireServantService.save(temp);
				Servant servant = servantService.get(s);
				CodeInfo isOnHoldCodeInfo = dictableService.loadCodeInfoById(IS_NOT_ON_HOLD);
				servant.setIsOnHold(isOnHoldCodeInfo);// 设置为不在任
				servantService.update(servant);
				
				//保存进出管理
//				ManagerRecordDTO mrdto = new ManagerRecordDTO();
//				mrdto.setHumanId(s);
//				mrdto.setRecordType(HumanKeepRecord.SERVANT_RETIRE);//退休备案
//				ManagerOutRecordEvent event = new ManagerOutRecordEvent(mrdto);
//				EventManager.send(event);
//				
//				//保存备案管理
//				HumankeepRecordDTO hkrdto = new HumankeepRecordDTO();
//				hkrdto.setHumanId(s);
//				hkrdto.setRecordType(HumanKeepRecord.SERVANT_RETIRE);//退休备案
//				hkrdto.setUnitType(servant.getDepartName());//人事单位类型
//				ServantHumamKeepRecordEvent event2 = new ServantHumamKeepRecordEvent(hkrdto);
//				EventManager.send(event2);
				result.setMessage("保存成功！");
			}
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}

	/**
	 * @Title: query
	 * @Description: 退休列表列表
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<ServantVO>
	 */
	@ResponseBody
	@RequestMapping("/query")
	public Page<RetireServantVO> queryRetireServant(RetireServantQueryParam param, Integer limit,
			Integer page) {

		Map<String, Object> filter = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(param.getName())) {// 姓名
			filter.put("name", "%" + param.getName() + "%");
		}
		if (StringUtils.isNotBlank(param.getCardNo())) {// 身份证
			filter.put("cardNo", "%" + param.getCardNo() + "%");
		}
		if (param.getSex()!=null&&StringUtils.isNotBlank(param.getSex().getId())) {// 备案类型
			filter.put("sex", param.getSex().getId());
		}
		if (param.getAge()!=null&&!"".equals(param.getAge())) {// 年龄
			filter.put("age",param.getAge());
		}
		return retireServantService.queryRetireServant(filter, page, limit);
	}
}
