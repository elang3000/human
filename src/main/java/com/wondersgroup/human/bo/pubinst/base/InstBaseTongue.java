package com.wondersgroup.human.bo.pubinst.base;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: InstBaseTongue
 * @Description: 国标 语言能力
 * @author: lyf
 * @date: 2018年9月5日09:02:40
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class InstBaseTongue<T> extends GenericEntity {


	private static final long serialVersionUID = 103646866197959978L;
	
	
	/**
	 * @fieldName: 语种
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 语种  数据字典。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A10001")
	private CodeInfo languageCategory;
	
	
	/**
	 * @fieldName: 语种熟练程度
	 * @fieldType: java.lang.String
	 * @Description: 语种熟练程度
	 */
	@Column(name = "A10004", length = 120)
	private String qualification;
	
	
	/**
	 * @fieldName: 掌握语种水平的级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 语种水平的级别  数据字典。
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A10015")
	private CodeInfo languageLevel;
	
	public CodeInfo getLanguageCategory() {
		return languageCategory;
	}


	public void setLanguageCategory(CodeInfo languageCategory) {
		this.languageCategory = languageCategory;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public CodeInfo getLanguageLevel() {
		return languageLevel;
	}


	public void setLanguageLevel(CodeInfo languageLevel) {
		this.languageLevel = languageLevel;
	}

	
	
	

}
