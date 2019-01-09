package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.pubinst.base.InstBaseWithdrawalSoldier;

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
@Table(name = "C26")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PtWithdrawalSoldier extends InstBaseWithdrawalSoldier<PtWithdrawalSoldier>{

	private static final long serialVersionUID = -5551962827736508114L;

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
