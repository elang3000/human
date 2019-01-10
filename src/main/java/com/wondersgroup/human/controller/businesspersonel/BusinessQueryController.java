package com.wondersgroup.human.controller.businesspersonel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.bo.analysis.GeneralQuery;
import com.wondersgroup.human.dto.businesspersonel.BusinessParam;
import com.wondersgroup.human.dto.businesspersonel.BusinessQueryParam;
import com.wondersgroup.human.service.analysis.GeneralQueryService;
import com.wondersgroup.human.service.pubinst.PublicInstitutionService;
import com.wondersgroup.human.vo.pubinst.PublicInstitutionVO;

/**
 * @ClassName: BusinessQueryController 事业单位
 * @Description: 综合查询控制类
 * @author: lihao
 * @date: 2018年8月20日下午2:31:55 
 * @version [版本号, YYYY-MM-DD] 
 * @see       [相关类/方法] 
 * @since     [产品/模块版本]
 */
@RequestMapping("/business/personel")
@Controller
public class BusinessQueryController {
	
	//@Autowired
	//private BusinessQueryService businessQueryService;
	
	@Autowired
	private PublicInstitutionService publicInstitutionService;

	@Autowired
	private GeneralQueryService generalQueryService;
	/**
	 * 事业人员综合查询人员列表
	 */
	public static final String VIEW_BUSINESSPERSONEL_BUSINESSQUERY = "models/businesspersonel/businessQuery/businessQueryList";
	
	
	/**
	 * 综合查询信息列表
	 * @return
	 */
	@RequestMapping("/businessQueryList")
	public String businessQueryList(){
		
		return VIEW_BUSINESSPERSONEL_BUSINESSQUERY;
	}
	
	
	
	/**
	 * 综合查询下拉列表
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/queryList/{category}")
	public List<BusinessQuery> queryList(@PathVariable(name = "category", required = true) String category){
		List<BusinessQuery> list = null;
		
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
			list = businessQueryService.findByFilter(filter,sort);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return list;
	}*/
	
	/**
	 * @Title:  
	 * @Description: 单条查询条件信息
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	/*@RequestMapping("/info")
	public BusinessQuery info(String id) {
		BusinessQuery g = businessQueryService.get(id);
		return g;
	}
	
	@RequestMapping("/info/{id}")
	@ResponseBody
	public BusinessQuery loadCodeInfoById(@PathVariable(name = "id", required = true) String id){
		return businessQueryService.get(id);
	}*/
	
	
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
	public Page<PublicInstitutionVO> query(BusinessQueryParam param,Integer limit,Integer page) {
		
		String code1 = param.getCode1();
		String code2 = param.getCode2();
		String code3 = param.getCode3();
		
		List<BusinessParam> spList = new ArrayList<BusinessParam>();
		
		if(StringUtils.isNotBlank(param.getName())){//姓名
			BusinessParam sp = new BusinessParam();
			sp.setCode1("c1.A01001");
			sp.setCode2("%"+param.getName()+"%");
			sp.setCode3("like");
			spList.add(sp);
		}
		if(StringUtils.isNotBlank(param.getDepartName())){//部门名称
			BusinessParam sp = new BusinessParam();
			sp.setCode1("c1.A01057A");
			sp.setCode2("%"+param.getDepartName()+"%");
			sp.setCode3("like");
			spList.add(sp);
		}
		if(StringUtils.isNotBlank(param.getCardNo())){//身份证号
			BusinessParam sp = new BusinessParam();
			sp.setCode1("c1.A01085");
			sp.setCode2(param.getCardNo());
			sp.setCode3("=");
			spList.add(sp);
		}
		if(param.getSex()!=null&&StringUtils.isNotBlank(param.getSex().getId())){//性别
			BusinessParam sp = new BusinessParam();
			sp.setCode1("c1.A01004");
			sp.setCode2(param.getSex().getId());
			sp.setCode3("=");
			spList.add(sp);
		}
		if(StringUtils.isNotBlank(param.getAgeMax())){//年龄上限
			BusinessParam sp = new BusinessParam();
			sp.setCode1("EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM c1.A01007)");
			sp.setCode2(param.getAgeMax());
			sp.setCode3("<=");
			spList.add(sp);
		}
		if(StringUtils.isNotBlank(param.getAgeMin())){//年龄下限
			BusinessParam sp = new BusinessParam();
			sp.setCode1("EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM c1.A01007)");
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
					BusinessParam sp = new BusinessParam();
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
					if(!tableName.equals("c1")){//除开a1表
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
		
		Page<PublicInstitutionVO> pageInfo = publicInstitutionService.queryBusinessInfoBySeniorCondation(spList, m, page, limit);
		return pageInfo;
	}



	private String oprSql(String s) {
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



	
	
	
	
	
}
