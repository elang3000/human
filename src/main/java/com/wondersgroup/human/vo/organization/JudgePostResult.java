package com.wondersgroup.human.vo.organization;

public class JudgePostResult {
	
	/**
	 * 是否通过编控验证
	 */
	public Boolean result;
	
	/**
	 * 真实占用职级
	 */
	public String postLvlCode;
	
	/**
	 * 真实领导关系
	 */
	public Integer isLeader;
	
	/**
	 * 人数
	 */
	public Integer human;
	
	/**
	 * @Title:JudgePostResult
	 * @Description:TODO 
	 * @param result
	 * @param postLvlCode
	 * @param isLeader
	 */
	
	public JudgePostResult(boolean result, String postLvlCode,int isLeader) {
		super();
		this.result = result;
		this.postLvlCode = postLvlCode;
		this.isLeader = isLeader;
	}
	
	public JudgePostResult(String postLvlCode,int isLeader,int human) {
		super();
		this.result = null;
		this.postLvlCode = postLvlCode;
		this.isLeader = isLeader;
		this.human = human;
	}

	public JudgePostResult(String postLvlCode, Integer isLeader) {
		super();
		this.postLvlCode = postLvlCode;
		this.isLeader = isLeader;
	}

	
	public Boolean getResult() {
		
		return result;
	}

	
	public void setResult(Boolean result) {
		
		this.result = result;
	}

	
	public String getPostLvlCode() {
		
		return postLvlCode;
	}

	
	public void setPostLvlCode(String postLvlCode) {
		
		this.postLvlCode = postLvlCode;
	}

	
	public Integer getIsLeader() {
		
		return isLeader;
	}

	
	public void setIsLeader(Integer isLeader) {
		
		this.isLeader = isLeader;
	}

	
	public Integer getHuman() {
		
		return human;
	}

	
	public void setHuman(Integer human) {
		
		this.human = human;
	}
	
}
