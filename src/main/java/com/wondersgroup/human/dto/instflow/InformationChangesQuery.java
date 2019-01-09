package com.wondersgroup.human.dto.instflow;

import java.io.Serializable;

public class InformationChangesQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2278733927415834798L;
	
	
	private String name1;//姓名
	
	private String cardNo;
	
	private String name;//职级名称

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
