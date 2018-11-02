/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: IntoMgr.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc 
 * 描述: 上海 调入情况
 * 创建人: tzy   
 * 创建时间: 2018年7月30日 上午10:18:11 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年7月30日 上午10:18:11 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.human.bo.ofc.base.BaseIntoMgr;

/**
 * 
 * @ClassName:  IntoMgr   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: bianyf
 * @date:   2018年8月20日 下午12:20:57   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "C29")
public class PtIntoMgr extends BaseIntoMgr<PtIntoMgr>{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: 
	 */
	private static final long serialVersionUID = -6383606597727737235L;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = true)
	@JoinColumn(name="PUBLICINSTITUTION_ID")
	private PublicInstitution publicInstitution;

	public PublicInstitution getPublicInstitution() {
		return publicInstitution;
	}

	public void setPublicInstitution(PublicInstitution publicInstitution) {
		this.publicInstitution = publicInstitution;
	}
}
