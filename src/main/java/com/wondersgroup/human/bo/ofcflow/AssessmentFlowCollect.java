
package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.ibm.icu.text.SimpleDateFormat;
import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.util.DateUtils;

/**
 * @ClassName: AssessmentFlowCollect
 * @Description: 考核计划表
 * @author: youyd
 * @date: 2018年9月7日 下午2:56:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_ASSESSMENT_FLOW_COLLECT")
public class AssessmentFlowCollect extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 默认序列号
	 */
	private static final long serialVersionUID = 1L;
	
	// 年度考核
	public static final Integer ASSESSYEAR = 1;
	
	// 月度考核
	public static final Integer ASSESSSEASON = 2;
	
	// 年度考核
	public static final String ASSESSYEARSTR = "年度考核";
	
	// 月度考核
	public static final String ASSESSSEASONSTR = "季度考核";
	
	// 考核年度
	@NotNull(message = "考核年度不能为空!")
	@Column(name = "YEAR")
	private Integer year;
	
	// 考核季度
	@Column(name = "SEASON")
	private Integer season;
	
	// 考核类别,年度考核还是季度考核
	@NotNull(message = "考核类型不能为空!")
	@Column(name = "ASSESSMENT_TYPE")
	private Integer assessmentType;
	
	//备注
	@Column(name = "REMARK", length = 2000)
	private String remark;
	
	//拟考核优秀人员百分比
	@Column(name="DRAFT_OUTSTANDING_PERCENT")
	private Integer draftOutstandingPercent;
	
	//状态 0考核未完成 1考核完成并且关闭
	@Column(name="STATUS")
	private Integer status;
	
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
		
	//获取创建时间
	public String getCreateTimeStr(){
		return DateUtils.format(super.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
	}

	public Integer getStatus() {
		
		return status;
	}

	public void setStatus(Integer status) {
		
		this.status = status;
	}

	
}
