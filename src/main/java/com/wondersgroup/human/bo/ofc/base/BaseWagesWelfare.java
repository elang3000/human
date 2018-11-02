/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseWagesWelfare.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 工资福利项目
 * 创建人: jiang
 * 创建时间: 2018年9月10日09:05:47
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月10日09:05:51
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

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
 * @ClassName: BaseWagesWelfare
 * @Description: 国标 工资福利项目
 * @author: jiang
 * @date: 2018年9月10日09:05:57
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseWagesWelfare<T> extends GenericEntity {
	
	private static final long serialVersionUID = -4433516030276851262L;
	
	/**
	 * @fieldName: 工资福利项目
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 构成该人工资福利费用总体中的某类项目名称。DM123
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33001")
	private CodeInfo wagesWelfareProject;
	
	/**
	 * @fieldName: 工资福利项目金额
	 * @fieldType: java.lang.Float
	 * @Description: 构成该人工资福利的该类项目金额。
	 */
	@Column(name = "A33004", length = 2)
	private Float wagesWelfareMoney;
	
	/**
	 * @fieldName: 工资福利项目备注
	 * @fieldType: java.lang.String
	 * @Description: 对该人该工资福利项目的补充说明。
	 */
	@Column(name = "A33007", length = 2000)
	private String wagesWelfareProjectRemarks;
	
	/**
	 * @fieldName: 工资标准类型
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 根据事业单位工作特点不同，其工作人员分别实行所对应的工资标准。DM220
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33011")
	private CodeInfo wagesStandardType;
	
	/**
	 * @fieldName: 工资合计
	 * @fieldType: java.lang.Float
	 * @Description: 职工在一定时期内直接从单位领取的劳动报酬总额。
	 */
	@Column(name = "A33105", length = 2)
	private Float payroll;
	
	/**
	 * @fieldName: 工资级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 表示公务员担任职务所对应的工资级别。DM052
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33125")
	private CodeInfo wagesLevel;
	
	/**
	 * @fieldName: 基础工资
	 * @fieldType: java.lang.Float
	 * @Description: 公务员职级工资中有基础工资额。
	 */
	@Column(name = "A33130", length = 2)
	private Float baseWages;
	
	/**
	 * @fieldName: 年薪酬
	 * @fieldType: java.lang.Float
	 * @Description: 该人全年的薪酬数额。
	 */
	@Column(name = "A33132", length = 2)
	private Float annualSalary;
	
	/**
	 * @fieldName: 职务工资
	 * @fieldType: java.lang.Float
	 * @Description: 公务员担任的职务所对应的职务工资额。
	 */
	@Column(name = "A33135", length = 2)
	private Float jobWages;
	
	/**
	 * @fieldName: 级别工资
	 * @fieldType: java.lang.Float
	 * @Description: 按公务员担任的职务及任职年限所对应的级别工资额。
	 */
	@Column(name = "A33140", length = 2)
	private Float levelWages;
	
	/**
	 * @fieldName: 岗位工资
	 * @fieldType: java.lang.Float
	 * @Description: 事业单位人员按照其岗位所对应的岗位工资额。
	 */
	@Column(name = "A33141", length = 2)
	private Float postWages;
	
	/**
	 * @fieldName: 薪级工资
	 * @fieldType: java.lang.Float
	 * @Description: 事业单位人员的薪级所对应的薪级工资额。
	 */
	@Column(name = "A33142", length = 2)
	private Float salaryLevelWages;
	
	/**
	 * @fieldName: 试用期工资
	 * @fieldType: java.lang.Float
	 * @Description: 公务员、干部被录用后一年试用期内的工资额。
	 */
	@Column(name = "A33150", length = 2)
	private Float probationWages;
	
	/**
	 * @fieldName: 津贴或补贴
	 * @fieldType: java.lang.Float
	 * @Description: 为补偿职工特殊或额外的劳动消耗和因其他特殊原因支付给职工的津
	 *               贴额，以及为了保证职工工资水平不受物价影响支付给职工的物价补贴额。
	 */
	@Column(name = "A33155", length = 2)
	private Float subsidy;
	
	/**
	 * @fieldName: 教龄、护龄津贴
	 * @fieldType: java.lang.Float
	 * @Description: 该人从事教师或护士职业的教龄、 护龄津贴额。
	 */
	@Column(name = "A33160", length = 2)
	private Float teachingAgeOrNurseAgeSubsidy;
	
	/**
	 * @fieldName: 伙食补贴
	 * @fieldType: java.lang.Float
	 * @Description: 直接支付给个人的伙食补贴额。
	 */
	@Column(name = "A33165", length = 2)
	private Float foodSubsidy;
	
	/**
	 * @fieldName: 地区附加津贴
	 * @fieldType: java.lang.Float
	 * @Description: 根据各地区经济发展水平和生活费用支出等因素，支付给个人的地区附加津贴额。
	 */
	@Column(name = "A33170", length = 2)
	private Float areaSubsidy;
	
	/**
	 * @fieldName: 奖金
	 * @fieldType: java.lang.Float
	 * @Description: 支付给职工的超额劳动报酬和增收节支的劳动报酬。
	 */
	@Column(name = "A33180", length = 2)
	private Float bonus;
	
	/**
	 * @fieldName: 工人技术等级工资
	 * @fieldType: java.lang.Float
	 * @Description: 技术工人按技术水平高低和工作能力大小所得到的等级工资额。
	 */
	@Column(name = "A33200", length = 2)
	private Float workerTechnologyLevlWages;
	
	/**
	 * @fieldName: 工人岗位津贴
	 * @fieldType: java.lang.Float
	 * @Description: 技术工人按实际工作量和岗位的差别所得到的岗位津贴额。
	 */
	@Column(name = "A33205", length = 2)
	private Float workerPostSubsidy;
	
	/**
	 * @fieldName: 人员工资类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人执行工资标准类别。DM051
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33210")
	private CodeInfo personnelWagesType;
	
	/**
	 * @fieldName: 工改前基本工资总额
	 * @fieldType: java.lang.Float
	 * @Description: 该人实行 2006 年工资套改制度前的基本工资总额。
	 */
	@Column(name = "A33211", length = 2)
	private Float beforeReformbaseWages;
	
	/**
	 * @fieldName: 薪级
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位根据工作人员工作表现、资历和所聘岗位等因素确定的薪级。DM089
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33212")
	private CodeInfo salaryLevelType;
	
	/**
	 * @fieldName: 公务员工资级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员该人的工资级别。DM052
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33217")
	private CodeInfo servantWagesLevel;
	
	/**
	 * @fieldName: 机关技术工人技术等级
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 机关技术工人的技术等级。DM012
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33225")
	private CodeInfo orgTechnologyWorkerTechnologyLevel;
	
	/**
	 * @fieldName: 机关技术工人级别工资额
	 * @fieldType: java.lang.Float
	 * @Description: 机关技术工人岗位工资档次所对应的工资额。
	 */
	@Column(name = "A33227", length = 2)
	private Float orgTechnologyWorkerLevelWages;
	
	/**
	 * @fieldName: 机关普通工人级别工资额
	 * @fieldType: java.lang.Float
	 * @Description: 机关普通工人岗位工资档次所对应的工资额。
	 */
	@Column(name = "A33228", length = 2)
	private Float orgOrdinaryWorkerLevelWages;
	
	/**
	 * @fieldName: 公务员套改年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员的套改年限标识。DM094
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33229")
	private CodeInfo servantChangeTime;
	
	/**
	 * @fieldName: 公务员任职年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 公务员的任职年限标识。DM095
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33230")
	private CodeInfo servantTenureTime;
	
	/**
	 * @fieldName: 机关技术（普通）工人套改年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 机关中技术工人或普通工人的套改年限标识。DM096
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33231")
	private CodeInfo orgOrdinaryWorkerChangeTime;
	
	/**
	 * @fieldName: 机关技术工人任技术等级（职务）年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 机关技术工人从考评（聘任）技术等级（职务）当前起计算的年限。DM097
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33232")
	private CodeInfo orgTechnologyWorkerJobTime;
	
	/**
	 * @fieldName: 事业单位管理人员套改年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中管理人员的的套改年限标识。DM098
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33233")
	private CodeInfo institutionManagerChangeTime;
	
	/**
	 * @fieldName: 事业单位管理人员任职年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中管理人员的任职年限标识。DM099
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33234")
	private CodeInfo institutionManagerTenureTime;
	
	/**
	 * @fieldName: 事业单位管理人员岗位等级
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中管理人员的岗位等级的标识。DM100
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33235")
	private CodeInfo institutionManagerPostLevel;
	
	/**
	 * @fieldName: 事业单位专业技术人员套改年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中专业技术人员的套改年限标识。DM101
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33236")
	private CodeInfo institutionProfessionTechnicianChangeTime;
	
	/**
	 * @fieldName: 事业单位专业技术人员任职年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中专业技术人员的任职年限标识。DM102
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33237")
	private CodeInfo institutionProfessionTechnicianTenureTime;
	
	/**
	 * @fieldName: 事业单位专业技术人员岗位等级
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中专业技术人员的岗位等级标识。DM103
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33238")
	private CodeInfo institutionProfessionTechnicianPostLevel;
	
	/**
	 * @fieldName: 事业单位技术工人套改年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中技术工人的套改年限标识。DM104
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33239")
	private CodeInfo institutionTechnicianChangeTime;
	
	/**
	 * @fieldName: 事业单位技术工人任职年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中技术工人的任职年限标识。DM105
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33240")
	private CodeInfo institutionTechnicianTenureTime;
	
	/**
	 * @fieldName: 事业单位技术工人岗位等级
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位中技术工人的岗位等级标识。DM106
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33241")
	private CodeInfo institutionTechnicianPostLevel;
	
	/**
	 * @fieldName: 事业单位普通工人套改年限
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 事业单位普通工人的套改年限标识。DM107
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33242")
	private CodeInfo institutionOrdinaryWorkerChangeTime;
	
	/**
	 * @fieldName: 公务员地区附加津贴
	 * @fieldType: java.lang.Float
	 * @Description: 根据地区经济发展水平、物价发展水平等方面的差异确定的津贴额。
	 */
	@Column(name = "A33243", length = 2)
	private Float servantAreaAdditionalSubsidy;
	
	/**
	 * @fieldName: 艰苦边远地区津贴
	 * @fieldType: java.lang.Float
	 * @Description: 根据自然地理环境、社会发展等方面的差异，对艰苦边远地区工作生活的机关工作人员给予的补偿。
	 */
	@Column(name = "A33244", length = 2)
	private Float remoteAreaSubsidy;
	
	/**
	 * @fieldName: 公务员岗位津贴
	 * @fieldType: java.lang.Float
	 * @Description: 在特殊岗位工作的机关工作人员，实行岗位津贴制度。
	 */
	@Column(name = "A33245", length = 2)
	private Float servantPostSubsidy;
	
	/**
	 * @fieldName: 事业单位岗位工资
	 * @fieldType: java.lang.Float
	 * @Description: 按该人所聘岗位对应岗位工资标准。
	 */
	@Column(name = "A33246", length = 2)
	private Float institutionPostWages;
	
	/**
	 * @fieldName: 事业单位薪级工资
	 * @fieldType: java.lang.Float
	 * @Description: 单位根据该人工作表现资历和所聘岗位等因素确定的薪级所对应薪级工资标准。
	 */
	@Column(name = "A33247", length = 2)
	private Float institutionSalaryLevelWages;
	
	/**
	 * @fieldName: 事业单位绩效工资
	 * @fieldType: java.lang.Float
	 * @Description: 根据该人的实绩和贡献所取得的绩效工资额。
	 */
	@Column(name = "A33248", length = 2)
	private Float institutionAchievementsWages;
	
	/**
	 * @fieldName: 事业单位艰苦边远地区津贴
	 * @fieldType: java.lang.Float
	 * @Description: 根据自然地理环境、社会发展等方面的差异，对在艰苦边远地区工作生活的工作人员给予适当补偿。
	 */
	@Column(name = "A33249", length = 2)
	private Float institutionRemoteAreaSubsidy;
	
	/**
	 * @fieldName: 事业单位特殊岗位津贴
	 * @fieldType: java.lang.Float
	 * @Description: 根据有关文件规定在单位苦、脏、累、险及其他特殊岗位工作的人员给予的特殊岗位津贴。
	 */
	@Column(name = "A33250", length = 2)
	private Float institutionSpecialPostSubsidy;
	
	/**
	 * @fieldName: 津补贴类型
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 给予津补贴的类型。DM108
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33251")
	private CodeInfo subsidyType;
	
	/**
	 * @fieldName: 津补贴形式
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 给予津补贴的形式。DM109
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33252")
	private CodeInfo subsidyForm;
	
	/**
	 * @fieldName: 津补贴类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 给予津补贴的类别。DM123
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33253")
	private CodeInfo subsidyCategory;
	
	/**
	 * @fieldName: 艰苦边远地区津贴类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 给予艰苦边远地区津贴的类别。DM111
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A33261")
	private CodeInfo remoteAreaSubsidyCategory;
	
	/**
	 * @fieldName: 未计工龄在校学习日期
	 * @fieldType: java.util.Date
	 * @Description: 在国家承认学历的全日制大专以上院校未计算为工龄的学习时间。GB/T 7408-2005
	 */
	@Column(name = "A33262")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date notCalculatedWorkAgeStudyOnShoolDate;
	
	/**
	 * @fieldName: 工改保留补贴
	 * @fieldType: java.lang.Float
	 * @Description: 该人在 1993 年工资改革后保留的补贴数额。
	 */
	@Column(name = "A33270", length = 2)
	private Float changeRetainSubsidy;
	
	/**
	 * @fieldName: 在京中央国家机关补贴
	 * @fieldType: java.lang.Float
	 * @Description: 在京中央国家机关工作人员按国管财字[1996]第 171 号规定执行的补贴。
	 */
	@Column(name = "A33271", length = 2)
	private Float inPekingOrgSubsidy;
	
	/**
	 * @fieldName: 住房补贴
	 * @fieldType: java.lang.Float
	 * @Description: 该人的住房补贴。
	 */
	@Column(name = "A33272", length = 2)
	private Float housingSubsidy;
	
	/**
	 * @fieldName: 医疗补贴
	 * @fieldType: java.lang.Float
	 * @Description: 该人的医疗补贴。
	 */
	@Column(name = "A33273", length = 2)
	private Float medicalSubsidy;
	
	/**
	 * @fieldName: 通讯补贴
	 * @fieldType: java.lang.Float
	 * @Description: 该人的通讯补贴数额。
	 */
	@Column(name = "A33274", length = 2)
	private Float telephoneSubsidy;
	
	/**
	 * @fieldName: 津贴总额
	 * @fieldType: java.lang.Float
	 * @Description: 该人的津贴总额。
	 */
	@Column(name = "A33275", length = 2)
	private Float subsidySum;
	
	/**
	 * @fieldName: 奖金总额
	 * @fieldType: java.lang.Float
	 * @Description: 该人的奖金总额。
	 */
	@Column(name = "A33276", length = 2)
	private Float bonusSum;
	
	/**
	 * @fieldName: 个人其他收入
	 * @fieldType: java.lang.Float
	 * @Description: 上述之外的工资收入。
	 */
	@Column(name = "A33290", length = 2)
	private Float otherIncome;
	
	/**
	 * @fieldName: 保险福利费合计
	 * @fieldType: java.lang.Float
	 * @Description: 单位在工资以外实际支付给职工和离休、退休、退职人员个人的社会保险和福利费用的总数。
	 */
	@Column(name = "A33305", length = 2)
	private Float socialsSecurityWelfareSum;
	
	/**
	 * @fieldName: 丧葬抚恤救济费
	 * @fieldType: java.lang.Float
	 * @Description: 职工死亡的丧葬费、丧葬补助费和所遗供养直系亲属的抚恤费、救济费、
	 *               生活补助费以及供养直系亲属死亡时的丧葬补助费。
	 */
	@Column(name = "A33310", length = 2)
	private Float funeralExpenses;
	
	/**
	 * @fieldName: 职工生活困难补助费
	 * @fieldType: java.lang.Float
	 * @Description: 单位对于因各种原因造成的生活困难的职工，在经济上给予临时的或定期的补助，以保证基本生活需要的一种福利。
	 */
	@Column(name = "A33315", length = 2)
	private Float difficultSecurity;
	
	/**
	 * @fieldName: 托儿补助费
	 * @fieldType: java.lang.Float
	 * @Description: 发给职工子女入托的补助费。
	 */
	@Column(name = "A33320", length = 2)
	private Float childcareSecurity;
	
	/**
	 * @fieldName: 计划生育补贴
	 * @fieldType: java.lang.Float
	 * @Description: 发给职工的独生子女补贴、保健费。
	 */
	@Column(name = "A33325", length = 2)
	private Float familyPlanningSecurity;
	
	/**
	 * @fieldName: 冬季取暖补贴
	 * @fieldType: java.lang.Float
	 * @Description: 按有关规定发给职工的冬季取暖补贴费。
	 */
	@Column(name = "A33330", length = 2)
	private Float heatingSecurity;
	
	/**
	 * @fieldName: 其他保险福利费
	 * @fieldType: java.lang.Float
	 * @Description: 上述费用以外用于职工保险福利的支出, 支付职工的暑期降温费和职工探亲路费。
	 */
	@Column(name = "A33399", length = 2)
	private Float otherSecurityWelfareSum;
	
	public CodeInfo getWagesWelfareProject() {
		
		return wagesWelfareProject;
	}
	
	public void setWagesWelfareProject(CodeInfo wagesWelfareProject) {
		
		this.wagesWelfareProject = wagesWelfareProject;
	}
	
	public Float getWagesWelfareMoney() {
		
		return wagesWelfareMoney;
	}
	
	public void setWagesWelfareMoney(Float wagesWelfareMoney) {
		
		this.wagesWelfareMoney = wagesWelfareMoney;
	}
	
	public String getWagesWelfareProjectRemarks() {
		
		return wagesWelfareProjectRemarks;
	}
	
	public void setWagesWelfareProjectRemarks(String wagesWelfareProjectRemarks) {
		
		this.wagesWelfareProjectRemarks = wagesWelfareProjectRemarks;
	}
	
	public CodeInfo getWagesStandardType() {
		
		return wagesStandardType;
	}
	
	public void setWagesStandardType(CodeInfo wagesStandardType) {
		
		this.wagesStandardType = wagesStandardType;
	}
	
	public Float getPayroll() {
		
		return payroll;
	}
	
	public void setPayroll(Float payroll) {
		
		this.payroll = payroll;
	}
	
	public CodeInfo getWagesLevel() {
		
		return wagesLevel;
	}
	
	public void setWagesLevel(CodeInfo wagesLevel) {
		
		this.wagesLevel = wagesLevel;
	}
	
	public Float getBaseWages() {
		
		return baseWages;
	}
	
	public void setBaseWages(Float baseWages) {
		
		this.baseWages = baseWages;
	}
	
	public Float getAnnualSalary() {
		
		return annualSalary;
	}
	
	public void setAnnualSalary(Float annualSalary) {
		
		this.annualSalary = annualSalary;
	}
	
	public Float getJobWages() {
		
		return jobWages;
	}
	
	public void setJobWages(Float jobWages) {
		
		this.jobWages = jobWages;
	}
	
	public Float getLevelWages() {
		
		return levelWages;
	}
	
	public void setLevelWages(Float levelWages) {
		
		this.levelWages = levelWages;
	}
	
	public Float getPostWages() {
		
		return postWages;
	}
	
	public void setPostWages(Float postWages) {
		
		this.postWages = postWages;
	}
	
	public Float getSalaryLevelWages() {
		
		return salaryLevelWages;
	}
	
	public void setSalaryLevelWages(Float salaryLevelWages) {
		
		this.salaryLevelWages = salaryLevelWages;
	}
	
	public Float getProbationWages() {
		
		return probationWages;
	}
	
	public void setProbationWages(Float probationWages) {
		
		this.probationWages = probationWages;
	}
	
	public Float getSubsidy() {
		
		return subsidy;
	}
	
	public void setSubsidy(Float subsidy) {
		
		this.subsidy = subsidy;
	}
	
	public Float getTeachingAgeOrNurseAgeSubsidy() {
		
		return teachingAgeOrNurseAgeSubsidy;
	}
	
	public void setTeachingAgeOrNurseAgeSubsidy(Float teachingAgeOrNurseAgeSubsidy) {
		
		this.teachingAgeOrNurseAgeSubsidy = teachingAgeOrNurseAgeSubsidy;
	}
	
	public Float getFoodSubsidy() {
		
		return foodSubsidy;
	}
	
	public void setFoodSubsidy(Float foodSubsidy) {
		
		this.foodSubsidy = foodSubsidy;
	}
	
	public Float getAreaSubsidy() {
		
		return areaSubsidy;
	}
	
	public void setAreaSubsidy(Float areaSubsidy) {
		
		this.areaSubsidy = areaSubsidy;
	}
	
	public Float getBonus() {
		
		return bonus;
	}
	
	public void setBonus(Float bonus) {
		
		this.bonus = bonus;
	}
	
	public Float getWorkerTechnologyLevlWages() {
		
		return workerTechnologyLevlWages;
	}
	
	public void setWorkerTechnologyLevlWages(Float workerTechnologyLevlWages) {
		
		this.workerTechnologyLevlWages = workerTechnologyLevlWages;
	}
	
	public Float getWorkerPostSubsidy() {
		
		return workerPostSubsidy;
	}
	
	public void setWorkerPostSubsidy(Float workerPostSubsidy) {
		
		this.workerPostSubsidy = workerPostSubsidy;
	}
	
	public CodeInfo getPersonnelWagesType() {
		
		return personnelWagesType;
	}
	
	public void setPersonnelWagesType(CodeInfo personnelWagesType) {
		
		this.personnelWagesType = personnelWagesType;
	}
	
	public Float getBeforeReformbaseWages() {
		
		return beforeReformbaseWages;
	}
	
	public void setBeforeReformbaseWages(Float beforeReformbaseWages) {
		
		this.beforeReformbaseWages = beforeReformbaseWages;
	}
	
	public CodeInfo getSalaryLevelType() {
		
		return salaryLevelType;
	}
	
	public void setSalaryLevelType(CodeInfo salaryLevelType) {
		
		this.salaryLevelType = salaryLevelType;
	}
	
	public CodeInfo getServantWagesLevel() {
		
		return servantWagesLevel;
	}
	
	public void setServantWagesLevel(CodeInfo servantWagesLevel) {
		
		this.servantWagesLevel = servantWagesLevel;
	}
	
	public CodeInfo getOrgTechnologyWorkerTechnologyLevel() {
		
		return orgTechnologyWorkerTechnologyLevel;
	}
	
	public void setOrgTechnologyWorkerTechnologyLevel(CodeInfo orgTechnologyWorkerTechnologyLevel) {
		
		this.orgTechnologyWorkerTechnologyLevel = orgTechnologyWorkerTechnologyLevel;
	}
	
	public Float getOrgTechnologyWorkerLevelWages() {
		
		return orgTechnologyWorkerLevelWages;
	}
	
	public void setOrgTechnologyWorkerLevelWages(Float orgTechnologyWorkerLevelWages) {
		
		this.orgTechnologyWorkerLevelWages = orgTechnologyWorkerLevelWages;
	}
	
	public Float getOrgOrdinaryWorkerLevelWages() {
		
		return orgOrdinaryWorkerLevelWages;
	}
	
	public void setOrgOrdinaryWorkerLevelWages(Float orgOrdinaryWorkerLevelWages) {
		
		this.orgOrdinaryWorkerLevelWages = orgOrdinaryWorkerLevelWages;
	}
	
	public CodeInfo getServantChangeTime() {
		
		return servantChangeTime;
	}
	
	public void setServantChangeTime(CodeInfo servantChangeTime) {
		
		this.servantChangeTime = servantChangeTime;
	}
	
	public CodeInfo getServantTenureTime() {
		
		return servantTenureTime;
	}
	
	public void setServantTenureTime(CodeInfo servantTenureTime) {
		
		this.servantTenureTime = servantTenureTime;
	}
	
	public CodeInfo getOrgOrdinaryWorkerChangeTime() {
		
		return orgOrdinaryWorkerChangeTime;
	}
	
	public void setOrgOrdinaryWorkerChangeTime(CodeInfo orgOrdinaryWorkerChangeTime) {
		
		this.orgOrdinaryWorkerChangeTime = orgOrdinaryWorkerChangeTime;
	}
	
	public CodeInfo getOrgTechnologyWorkerJobTime() {
		
		return orgTechnologyWorkerJobTime;
	}
	
	public void setOrgTechnologyWorkerJobTime(CodeInfo orgTechnologyWorkerJobTime) {
		
		this.orgTechnologyWorkerJobTime = orgTechnologyWorkerJobTime;
	}
	
	public CodeInfo getInstitutionManagerChangeTime() {
		
		return institutionManagerChangeTime;
	}
	
	public void setInstitutionManagerChangeTime(CodeInfo institutionManagerChangeTime) {
		
		this.institutionManagerChangeTime = institutionManagerChangeTime;
	}
	
	public CodeInfo getInstitutionManagerTenureTime() {
		
		return institutionManagerTenureTime;
	}
	
	public void setInstitutionManagerTenureTime(CodeInfo institutionManagerTenureTime) {
		
		this.institutionManagerTenureTime = institutionManagerTenureTime;
	}
	
	public CodeInfo getInstitutionManagerPostLevel() {
		
		return institutionManagerPostLevel;
	}
	
	public void setInstitutionManagerPostLevel(CodeInfo institutionManagerPostLevel) {
		
		this.institutionManagerPostLevel = institutionManagerPostLevel;
	}
	
	public CodeInfo getInstitutionProfessionTechnicianChangeTime() {
		
		return institutionProfessionTechnicianChangeTime;
	}
	
	public void setInstitutionProfessionTechnicianChangeTime(CodeInfo institutionProfessionTechnicianChangeTime) {
		
		this.institutionProfessionTechnicianChangeTime = institutionProfessionTechnicianChangeTime;
	}
	
	public CodeInfo getInstitutionProfessionTechnicianTenureTime() {
		
		return institutionProfessionTechnicianTenureTime;
	}
	
	public void setInstitutionProfessionTechnicianTenureTime(CodeInfo institutionProfessionTechnicianTenureTime) {
		
		this.institutionProfessionTechnicianTenureTime = institutionProfessionTechnicianTenureTime;
	}
	
	public CodeInfo getInstitutionProfessionTechnicianPostLevel() {
		
		return institutionProfessionTechnicianPostLevel;
	}
	
	public void setInstitutionProfessionTechnicianPostLevel(CodeInfo institutionProfessionTechnicianPostLevel) {
		
		this.institutionProfessionTechnicianPostLevel = institutionProfessionTechnicianPostLevel;
	}
	
	public CodeInfo getInstitutionTechnicianChangeTime() {
		
		return institutionTechnicianChangeTime;
	}
	
	public void setInstitutionTechnicianChangeTime(CodeInfo institutionTechnicianChangeTime) {
		
		this.institutionTechnicianChangeTime = institutionTechnicianChangeTime;
	}
	
	public CodeInfo getInstitutionTechnicianTenureTime() {
		
		return institutionTechnicianTenureTime;
	}
	
	public void setInstitutionTechnicianTenureTime(CodeInfo institutionTechnicianTenureTime) {
		
		this.institutionTechnicianTenureTime = institutionTechnicianTenureTime;
	}
	
	public CodeInfo getInstitutionTechnicianPostLevel() {
		
		return institutionTechnicianPostLevel;
	}
	
	public void setInstitutionTechnicianPostLevel(CodeInfo institutionTechnicianPostLevel) {
		
		this.institutionTechnicianPostLevel = institutionTechnicianPostLevel;
	}
	
	public CodeInfo getInstitutionOrdinaryWorkerChangeTime() {
		
		return institutionOrdinaryWorkerChangeTime;
	}
	
	public void setInstitutionOrdinaryWorkerChangeTime(CodeInfo institutionOrdinaryWorkerChangeTime) {
		
		this.institutionOrdinaryWorkerChangeTime = institutionOrdinaryWorkerChangeTime;
	}
	
	public Float getServantAreaAdditionalSubsidy() {
		
		return servantAreaAdditionalSubsidy;
	}
	
	public void setServantAreaAdditionalSubsidy(Float servantAreaAdditionalSubsidy) {
		
		this.servantAreaAdditionalSubsidy = servantAreaAdditionalSubsidy;
	}
	
	public Float getRemoteAreaSubsidy() {
		
		return remoteAreaSubsidy;
	}
	
	public void setRemoteAreaSubsidy(Float remoteAreaSubsidy) {
		
		this.remoteAreaSubsidy = remoteAreaSubsidy;
	}
	
	public Float getServantPostSubsidy() {
		
		return servantPostSubsidy;
	}
	
	public void setServantPostSubsidy(Float servantPostSubsidy) {
		
		this.servantPostSubsidy = servantPostSubsidy;
	}
	
	public Float getInstitutionPostWages() {
		
		return institutionPostWages;
	}
	
	public void setInstitutionPostWages(Float institutionPostWages) {
		
		this.institutionPostWages = institutionPostWages;
	}
	
	public Float getInstitutionSalaryLevelWages() {
		
		return institutionSalaryLevelWages;
	}
	
	public void setInstitutionSalaryLevelWages(Float institutionSalaryLevelWages) {
		
		this.institutionSalaryLevelWages = institutionSalaryLevelWages;
	}
	
	public Float getInstitutionAchievementsWages() {
		
		return institutionAchievementsWages;
	}
	
	public void setInstitutionAchievementsWages(Float institutionAchievementsWages) {
		
		this.institutionAchievementsWages = institutionAchievementsWages;
	}
	
	public Float getInstitutionRemoteAreaSubsidy() {
		
		return institutionRemoteAreaSubsidy;
	}
	
	public void setInstitutionRemoteAreaSubsidy(Float institutionRemoteAreaSubsidy) {
		
		this.institutionRemoteAreaSubsidy = institutionRemoteAreaSubsidy;
	}
	
	public Float getInstitutionSpecialPostSubsidy() {
		
		return institutionSpecialPostSubsidy;
	}
	
	public void setInstitutionSpecialPostSubsidy(Float institutionSpecialPostSubsidy) {
		
		this.institutionSpecialPostSubsidy = institutionSpecialPostSubsidy;
	}
	
	public CodeInfo getSubsidyType() {
		
		return subsidyType;
	}
	
	public void setSubsidyType(CodeInfo subsidyType) {
		
		this.subsidyType = subsidyType;
	}
	
	public CodeInfo getSubsidyForm() {
		
		return subsidyForm;
	}
	
	public void setSubsidyForm(CodeInfo subsidyForm) {
		
		this.subsidyForm = subsidyForm;
	}
	
	public CodeInfo getSubsidyCategory() {
		
		return subsidyCategory;
	}
	
	public void setSubsidyCategory(CodeInfo subsidyCategory) {
		
		this.subsidyCategory = subsidyCategory;
	}
	
	public CodeInfo getRemoteAreaSubsidyCategory() {
		
		return remoteAreaSubsidyCategory;
	}
	
	public void setRemoteAreaSubsidyCategory(CodeInfo remoteAreaSubsidyCategory) {
		
		this.remoteAreaSubsidyCategory = remoteAreaSubsidyCategory;
	}
	
	public Date getNotCalculatedWorkAgeStudyOnShoolDate() {
		
		return notCalculatedWorkAgeStudyOnShoolDate;
	}
	
	public void setNotCalculatedWorkAgeStudyOnShoolDate(Date notCalculatedWorkAgeStudyOnShoolDate) {
		
		this.notCalculatedWorkAgeStudyOnShoolDate = notCalculatedWorkAgeStudyOnShoolDate;
	}
	
	public Float getChangeRetainSubsidy() {
		
		return changeRetainSubsidy;
	}
	
	public void setChangeRetainSubsidy(Float changeRetainSubsidy) {
		
		this.changeRetainSubsidy = changeRetainSubsidy;
	}
	
	public Float getInPekingOrgSubsidy() {
		
		return inPekingOrgSubsidy;
	}
	
	public void setInPekingOrgSubsidy(Float inPekingOrgSubsidy) {
		
		this.inPekingOrgSubsidy = inPekingOrgSubsidy;
	}
	
	public Float getHousingSubsidy() {
		
		return housingSubsidy;
	}
	
	public void setHousingSubsidy(Float housingSubsidy) {
		
		this.housingSubsidy = housingSubsidy;
	}
	
	public Float getMedicalSubsidy() {
		
		return medicalSubsidy;
	}
	
	public void setMedicalSubsidy(Float medicalSubsidy) {
		
		this.medicalSubsidy = medicalSubsidy;
	}
	
	public Float getTelephoneSubsidy() {
		
		return telephoneSubsidy;
	}
	
	public void setTelephoneSubsidy(Float telephoneSubsidy) {
		
		this.telephoneSubsidy = telephoneSubsidy;
	}
	
	public Float getSubsidySum() {
		
		return subsidySum;
	}
	
	public void setSubsidySum(Float subsidySum) {
		
		this.subsidySum = subsidySum;
	}
	
	public Float getBonusSum() {
		
		return bonusSum;
	}
	
	public void setBonusSum(Float bonusSum) {
		
		this.bonusSum = bonusSum;
	}
	
	public Float getOtherIncome() {
		
		return otherIncome;
	}
	
	public void setOtherIncome(Float otherIncome) {
		
		this.otherIncome = otherIncome;
	}
	
	public Float getSocialsSecurityWelfareSum() {
		
		return socialsSecurityWelfareSum;
	}
	
	public void setSocialsSecurityWelfareSum(Float socialsSecurityWelfareSum) {
		
		this.socialsSecurityWelfareSum = socialsSecurityWelfareSum;
	}
	
	public Float getFuneralExpenses() {
		
		return funeralExpenses;
	}
	
	public void setFuneralExpenses(Float funeralExpenses) {
		
		this.funeralExpenses = funeralExpenses;
	}
	
	public Float getDifficultSecurity() {
		
		return difficultSecurity;
	}
	
	public void setDifficultSecurity(Float difficultSecurity) {
		
		this.difficultSecurity = difficultSecurity;
	}
	
	public Float getChildcareSecurity() {
		
		return childcareSecurity;
	}
	
	public void setChildcareSecurity(Float childcareSecurity) {
		
		this.childcareSecurity = childcareSecurity;
	}
	
	public Float getFamilyPlanningSecurity() {
		
		return familyPlanningSecurity;
	}
	
	public void setFamilyPlanningSecurity(Float familyPlanningSecurity) {
		
		this.familyPlanningSecurity = familyPlanningSecurity;
	}
	
	public Float getHeatingSecurity() {
		
		return heatingSecurity;
	}
	
	public void setHeatingSecurity(Float heatingSecurity) {
		
		this.heatingSecurity = heatingSecurity;
	}
	
	public Float getOtherSecurityWelfareSum() {
		
		return otherSecurityWelfareSum;
	}
	
	public void setOtherSecurityWelfareSum(Float otherSecurityWelfareSum) {
		
		this.otherSecurityWelfareSum = otherSecurityWelfareSum;
	}
	
}
