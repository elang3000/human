
package com.wondersgroup.human.bo.ofcflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: JobShiftCollect
 * @Description: 职务变动计划表
 * @author: youyd
 * @date: 2018年12月25日 下午2:53:25
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_SERVANT_JOBSHIFT_COLLECT")
public class JobShiftCollect extends BaseFlowOrgFormation<Object> {
	
	private static final long serialVersionUID = 1L;
	
	public static final Integer JOBSHIFT_TOBESUBMIT=0;

	public static  List<String> allSteps;


	// 1职务降职流程code
	public static final String JOBSHIFTB_PROMOTE_STEP1 = "STATUS_JOBSHIFTB_PROMOTE_STEP1";

	public static final String JOBSHIFTB_PROMOTE_STEP2 = "STATUS_JOBSHIFTB_PROMOTE_STEP2";

	public static final String JOBSHIFTB_PROMOTE_STEP3 = "STATUS_JOBSHIFTB_PROMOTE_STEP3";

	public static final String JOBSHIFTB_PROMOTE_STEP4 = "STATUS_JOBSHIFTB_PROMOTE_STEP4";

	public static final String JOBSHIFTB_PROMOTE_STEP5 = "STATUS_JOBSHIFTB_PROMOTE_STEP5";

	public static final String JOBSHIFTB_PROMOTE_STEP6 = "STATUS_JOBSHIFTB_PROMOTE_STEP6";

	public static final String JOBSHIFTB_PROMOTE_STEP7 = "STATUS_JOBSHIFTB_PROMOTE_STEP7";

	public static final String JOBSHIFTB_PROMOTE_STEP8 = "STATUS_JOBSHIFTB_PROMOTE_STEP8";

	public static final String JOBSHIFTB_PROMOTE_STEP9 = "STATUS_JOBSHIFTB_PROMOTE_STEP9";

	public static final String JOBSHIFTB_PROMOTE_STEP10 = "STATUS_JOBSHIFTB_PROMOTE_STEP10";

	public static final String JOBSHIFTB_PROMOTE_STEP11 = "STATUS_JOBSHIFTB_PROMOTE_STEP11";

	public static final String JOBSHIFTB_PROMOTE_STEP12 = "STATUS_JOBSHIFTB_PROMOTE_STEP12";
	
	public static final String JOBSHIFTB_PROMOTE_STEP13 = "STATUS_JOBSHIFTB_PROMOTE_STEP13";
	
	static {
		allSteps = new ArrayList<>();
		allSteps.add(JOBSHIFTB_PROMOTE_STEP1 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP2 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP3 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP4 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP5 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP6 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP7 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP8 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP9 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP10 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP11 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP12 );
		allSteps.add(JOBSHIFTB_PROMOTE_STEP13 );
	}

	// 职务变动计划表名称
	@Column(name = "COLLECTNAME")
	private String collectName;
	
	// 职务变动计划类别 使用FlowBusTypeConstant 静态量
	@Column(name = "COLLECTTYPE", length = 20)
	private String collectType;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	// 批次人数
	/**
	 * @fieldName: sumNumber
	 * @fieldType: java.lang.Integer
	 * @Description: 转任总人数。
	 */
	@Column(name = "SUMNUMBER", length = 4)
	private Integer sumNumber;
	
	/**
	 * @fieldName: plusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description:正科级（领导）转任人数。
	 */
	@Column(name = "PLUSKE_LEADER_NUM", length = 4)
	private Integer plusKeLeaderNum;
	
	/**
	 * @fieldName: plusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description:正科级（非领导）转任人数。
	 */
	@Column(name = "PLUSKE_NOLEADER_NUM", length = 4)
	private Integer plusKeNoLeaderNum;
	
	/**
	 * @fieldName: minusKeLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description:副科级（领导）转任人数。
	 */
	@Column(name = "MINUSKE_LEADER_NUM", length = 4)
	private Integer minusKeLeaderNum;
	
	/**
	 * @fieldName: minusKeNoLeaderNum
	 * @fieldType: java.lang.Integer
	 * @Description:副科级（非领导）转任人数。
	 */
	@Column(name = "MINUSKE_NOLEADER_NUM", length = 4)
	private Integer minusKeNoLeaderNum;
	
	/**
	 * @fieldName: clerkNumber
	 * @fieldType: java.lang.Integer
	 * @Description:科员转任人数。
	 */
	@Column(name = "CLERK_NUMBER", length = 4)
	private Integer clerkNumber;
	
	/**
	 * @fieldName: cClerkNumber
	 * @fieldType: java.lang.Integer
	 * @Description:办事员转任人数。
	 */
	@Column(name = "C_CLERK_NUMBER", length = 4)
	private Integer cClerkNumber;
	
