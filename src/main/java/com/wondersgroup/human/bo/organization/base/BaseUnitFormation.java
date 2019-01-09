/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseUnitFormation.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 单位编制情况
 * 创建人: jiang
 * 创建时间: 2018年9月12日10:20:28
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月12日10:20:31
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.organization.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: BaseUnitFormation
 * @Description: 国标 单位编制情况
 * @author: jiang
 * @date: 2018年9月12日10:20:36
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseUnitFormation<T> extends GenericEntity {
	
	private static final long serialVersionUID = -3160174158659430956L;
	
	/**
	 * @fieldName: 批准编制日期
	 * @fieldType: java.util.Date
	 * @Description: 由上级编制部门批准编制的日期。GB/T 7408-2005
	 */
	@Column(name = "B02001")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approveDate;
	
	/**
	 * @fieldName: 批准编制文号
	 * @fieldType: java.lang.String
	 * @Description: 批准编制的正式批准文件的文号。
	 */
	@Column(name = "B02004", length = 48)
	private String approveNo;
	
	/**
	 * @fieldName: 编制批准单位名称
	 * @fieldType: java.lang.String
	 * @Description: 批准编制的上级编制部门的单位名称。
	 */
	@Column(name = "B02007A", length = 120)
	private String approveUnitName;
	
	/**
	 * @fieldName: 编制批准单位代码
	 * @fieldType: java.lang.String
	 * @Description: 批准编制的上级编制部门的单位名称。GB 32100-2015
	 */
	@Column(name = "B02007B", length = 18)
	private String approveUnitCode;
	
	/**
	 * @fieldName: 内设机构数
	 * @fieldType: java.lang.Integer
	 * @Description: 该单位内部下设一级的机构数。
	 */
	@Column(name = "B02011", length = 2)
	private Integer internalInstitutioNum;
	
	/**
	 * @fieldName: 内设机构名称
	 * @fieldType: java.lang.String
	 * @Description: 内设机构名称。
	 */
	@Column(name = "B02014", length = 40)
	private String internalInstitutioName;
	
	/**
	 * @fieldName: 内设机构级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该单位内部下设一级的机构级别。DM141
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B02017")
	private CodeInfo internalInstitutioLevel;
	
	/**
	 * @fieldName: 编制性质
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 占该单位主体部分的人员编制的类别划分。DM143
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B02021")
	private CodeInfo unitPlanningProperty;
	
	/**
	 * @fieldName: 批准编制总数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门核定批准该单位（机构）的人员编制人数。
	 */
	@Column(name = "B02024", length = 2)
	private Integer unitPlanningTotal = 0;
	
	/**
	 * @fieldName: 行政编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门批准的该单位（机构）的行政编制人数。
	 */
	@Column(name = "B02027", length = 2)
	private Integer adminWeaveNumber = 0;
	
	/**
	 * @fieldName: 事业编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门批准的该单位（机构）的事业编制人数。
	 */
	@Column(name = "B02031", length = 2)
	private Integer institutionWeaveNumber = 0;
	
	/**
	 * @fieldName: 参照公务员法管理事业编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门批准的该单位（机构）参照公务员法管理的事业编制人数。
	 */
	@Column(name = "B02032", length = 2)
	private Integer causeWeaveNumber = 0 ;
	
	/**
	 * @fieldName: 其他事业编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门批准的该单位（机构）其他的事业编制人数。
	 */
	@Column(name = "B02033", length = 2)
	private Integer otherinstitutionWeaveNumber;
	
	/**
	 * @fieldName: 企业编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门批准的该单位（机构）的企业编制人数。
	 */
	@Column(name = "B02034", length = 2)
	private Integer companyWeaveNumber;
	
	/**
	 * @fieldName: 社团编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 编制主管部门批准的该单位（机构）的社团编制人数。
	 */
	@Column(name = "B02037", length = 2)
	private Integer corporationWeaveNumber;
	
	/**
	 * @fieldName: 单位领导职数
	 * @fieldType: java.lang.Integer
	 * @Description: 工作单位及其内设机构正副职领导编制数。
	 */
	@Column(name = "B02041", length = 2)
	private Integer leaderWeaveNumber;
	
	/**
	 * @fieldName: 单位领导同级非领导职务职数
	 * @fieldType: java.lang.Integer
	 * @Description: 该单位担任同级非领导职务的人员职数。
	 */
	@Column(name = "B02044", length = 2)
	private Integer equativeNotLeaderWeaveNumber;
	
	/**
	 * @fieldName: 内设第一级机构领导职数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内设第一级机构正副职领导职数。
	 */
	@Column(name = "B02047", length = 2)
	private Integer institutionsLeaderWeaveNumber;
	
	/**
	 * @fieldName: 内设第一级机构领导同级非领导职务职数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内设第一级机构担任同级非领导职务的人员职数。
	 */
	@Column(name = "B02051", length = 2)
	private Integer equativeNotInstitutionsLeaderWeaveNumber;
	
	/**
	 * @fieldName: 业务人员编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 该单位从事业务工作的各种人员编制数。
	 */
	@Column(name = "B02054", length = 2)
	private Integer businessWeaveNumber;
	
	/**
	 * @fieldName: 工勤人员编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 该单位工勤人员编制数。
	 */
	@Column(name = "B02057", length = 2)
	private Integer workersWeaveNumber;
	
	/**
	 * @fieldName: 使用全额拨款人数
	 * @fieldType: java.lang.Integer
	 * @Description: 该单位使用由财政预算拨款的人数。
	 */
	@Column(name = "B02061", length = 2)
	private Integer fullAllotmentWeaveNumber;
	
	/**
	 * @fieldName: 使用差额拨款人数
	 * @fieldType: java.lang.Integer
	 * @Description: 事业单位使用的经费部分来源于财政预算拨款，部分来源于单位的经营、服务收入，使用这种拨款方式的人数。
	 */
	@Column(name = "B02064", length = 2)
	private Integer balanceWeaveNumber;
	
	/**
	 * @fieldName: 使用自筹资金人数
	 * @fieldType: java.lang.Integer
	 * @Description: 事业单位实行自收自支，所需经费全部由自己解决，使用这种经费管理方式的人数。
	 */
	@Column(name = "B02067", length = 2)
	private Integer selfFinancingWeaveNumber;
	
	/**
	 * @fieldName: 实有人数
	 * @fieldType: java.lang.Integer
	 * @Description: 本机构内占编制的实有人数。
	 */
	@Column(name = "B02110", length = 2)
	private Integer actualNumber = 0;
	
	/**
	 * @fieldName: 空编人数
	 * @fieldType: java.lang.Integer
	 * @Description: 本机构内空编或超编人数。
	 */
	@Column(name = "B02115", length = 2)
	private Integer vacancyExcessNumber = 0;
	
	/**
	 * @fieldName: 工勤编制数
	 * @fieldType: java.lang.Integer
	 * @Description: 由编制部门批准的企业编制人数。
	 */
	@Column(name = "B02130", length = 2)
	private Integer workAttendanceNumber;
	
	/**
	 * @fieldName: 单位非领导职数
	 * @fieldType: java.lang.Integer
	 * @Description: 工作单位正副职非领导编制数。
	 */
	@Column(name = "B02145", length = 2)
	private Integer nonLeaderWeaveNumber;
	
	/**
	 * @fieldName: 内设机构领导职数
	 * @fieldType: java.lang.Integer
	 * @Description: 工作单位内设机构正副职领导职数。
	 */
	@Column(name = "B02150", length = 2)
	private Integer internalInstitutionsLeaderWeaveNumber;
	
	/**
	 * @fieldName: 内设机构非领导职数
	 * @fieldType: java.lang.Integer
	 * @Description: 工作单位内设机构正副职非领导职数。
	 */
	@Column(name = "B02155", length = 2)
	private Integer internalInstitutionsNonLeaderWeaveNumber;
	
	/**
	 * @fieldName: 登记人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 公务员登记人员的数目。
	 */
	@Column(name = "B02197", length = 2)
	private Integer registrantsNumber;
	
	/**
	 * @fieldName: 暂缓登记人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 暂缓登记人员的数目。
	 */
	@Column(name = "B02198", length = 2)
	private Integer respiteRegistrantsNumber;
	
	/**
	 * @fieldName: 期末职工人数
	 * @fieldType: java.lang.Integer
	 * @Description: 报告期末最后一天的实有人数。不含已经招用但到期末尚未报到的人员和尚未用完的招工指标
	 */
	@Column(name = "B02205", length = 2)
	private Integer finalStaffWeaveNumber;
	
	/**
	 * @fieldName: 职工平均人数
	 * @fieldType: java.lang.Integer
	 * @Description: 报告期内每天平均拥有的人数。
	 */
	@Column(name = "B02210", length = 2)
	private Integer averageEmployeesNumber;
	
	/**
	 * @fieldName: 正式职工人数
	 * @fieldType: java.lang.Integer
	 * @Description: 指在机关、事业单位中，经国家有关部门分配、安排或批准招收录用的职工。
	 *               包括原固定职工和使用期限在一年以上的合同制职工。
	 */
	@Column(name = "B02218", length = 2)
	private Integer officialStaffNumber;
	
	/**
	 * @fieldName: 干部人数
	 * @fieldType: java.lang.Integer
	 * @Description: 在领导、管理、专业技术、行政、党务等岗位上工作的人员。
	 */
	@Column(name = "B02235", length = 2)
	private Integer cadreNumber;
	
	/**
	 * @fieldName: 工人人数
	 * @fieldType: java.lang.Integer
	 * @Description: 在生产、流通、经营、服务、后勤保障等岗位上工作的人员。
	 */
	@Column(name = "B02240", length = 2)
	private Integer workerNumber;
	
	/**
	 * @fieldName: 行政管理人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 行政机关、事业、企业的单位领导和在各职能部门及所属机构，从事行政管理、生产管理、经济管理和政治工作的人员。
	 */
	@Column(name = "B02245", length = 2)
	private Integer administrativePersonnelNumber;
	
	/**
	 * @fieldName: 专业技术人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 经人事部门正式聘用并备案的具有专业技术职务或没有专业技术职务但实际从事专业技术工作的人员。
	 */
	@Column(name = "B02250", length = 2)
	private Integer professionalTechnicalNumber;
	
	/**
	 * @fieldName: 长期病休人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 病休 6 个月以上，脱离工作岗位的人员。
	 */
	@Column(name = "B02260", length = 2)
	private Integer medicalLeaveNumber;
	
	/**
	 * @fieldName: 带工资学习一年以上人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内带工资学习一年以上的人员数。
	 */
	@Column(name = "B02265", length = 2)
	private Integer studiedSalaryNumber;
	
	/**
	 * @fieldName: 省部级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内省部级以及与省部级相当职级的人员总数。
	 */
	@Column(name = "B02305", length = 2)
	private Integer provincialLevelNumber;
	
	/**
	 * @fieldName: 副省部级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内副省部级以及与副省部级相当职级的人员总数。
	 */
	@Column(name = "B02310", length = 2)
	private Integer deputyProvincialLevelNumber;
	
	/**
	 * @fieldName: 司局级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内司局级以及与司局级相当职级的人员总数。
	 */
	@Column(name = "B02315", length = 2)
	private Integer generalLevelNumber;
	
	/**
	 * @fieldName: 副司局级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内副司局级以及与副司局级相当职级的人员总数。
	 */
	@Column(name = "B02320", length = 2)
	private Integer deputyGeneralLevelNumber;
	
	/**
	 * @fieldName: 处级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内处级以及与处级相当职级的人员总数。
	 */
	@Column(name = "B02325", length = 2)
	private Integer divisionChiefLevelNumber;
	
	/**
	 * @fieldName: 副处级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内副处级以及与副处级相当职级的人员总数。
	 */
	@Column(name = "B02330", length = 2)
	private Integer deputyDivisionChiefLevelNumber;
	
	/**
	 * @fieldName: 科级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内科级以及与科级相当职级的人员总数。
	 */
	@Column(name = "B02335", length = 2)
	private Integer sectionChiefLevelNumber;
	
	/**
	 * @fieldName: 副科级及相当者人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内副科级以及与副科级相当职级的人员总数。
	 */
	@Column(name = "B02340", length = 2)
	private Integer deputySectionChiefLevelNumber;
	
	/**
	 * @fieldName: 科员、办事员及其他人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内科员、办事员及其他人员总数。
	 */
	@Column(name = "B02345", length = 2)
	private Integer staffMembersNumber;
	
	/**
	 * @fieldName: 高级专业技术职务人数（含正高、副高）
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内包括正高和副高在内的高级专业技术职务人员总数。
	 */
	@Column(name = "B02350", length = 2)
	private Integer seniorProfessionalTechnicalNumber;
	
	/**
	 * @fieldName: 中级专业技术职务人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内的中级专业技术职务人员总数。
	 */
	@Column(name = "B02355", length = 2)
	private Integer intermediateProfessionalTechnicalNumber;
	
	/**
	 * @fieldName: 初级专业技术职务人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内的初级专业技术职务人员总数。
	 */
	@Column(name = "B02360", length = 2)
	private Integer primaryProfessionalTechnicalNumber;
	
	/**
	 * @fieldName: 未评聘专业技术职务人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内未评聘专业技术职务的人员总数。
	 */
	@Column(name = "B02365", length = 2)
	private Integer nonLevelProfessionalTechnicalNumber;
	
	/**
	 * @fieldName: 高级技师人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内的高级技师人员总数。
	 */
	@Column(name = "B02405", length = 2)
	private Integer seniorTechniciansNumber;
	
	/**
	 * @fieldName: 技师人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内的技师人员总数。
	 */
	@Column(name = "B02410", length = 2)
	private Integer techniciansNumber;
	
	/**
	 * @fieldName: 高级技术工人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内高级技术工人的人员总数。
	 */
	@Column(name = "B02415", length = 2)
	private Integer highlySkilledNumber;
	
	/**
	 * @fieldName: 中级技术工人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内中级技术工人的人员总数。
	 */
	@Column(name = "B02420", length = 2)
	private Integer intermediateSkilledNumber;
	
	/**
	 * @fieldName: 初级技术工人数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内初级技术工人的人员总数。
	 */
	@Column(name = "B02425", length = 2)
	private Integer juniorSkilledNumber;
	
	/**
	 * @fieldName: 未聘人员数
	 * @fieldType: java.lang.Integer
	 * @Description: 单位内未聘用的人员总数。
	 */
	@Column(name = "B02430", length = 2)
	private Integer unemployedNumber;
	
	/**
	 * @fieldName: 各种原因流动人数
	 * @fieldType: java.lang.Integer
	 * @Description: 因各种原因离开现工作单位进行流动的人数。
	 */
	@Column(name = "B02505", length = 2)
	private Integer variousReasonsNumber;
	
	/**
	 * @fieldName: 各种流向人数
	 * @fieldType: java.lang.Integer
	 * @Description: 工作调动的各种流向人数。流向通常有经济类型、区域、地区类别、单位性质、单位隶属关系等几种不同流向的划分方法
	 */
	@Column(name = "B02510", length = 2)
	private Integer variousNumber;
	
	/**
	 * @fieldName: 人员增加来源
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调出或离开本单位的情况分类。GB/T 12405-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B02515")
	private CodeInfo addPersonSource;
	
	/**
	 * @fieldName: 人员减少去向
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 在报告期内离开现工作单位，并不再由该单位支付工资的人员。GB/T 12405-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B02520")
	private CodeInfo reductionPersonOrientation;
	
	/**
	 * @fieldName: 离休人数
	 * @fieldType: java.lang.Integer
	 * @Description: 在 1949 年 9 月 30 日以前参加革命工作，符合 1982 年国务院发布的《关于老干部离职休养的几项规定》的离休条件，实际离职休养的干部人数。
	 * 包括：调到城镇集体所有制单位和其他各种所有制单位工作，保留全民所有制单位干部身份，已办理离休手续的人员；过去退休后改办离休待遇的人员。
	 * 不包括建国前参加革命工作，退休前在工人岗位的退休工人和已死亡的人数
	 */
	@Column(name = "B02605", length = 2)
	private Integer retiredVeteranNumber;
	
	/**
	 * @fieldName: 退休人数
	 * @fieldType: java.lang.Integer
	 * @Description: 全民所有制单位、城镇集体所有制单位和其他各种所有制已经退休的人员数。不包括已经死亡的人数和退休后改办手续转为离休干部的人数
	 */
	@Column(name = "B02610", length = 2)
	private Integer retiredNumber;
	
	/**
	 * @fieldName: 领取定期生活费的退职人数
	 * @fieldType: java.lang.Integer
	 * @Description: 国有经济、城镇集体经济单位和其他各种经济类型单位中按照国家有关规定退职的人数。
	 */
	@Column(name = "B02615", length = 2)
	private Integer regularBasisNumber;
	
	/**
	 * @fieldName: 离休费在最低标准的人数
	 * @fieldType: java.lang.Integer
	 * @Description:按国家有关规定领取最低档离休金的人数。
	 */
	@Column(name = "B02620", length = 2)
	private Integer leaveMinimumNumber;
	
	/**
	 * @fieldName: 退休费在最低标准的人数
	 * @fieldType: java.lang.Integer
	 * @Description:按国家有关规定领取最低档退休金的人数。
	 */
	@Column(name = "B02625", length = 2)
	private Integer minimumRetirementNumber;
	
	/**
	 * @fieldName: 生活不能自理者人数
	 * @fieldType: java.lang.Integer
	 * @Description:离退休人员中生活不能自理的人员总数。
	 */
	@Column(name = "B02630", length = 2)
	private Integer noncareThemselvesNumber;
	
	/**
	 * @fieldName: 单位成立申请日期
	 * @fieldType: java.util.Date
	 * @Description: 申请该单位成立的日期。GB/T 7408-2005
	 */
	@Column(name = "B02705")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date applyDate;
	
	/**
	 * @fieldName: 单位成立申请文号
	 * @fieldType: java.lang.String
	 * @Description: 申请该单位成立的文件号。
	 */
	@Column(name = "B02707", length = 48)
	private String applyNo;
	
	/**
	 * @fieldName: 法律法规授权情况
	 * @fieldType: java.lang.String
	 * @Description: 编制主管部门批准该单位成立法律法规授权的情况说明。
	 */
	@Column(name = "B02709", length = 2000)
	private String authorizationLaws;
	
	/**
	 * @fieldName: 参照公务员法管理审批时间
	 * @fieldType: java.util.Date
	 * @Description: 编制主管部门批准的该单位（机构）参照公务员法管理的批准时间。GB/T 7408-2005
	 */
	@Column(name = "B02801")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date manageApprovalDate;
	
	/**
	 * @fieldName: 参照公务员法管理审批文号
	 * @fieldType: java.lang.String
	 * @Description: 编制主管部门批准的该单位（机构）参照公务员法管理的批准文件编号。
	 */
	@Column(name = "B02804", length = 2000)
	private String manageApprovalNo;
	
	/**
	 * @fieldName: 单位参照公务员法管理标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该单位（机构）是否参照公务员法管理的标识。GB/T 12405-2008
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "B02807")
	private CodeInfo manageMark;

	
	public Date getApproveDate() {
		
		return approveDate;
	}

	
	public void setApproveDate(Date approveDate) {
		
		this.approveDate = approveDate;
	}

	
	public String getApproveNo() {
		
		return approveNo;
	}

	
	public void setApproveNo(String approveNo) {
		
		this.approveNo = approveNo;
	}

	
	public String getApproveUnitName() {
		
		return approveUnitName;
	}

	
	public void setApproveUnitName(String approveUnitName) {
		
		this.approveUnitName = approveUnitName;
	}

	
	public String getApproveUnitCode() {
		
		return approveUnitCode;
	}

	
	public void setApproveUnitCode(String approveUnitCode) {
		
		this.approveUnitCode = approveUnitCode;
	}

	
	public Integer getInternalInstitutioNum() {
		
		return internalInstitutioNum;
	}

	
	public void setInternalInstitutioNum(Integer internalInstitutioNum) {
		
		this.internalInstitutioNum = internalInstitutioNum;
	}

	
	public String getInternalInstitutioName() {
		
		return internalInstitutioName;
	}

	
	public void setInternalInstitutioName(String internalInstitutioName) {
		
		this.internalInstitutioName = internalInstitutioName;
	}

	
	public CodeInfo getInternalInstitutioLevel() {
		
		return internalInstitutioLevel;
	}

	
	public void setInternalInstitutioLevel(CodeInfo internalInstitutioLevel) {
		
		this.internalInstitutioLevel = internalInstitutioLevel;
	}

	
	public CodeInfo getUnitPlanningProperty() {
		
		return unitPlanningProperty;
	}

	
	public void setUnitPlanningProperty(CodeInfo unitPlanningProperty) {
		
		this.unitPlanningProperty = unitPlanningProperty;
	}

	
	public Integer getUnitPlanningTotal() {
		
		return unitPlanningTotal;
	}

	
	public void setUnitPlanningTotal(Integer unitPlanningTotal) {
		
		this.unitPlanningTotal = unitPlanningTotal;
	}

	
	public Integer getAdminWeaveNumber() {
		
		return adminWeaveNumber;
	}

	
	public void setAdminWeaveNumber(Integer adminWeaveNumber) {
		
		this.adminWeaveNumber = adminWeaveNumber;
	}

	
	public Integer getInstitutionWeaveNumber() {
		
		return institutionWeaveNumber;
	}

	
	public void setInstitutionWeaveNumber(Integer institutionWeaveNumber) {
		
		this.institutionWeaveNumber = institutionWeaveNumber;
	}

	
	public Integer getCauseWeaveNumber() {
		
		return causeWeaveNumber;
	}

	
	public void setCauseWeaveNumber(Integer causeWeaveNumber) {
		
		this.causeWeaveNumber = causeWeaveNumber;
	}

	
	public Integer getOtherinstitutionWeaveNumber() {
		
		return otherinstitutionWeaveNumber;
	}

	
	public void setOtherinstitutionWeaveNumber(Integer otherinstitutionWeaveNumber) {
		
		this.otherinstitutionWeaveNumber = otherinstitutionWeaveNumber;
	}

	
	public Integer getCompanyWeaveNumber() {
		
		return companyWeaveNumber;
	}

	
	public void setCompanyWeaveNumber(Integer companyWeaveNumber) {
		
		this.companyWeaveNumber = companyWeaveNumber;
	}

	
	public Integer getCorporationWeaveNumber() {
		
		return corporationWeaveNumber;
	}

	
	public void setCorporationWeaveNumber(Integer corporationWeaveNumber) {
		
		this.corporationWeaveNumber = corporationWeaveNumber;
	}

	
	public Integer getLeaderWeaveNumber() {
		
		return leaderWeaveNumber;
	}

	
	public void setLeaderWeaveNumber(Integer leaderWeaveNumber) {
		
		this.leaderWeaveNumber = leaderWeaveNumber;
	}

	
	public Integer getEquativeNotLeaderWeaveNumber() {
		
		return equativeNotLeaderWeaveNumber;
	}

	
	public void setEquativeNotLeaderWeaveNumber(Integer equativeNotLeaderWeaveNumber) {
		
		this.equativeNotLeaderWeaveNumber = equativeNotLeaderWeaveNumber;
	}

	
	public Integer getInstitutionsLeaderWeaveNumber() {
		
		return institutionsLeaderWeaveNumber;
	}

	
	public void setInstitutionsLeaderWeaveNumber(Integer institutionsLeaderWeaveNumber) {
		
		this.institutionsLeaderWeaveNumber = institutionsLeaderWeaveNumber;
	}

	
	public Integer getEquativeNotInstitutionsLeaderWeaveNumber() {
		
		return equativeNotInstitutionsLeaderWeaveNumber;
	}

	
	public void setEquativeNotInstitutionsLeaderWeaveNumber(Integer equativeNotInstitutionsLeaderWeaveNumber) {
		
		this.equativeNotInstitutionsLeaderWeaveNumber = equativeNotInstitutionsLeaderWeaveNumber;
	}

	
	public Integer getBusinessWeaveNumber() {
		
		return businessWeaveNumber;
	}

	
	public void setBusinessWeaveNumber(Integer businessWeaveNumber) {
		
		this.businessWeaveNumber = businessWeaveNumber;
	}

	
	public Integer getWorkersWeaveNumber() {
		
		return workersWeaveNumber;
	}

	
	public void setWorkersWeaveNumber(Integer workersWeaveNumber) {
		
		this.workersWeaveNumber = workersWeaveNumber;
	}

	
	public Integer getFullAllotmentWeaveNumber() {
		
		return fullAllotmentWeaveNumber;
	}

	
	public void setFullAllotmentWeaveNumber(Integer fullAllotmentWeaveNumber) {
		
		this.fullAllotmentWeaveNumber = fullAllotmentWeaveNumber;
	}

	
	public Integer getBalanceWeaveNumber() {
		
		return balanceWeaveNumber;
	}

	
	public void setBalanceWeaveNumber(Integer balanceWeaveNumber) {
		
		this.balanceWeaveNumber = balanceWeaveNumber;
	}

	
	public Integer getSelfFinancingWeaveNumber() {
		
		return selfFinancingWeaveNumber;
	}

	
	public void setSelfFinancingWeaveNumber(Integer selfFinancingWeaveNumber) {
		
		this.selfFinancingWeaveNumber = selfFinancingWeaveNumber;
	}

	
	public Integer getActualNumber() {
		
		return actualNumber;
	}

	
	public void setActualNumber(Integer actualNumber) {
		
		this.actualNumber = actualNumber;
	}

	
	public Integer getVacancyExcessNumber() {
		
		return vacancyExcessNumber;
	}

	
	public void setVacancyExcessNumber(Integer vacancyExcessNumber) {
		
		this.vacancyExcessNumber = vacancyExcessNumber;
	}

	
	public Integer getWorkAttendanceNumber() {
		
		return workAttendanceNumber;
	}

	
	public void setWorkAttendanceNumber(Integer workAttendanceNumber) {
		
		this.workAttendanceNumber = workAttendanceNumber;
	}

	
	public Integer getNonLeaderWeaveNumber() {
		
		return nonLeaderWeaveNumber;
	}

	
	public void setNonLeaderWeaveNumber(Integer nonLeaderWeaveNumber) {
		
		this.nonLeaderWeaveNumber = nonLeaderWeaveNumber;
	}

	
	public Integer getInternalInstitutionsLeaderWeaveNumber() {
		
		return internalInstitutionsLeaderWeaveNumber;
	}

	
	public void setInternalInstitutionsLeaderWeaveNumber(Integer internalInstitutionsLeaderWeaveNumber) {
		
		this.internalInstitutionsLeaderWeaveNumber = internalInstitutionsLeaderWeaveNumber;
	}

	
	public Integer getInternalInstitutionsNonLeaderWeaveNumber() {
		
		return internalInstitutionsNonLeaderWeaveNumber;
	}

	
	public void setInternalInstitutionsNonLeaderWeaveNumber(Integer internalInstitutionsNonLeaderWeaveNumber) {
		
		this.internalInstitutionsNonLeaderWeaveNumber = internalInstitutionsNonLeaderWeaveNumber;
	}

	
	public Integer getRegistrantsNumber() {
		
		return registrantsNumber;
	}

	
	public void setRegistrantsNumber(Integer registrantsNumber) {
		
		this.registrantsNumber = registrantsNumber;
	}

	
	public Integer getRespiteRegistrantsNumber() {
		
		return respiteRegistrantsNumber;
	}

	
	public void setRespiteRegistrantsNumber(Integer respiteRegistrantsNumber) {
		
		this.respiteRegistrantsNumber = respiteRegistrantsNumber;
	}

	
	public Integer getFinalStaffWeaveNumber() {
		
		return finalStaffWeaveNumber;
	}

	
	public void setFinalStaffWeaveNumber(Integer finalStaffWeaveNumber) {
		
		this.finalStaffWeaveNumber = finalStaffWeaveNumber;
	}

	
	public Integer getAverageEmployeesNumber() {
		
		return averageEmployeesNumber;
	}

	
	public void setAverageEmployeesNumber(Integer averageEmployeesNumber) {
		
		this.averageEmployeesNumber = averageEmployeesNumber;
	}

	
	public Integer getOfficialStaffNumber() {
		
		return officialStaffNumber;
	}

	
	public void setOfficialStaffNumber(Integer officialStaffNumber) {
		
		this.officialStaffNumber = officialStaffNumber;
	}

	
	public Integer getCadreNumber() {
		
		return cadreNumber;
	}

	
	public void setCadreNumber(Integer cadreNumber) {
		
		this.cadreNumber = cadreNumber;
	}

	
	public Integer getWorkerNumber() {
		
		return workerNumber;
	}

	
	public void setWorkerNumber(Integer workerNumber) {
		
		this.workerNumber = workerNumber;
	}

	
	public Integer getAdministrativePersonnelNumber() {
		
		return administrativePersonnelNumber;
	}

	
	public void setAdministrativePersonnelNumber(Integer administrativePersonnelNumber) {
		
		this.administrativePersonnelNumber = administrativePersonnelNumber;
	}

	
	public Integer getProfessionalTechnicalNumber() {
		
		return professionalTechnicalNumber;
	}

	
	public void setProfessionalTechnicalNumber(Integer professionalTechnicalNumber) {
		
		this.professionalTechnicalNumber = professionalTechnicalNumber;
	}

	
	public Integer getMedicalLeaveNumber() {
		
		return medicalLeaveNumber;
	}

	
	public void setMedicalLeaveNumber(Integer medicalLeaveNumber) {
		
		this.medicalLeaveNumber = medicalLeaveNumber;
	}

	
	public Integer getStudiedSalaryNumber() {
		
		return studiedSalaryNumber;
	}

	
	public void setStudiedSalaryNumber(Integer studiedSalaryNumber) {
		
		this.studiedSalaryNumber = studiedSalaryNumber;
	}

	
	public Integer getProvincialLevelNumber() {
		
		return provincialLevelNumber;
	}

	
	public void setProvincialLevelNumber(Integer provincialLevelNumber) {
		
		this.provincialLevelNumber = provincialLevelNumber;
	}

	
	public Integer getDeputyProvincialLevelNumber() {
		
		return deputyProvincialLevelNumber;
	}

	
	public void setDeputyProvincialLevelNumber(Integer deputyProvincialLevelNumber) {
		
		this.deputyProvincialLevelNumber = deputyProvincialLevelNumber;
	}

	
	public Integer getGeneralLevelNumber() {
		
		return generalLevelNumber;
	}

	
	public void setGeneralLevelNumber(Integer generalLevelNumber) {
		
		this.generalLevelNumber = generalLevelNumber;
	}

	
	public Integer getDeputyGeneralLevelNumber() {
		
		return deputyGeneralLevelNumber;
	}

	
	public void setDeputyGeneralLevelNumber(Integer deputyGeneralLevelNumber) {
		
		this.deputyGeneralLevelNumber = deputyGeneralLevelNumber;
	}

	
	public Integer getDivisionChiefLevelNumber() {
		
		return divisionChiefLevelNumber;
	}

	
	public void setDivisionChiefLevelNumber(Integer divisionChiefLevelNumber) {
		
		this.divisionChiefLevelNumber = divisionChiefLevelNumber;
	}

	
	public Integer getDeputyDivisionChiefLevelNumber() {
		
		return deputyDivisionChiefLevelNumber;
	}

	
	public void setDeputyDivisionChiefLevelNumber(Integer deputyDivisionChiefLevelNumber) {
		
		this.deputyDivisionChiefLevelNumber = deputyDivisionChiefLevelNumber;
	}

	
	public Integer getSectionChiefLevelNumber() {
		
		return sectionChiefLevelNumber;
	}

	
	public void setSectionChiefLevelNumber(Integer sectionChiefLevelNumber) {
		
		this.sectionChiefLevelNumber = sectionChiefLevelNumber;
	}

	
	public Integer getDeputySectionChiefLevelNumber() {
		
		return deputySectionChiefLevelNumber;
	}

	
	public void setDeputySectionChiefLevelNumber(Integer deputySectionChiefLevelNumber) {
		
		this.deputySectionChiefLevelNumber = deputySectionChiefLevelNumber;
	}

	
	public Integer getStaffMembersNumber() {
		
		return staffMembersNumber;
	}

	
	public void setStaffMembersNumber(Integer staffMembersNumber) {
		
		this.staffMembersNumber = staffMembersNumber;
	}

	
	public Integer getSeniorProfessionalTechnicalNumber() {
		
		return seniorProfessionalTechnicalNumber;
	}

	
	public void setSeniorProfessionalTechnicalNumber(Integer seniorProfessionalTechnicalNumber) {
		
		this.seniorProfessionalTechnicalNumber = seniorProfessionalTechnicalNumber;
	}

	
	public Integer getIntermediateProfessionalTechnicalNumber() {
		
		return intermediateProfessionalTechnicalNumber;
	}

	
	public void setIntermediateProfessionalTechnicalNumber(Integer intermediateProfessionalTechnicalNumber) {
		
		this.intermediateProfessionalTechnicalNumber = intermediateProfessionalTechnicalNumber;
	}

	
	public Integer getPrimaryProfessionalTechnicalNumber() {
		
		return primaryProfessionalTechnicalNumber;
	}

	
	public void setPrimaryProfessionalTechnicalNumber(Integer primaryProfessionalTechnicalNumber) {
		
		this.primaryProfessionalTechnicalNumber = primaryProfessionalTechnicalNumber;
	}

	
	public Integer getNonLevelProfessionalTechnicalNumber() {
		
		return nonLevelProfessionalTechnicalNumber;
	}

	
	public void setNonLevelProfessionalTechnicalNumber(Integer nonLevelProfessionalTechnicalNumber) {
		
		this.nonLevelProfessionalTechnicalNumber = nonLevelProfessionalTechnicalNumber;
	}

	
	public Integer getSeniorTechniciansNumber() {
		
		return seniorTechniciansNumber;
	}

	
	public void setSeniorTechniciansNumber(Integer seniorTechniciansNumber) {
		
		this.seniorTechniciansNumber = seniorTechniciansNumber;
	}

	
	public Integer getTechniciansNumber() {
		
		return techniciansNumber;
	}

	
	public void setTechniciansNumber(Integer techniciansNumber) {
		
		this.techniciansNumber = techniciansNumber;
	}

	
	public Integer getHighlySkilledNumber() {
		
		return highlySkilledNumber;
	}

	
	public void setHighlySkilledNumber(Integer highlySkilledNumber) {
		
		this.highlySkilledNumber = highlySkilledNumber;
	}

	
	public Integer getIntermediateSkilledNumber() {
		
		return intermediateSkilledNumber;
	}

	
	public void setIntermediateSkilledNumber(Integer intermediateSkilledNumber) {
		
		this.intermediateSkilledNumber = intermediateSkilledNumber;
	}

	
	public Integer getJuniorSkilledNumber() {
		
		return juniorSkilledNumber;
	}

	
	public void setJuniorSkilledNumber(Integer juniorSkilledNumber) {
		
		this.juniorSkilledNumber = juniorSkilledNumber;
	}

	
	public Integer getUnemployedNumber() {
		
		return unemployedNumber;
	}

	
	public void setUnemployedNumber(Integer unemployedNumber) {
		
		this.unemployedNumber = unemployedNumber;
	}

	
	public Integer getVariousReasonsNumber() {
		
		return variousReasonsNumber;
	}

	
	public void setVariousReasonsNumber(Integer variousReasonsNumber) {
		
		this.variousReasonsNumber = variousReasonsNumber;
	}

	
	public Integer getVariousNumber() {
		
		return variousNumber;
	}

	
	public void setVariousNumber(Integer variousNumber) {
		
		this.variousNumber = variousNumber;
	}

	
	public CodeInfo getAddPersonSource() {
		
		return addPersonSource;
	}

	
	public void setAddPersonSource(CodeInfo addPersonSource) {
		
		this.addPersonSource = addPersonSource;
	}

	
	public CodeInfo getReductionPersonOrientation() {
		
		return reductionPersonOrientation;
	}

	
	public void setReductionPersonOrientation(CodeInfo reductionPersonOrientation) {
		
		this.reductionPersonOrientation = reductionPersonOrientation;
	}

	
	public Integer getRetiredVeteranNumber() {
		
		return retiredVeteranNumber;
	}

	
	public void setRetiredVeteranNumber(Integer retiredVeteranNumber) {
		
		this.retiredVeteranNumber = retiredVeteranNumber;
	}

	
	public Integer getRetiredNumber() {
		
		return retiredNumber;
	}

	
	public void setRetiredNumber(Integer retiredNumber) {
		
		this.retiredNumber = retiredNumber;
	}

	
	public Integer getRegularBasisNumber() {
		
		return regularBasisNumber;
	}

	
	public void setRegularBasisNumber(Integer regularBasisNumber) {
		
		this.regularBasisNumber = regularBasisNumber;
	}

	
	public Integer getLeaveMinimumNumber() {
		
		return leaveMinimumNumber;
	}

	
	public void setLeaveMinimumNumber(Integer leaveMinimumNumber) {
		
		this.leaveMinimumNumber = leaveMinimumNumber;
	}

	
	public Integer getMinimumRetirementNumber() {
		
		return minimumRetirementNumber;
	}

	
	public void setMinimumRetirementNumber(Integer minimumRetirementNumber) {
		
		this.minimumRetirementNumber = minimumRetirementNumber;
	}

	
	public Integer getNoncareThemselvesNumber() {
		
		return noncareThemselvesNumber;
	}

	
	public void setNoncareThemselvesNumber(Integer noncareThemselvesNumber) {
		
		this.noncareThemselvesNumber = noncareThemselvesNumber;
	}

	
	public Date getApplyDate() {
		
		return applyDate;
	}

	
	public void setApplyDate(Date applyDate) {
		
		this.applyDate = applyDate;
	}

	
	public String getApplyNo() {
		
		return applyNo;
	}

	
	public void setApplyNo(String applyNo) {
		
		this.applyNo = applyNo;
	}

	
	public String getAuthorizationLaws() {
		
		return authorizationLaws;
	}

	
	public void setAuthorizationLaws(String authorizationLaws) {
		
		this.authorizationLaws = authorizationLaws;
	}

	
	public Date getManageApprovalDate() {
		
		return manageApprovalDate;
	}

	
	public void setManageApprovalDate(Date manageApprovalDate) {
		
		this.manageApprovalDate = manageApprovalDate;
	}

	
	public String getManageApprovalNo() {
		
		return manageApprovalNo;
	}

	
	public void setManageApprovalNo(String manageApprovalNo) {
		
		this.manageApprovalNo = manageApprovalNo;
	}

	
	public CodeInfo getManageMark() {
		
		return manageMark;
	}

	
	public void setManageMark(CodeInfo manageMark) {
		
		this.manageMark = manageMark;
	}
	
}
