package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.human.bo.pubinst.base.InstBaseResignDismiss;

/**
 * @ClassName: PtResignDismiss
 * @Description: 辞职辞退情况
 * @author: LYF
 * @date: 2018年9月6日 下午16:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "C51")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PtResignDismiss extends InstBaseResignDismiss<PtResignDismiss>{

	
	private static final long serialVersionUID = 8519994240589701043L;

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
