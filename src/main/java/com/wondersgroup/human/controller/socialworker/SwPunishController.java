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
package com.wondersgroup.human.controller.socialworker;

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
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.bo.socialworker.SrRewardAndPunish;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwRewardAndPunishService;
import com.wondersgroup.human.vo.ofc.PunishVO;
import com.wondersgroup.human.vo.socialworker.SwPunishVO;

/** 
 * @ClassName: RewardAndPunishController 
 * @Description: 处分备案 控制器
 * @author: lihao
 * @date: 2018年7月12日 上午10:18:07
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@RequestMapping("/socialWorker/punish")
@Controller
public class SwPunishController  extends GenericController{
	
	@Autowired
	private SwRewardAndPunishService rewardAndPunishService;

	@Autowired
	private SocialWorkerService socialWorkerService;

	
	//跳转路径
	/**
	 * 人员列表
	 */
	private static final String SOCIAL_PUNISH_SERVANT_LIST="models/socialworker/socialPunishServantList";
	
	/**
	 * 惩戒列表
	 */
	private static final String SOCIAL_PUNISH_PUNISH_LIST="models/socialworker/socialPunishList";
	
	/**
	 * 惩戒详情
	 */
	private static final String SOCIAL_PUNISH_DETAIL="models/socialworker/punish/socialPunishDetail";
	
	/**
	 * 惩戒增加
	 */
	private static final String SOCIAL_PUNISH_EDIT="models/socialworker/socialPunishEdit";
	
	/**
	 * 惩戒人员选择
	 */
	private static final String SOCIAL_PUNISH_SELECT_SERVANT="mmodels/socialworker/punish/socialPunishSelectServant";
	

	/**
	 * @Title: punishList 
	 * @Description: 人员列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/servantList")
	public String servantList(){
		return SOCIAL_PUNISH_SERVANT_LIST;
	}
	
	/**
	 * @Title: punishList 
	 * @Description: 惩戒列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/punishList")
	public String punishList(){
		return SOCIAL_PUNISH_PUNISH_LIST;
	}
	
	@RequestMapping("/edit")
	public String editPunish(String Id,String punishId,Model model){
		if (StringUtils.isNoneBlank(punishId)) {
			SrRewardAndPunish punish = rewardAndPunishService.get(punishId);
			model.addAttribute("punish", punish);
			model.addAttribute("socialWorker", punish.getSocialWorker());
		} else {
			SocialWorker SOCIAL = socialWorkerService.get(Id);
			model.addAttribute("socialWorker", SOCIAL);
		}
		return SOCIAL_PUNISH_EDIT;
	}
	
	/**
	 * @Title: punishList 
	 * @Description: 惩戒列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/selectPunishServant")
	public String selectPunishServant(){
		return SOCIAL_PUNISH_SELECT_SERVANT;
	}
	
	/**
	 * @Title: getPage 
	 * @Description: 惩戒人员列表
	 * @param PunishVO		查询条件
	 * @param limit			页大小
	 * @param page			页码
	 * @return: Page<PunishVO>
	 */
	@ResponseBody
	@RequestMapping("/getPage")
	public Page<SwPunishVO> getPage(PunishVO punishVO,Integer limit,Integer page){
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
			Predicate p = new Predicate("socialWorker.name", Operator.LIKE,punishVO.getName(), "socialWorker");
			filter.add(p);
		}
		if(StringUtils.isNotBlank(punishVO.getCardNo())){//身份证
			Predicate p = new Predicate("socialWorker.cardNo", Operator.LIKE,punishVO.getCardNo(), "socialWorker");
			filter.add(p);
		}
		Predicate p = new Predicate("type", Operator.EQ,1, "");
		filter.add(p);
		Page<SwPunishVO> pageInfo = rewardAndPunishService.getPage(filter, sort, page, limit);
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
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<SwPunishVO> pageList(String Id, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("socialWorker.id", Operator.EQ, Id, "");
		filter.add(p);
		p = new Predicate("type", Operator.EQ,SrRewardAndPunish.TYPE_OF_PUNISH, "");
		filter.add(p);
		Page<SwPunishVO> pageInfo = rewardAndPunishService.getPunishPage(filter, null, page, limit);
		return pageInfo;
	}

	/**
	 * @Title: save 
	 * @Description: 	处分保存功能
	 * @param temp		职务信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(SrRewardAndPunish temp){
		AjaxResult result = new AjaxResult(true);
		try {
			
			if(StringUtils.isNotBlank(temp.getId())){//更新
				SrRewardAndPunish rewardAndPunish = rewardAndPunishService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, rewardAndPunish);
				DictUtils.operationCodeInfo(rewardAndPunish);//将CodeInfo中id为空的属性 设置为null
				rewardAndPunishService.saveOrUpdate(rewardAndPunish);//保存
			}else{//新增
				temp.setId(null);
				temp.setType(SrRewardAndPunish.TYPE_OF_PUNISH);
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
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delPunish(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			SrRewardAndPunish rewardAndPunish = rewardAndPunishService.get(id);
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
