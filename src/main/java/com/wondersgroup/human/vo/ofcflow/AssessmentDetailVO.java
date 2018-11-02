
package com.wondersgroup.human.vo.ofcflow;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.bo.ofcflow.AssessmentDetail;

/**
 * @ClassName: AssessmentDetailVO
 * @Description: 考核记录视图类
 * @author: youyd
 * @date: 2018年9月26日 下午6:50:14
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class AssessmentDetailVO {
	
	private String id;
	
	private String cardNo;
	
	private String name;
	
	private String orgName;
	
	// 考核结果
	private String result;
	
	// 备注
	private String remarks;
	
	public AssessmentDetailVO(AssessmentDetail assessmentDetail) {
		this.id = assessmentDetail.getId();
		this.cardNo = assessmentDetail.getServant().getCardNo();
		this.orgName = assessmentDetail.getServant().getDepartName();
		this.name = assessmentDetail.getServant().getName();
		this.remarks = assessmentDetail.getRemarks();
		if(null!=assessmentDetail.getResult()){
			this.result = assessmentDetail.getResult().getName();
		}
		
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public String getResult() {
		if(StringUtils.isBlank(result)){
			return "未审核";
		}
		return result;
	}
	
	public void setResult(String result) {
		
		this.result = result;
	}
	
	public String getRemarks() {
		if(StringUtils.isBlank(remarks)){
			return "-";
		}
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		
		this.remarks = remarks;
	}
	
	public String getId() {
		
		return id;
	}
	
	public void setId(String id) {
		
		this.id = id;
	}
	
	public String getCardNo() {
		
		return cardNo;
	}
	
	public void setCardNo(String cardNo) {
		
		this.cardNo = cardNo;
	}
	
	public String getOrgName() {
		
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		
		this.orgName = orgName;
	}
	
	/**
	 * 
	 * @Title: AssessmentDetail2VO 
	 * @Description: 类转vo类方法
	 * @param list
	 * @return
	 * @return: List<AssessmentDetailVO>
	 */
	public static Page<AssessmentDetailVO> AssessmentDetail2VO(Page<AssessmentDetail> page) {
		List<AssessmentDetailVO> listVO = new ArrayList<>();
		for (AssessmentDetail assessmentDetail : page.getResult()) {
			AssessmentDetailVO vo = new AssessmentDetailVO(assessmentDetail);
			listVO.add(vo);
		}
		Page<AssessmentDetailVO> pageVO=new Page<>(page.getStart(),page.getCurrentPageSize(),page.getTotalSize(),page.getPageSize(),listVO);
		return pageVO;
	}
	
}
