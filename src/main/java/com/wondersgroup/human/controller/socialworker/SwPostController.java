/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: PostController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofc 
 * 描述: 人员信息子表-职务 控制器
 * 创建人: tzy   
 * 创建时间: 2018年5月30日 下午5:39:59 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月30日 下午5:39:59 
 * 修改任务号
 * 修改内容：
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
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.bo.socialworker.SrExperience;
import com.wondersgroup.human.bo.socialworker.SrPost;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwExperienceService;
import com.wondersgroup.human.service.socialworker.SwPostService;
import com.wondersgroup.human.vo.socialworker.SwPostVO;

/** 
 * @ClassName: PostController 
 * @Description: 人员信息子表-职务 控制器
 * @author: tzy
 * @date: 2018年5月30日 下午5:39:59
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@RequestMapping("/social/post")
@Controller
public class SwPostController extends GenericController{
	
	/**
	 * 职务编辑层
	 */
	private static final String VIEW_POST_EDIT="models/socialworker/socialpostEdit";
	
	@Autowired
	private SwPostService swpostService;
	@Autowired
	private SocialWorkerService socialWorkerService;
	
	@Autowired
	private SwExperienceService swExperienceService;
	
	@RequestMapping("/edit")
	public String postEditSw(String Id,String postId,Model model){
		if(StringUtils.isNoneBlank(postId)){//编辑层
			SrPost post = swpostService.get(postId);
			model.addAttribute("post", post);
			model.addAttribute("id",Id);
			model.addAttribute("socialWorker", post.getSocialWorker());
		}else{
			 SocialWorker pubinst = socialWorkerService.get(Id);
			model.addAttribute("socialWorker", pubinst);
		}
		return VIEW_POST_EDIT;
	}
	
	/**
	 * @Title: pageList 
	 * @Description: 在职人员信息列表
	 * @param servantId			人员id
	 * @param limit			页大小
	 * @param page			页码
	 * @return: Page<PostVO>
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<SwPostVO> pageListSw(String Id,Integer limit,Integer page){
		List<Predicate> filter = new ArrayList<>();//查询条件
		Predicate p = new Predicate("socialWorker.id", Operator.EQ,Id, "");//查询人员下职务信息
		filter.add(p);
		Page<SwPostVO> pageInfo = swpostService.getPage(filter, null, page, limit);
		return pageInfo;
	}
	/**
	 * @Title: save 
	 * @Description: 	职务信息保存功能
	 * @param temp		职务信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult saveSw(SrPost temp){
		AjaxResult result = new AjaxResult(true);
		//同步更新简历
		SrExperience ptex = new SrExperience();
		
		//SrPost ptex=new SrPost();
		try {
			
			if(StringUtils.isNotBlank(temp.getId())){//更新
				SrPost post = swpostService.get(temp.getId());
				CodeInfo postCode = temp.getPostCode();
				if (post != null) {
					post.setPostName(postCode.getName());
				}
				BeanUtils.copyPropertiesIgnoreNull(temp, post);
				DictUtils.operationCodeInfo(post);//将CodeInfo中id为空的属性 设置为null
				swpostService.saveOrUpdate(post);//保存
//				ptExperienceService.SaveOrUpdateExperienceBypost(post);
			}else{//新增
				temp.setId(null);
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				swpostService.saveOrUpdate(temp);
				//开始根据新增职务新增一条简历
				swExperienceService.SaveOrUpdateExperienceBypost(temp);
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
	 * @Description: 	职务信息删除功能
	 * @param temp		职务信息
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult deleteSw(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			SrPost post = swpostService.get(id);
			swpostService.delete(post);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
}
