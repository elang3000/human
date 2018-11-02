
package com.wondersgroup.human.vo.ofcflow;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 
 * @ClassName: AssessProgressVO 
 * @Description: 公务员考核各单位情况
 * @author: youyd
 * @date: 2018年9月28日 上午11:13:37
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class AssessmentFlowUnitPercentVO {
	
	public AssessmentFlowUnitPercentVO(Map<String,Object> map){
		id=(String)map.get("ID");
		departName=(String)map.get("DEPARTNAME");
		if(map.get("USERCOUNT")!=null)
		userCount=((BigDecimal)map.get("USERCOUNT")).intValue();
		if(map.get("ASSESSCOUNT")!=null)
		assessCount=((BigDecimal)map.get("ASSESSCOUNT")).intValue();
		if(map.get("STATUS")!=null)
			status=((BigDecimal)map.get("STATUS")).intValue();
		if(map.get("FLOW_STATUS")!=null)
			setFlowStatus(((BigDecimal)map.get("FLOW_STATUS")).intValue());
		if(map.get("OUTSTANDING_NUMB")!=null)
			outstanding_numb=((BigDecimal)map.get("OUTSTANDING_NUMB")).intValue();
		if(map.get("UNIT_OUTSTANDING_NUMB")!=null)
			unit_outstanding_numb=((BigDecimal)map.get("UNIT_OUTSTANDING_NUMB")).intValue();
		if(map.get("CONFIRMCOUNT")!=null)
			confirmCount=((BigDecimal)map.get("CONFIRMCOUNT")).intValue();
	}
	
	private static final String FLOW_FINSH="已经完成";
	private static final String FLOW_UNSUBMIT="未发起";
	private static final String FLOW_UNFINISH="流程未完成";
	private static final String CONFIRM_YES="已经完成考核";
	private static final String CONFIRM_NO="暂未完成";


	
	//单位id
	private String id;
	
	//单位名称
	private String departName;
	
	//单位考核人员总数
	private Integer userCount;
	
	//已经考核人员数量
	private Integer assessCount;
	
	//是否完成流程状态
	private Integer status;
	
	//是否完成流程状态
	private Integer flowStatus;
	
	//人社局指定人数
	private Integer outstanding_numb;
	
	//单位流程中申请的人数
	private Integer unit_outstanding_numb;
	
	//单位流程中申请的人数
	private Integer confirmCount;
	
	
	public Integer getStatus() {
		
		return status;
	}


	
	public void setStatus(Integer status) {
		
		this.status = status;
	}


	
	public Integer getOutstanding_numb() {
		
		return outstanding_numb;
	}


	
	public void setOutstanding_numb(Integer outstanding_numb) {
		
		this.outstanding_numb = outstanding_numb;
	}


	
	public Integer getUnit_outstanding_numb() {
		
		return unit_outstanding_numb;
	}


	
	public void setUnit_outstanding_numb(Integer unit_outstanding_numb) {
		
		this.unit_outstanding_numb = unit_outstanding_numb;
	}


	public String getId() {
		
		return id;
	}

	
	public void setId(String id) {
		
		this.id = id;
	}

	public String getDepartName() {
		
		return departName;
	}
	
	public void setDepartName(String departName) {
		
		this.departName = departName;
	}
	
	public Integer getUserCount() {
		
		return userCount;
	}
	
	public void setUserCount(Integer userCount) {
		
		this.userCount = userCount;
	}
	
	public Integer getAssessCount() {
		
		return assessCount;
	}
	
	public void setAssessCount(Integer assessCount) {
		
		this.assessCount = assessCount;
	}
	
	
	private boolean isConfirm(){
		if(userCount==confirmCount){
			return true;
		}else{
			return false;
		}
	}
	
	public String getIsConfirmStr(){
		if(isConfirm()){
			return CONFIRM_YES;
		}else{
			return CONFIRM_NO;
		}
	}
	
	public String getFinishFlowStr(){
		if(flowStatus==null){
			return null;
		}
		if(flowStatus==1){
			return FLOW_FINSH;
		}else if(flowStatus==0){
			return FLOW_UNFINISH;
		}else{
			return FLOW_UNSUBMIT;
		}
	}



	public Integer getConfirmCount() {
		
		return confirmCount;
	}



	public void setConfirmCount(Integer confirmCount) {
		
		this.confirmCount = confirmCount;
	}



	public Integer getFlowStatus() {
		
		return flowStatus;
	}



	public void setFlowStatus(Integer flowStatus) {
		
		this.flowStatus = flowStatus;
	}
	
	
	
}
