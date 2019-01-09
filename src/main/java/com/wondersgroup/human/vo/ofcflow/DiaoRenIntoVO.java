/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DiaoRenIntoVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 调任 调入情况信息 VO
 * 创建人: tzy
 * 创建时间: 2018年8月1日 上午11:02:16
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年8月1日 上午11:02:16
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.bo.ofcflow.DiaoRenInto;
import com.wondersgroup.human.bo.ofcflow.DiaoRenIntoBatch;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;

/**
 * @ClassName: DiaoRenIntoVO
 * @Description: 调任 转入情况信息 VO
 * @author: tzy
 * @date: 2018年8月1日 上午11:02:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class DiaoRenIntoVO {
	
	/**
	 * @Description: ID。
	 */
	private String id;
	
	/**
	 * @Description: 姓名。
	 */
	private String name;
	
	/**
	 * @Description: 性别
	 */
	private String sex;
	
	/**
	 * @Description: 出生日期，GB/T 7408-2005 该人在公安户籍管理部门登记注册的、在人事档案中记载的并经组织、干部、人事部门确认的出生年月日。
	 */
	private String birthDate;
	
	/**
	 * @Description: 籍贯名称 ,该人祖居所在地的当前县级及县级以上国家行政区划名称
	 */
	private String nativePlaceName;
	
	/**
	 * @Description: 民族 ,GB 3304-1991 该人归属的、国家认可的、在公安户籍管理部门登记注册的民族。
	 */
	private String nation;
	
	/**
	 * @Description: 身份证。
	 */
	private String cardNo;
	
	/**
	 * @Description: 该人调入本单位原因。DM015
	 */
	private String enterReason;
	
	/**
	 * @Description: 该人进入本单位前原工作单位名称。
	 */
	private String formerUnitName;
	
	/**
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	private String personType;
	
	/**
	 * @Description: 拟任职务
	 */
	private String postName;
	
	/**
	 * @Description: 职级名称
	 */
	private String jobLevelName;
	
	/**
	 * @Description: 职级属性
	 */
	private String isLeaderView;
	
	public DiaoRenIntoVO() {
		
	}
	
	public DiaoRenIntoVO(DiaoRenInto d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (DiaoRenIntoBatch.AREA_OUTER.equals(d.getDiaoRenIntoBatch().getAreaType())) {
			this.name = d.getName();
			if (d.getSex() != null)
				this.sex = d.getSex().getName();
			if (d.getBirthDate() != null) {
				this.birthDate = sdf.format(d.getBirthDate());
			}
			if (d.getNativePlaceName() != null) {
				this.nativePlaceName = d.getNativePlaceName();
			}
			if (d.getNation() != null) {
				this.nation = d.getNation().getName();
			}
			if (d.getPersonType() != null) {
				this.personType = d.getPersonType().getName();
			}
			this.cardNo = d.getCardNoView();
		} else {
			if (DiaoRenIntoBatch.SOURCE_TYPE_1.equals(d.getDiaoRenIntoBatch().getAreaType())) {// 事业人员
				if (d.getPublicInstitution() != null) {
					PublicInstitution s = d.getPublicInstitution();
					this.name = s.getName();
					if (s.getSex() != null)
						this.sex = s.getSex().getName();
					if (s.getBirthDate() != null) {
						this.birthDate = sdf.format(s.getBirthDate());
					}
					this.nativePlaceName = s.getNativePlaceName();
					if (s.getNation() != null) {
						this.nation = s.getNation().getName();
					}
					this.cardNo = s.getCardNoView();
				}
			} else if (DiaoRenIntoBatch.SOURCE_TYPE_2.equals(d.getDiaoRenIntoBatch().getAreaType())) {// 国企职工
				if (d.getNationalCompany() != null) {
					NationalCompany s = d.getNationalCompany();
					this.name = s.getName();
					if (s.getSex() != null)
						this.sex = s.getSex().getName();
					if (s.getBirthDate() != null) {
						this.birthDate = sdf.format(s.getBirthDate());
					}
					this.nativePlaceName = s.getNativePlaceName();
					if (s.getNation() != null) {
						this.nation = s.getNation().getName();
					}
					this.cardNo = s.getCardNoView();
				}
			}
		}
		if (d.getEnterReason() != null) {
			this.enterReason = d.getEnterReason().getName();
		}
		this.id = d.getId();
		this.formerUnitName = d.getFormerUnitName();
		this.jobLevelName = d.getJobLevelName();
		this.postName = d.getPostName();
		this.isLeaderView = d.getIsLeaderView();
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getSex() {
		
		return sex;
	}
	
	public void setSex(String sex) {
		
		this.sex = sex;
	}
	
	public String getBirthDate() {
		
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		
		this.birthDate = birthDate;
	}
	
	public String getNativePlaceName() {
		
		return nativePlaceName;
	}
	
	public void setNativePlaceName(String nativePlaceName) {
		
		this.nativePlaceName = nativePlaceName;
	}
	
	public String getNation() {
		
		return nation;
	}
	
	public void setNation(String nation) {
		
		this.nation = nation;
	}
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public String getEnterReason() {
		
		return enterReason;
	}
	
	public void setEnterReason(String enterReason) {
		
		this.enterReason = enterReason;
	}
	
	public String getFormerUnitName() {
		
		return formerUnitName;
	}
	
	public void setFormerUnitName(String formerUnitName) {
		
		this.formerUnitName = formerUnitName;
	}
	
	public String getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(String personType) {
		
		this.personType = personType;
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
	
	public String getJobLevelName() {
		
		return jobLevelName;
	}
	
	public void setJobLevelName(String jobLevelName) {
		
		this.jobLevelName = jobLevelName;
	}
	
	public String getIsLeaderView() {
		
		return isLeaderView;
	}
	
	public void setIsLeaderView(String isLeaderView) {
		
		this.isLeaderView = isLeaderView;
	}
	
}
