package com.wondersgroup.human.vo.ofcflow;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.JobShiftB;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchange;
import com.wondersgroup.human.service.ofc.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;

public class JobShiftBVO {


	/**
	 * @Description: ID。
	 */
	private String id;
	
	/**
	 * @Description: 姓名。
	 */
	private String name;
	
	/**
	 * @Description: 性别
	 */
	private String sex;
	
	/**
	 * @Description: 出生日期，GB/T 7408-2005 该人在公安户籍管理部门登记注册的、在人事档案中记载的并经组织、干部、人事部门确认的出生年月日。
	 */
	private String birthDate;
	
	/**
	 * @Description: 民族 ,GB 3304-1991 该人归属的、国家认可的、在公安户籍管理部门登记注册的民族。
	 */
	private String nation;
	
	/**
	 * @Description: 身份证。
	 */
	private String cardNo;
	
	/**
	 * @Description: 人员类别,DM199 该人职务相关的身份类别。
	 */
	private String personType;
	
	/**
	 * @Description: 原单位名称
	 */
	private String sourceOrganName;
	
	/**
	 * @Description: 状态。
	 */
	private String status;
	
	/**
	 * @Description: 状态名称。
	 */
	private String statusName;


	private String formerPostName;

	private String formerJobLevelName;

	private String newPostName;

	private String newJobLevelName;
	
	public JobShiftBVO() {
		
	}
	
	public JobShiftBVO(JobShiftB d,JobLevel jobLevel) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (d.getServant() != null) {
			Servant s = d.getServant();
			this.name = s.getName();
			if (s.getSex() != null)
				this.sex = s.getSex().getName();
			if (s.getBirthDate() != null) {
				this.birthDate = sdf.format(s.getBirthDate());
			}
			if (s.getNation() != null) {
				this.nation = s.getNation().getName();
			}
			if (s.getPersonType() != null) {
				this.personType = s.getPersonType().getName();
			}
			this.cardNo = s.getCardNoView();
			this.formerJobLevelName = jobLevel.getName()+"("+jobLevel.getIsLeaderStr()+")";
			this.formerPostName = d.getFormerPostName();
			this.newPostName = d.getNewPostName();
			if (d.getNewJobLevel() != null) {
				this.newJobLevelName = d.getNewJobLevel().getName()+"("+d.getIsLeaderStr()+")";
			}




		}
		this.id = d.getId();
//		this.sourceOrganName = d.getSourceOrganNode().getName();
		//如果已经提交流程，并且是起始节点，设置流程状态为99，不能编辑和删除
		if(d.getFlowRecord()!=null&&d.getStatus()==ReferenceExchange.STATUS_EXCHANGE_STATE){
			this.status = "99";
		}else{
			this.status = String.valueOf(d.getStatus());
		}
		if (d.getStatus()!=null) {
			this.statusName = convertState(d.getStatus());
		}
	}
	
	public String convertState(int state) {
		
		if (state == ReferenceExchange.STATUS_EXCHANGE_STATE) {
			return "待提交参公交流申请";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL) {
			return "待上级单位审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL_1) {
			return "待区人事主管部门一级审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL_2) {
			return "待区人事主管部门二级审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL_3) {
			return "待区人事主管部门三级审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_TRIAL_4) {
			return "待区人事主管部门四级审核";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_AGREE) {
			return "待参公单位同意";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_PRINT) {
			return "待区人事主管部门打印电子介绍信";
		} else if (state == ReferenceExchange.STATUS_EXCHANGE_FINISH) {
			return "已完成申请";
		} else {
			return "";
		}
	}

	public String getFormerPostName() {
		return formerPostName;
	}

	public void setFormerPostName(String formerPostName) {
		this.formerPostName = formerPostName;
	}

	public String getFormerJobLevelName() {
		return formerJobLevelName;
	}

	public void setFormerJobLevelName(String formerJobLevelName) {
		this.formerJobLevelName = formerJobLevelName;
	}

	public String getNewPostName() {
		return newPostName;
	}

	public void setNewPostName(String newPostName) {
		this.newPostName = newPostName;
	}

	public String getNewJobLevelName() {
		return newJobLevelName;
	}

	public void setNewJobLevelName(String newJobLevelName) {
		this.newJobLevelName = newJobLevelName;
	}

	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getSex() {
		
		return sex;
	}
	
	public void setSex(String sex) {
		
		this.sex = sex;
	}
	
	public String getBirthDate() {
		
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		
		this.birthDate = birthDate;
	}
	
	public String getNation() {
		
		return nation;
	}
	
	public void setNation(String nation) {
		
		this.nation = nation;
	}
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public String getPersonType() {
		
		return personType;
	}
	
	public void setPersonType(String personType) {
		
		this.personType = personType;
	}
	
	public String getSourceOrganName() {
		
		return sourceOrganName;
	}
	
	public void setSourceOrganName(String sourceOrganName) {
		
		this.sourceOrganName = sourceOrganName;
	}
	
	public String getStatus() {
		
		return status;
	}
	
	public void setStatus(String status) {
		
		this.status = status;
	}
	
	public String getStatusName() {
		
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		
		this.statusName = statusName;
	}
	

}
