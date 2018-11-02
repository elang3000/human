/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: FlowRecordController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 流程记录控制器
 * 创建人: tzy   
 * 创建时间: 2018年8月22日 下午7:34:42 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年8月22日 下午7:34:42 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.FlowRecordService;
import com.wondersgroup.framework.workflow.vo.FlowRecordVO;

/** 
 * @ClassName: FlowRecordController 
 * @Description: 流程记录控制器
 * @author: tzy
 * @date: 2018年8月22日 下午7:34:42
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/flow")
public class FlowRecordController extends GenericController{
	@Autowired
	private FlowRecordService  flowRecordService;
	/**
	 * @Title: intoList 
	 * @Description: 待办列表
	 * @param request
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<FlowRecordVO>
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Page<FlowRecordVO> list(HttpServletRequest request,Integer limit,Integer page) {
		String busType = request.getParameter("busType");//业务类型
		String busTypes = request.getParameter("busTypes");//多 业务类型
		String sourceOrganNode = request.getParameter("sourceOrganNode");//发文单位
		String ofcFlowState = request.getParameter("ofcFlowState");//0--已办，1--待办
		if(StringUtils.isBlank(ofcFlowState)){
			ofcFlowState="1";
		}
		if (page == null || page == 0)
			page = 1;
		List<QueryParameter> listqueryparameter=new ArrayList<>();
		StringBuilder hql=new StringBuilder();
		hql.append("from FlowRecord where removed=:removed and ofcFlowState =:ofcFlowState and targetSecurityUser.id =:targetSecurityUser ");
		QueryParameter queryParameteritem=new QueryParameter("removed", false);
		listqueryparameter.add(queryParameteritem);
		listqueryparameter.add(new QueryParameter("ofcFlowState", Integer.parseInt(ofcFlowState)));
		listqueryparameter.add(new QueryParameter("targetSecurityUser", SecurityUtils.getUserId()));
		if(StringUtils.isNotBlank(busType)){
			hql.append( " and busType =:busType");
			queryParameteritem=new QueryParameter("busType", busType);
			listqueryparameter.add(queryParameteritem);
		}
		if(StringUtils.isNotBlank(busTypes)){
			hql.append( " and busType in( ");
			String[] busArr = busTypes.split(",");
			for(int i=0;i<busArr.length;i++){
				if(i==0){
					hql.append(":busType"+i);
				}else{
					hql.append(",:busType"+i);
				}
				listqueryparameter.add(new QueryParameter("busType"+i, busArr[i]));
			}
			hql.append( " ) ");
		}
		if(StringUtils.isNotBlank(sourceOrganNode)){
			hql.append( " and sourceOrganNode.id =:sourceOrganNode");
			queryParameteritem=new QueryParameter("sourceOrganNode", sourceOrganNode);
			listqueryparameter.add(queryParameteritem);
		}
		hql.append( " order by createTime desc");
		
		Map<String,Object> params = new HashMap<>();
		params.put("ofcFlowState", FlowRecord.DOING);
		params.put("targetSecurityUser", SecurityUtils.getUserId());
		params.put("busType", busType);
		params.put("sourceOrganNode", sourceOrganNode);
		Page<FlowRecordVO> pageInfo = flowRecordService.findFlowRecord(params,page,limit,true);

		return pageInfo;
	}
	/**
	 * @Title: busList 
	 * @Description: 业务数据：已办理情况列表
	 * @param request
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<FlowRecordVO>
	 */
	@ResponseBody
	@RequestMapping("/busList")
	public Page<FlowRecordVO> busList(HttpServletRequest request,Integer limit,Integer page) {
		String busId = request.getParameter("busId");//业务id
		if (page == null || page == 0)
			page = 1;
		
		Map<String,Object> params = new HashMap<>();
		params.put("busId", busId);
		params.put("ofcFlowState", FlowRecord.DONE);
		Page<FlowRecordVO> pageInfo = flowRecordService.findFlowRecord(params,page,limit,true);
		
		return pageInfo;
	}
}
