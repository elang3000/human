/**   
 * Copyright © 2019 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: QuickQueryController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.analysis 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2019年1月8日 下午2:18:27 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2019年1月8日 下午2:18:27 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.controller.analysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.bo.CodeType;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.dto.analysis.QuickQueryParam;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.ServantVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: QuickQueryController 
 * @Description: 快捷查询控制类
 * @author: lihao
 * @date: 2019年1月8日 下午2:18:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

@RequestMapping("/analysis/qucikQuery")
@Controller
public class QuickQueryController {

	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private ServantService servantService;
	/**
	 * 综合查询人员列表
	 */
	private static final String QUICKQUERY_INDEX = "models/analysis/quickQuery/index";
	
	/**
	 * @Title: index
	 * @Description:综合查询信息列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return QUICKQUERY_INDEX;
	}
	
	
	/**
	 * @Title: query
	 * @Description: 
	 * @param 查询条件
	 * @param limit页大小
	 * @param page页码
	 * @return: 
	 */
	@Log(title = "查询快捷查询列表", operatorType = OperatorType.MANAGE, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/queryList")
	public Page<ServantVO> queryList(QuickQueryParam param,Integer limit,Integer page) {
		
		CodeInfo code1 = null;
		CodeInfo code2 = null;
		CodeInfo code3 = null;
		CodeInfo code4 = null;

		CodeType codeType = null;
		List<String> pList = new ArrayList<>();//存放sql
		List<String> l = new ArrayList<String>();//存放表名
		
		if(StringUtils.isNotBlank(param.getName())){//姓名
			String sql = "a1.A01001 like '%"+param.getName()+"%'";
			pList.add(sql);
		}
		if(StringUtils.isNotBlank(param.getDepartName())){//单位名
			String sql = "a1.A01057A like '%"+param.getDepartName()+"%'";
			pList.add(sql);
		}
		//学历
		if(StringUtils.isNotBlank(param.getQucikA())){//学历
			String sqlStr = "";
			for(String s:param.getQucikA().split(",")){
				String sql = "";
				switch (s) {
				case "1"://本科
					code1 = dictableService.getCodeInfoByCode("21", "GBT_4658_2006_CN");// 本科codeInfo
					sql = "(a08.A08002B = '"+code1.getId()+"')";
					break;
				case "2"://专科
					code1 = dictableService.getCodeInfoByCode("31", "GBT_4658_2006_CN");// 专科codeInfo
					sql = "(a08.A08002B = '"+code1.getId()+"')";
					break;
				case "3"://硕士
					code1 = dictableService.getCodeInfoByCode("14", "GBT_4658_2006_CN");// 硕士codeInfo
					sql = "(a08.A08002B = '"+code1.getId()+"')";
					break;
				case "4"://博士
					code1 = dictableService.getCodeInfoByCode("11", "GBT_4658_2006_CN");// 博士codeInfo
					sql = "(a08.A08002B = '"+code1.getId()+"')";
					break;
				default://其他
					code1 = dictableService.getCodeInfoByCode("21", "GBT_4658_2006_CN");// 本科codeInfo
					code2 = dictableService.getCodeInfoByCode("31", "GBT_4658_2006_CN");// 专科codeInfo
					code3 = dictableService.getCodeInfoByCode("14", "GBT_4658_2006_CN");// 硕士codeInfo
					code4 = dictableService.getCodeInfoByCode("11", "GBT_4658_2006_CN");// 博士codeInfo
					sql = "(a08.A08002B not in ('"+code1.getId()+"','"+code2.getId()+"','"+code3.getId()+"','"+code4.getId()+"'))";
					break;
				}
				sqlStr  += sql + " or ";
			}
			
			l.add("a08");//添加a08表
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//年龄
		if(StringUtils.isNotBlank(param.getQucikB())){//年龄
			String sqlStr = "";
			for(String s:param.getQucikB().split(",")){
				String sql = "";
				switch (s) {
				case "1"://35
					sql = "(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM a1.A01007)=35)";
					break;
				case "2"://35-45
					sql = "(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM a1.A01007)>35 and EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM a1.A01007)<=45)";
					break;
				case "3"://45-55
					sql = "(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM a1.A01007)>45 and EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM a1.A01007)<=55)";
					break;
				default://55以上
					sql = "(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM a1.A01007)>55)";
					break;
				}
				sqlStr  += sql + " or ";
			}
			
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//性别
		if(StringUtils.isNotBlank(param.getQucikC())){
			String sqlStr = "";
			for(String s:param.getQucikC().split(",")){
				String sql = "";
				switch (s) {
				case "1"://男
					code1 = dictableService.getCodeInfoByCode("1", "GBT_2261_1_2003");// 男codeInfo
					sql = "(a1.A01004 = '"+code1.getId()+"')";
					break;
				default://女
					code1 = dictableService.getCodeInfoByCode("2", "GBT_2261_1_2003");// 女codeInfo
					sql = "(a1.A01004 = '"+code1.getId()+"')";
					break;
				}
				sqlStr  += sql + " or ";
			}
			
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//党派
		if(StringUtils.isNotBlank(param.getQucikD())){
			String sqlStr = "";
			for(String s:param.getQucikD().split(",")){
				String sql = "";
				switch (s) {
				case "1"://中共
					code1 = dictableService.getCodeInfoByCode("01", "GBT_4762_1984");// 中共codeInfo
					code2 = dictableService.getCodeInfoByCode("02", "GBT_4762_1984");// 中共预备codeInfo
					sql = "(a1.SH_A2205 = '"+code1.getId()+"')";
					break;
				case "2"://共青团
					code1 = dictableService.getCodeInfoByCode("03", "GBT_4762_1984");// 共青团codeInfo
					sql = "(a1.SH_A2205 = '"+code1.getId()+"')";
					break;
				case "3"://民族党派
					code1 = dictableService.getCodeInfoByCode("01", "GBT_4762_1984");// 中共codeInfo
					code2 = dictableService.getCodeInfoByCode("02", "GBT_4762_1984");// 中共预备codeInfo
					code3 = dictableService.getCodeInfoByCode("03", "GBT_4762_1984");// 共青团codeInfo
					code4 = dictableService.getCodeInfoByCode("13", "GBT_4762_1984");// 群众codeInfo
					sql = "(a1.SH_A2205 not in ('"+code1.getId()+"','"+code2.getId()+"','"+code3.getId()+"','"+code4.getId()+"'))";
					break;
				default://群众
					code1 = dictableService.getCodeInfoByCode("13", "GBT_4762_1984");// 群众codeInfo
					sql = "(a1.SH_A2205 = '"+code1.getId()+"')";
					break;
				}
				sqlStr  += sql + " or ";
			}
			
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//民族
		if(StringUtils.isNotBlank(param.getQucikE())){
			String sqlStr = "";
			for(String s:param.getQucikE().split(",")){
				String sql = "";
				switch (s) {
				case "1"://汉
					code1 = dictableService.getCodeInfoByCode("01", "GBT_3304_1991");// 汉codeInfo
					sql = "(a1.A01017 = '"+code1.getId()+"')";
					break;
				default://少数民族
					code1 = dictableService.getCodeInfoByCode("01", "GBT_3304_1991");// 少数民族codeInfo
					sql = "(a1.A01017 != '"+code1.getId()+"')";
					break;
				}
				sqlStr  += sql + " or ";
			}
			
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//专业
		
		if(StringUtils.isNotBlank(param.getQucikF())){
			String sqlStr = "";
			codeType = dictableService.getCodeType("GBT_16835_1997");//专业类codeType
			for(String s:param.getQucikF().split(",")){
				String sql = "";
				switch (s) {
				case "1"://文史类 code  01哲学类 05文学  06历史学
					sql = "(a08.A08027 in (select id from cf_code_info where (code like '01%' or code like '05%' or code like '06%') and length(code) = 6 and type_id = '"+codeType.getId()+"'))";
					break;
				case "2"://理工类 code 07理学  08工学
					sql = "(a08.A08027 in (select id from cf_code_info where (code like '07%' or code like '08%') and length(code) = 6 and type_id = '"+codeType.getId()+"'))";
					break;
				case "3"://法学 code 03法学
					sql = "(a08.A08027 in (select id from cf_code_info where code like '03%' and length(code) = 6 and type_id = '"+codeType.getId()+"'))";
					break;
				case "4"://管理学 code
					break;
				case "5"://经济学 code 02经济学
					sql = "(a08.A08027 in (select id from cf_code_info where code like '02%' and length(code) = 6 and type_id = '"+codeType.getId()+"'))";
					break;
				default://其它
					sql = "(a08.A08027 not in (select id from cf_code_info where (code like '01%' or code like '02%' or code like '03%' or code like '05%' or code like '06%' or code like '07%' or code like '08%') and length(code) = 6 and type_id = '"+codeType.getId()+"'))";
					break;
				}
				sqlStr  += sql + " or ";
			}
			l.add("a08");//添加a08表
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//学校种类
		if(StringUtils.isNotBlank(param.getQucikG())){
			String sqlStr = "";
			for(String s:param.getQucikG().split(",")){
				String sql = "";
				switch (s) {
				case "1"://双一流
					code1 = dictableService.getCodeInfoByCode("1", "DM215");// 双一流codeInfo
					sql = "(a08.NINE_EIGHT_FIVE_FLAG = '"+code1.getId()+"')";
					break;
				case "2"://985
					code1 = dictableService.getCodeInfoByCode("1", "DM215");// 985codeInfo
					sql = "(a08.TWO_ONE_ONE_FLAG = '"+code1.getId()+"')";
					break;
				case "3"://211
					code1 = dictableService.getCodeInfoByCode("1", "DM215");// 211codeInfo
					sql = "(a08.DOUBLE_FIRST_RATE_FlAG = '"+code1.getId()+"')";
					break;
				default://其他
					code1 = dictableService.getCodeInfoByCode("0", "DM215");// 其他codeInfo
					sql = "(a08.NINE_EIGHT_FIVE_FLAG = '"+code1.getId()+"' and a08.TWO_ONE_ONE_FLAG ='"+code1.getId()+"' and a08.DOUBLE_FIRST_RATE_FlAG ='"+code1.getId()+"')";
					break;
				}
				sqlStr  += sql + " or ";
			}
			l.add("a08");//添加a08表
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//职务层次
		if(StringUtils.isNotBlank(param.getQucikH())){
			String sqlStr = "";
			for(String s:param.getQucikH().split(",")){
				String sql = "";
				switch (s) {
				case "1"://正科
					code1 = dictableService.getCodeInfoByCode("141", "GBT_12407_2008");// 乡科级正职codeInfo
					sql = "(a05.A05002B = '"+code1.getId()+"' and a05.IS_LEADER = 1)";
					break;
				case "2"://主任科员
					code1 = dictableService.getCodeInfoByCode("141", "GBT_12407_2008");// 乡科级正职codeInfo
					sql = "(a05.A05002B = '"+code1.getId()+"' and a05.IS_LEADER = 0)";
					break;
				case "3"://副科
					code1 = dictableService.getCodeInfoByCode("142", "GBT_12407_2008");// 乡科级副职codeInfo
					sql = "(a05.A05002B = '"+code1.getId()+"' and a05.IS_LEADER = 1)";
					break;
				default://副主任科员
					code1 = dictableService.getCodeInfoByCode("142", "GBT_12407_2008");// 乡科级副职codeInfo
					sql = "(a05.A05002B = '"+code1.getId()+"' and a05.IS_LEADER = 0)";
					break;
				}
				sqlStr  += sql + " or ";
			}
			l.add("a05");//添加a05表
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//人员状态
		if(StringUtils.isNotBlank(param.getQucikI())){
			String sqlStr = "";
			for(String s:param.getQucikI().split(",")){
				String sql = "";
				switch (s) {
				case "1"://现职
					code1 = dictableService.getCodeInfoByCode("1", "DM200");// 现职codeInfo
					sql = "(a1.A01063 = '"+code1.getId()+"')";
					break;
				case "2"://调出
					code1 = dictableService.getCodeInfoByCode("3", "DM200");// 调出codeInfo
					sql = "(a1.A01063 = '"+code1.getId()+"')";
					break;
				case "3"://试用期
					code1 = dictableService.getCodeInfoByCode("6", "DM200");// 试用期codeInfo
					sql = "(a1.A01063 = '"+code1.getId()+"')";
					break;
				default://其他
					code1 = dictableService.getCodeInfoByCode("1", "DM200");// 现职codeInfo
					code2 = dictableService.getCodeInfoByCode("3", "DM200");// 调出codeInfo
					code3 = dictableService.getCodeInfoByCode("6", "DM200");// 试用期codeInfo
					sql = "(a1.A01063 not in ('"+code1.getId()+"','"+code2.getId()+"','"+code3.getId()+"'))";
					break;
				}
				sqlStr  += sql + " or ";
			}
			pList.add(sqlStr.substring(0, sqlStr.length()-4));
		}
		
		//去除表名重复
		HashSet<String> h = new HashSet<String>(l);
        l.clear();
        l.addAll(h);
        
        Page<ServantVO> pageInfo = servantService.queryServantInfoBySeniorCondation(pList,l,page, limit);
		return pageInfo;
	}
}
