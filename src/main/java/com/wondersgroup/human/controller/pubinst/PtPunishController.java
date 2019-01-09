/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RewardAndPunishController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc 
 * 描述: 人员奖惩 控制器
 * 创建人: lihao   
 * 创建时间: 2018年7月12日 上午10:18:07 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年7月12日 上午10:18:07 
 * 修改任务号
 * 修改内容：TODO
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
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.pubinst.PtRewardAndPunish;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;
import com.wondersgroup.human.service.pubinst.PtRewardAndPunishService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.ofc.PunishVO;
import com.wondersgroup.human.vo.pubinst.PtPunishVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: RewardAndPunishController 
 * @Description: 处分备案 控制器
 * @author: lihao
 * @date: 2018年7月12日 上午10:18:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@RequestMapping("/pubinst/punish")
@Controller
public class PtPunishController  extends GenericController{
	
	@Autowired
	private PtRewardAndPunishService rewardAndPunishService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;
	
	//跳转路径
	/**
	 * 人员列表
	 */
	private static final String PUBINST_PUNISH_SERVANT_LIST="models/publicInstitution/pubinstPunishServantList";
	
	/**
	 * 惩戒列表
	 */
	private static final String PUBINST_PUNISH_PUNISH_LIST="models/publicInstitution/pubinstPunishList";
	
	/**
	 * 惩戒详情
	 */
	private static final String PUBINST_PUNISH_DETAIL="models/publicInstitution/punish/pubinstPunishDetail";
	
	/**
	 * 惩戒增加
	 */
	private static final String PUBINST_PUNISH_EDIT="models/publicInstitution/pubinstPunishEdit";
	
	/**
	 * 惩戒人员选择
	 */
	private static final String PUBINST_PUNISH_SELECT_SERVANT="mmodels/publicInstitution/punish/pubinstPunishSelectServant";
	

	/**
	 * @Title: punishList 
	 * @Description: 人员列表
	 * @return
	 * @return: String
	 */
	@Log(title = "查询人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/servantList")
	public String servantList(){
		return PUBINST_PUNISH_SERVANT_LIST;
	}
	
	/**
	 * @Title: punishList 
	 * @Description: 惩戒列表
	 * @return
	 * @return: String
	 */
	@Log(title = "查询惩戒信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/punishList")
	public String punishList(){
		return PUBINST_PUNISH_PUNISH_LIST;
	}
	
	@RequestMapping("/edit")
	public String editPunish(String Id,String punishId,Model model){
		if (StringUtils.isNoneBlank(punishId)) {
			PtRewardAndPunish punish = rewardAndPunishService.get(punishId);
			model.addAttribute("punish", punish);
			model.addAttribute("pubinst", punish.getPublicInstitution());
		} else {
			PublicInstitution pubinst = publicInstitutionService.get(Id);
			model.addAttribute("pubinst", pubinst);
		}
		return PUBINST_PUNISH_EDIT;
	}
	
	/**
	 * @Title: punishList 
	 * @Description: 惩戒列表
	 * @return
	 * @return: String
	 */
	@Log(title = "查询惩戒信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@RequestMapping("/selectPunishServant")
	public String selectPunishServant(){
		return PUBINST_PUNISH_SELECT_SERVANT;
	}
	
	/**
	 * @Title: getPage 
	 * @Description: 惩戒人员列表
	 * @param PunishVO		查询条件
	 * @param limit			页大小
	 * @param page			页码
	 * @return: Page<PunishVO>
	 */
	@Log(title = "查询惩戒人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/getPage")
	public Page<PtPunishVO> getPage(PunishVO punishVO,Integer limit,Integer page){
		List<Predicate> filter = new ArrayList<>();//查询条件
		Sorts sort = new Sorts();//排序规则
//		sort.add("reportDate", false);//降序
		if (StringUtils.isNotBlank(punishVO.getPunishNo())) {//文件号
			Predicate p = new Predicate("punishNo", Operator.LIKE,punishVO.getPunishNo(), "");
			filter.add(p);
		}
		if (StringUtils.isNotBlank(punishVO.getPunishName())) {//惩戒名称
			Predicate p = new Predicate("punishName", Operator.LIKE,punishVO.getPunishName(), "");
			filter.add(p);
		}
		if (StringUtils.isNotBlank(punishVO.getPunishReason())) {//惩戒原因
			Predicate p = new Predicate("punishReason.id", Operator.EQ,punishVO.getPunishReason(), "");
			filter.add(p);
		}
		if(StringUtils.isNotBlank(punishVO.getName())){//姓名
			Predicate p = new Predicate("publicInstitution.name", Operator.LIKE,punishVO.getName(), "publicInstitution");
			filter.add(p);
		}
		if(StringUtils.isNotBlank(punishVO.getCardNo())){//身份证
			Predicate p = new Predicate("publicInstitution.cardNo", Operator.LIKE,punishVO.getCardNo(), "publicInstitution");
			filter.add(p);
		}
		Predicate p = new Predicate("type", Operator.EQ,1, "");
		filter.add(p);
		Page<PtPunishVO> pageInfo = rewardAndPunishService.getPage(filter, sort, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: list
	 * @Description: 自己中，处分信息列表
	 * @param Id 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<PostVO>
	 */
	@Log(title = "查询处分信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<PtPunishVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("publicInstitution.id", Operator.EQ, Id, "");
		filter.add(p);
		p = new Predicate("type", Operator.EQ,PtRewardAndPunish.TYPE_OF_PUNISH, "");
		filter.add(p);
		Page<PtPunishVO> pageInfo = rewardAndPunishService.getPunishPage(filter, null, page, limit);
		return pageInfo;
	}

	/**
	 * @Title: save 
	 * @Description: 	处分保存功能
	 * @param temp		职务信息
	 * @return: AjaxResult
	 */
	@Log(title = "编辑处分信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.UPDATE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(PtRewardAndPunish temp){
		AjaxResult result = new AjaxResult(true);
		try {
			
			if(StringUtils.isNotBlank(temp.getId())){//更新
				PtRewardAndPunish rewardAndPunish = rewardAndPunishService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, rewardAndPunish);
				DictUtils.operationCodeInfo(rewardAndPunish);//将CodeInfo中id为空的属性 设置为null
				rewardAndPunishService.saveOrUpdate(rewardAndPunish);//保存
			}else{//新增
				temp.setId(null);
				//temp.setType(RewardAndPunish.TYPE_OF_PUNISH);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				rewardAndPunishService.saveOrUpdate(temp);
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
	 * @Description: 	处分删除功能
	 * @param temp		培训人员
	 * @return: AjaxResult
	 */
	@Log(title = "删除处分信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.DELETE,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delPunish(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			PtRewardAndPunish rewardAndPunish = rewardAndPunishService.get(id);
			rewardAndPunishService.delete(rewardAndPunish);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
}
