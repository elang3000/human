/**
 * Copyright © 2019 . All rights reserved.万达信息股份有限公司
 * 文件名: PeopleVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: TODO
 * 创建人: GP
 * 创建时间: 2019年1月7日 下午4:25:14
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2019年1月7日 下午4:25:14
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.vo.ofc;

import com.wondersgroup.human.bo.ofc.PeopleGenericy;

/**
 * @ClassName: PeopleVO
 * @Description: TODO
 * @author: GP
 * @date: 2019年1月7日 下午4:25:14
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PeopleVO extends ServantVO {
	
	private String peopleType;
	
	/**
	 * 公务员、事业单位、国企职工、社工
	 */
	private String peopleTypeName;
	
	public String getPeopleType() {
		
		return peopleType;
	}
	
	public void setPeopleType(String peopleType) {
		
		this.peopleType = peopleType;
	}
	
	public String getPeopleTypeName() {
		
		return peopleTypeName;
	}
	
	public void setPeopleTypeName(String peopleTypeName) {
		
		this.peopleTypeName = peopleTypeName;
	}
	
	public PeopleVO() {
		
	}
	
	public PeopleVO(PeopleGenericy pg) {
		this.setId(pg.getId());
		this.setName(pg.getName());
		this.setCardNo(pg.getCardNo());
		if (pg.getSex() != null) {
			this.setSex(pg.getSex().getName());
		}
		this.setDepartName(pg.getDepartName());
		if (pg.getJobLevel() != null) {
			this.setJobLevel(pg.getJobLevel().getName());
		}
		this.setPostName(pg.getPostName());
		if (pg.getPostAttributeName() != null) {
			this.setPostAttributeName(pg.getPostAttributeName().getName());
		}
		if (pg.getIsOnHold() != null) {
			this.setIsOnHold(pg.getIsOnHold().getName());
		}
		this.setPeopleType(pg.getItype());
		if ("1".equals(pg.getItype())) {
			this.setPeopleTypeName("公务员");
		}
		if ("2".equals(pg.getItype())) {
			this.setPeopleTypeName("事业单位人员");
		}
		if ("3".equals(pg.getItype())) {
			this.setPeopleTypeName("国企职工");
		}
		if ("4".equals(pg.getItype())) {
			this.setPeopleTypeName("社工");
		}
	}
	
}
