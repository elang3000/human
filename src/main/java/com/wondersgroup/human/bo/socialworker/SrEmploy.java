/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Post.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 职务信息
 * 创建人: tzy
 * 创建时间: 2018年5月29日 上午10:08:04
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月29日 上午10:08:04
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.socialworker;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.base.BaseEmploy;

/**
 * 
 * @ClassName:  Employ   
 * @Description:luyongxinxi   
 * @author: bianyf
 * @date:   2018年8月20日 下午4:58:51   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "E03")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SrEmploy extends BaseEmploy<SrEmploy> {
	
	private static final long serialVersionUID = 7253487601845963934L;
	
	/**
	 * @fieldName: socialWorker
	 * @fieldType: com.wondersgroup.human.bo.socialworker.SocialWorker
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOCIALWORKER_ID")
	private SocialWorker socialWorker;
	
	/**
	 * @fieldName: 是否考试录用人员
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A99Z101")
	private CodeInfo isExamEmploy;
	
	/**
	 * @fieldName: 录用时政治面貌
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人录用为公务员或参照管理人员的政治面貌。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A29321")
	private CodeInfo politics;
	
	/**
	 * @fieldName: 录用时学历名称
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员前接受正式教育并取得学历证书的学习经历名称。
	 */
	@Column(name = "SH_A29324A", length = 40)
	private String educationName;
	
	/**
	 * @fieldName: 录用时学历代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人录用为公务员或参照管理人员前接受正式教育并取得学历证书的学习经历代码。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A29324B")
	private CodeInfo educationCode;
	
	/**
	 * @fieldName: 录用时学位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员前完成一定学历教育后所取得的学位名称。
	 */
	@Column(name = "SH_A29327A", length = 40)
	private String degreeName;
	
	/**
	 * @fieldName: 录用时学位代码
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人录用为公务员或参照管理人员前完成一定学历教育后所取得的学位代码。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A29327B")
	private CodeInfo degreeCode;
	
	/**
	 * @fieldName: 录用时基层工作时间
	 * @fieldType: java.lang.Integer
	 * @Description: 该人录用为公务员或参照管理人员时在基层单位的工作经历时间。
	 */
	@Column(name = "SH_A29334")
	private Integer basicWorkTime;
	
	/**
	 * @fieldName:是否退役士兵
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否退役士兵的标识。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A39064")
	private CodeInfo isRetiredSoldier;
	
	/**
	 * @fieldName:是否退役大学生士兵
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否退役大学生士兵的标识。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A39067")
	private CodeInfo isRetiredCollegeStudentSoldier;
	
	/**
	 * @fieldName:是否残疾人
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否残疾人的标识。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A39071")
	private CodeInfo isdisabled;
	
	/**
	 * @fieldName:留学年限
	 * @fieldType: java.lang.Integer
	 * @Description: 该人完成该海外留学学历教育，在校学习完成学业的规定时间。
	 */
	@Column(name = "SH_A39077")
	private Integer studyAbroadTime;
	
	/**
	 * @fieldName:海外工作年限
	 * @fieldType: java.lang.Integer
	 * @Description: 该人在海外工作的工作经历时间。
	 */
	@Column(name = "SH_A39084")
	private Integer workAbroadTime;
	
	/**
	 * @fieldName:是否有海外留学经历
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: RB006-2006/BSM 《标识码》。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A44027")
	private CodeInfo isStudyAbroad;
	
	/**
	 * @fieldName:是否有海外工作经历
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: RB006-2006/BSM 《标识码》。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A44031")
	private CodeInfo isWorkAbroad;
	
	/**
	 * @fieldName:该人属于哪种服务基层项目人员标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: RB006-2006/BSM 《标识码》。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_A39061")
	private CodeInfo isBasicWork;
	
	/**
	 * @fieldName: 人员来源情况
	 * @fieldType: java.lang.String
	 * @Description: 该人录用为公务员或参照管理人员时的来源情况。
	 */
	@Column(name = "SH_A29337", length = 120)
	private String sourceSituation;
	
	/**
	 * @fieldName:来源
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 单位增员类别代码（部分）。
	 */
	@ManyToOne
	@JoinColumn(name = "SH_SOURCE")
	private CodeInfo shSource;

	
	public SocialWorker getSocialWorker() {
		return socialWorker;
	}


	public void setSocialWorker(SocialWorker socialWorker) {
		this.socialWorker = socialWorker;
	}


	public CodeInfo getIsExamEmploy() {
		
		return isExamEmploy;
	}

	
	public void setIsExamEmploy(CodeInfo isExamEmploy) {
		
		this.isExamEmploy = isExamEmploy;
	}

	
	public CodeInfo getPolitics() {
		
		return politics;
	}

	
	public void setPolitics(CodeInfo politics) {
		
		this.politics = politics;
	}

	
	public String getEducationName() {
		
		return educationName;
	}

	
	public void setEducationName(String educationName) {
		
		this.educationName = educationName;
	}

	
	public CodeInfo getEducationCode() {
		
		return educationCode;
	}

	
	public void setEducationCode(CodeInfo educationCode) {
		
		this.educationCode = educationCode;
	}

	
	public String getDegreeName() {
		
		return degreeName;
	}

	
	public void setDegreeName(String degreeName) {
		
		this.degreeName = degreeName;
	}

	
	public CodeInfo getDegreeCode() {
		
		return degreeCode;
	}

	
	public void setDegreeCode(CodeInfo degreeCode) {
		
		this.degreeCode = degreeCode;
	}

	
	public Integer getBasicWorkTime() {
		
		return basicWorkTime;
	}

	
	public void setBasicWorkTime(Integer basicWorkTime) {
		
		this.basicWorkTime = basicWorkTime;
	}

	
	public CodeInfo getIsRetiredSoldier() {
		
		return isRetiredSoldier;
	}

	
	public void setIsRetiredSoldier(CodeInfo isRetiredSoldier) {
		
		this.isRetiredSoldier = isRetiredSoldier;
	}

	
	public CodeInfo getIsRetiredCollegeStudentSoldier() {
		
		return isRetiredCollegeStudentSoldier;
	}

	
	public void setIsRetiredCollegeStudentSoldier(CodeInfo isRetiredCollegeStudentSoldier) {
		
		this.isRetiredCollegeStudentSoldier = isRetiredCollegeStudentSoldier;
	}

	
	public CodeInfo getIsdisabled() {
		
		return isdisabled;
	}

	
	public void setIsdisabled(CodeInfo isdisabled) {
		
		this.isdisabled = isdisabled;
	}

	
	public Integer getStudyAbroadTime() {
		
		return studyAbroadTime;
	}

	
	public void setStudyAbroadTime(Integer studyAbroadTime) {
		
		this.studyAbroadTime = studyAbroadTime;
	}

	
	public Integer getWorkAbroadTime() {
		
		return workAbroadTime;
	}

	
	public void setWorkAbroadTime(Integer workAbroadTime) {
		
		this.workAbroadTime = workAbroadTime;
	}

	
	public CodeInfo getIsStudyAbroad() {
		
		return isStudyAbroad;
	}

	
	public void setIsStudyAbroad(CodeInfo isStudyAbroad) {
		
		this.isStudyAbroad = isStudyAbroad;
	}

	
	public CodeInfo getIsWorkAbroad() {
		
		return isWorkAbroad;
	}

	
	public void setIsWorkAbroad(CodeInfo isWorkAbroad) {
		
		this.isWorkAbroad = isWorkAbroad;
	}

	
	public CodeInfo getIsBasicWork() {
		
		return isBasicWork;
	}

	
	public void setIsBasicWork(CodeInfo isBasicWork) {
		
		this.isBasicWork = isBasicWork;
	}

	
	public String getSourceSituation() {
		
		return sourceSituation;
	}

	
	public void setSourceSituation(String sourceSituation) {
		
		this.sourceSituation = sourceSituation;
	}

	
	public CodeInfo getShSource() {
		
		return shSource;
	}

	
	public void setShSource(CodeInfo shSource) {
		
		this.shSource = shSource;
	}
	
}
