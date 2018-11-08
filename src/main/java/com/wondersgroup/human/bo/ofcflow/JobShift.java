
package com.wondersgroup.human.bo.ofcflow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;

/**
 * @ClassName: JobShift
 * @Description: 职务变动业务类
 * @author: youyd
 * @date: 2018年9月7日 下午2:56:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_SERVANT_JOBSHIFT")
public class JobShift extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 1L;
	
	// 晋升流程的长度
	public static final int PROMOTE_FLOW_LENGTH = 17;
	
	// 降级流程的长度
	public static final int DEMOTE_FLOW_LENGTH = 6;
	
	// 免职流程的长度
	public static final int DEPOSE_FLOW_LENGTH = 6;
	
	// 轮岗流程的长度
	public static final int SHIFT_FLOW_LENGTH = 6;
	
	// 职务变动流程节点
	
	// 1职务降职流程code
	public static final String JOBSHIFT_PROMOTE_STEP1 = "STATUS_JOBSHIFT_PROMOTE_STEP1";
	
	public static final String JOBSHIFT_PROMOTE_STEP2 = "STATUS_JOBSHIFT_PROMOTE_STEP2";
	
	public static final String JOBSHIFT_PROMOTE_STEP3 = "STATUS_JOBSHIFT_PROMOTE_STEP3";
	
	public static final String JOBSHIFT_PROMOTE_STEP4 = "STATUS_JOBSHIFT_PROMOTE_STEP4";
	
	public static final String JOBSHIFT_PROMOTE_STEP5 = "STATUS_JOBSHIFT_PROMOTE_STEP5";
	
	public static final String JOBSHIFT_PROMOTE_STEP6 = "STATUS_JOBSHIFT_PROMOTE_STEP6";
	
	public static final String JOBSHIFT_PROMOTE_STEP7 = "STATUS_JOBSHIFT_PROMOTE_STEP7";
	
	public static final String JOBSHIFT_PROMOTE_STEP8 = "STATUS_JOBSHIFT_PROMOTE_STEP8";
	
	public static final String JOBSHIFT_PROMOTE_STEP9 = "STATUS_JOBSHIFT_PROMOTE_STEP9";
	
	public static final String JOBSHIFT_PROMOTE_STEP10 = "STATUS_JOBSHIFT_PROMOTE_STEP10";
	
	public static final String JOBSHIFT_PROMOTE_STEP11 = "STATUS_JOBSHIFT_PROMOTE_STEP11";
	
	public static final String JOBSHIFT_PROMOTE_STEP12 = "STATUS_JOBSHIFT_PROMOTE_STEP12";
	
	public static final String JOBSHIFT_PROMOTE_STEP13 = "STATUS_JOBSHIFT_PROMOTE_STEP13";
	
	public static final String JOBSHIFT_PROMOTE_STEP14 = "STATUS_JOBSHIFT_PROMOTE_STEP14";
	
	public static final String JOBSHIFT_PROMOTE_STEP15 = "STATUS_JOBSHIFT_PROMOTE_STEP15";
	
	public static final String JOBSHIFT_PROMOTE_STEP16 = "STATUS_JOBSHIFT_PROMOTE_STEP16";
	
	// 消息通知变量
	public static final String PROMOTE_TITLE = "jobShiftPromoteTitle";
	
	public static final String PROMOTE_CONTENT = "jobShiftPromoteContent";
	
	// 2职务降职流程code
	public static final String JOBSHIFT_DEMOTE_STEP1 = "STATUS_JOBSHIFT_DEMOTE_STEP1";
	
	public static final String JOBSHIFT_DEMOTE_STEP2 = "STATUS_JOBSHIFT_DEMOTE_STEP2";
	
	public static final String JOBSHIFT_DEMOTE_STEP3 = "STATUS_JOBSHIFT_DEMOTE_STEP3";
	
	public static final String JOBSHIFT_DEMOTE_STEP4 = "STATUS_JOBSHIFT_DEMOTE_STEP4";
	
	public static final String JOBSHIFT_DEMOTE_STEP5 = "STATUS_JOBSHIFT_DEMOTE_STEP5";
	
	public static final String JOBSHIFT_DEMOTE_STEP6 = "STATUS_JOBSHIFT_DEMOTE_STEP6";
	
	public static final String JOBSHIFT_DEMOTE_STEP7 = "STATUS_JOBSHIFT_DEMOTE_STEP7";
	// 职务
	
	// 人员
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT")
	private Servant servant;
	
	// 原职位
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PREPOST")
	private Post prePost;
	
	/*
	 * //操作后职位
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * @JoinColumn(name = "POSTPOST")
	 * private Post postPost;
	 */
	
	/*	*//**
	       * @fieldName: 原单位名称
	       * @fieldType: java.lang.String
	       * @Description: 与该人原任职务相对应的工作机构及工作机构部门名称。
	       */
	/*
	 * @Column(name = "FORMERUNITNAME", length = 120)
	 * private String formerUnitName;
	 *//**
	   * @fieldName: 原单位代码
	   * @fieldType: java.lang.String
	   * @Description: 与该人原任职务相对应的工作机构及工作机构部门名称。GB 32100-2015
	   */
	/*
	 * @Column(name = "FORMERUNITCODE", length = 15)
	 * private String formerUnitCode;
	 *//**
	   * @fieldName: 新单位名称
	   * @fieldType: java.lang.String
	   * @Description: 与该人新任职务相对应的工作机构及工作机构部门名称。
	   */
	/*
	 * @Column(name = "NEWUNITNAME", length = 120)
	 * private String newUnitName;
	 *//**
	   * @fieldName: 新单位代码
	   * @fieldType: java.lang.String
	   * @Description: 与该人新任职务相对应的工作机构及工作机构部门代码。GB 32100-2015
	   *//*
	     * @Column(name = "NEWUNITCODE", length = 15)
	     * private String newUnitCode;
	     */
	
	/**
	 * @fieldName: 原职务名称
	 * @fieldType: java.lang.String
	 * @Description: 该人原任职务的名称。
	 */
	@Column(name = "FORMERPOSTNAME", length = 80)
	private String formerPostName;
	
	/**
	 * @fieldName: 原职务代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人原任职务的代码。GB/T 12403-1990
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMERPOSTCODE")
	private CodeInfo formerPostCode;
	
	/**
	 * @fieldName: 新职务名称
	 * @fieldType: java.lang.String
	 * @Description: 该人新任职务的名称。
	 */
	@Column(name = "NEWPOSTNAME", length = 80)
	private String newPostName;
	
	/**
	 * @fieldName: 新职务代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务的代码。GB/T 12403-1990
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NEWPOSTCODE")
	private CodeInfo newPostCode;
	
	/**
	 * @fieldName: 原职务级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人原任职务的等级。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMERJOBLEVEL")
	private CodeInfo formerJobLevel;
	
	/**
	 * @fieldName: 新职务级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务的等级。GB/T 12407-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NEWJOBLEVEL")
	private CodeInfo newJobLevel;
	
	/**
	 * @fieldName: 原职务类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人原任职务在相应的工作机构的领导班子中的职务类别。DM049
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORMERPOSTATTRIBUTE")
	private CodeInfo formerPostAttribute;
	
	/**
	 * @fieldName: 新职务类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务在相应的工作机构的领导班子中的职务类别。DM049
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NEWPOSTATTRIBUTE")
	private CodeInfo newPostAttribute;
	
	/**
	 * @fieldName:职务变动日期
	 * @fieldType: java.util.Date
	 * @Description: 该人此次职务变动发生的日期，一般为由具有法定管理权限的机构签发
	 *               的文件确定的该职务任职或免职的日期或由会议决定该职务任职或免职的日期。GB/T 7408-2005
	 */
	@Column(name = "POSTCHANGEDATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postChangeDate;
	
	/**
	 * @fieldName: 职务变动类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人新任职务相对于原任职务变动的类别划分。DM006
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POSTTENURECHANGE")
	private CodeInfo postTenureChange;
	
	/*	*//**
	       * @fieldName: 是否达到任职年限标识
	       * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	       * @Description: 该人该职务变动是否达到任职年限的标识。DM215
	       *//*
	         * @ManyToOne(fetch = FetchType.LAZY)
	         * @JoinColumn(name = "DEADLINEFLAG")
	         * private CodeInfo deadlineFlag;
	         */
	
	/*	*//**
	       * @fieldName:现任职级确定日期
	       * @fieldType: java.util.Date
	       * @Description: 该人现任职务等级确定的日期。GB/T 7408-2005
	       *//*
	         * @Column(name = "JOBLEVELCONFIRMDATE")
	         * @Temporal(TemporalType.DATE)
	         * @DateTimeFormat(pattern = "yyyy-MM-dd")
	         * private Date jobLevelConfirmDate;
	         */
	
	/**
	 * @fieldName: 晋级原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人晋级的原因。DM113
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROMOTIONTYPE")
	private CodeInfo promotionType;
	
	/**
	 * @fieldName: 降级原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人降级的原因。DM114
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEGRADETYPE")
	private CodeInfo degradeType;
	
	/**
	 * @fieldName: 因处分延长晋级标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人是否因处分而延长晋级。DM215
	 *//*
	   * @ManyToOne(fetch = FetchType.LAZY)
	   * @JoinColumn(name = "PUNISHMENTEXTENDPROMOTIONFLAG")
	   * private CodeInfo punishmentExtendPromotionFlag;
	   */
	
	/*	*//**
	       * @fieldName: 延长晋级时间
	       * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	       * @Description: 该人延长晋级的时间段长度。DM115
	       *//*
	         * @ManyToOne(fetch = FetchType.LAZY)
	         * @JoinColumn(name = "EXTENDPROMOTIONTIME")
	         * private CodeInfo extendPromotionTime;
	         */
	
	/**
	 * 人员信息任免表文件路径
	 */
	@Column(name = "APPOINTSHEETFILEPATH")
	private String appointSheetFilePath;
	
	/**
	 * 考核材料文件路径
	 */
	@Column(name = "EXAMINEFILEPATH")
	private String examineFilePath;
	
	/**
	 * 公示人员信息附件文件路径
	 */
	@Column(name = "PERSONINFOFILEPATH")
	private String personInfoFilePath;
	
	/*
	 * 备注
	 */
	@Column(name = "REMARK", length = 240)
	private String remark;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态从1开始依次加1,总共操作次数
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/**
	 * @fieldName: sourceOrganNode
	 * @fieldType: com.wondersgroup.framework.organization.bo.OrganNode
	 * @Description: 业务流程发起组织部门
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private OrganNode sourceOrganNode;
	
	@Column(name = "LOWTOHIGH")
	@org.hibernate.annotations.Type(type = "yes_no")
	private boolean lowToHigh;
	
	public boolean isLowToHigh() {
		
		return lowToHigh;
	}

	
	public void setLowToHigh(boolean lowToHigh) {
		
		this.lowToHigh = lowToHigh;
	}

	public Integer getStatus() {
		
		return status;
	}
	
	public void setStatus(Integer status) {
		
		this.status = status;
	}
	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}
	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}
	
	public Post getPrePost() {
		
		return prePost;
	}
	
	public void setPrePost(Post prePost) {
		
		this.prePost = prePost;
	}
	
	/*
	 * public Post getPostPost() {
	 * return postPost;
	 * }
	 * public void setPostPost(Post postPost) {
	 * this.postPost = postPost;
	 * }
	 */
	
	public OrganNode getSourceOrganNode() {
		
		return sourceOrganNode;
	}
	
	public void setSourceOrganNode(OrganNode sourceOrganNode) {
		
		this.sourceOrganNode = sourceOrganNode;
	}
	
	public String getFormerPostName() {
		
		return formerPostName;
	}
	
	public void setFormerPostName(String formerPostName) {
		
		this.formerPostName = formerPostName;
	}
	
	public CodeInfo getFormerPostCode() {
		
		return formerPostCode;
	}
	
	public void setFormerPostCode(CodeInfo formerPostCode) {
		
		this.formerPostCode = formerPostCode;
	}
	
	public String getNewPostName() {
		
		return newPostName;
	}
	
	public void setNewPostName(String newPostName) {
		
		this.newPostName = newPostName;
	}
	
	public CodeInfo getNewPostCode() {
		
		return newPostCode;
	}
	
	public void setNewPostCode(CodeInfo newPostCode) {
		
		this.newPostCode = newPostCode;
	}
	
	public CodeInfo getFormerJobLevel() {
		
		return formerJobLevel;
	}
	
	public void setFormerJobLevel(CodeInfo formerJobLevel) {
		
		this.formerJobLevel = formerJobLevel;
	}
	
	public CodeInfo getNewJobLevel() {
		
		return newJobLevel;
	}
	
	public void setNewJobLevel(CodeInfo newJobLevel) {
		
		this.newJobLevel = newJobLevel;
	}
	
	public CodeInfo getFormerPostAttribute() {
		
		return formerPostAttribute;
	}
	
	public void setFormerPostAttribute(CodeInfo formerPostAttribute) {
		
		this.formerPostAttribute = formerPostAttribute;
	}
	
	public CodeInfo getNewPostAttribute() {
		
		return newPostAttribute;
	}
	
	public void setNewPostAttribute(CodeInfo newPostAttribute) {
		
		this.newPostAttribute = newPostAttribute;
	}
	
	public Date getPostChangeDate() {
		
		return postChangeDate;
	}
	
	public void setPostChangeDate(Date postChangeDate) {
		
		this.postChangeDate = postChangeDate;
	}
	
	public CodeInfo getPostTenureChange() {
		
		return postTenureChange;
	}
	
	public void setPostTenureChange(CodeInfo postTenureChange) {
		
		this.postTenureChange = postTenureChange;
	}
	
	public CodeInfo getPromotionType() {
		
		return promotionType;
	}
	
	public void setPromotionType(CodeInfo promotionType) {
		
		this.promotionType = promotionType;
	}
	
	public CodeInfo getDegradeType() {
		
		return degradeType;
	}
	
	public void setDegradeType(CodeInfo degradeType) {
		
		this.degradeType = degradeType;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
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
	
}
