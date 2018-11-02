package com.wondersgroup.human.bo.pubinst.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.wondersgroup.framework.core.bo.GenericEntity;

/**
 * @ClassName: InstBaseWageWelfareProject
 * @Description: 国标  工资福利项目
 * @author: lyf
 * @date: 2018年9月6日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseWageWelfareProject<T> extends GenericEntity {

	private static final long serialVersionUID = 1938654500487974545L;
	
	
	/**
	 * @fieldName: personnelChangePeriod
	 * @fieldType: java.lang.String
	 * @Description: 事业单位管理人员套改年限
	 */
	@Column(name = "A33233", length = 120)
	private String personnelChangePeriod;
	
	/**
	 * @fieldName: personnelPostPeriod
	 * @fieldType: java.lang.String
	 * @Description: 事业单位管理人员任职年限
	 */
	@Column(name = "A33234", length = 120)
	private String personnelPostPeriod;
	
	/**
	 * @fieldName: managerRank
	 * @fieldType: java.lang.String
	 * @Description: 事业单位管理人员岗位等级
	 */
	@Column(name = "A33235", length = 120)
	private String managerRank;
	
	/**
	 * @fieldName: technologyChangePeriod
	 * @fieldType: java.lang.String
	 * @Description: 事业单位专业技术人员任职年限
	 */
	@Column(name = "A33236", length = 120)
	private String technologyChangePeriod;
	
	
	/**
	 * @fieldName: technologyPostPeriod
	 * @fieldType: java.lang.String
	 * @Description: 事业单位专业技术人员岗位等级
	 */
	@Column(name = "A33237", length = 120)
	private String technologyPostPeriod;
	
	
	
	/**
	 * @fieldName: workerChangePeriod
	 * @fieldType: java.lang.String
	 * @Description: 事业单位技术工人套改年限
	 */
	@Column(name = "A33239", length = 120)
	private String workerChangePeriod;
	
	/**
	 * @fieldName: workerPostPeriod
	 * @fieldType: java.lang.String
	 * @Description: 事业单位技术工人任职年限
	 */
	@Column(name = "A33240", length = 120)
	private String workerPostPeriod;
	
	/**
	 * @fieldName: workerRank
	 * @fieldType: java.lang.String
	 * @Description: 事业单位技术工人岗位等级
	 */
	@Column(name = "A33241", length = 120)
	private String workerRank;
	
	
	/**
	 * @fieldName: publicWorkerChangePeriod
	 * @fieldType: java.lang.String
	 * @Description: 事业单位普通工人套改年限
	 */
	@Column(name = "A33242", length = 120)
	private String publicWorkerChangePeriod;


	public String getPersonnelChangePeriod() {
		return personnelChangePeriod;
	}


	public void setPersonnelChangePeriod(String personnelChangePeriod) {
		this.personnelChangePeriod = personnelChangePeriod;
	}


	public String getPersonnelPostPeriod() {
		return personnelPostPeriod;
	}


	public void setPersonnelPostPeriod(String personnelPostPeriod) {
		this.personnelPostPeriod = personnelPostPeriod;
	}


	public String getManagerRank() {
		return managerRank;
	}


	public void setManagerRank(String managerRank) {
		this.managerRank = managerRank;
	}


	public String getTechnologyChangePeriod() {
		return technologyChangePeriod;
	}


	public void setTechnologyChangePeriod(String technologyChangePeriod) {
		this.technologyChangePeriod = technologyChangePeriod;
	}


	public String getTechnologyPostPeriod() {
		return technologyPostPeriod;
	}


	public void setTechnologyPostPeriod(String technologyPostPeriod) {
		this.technologyPostPeriod = technologyPostPeriod;
	}


	public String getWorkerChangePeriod() {
		return workerChangePeriod;
	}


	public void setWorkerChangePeriod(String workerChangePeriod) {
		this.workerChangePeriod = workerChangePeriod;
	}


	public String getWorkerPostPeriod() {
		return workerPostPeriod;
	}


	public void setWorkerPostPeriod(String workerPostPeriod) {
		this.workerPostPeriod = workerPostPeriod;
	}


	public String getWorkerRank() {
		return workerRank;
	}


	public void setWorkerRank(String workerRank) {
		this.workerRank = workerRank;
	}


	public String getPublicWorkerChangePeriod() {
		return publicWorkerChangePeriod;
	}


	public void setPublicWorkerChangePeriod(String publicWorkerChangePeriod) {
		this.publicWorkerChangePeriod = publicWorkerChangePeriod;
	}
	
	 
}
