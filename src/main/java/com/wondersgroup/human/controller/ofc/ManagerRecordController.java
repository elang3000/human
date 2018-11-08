/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ManagerRecordController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc 
 * 描述: TODO
 * 创建人: Administrator   
 * 创建时间: 2018年6月8日 下午2:39:23 
 * 版本号: V1.0
 * 修改人：Administrator 
 * 修改时间：2018年6月8日 下午2:39:23 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.ofc;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.dto.ofc.ManagerRecordParam;
import com.wondersgroup.human.service.ofc.ManagerRecordService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.ItemRecordVO;
import com.wondersgroup.human.vo.ofc.ManagerRecordVO;

/**
 * @ClassName: ManagerRecordController
 * @Description: 人员进出管理控制器
 * @author: lihao
 * @date: 2018年6月8日下午2:39:23 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@RequestMapping("/ofc/managerRecord")
@Controller
public class ManagerRecordController extends GenericController {

	@Autowired
	ManagerRecordService managerRecordService;

	@Autowired
	ServantService servantService;

	// 跳转路径
	/**
	 * 人事管理记录列表
	 */

	private final String MANAGER_RECORD_LIST = "models/ofc/infoMainten/managerRecordList";

	/**
	 * 人事管理记录详情
	 */

	private final String MANAGER_RECORD_DETAIL = "models/ofc/infoMainten/managerRecordDetail";

	/**
	 * @Title:
	 * @Description: 人事管理列表页面
	 * @return: String
	 */
	@RequestMapping("/managerRecordList")
	public String keepRecordList() {
		return MANAGER_RECORD_LIST;
	}

	/**
	 * @Title: detail
	 * @Description: 人事管理详情页面
	 * @param id
	 * @return: String
	 */
	@RequestMapping("/detail")
	public String keepRecordDetail(String id, Model model) {
		ManagerRecord managerRecord = managerRecordService.get(id);
		Servant servant = servantService.get(managerRecord.getServant().getId());
		model.addAttribute("id", id);
		model.addAttribute("servant", servant);
		model.addAttribute("managerRecord", managerRecord);
		return MANAGER_RECORD_DETAIL;
	}

	/**
	 * @Title: query
	 * @Description: 人事管理信息列表
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<Servant>
	 */
	@ResponseBody
	@RequestMapping("/query")
	public Page<ManagerRecordVO> queryManagerRecord(ManagerRecordVO managerRecordVO, Integer limit, Integer page) {

		Map<String, Object> filter = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(managerRecordVO.getName())) {// 姓名
			filter.put("name", "%" + managerRecordVO.getName() + "%");
		}
		if (StringUtils.isNotBlank(managerRecordVO.getCardNo())) {// 身份证
			filter.put("cardNo", "%" + managerRecordVO.getCardNo() + "%");
		}
		if (StringUtils.isNotBlank(managerRecordVO.getSex())) {// 性别
			filter.put("sex", managerRecordVO.getSex());
		}
		if (StringUtils.isNotBlank(managerRecordVO.getRecordType())) {// 人事管理类型
			filter.put("recordType", managerRecordVO.getRecordType());
		}
		if (StringUtils.isNotBlank(managerRecordVO.getManagerType())) {// 进出管理类型
			filter.put("managerType", managerRecordVO.getManagerType());
		}
		return managerRecordService.queryManagerRecord(filter, page, limit);
	}
	
	/**
	 * @Title: query
	 * @Description: 人事管理信息列表
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: Page<Servant>
	 */
	@ResponseBody
	@RequestMapping("/getPage")
	public Page<ItemRecordVO> getPage(ManagerRecordParam param, Integer limit, Integer page) {
		return managerRecordService.getPage(param, page, limit);
	}
}
