package com.wondersgroup.human.bo.pubinst;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wondersgroup.human.bo.pubinst.base.InstBaseAppointDismissDuty;

/**
 * @ClassName: AppointDismissDuty
 * @Description: 拟任拟免职务
 * @author: LYF
 * @date: 2018年9月6日 下午6:09:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name="C53")
public class PtAppointDismissDuty extends InstBaseAppointDismissDuty<PtAppointDismissDuty>{

	

		private static final long serialVersionUID = -1439107420574613648L;

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
