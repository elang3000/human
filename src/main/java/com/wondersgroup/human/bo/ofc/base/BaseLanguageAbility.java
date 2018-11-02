/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: BaseLanguageAbility.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.ofc.base
 * 描述: 国标 语言能力
 * 创建人: jiang
 * 创建时间: 2018年9月7日14:31:48
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年9月7日14:31:51
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofc.base;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.wondersgroup.framework.core.bo.GenericEntity;
import com.wondersgroup.framework.dict.bo.CodeInfo;

/**
 * @ClassName: BaseLanguageAbility
 * @Description: 国标 语言能力
 * @author: jiang
 * @date: 2018年9月7日14:31:55
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@MappedSuperclass
public class BaseLanguageAbility<T> extends GenericEntity {
	
	private static final long serialVersionUID = -6165380281046083205L;
	
	/**
	 * @fieldName: 语种
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人掌握某一外国语及本国民族语言的语种名称。GB/T 4880.1-2005
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A10001")
	private CodeInfo category;
	
	/**
	 * @fieldName: 语种熟练程度
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人掌握该语种的熟练程度。GB/T 6865-2009-1
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A10004")
	private CodeInfo proficiency;
	
	/**
	 * @fieldName: 掌握语种水平的级别
	 * @fieldType: com.wondersgroup.framework.dict.bo.CodeInfo
	 * @Description: 该人掌握语种并经过国家教育部门组织的考试取得的外语水平级别。GB/T 6865-2009-2
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "A10015")
	private CodeInfo masterylevel;
	
	public CodeInfo getCategory() {
		
		return category;
	}
	
	public void setCategory(CodeInfo category) {
		
		this.category = category;
	}
	
	public CodeInfo getProficiency() {
		
		return proficiency;
	}
	
	public void setProficiency(CodeInfo proficiency) {
		
		this.proficiency = proficiency;
	}
	
	public CodeInfo getMasterylevel() {
		
		return masterylevel;
	}
	
	public void setMasterylevel(CodeInfo masterylevel) {
		
		this.masterylevel = masterylevel;
	}
	
}
