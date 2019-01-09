/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Competence.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 专业技术任职资格信息
 * 创建人: jiang
 * 创建时间: 2018年6月12日16:24:31
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年6月11日15:07:44
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

import com.wondersgroup.human.bo.ofc.base.BaseProbation;

/**
 * 
 * @ClassName:  Competence   
 * @Description:专业技术任职资格信息   
 * @author: bianyf
 * @date:   2018年8月20日 下午4:56:10   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "E06")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SrCompetence extends BaseProbation<SrCompetence> {
	
	private static final long serialVersionUID = 8651294713054078089L;
	
	/**
	 * @fieldName: socialWorker
	 * @fieldType: com.wondersgroup.human.bo.socialworker.SocialWorker
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOCIALWORKER_ID")
	private SocialWorker socialWorker;
	
	/**
	 * @fieldName: 输出标识
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 是否在任免表中输出的标识。1—输出；0—不输出
	 */
	@Column(name = "SH_A0699")
	private Integer exportFlag;
	
	public SocialWorker getSocialWorker() {
		return socialWorker;
	}

	public void setSocialWorker(SocialWorker socialWorker) {
		this.socialWorker = socialWorker;
	}

	public Integer getExportFlag() {
		
		return exportFlag;
	}
	
	public void setExportFlag(Integer exportFlag) {
		
		this.exportFlag = exportFlag;
	}
	
}
