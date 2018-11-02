package com.wondersgroup.human.vo.company;

import java.util.Date;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.company.CyOutMgr;

public class NcOutMgrVo {

	private String id;
	
	/*
	 * 调出本单位类别
	 * 该人调出或离开本单位的情况分类名称
	 */
	
	private String category;
	
	/*
	 * 调出本单位时间
	 * 
	 */
	private Date outDate;
	
	/*
	 * 调往单位名称
	 */
	private String gotoUnitName;
	
	/*
	 * 调往单位性质类别
	 */
	private String gotoUnitProperties;
	
	/*
	 *调往单位所属行业 
	 */
	private String gotoUnitOccupation;
	
	/*
	 * 提出调动类型
	 */
	private String proposeType;
	
	/*
	 * 姓名
	 */
	private String name;
	
	/*
	 * 性别
	 */
	private String sex;
	
	/*
	 * 公民身份证
	 */
	private String cardNo;
	
	/*
	 * 人事关系所在单位名称
	 */
	private String departName;
	
	/*
	 * 现职务层次
	 */
	private String nowPostName;
	
	/*
	 * 人员管理状态,DM200 该人在管理上的在职、离退等状态。
	 */
	private String isOnHold;
	
	/*
	 * 社工主表id
	 */
	
	private String swId;
	
	
	public NcOutMgrVo(){
		
	}
	
	/**
	 * @Title:NcOutMgrVO
	 * @Description:将NcOutMgr bo转换为vo
	 * @param s  转换的NcOutMgr BO对象
	 */
	
	public NcOutMgrVo(CyOutMgr c){
		this.id=c.getId();
		if(c.getCategory() != null){//调出本单位类别
			this.category=c.getCategory().getName();
		}
		this.outDate=c.getOutDate();
		this.gotoUnitName=c.getGotoUnitName();
		if(c.getGotoUnitProperties() != null){ //调往单位性质类别
			this.gotoUnitProperties=c.getGotoUnitProperties().getName();
		}
		if(c.getGotoUnitOccupation() != null){//调往单位所属行业
			
			this.gotoUnitOccupation=c.getGotoUnitOccupation().getName();
		}
		if(c.getProposeType() != null){ //提出调动类型
			this.proposeType=c.getProposeType().getName();
		}
		
		if(c.getNationalCompany() != null){
			this.name=c.getNationalCompany().getName();
			if(c.getNationalCompany().getSex() != null){//性别
				this.sex=c.getNationalCompany().getSex().getName();
			}
			
			this.cardNo=c.getNationalCompany().getCardNo();
			this.departName=c.getNationalCompany().getDepartName();
			this.nowPostName=c.getNationalCompany().getNowPostName();
			if(c.getNationalCompany().getIsOnHold() != null){//人员管理状态,在职或离退
				this.isOnHold=c.getNationalCompany().getIsOnHold().getName();
			}
			this.swId=c.getNationalCompany().getId();
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

//	public String getNowPostName() {
//		return nowPostName;
//	}
//
//	public void setNowPostName(String nowPostName) {
//		this.nowPostName = nowPostName;
//	}
	
	

	public String getIsOnHold() {
		return isOnHold;
	}

	
	

	public String getNowPostName() {
		return nowPostName;
	}

	public void setNowPostName(String nowPostName) {
		this.nowPostName = nowPostName;
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
