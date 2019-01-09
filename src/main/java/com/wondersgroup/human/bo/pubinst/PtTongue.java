package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.pubinst.base.InstBaseServant;
import com.wondersgroup.human.bo.pubinst.base.InstBaseTongue;


/**
 * @ClassName:  PtTongue   
 * @Description:语言能力信息表   
 * @author: lyf
 * @date:   2018年9月6日 下午2:30:48   
 * @version [版本号, YYYY-MM-DD]
 *  @see       [相关类/方法]
 * @since     [产品/模块版本]    
 * @Copyright: 2018 万达信息股份有限公司 Inc. All rights reserved.
 */
@Entity
@Table(name = "C10")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PtTongue extends InstBaseTongue<PtTongue> {

	private static final long serialVersionUID = -6983749840985548362L;

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
