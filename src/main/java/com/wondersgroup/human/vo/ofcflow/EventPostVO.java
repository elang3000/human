/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: EventPostVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 流程信息 职务 临时表 VO
 * 创建人: tzy
 * 创建时间: 2018年7月31日 下午2:21:20
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月31日 下午2:21:20
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofcflow.EventPost;

/**
 * @ClassName: EventPostVO
 * @Description: 流程信息 职务 临时表 VO
 * @author: tzy
 * @date: 2018年7月31日 下午2:21:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class EventPostVO {
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * @Description:人员姓名，用于标识该职务信息属于哪个人员，因为长宁区中有两个人的身份证号不唯一。
	 */
	private String name;
	
	/**
	 * @Description:身份证号，用于标识该职务信息属于哪个人员。
	 */
	private String cardNo;
	
	/**
	 * @Description: 职务名称 ,该人担任职务的具体名称。
	 */
	private String postName;
	
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
	 * @Description: 任职机构名称 ,任职单位的名称。
	 */
	private String tenureName;
	
	/**
	 * @Description: 职务生效日期，GB/T 7408-2005 该职务经过某级人民代表大会选举通过或经过某级人大常委会讨论通过的任命生效日期。
	 */
	private String takeDate;
	
	public EventPostVO() {
		
	}
	
	public EventPostVO(EventPost p) {
		this.id = p.getId();
		this.name = p.getPersonName();
		this.cardNo = p.getCardNo();
		this.postName = p.getPostName();
		if (p.getTenureForm() != null) {
			this.tenureForm = p.getTenureForm().getName();
		}
		if (p.getIsTeamMember() != null) {
			this.isTeamMember = p.getIsTeamMember().getName();
		}
		if (p.getMemberType() != null) {
			this.memberType = p.getMemberType().getName();
		}
		this.postChangeReason = p.getPostChangeReason();
		this.tenureName = p.getTenureName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(p.getTakeDate()!=null){
			this.takeDate = sdf.format(p.getTakeDate());
		}
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public String getPostName() {
		
		return postName;
	}
	
	public void setPostName(String postName) {
		
		this.postName = postName;
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
	
	public String getTenureName() {
		
		return tenureName;
	}
	
	public void setTenureName(String tenureName) {
		
		this.tenureName = tenureName;
	}
	
	public String getTakeDate() {
		
		return takeDate;
	}
	
	public void setTakeDate(String takeDate) {
		
		this.takeDate = takeDate;
	}
	
}
