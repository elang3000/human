package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.pubinst.base.InstBaseOrganizationInspect;

/**
 * @ClassName: PtOrganizationInspect
 * @Description: 组织考察情况
 * @author: LYF
 * @date: 2018年9月6日 下午17:41:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name="C47")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PtOrganizationInspect extends InstBaseOrganizationInspect<PtOrganizationInspect> {

	
	private static final long serialVersionUID = 1174395211635861542L;

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
