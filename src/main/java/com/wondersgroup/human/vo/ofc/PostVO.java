/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: PostVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-职务VO
 * 创建人: tzy
 * 创建时间: 2018年5月31日 上午9:47:55
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月31日 上午9:47:55
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofc;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.Post;

/**
 * @ClassName: PostVO
 * @Description: 人员信息子表-职务VO
 * @author: tzy
 * @date: 2018年5月31日 上午9:47:55
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PostVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: postName
	 * @fieldType: java.lang.String
	 * @Description: 职务名称 ,该人担任职务的具体名称。
	 */
	private String postName;
	
	/**
	 * @fieldName: tenureName
	 * @fieldType: java.lang.String
	 * @Description: 任职机构名称 ,任职单位的名称。
	 */
	private String tenureName;
	
	/**
	 * @fieldName: tenureReason
	 * @fieldType: java.lang.String
	 * @Description: 任职原因,DM004 该人任职原因的类别。
	 */
	private String tenureReason;
	
	/**
	 * @fieldName: tenureStatus
	 * @fieldType: java.lang.Integer
	 * @Description: 任职状态。
	 */
	private String tenureStatus;
	
	/**
	 * @Description: 任职形式,RB114-2006/RZXS 《任职形式》 该人担任该职务的被任用方式（委任、聘任等）。
	 */
	private String tenureForm;
	
	/**
	 * @Description: 是否班子成员,RB006-2006/BSM 《标识码》。
	 */
	private String isTeamMember;
	
	/**
	 * @Description: 成员类别,班子成员类别代码 该人在机构中所担任的成员类别。
	 */
	private String memberType;
	
	/**
	 * @Description:职务变动原因综述，职务变动原因描述。
	 */
	private String postChangeReason;
	
	/**
	 * @Description: 职务生效日期，GB/T 7408-2005 该职务经过某级人民代表大会选举通过或经过某级人大常委会讨论通过的任命生效日期。
	 */
	private String takeDate;
	
	
	/**
	 * @fieldName: attribute
	 * @Description: 职务属性,DM049 担任领导职务或非领导职务的情况。
	 */
	private String attribute;
	
	/**
	 * @fieldName: workContentRange
	 * @fieldType: java.lang.String
	 * @Description: 主管（从事）工作,该人主管、 从事工作的范围。
	 */
	private String workContentRange;
	
	public PostVO() {
		
	}
	
	/**
	 * @Title:PostVO
	 * @Description:将Post bo转换为vo
	 * @param s 转换的Post BO对象
	 */
	public PostVO(Post s) {
		this.id = s.getId();
		this.postName = s.getPostName();
		this.tenureName = s.getTenureName();
		this.workContentRange = s.getWorkContentRange();
		if (s.getTenureReason() != null) {
			this.tenureReason = s.getTenureReason().getName();
		}
		if (s.getTenureStatus() != null) {
			this.tenureStatus = s.getTenureStatus().getName();
		}
		if (s.getTenureForm() != null) {
			this.tenureForm = s.getTenureForm().getName();
		}
		if (s.getIsTeamMember() != null) {
			this.isTeamMember = s.getIsTeamMember().getName();
		}
		if (s.getMemberType() != null) {
			this.memberType = s.getMemberType().getName();
		}
		this.postChangeReason = s.getPostChangeReason();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getTakeDate() != null) {
			this.takeDate = sdf.format(s.getTakeDate());
		}
		if(s.getAttribute() !=null){
			this.attribute = s.getAttribute().getName();
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getPostName() {
		
		return postName;
	}
	
	public void setPostName(String postName) {
		
		this.postName = postName;
	}
	
	public String getTenureName() {
		
		return tenureName;
	}
	
	public void setTenureName(String tenureName) {
		
		this.tenureName = tenureName;
	}
	
	public String getTenureReason() {
		
		return tenureReason;
	}
	
	public void setTenureReason(String tenureReason) {
		
		this.tenureReason = tenureReason;
	}
	
	public String getTenureStatus() {
		
		return tenureStatus;
	}
	
	public void setTenureStatus(String tenureStatus) {
		
		this.tenureStatus = tenureStatus;
	}
	
	public String getTenureForm() {
		
		return tenureForm;
	}
	
	public void setTenureForm(String tenureForm) {
		
		this.tenureForm = tenureForm;
	}
	
	public String getIsTeamMember() {
		
		return isTeamMember;
	}
	
	public void setIsTeamMember(String isTeamMember) {
		
		this.isTeamMember = isTeamMember;
	}
	
	public String getMemberType() {
		
		return memberType;
	}
	
	public void setMemberType(String memberType) {
		
		this.memberType = memberType;
	}
	
	public String getPostChangeReason() {
		
		return postChangeReason;
	}
	
	public void setPostChangeReason(String postChangeReason) {
		
		this.postChangeReason = postChangeReason;
	}
	
	public String getTakeDate() {
		
		return takeDate;
	}
	
	public void setTakeDate(String takeDate) {
		
		this.takeDate = takeDate;
	}

	
	public String getAttribute() {
		
		return attribute;
	}

	
	public void setAttribute(String attribute) {
		
		this.attribute = attribute;
	}

	
	public String getWorkContentRange() {
		
		return workContentRange;
	}

	
	public void setWorkContentRange(String workContentRange) {
		
		this.workContentRange = workContentRange;
	}
	
}
