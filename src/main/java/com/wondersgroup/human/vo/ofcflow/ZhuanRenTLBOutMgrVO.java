/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenTLBIntoMgrVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 同类别转任 转出情况信息 VO
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

import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutMgr;

/**
 * @ClassName: ZhuanRenTLBIntoMgrVO
 * @Description: 同类别转任 转出情况信息 VO
 * @author: tzy
 * @date: 2018年8月1日 上午11:02:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ZhuanRenTLBOutMgrVO {
	
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
	 * @Description: 原工作单位
	 */
	private String sourceOrgan;
	
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
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	private String personType;
	
	/**
	 * @Description: 状态。
	 */
	private String status;
	
	/**
	 * @Description: 状态名称。
	 */
	private String statusName;
	
	/**
	 * @fieldName: 调出本单位类别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人调出或离开本单位的情况分类。GB/T 12405-2008
	 */
	private String category;
	
	/**
	 * @fieldName: 调出本单位日期
	 * @fieldType: java.util.Date
	 * @Description: 由组织、干部、人事、劳动部门签发的该人调出、退职、除名等文件的日期或者因其他原因实际离开的日期。GB/T 7408-2005
	 */
	private String outDate;
	
	/**
	 * @fieldName: 调往单位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人调往的工作单位的名称。
	 */
	private String gotoUnitName;
	
	/**
	 * @fieldName: 调动原因
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人工作调动的原因。DM015
	 */
	private String reasonType;
	
	public ZhuanRenTLBOutMgrVO() {
		
	}
	
	public ZhuanRenTLBOutMgrVO(ZhuanRenTLBOutMgr d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (d.getServant() != null) {
			Servant s = d.getServant();
			this.name = s.getName();
			if (s.getSex() != null)
				this.sex = s.getSex().getName();
			if (s.getBirthDate() != null) {
				this.birthDate = sdf.format(s.getBirthDate());
			}
			if (s.getNativePlace() != null) {
				this.nativePlaceName = s.getNativePlace().getName();
			}
			if (s.getNation() != null) {
				this.nation = s.getNation().getName();
			}
			this.cardNo = s.getCardNo();
			if (s.getPersonType() != null) {
				this.personType = s.getPersonType().getName();
			}
		}
		if (d.getCategory() != null) {
			this.category = d.getCategory().getName();
		}
		if (d.getOutDate() != null) {
			this.outDate = sdf.format(d.getOutDate());
		}
		this.id = d.getId();
		if (d.getSourceOrgan() != null) {
			this.sourceOrgan = d.getSourceOrgan().getName();
		}
		this.gotoUnitName = d.getGotoUnitName();
		if(d.getStatus() == ZhuanRenTLBOutMgr.STATUS_ZHUANCHU_CONFIRM){
			OrganNode organ = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());
			if(organ!=null&&d.getSourceOrgan()!=null){
				if(!organ.getId().equals(d.getSourceOrgan().getId())){//如果当前登录人所在单位不是转出单位，只有查看权限
					this.status = "3";
				}else{
					this.status = ZhuanRenTLBOutMgr.STATUS_ZHUANCHU_CONFIRM+"";
				}
			}else{
				this.status = "3";
			}
		}else{
			this.status = String.valueOf(d.getStatus());
		}
		if (d.getReasonType() != null) {
			this.reasonType = d.getReasonType().getName();
		}
		this.statusName = convertState(d.getStatus());
	}
	
	public String convertState(int state) {
		
		if (state == ZhuanRenTLBOutMgr.STATUS_ZHUANCHU_STATE) {
			return "待发起转出流程";
		} else if (state == ZhuanRenTLBOutMgr.STATUS_ZHUANCHU_CONFIRM) {
			return "待转出单位备案";
		} else if (state == ZhuanRenTLBOutMgr.STATUS_ZHUANCHU_FINISH) {
			return "人员转出信息已备案";
		} else {
			return "";
		}
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
	
	public String getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(String personType) {
		
		this.personType = personType;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
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
	
	public String getOutDate() {
		
		return outDate;
	}
	
	public void setOutDate(String outDate) {
		
		this.outDate = outDate;
	}
	
	public String getGotoUnitName() {
		
		return gotoUnitName;
	}
	
	public void setGotoUnitName(String gotoUnitName) {
		
		this.gotoUnitName = gotoUnitName;
	}
	
	public String getReasonType() {
		
		return reasonType;
	}
	
	public void setReasonType(String reasonType) {
		
		this.reasonType = reasonType;
	}
	
	public String getStatusName() {
		
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		
		this.statusName = statusName;
	}
	
	public String getSourceOrgan() {
		
		return sourceOrgan;
	}
	
	public void setSourceOrgan(String sourceOrgan) {
		
		this.sourceOrgan = sourceOrgan;
	}
}