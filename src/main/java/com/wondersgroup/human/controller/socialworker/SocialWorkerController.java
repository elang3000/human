package com.wondersgroup.human.controller.socialworker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.ofc.RewardAndPunish;
import com.wondersgroup.human.bo.pubinst.PtRewardAndPunish;
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.bo.socialworker.SrExperience;
import com.wondersgroup.human.bo.socialworker.SrOutMgr;
import com.wondersgroup.human.bo.socialworker.SrRewardAndPunish;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwExperienceService;
import com.wondersgroup.human.service.socialworker.SwOutMgrService;
import com.wondersgroup.human.service.socialworker.SwRewardAndPunishService;
import com.wondersgroup.human.vo.socialworker.OutMgrVO;
import com.wondersgroup.human.vo.socialworker.SocialWorkerVO;

/**
 * 
 * @ClassName:  SocialWorkerController   
 * @Description:社工管理控制器   
 * @author: bianyf
 * @date:   2018年8月20日 下午5:06:47   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Controller
@RequestMapping("/socialworker")
public class SocialWorkerController extends GenericController {
	
	
	@Autowired
	private SocialWorkerService socialWorkerService;
	
	@Autowired
	private SwOutMgrService swOutMgrService;
	
	@Autowired
	private DictableService dictableService;
	
	//简历
	@Autowired
	private SwExperienceService experienceService;
	
	@Autowired
	private SwRewardAndPunishService rewardAndPunishService;
	
	//在职人员列表
	private static final String VIEW_SOCIALWORKER_LIST="models/socialworker/socialworkerList";
	
	//调出人员信息列表
	private static final String VIEW_SWOUTMGR_LIST="models/socialworker/swOutMgrList";
	
	//导入社工弹出框
	private final static String DRAFT_SOCIALWORKER_IMPORT = "models/socialworker/draftSocialWorkerImportForm";
	
	//查看信息列表
	private static final String VIEW_SOCIALWORKER_MAIN = "models/socialworker/socialworkerMain";
	
	
	//其他子集
	private static final String VIEW_INSTITUTION_EXTEND_LIST_SOCIALWORKER = "models/socialworker/socialworkerExtendList";
	private static final String VIEW_INSTITUTION_EXTEND_LIST_VIEW_SOCIALWORKER = "models/socialworker/socialworkerExtendListView";

	
	
	/**
	 * @Title: workerlist 
	 * @Description: 社工人员信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/list")
	public String workerlist(){
		return VIEW_SOCIALWORKER_LIST;
	}
	
	
	/**
	 * @Title: pageList 
	 * @Description: 在职人员信息列表
	 * @param servant		查询条件
	 * @param limit			页大小
	 * @param page			页码
	 * @return: Page<SocialWorkerVO>
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<SocialWorkerVO> pageList(SocialWorker socialWorker,Integer limit,Integer page) throws Exception{
		List<Predicate> filter = new ArrayList<>();//查询条件
		Sorts sort = new Sorts();//排序规则
		sort.add("reportDate", false);//降序
		if (StringUtils.isNotBlank(socialWorker.getDepartId())) {//单位下人员
			Predicate p = new Predicate("departId", Operator.EQ,socialWorker.getDepartId(), "");
			filter.add(p);
		}
		String name = socialWorker.getName();
		if(StringUtils.isNotBlank(name)){//姓名
			//name = new String(name.trim().getBytes("iso-8859-1"), "utf-8");
			Predicate p = new Predicate("name", Operator.EQ, name, "");
			filter.add(p);
		}
		if(StringUtils.isNotBlank(socialWorker.getCardNo())){//身份证
			Predicate p = new Predicate("cardNo", Operator.LIKE,socialWorker.getCardNo(), "");
			filter.add(p);
		}
		if(socialWorker.getSex()!=null&&StringUtils.isNotBlank(socialWorker.getSex().getId())){//性别
			Predicate p = new Predicate("sex.id", Operator.EQ,socialWorker.getSex().getId(), "");
			filter.add(p);
		}
		if(socialWorker.getIsOnHold()!=null&&StringUtils.isNotBlank(socialWorker.getIsOnHold().getId())){//任职状态
			Predicate p = new Predicate("isOnHold.id", Operator.EQ,socialWorker.getIsOnHold().getId(), "");
			filter.add(p);
		}
		//Predicate p = new Predicate("isOnHold.id", Operator.EQ, Constants.INCUMBENCY, "");//查询在职人员
		//CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", "DM200");//在职CODE
		//Predicate p = new Predicate("isOnHold.id", Operator.EQ,isOnHold.getId(), "");//查询在职人员
		//filter.add(p);
		Page<SocialWorkerVO> pageInfo = socialWorkerService.getPage(filter, sort, page, limit);
		return pageInfo;
	}
	
	
	
	/**
	 * @Title: outMgrlist 
	 * @Description: 社工人员调出信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/outmgrlist")
	public String outMgrlist(){
		return VIEW_SWOUTMGR_LIST;
	}
	
	
	/**
	 * @Title: outMgrPageList 
	 * @Description: 社工调出人员信息列表
	 * @param servant		查询条件
	 * @param limit			页大小
	 * @param page			页码
	 * @return: Page<OutMgrVO>
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/outMgrPageList")
	public Page<OutMgrVO> outMgrList(OutMgrVO outMgrVO, String sexId, Integer limit,Integer page) throws Exception{
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SrOutMgr.class);
		DetachedCriteria swdCriteria = detachedCriteria.createAlias("socialWorker", "sw");
		if(StringUtils.isNotBlank(outMgrVO.getName())){
			swdCriteria.add(Restrictions.eq("sw.name", outMgrVO.getName()));
		}
		if (StringUtils.isNotBlank(outMgrVO.getCardNo())) {
			detachedCriteria.add(Restrictions.eq("sw.cardNo", outMgrVO.getCardNo()));
		}
		if (StringUtils.isNotBlank(sexId)) {
			detachedCriteria.add(Restrictions.eq("sw.sex.id", sexId));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.setFetchMode("socialWorker", FetchMode.JOIN);
		Page<SrOutMgr>  temppage = swOutMgrService.findByCriteria(detachedCriteria, page, limit);
		
		List<OutMgrVO> voList = new ArrayList<>();
		for(SrOutMgr s:temppage.getResult()){
			OutMgrVO vo = new OutMgrVO(s);
			voList.add(vo);
		}
		Page<OutMgrVO> result = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return result;
	}
	
	/**
	 * 弹出框填充页
	 * @return
	 */
	@RequestMapping("draftsocialworkerImport")
	public String draftservantImport(){
		return DRAFT_SOCIALWORKER_IMPORT;
	}
	
	
	/**
	 * @Title: importdraftservantInfo 
	 * @Description: 导入社工人员
	 * @RequestParam
	 * @param request
	 * @return
	 * @return: Map<String,String>
	 */
	@RequestMapping("importdraftSocialWorkerInfo")
	@ResponseBody
	public Map<String,String> importdraftservantInfo(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		Map<String,String> data = new HashMap<String,String>();//存放文件在ftp服务器的路径和文件名称，code为1时文件保存成功
		try{
			String field = multipartRequest.getParameter("field");//数据input的name值，用以获取文件
			String year = multipartRequest.getParameter("folder");//年份 人员类型  临时通过此参数传递
			if(field==null||"".equals(field)||"undefined".equals(field)){//layui的默认值file
				field="file";
			}
			MultipartFile file = multipartRequest.getFile(field);
			if(file!=null && file.getSize()>0){
				//导入人员信息
				String successInfo= swOutMgrService.saveImportRecord(file,Integer.parseInt(year));
				if("success".equals(successInfo)){
					data.put("code", "1");
					data.put("msg", "人员导入成功!");
				}else{
					data.put("msg",  "人员导入失败!");
				}
				
			}
		}catch(Exception e){
			data.put("code", "0");
			data.put("msg", "上传失败!请检查上传文件!");
			e.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * @Title: ofcMain 
	 * @Description: 公务员登记表
	 * @param servantId
	 * @return: String
	 */
	@RequestMapping("/main")
	public String ofcMain(String id, String flag,Model model){
		SocialWorker socialWorker = socialWorkerService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("flag", flag);
		model.addAttribute("socialWorker", socialWorker);
		installInfoList(id, model);
		return VIEW_SOCIALWORKER_MAIN;
	}
	
	
	
	/**
	 * @Title: extendList
	 * @Description:其他子集
	 * @param id
	 * @return: String
	 */
	@RequestMapping("/extendlist")
	public String extendList(String id, Model model) {
		
		model.addAttribute("id", id);
		return VIEW_INSTITUTION_EXTEND_LIST_SOCIALWORKER;
	}
	
	
	/**
	 * @Title: extendList
	 * @Description:其他子集（信息展示）
	 * @param id
	 * @return: String
	 */
	@RequestMapping("extend_list/view")
	public String extendListViewSw(String id, Model model) {
		
		model.addAttribute("id", id);
		return VIEW_INSTITUTION_EXTEND_LIST_VIEW_SOCIALWORKER;
	}
	
	/**
	 * 
	 * @Title: installInfoList 
	 * @Description: 组装登记表显示字段
	 * @param Id
	 * @param model
	 * @return: void
	 */
	private void installInfoList(String Id, Model model) {
		
		// 查询简历集合（按照起始时间倒序）
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SrExperience.class);
				// DetachedCriteria criteria = detachedCriteria.createAlias("servant", "s");
				// criteria.setFetchMode("servant", FetchMode.JOIN);
				detachedCriteria.add(Restrictions.eq("socialWorker.id", Id));
				detachedCriteria.add(Restrictions.eq("removed", false));
				detachedCriteria.addOrder(Order.desc("startDate"));
				List<SrExperience> experienceList = experienceService.findByCriteria(detachedCriteria);
				// 拼装简历信息
				List<String> experienceInfos = new ArrayList<>();
				if (!experienceList.isEmpty()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					for (SrExperience e : experienceList) {
						StringBuilder info = new StringBuilder();
						info.append(sdf.format(e.getStartDate()) + " ~ ");
						if (e.getEndDate() == null) {
							info.append("至今");
						} else {
							info.append(sdf.format(e.getEndDate()));
						}
						info.append("&nbsp;&nbsp;");
						info.append(e.getFormerUnit());
						info.append("&nbsp;&nbsp;");
						info.append(e.getFormerJob());
						experienceInfos.add(info.toString());
					}
				} else {
					experienceInfos.add("无");
				}
				model.addAttribute("experienceInfos", experienceInfos);
				
				// 查询奖励处分集合（按照起始时间倒序）
				
				detachedCriteria = DetachedCriteria.forClass(SrRewardAndPunish.class);
				detachedCriteria.add(Restrictions.eq("socialWorker.id", Id));
				detachedCriteria.add(Restrictions.eq("removed", false));
				detachedCriteria.add(Restrictions.eq("type", RewardAndPunish.TYPE_OF_REWARD));
				detachedCriteria.addOrder(Order.desc("rewardApprovalDate"));
				
				List<SrRewardAndPunish> rewardList = rewardAndPunishService.findByCriteria(detachedCriteria);
				
				detachedCriteria = DetachedCriteria.forClass(SrRewardAndPunish.class);
				detachedCriteria.add(Restrictions.eq("socialWorker.id", Id));
				detachedCriteria.add(Restrictions.eq("removed", false));
				detachedCriteria.add(Restrictions.eq("type", RewardAndPunish.TYPE_OF_PUNISH));
				detachedCriteria.addOrder(Order.desc("punishApprovalDate"));
				
				List<SrRewardAndPunish> punishList = rewardAndPunishService.findByCriteria(detachedCriteria);
				
				// 拼装奖惩信息
				List<String> rewardAndPunishInfos = new ArrayList<>();
				if (!rewardList.isEmpty()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					for (SrRewardAndPunish r : rewardList) {
						StringBuilder info = new StringBuilder();
						info.append("在&nbsp;&nbsp;" + sdf.format(r.getRewardApprovalDate()));
						info.append("&nbsp;&nbsp;获得");
						info.append("&nbsp;&nbsp;&nbsp;");
						info.append(r.getCategory().getName());
						info.append("&nbsp;&nbsp;&nbsp;");
						info.append(r.getRewardName());
						rewardAndPunishInfos.add(info.toString());
					}
				}
				
				if (!rewardList.isEmpty()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					for (SrRewardAndPunish r : punishList) {
						StringBuilder info = new StringBuilder();
						info.append("在&nbsp;&nbsp;" + sdf.format(r.getPunishApprovalDate()));
						info.append("&nbsp;&nbsp;接受");
						info.append("&nbsp;&nbsp;&nbsp;");
						info.append(r.getCategory().getName());
						info.append("&nbsp;&nbsp;");
						info.append(r.getPunishName());
						rewardAndPunishInfos.add(info.toString());
					}
				}
				if(rewardAndPunishInfos.isEmpty()){
					rewardAndPunishInfos.add("无");
				}
				model.addAttribute("rewardAndPunishInfos", rewardAndPunishInfos);
	}
}
