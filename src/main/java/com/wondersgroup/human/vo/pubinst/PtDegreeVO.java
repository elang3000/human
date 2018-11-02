/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DegreeVO.java
 * 工程名: human
 * 包名: com.wondersgroup.human.vo.ofc
 * 描述: 人员信息子表-学位VO
 * 创建人: jiang
 * 创建时间: 2018年7月2日14:08:15
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日14:08:18
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.vo.pubinst;

import java.text.SimpleDateFormat;

import com.wondersgroup.human.bo.ofc.Degree;
import com.wondersgroup.human.bo.pubinst.PtDegree;

/**
 * @ClassName: DegreeVO
 * @Description: 人员信息子表-学位VO
 * @author: jiang
 * @date: 2018年7月2日14:08:22
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PtDegreeVO {
	
	/**
	 * @fieldName: id
	 * @fieldType: java.lang.String
	 * @Description: id
	 */
	private String id;
	
	/**
	 * @fieldName: 学位名称
	 * @fieldType: java.lang.String
	 * @Description: 该人完成一定学历教育后所取得的学位名称。
	 */
	private String name;
	
	/**
	 * @fieldName: 学位授予日期
	 * @fieldType: java.util.Date
	 * @Description: 该人取得的学位证书的签发日期。GB/T 7408-2005
	 */
	private String grantDate;
	
	/**
	 * @fieldName: 学位授予单位
	 * @fieldType: java.lang.String
	 * @Description: 授予该人学位的单位名称。
	 */
	private String grantUnit;
	
	/**
	 * @fieldName: 学位证书号
	 * @fieldType: java.lang.String
	 * @Description: 取得学位证书的号码。
	 */
	private String degreeNo;
	
	public PtDegreeVO() {
		
	}
	
	/**
	 * @Title:DegreeVO
	 * @Description:将Degree bo转换为vo
	 * @param s 转换的Degree BO对象
	 */
	public PtDegreeVO(PtDegree s) {
		this.id = s.getId();
		this.name = s.getName();
		this.degreeNo = s.getDegreeNo();
		this.grantUnit = s.getGrantUnit();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (s.getGrantDate() != null) {
			this.grantDate = sdf.format(s.getGrantDate());
		}
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

	
	public String getGrantDate() {
		
		return grantDate;
	}

	
	public void setGrantDate(String grantDate) {
		
		this.grantDate = grantDate;
	}

	
	public String getGrantUnit() {
		
		return grantUnit;
	}

	
	public void setGrantUnit(String grantUnit) {
		
		this.grantUnit = grantUnit;
	}

	
	public String getDegreeNo() {
		
		return degreeNo;
	}

	
	public void setDegreeNo(String degreeNo) {
		
		this.degreeNo = degreeNo;
	}
	
}
