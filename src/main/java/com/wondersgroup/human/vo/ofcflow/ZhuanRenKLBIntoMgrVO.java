/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ZhuanRenKLBIntoMgrVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 跨类别转任 调入情况信息 VO
 * 创建人: tzy
 * 创建时间: 2018年9月20日 上午11:02:16
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年9月20日 上午11:02:16
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenKLBIntoMgr;

/**
 * @ClassName: ZhuanRenKLBIntoMgrVO
 * @Description: 跨类别转任 转入情况信息 VO
 * @author: tzy
 * @date: 2018年9月20日 上午11:02:16
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ZhuanRenKLBIntoMgrVO {
	
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
	 * @Description: 状态。
	 */
	private String status;
	
	/**
	 * @Description: 状态名称。
	 */
	private String statusName;
	
	public ZhuanRenKLBIntoMgrVO() {
		
	}
	
	public ZhuanRenKLBIntoMgrVO(ZhuanRenKLBIntoMgr d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (d.getServant() != null) {
			Servant s = d.getServant();
			this.name = s.getName();
			if (s.getSex() != null)
				this.sex = s.getSex().getName();
			if (s.getBirthDate() != null) {
				this.birthDate = sdf.format(s.getBirthDate());
			}
			if (s.getNativePlaceName() != null) {
				this.nativePlaceName = s.getNativePlaceName();
			}
			if (s.getNation() != null) {
				this.nation = s.getNation().getName();
			}
			if (s.getPersonType() != null) {
				this.personType = s.getPersonType().getName();
			}
			this.cardNo = s.getCardNo();
		} else {
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
			this.cardNo = d.getCardNo();
		}
		if (d.getEnterReason() != null) {
			this.enterReason = d.getEnterReason().getName();
		}
		this.id = d.getId();
		this.formerUnitName = d.getFormerUnitName();
		this.status = String.valueOf(d.getStatus());
		this.statusName = convertState(d.getStatus());
	}
	
	public String convertState(int state) {
		
		if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_STATE) {
			return "待提交转任申请";
		} else if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_TRIAL) {
			return "待上级单位审核";
		} else if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_TRIAL_1) {
			return "待区人事主管部门一级审核";
		} else if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_TRIAL_2) {
			return "待区人事主管部门二级审核";
		} else if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_TRIAL_3) {
			return "待区人事主管部门三级审核";
		} else if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_TRIAL_4) {
			return "待区人事主管部门四级审核";
		} else if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_AGREE) {
			return "待转出单位同意";
		} else if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_PRINT) {
			return "待区人事主管部门打印电子介绍信";
		} else if (state == ZhuanRenKLBIntoMgr.STATUS_ZHUANREN_FINISH) {
			return "人员信息已入库";
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
	
	public String getStatusName() {
		
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		
		this.statusName = statusName;
	}
	
}
