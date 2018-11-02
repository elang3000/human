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

package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.base.BasePost;

/**
 * 
 * @ClassName:  Post   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: bianyf
 * @date:   2018年8月20日 下午12:24:19   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "C02")
public class PtPost extends BasePost<PtPost> {
	
	private static final long serialVersionUID = -7571159177161817773L;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@JoinColumn(name="PUBLICINSTITUTION_ID")
	private PublicInstitution publicInstitution;

	public PublicInstitution getPublicInstitution() {
		return publicInstitution;
	}

	public void setPublicInstitution(PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
	}
	
	/**
	 * @fieldName: tenureForm
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 任职形式,RB114-2006/RZXS 《任职形式》 该人担任该职务的被任用方式（委任、聘任等）。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A0714")
	private CodeInfo tenureForm;
	
	/**
	 * @fieldName: isTeamMember
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否班子成员,RB006-2006/BSM 《标识码》。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_IS_BANZI")
	private CodeInfo isTeamMember;
	
	/**
	 * @fieldName: memberType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 成员类别,班子成员类别代码 该人在机构中所担任的成员类别。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_G01006")
	private CodeInfo memberType;
	
	/**
	 * @fieldName: postChangeReason
	 * @fieldType: java.lang.String
	 * @Description:职务变动原因综述，职务变动原因描述。
	 */
	@Column(name = "SH_A0272", length = 100)
	private String postChangeReason;
	
	/**
	 * @fieldName: isLowerLeader
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否兼任下级领导职务,在任职务需填。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_HAS_LOW_LEVEL")
	private CodeInfo isLowerLeader;
	
	/**
	 * @fieldName: isSkipLevel
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否越级提拔
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_IS_SKIP_LEVEL")
	private CodeInfo isSkipLevel;
	
	/**
	 * @fieldName: communicationType
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 交流方式，该人交流任职的方式。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A49001")
	private CodeInfo communicationType;
	
	/**
	 * @fieldName: communicationReason
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 交流原因，该人交流任职的原因。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A49004")
	private CodeInfo communicationReason;
	
	/**
	 * @fieldName: communicationWhere
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 交流去向，该人交流任职的去向。
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "SH_A49007")
	private CodeInfo communicationWhere;
	
	/**
	 * @fieldName: outputSign
	 * @fieldType: java.lang.String
	 * @Description:职务输出标识，该职务是否在任免表、名册中输出的标识。1—输出；0—不输出。用于历史公务员登记表的打印。
	 */
	@Column(name = "SH_A0281", length = 1)
	private String outputSign;
	
	
	/**
	 * @fieldName: highestPostSign
	 * @fieldType: java.lang.String
	 * @Description:最高职务标记，DM215。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SH_S0163_1")
	private CodeInfo highestPostSign;
	
	/**
	 * @fieldName: highestPostSign
	 * @fieldType: java.lang.String
	 * @Description:现任职务标记，DM215。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SH_S0163")
	private CodeInfo nowPostSign;
	
	/**
	 * @fieldName: nowJobLevel
	 * @fieldType: CodeInfo
	 * @Description:现任职务职级，GBT_12407_2008。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SH_S0164")
	private CodeInfo nowJobLevel;
	
	
	public CodeInfo getNowJobLevel() {
		return nowJobLevel;
	}

	public void setNowJobLevel(CodeInfo nowJobLevel) {
		this.nowJobLevel = nowJobLevel;
	}

	public CodeInfo getTenureForm() {
		
		return tenureForm;
	}
	
	public void setTenureForm(CodeInfo tenureForm) {
		
		this.tenureForm = tenureForm;
	}
	
	public CodeInfo getIsTeamMember() {
		
		return isTeamMember;
	}
	
	public void setIsTeamMember(CodeInfo isTeamMember) {
		
		this.isTeamMember = isTeamMember;
	}
	
	public CodeInfo getMemberType() {
		
		return memberType;
	}
	
	public void setMemberType(CodeInfo memberType) {
		
		this.memberType = memberType;
	}
	
	public String getPostChangeReason() {
		
		return postChangeReason;
	}
	
	public void setPostChangeReason(String postChangeReason) {
		
		this.postChangeReason = postChangeReason;
	}
	
	public CodeInfo getIsLowerLeader() {
		
		return isLowerLeader;
	}
	
	public void setIsLowerLeader(CodeInfo isLowerLeader) {
		
		this.isLowerLeader = isLowerLeader;
	}
	
	public CodeInfo getIsSkipLevel() {
		
		return isSkipLevel;
	}
	
	public void setIsSkipLevel(CodeInfo isSkipLevel) {
		
		this.isSkipLevel = isSkipLevel;
	}
	
	public CodeInfo getCommunicationType() {
		
		return communicationType;
	}
	
	public void setCommunicationType(CodeInfo communicationType) {
		
		this.communicationType = communicationType;
	}
	
	public CodeInfo getCommunicationReason() {
		
		return communicationReason;
	}
	
	public void setCommunicationReason(CodeInfo communicationReason) {
		
		this.communicationReason = communicationReason;
	}
	
	public CodeInfo getCommunicationWhere() {
		
		return communicationWhere;
	}
	
	public void setCommunicationWhere(CodeInfo communicationWhere) {
		
		this.communicationWhere = communicationWhere;
	}
	
	public String getOutputSign() {
		
		return outputSign;
	}
	
	public void setOutputSign(String outputSign) {
		
		this.outputSign = outputSign;
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
	
	
	
}
