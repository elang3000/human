package com.wondersgroup.human.vo.ofcflow;

import java.io.Serializable;

import com.wondersgroup.human.bo.ofcflow.DraftServantReport;

/**
 * 
 * @ClassName: DraftServantReportVO 
 * @Description: 
 * @author: youyd
 * @date: 2018年9月3日 上午9:24:31
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class DraftServantReportVO implements Serializable{
	
	public DraftServantReportVO(DraftServantReport dsp){
		this.total=dsp.getTotal();
		this.total310=dsp.getTotal310();
		this.totalCg=dsp.getTotalCg();
		this.totalCg310=dsp.getTotalCg310();
		this.unitName=dsp.getUnitName();
		this.serialNumber=dsp.getSerialNumber();
		this.signer=dsp.getSigner();
		this.creater=dsp.getCreater();
		this.id=dsp.getId();
		this.isAgree=dsp.getIsAgree();
		
	}

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private Integer total;
	
	private Integer total310;
	
	private Integer totalCg;
	
	private Integer totalCg310;
	
	private String unitName;
	
	private String  serialNumber;
	
	private String signer;
	
	private String creater;
	
	private Integer isAgree;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotal310() {
		return total310;
	}

	public void setTotal310(Integer total310) {
		this.total310 = total310;
	}

	public Integer getTotalCg() {
		return totalCg;
	}

	public void setTotalCg(Integer totalCg) {
		this.totalCg = totalCg;
	}

	public Integer getTotalCg310() {
		return totalCg310;
	}

	public void setTotalCg310(Integer totalCg310) {
		this.totalCg310 = totalCg310;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsAgree() {
		if(isAgree==DraftServantReport.SERVANTREPORT_AGREE){
			return "同意";
		}else{
			return "尚未同意";
		}
	}

	public void setIsAgree(Integer isAgree) {
		this.isAgree = isAgree;
	}
	
	
	
}
