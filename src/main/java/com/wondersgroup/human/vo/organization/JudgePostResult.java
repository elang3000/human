package com.wondersgroup.human.vo.organization;

public class JudgePostResult {
	
	/**
	 * 是否通过编控验证
	 */
	boolean result;
	
	/**
	 * 是否高职低配
	 */
	boolean isLowToHigh;
	
	public JudgePostResult(boolean result, boolean isLowToHigh) {
		super();
		this.result = result;
		this.isLowToHigh = isLowToHigh;
	}
}
