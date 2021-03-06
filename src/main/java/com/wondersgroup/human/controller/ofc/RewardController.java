/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RewardController.java
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc
 * 描述: 人员信息子表-奖励 控制器
 * 创建人: jiang
 * 创建时间: 2018年8月16日14:44:26
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年8月16日14:44:29
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofc;

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
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.service.ofc.RewardAndPunishService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.RewardVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: RewardController
 * @Description: 人员信息子表-奖励 控制器
 * @author: jiang
 * @date: 2018年8月16日14:44:39
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@RequestMapping("/ofc/reward")
@Controller
public class RewardController extends GenericController {
	
	/**
	 * 奖励编辑层
	 */
	private static final String VIEW_REWARD_EDIT = "models/ofc/infoMainten/rewardEdit";
	
	@Autowired
	RewardAndPunishService rewardAndPunishService;
	
	@Autowired
	ServantService servantService;
	
	@RequestMapping("/edit")
	public String rewardEdit(String servantId, String rewardId, Model model) {
		
		if (StringUtils.isNoneBlank(rewardId)) {
			RewardAndPunish reward = rewardAndPunishService.get(rewardId);
			model.addAttribute("reward", reward);
			model.addAttribute("servant", reward.getServant());
		} else {
			Servant servant = servantService.get(servantId);
			model.addAttribute("servant", servant);
		}
		return VIEW_REWARD_EDIT;
	}
	
	/**
	 * @Title: list
	 * @Description: 奖励信息列表
	 * @param servantId 人员id
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<PostVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<RewardVO> pageList(String servantId, Integer limit, Integer page) {
		
		List<Predicate> filter = new ArrayList<>();// 查询条件
		Predicate p = new Predicate("servant.id", Operator.EQ, servantId, "");
		filter.add(p);
		p = new Predicate("type", Operator.EQ,RewardAndPunish.TYPE_OF_REWARD, "");
		filter.add(p);
		Page<RewardVO> pageInfo = rewardAndPunishService.getRewardPage(filter, null, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: save
	 * @Description: 奖励信息保存功能
	 * @param temp 奖励信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(RewardAndPunish temp) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			if (StringUtils.isNotBlank(temp.getId())) {// 更新
				RewardAndPunish reward = rewardAndPunishService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, reward);
				DictUtils.operationCodeInfo(reward);// 将CodeInfo中id为空的属性 设置为null
				rewardAndPunishService.saveOrUpdate(reward);// 保存
			} else {// 新增
				temp.setId(null);
				temp.setType(RewardAndPunish.TYPE_OF_REWARD);
				DictUtils.operationCodeInfo(temp);// 将CodeInfo中id为空的属性 设置为null
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
	 * @Description: 奖励信息删除功能
	 * @param temp 奖励信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id) {
		
		AjaxResult result = new AjaxResult(true);
		try {
			RewardAndPunish reward = rewardAndPunishService.get(id);
			rewardAndPunishService.delete(reward);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
