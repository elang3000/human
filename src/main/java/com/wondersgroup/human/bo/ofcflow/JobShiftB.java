
package com.wondersgroup.human.bo.ofcflow;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
@Table(name = "HUMAN_SERVANT_JOBSHIFTB")
public class JobShiftB extends GenericEntity {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 */
	private static final long serialVersionUID = 1L;
	
	// 晋升流程的长度
	public static final int PROMOTE_FLOW_LENGTH = 13;
	
	// 降级流程的长度
	public static final int DEMOTE_FLOW_LENGTH = 6;
	
	// 免职流程的长度
	public static final int DEPOSE_FLOW_LENGTH = 6;
	
	// 轮岗流程的长度
	public static final int SHIFT_FLOW_LENGTH = 6;
	

	// 消息通知变量
	public static final String PROMOTE_TITLE = "jobShiftPromoteTitle";
	
	public static final String PROMOTE_CONTENT = "jobShiftPromoteContent";
	

	//职务变动计划申请单
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOBSHIFTCOLLECT")
	private JobShiftCollect jobShiftCollect;
	
	// 人员
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT")
	private Servant servant;
	
	// 原职位
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PREPOST")
	private Post prePost;
	
	
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
	@Column(name = "FORMERJOBLEVELNAME")
	private String formerJobLevelName;
	
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
	
	
	/*
	 * 备注
	 */
	@Column(name = "REMARK", length = 240)
	private String remark;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态从1开始依次加1,总共操作次数,假如取消流程的话,status设置为0
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
	@JoinColumn(name = "SOURCEORGANNODE")
	private OrganNode sourceOrganNode;
	
	@Column(name = "LOWTOHIGH")
	@org.hibernate.annotations.Type(type = "yes_no")
	private Boolean lowToHigh;

	/**
	 * @fieldName: highestPostSign
	 * @fieldType: java.lang.String
	 * @Description:最高职务标记，DM215。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HIGHESTPOSTSIGN")
	private CodeInfo highestPostSign;

	/**
	 * @fieldName: highestPostSign
	 * @fieldType: java.lang.String
	 * @Description:现任职务标记，DM215。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOWPOSTSIGN")
	private CodeInfo nowPostSign;

	/**
	 * @fieldName: isLowerLeader
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否兼任下级领导职务,在任职务需填。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ISLOWERLEADER")
	private CodeInfo isLowerLeader;
	
	/**
	 * @fieldName: isLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 是否是领导属性
	 */
	@Column(name = "IS_LEADER")
	private Integer isLeader;
	
	/**
	 * @fieldName: realJobLevelCode
	 * @fieldType: java.lang.String
	 * @Description: 占编时 使用的职级代码
	 */
	@Column(name = "REAL_JOB_LVL_CODE")
	private String realJobLevelCode;
	
	/**
	 * @fieldName: realLeader
	 * @fieldType: java.lang.Integer
	 * @Description: 占编时 是否是领导属性
	 */
	@Column(name = "REAL_LEADER")
	private Integer realLeader;

	public CodeInfo getIsLowerLeader() {
		return isLowerLeader;
	}

	public void setIsLowerLeader(CodeInfo isLowerLeader) {
		this.isLowerLeader = isLowerLeader;
	}

	public CodeInfo getHighestPostSign() {
        return highestPostSign;
    }

    public void setHighestPostSign(CodeInfo highestPostSign) {
        this.highestPostSign = highestPostSign;
    }

    public CodeInfo getNowPostSign() {
        return nowPostSign;
    }

    public void setNowPostSign(CodeInfo nowPostSign) {
        this.nowPostSign = nowPostSign;
    }

    public Boolean getLowToHigh() {
		return lowToHigh;
	}

	public void setLowToHigh(Boolean lowToHigh) {
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

	public String getFormerJobLevelName() {
		return formerJobLevelName;
	}

	public void setFormerJobLevelName(String formerJobLevelName) {
		this.formerJobLevelName = formerJobLevelName;
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

	
	public JobShiftCollect getJobShiftCollect() {
		
		return jobShiftCollect;
	}

	
	public void setJobShiftCollect(JobShiftCollect jobShiftCollect) {
		
		this.jobShiftCollect = jobShiftCollect;
	}

	
	public Integer getIsLeader() {
		
		return isLeader;
	}

	
	public void setIsLeader(Integer isLeader) {
		
		this.isLeader = isLeader;
	}

	
	public String getRealJobLevelCode() {
		
		return realJobLevelCode;
	}

	
	public void setRealJobLevelCode(String realJobLevelCode) {
		
		this.realJobLevelCode = realJobLevelCode;
	}

	
	public Integer getRealLeader() {
		
		return realLeader;
	}

	
	public void setRealLeader(Integer realLeader) {
		
		this.realLeader = realLeader;
	}


	public String getIsLeaderStr(){
		if(null==this.isLeader){
			return "";
		}else{
			if(this.isLeader==1){
				return JobLevel.LEADER;
			}else if(this.isLeader==0){
				return JobLevel.NOTLEADER;
			}
			return "";
		}
	}
}
