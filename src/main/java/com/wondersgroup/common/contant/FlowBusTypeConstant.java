
package com.wondersgroup.common.contant;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: FlowCodeConstant
 * @Description: 定义流程类型BUS_TYPE常量
 * @author: youyd
 * @date: 2018年9月11日 下午3:37:43
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class FlowBusTypeConstant {
	
	// 流程【职务变动-晋升】
	public final static String FLOW_JOBSHIFT_PROMOTE = "JOBSHIFT_PROMOTE";
	
	// 流程【职务变动-降级】
	public final static String FLOW_JOBSHIFT_DEMOTE = "JOBSHIFT_DEMOTE";
	
	// 流程【职务变动-免职】
	public final static String FLOW_JOBSHIFT_DEPOSE = "JOBSHIFT_DEPOSE";
	
	// 流程【职务变动-轮岗】
	public final static String FLOW_JOBSHIFT_SHIFT = "JOBSHIFT_SHIFT";
	
	//流程【年度考核奖励】
	public final static String FLOW_ASSESS_REWARD="ASSESS_REWARD";
	
	// 职务变动所有流程,拼接字符串
	public static String FLOW_JOBSHIFT_ALL;
	
	static {
		String[] jobShiftFlowArrays = {
		        FLOW_JOBSHIFT_PROMOTE, FLOW_JOBSHIFT_DEMOTE, FLOW_JOBSHIFT_DEPOSE, FLOW_JOBSHIFT_SHIFT
		};
		FLOW_JOBSHIFT_ALL = StringUtils.join(jobShiftFlowArrays, ",");
	}
	
}
