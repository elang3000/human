/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: TrainingHoursController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年6月13日 下午2:11:07 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年6月13日 下午2:11:07 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.pubinst;


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
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.pubinst.PtTrainingHours;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtTrainingHoursService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PtTrainingHoursVO;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: TrainingHoursController 
 * @Description: 培训学时考核控制器
 * @author: lihao
 * @date: 2018年6月13日 下午2:11:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@RequestMapping("/pubinst/trainingHours")
@Controller
public class PtTrainingHoursController extends GenericController {
	
	/**
	 * 培训学时列表页面
	 */
	private static final String TRAINING_HOURS_LIST = "models/publicInstitution/pubinstTrainingHoursList";
	
	/**
	 * 培训学时添加页面
	 */
	private static final String TRAINING_HOURS_ADD = "models/publicInstitution/pubinstAddTrainingHours";
	
	/**
	 * 培训学时添加页面
	 */
	private static final String TRAINING_HOURS_ADD_LIST = "models/publicInstitution/pubinstAddTrainingHoursList";
	
	/**
	 * 培训学时详情
	 */
	private static final String TRAINING_HOURS_DETAIL = "models/publicInstitution/pubinstTrainingHoursDetail";
	
	@Autowired
	PtTrainingHoursService trainingHoursService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	/**
	 * @Title: 
	 * @Description: 培训学时列表页面
	 * @return: String
	 */
	
	@RequestMapping("/trainingHours")
	public String trainingHoursList() {
		return TRAINING_HOURS_LIST;
	}
	
	/**
	 * @Title: 
	 * @Description: 培训学时列表页面
	 * @return: String
	 */
	@RequestMapping("/addTrainingHours")
	public String addTrainingHours() {
		return TRAINING_HOURS_ADD;
	}
	
	/**
	 * @Title: 
	 * @Description: 选择学员列表页面
	 * @return: String
	 */
	@RequestMapping("/addTrainingHoursList")
	public String addTrainingHoursList(String id, Model model) {
		PtTrainingHours trainingHours = trainingHoursService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("trainingHours", trainingHours);
		return TRAINING_HOURS_ADD_LIST;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 培训列表
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<TrainingHoursVO>
	 */
	@Log(title = "查询培训信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtTrainingHoursVO> pageList(PtTrainingHoursVO trainingHoursVO, Integer limit, Integer page) {
		
		Map<String, Object> filter = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(trainingHoursVO.getTrainName())) {//培训名称
			filter.put("trainName", "%"+trainingHoursVO.getTrainName()+"%");
		}
		if (StringUtils.isNotBlank(trainingHoursVO.getName())) {//姓名
			filter.put("name", "%"+trainingHoursVO.getName()+"%");
		}
		if (StringUtils.isNotBlank(trainingHoursVO.getDepartName())) {//所在单位
			filter.put("departName", "%"+trainingHoursVO.getDepartName()+"%");
		}
		return trainingHoursService.queryTrainingHours(filter, page, limit);
	}
	
	/**
	 * @Title: save 
	 * @Description: 	培训内容保存功能
	 * @param temp		培训内容
	 * @return: AjaxResult
	 */
	@Log(title = "编辑培训内容信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtTrainingHours temp){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				PtTrainingHours trainingHours = trainingHoursService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, trainingHours);
				trainingHoursService.update(trainingHours);//保存
				result.setData(trainingHours);
			}else{//新增
				trainingHoursService.save(temp);
				result.setData(temp);
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
	 * @Title: save 
	 * @Description: 	培训人员保存功能
	 * @param temp		培训人员
	 * @return: AjaxResult
	 */
	@Log(title = "编辑学习培训人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/saveServant")
	public AjaxResult saveServant(String id,String[] ids){
		AjaxResult result = new AjaxResult(true);
		try {
			PtTrainingHours trainingHours = trainingHoursService.get(id);
			
		    for(String s : ids){
		    	PublicInstitution servant = publicInstitutionService.get(s);
		    	trainingHours.getTrainServants().add(servant);
		    }
			
		    trainingHoursService.update(trainingHours);
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
	 * @Description: 培训人员信息列表
	 * @param servant		查询条件
	 * @param limit			页大小
	 * @param page			页码
	 * @return: Page<TrainingHoursVO>
	 */
	@Log(title = "查询培训人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/servantTrainList")
	public Page<PtTrainingHoursVO> servantTrainList(String trainId){
		Page<PtTrainingHoursVO> pageInfo = trainingHoursService.getPage(trainId);
		return pageInfo;
	}
	
	/**
	 * @Title: pageList 
	 * @Description: 在职人员信息列表
	 * @param servant		查询条件
	 * @param limit			页大小
	 * @param page			页码
	 * @return: Page<PublicInstitutionVO>
	 */
	@Log(title = "新增在职人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.INSERT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/addServantTrainList")
	public Page<PublicInstitutionVO> pageList3(PublicInstitution pubinst,String trainId,Integer limit,Integer page){
		Map<String, Object> filter = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(pubinst.getName())) {//姓名
			filter.put("name", "%"+pubinst.getName()+"%");
		}
		if (StringUtils.isNotBlank(pubinst.getCardNo())) {//身份证
			filter.put("cardNo", "%"+pubinst.getCardNo()+"%");
		}
		if (pubinst.getSex()!=null&&StringUtils.isNotBlank(pubinst.getSex().getId())) {//性别
			filter.put("sex", pubinst.getSex().getId());
		}
		
		filter.put("isOnHold", "c5e1b100-c8b1-4cf9-9697-d250d0812246");//在职
		filter.put("trainId", trainId);//培训id
		
		Page<PublicInstitutionVO> pageInfo = trainingHoursService.getPage(filter, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: delete 
	 * @Description: 	培训人员删除功能
	 * @param temp		培训人员
	 * @return: AjaxResult
	 */@Log(title = "删除培训人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	
	@ResponseBody
	@RequestMapping("/delServant")
	public AjaxResult delServant(String trainId,String Id){
		AjaxResult result = new AjaxResult(true);
		try {
			PtTrainingHours trainingHours = trainingHoursService.get(trainId);
			
	    	PublicInstitution pubinst = publicInstitutionService.get(Id);
	    	trainingHours.getTrainServants().remove(pubinst);
			
			trainingHoursService.update(trainingHours);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	
	/**
	 * @Title: detail
	 * @Description: 人事管理详情页面
	 * @param id
	 * @return: String
	 */
	 @Log(title = "x更新信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@RequestMapping("/detail")
	public String trainingHoursDetail(String id,String Id, Model model) {
		PtTrainingHours trainingHours = trainingHoursService.get(id);
		PublicInstitution pubinst = publicInstitutionService.get(Id);
		model.addAttribute("id", id);
		model.addAttribute("trainingHours", trainingHours);
		model.addAttribute("pubinst", pubinst);
		return TRAINING_HOURS_DETAIL;
	}
	
	/**
	 * @Title: 
	 * @Description: 培训学时列表页面
	 * @return: String
	 */
	 @Log(title = "查询学时信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/editTrainingHours")
	public String editTrainingHours(String id,Model model) {
		PtTrainingHours trainingHours = trainingHoursService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("trainingHours", trainingHours);
		return TRAINING_HOURS_ADD;
	}
}
