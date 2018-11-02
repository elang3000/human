package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.bo.pubinst.base.InstBaseAddressCommunication;
/**
 * @ClassName: PtAddressCommunication
 * @Description: 地址与通信信息
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "C37")
public class PtAddressCommunication extends InstBaseAddressCommunication<PtAddressCommunication>{

	private static final long serialVersionUID = -7449547145363984250L;
	
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
