/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: RecruitEmployPostSummeryVO.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofcflow 
 * 描述: TODO
 * 创建人: wangzhanfei   
 * 创建时间: 2018年6月7日 下午4:31:18 
 * 版本号: V1.0
 * 修改人：wangzhanfei 
 * 修改时间：2018年6月7日 下午4:31:18 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.vo.ofcflow;

/** 
 * @ClassName: RecruitEmployPostSummeryVO 
 * @Description: TODO
 * @author: wangzhanfei
 * @date: 2018年6月7日 下午4:31:18
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class RecruitEmployPostSummeryVO {
	private String id;
	private String name;
	private String objectType;//招录对象
	private String professional;//专业类别
	private String education;//学历
	private String degree;//学位
	
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
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	
}
