/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OutMgrController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-调出（退出） 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月22日17:17:30
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月22日17:17:32
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.pubinst;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.pubinst.PtOutMgr;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtOutMgrService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtOutMgrVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: OutMgrController
 * @Description: 人员信息子表-调出（退出） 控制器
 * @author: jiang
 * @date: 2018年8月22日17:20:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/pubinst/outMgr")
@Controller
public class PtOutMgrController extends GenericController {
	
	/**
	 * 调出（退出）编辑层
	 */
	private static final String VIEW_OUTMGR_EDIT = "models/publicInstitution/pubinstOutMgrEdit";
	
	@Autowired
	PtOutMgrService outMgrService;
	
	@Autowired
	PublicInstitutionService publicInstitutionService;
	
	@RequestMapping("/edit")
	public String outMgrEdit(String Id, String outMgrId, Model model) {
		
		if (StringUtils.isNoneBlank(outMgrId)) {
			PtOutMgr outMgr = outMgrService.get(outMgrId);
			model.addAttribute("outMgr", outMgr);
			model.addAttribute("pubinst", outMgr.getPublicInstitution());
		} else {
			PublicInstitution pubinst = publicInstitutionService.get(Id);
			model.addAttribute("pubinst", pubinst);
		}
		return VIEW_OUTMGR_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 调出（退出）信息列表
	 * @param Id 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<OutMgrVO>
	 */
	@Log(title = "查询调出信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtOutMgrVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		Page<PtOutMgrVO> pageInfo = outMgrService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 调出（退出）信息保存功能
	 * @param temp 调出（退出）信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑调出信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtOutMgr temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				PtOutMgr outMgr = outMgrService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, outMgr);
				DictUtils.operationCodeInfo(outMgr);// 将CodeInfo中id为空的属性 设置为null
				outMgrService.saveOrUpdate(outMgr);// 保存
			} else {// 新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
				outMgrService.saveOrUpdate(temp);
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
	 * @Title: delete
	 * @Description: 调出（退出）信息删除功能
	 * @param temp 调出（退出）信息
	 * @return: AjaxResult
	 */
	@Log(title = "删除调出信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			PtOutMgr outMgr = outMgrService.get(id);
			outMgrService.delete(outMgr);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
