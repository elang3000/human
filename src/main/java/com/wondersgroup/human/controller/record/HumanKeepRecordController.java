/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: HumanKeepRecordVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.record
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月29日 下午5:17:20
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月29日 下午5:17:20
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.record.HumanKeepRecordParam;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.record.HumanKeepRecordService;
import com.wondersgroup.human.vo.record.KeepRecordVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * 备案记录控制器
 * 
 * @ClassName: HumanKeepRecordController
 * @Description: 备案记录控制器
 * @author: GP
 * @date: 2018年5月24日下午3:06:54 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@Controller
@RequestMapping("/keep")
public class HumanKeepRecordController extends GenericController {
	// 跳转路径
	/**
	 * 备案记录列表
	 */

	private final String KEEP_RECORD_LIST = "models/record/humanKeepRecordList";

	/**
	 * 备案记录详情
	 */

	private final String KEEP_RECORD_DETAIL = "models/record/humanKeepRecordDetail";

	@Autowired
	private HumanKeepRecordService humanKeepRecordService;

	@Autowired
	private ServantService servantService;

	/**
	 * @Title: ofclist
	 * @Description: 备案信息列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/record")
	public String keepRecordList() {
		return KEEP_RECORD_LIST;
	}

	/**
	 * @Title: detail
	 * @Description: 备案详情
	 * @param id
	 * @return: String
	 */
	@Log(title = "备案管理详情", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/detail")
	public String keepRecordDetail(String id, Model model) {
		HumanKeepRecord humanKeepRecord = humanKeepRecordService.get(id);
		Servant servant = servantService.get(humanKeepRecord.getServant().getId());
		model.addAttribute("id", id);
		model.addAttribute("servant", servant);
		model.addAttribute("humanKeepRecord", humanKeepRecord);
		return KEEP_RECORD_DETAIL;
	}
	
	/**
	 * @Title: query
	 * @Description: 备案列表
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<HumanKeepRecordVO>
	 */
	@Log(title = "备案管理列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/getPage")
	public Page<KeepRecordVO> getPage(HumanKeepRecordParam param, Integer limit,
			Integer page) {

		return humanKeepRecordService.getPage(param, page, limit);
	}
}
