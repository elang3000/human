/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: SpecialCountController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年10月31日 上午10:45:04 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年10月31日 上午10:45:04 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.analysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ibm.icu.text.DecimalFormat;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.service.analysis.SpecialCountService;
import com.wondersgroup.human.vo.analysis.PunishCountVO;
import com.wondersgroup.human.vo.analysis.RewardCountVO;
import com.wondersgroup.human.vo.analysis.MagCountVO;

/** 
 * @ClassName: SpecialCountController 
 * @Description: 专项统计
 * @author: lihao
 * @date: 2018年10月31日 上午10:45:04
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("/analysis/specialCount")
public class SpecialCountController  extends GenericController {

	@Autowired
	SpecialCountService specialCountService;
	
	//专项统计奖励首页
    private static final String REWARD_COUNT_INDEX = "models/analysis/specialCount/rewardCount/rewardIndex";
    
    //专项统计奖励详情
    private static final String REWARD_COUNT_VIEW = "models/analysis/specialCount/rewardCount/rewardView";
    
    //专项统计惩罚首页
    private static final String PUNISH_COUNT_INDEX = "models/analysis/specialCount/punishCount/punishIndex";
    
    //专项统计惩罚详情
    private static final String PUNISH_COUNT_VIEW = "models/analysis/specialCount/punishCount/punishView";
    
    //专项统计惩罚首页
    private static final String MAG_COUNT_INDEX = "models/analysis/specialCount/magCount/magIndex";
    
    //专项统计惩罚详情
    private static final String MAG_COUNT_VIEW = "models/analysis/specialCount/magCount/magView";

    //专项统计奖励首页
    @RequestMapping("/reward/index")
    public String rewardIndex() {
        return REWARD_COUNT_INDEX;
    }
    
    //专项统计奖励列表
    /**
	 * @Title: query
	 * @Description: 
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: 
	 */
	@ResponseBody
	@RequestMapping("/reward/list")
	public Page<RewardCountVO> getRewardCount(String departName,Integer year,Integer limit,Integer page) {
		Page<RewardCountVO> pageInfo = specialCountService.getRewardCount(departName,year,page,limit);
		return pageInfo;
	}
    //专项统计奖励详情
    @RequestMapping("/reward/view")
    public String rewardView(Model model,String departId,String departName,Integer year) {
    	model.addAttribute("departId", departId);
    	model.addAttribute("departName", departName);
    	model.addAttribute("year", year);
        return REWARD_COUNT_VIEW;
    }
    
    @RequestMapping("/reward/echarts")
	@ResponseBody
	public Map<String, Object> getRewardCountByDepartId(String departId,Integer year) {
    	DecimalFormat df = new DecimalFormat("0.00");
		Map<String, BigDecimal> reward = specialCountService.getRewardCountByDepartId(departId,year);
		Iterator<Entry<String, BigDecimal>> iterator = reward.entrySet().iterator();
		List<String> xAxis = new ArrayList<String>();
		List<Integer> series0 = new ArrayList<Integer>();
		List<String> series1 = new ArrayList<String>();
		Integer total = 0;
		while (iterator.hasNext()) {
			Entry<String, BigDecimal> entry = iterator.next();
			xAxis.add(entry.getKey());
			series0.add(entry.getValue().intValue());
			total += entry.getValue().intValue();
		}
		iterator = reward.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, BigDecimal> entry = iterator.next();
			if(total!=0){
				series1.add(df.format(
						new BigDecimal(new Double(entry.getValue().intValue()) / total).setScale(4, RoundingMode.HALF_UP).doubleValue()
						*100));
			}else{
				series1.add(df.format(0.00));
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("xAxis", xAxis);
		result.put("series0", series0);
		result.put("series1", series1);
		return result;
	}
    
    //专项统计奖励首页
    @RequestMapping("/punish/index")
    public String punishIndex() {
        return PUNISH_COUNT_INDEX;
    }
    
    //专项统计奖励列表
    /**
	 * @Title: query
	 * @Description: 
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: 
	 */
	@ResponseBody
	@RequestMapping("/punish/list")
	public Page<PunishCountVO> getPunishCount(String departName,Integer year,Integer limit,Integer page) {
		Page<PunishCountVO> pageInfo = specialCountService.getPunishCount(departName,year,page,limit);
		return pageInfo;
	}
    //专项统计奖励详情
    @RequestMapping("/punish/view")
    public String punishView(Model model,String departId,String departName,Integer year) {
    	model.addAttribute("departId", departId);
    	model.addAttribute("departName", departName);
    	model.addAttribute("year", year);
        return PUNISH_COUNT_VIEW;
    }
    
    @RequestMapping("/punish/echarts")
	@ResponseBody
	public Map<String, Object> getPunishCountByDepartId(String departId,Integer year) {
    	DecimalFormat df = new DecimalFormat("0.00");
		Map<String, BigDecimal> reward = specialCountService.getPunishCountByDepartId(departId,year);
		Iterator<Entry<String, BigDecimal>> iterator = reward.entrySet().iterator();
		List<String> xAxis = new ArrayList<String>();
		List<Integer> series0 = new ArrayList<Integer>();
		List<String> series1 = new ArrayList<String>();
		Integer total = 0;
		while (iterator.hasNext()) {
			Entry<String, BigDecimal> entry = iterator.next();
			xAxis.add(entry.getKey());
			series0.add(entry.getValue().intValue());
			total += entry.getValue().intValue();
		}
		iterator = reward.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, BigDecimal> entry = iterator.next();
			if(total!=0){
				series1.add(df.format(
						new BigDecimal(new Double(entry.getValue().intValue()) / total).setScale(4, RoundingMode.HALF_UP).doubleValue()
						*100));
			}else{
				series1.add(df.format(0.00));
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("xAxis", xAxis);
		result.put("series0", series0);
		result.put("series1", series1);
		return result;
	}
    
    //专项统计进出管理首页
    @RequestMapping("/mag/index")
    public String magIndex() {
        return MAG_COUNT_INDEX;
    }
    
    //专项统计进出管理列表
    /**
	 * @Title: query
	 * @Description: 
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: 
	 */
	@ResponseBody
	@RequestMapping("/mag/list")
	public Page<MagCountVO> getMagCount(String departName,Integer year,Integer limit,Integer page) {
		Page<MagCountVO> pageInfo = specialCountService.getMagCount(departName,year,page,limit);
		return pageInfo;
	}
    //专项统计进出管理详情
    @RequestMapping("/mag/view")
    public String magView(Model model,String departId,String departName,Integer year) {
    	model.addAttribute("departId", departId);
    	model.addAttribute("departName", departName);
    	model.addAttribute("year", year);
        return MAG_COUNT_VIEW;
    }
    
    @RequestMapping("/mag/echarts")
	@ResponseBody
	public Map<String, Object> getMagCountByDepartId(String departId,Integer year) {
    	DecimalFormat df = new DecimalFormat("0.00");
		Map<String, BigDecimal> reward = specialCountService.getMagCountByDepartId(departId,year);
		Iterator<Entry<String, BigDecimal>> iterator = reward.entrySet().iterator();
		List<String> xAxis = new ArrayList<String>();
		List<Integer> series0 = new ArrayList<Integer>();
		List<String> series1 = new ArrayList<String>();
		Integer total = 0;
		while (iterator.hasNext()) {
			Entry<String, BigDecimal> entry = iterator.next();
			xAxis.add(entry.getKey());
			series0.add(entry.getValue().intValue());
			total += entry.getValue().intValue();
		}
		iterator = reward.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, BigDecimal> entry = iterator.next();
			if(total!=0){
				series1.add(df.format(
						new BigDecimal(new Double(entry.getValue().intValue()) / total).setScale(4, RoundingMode.HALF_UP).doubleValue()
						*100));
			}else{
				series1.add(df.format(0.00));
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("xAxis", xAxis);
		result.put("series0", series0);
		result.put("series1", series1);
		return result;
	}
}
