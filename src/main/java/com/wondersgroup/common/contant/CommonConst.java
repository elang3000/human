/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: CommonConst.java
 * 工程名: human
 * 包名: com.wondersgroup.common.contant
 * 描述: 系统通用常量定义
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月17日 下午4:14:56
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.common.contant;

/**
 * @ClassName: CommonConst
 * @Description: 系统通用常量定义类
 * @author: Wonders-Rain
 * @date: 2018年5月17日 下午4:14:56
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class CommonConst {
	
	// 根组织标识
	public static final String ROOT_ORGAN_CODE = "changning";
	
	// 根组织名称
	public static final String ROOT_ORGAN_NAME = "长海市长宁区";
	
	// 长宁区人社局标识
	public static final String HR_ROOT_ORGAN_CODE = "11310105688751434X";
	
	// 长宁区人社局名称
	public static final String HR_ROOT_ORGAN_NAME = "长宁区人力资源和社会保障局";
	
	// 组织类型：系统
	public static final String ORGAN_TYPE_CLASS_CODE = "class";
	
	// 组织类型：行政机关
	public static final String ORGAN_TYPE_D_CLASS_CODE = "d_class";
	
	// 组织类型：内设机构
	public static final String ORGAN_TYPE_N_CLASS_CODE = "n_class";
	
	// 组织类型：市辖区
	public static final String ORGAN_TYPE_DISTRICT_CODE = "shixiaqu";
	
	// 组织类型：县
	public static final String ORGAN_TYPE_COUNTY_CODE = "xian";
	
	// 组织类型：事业单位
	public static final String ORGAN_TYPE_UNIT_CODE = "sy";
	
	// 组织类型：街道乡镇
	public static final String ORGAN_TYPE_TOWN_CODE = "jdxzbm";
	
	// 组织类型：直辖市
	public static final String ORGAN_TYPE_CITY_CODE = "city";
	
	// 组织类型：相关节点
	public static final String ORGAN_TYPE_REL_CODE = "rel";
	
	// 组织类型：虚拟节点
	public static final String ORGAN_TYPE_XUNI_CODE = "xuni";
	
	// 组织关系：行政上下级
	public static final String ORGAN_RELATION_ADMIN_CODE = "AUL";
	
	// 组织关系：人事管理上下级
	public static final String ORGAN_RELATION_HR_CODE = "PM";
	
	// 组织关系：业务委托
	public static final String ORGAN_RELATION_BE_CODE = "BE";
	
	// 组织关系：业务指导
	public static final String ORGAN_RELATION_BG_CODE = "BG";
	
	// 组织关系：编制审核类型
	public static final String ORGAN_RELATION_AS_CODE = "AS";
	
	// 组织关系：行政管理
	public static final String ORGAN_RELATION_AM_CODE = "AM";
	
	// 排序方向：升序
	public static final String ORDER_DIR_ASC = "asc";
	
	// 排序方向：降序
	public static final String ORDER_DIR_DESC = "desc";
	
	// 是否：是
	public static final String TRUE = "T";
	
	//是否:是
	public static final Integer YES=1;
	
	//是否:否
	public static final Integer NO=0;
	
	// 是否：否
	public static final String FALSE = "F";
	
	// 系统报表代码
	public static final String SYSTEM_REPORT_CODE = "800";
	
	// 日志事件来源系统代码
	
	// 日志事件代码类型code
	public static final String CODE_TYPE_LOG = "LOG_EVENT";
	
	// 来源系统【系统平台】代码
	public static final String LOG_EVENT_SYSTEM = "LOG_EVENT_SYSTEM";
	
	// 来源系统【门户】代码
	public static final String LOG_EVENT_PORTAL = "LOG_EVENT_PORTAL";
	
	// 来源系统【交流管理】代码
	public static final String LOG_EVENT_COM = "LOG_EVENT_COM";
	
	// 日志事件代码
	
	// 【门户】事件：登录系统
	public static final String LOG_EVENT_PORTAL_01 = "LOG_EVENT_PORTAL_01";
	
	// 【门户】事件：登出系统
	public static final String LOG_EVENT_PORTAL_02 = "LOG_EVENT_PORTAL_02";
	
	// 【交流管理】事件：发布交流信息
	public static final String LOG_EVENT_COM_01 = "LOG_EVENT_COM_01";
	
	// 【交流管理】事件：申请交流
	public static final String LOG_EVENT_COM_02 = "LOG_EVENT_COM_02";
	
	// 【交流管理】事件：用人单位审批
	public static final String LOG_EVENT_COM_03 = "LOG_EVENT_COM_03";
	
	// 【交流管理】事件：原单位审批
	public static final String LOG_EVENT_COM_04 = "LOG_EVENT_COM_04";
	
	// 主页面业务类型-领导
	public static final String COMMON_BIZ_TYPE_LEADER = "1";
	
	// 主页面业务类型-市级
	public static final String COMMON_BIZ_TYPE_CITY = "2";
	
	// 主页面业务类型-区级
	public static final String COMMON_BIZ_TYPE_AREA = "3";
	
	// 主页面业务类型-基层
	public static final String COMMON_BIZ_TYPE_BASE = "4";
	
	// 事项申请
	public static final String SHIXIANGSHIQING = "500300";
	
	// 公务员管理
	public static final String GWYGL = "500";
	
	// 待办事项
	public static final String DAIBANSHIXIANG = "500100";
	
	// 已办事项
	public static final String YIBANSHIXIANG = "500200";
	
	// 部门级别
	public static final int DEPT_LEVEL_ONE = 1;
	
	public static final int DEPT_LEVEL_TWO = 2;
	
	public static final int DEPT_LEVEL_THREE = 3;
	
	// 流程实例状态
	public static final String PROCESS_INSTANCE_STATE = "PROCESS_INSTANCE_STATE";
	
	// 流程工作项状态
	public static final String PROCESS_WORKITEM_STATE = "PROCESS_WORKITEM_STATE";
	
	// 流程工作人员状态
	public static final String PROCESS_WORKPERSON_STATE = "PROCESS_WORKPERSON_STATE";
	
	//操作成功返回语句
	public static final String AJAXRESULT_SUCCESS="操作成功!";
	
	//操作失败返回语句
	public static final String AJAXRESULT_FAIL="操作失败!";
	
	
}
