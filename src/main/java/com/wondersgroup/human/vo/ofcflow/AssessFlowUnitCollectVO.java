package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.framework.core.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class AssessFlowUnitCollectVO {
	




	public AssessFlowUnitCollectVO(Map<String,Object> map){
		if(map.get("ID")!=null)
			this.id=(String)map.get("ID");
		if(map.get("YEAR")!=null)
			this.year=((BigDecimal)map.get("YEAR")).intValue();
		if(map.get("SEASON")!=null)
			this.season=((BigDecimal)map.get("SEASON")).intValue();
		if(map.get("REMARK")!=null)
			this.remark=(String)map.get("REMARK");
		if(map.get("DRAFT_OUTSTANDING_PERCENT")!=null)
			this.draftOutstandingPercent=((BigDecimal)map.get("DRAFT_OUTSTANDING_PERCENT")).intValue();
		if(map.get("ASSESSMENT_TYPE")!=null)
			this.assessmentType=((BigDecimal)map.get("ASSESSMENT_TYPE")).intValue();
		if(map.get("FLOW_STATUS")!=null)
			this.flowStatus=((BigDecimal)map.get("FLOW_STATUS")).intValue();
		if(map.get("STATUS")!=null)
			this.setStatus(((BigDecimal)map.get("STATUS")).intValue());
		if(map.get("CREATE_TIME")!=null){
			this.createTime=(Timestamp)map.get("CREATE_TIME");
		}
		if(map.get("ALLNUM")!=null){
			this.allNum=((BigDecimal)map.get("ALLNUM")).intValue();
		}
		if(map.get("ASSESSNUM")!=null){
			this.assessNum=((BigDecimal)map.get("ASSESSNUM")).intValue();
		}
	}
	
	// 年度考核
	public static final Integer ASSESSYEAR = 1;
	
	// 月度考核
	public static final Integer ASSESSSEASON = 2;
	
	// 年度考核
	public static final String ASSESSYEARSTR = "年度考核";
	
	// 月度考核
	public static final String ASSESSSEASONSTR = "季度考核";
	
	private String id;
	// 考核年度
	private Integer year;
	
	//考核状态 0 未完成 1完成
	private int status;
	
	// 考核季度
	private Integer season;
	
	// 考核类别,年度考核还是季度考核
	private Integer assessmentType;
	
	//备注
	private String remark;
	
	//拟考核优秀人员百分比
	private Integer draftOutstandingPercent;
	
	// 考核状态       -1 未开始流程  0开始流程 1完成流程
	private Integer flowStatus;
	
	private Timestamp createTime;


	private Integer allNum;

	private Integer assessNum;
	
	
	
	public String getCreateTime() {
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return 	sdf.format(createTime);
	}


	
	public void setCreateTime(Timestamp createTime) {
		
		this.createTime = createTime;
	}


	public Integer getFlowStatus() {
		
		return flowStatus;
	}

	
	public void setFlowStatus(Integer flowStatus) {
		
		this.flowStatus = flowStatus;
	}

	public Integer getYear() {
		
		return year;
	}
	
	public void setYear(Integer year) {
		
		this.year = year;
	}
	
	public Integer getSeason() {
		
		return season;
	}
	
	public String getSeasonStr() {
		if(season!=null){
			String seasonStr="第"+season+"季度";
			return seasonStr;
		}
		else{
			return "-";
		}
	}
	
	public void setSeason(Integer season) {
		
		this.season = season;
	}
	
	public Integer getAssessmentType() {
		
		return assessmentType;
	}
	
	public void setAssessmentType(Integer assessmentType) {
		
		this.assessmentType = assessmentType;
	}
	
	
	
	public String getRemark() {
		if(StringUtils.isBlank(remark)){
			return "-";
		}
		return remark;
	}

	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}

	
	public String getDraftOutstandingPercentStr() {
		if(null==draftOutstandingPercent){
			return "-";
		}else if(draftOutstandingPercent>100){
			return 100+"";
		}else if(draftOutstandingPercent<0){
			return 0+"";
		}else{
			return draftOutstandingPercent+"";
		}
		
	}
	
	public Integer getDraftOutstandingPercent() {
		return draftOutstandingPercent;
	}

	
	public void setDraftOutstandingPercent(Integer draftOutstandingPercent) {
		
		this.draftOutstandingPercent = draftOutstandingPercent;
	}
	
	//获取考核类型名称
	public String getAssessmentTypeStr() {
		
		if(this.assessmentType==ASSESSYEAR){
			return ASSESSYEARSTR;
		}
		else if(this.assessmentType==ASSESSSEASON){
			return ASSESSSEASONSTR;
		}else{
			throw new BusinessException("考核类型错误!");
		}
	}
		


	public String getId() {
		
		return id;
	}



	public void setId(String id) {
		
		this.id = id;
	}



	public int getStatus() {
		
		return status;
	}
	
	public String getStatusStr() {
		if(status==1){
			return "已经完成";
		}
		return "未完成";
	}


	public String getUnitStatus(){
		if(this.assessmentType==ASSESSYEAR){
			if(null==flowStatus){
				return "";
			}else{
				// 考核状态       -1 未开始流程  0开始流程 1完成流程
				if(0==flowStatus){
					return "流程开始";
				}
				if(1==flowStatus){
					return "考核完成";
				}
				if(-1==flowStatus){
					return "考核未完成";
				}
				return "考核未完成";
			}

		}
		else if(this.assessmentType==ASSESSSEASON){
			if(allNum!=null&&assessNum!=null&&allNum==assessNum){
				return "考核完成";
			}
			return "考核未完成";
		}
		return "";
	}



	public void setStatus(int status) {
		
		this.status = status;
	}


	public Integer getAllNum() {
		return allNum;
	}

	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}

	public Integer getAssessNum() {
		return assessNum;
	}

	public void setAssessNum(Integer assessNum) {
		this.assessNum = assessNum;
	}

}
