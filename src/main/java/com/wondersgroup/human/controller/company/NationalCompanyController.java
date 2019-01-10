package com.wondersgroup.human.controller.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
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
import com.wondersgroup.human.bo.company.CyOutMgr;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.service.company.NationalCompanyService;
import com.wondersgroup.human.service.company.NcOutMgrService;
import com.wondersgroup.human.vo.company.NationalCompanyVo;
import com.wondersgroup.human.vo.company.NcOutMgrVo;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;


/**
 * 
 * @ClassName:  NationalCompanyController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: bianyf
 * @date:   2018年8月22日 下午2:12:27   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Controller
@RequestMapping("/company")
public class NationalCompanyController extends GenericController {
	
	
	@Autowired
	private NationalCompanyService nationalCompanyService;
	@Autowired
	private NcOutMgrService ncOutMgrService;
	@Autowired
	private DictableService dictableService;
	
	
	
	
	
	
	//跳转路径
	/**
	 * 在职人员列表
	 */
	private static final String VIEW_INSTITUTION_LIST="models/company/nationalCompanyList";
	/*
	 * 调出人员信息列表
	 */
	private static final String VIEW_NCOUTMGR_LIST="models/company/outmgrlist";
	/*
	 * 导入国企人员弹出框
	 */
	private static final String DRAFT_NATIONALCOMPANY_IMPORT = "models/company/draftNationalCompanyImportForm";
	/*
	 * 查看信息列表
	 */
	private static final String VIEW_NATIONALCOMPANY_MAIN = "models/company/nationalCompanyMain";
	/**
	 * @Title: causelist 
	 * @Description: 事业单位人员信息列表
	 * @returnneng
	 * @return: String
	 */
	@RequestMapping("/list")
	public String companylist(){
		return VIEW_INSTITUTION_LIST;
	}

	@Log(title = "查询人员信息", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<NationalCompanyVo> pageList(NationalCompany nationalCompany,Integer limit,Integer page) throws Exception{
		List<Predicate> filter = new ArrayList<>();//查询条件
		Sorts sort = new Sorts();//排序规则
		sort.add("reportDate", false);//降序
		if (StringUtils.isNotBlank(nationalCompany.getDepartId())) {//单位下人员
			Predicate p = new Predicate("departId", Operator.EQ,nationalCompany.getDepartId(), "");
			filter.add(p);
		}
		String name = nationalCompany.getName();
		if(StringUtils.isNotBlank(name)){//姓名
			Predicate p = new Predicate("name", Operator.EQ, name, "");
			filter.add(p);
		}
		if(StringUtils.isNotBlank(nationalCompany.getDepartName())){//单位部门
			Predicate p = new Predicate("departName", Operator.LIKE,nationalCompany.getDepartName() ,"");
			filter.add(p);
		}
		if(StringUtils.isNotBlank(nationalCompany.getCardNo())){//身份证
			Predicate p = new Predicate("cardNo", Operator.EQ,nationalCompany.getCardNo(), "");
			filter.add(p);
		}
		if(nationalCompany.getSex()!=null&&StringUtils.isNotBlank(nationalCompany.getSex().getId())){//性别
			Predicate p = new Predicate("sex.id", Operator.EQ,nationalCompany.getSex().getId(), "");
			filter.add(p);
		}
		if(nationalCompany.getIsOnHold()!=null&&StringUtils.isNotBlank(nationalCompany.getIsOnHold().getId())){//任职状态
			Predicate p = new Predicate("isOnHold.id", Operator.EQ,nationalCompany.getIsOnHold().getId(), "");
			filter.add(p);
		}
		
		Page<NationalCompanyVo> pageInfo = nationalCompanyService.getPage(filter, sort, page, limit);
		return pageInfo;
	}
	
	/*
	 * 调出人员信息列表
	 */
	@RequestMapping("/ncoutmgrlist")
	public String outMgrList(){
		return VIEW_NCOUTMGR_LIST;
	}
	
	
	/*
	 *  @Description: 国企调出人员信息列表
	 * @param servant		查询条件
	 * @param limit			页大小
	 * @param page			页码
	 * @return: Page<OutMgrVO>
	 */
	@ResponseBody
	@RequestMapping("/outMgrPageList")
	public Page<NcOutMgrVo>OutMgrList(NcOutMgrVo ncOutMgrVo,String sexId,Integer limit,Integer page)throws Exception{
		
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CyOutMgr.class);
		DetachedCriteria swdCriteria = detachedCriteria.createAlias("nationalCompany", "nc");
		if(StringUtils.isNotBlank(ncOutMgrVo.getName())){
			swdCriteria.add(Restrictions.eq("nc.name", ncOutMgrVo.getName()));
		}
		if (StringUtils.isNotBlank(ncOutMgrVo.getCardNo())) {
			detachedCriteria.add(Restrictions.eq("nc.cardNo", ncOutMgrVo.getCardNo()));
		}
		if (StringUtils.isNotBlank(sexId)) {
			detachedCriteria.add(Restrictions.eq("nc.sex.id", sexId));
		}
		detachedCriteria.add(Restrictions.eq("removed", false));
		detachedCriteria.setFetchMode("nationalCompany", FetchMode.JOIN);
		Page<CyOutMgr>  temppage = ncOutMgrService.findByCriteria(detachedCriteria, page, limit);
		
		List<NcOutMgrVo> voList = new ArrayList<>();
		for(CyOutMgr s:temppage.getResult()){
			NcOutMgrVo vo = new NcOutMgrVo(s);
			voList.add(vo);
		}
		Page<NcOutMgrVo> result = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return result;
	}
	
	/*
	 * 弹出框填充页
	 */
	@RequestMapping("draftNationalCompanyImport")
	public String draftservantImport(){
		return DRAFT_NATIONALCOMPANY_IMPORT;
		
	}
	
	
	/*
	* @Description: 导入国企人员
	 * @RequestParam
	 * @param request
	 * @return
	 * @return: Map<String,String>
	 */
	
	@RequestMapping("importdraftNationalCompanyInfo")
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
				String successInfo= ncOutMgrService.saveImportRecord(file,Integer.parseInt(year));
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

	
	
	/*
	 * 公务员登记
	 */
	@RequestMapping("/main")
	public String ofcMain(String id, String flag,Model model){
		NationalCompany nationalCompany = nationalCompanyService.get(id);
		model.addAttribute("id", id);
		model.addAttribute("flag", flag);
		model.addAttribute("nationalCompany", nationalCompany);
		
		
		return VIEW_NATIONALCOMPANY_MAIN;

		
	}
	
	
	
	
	
	
}
