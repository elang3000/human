/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Education.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 学历
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:35:35
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:35:37
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.human.bo.ofc.base.BaseEducation;

/**
 * @ClassName: Education
 * @Description: 上海 学历
 * @author: jiang
 * @date: 2018年7月2日14:35:47
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "A08")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Education extends BaseEducation<Education> {
	
	private static final long serialVersionUID = -7170691304808657446L;
	
	/**
	 * @fieldName: servant
	 * @fieldType: com.wondersgroup.human.bo.ofc.Servant
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SERVANT_ID")
	private Servant servant;
	
	/**
	 * @fieldName: 所学专业名称(冗余字段保存大专业名称)
	 * @fieldType: java.lang.String
	 * @Description: 该人取得学历所学专业的具体名称。
	 */
	@Column(name = "R_A08024", length = 80)
	private String bigProfessionName;
	
	/**
	 * @fieldName: nineEightFiveFlag
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 985标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NINE_EIGHT_FIVE_FLAG")
	private CodeInfo nineEightFiveFlag;
	
	/**
	 * @fieldName: twoOneOneFlag
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 211标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TWO_ONE_ONE_FLAG")
	private CodeInfo twoOneOneFlag;
	
	/**
	 * @fieldName: isDoubleFirstRate
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 双一流标识。DM215
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOUBLE_FIRST_RATE_FlAG")
	private CodeInfo doubleFirstRateFlag;
	
	public Servant getServant() {
		
		return servant;
	}
	
	public void setServant(Servant servant) {
		
		this.servant = servant;
	}

	
	public String getBigProfessionName() {
		
		return bigProfessionName;
	}

	
	public void setBigProfessionName(String bigProfessionName) {
		
		this.bigProfessionName = bigProfessionName;
	}

	
	public CodeInfo getNineEightFiveFlag() {
		
		return nineEightFiveFlag;
	}

	
	public void setNineEightFiveFlag(CodeInfo nineEightFiveFlag) {
		
		this.nineEightFiveFlag = nineEightFiveFlag;
	}

	
	public CodeInfo getTwoOneOneFlag() {
		
		return twoOneOneFlag;
	}

	
	public void setTwoOneOneFlag(CodeInfo twoOneOneFlag) {
		
		this.twoOneOneFlag = twoOneOneFlag;
	}

	
	public CodeInfo getDoubleFirstRateFlag() {
		
		return doubleFirstRateFlag;
	}

	
	public void setDoubleFirstRateFlag(CodeInfo doubleFirstRateFlag) {
		
		this.doubleFirstRateFlag = doubleFirstRateFlag;
	}
	
}