	/**
	 * @fieldName: filePath
	 * @fieldType: java.lang.String
	 * @Description: 附件FTP路径。
	 */
	@Column(name = "FILE_PATH", length = 2000)
	private String filePath;
	
	
	@Column(name = "STATUS", length = 2)
	private Integer status;
	
	@Column(name = "REMARK", length = 2000)
	private String remark;

	/**
	 * @fieldName: sourceOrganNode
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 业务流程发起组织部门
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private OrganNode sourceOrganNode;
	
	
	/**
	 * 人员信息任免表文件路径
	 */
	@Column(name = "APPOINTSHEETFILEPATH",length=1000)
	private String appointSheetFilePath;
	
	/**
	 * 考核材料文件路径
	 */
	@Column(name = "EXAMINEFILEPATH",length=1000)
	private String examineFilePath;
	
	/**
	 * 公示人员信息附件文件路径
	 */
	@Column(name = "PERSONINFOFILEPATH",length=1000)
	private String personInfoFilePath;

	@Column(name="isDownLoad")
	private Integer isDownLoad;
	
	
	public String getAppointSheetFilePath() {
		
		return appointSheetFilePath;
	}




	
	public void setAppointSheetFilePath(String appointSheetFilePath) {
		
		this.appointSheetFilePath = appointSheetFilePath;
	}




	
	public String getExamineFilePath() {
		
		return examineFilePath;
	}




	
	public void setExamineFilePath(String examineFilePath) {
		
		this.examineFilePath = examineFilePath;
	}




	
	public String getPersonInfoFilePath() {
		
		return personInfoFilePath;
	}




	
	public void setPersonInfoFilePath(String personInfoFilePath) {
		
		this.personInfoFilePath = personInfoFilePath;
	}




	public String getRemark() {
		
		return remark;
	}



	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}



	public Integer getStatus() {
		
		return status;
	}


	
	public void setStatus(Integer status) {
		
		this.status = status;
	}


	public String getCollectName() {
		
		return collectName;
	}

	
	public void setCollectName(String collectName) {
		
		this.collectName = collectName;
	}

	
	public String getCollectType() {
		
		return collectType;
	}

	
	public void setCollectType(String collectType) {
		
		this.collectType = collectType;
	}

	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}

	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}

	
	public Integer getSumNumber() {
		
		return sumNumber;
	}

	
	public void setSumNumber(Integer sumNumber) {
		
		this.sumNumber = sumNumber;
	}

	
	public Integer getPlusKeLeaderNum() {
		
		return plusKeLeaderNum;
	}

	
	public void setPlusKeLeaderNum(Integer plusKeLeaderNum) {
		
		this.plusKeLeaderNum = plusKeLeaderNum;
	}

	
	public Integer getPlusKeNoLeaderNum() {
		
		return plusKeNoLeaderNum;
	}

	
	public void setPlusKeNoLeaderNum(Integer plusKeNoLeaderNum) {
		
		this.plusKeNoLeaderNum = plusKeNoLeaderNum;
	}

	
	public Integer getMinusKeLeaderNum() {
		
		return minusKeLeaderNum;
	}

	
	public void setMinusKeLeaderNum(Integer minusKeLeaderNum) {
		
		this.minusKeLeaderNum = minusKeLeaderNum;
	}

	
	public Integer getMinusKeNoLeaderNum() {
		
		return minusKeNoLeaderNum;
	}

	
	public void setMinusKeNoLeaderNum(Integer minusKeNoLeaderNum) {
		
		this.minusKeNoLeaderNum = minusKeNoLeaderNum;
	}

	
	public Integer getClerkNumber() {
		
		return clerkNumber;
	}

	
	public void setClerkNumber(Integer clerkNumber) {
		
		this.clerkNumber = clerkNumber;
	}

	
	public Integer getcClerkNumber() {
		
		return cClerkNumber;
	}

	
	public void setcClerkNumber(Integer cClerkNumber) {
		
		this.cClerkNumber = cClerkNumber;
	}

	
	public String getFilePath() {
		
		return filePath;
	}

	
	public void setFilePath(String filePath) {
		
		this.filePath = filePath;
	}




	public OrganNode getSourceOrganNode() {
		
		return sourceOrganNode;
	}




	public void setSourceOrganNode(OrganNode sourceOrganNode) {
		
		this.sourceOrganNode = sourceOrganNode;
	}


	public Integer getIsDownLoad() {
		return isDownLoad;
	}

	public void setIsDownLoad(Integer isDownLoad) {
		this.isDownLoad = isDownLoad;
	}
}
