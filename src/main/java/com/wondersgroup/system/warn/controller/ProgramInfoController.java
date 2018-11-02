/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ProgramInfoController.java 
 * 工程名: human
 * 包名: com.wondersgroup.system.warn.controller 
 * 描述: 预警预告：查询方案 控制器
 * 创建人: tzy   
 * 创建时间: 2018年10月30日 上午9:16:32 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年10月30日 上午9:16:32 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.system.warn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.util.BeanUtils;
import com.wondersgroup.framework.utils.DictUtils;
import com.wondersgroup.system.warn.bo.ProgramInfo;
import com.wondersgroup.system.warn.bo.ProgramTree;
import com.wondersgroup.system.warn.service.ProgramInfoService;
import com.wondersgroup.system.warn.service.ProgramTreeService;
import com.wondersgroup.system.warn.util.QuartzManager;
import com.wondersgroup.system.warn.vo.ProgramInfoVO;

/** 
 * @ClassName: ProgramInfoController 
 * @Description: 预警预告：查询方案 控制器
 * @author: tzy
 * @date: 2018年10月30日 上午9:16:32
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("programinfo")
public class ProgramInfoController extends GenericEntity{

	private static final long serialVersionUID = 6489165863970087353L;
	
	@Autowired
	private ProgramInfoService programInfoService;
	@Autowired
	private ProgramTreeService programTreeService;
	
	@Autowired
	private QuartzManager quartzManager;
	
	/**
	 * 数据列表
	 */
	private final static String DATA_LIST = "models/programinfo/programInfoList";
	
	/**
	 * 设置执行时间页面
	 */
	private final static String SET_TIME = "models/programinfo/setTime";
	
	/**
	 * 新增方案
	 */
	private final static String ADD_DATA = "models/programinfo/add";
	
	/**
	 * @Title: index 
	 * @Description: 数据列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/index")
	public String index() {
		return DATA_LIST;
	}
	/**
	 * @Title: add 
	 * @Description: 新增页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/add")
	public String add() {
		return ADD_DATA;
	}
	/**
	 * @Title: edit 
	 * @Description: 设置执行时间页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("/setTime")
	public String setTime(Model model) {
		//生成各种时间类型的值
		List<Integer> minute = new ArrayList<>();//1.分
		List<Integer> hour = new ArrayList<>();//2.时
		List<Integer> day = new ArrayList<>();//3.天
		List<Integer> month = new ArrayList<>();//4.月
		List<Integer> week = new ArrayList<>();//5.周
		for(int i=0;i<60;i++){
			minute.add(i);
			if(i<24){
				hour.add(i);
			}
			if(i>=1&&i<=31){
				day.add(i);
			}
			if(i>=1&&i<=12){
				month.add(i);
			}
			if(i>=1&&i<=7){
				week.add(i);
			}
		}
		model.addAttribute("minute", minute);
		model.addAttribute("hour", hour);
		model.addAttribute("day", day);
		model.addAttribute("month", month);
		model.addAttribute("week", week);
		return SET_TIME;
	}
	
	/**
	 * @Title: list 
	 * @Description: 数据列表
	 * @param name	方案名称
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<ProgramInfo>
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Page<ProgramInfoVO> list(String name,String programType,Integer limit,Integer page) {
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from ProgramInfo where removed=:removed ");
		listqueryparameter.add(new QueryParameter("removed", false));
		if(StringUtils.isNotBlank(name)){
			hql.append( " and name like :name");
			listqueryparameter.add(new QueryParameter("name", "%"+name+"%"));
		}
		if(StringUtils.isNotBlank(programType)){
			hql.append( " and programType = :programType");
			listqueryparameter.add(new QueryParameter("programType", programType));
		}
		hql.append( " order by createTime desc");
		
		Page<ProgramInfoVO> pageInfo = programInfoService.findbyHQLforVO(hql.toString(), listqueryparameter, page, limit);

		return pageInfo;
	}
	
	/**
	 * @Title: start 
	 * @Description: 启动预警预告方案
	 * @param id	方案ID
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/start")
	public AjaxResult start(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			ProgramInfo info = programInfoService.get(id);
			info.setProgramState(ProgramInfo.WARN);
			  //启动
			quartzManager.addJob(info.getName(), id, info.getName(),info.getId(), QuartzJobController.class, info.getProgramTime());
			programInfoService.update(info);
			result.setMessage("启动成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("启动失败！");
		}
		return result;
	}
	/**
	 * @Title: close 
	 * @Description: 关闭预警预告方案
	 * @param id	方案ID
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/close")
	public AjaxResult close(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			ProgramInfo info = programInfoService.get(id);
			info.setProgramState(ProgramInfo.PREVIEW);
			//启动
			quartzManager.removeJob(info.getName(),id, info.getName(),info.getId()); 
			programInfoService.update(info);
			result.setMessage("关闭成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("关闭失败！");
		}
		return result;
	}
	/**
	 * @Title: save 
	 * @Description: 方案保存
	 * @param temp	方案信息
	 * @param operation	查询条件符  =,>,<,like
	 * @param condition	查询条件值和逻辑关联值，逻辑关联值：OR,AND,(,)  查询条件值：用户输入数据
	 * @param colName	查询条件的字段名  格式：表名.字段名  多表查询时不报错  例：A01.ID
	 * @param tableName	查询条件涉及的表名
	 * @param colType	查询条件类型  logic:逻辑运算符  1:字符串  2：日期   3：数据字典
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ProgramInfo temp,String[] operation,String[] condition,String[] colName,String[] tableName,String[] colType){
		AjaxResult result = new AjaxResult(true);
		try {
			if(StringUtils.isNotBlank(temp.getId())){//更新
				ProgramInfo p = programInfoService.get(temp.getId());
				BeanUtils.copyPropertiesIgnoreNull(temp, p);
				DictUtils.operationCodeInfo(p);//将CodeInfo中id为空的属性 设置为null
				programInfoService.saveOrUpdate(p);//保存
			}else{//新增
				if(colType==null||colType.length==0){
					throw new BusinessException("请选择该方案的数据条件！");
				}
				if(colType.length!=operation.length||colType.length!=condition.length||colType.length!=colName.length||colType.length!=tableName.length){
					throw new BusinessException("方案的条件数据不正确！");
				}
				StringBuffer resultSql = new StringBuffer("select A01.* from ");//最终生成sql
				StringBuffer tableName2 = new StringBuffer();//拼接的表名
				StringBuffer tableCondition = new StringBuffer();//表之间的关联条件
				StringBuffer condition2 = new StringBuffer();//拼接的查询条件
				Set<String> tableSet =  new HashSet<>();//将表名去重
				tableSet.add("A01");//A01表必需
				for(int i=0;i<colType.length;i++){//遍历条件
					String colType2 = colType[i];
					if("logic".equals(colType2)){//逻辑运算符，or,and,(,)这些
						if("& #40;".equals(condition[i])){//括号需要判断，直接写死，因为后台接收的是已经转码的数据
							condition2.append(" ( ");
						}else if("& #41;".equals(condition[i])){
							condition2.append(" ) ");
						}else{
							condition2.append(" ").append(condition[i]).append(" ");
						}
					}else{
						tableSet.add(tableName[i]);
						if("1".equals(colType2)){//字符串
							condition2.append(" ").append(colName[i]).append(" ");
							if("like".equals(operation[i].trim())){
								condition2.append(operation[i]).append(" '%").append(condition[i]).append("%' ");
							}else if("lt".equals(operation[i].trim())){
								condition2.append(" < '").append(condition[i]).append("' ");
							}else if("gt".equals(operation[i].trim())){
								condition2.append(" > '").append(condition[i]).append("' ");
							}else{
								condition2.append(operation[i]).append(" '").append(condition[i]).append("' ");
							}
						}else if("2".equals(colType2)){//日期
							
						}else if("3".equals(colType2)){//数据字典
							
						}
					}
				}
				//计算查询哪些表，生成拼接表名和关联条件
				String table2 = "";
				String tableCondition2 = "";
				if(tableSet.size()==1){
					table2 = " A01 ";
				}else{
					for(String table : tableSet){
						tableName2.append(",").append(table);
						if(!"A01".equals(table)){//基本信息表不生成表关联条件
							tableCondition.append(" and A01.ID=").append(table).append(".SERVANT_ID(+)");
						}
					}
					table2 = tableName2.substring(1, tableName2.length());
					tableCondition2 = tableCondition.substring(4, tableCondition.length());
				}
				//拼接表名-表之间关联条件-查询条件
				if("".equals(tableCondition2)){//单表查询
					resultSql.append(table2).append(" where removed='N' AND ( ").append(condition2).append(" ) ");
				}else{
					resultSql.append(table2).append(" where ").append(tableCondition2).append(" AND A01.removed='N' AND ( ").append(condition2).append(" ) ");
				}
				
				temp.setResultSql(resultSql.toString());
				DictUtils.operationCodeInfo(temp);//将CodeInfo中id为空的属性 设置为null
				temp.setProgramState(ProgramInfo.PREVIEW);//默认未启动
				programInfoService.saveOrUpdate(temp);
			}
			result.setMessage("保存成功！");
		} catch (BusinessException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("保存失败！");
		}
		return result;
	}
	/**
	 * @Title: delete 
	 * @Description: 删除预警预告方案
	 * @param id	方案ID
	 * @return
	 * @return: AjaxResult
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(String id){
		AjaxResult result = new AjaxResult(true);
		try {
			ProgramInfo info = programInfoService.get(id);
			programInfoService.delete(info);
			result.setMessage("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("删除失败！");
		}
		return result;
	}
	/**
	 * @Title: orgRelations 
	 * @Description: 加载树
	 * @param id
	 * @return
	 * @return: List<HashMap<String,Object>>
	 */
	@ResponseBody
	@RequestMapping("/tree")
	public List<HashMap<String, Object>> tree(String id,String type) {
		
		List<HashMap<String, Object>> treeData = new ArrayList<HashMap<String, Object>>();
		DetachedCriteria criteria = DetachedCriteria.forClass(ProgramTree.class);
		if(StringUtils.isNotBlank(id)){
			criteria.add(Restrictions.eq("parent.id", id));
		}
		if(StringUtils.isNotBlank(type)){
			criteria.add(Restrictions.eq("dataType", type));
		}
		criteria.addOrder(Order.asc("createTime"));
		criteria.add(Restrictions.eq("removed", false));
		List<ProgramTree> list = programTreeService.findByCriteria(criteria);
		for(ProgramTree p:list){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", p.getId());
			map.put("name", p.getName());
			map.put("code", p.getCode());
			map.put("colType", p.getColType());
			map.put("codeValue", p.getCodeValue());
			if(p.getParent()!=null){
				map.put("parentId", p.getParent().getId());
				map.put("parentName", p.getParent().getName());
				map.put("tableName", p.getParent().getCode());
			}else{
				map.put("parentName", null);
				map.put("tableName", null);
				map.put("parentId", null);
			}
			treeData.add(map);
		}
		return treeData;
	}
}
