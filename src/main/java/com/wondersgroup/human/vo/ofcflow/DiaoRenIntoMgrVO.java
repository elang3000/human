/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DiaoRenIntoMgrVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 调任-调入信息VO
 * 创建人: tzy
 * 创建时间: 2018年7月30日 下午4:41:31
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月30日 下午4:41:31
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.bo.ofcflow.DiaoRenIntoMgr;
import com.wondersgroup.human.bo.pubinst.PublicInstitution;

/**
 * @ClassName: DiaoRenIntoMgrVO
 * @Description: 调任-调入信息VO
 * @author: tzy
 * @date: 2018年7月30日 下午4:41:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class DiaoRenIntoMgrVO {
	
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
	 * @Description: 原单位名称
	 */
	private String sourceorgan;
	
	/**
	 * @Description: 该人进入本单位前原工作单位性质的类别。DM142
	 */
	private String formerUnitNature;
	
	/**
	 * @Description: 调入时间。GB/T 7408-2005
	 */
	private String intoDate;
	
	/**
	 * @Description: 状态。
	 */
	private String status;
	
	/**
	 * @Description: 状态名称。
	 */
	private String statusName;
	
	public DiaoRenIntoMgrVO() {
		
	}
	
	public DiaoRenIntoMgrVO(DiaoRenIntoMgr d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(DiaoRenIntoMgr.AREA_OUTER.equals(d.getAreaType())){//外区人员
			this.name = d.getName();
			if (d.getSex() != null)
				this.sex = d.getSex().getName();
			if (d.getBirthDate() != null) {
				this.birthDate = sdf.format(d.getBirthDate());
			}
			this.nativePlaceName = d.getNativePlaceName();
			if (d.getNation() != null) {
				this.nation = d.getNation().getName();
			}
			this.cardNo = d.getCardNo();
		}else{
			if (DiaoRenIntoMgr.SOURCE_TYPE_1.equals(d.getSourceType())) {// 事业人员
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
					this.cardNo = s.getCardNo();
				}
			} else if (DiaoRenIntoMgr.SOURCE_TYPE_2.equals(d.getSourceType())) {// 国企职工
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
					this.cardNo = s.getCardNo();
				}
			}
		}
		
		if (d.getFormerUnitNature() != null) {
			this.formerUnitNature = d.getFormerUnitNature().getName();
		}
		if (d.getIntoDate() != null) {
			this.intoDate = sdf.format(d.getIntoDate());
		}
		
		this.sourceorgan = d.getFormerUnitName();
		this.id = String.valueOf(d.getId());
		this.status = String.valueOf(d.getStatus());
		this.statusName = convertState(d.getStatus());
	}
	
	public String convertState(int state) {
		
		if (state == DiaoRenIntoMgr.STATUS_DIAOREN_STATE) {
			return "待提出调任申请";
		} else if (state == DiaoRenIntoMgr.STATUS_DIAOREN_TRIAL) {
			return "待上级单位审核";
		} else if (state == DiaoRenIntoMgr.STATUS_DIAOREN_TRIAL_1) {
			return "待区人事主管部门一级审核";
		} else if (state == DiaoRenIntoMgr.STATUS_DIAOREN_TRIAL_2) {
			return "待区人事主管部门二级审核";
		} else if (state == DiaoRenIntoMgr.STATUS_DIAOREN_TRIAL_3) {
			return "待区人事主管部门三级审核";
		} else if (state == DiaoRenIntoMgr.STATUS_DIAOREN_TRIAL_4) {
			return "待区人事主管部门四级审核";
		} else if (state == DiaoRenIntoMgr.STATUS_DIAOREN_AGREE) {
			return "待调出单位同意";
		} else if (state == DiaoRenIntoMgr.STATUS_DIAOREN_PRINT) {
			return "待区人事主管部门打印电子介绍信";
		} else if (state == DiaoRenIntoMgr.STATUS_DIAOREN_FINISH) {
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
	
	public String getSourceorgan() {
		
		return sourceorgan;
	}
	
	public void setSourceorgan(String sourceorgan) {
		
		this.sourceorgan = sourceorgan;
	}
	
	public String getFormerUnitNature() {
		
		return formerUnitNature;
	}
	
	public void setFormerUnitNature(String formerUnitNature) {
		
		this.formerUnitNature = formerUnitNature;
	}
	
	public String getIntoDate() {
		
		return intoDate;
	}
	
	public void setIntoDate(String intoDate) {
		
		this.intoDate = intoDate;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public String getStatusName() {
		
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		
		this.statusName = statusName;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
}
