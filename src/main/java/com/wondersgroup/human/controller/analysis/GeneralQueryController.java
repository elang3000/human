/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: GeneralQueryController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.analysis 
 * 描述: 综合查询控制类
 * 创建人: lihao   
 * 创建时间: 2018年8月20日 下午2:31:55 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年8月20日 下午2:31:55 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.human.bo.analysis.GeneralQuery;
import com.wondersgroup.human.dto.analysis.ServantParam;
import com.wondersgroup.human.dto.analysis.ServantQueryParam;
import com.wondersgroup.human.service.analysis.GeneralQueryService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.PeopleVO;
import com.wondersgroup.human.vo.ofc.ServantVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/**
 * @ClassName: GeneralQueryController
 * @Description: 综合查询控制类
 * @author: lihao
 * @date: 2018年8月20日下午2:31:55 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */

@RequestMapping("/analysis/general")
@Controller
public class GeneralQueryController {
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private GeneralQueryService generalQueryService;

	/**
	 * 综合查询人员列表
	 */
	private static final String VIEW_ANALYSIS_GENERALQUERY = "models/analysis/generalQuery/generalQueryList";
	
	private static final String VIEW_ANALYSIS_PEOPLEQUERY = "models/analysis/generalQuery/peopleQueryList";
	
	/**
	 * @Title: generalQueryList
	 * @Description:综合查询信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/generalQueryList")
	public String generalQueryList() {
		return VIEW_ANALYSIS_GENERALQUERY;
	}

	/**
	 * 人员综合查询
	 * @Title: generalQueryList 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@RequestMapping("/peopleQueryList")
	public String peopleQueryList() {
		return VIEW_ANALYSIS_PEOPLEQUERY;
	}
	
	/**
	 * 
	 * @Title: queryList 
	 * @Description: 综合查询下拉列表
	 * @return
	 * @return: List<GeneralQuery>
	 */
	@Log(title = "查询综合查询列表", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/queryList/{category}")
	public List<GeneralQuery> queryList(@PathVariable(name = "category", required = true) String category) {
		
		List<GeneralQuery> list = null;
		try {
			Sorts sort = new Sorts();// 排序规则
			sort.add("queryOrder", true);// 降序
			sort.add("createTime", true);// 降序
			List<Predicate> filter = new ArrayList<>();// 查询条件
			Predicate p = new Predicate("removed", Operator.EQ, false, "");
			filter.add(p);
			if(category.equals(GeneralQuery.CATEGORY_GOV)){// 公务员
				Predicate p1 = new Predicate("category", Operator.EQ, GeneralQuery.CATEGORY_GOV, "");
				filter.add(p1);
			}else if(category.equals(GeneralQuery.CATEGORY_INS)){// 事业
				Predicate p1 = new Predicate("category", Operator.EQ, GeneralQuery.CATEGORY_INS, "");
				filter.add(p1);
			}else if(category.equals(GeneralQuery.CATEGORY_PUB)){// 国企
				Predicate p1 = new Predicate("category", Operator.EQ, GeneralQuery.CATEGORY_PUB, "");
				filter.add(p1);
			}else{// 社工
				Predicate p1 = new Predicate("category", Operator.EQ, GeneralQuery.CATEGORY_SOC, "");
				filter.add(p1);
			}
			list = generalQueryService.findByFilter(filter, sort);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Title:  
	 * @Description: 单条查询条件信息
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/info")
	public GeneralQuery info(String id) {
		GeneralQuery g = generalQueryService.get(id);
		return g;
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public GeneralQuery loadCodeInfoById(@PathVariable(name = "id", required = true) String id){
		return generalQueryService.get(id);
	}
	
	/**
	 * @Title: query
	 * @Description: 
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: 
	 */
	@ResponseBody
	@RequestMapping("/query")
	public Page<ServantVO> query(ServantQueryParam param,Integer limit,Integer page) {
		
		String code1 = param.getCode1();
		String code2 = param.getCode2();
		String code3 = param.getCode3();
		
		List<ServantParam> spList = new ArrayList<ServantParam>();
		
		if(StringUtils.isNotBlank(param.getName())){//姓名
			ServantParam sp = new ServantParam();
			sp.setCode1("a1.A01001");
			sp.setCode2("%"+param.getName()+"%");
			sp.setCode3("like");
			spList.add(sp);
		}
		if(StringUtils.isNotBlank(param.getDepartName())){//部门名称
			ServantParam sp = new ServantParam();
			sp.setCode1("a1.A01057A");
			sp.setCode2("%"+param.getDepartName()+"%");
			sp.setCode3("like");
			spList.add(sp);
		}
		if(StringUtils.isNotBlank(param.getCardNo())){//身份证号
			ServantParam sp = new ServantParam();
			sp.setCode1("a1.A01085");
			sp.setCode2(param.getCardNo());
			sp.setCode3("=");
			spList.add(sp);
		}
		if(param.getSex()!=null&&StringUtils.isNotBlank(param.getSex().getId())){//性别
			ServantParam sp = new ServantParam();
			sp.setCode1("a1.A01004");
			sp.setCode2(param.getSex().getId());
			sp.setCode3("=");
			spList.add(sp);
		}
		if(StringUtils.isNotBlank(param.getAgeMax())){//年龄上限
			ServantParam sp = new ServantParam();
			sp.setCode1("EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM a1.A01007)");
			sp.setCode2(param.getAgeMax());
			sp.setCode3("<=");
			spList.add(sp);
		}
		if(StringUtils.isNotBlank(param.getAgeMin())){//年龄下限
			ServantParam sp = new ServantParam();
			sp.setCode1("EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM a1.A01007)");
			sp.setCode2(param.getAgeMin());
			sp.setCode3(">=");
			spList.add(sp);
		}
		
		List<String> l = new ArrayList<String>();
		Map<String,String> m = new HashMap<String,String>();
		if(StringUtils.isNoneBlank(code1)&&StringUtils.isNoneBlank(code2)&&StringUtils.isNoneBlank(code3)){//code1 2不为空
			String[] code11  = code1.split(",");//项的id组装成数组
			String[] code22  = code2.split(",");//值组装成数组
			String[] code33  = code3.split(",");//操作符号组装成数组
			for(int i=0;i<code11.length;i++){
				if(StringUtils.isNotBlank(code11[i])&&StringUtils.isNotBlank(code22[i])&&StringUtils.isNotBlank(code33[i])){//如果code1、code2和code3取出来的值不为空
					ServantParam sp = new ServantParam();
					GeneralQuery gq = generalQueryService.get(code11[i]);
					sp.setCode1(gq.getCode());
					sp.setCode3(oprSql(code33[i]));
					if(oprSql(code33[i]).equals("like")){//如果操作符号是like添加百分比号
						sp.setCode2("%"+code22[i]+"%");
					}else{
						sp.setCode2(code22[i]);
					}
					spList.add(sp);
					
					int index=gq.getCode().indexOf(".");
					String tableName = gq.getCode().substring(0,index);
					if(!tableName.equals("a1")){//除开a1表
						l.add(tableName);
					}
				}
			}
		}
		
		//去重
		HashSet<String> h = new HashSet<String>(l);
        l.clear();
        l.addAll(h);
        
        //将表名按照如（a02，a2）的形式放进map内
        for(String s : h){
        	String tableNickName = "";
        	if(s.length()<3){//a1,a2这种中间加个0
        		tableNickName = s.substring(0,1)+"0"+s.substring(1,s.length());
        	}else{//a30这种不加值
        		tableNickName = s;
        	}
        	m.put(tableNickName,s);
        }
		
		Page<ServantVO> pageInfo = servantService.queryServantInfoBySeniorCondation(spList,m,page, limit);
		return pageInfo;
	}
	
	
	private String oprSql(String s){
		String o = "";
		switch (s) {
		case "1":
			o = "<";
			break;
		case "2":
			o = "<=";
			break;
		case "3":
			o = "=";
			break;
		case "4":
			o = ">=";
			break;
		case "5":
			o = ">";
			break;
		case "6":
			o = "like";
			break;
		default://默认“=”号
			o = "=";
			break;
		}
		return o;
	}
	
	/**
	 * @Title: query
	 * @Description: 
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: 
	 */
	@ResponseBody
	@RequestMapping("/peoplequery")
	public Page<PeopleVO> peoplequery(ServantQueryParam param,Integer limit,Integer page,String itype) {
		Page<PeopleVO> pageInfo = servantService.queryPeopleInfo(param,itype,page, limit);
		return pageInfo;
	}
}
