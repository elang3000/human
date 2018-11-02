/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServantVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员基本信息VO
 * 创建人: tzy
 * 创建时间: 2018年5月28日 上午10:06:33
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月28日 上午10:06:33
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.socialworker;

import java.util.Date;

import com.wondersgroup.human.bo.socialworker.SrOutMgr;

/**
 * @ClassName: OutMgrVO
 * @Description: 社工调出信息VO
 * @author: lyf
 * @date: 2018年5月28日 上午10:06:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class OutMgrVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 调出本单位类别
	 * @fieldType: java.lang.String
	 * @Description: 该人调出或离开本单位的情况分类名称。
	 */
	private String category;
	
	/**
	 * @fieldName: 调出本单位日期
	 * @fieldType: java.util.Date
	 * @Description: 由组织、干部、人事、劳动部门签发的该人调出、退职、除名等文件的日期或者因其他原因实际离开的日期。
	 */
	private Date outDate;
	
	/**
	 * @fieldName: 调往单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人调往的工作单位的名称。
	 */
	private String gotoUnitName;
	
	
	/**
	 * @fieldName: 调往单位性质类别
	 * @fieldType: java.lang.String
	 * @Description: 该人调往单位性质的类别。
	 */
	private String gotoUnitProperties;
	
	/**
	 * @fieldName: 调往单位所属行业
	 * @fieldType: java.lang.String
	 * @Description: 该人调往单位属于的某类行业。
	 */
	private String gotoUnitOccupation;
	
	
	/**
	 * @fieldName: 提出调动类型
	 * @fieldType: java.lang.String
	 * @Description: 该人调动时是由工作需要组织调动，还是由个人申请调动的情况。
	 */
	private String proposeType;
	
	
	/**
	 * @fieldName: name
	 * @fieldType: java.lang.String
	 * @Description: 姓名 ,在公安户籍管理部门登记注册、人事档案中记载的、正在使用的该人姓名全称。
	 */
	private String name; 
	
	/**
	 * @fieldName: sex
	 * @fieldType: java.lang.String
	 * @Description: 性别。
	 */
	private String sex;
	
	/**
	 * @fieldName: cardNo
	 * @fieldType: java.lang.String
	 * @Description: 公民身份证号 ,公安机关对（公民）居民给出的唯一的、终身不变的法定号码。
	 */
	private String cardNo;
	
	/**
	 * @fieldName: departName
	 * @fieldType: java.lang.String
	 * @Description: 人事关系所在单位名称 ,该人人事关系所在的单位名称。
	 */
	private String departName;
	
	/**
	 * @fieldName: nowPostRank
	 * @fieldType: java.lang.String
	 * @Description: 现职务层次
	 */
	private String nowPostName;
	
	/**
	 * @fieldName: isOnHold
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 人员管理状态,DM200 该人在管理上的在职、离退等状态。
	 */
	private String isOnHold;
	
	/**
	 * 社工主表id
	 */
	private String swId;
	
	
	
	
	public OutMgrVO(){
		
	}
	
	/**
	 * @Title:OutMgrVO
	 * @Description:将OutMgr bo转换为vo
	 * @param s  转换的OutMgr BO对象
	 */
	public OutMgrVO(SrOutMgr o){
		this.id=o.getId();
		if (o.getCategory() !=null) {
			this.category = o.getCategory().getName();
		}
		this.outDate = o.getOutDate();
		this.gotoUnitName = o.getGotoUnitName();
		if (o.getGotoUnitProperties() != null) {
			this.gotoUnitProperties = o.getGotoUnitProperties().getName();
		}
		if (o.getGotoUnitOccupation() != null) {
			this.gotoUnitOccupation = o.getGotoUnitOccupation().getName();
		}
		if (o.getProposeType() != null) {
			this.proposeType = o.getProposeType().getName();
		}
		if (o.getSocialWorker() != null) {
			this.name = o.getSocialWorker().getName();
			if (o.getSocialWorker().getSex() != null) {
				this.sex = o.getSocialWorker().getSex().getName();
			}
			this.cardNo = o.getSocialWorker().getCardNo();
			this.departName = o.getSocialWorker().getDepartName();
            this.nowPostName = o.getSocialWorker().getNowPostName();
			if (o.getSocialWorker().getIsOnHold() != null) {
				this.isOnHold = o.getSocialWorker().getIsOnHold().getName();
			}
			this.swId = o.getSocialWorker().getId();
		}
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getGotoUnitName() {
		return gotoUnitName;
	}

	public void setGotoUnitName(String gotoUnitName) {
		this.gotoUnitName = gotoUnitName;
	}

	public String getGotoUnitProperties() {
		return gotoUnitProperties;
	}

	public void setGotoUnitProperties(String gotoUnitProperties) {
		this.gotoUnitProperties = gotoUnitProperties;
	}

	public String getGotoUnitOccupation() {
		return gotoUnitOccupation;
	}

	public void setGotoUnitOccupation(String gotoUnitOccupation) {
		this.gotoUnitOccupation = gotoUnitOccupation;
	}

	public String getProposeType() {
		return proposeType;
	}

	public void setProposeType(String proposeType) {
		this.proposeType = proposeType;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}


	public String getNowPostName() {
		return nowPostName;
	}

	public void setNowPostName(String nowPostName) {
		this.nowPostName = nowPostName;
	}

	public String getIsOnHold() {
		return isOnHold;
	}

	public void setIsOnHold(String isOnHold) {
		this.isOnHold = isOnHold;
	}

	public String getSwId() {
		return swId;
	}

	public void setSwId(String swId) {
		this.swId = swId;
	}
	
	
	
	
}
