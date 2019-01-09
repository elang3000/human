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

package com.wondersgroup.human.bo.socialworker;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.ofc.base.BaseEducation;

/**
 * 
 * @ClassName:  Education   
 * @Description:学历   
 * @author: bianyf
 * @date:   2018年8月20日 下午4:58:10   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "E08")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SrEducation extends BaseEducation<SrEducation> {
	
	private static final long serialVersionUID = -7170691304808657446L;
	
	/**
	 * @fieldName: socialWorker
	 * @fieldType: com.wondersgroup.human.bo.socialworker.SocialWorker
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOCIALWORKER_ID")
	private SocialWorker socialWorker;
	

	/**
	 * @fieldName: 所学专业名称(冗余字段保存大专业名称)
	 * @fieldType: java.lang.String
	 * @Description: 该人取得学历所学专业的具体名称。
	 */
	@Column(name = "R_A08024", length = 80)
	private String bigProfessionName; 
	
	public SocialWorker getSocialWorker() {
		return socialWorker;
	}

	public void setSocialWorker(SocialWorker socialWorker) {
		this.socialWorker = socialWorker;
	}

	public String getBigProfessionName() {
		return bigProfessionName;
	}

	public void setBigProfessionName(String bigProfessionName) {
		this.bigProfessionName = bigProfessionName;
	}
	
	
}
