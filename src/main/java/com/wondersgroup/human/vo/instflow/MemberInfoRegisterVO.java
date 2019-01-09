/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: RecruitEmployPlanVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow
 * 描述: 招录计划VO
 * 创建人: wangzhanfei
 * 创建时间: 2018年5月30日 上午10:57:27
 * 版本号: V1.0
 * 修改人：wangzhanfei
 * 修改时间：2018年5月30日 上午10:57:27
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.instflow;

import com.wondersgroup.human.bo.instflow.MemberInfoRegister;

/**
 * @ClassName: RecruitEmployPlanVO
 * @Description:人员录入登记VO
 * @author: luoyongfu
 * @date: 2018年12月26日 上午10:57:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class MemberInfoRegisterVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
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
	 * @fieldName: status
	 * @fieldType: String
	 * @Description: 状态
	 */
	private String status;
	
	public MemberInfoRegisterVO(MemberInfoRegister r){
		this.id = r.getId();
		this.name = r.getPublicInstitution().getName();
		if (r.getPublicInstitution().getSex() != null) {
			this.sex = r.getPublicInstitution().getSex().getName();
		}
		this.cardNo = r.getPublicInstitution().getCardNoView();
		this.departName = r.getPublicInstitution().getDepartName();
		this.status = convertState(r.getPlanState());
	}
	
	public String convertState(Integer state) {
		if (state == null) {
			return "流程办结";
		}else if (state == 1) {
			return "待上级单位初审";
		} else if (state == 2) {
			return "待区人事主管部门";
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
