/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ReferenceExchangeVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 参公交流 VO
 * 创建人: tzy
 * 创建时间: 2018年9月27日 下午3:01:43
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年9月27日 下午3:01:43
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchange;

/**
 * @ClassName: ReferenceExchangeVO
 * @Description: 参公交流 VO
 * @author: tzy
 * @date: 2018年9月27日 下午3:01:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ReferenceExchangeVO {
	
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
	 * @Description: 原单位名称
	 */
	private String sourceOrganName;
	
	/**
	 * @Description: 状态。
	 */
	private String status;
	
	/**
	 * @Description: 状态名称。
	 */
	private String statusName;
	
	public ReferenceExchangeVO() {
		
	}
	
	public ReferenceExchangeVO(ReferenceExchange d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (d.getServant() != null) {
			Servant s = d.getServant();
			this.name = s.getName();
			if (s.getSex() != null)
				this.sex = s.getSex().getName();
			if (s.getBirthDate() != null) {
				this.birthDate = sdf.format(s.getBirthDate());
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
			if (d.getNation() != null) {
				this.nation = d.getNation().getName();
			}
			this.cardNo = d.getCardNo();
			if (d.getPersonType() != null) {
				this.personType = d.getPersonType().getName();
			}
		}
		this.id = d.getId();
		this.sourceOrganName = d.getSourceOrganName();
		this.status = String.valueOf(d.getStatus());
		this.statusName = convertState(d.getStatus());
	}
	
	public String convertState(int state) {
		
		if (state == ReferenceExchange.STATUS_EXCHANGE_STATE) {
			return "待提交参公交流申请";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL) {
			return "待上级单位审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL_1) {
			return "待区人事主管部门一级审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL_2) {
			return "待区人事主管部门二级审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL_3) {
			return "待区人事主管部门三级审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL_4) {
			return "待区人事主管部门四级审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_AGREE) {
			return "待参公单位同意";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_PRINT) {
			return "待区人事主管部门打印电子介绍信";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_FINISH) {
			return "已完成申请";
		} else {
			return "";
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
	
	public String getSourceOrganName() {
		
		return sourceOrganName;
	}
	
	public void setSourceOrganName(String sourceOrganName) {
		
		this.sourceOrganName = sourceOrganName;
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
	
}
