package com.wondersgroup.human.dto.instflow;

import java.io.Serializable;

public class RecordableRecordQuery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5048822938853947899L;

	
	private String name;
	
	private String cardNo;
	
	private String isOnHold;//任职状态 离退,退休,死亡,辞退,解聘

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIsOnHold() {
		return isOnHold;
	}

	public void setIsOnHold(String isOnHold) {
		this.isOnHold = isOnHold;
	}
	
	
	

}
