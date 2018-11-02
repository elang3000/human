/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RecruitEmployPlan.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofcflow
 * 描述: 职位简章招录计划
 * 创建人: wangzhanfei
 * 创建时间: 2018年5月28日 上午9:39:43
 * 版本号: V1.0
 * 修改人：wangzhanfei
 * 修改时间：2018年5月28日 上午9:39:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.workflow.bo.FlowRecord;

/**
 * @ClassName: RecruitEmployPlan
 * @Description: 职位简章招录计划
 * @author: wangzhanfei
 * @date: 2018年5月28日 上午9:39:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_RECRUIT_EMPLOY_PLAN")
public class RecruitEmployPlan extends GenericEntity {
	
	private static final long serialVersionUID = -2045142251901326764L;
	
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_LEVEL_CITY = 0;
	
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_LEVEL_AREA = 1;
	
	/**
	 * 招录计划状态
	 */
	// 待提交招录计划
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_STATE_POST = 0;
	// 待上级单位初审
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_SUPERIOR_TRIAL = 1;
	
	// 待区人事主管部门一级初审
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_1 = 2;
	
	// 待区人事主管部门二级初审
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_2 = 3;
	
	// 待区人事主管部门三级初审
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_3 = 4;
	
	// 待区人事主管部门四级初审
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_4 = 5;
	
	// 待区编办终审
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_AREA_OGR = 6;
	
	// 待区人事主管部门确认
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_CONFIRM = 7;
	
	// 区人事主管部门确认通过，待上报职位
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_POST = 8;
	
	// 职位上报，待上级单位审核
	@Transient
	public final static Integer RECRUIT_EMPLOY_POST_SUPERIOR_TRIAL = 9;
	
	// 职位上报，待区人事主管部门一级审核
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_POST_FIRST_TRIAL_1 = 10;
	
	// 职位上报，待区人事主管部门二级审核
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_POST_FIRST_TRIAL_2 = 11;
	
	// 职位上报，待区人事主管部门三级审核
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_POST_FIRST_TRIAL_3 = 12;
	
	// 职位上报，待区人事主管部门四级审核
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_POST_FIRST_TRIAL_4 = 13;
	
	// 职位上报，区人事主管部门审核通过
	@Transient
	public final static Integer RECRUIT_EMPLOY_PLAN_POST_PASS = 14;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String,Integer> power = new HashMap<>();
	
	static {
		power.put("REPORT_EMPLOY_PLAN",RECRUIT_EMPLOY_PLAN_STATE_POST);
		power.put("REPORT_EMPLOY_PLAN_SUPERIOR_TRIAL", RECRUIT_EMPLOY_PLAN_SUPERIOR_TRIAL);
		power.put("FIRST_APPROVAL_EMPLOY_PLAN", RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_1);
		power.put( "SECOND_APPROVAL_EMPLOY_PLAN",RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_2);
		power.put( "THIRD_APPROVAL_EMPLOY_PLAN",RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_3);
		power.put("FOUTH_APPROVAL_EMPLOY_PLAN", RECRUIT_EMPLOY_PLAN_FIRST_TRIAL_4);
		power.put("FINAL_APPROVAL_EMPLOY_PLAN", RECRUIT_EMPLOY_PLAN_AREA_OGR);
		power.put( "REPORT_EMPLOY_PLAN_POST",RECRUIT_EMPLOY_PLAN_CONFIRM);
		
		// 职位上报权限代码
		power.put("REPORT_EMPLOY_PLAN_POST_DOING",RECRUIT_EMPLOY_PLAN_POST );
		power.put("RECRUIT_EMPLOY_POST_SUPERIOR_TRIAL",RECRUIT_EMPLOY_POST_SUPERIOR_TRIAL );
		power.put("FIRST_APPROVAL_EMPLOY_PLAN_POST", RECRUIT_EMPLOY_PLAN_POST_FIRST_TRIAL_1);
		power.put("SECOND_APPROVAL_EMPLOY_PLAN_POST", RECRUIT_EMPLOY_PLAN_POST_FIRST_TRIAL_2);
		power.put("THIRD_APPROVAL_EMPLOY_PLAN_POST", RECRUIT_EMPLOY_PLAN_POST_FIRST_TRIAL_3);
		power.put("FOUTH_APPROVAL_EMPLOY_PLAN_POST", RECRUIT_EMPLOY_PLAN_POST_FIRST_TRIAL_4);
		power.put("REPORT_EMPLOY_PLAN_POST_PASS", RECRUIT_EMPLOY_PLAN_POST_PASS);
	}
	
	/**
	 * @fieldName: yearPlan
	 * @fieldType: RecruitYearPlan
	 * @Description: 年度招聘计划
	 */
	@OneToOne
	private RecruitYearPlan yearPlan;
	
	/**
	 * @fieldName: recruitOrgan
	 * @fieldType: OrganNode
	 * @Description: 招录机关
	 */
	@OneToOne
	private OrganNode recruitOrgan;
	
	/**
	 * @fieldName: employOrgan
	 * @fieldType: OrganNode
	 * @Description: 用人机关
	 */
	@OneToOne
	private OrganNode employOrgan;
	
	/**
	 * @fieldName: allowWeaveNum
	 * @fieldType: Integer
	 * @Description: 核定编制数
	 */
	@Column(name = "ALLOW_WEAVE_NUM")
	private Integer allowWeaveNum;
	
	/**
	 * @fieldName: realNum
	 * @fieldType: Integer
	 * @Description: 实有人数
	 */
	@Column(name = "REAL_NUM")
	private Integer realNum;
	
	/**
	 * @fieldName: thisYearLackWeaveNum
	 * @fieldType: Integer
	 * @Description: 机构缺编数
	 */
	@Column(name = "THIS_YEAR_LACK_WEAVE_NUM")
	private Integer thisYearLackWeaveNum;
	
	/**
	 * @fieldName: chiefLackWeaveNum
	 * @fieldType: Integer
	 * @Description: 处级实职缺编人数
	 */
	@Column(name = "CHIEF_LACK_WEAVE_NUM")
	private Integer chiefLackWeaveNum;
	
	/**
	 * @fieldName: planCutNum
	 * @fieldType: Integer
	 * @Description: 计划减员人数
	 */
	@Column(name = "PLAN_CUT_NUM")
	private Integer planCutNum;
	
	/**
	 * @fieldName: planEmployNum
	 * @fieldType: java.lang.int
	 * @Description: 计划招录人数
	 */
	@Column(name = "PLAN_EMPLOY_NUM")
	private Integer planEmployNum;
	
	/**
	 * @fieldName: employNum
	 * @fieldType: java.lang.int
	 * @Description: 审批时 传递人数
	 */
	@Transient
	private Integer employNum;
	
	/**
	 * @fieldName: firstEmployNum
	 * @fieldType: java.lang.int
	 * @Description: 初审招录人数
	 */
	@Column(name = "FIRST_EMPLOY_NUM")
	private Integer firstEmployNum;
	
	/**
	 * @fieldName: endEmployNum
	 * @fieldType: java.lang.int
	 * @Description: 终审招录人数
	 */
	@Column(name = "END_EMPLOY_NUM")
	private Integer endEmployNum;
	
	/**
	 * @fieldName: planState
	 * @fieldType: Integer
	 * @Description: 状态
	 */
	@Column(name = "PLAN_STATE")
	private Integer planState;
	
	/**
	 * @fieldName: level
	 * @fieldType: Integer
	 * @Description: 级别
	 */
	@Column(name = "PLAN_LEVEL")
	private Integer level;
	
	/**
	 * @fieldName: loc
	 * @fieldType: String
	 * @Description: TODO
	 */
	@Column(name = "LOC", length = 200)
	private String loc;
	
	/**
	 * @fieldName: recuritType
	 * @fieldType: CodeInfo
	 * @Description: 编制类型
	 */
	@OneToOne
	private CodeInfo recuritType;
	
	/**
	 * @fieldName: personType
	 * @fieldType: CodeInfo
	 * @Description: 人员类别
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CodeInfo personType;
	
	/**
	 * @fieldName: remark
	 * @fieldType: String
	 * @Description: 备注
	 */
	@Column(name = "REMARK", length = 255)
	private String remark;
	
	/**
	 * @fieldName: contacter
	 * @fieldType: String
	 * @Description: 联系人
	 */
	@Column(name = "CONTACTER", length = 255)
	private String contacter;
	
	/**
	 * @fieldName: contactPhone
	 * @fieldType: String
	 * @Description: 联系电话
	 */
	@Column(name = "CONTACT_PHONE", length = 255)
	private String contactPhone;
	
	/**
	 * @fieldName: contactAddress
	 * @fieldType: String
	 * @Description: 通宵地址
	 */
	@Column(name = "CONTACT_ADDRESS", length = 255)
	private String contactAddress;
	
	/**
	 * @fieldName: consultPhone
	 * @fieldType: String
	 * @Description: 咨询电话
	 */
	@Column(name = "CONSULT_PHONE", length = 255)
	private String consultPhone;
	
	/**
	 * @fieldName: post
	 * @fieldType: Set<RecruitPost>
	 * @Description: 职位信息
	 */
	@OneToMany(cascade = {
	        CascadeType.ALL
	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID")
	private Set<RecruitPost> post = new HashSet<RecruitPost>();
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	public RecruitYearPlan getYearPlan() {
		
		return yearPlan;
	}
	
	public void setYearPlan(RecruitYearPlan yearPlan) {
		
		this.yearPlan = yearPlan;
	}
	
	public OrganNode getRecruitOrgan() {
		
		return recruitOrgan;
	}
	
	public void setRecruitOrgan(OrganNode recruitOrgan) {
		
		this.recruitOrgan = recruitOrgan;
	}
	
	public OrganNode getEmployOrgan() {
		
		return employOrgan;
	}
	
	public void setEmployOrgan(OrganNode employOrgan) {
		
		this.employOrgan = employOrgan;
	}
	
	public Integer getAllowWeaveNum() {
		
		return allowWeaveNum;
	}
	
	public void setAllowWeaveNum(Integer allowWeaveNum) {
		
		this.allowWeaveNum = allowWeaveNum;
	}
	
	public Integer getRealNum() {
		
		return realNum;
	}
	
	public void setRealNum(Integer realNum) {
		
		this.realNum = realNum;
	}
	
	public Integer getThisYearLackWeaveNum() {
		
		return thisYearLackWeaveNum;
	}
	
	public void setThisYearLackWeaveNum(Integer thisYearLackWeaveNum) {
		
		this.thisYearLackWeaveNum = thisYearLackWeaveNum;
	}
	
	public Integer getPlanCutNum() {
		
		return planCutNum;
	}
	
	public void setPlanCutNum(Integer planCutNum) {
		
		this.planCutNum = planCutNum;
	}
	
	public Integer getPlanEmployNum() {
		
		return planEmployNum;
	}
	
	public void setPlanEmployNum(Integer planEmployNum) {
		
		this.planEmployNum = planEmployNum;
	}
	
	public Integer getPlanState() {
		
		return planState;
	}
	
	public void setPlanState(Integer planState) {
		
		this.planState = planState;
	}
	
	public Integer getLevel() {
		
		return level;
	}
	
	public void setLevel(Integer level) {
		
		this.level = level;
	}
	
	public String getLoc() {
		
		return loc;
	}
	
	public void setLoc(String loc) {
		
		this.loc = loc;
	}
	
	public CodeInfo getRecuritType() {
		
		return recuritType;
	}
	
	public void setRecuritType(CodeInfo recuritType) {
		
		this.recuritType = recuritType;
	}
	
	public String getRemark() {
		
		return remark;
	}
	
	public void setRemark(String remark) {
		
		this.remark = remark;
	}
	
	public String getContacter() {
		
		return contacter;
	}
	
	public void setContacter(String contacter) {
		
		this.contacter = contacter;
	}
	
	public String getContactPhone() {
		
		return contactPhone;
	}
	
	public void setContactPhone(String contactPhone) {
		
		this.contactPhone = contactPhone;
	}
	
	public String getContactAddress() {
		
		return contactAddress;
	}
	
	public void setContactAddress(String contactAddress) {
		
		this.contactAddress = contactAddress;
	}
	
	public String getConsultPhone() {
		
		return consultPhone;
	}
	
	public void setConsultPhone(String consultPhone) {
		
		this.consultPhone = consultPhone;
	}
	
	public Set<RecruitPost> getPost() {
		
		return post;
	}
	
	public void setPost(Set<RecruitPost> post) {
		
		this.post = post;
	}
	
	public CodeInfo getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(CodeInfo personType) {
		
		this.personType = personType;
	}
	
	public Integer getChiefLackWeaveNum() {
		
		return chiefLackWeaveNum;
	}
	
	public void setChiefLackWeaveNum(Integer chiefLackWeaveNum) {
		
		this.chiefLackWeaveNum = chiefLackWeaveNum;
	}
	
	public Integer getFirstEmployNum() {
		
		return firstEmployNum;
	}
	
	public Integer getEndEmployNum() {
		
		return endEmployNum;
	}
	
	public void setEndEmployNum(Integer endEmployNum) {
		
		this.endEmployNum = endEmployNum;
	}
	
	public void setFirstEmployNum(Integer firstEmployNum) {
		
		this.firstEmployNum = firstEmployNum;
	}
	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}
	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}
	
	public Integer getEmployNum() {
		
		return employNum;
	}
	
	public void setEmployNum(Integer employNum) {
		
		this.employNum = employNum;
	}
	
}
