package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.human.bo.ofcflow.DraftServantReport;

import java.io.Serializable;

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
		this.totalGwy=dsp.getTotalGwy();
		this.totalGwy310=dsp.getTotalGwy310();
		
	}

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private Integer total;
	
	private Integer total310;
	
	private Integer totalCg;
	
	private Integer totalCg310;

	private Integer totalGwy310;

	private Integer totalGwy;
	
	private String unitName;
	
	private String  serialNumber;
	
	private String signer;
	
	private String creater;
	
	private Integer isAgree;

	private String createTime;

	public DraftServantReportVO(DraftServantReport dsp, UserService userService) {
		this.createTime= DateUtils.formatDateTime(dsp.getCreateTime());
		this.total=dsp.getTotal();
		this.total310=dsp.getTotal310();
		this.totalCg=dsp.getTotalCg();
		this.totalCg310=dsp.getTotalCg310();
		this.unitName=dsp.getUnitName();
		this.serialNumber=dsp.getSerialNumber();
		this.signer=dsp.getSigner();
		SecurityUser createrUser = userService.get(dsp.getCreater());
		if(createrUser!=null){
			this.creater=createrUser.getName();
		}else{
			this.creater=dsp.getCreater();
		}
		this.id=dsp.getId();
		this.isAgree=dsp.getIsAgree();
		this.totalGwy=dsp.getTotalGwy();
		this.totalGwy310=dsp.getTotalGwy310();
	}

	public Integer getTotal() {
		if(this.total==null){
			return 0;
		}
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotal310() {
		if(this.total310==null){
			return 0;
		}
		return total310;
	}

	public void setTotal310(Integer total310) {
		this.total310 = total310;
	}

	public Integer getTotalCg() {
		if(this.totalCg==null){
			return 0;
		}
		return totalCg;
	}

	public void setTotalCg(Integer totalCg) {
		this.totalCg = totalCg;
	}

	public Integer getTotalCg310() {
		if(this.totalCg310==null){
			return 0;
		}
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


	public Integer getTotalGwy310() {
		if(this.totalGwy310==null){
			return 0;
		}
		return totalGwy310;
	}

	public void setTotalGwy310(Integer totalGwy310) {
		this.totalGwy310 = totalGwy310;
	}

	public Integer getTotalGwy() {
		if(this.totalGwy==null){
			return 0;
		}
		return totalGwy;
	}

	public void setTotalGwy(Integer totalGwy) {
		this.totalGwy = totalGwy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
