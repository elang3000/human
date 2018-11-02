/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: Assessment.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc
 * 描述:上海 考核
 * 创建人: jiang
 * 创建时间: 2018年7月2日10:00:20
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日10:00:23
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.socialworker;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.human.bo.ofc.base.BaseAssessment;

/**
 * 
 * @ClassName:  Assessment   
 * @Description:社工信息表   
 * @author: bianyf
 * @date:   2018年8月20日 下午4:10:50   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "E15")
public class SrAssessment extends BaseAssessment<SrAssessment> {
	
	
	private static final long serialVersionUID = 8349377151502542223L;
	
	/**
	 * @fieldName: socialWorker
	 * @fieldType: com.wondersgroup.human.bo.socialworker.SocialWorker
	 * @Description: 人员信息主表信息。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SOCIALWORKER_ID")
	private SocialWorker socialWorker;

	public SocialWorker getSocialWorker() {
		return socialWorker;
	}

	public void setSocialWorker(SocialWorker socialWorker) {
		this.socialWorker = socialWorker;
	}
	
}
