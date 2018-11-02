package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.human.bo.pubinst.base.InstBaseExchange;

/**
 * @ClassName: PtExchange
 * @Description: 交流情况
 * @author: LYF
 * @date: 2018年9月6日 下午15:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name="C49")
public class PtExchange extends InstBaseExchange<PtExchange>{

	
	private static final long serialVersionUID = 8748338183087392882L;
	
	 
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
