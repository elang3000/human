package com.wondersgroup.human.vo.instflow;


import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.wondersgroup.human.bo.instflow.RecordableRecord;

/**
 * @ClassName: RecordableRecordVO
 * @Description:人员离退备案VO
 * @author: wangzhanfei
 * @date: 2018年9月13日 上午10:57:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class RecordableRecordVO {

private String id;
	
	private String name;
	
	private String remark;
	
	private String planState;//状态
	//离退人当前机构
	private String recordOrgan;
	//离退日期
	private Date recordDate;
	
	public RecordableRecordVO(){
		
	}
	
	public RecordableRecordVO(RecordableRecord record) {
		this.id = record.getId();
		if (StringUtils.isNotBlank(record.getRemark())) {
			this.remark = record.getRemark();
		}
		this.recordOrgan = record.getRecordOrgan().getName();
		this.recordDate = record.getRecordDate();
		this.planState = convertState(record.getPlanState());
	}
	
	public String convertState(int state){
		
		if (state == 0) {
			return "待提交离退备案";
		} else if (state == 1) {
			return "待上级单位确定";
		}else{
			return "";
		}
	}
	
	
}
