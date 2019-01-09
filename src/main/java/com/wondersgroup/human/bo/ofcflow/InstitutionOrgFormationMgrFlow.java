/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: InstitutionOrgFormationMgrFlow.java
 * 工程名: human
 * 包名: com.wondersgroup.human.bo.organization
 * 描述: 事业单位编制情况维护流程表单
 * 创建人: jiang
 * 创建时间: 2018年12月5日14:33:04
 * 版本号: V1.0
 * 修改人：
 * 修改时间：
 * 修改任务号
 * 修改内容：
 */

package com.wondersgroup.human.bo.ofcflow;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.bo.organization.base.BaseUnitFormation;

/**
 * 机构信息
 * @ClassName: InstitutionOrgFormationMgrFlow
 * @Description: 事业单位编制情况维护流程表单
 * @author: jiang
 * @date: 2018年12月5日14:33:06
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Entity
@Table(name = "HUMAN_FLOW_INST_ORG_FORMATION_MGR")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InstitutionOrgFormationMgrFlow extends BaseUnitFormation<InstitutionOrgFormationMgrFlow> {
	
	/**
	 * 待提交申请信息
	 */
	public static final int STATUS_ORG_FORMATION_MGR_FLOW_STATE = 0;
	
	/**
	 * 待上级审核
	 */
	public static final int STATUS_ORG_FORMATION_MGR_FLOW_TRIAL1 = 1;
	
	/**
	 * 审核通过
	 */
	public static final int STATUS_ORG_FORMATION_MGR_FLOW_TRIAL2 = 2;
	
	/**
	 * 权限代码map
	 * key：权限代码，value：业务状态
	 */
	public final static Map<String, Integer> power = new HashMap<>();
	
	static {
		power.put("INST_FORMATION_ADJUST_WAIT_SUBMIT", STATUS_ORG_FORMATION_MGR_FLOW_STATE);
		power.put("INST_FORMATION_ADJUST_WAIT_UP_APPROVAL", STATUS_ORG_FORMATION_MGR_FLOW_TRIAL1);
	}
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4603193733428802138L;
	
	/**
	 * *
	 * 关联单位
	 **/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGINFO_ID")
	private OrgInfo orgInfo;
	
	/**
	 * * 操作类型
	 **/
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "OPTION_TYPE")
	private CodeInfo optionType;
	
	/**
	 * @fieldName: flowRecord
	 * @fieldType: String
	 * @Description: 当前流程节点
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "FLOWRECORD_ID")
	private FlowRecord flowRecord;
	
	/**
	 * @fieldName: status
	 * @fieldType: java.lang.Integer
	 * @Description: 流程状态
	 */
	@Column(name = "STATUS")
	private Integer status;
	
	/**
	 * @fieldName: planPath
	 * @fieldType: java.lang.String
	 * @Description: 三定方案 文件路径。
	 */
	@Column(name = "PlAN_PATH", length = 2000)
	private String planPath;
	
	
	/**
	 * @fieldName: 定编 管理人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员一级人数总数。
	 */
	@Column(name = "MGR_I_A", length = 2)
	private Integer approveMgrLevelINum = 0;
	
	/**
	 * @fieldName: 实有 管理人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员一级人数总数。
	 */
	@Column(name = "MGR_I_B", length = 2)
	private Integer realMgrLevelINum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员一级人数
	 */
	@Column(name = "NOT_INTO_MGR_I", length = 2)
	private Integer notIntoMgrLevelINum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员一级人数
	 */
	@Column(name = "NOT_OUT_MGR_I", length = 2)
	private Integer notOutMgrLevelINum = 0;
	
	/**
	 * @fieldName: 定编 管理人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员二级人数总数。
	 */
	@Column(name = "MGR_II_A", length = 2)
	private Integer approveMgrLevelIINum = 0;
	
	/**
	 * @fieldName: 实有 管理人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员二级人数总数。
	 */
	@Column(name = "MGR_II_B", length = 2)
	private Integer realMgrLevelIINum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员二级人数
	 */
	@Column(name = "NOT_INTO_MGR_II", length = 2)
	private Integer notIntoMgrLevelIINum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员二级人数
	 */
	@Column(name = "NOT_OUT_MGR_II", length = 2)
	private Integer notOutMgrLevelIINum = 0;
	
	/**
	 * @fieldName: 定编 管理人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员三级人数总数。
	 */
	@Column(name = "MGR_III_A", length = 2)
	private Integer approveMgrLevelIIINum = 0;
	
	/**
	 * @fieldName: 实有 管理人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员三级人数总数。
	 */
	@Column(name = "MGR_III_B", length = 2)
	private Integer realMgrLevelIIINum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员三级人数
	 */
	@Column(name = "NOT_INTO_MGR_III", length = 2)
	private Integer notIntoMgrLevelIIINum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员三级人数
	 */
	@Column(name = "NOT_OUT_MGR_III", length = 2)
	private Integer notOutMgrLevelIIINum = 0;
	
	/**
	 * @fieldName: 定编 管理人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员四级人数总数。
	 */
	@Column(name = "MGR_IV_A", length = 2)
	private Integer approveMgrLevelIVNum = 0;
	
	/**
	 * @fieldName: 实有 管理人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员四级人数总数。
	 */
	@Column(name = "MGR_IV_B", length = 2)
	private Integer realMgrLevelIVNum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员四级人数
	 */
	@Column(name = "NOT_INTO_MGR_IV", length = 2)
	private Integer notIntoMgrLevelIVNum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员四级人数
	 */
	@Column(name = "NOT_OUT_MGR_IV", length = 2)
	private Integer notOutMgrLevelIVNum = 0;
	
	/**
	 * @fieldName: 定编 管理人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员五级人数总数。
	 */
	@Column(name = "MGR_V_A", length = 2)
	private Integer approveMgrLevelVNum = 0;
	
	/**
	 * @fieldName: 实有 管理人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员五级人数总数。
	 */
	@Column(name = "MGR_V_B", length = 2)
	private Integer realMgrLevelVNum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员五级人数
	 */
	@Column(name = "NOT_INTO_MGR_V", length = 2)
	private Integer notIntoMgrLevelVNum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员五级人数
	 */
	@Column(name = "NOT_OUT_MGR_V", length = 2)
	private Integer notOutMgrLevelVNum = 0;
	
	/**
	 * @fieldName: 定编 管理人员六级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员六级人数总数。
	 */
	@Column(name = "MGR_VI_A", length = 2)
	private Integer approveMgrLevelVINum = 0;
	
	/**
	 * @fieldName: 实有 管理人员六级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员六级人数总数。
	 */
	@Column(name = "MGR_VI_B", length = 2)
	private Integer realMgrLevelVINum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员六级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员六级人数
	 */
	@Column(name = "NOT_INTO_MGR_VI", length = 2)
	private Integer notIntoMgrLevelVINum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员六级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员六级人数
	 */
	@Column(name = "NOT_OUT_MGR_VI", length = 2)
	private Integer notOutMgrLevelVINum = 0;
	
	/**
	 * @fieldName: 定编 管理人员七级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员七级人数总数。
	 */
	@Column(name = "MGR_VII_A", length = 2)
	private Integer approveMgrLevelVIINum = 0;
	
	/**
	 * @fieldName: 实有 管理人员七级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员七级人数总数。
	 */
	@Column(name = "MGR_VII_B", length = 2)
	private Integer realMgrLevelVIINum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员七级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员七级人数
	 */
	@Column(name = "NOT_INTO_MGR_VII", length = 2)
	private Integer notIntoMgrLevelVIINum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员七级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员七级人数
	 */
	@Column(name = "NOT_OUT_MGR_VII", length = 2)
	private Integer notOutMgrLevelVIINum = 0;
	
	/**
	 * @fieldName: 定编 管理人员八级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员八级人数总数。
	 */
	@Column(name = "MGR_VIII_A", length = 2)
	private Integer approveMgrLevelVIIINum = 0;
	
	/**
	 * @fieldName: 实有 管理人员八级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员八级人数总数。
	 */
	@Column(name = "MGR_VIII_B", length = 2)
	private Integer realMgrLevelVIIINum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员八级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员八级人数
	 */
	@Column(name = "NOT_INTO_MGR_VIII", length = 2)
	private Integer notIntoMgrLevelVIIINum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员八级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员八级人数
	 */
	@Column(name = "NOT_OUT_MGR_VIII", length = 2)
	private Integer notOutMgrLevelVIIINum = 0;
	
	/**
	 * @fieldName: 定编 管理人员九级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员九级人数总数。
	 */
	@Column(name = "MGR_IX_A", length = 2)
	private Integer approveMgrLevelIXNum = 0;
	
	/**
	 * @fieldName: 实有 管理人员九级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员九级人数总数。
	 */
	@Column(name = "MGR_IX_B", length = 2)
	private Integer realMgrLevelIXNum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员九级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员九级人数
	 */
	@Column(name = "NOT_INTO_MGR_IX", length = 2)
	private Integer notIntoMgrLevelIXNum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员九级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员九级人数
	 */
	@Column(name = "NOT_OUT_MGR_IX", length = 2)
	private Integer notOutMgrLevelIXNum = 0;
	
	/**
	 * @fieldName: 定编 管理人员十级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 管理人员十级人数总数。
	 */
	@Column(name = "MGR_X_A", length = 2)
	private Integer approveMgrLevelXNum = 0;
	
	/**
	 * @fieldName: 实有 管理人员十级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 管理人员十级人数总数。
	 */
	@Column(name = "MGR_X_B", length = 2)
	private Integer realMgrLevelXNum = 0;
	
	/**
	 * @fieldName: 未调入 管理人员十级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 管理人员十级人数
	 */
	@Column(name = "NOT_INTO_MGR_X", length = 2)
	private Integer notIntoMgrLevelXNum = 0;
	
	/**
	 * @fieldName: 未调出 管理人员十级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 管理人员十级人数
	 */
	@Column(name = "NOT_OUT_MGR_X", length = 2)
	private Integer notOutMgrLevelXNum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员一级人数总数。
	 */
	@Column(name = "TECH_I_A", length = 2)
	private Integer approveTechLevelINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员一级人数总数。
	 */
	@Column(name = "TECH_I_B", length = 2)
	private Integer realTechLevelINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员一级人数
	 */
	@Column(name = "NOT_INTO_TECH_I", length = 2)
	private Integer notIntoTechLevelINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员一级人数
	 */
	@Column(name = "NOT_OUT_TECH_I", length = 2)
	private Integer notOutTechLevelINum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员二级人数总数。
	 */
	@Column(name = "TECH_II_A", length = 2)
	private Integer approveTechLevelIINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员二级人数总数。
	 */
	@Column(name = "TECH_II_B", length = 2)
	private Integer realTechLevelIINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员二级人数
	 */
	@Column(name = "NOT_INTO_TECH_II", length = 2)
	private Integer notIntoTechLevelIINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员二级人数
	 */
	@Column(name = "NOT_OUT_TECH_II", length = 2)
	private Integer notOutTechLevelIINum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员三级人数总数。
	 */
	@Column(name = "TECH_III_A", length = 2)
	private Integer approveTechLevelIIINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员三级人数总数。
	 */
	@Column(name = "TECH_III_B", length = 2)
	private Integer realTechLevelIIINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员三级人数
	 */
	@Column(name = "NOT_INTO_TECH_III", length = 2)
	private Integer notIntoTechLevelIIINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员三级人数
	 */
	@Column(name = "NOT_OUT_TECH_III", length = 2)
	private Integer notOutTechLevelIIINum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员四级人数总数。
	 */
	@Column(name = "TECH_IV_A", length = 2)
	private Integer approveTechLevelIVNum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员四级人数总数。
	 */
	@Column(name = "TECH_IV_B", length = 2)
	private Integer realTechLevelIVNum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员四级人数
	 */
	@Column(name = "NOT_INTO_TECH_IV", length = 2)
	private Integer notIntoTechLevelIVNum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员四级人数
	 */
	@Column(name = "NOT_OUT_TECH_IV", length = 2)
	private Integer notOutTechLevelIVNum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员五级人数总数。
	 */
	@Column(name = "TECH_V_A", length = 2)
	private Integer approveTechLevelVNum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员五级人数总数。
	 */
	@Column(name = "TECH_V_B", length = 2)
	private Integer realTechLevelVNum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员五级人数
	 */
	@Column(name = "NOT_INTO_TECH_V", length = 2)
	private Integer notIntoTechLevelVNum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员五级人数
	 */
	@Column(name = "NOT_OUT_TECH_V", length = 2)
	private Integer notOutTechLevelVNum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员六级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员六级人数总数。
	 */
	@Column(name = "TECH_VI_A", length = 2)
	private Integer approveTechLevelVINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员六级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员六级人数总数。
	 */
	@Column(name = "TECH_VI_B", length = 2)
	private Integer realTechLevelVINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员六级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员六级人数
	 */
	@Column(name = "NOT_INTO_TECH_VI", length = 2)
	private Integer notIntoTechLevelVINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员六级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员六级人数
	 */
	@Column(name = "NOT_OUT_TECH_VI", length = 2)
	private Integer notOutTechLevelVINum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员七级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员七级人数总数。
	 */
	@Column(name = "TECH_VII_A", length = 2)
	private Integer approveTechLevelVIINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员七级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员七级人数总数。
	 */
	@Column(name = "TECH_VII_B", length = 2)
	private Integer realTechLevelVIINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员七级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员七级人数
	 */
	@Column(name = "NOT_INTO_TECH_VII", length = 2)
	private Integer notIntoTechLevelVIINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员七级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员七级人数
	 */
	@Column(name = "NOT_OUT_TECH_VII", length = 2)
	private Integer notOutTechLevelVIINum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员八级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员八级人数总数。
	 */
	@Column(name = "TECH_VIII_A", length = 2)
	private Integer approveTechLevelVIIINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员八级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员八级人数总数。
	 */
	@Column(name = "TECH_VIII_B", length = 2)
	private Integer realTechLevelVIIINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员八级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员八级人数
	 */
	@Column(name = "NOT_INTO_TECH_VIII", length = 2)
	private Integer notIntoTechLevelVIIINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员八级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员八级人数
	 */
	@Column(name = "NOT_OUT_TECH_VIII", length = 2)
	private Integer notOutTechLevelVIIINum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员九级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员九级人数总数。
	 */
	@Column(name = "TECH_IX_A", length = 2)
	private Integer approveTechLevelIXNum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员九级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员九级人数总数。
	 */
	@Column(name = "TECH_IX_B", length = 2)
	private Integer realTechLevelIXNum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员九级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员九级人数
	 */
	@Column(name = "NOT_INTO_TECH_IX", length = 2)
	private Integer notIntoTechLevelIXNum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员九级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员九级人数
	 */
	@Column(name = "NOT_OUT_TECH_IX", length = 2)
	private Integer notOutTechLevelIXNum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员十级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员十级人数总数。
	 */
	@Column(name = "TECH_X_A", length = 2)
	private Integer approveTechLevelXNum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员十级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员十级人数总数。
	 */
	@Column(name = "TECH_X_B", length = 2)
	private Integer realTechLevelXNum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员十级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员十级人数
	 */
	@Column(name = "NOT_INTO_TECH_X", length = 2)
	private Integer notIntoTechLevelXNum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员十级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员十级人数
	 */
	@Column(name = "NOT_OUT_TECH_X", length = 2)
	private Integer notOutTechLevelXNum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员十一级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员十一级人数总数。
	 */
	@Column(name = "TECH_XI_A", length = 2)
	private Integer approveTechLevelXINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员十一级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员十一级人数总数。
	 */
	@Column(name = "TECH_XI_B", length = 2)
	private Integer realTechLevelXINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员十一级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员十一级人数
	 */
	@Column(name = "NOT_INTO_TECH_XI", length = 2)
	private Integer notIntoTechLevelXINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员十一级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员十一级人数
	 */
	@Column(name = "NOT_OUT_TECH_XI", length = 2)
	private Integer notOutTechLevelXINum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员十二级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员十二级人数总数。
	 */
	@Column(name = "TECH_XII_A", length = 2)
	private Integer approveTechLevelXIINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员十二级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员十二级人数总数。
	 */
	@Column(name = "TECH_XII_B", length = 2)
	private Integer realTechLevelXIINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员十二级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员十二级人数
	 */
	@Column(name = "NOT_INTO_TECH_XII", length = 2)
	private Integer notIntoTechLevelXIINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员十二级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员十二级人数
	 */
	@Column(name = "NOT_OUT_TECH_XII", length = 2)
	private Integer notOutTechLevelXIINum = 0;
	
	/**
	 * @fieldName: 定编 专业技术人员十三级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 专业技术人员十三级人数总数。
	 */
	@Column(name = "TECH_XIII_A", length = 2)
	private Integer approveTechLevelXIIINum = 0;
	
	/**
	 * @fieldName: 实有 专业技术人员十三级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 专业技术人员十三级人数总数。
	 */
	@Column(name = "TECH_XIII_B", length = 2)
	private Integer realTechLevelXIIINum = 0;
	
	/**
	 * @fieldName: 未调入 专业技术人员十三级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 专业技术人员十三级人数
	 */
	@Column(name = "NOT_INTO_TECH_XIII", length = 2)
	private Integer notIntoTechLevelXIIINum = 0;
	
	/**
	 * @fieldName: 未调出 专业技术人员十三级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 专业技术人员十三级人数
	 */
	@Column(name = "NOT_OUT_TECH_XIII", length = 2)
	private Integer notOutTechLevelXIIINum = 0;
	
	/**
	 * @fieldName: 定编 工勤人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 工勤人员一级人数总数。
	 */
	@Column(name = "WORK_I_A", length = 2)
	private Integer approveWorkLevelINum = 0;
	
	/**
	 * @fieldName: 实有 工勤人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 工勤人员一级人数总数。
	 */
	@Column(name = "WORK_I_B", length = 2)
	private Integer realWorkLevelINum = 0;
	
	/**
	 * @fieldName: 未调入 工勤人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 工勤人员一级人数
	 */
	@Column(name = "NOT_INTO_WORK_I", length = 2)
	private Integer notIntoWorkLevelINum = 0;
	
	/**
	 * @fieldName: 未调出 工勤人员一级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 工勤人员一级人数
	 */
	@Column(name = "NOT_OUT_WORK_I", length = 2)
	private Integer notOutWorkLevelINum = 0;
	
	/**
	 * @fieldName: 定编 工勤人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 工勤人员二级人数总数。
	 */
	@Column(name = "WORK_II_A", length = 2)
	private Integer approveWorkLevelIINum = 0;
	
	/**
	 * @fieldName: 实有 工勤人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 工勤人员二级人数总数。
	 */
	@Column(name = "WORK_II_B", length = 2)
	private Integer realWorkLevelIINum = 0;
	
	/**
	 * @fieldName: 未调入 工勤人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 工勤人员二级人数
	 */
	@Column(name = "NOT_INTO_WORK_II", length = 2)
	private Integer notIntoWorkLevelIINum = 0;
	
	/**
	 * @fieldName: 未调出 工勤人员二级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 工勤人员二级人数
	 */
	@Column(name = "NOT_OUT_WORK_II", length = 2)
	private Integer notOutWorkLevelIINum = 0;
	
	/**
	 * @fieldName: 定编 工勤人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 工勤人员三级人数总数。
	 */
	@Column(name = "WORK_III_A", length = 2)
	private Integer approveWorkLevelIIINum = 0;
	
	/**
	 * @fieldName: 实有 工勤人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 工勤人员三级人数总数。
	 */
	@Column(name = "WORK_III_B", length = 2)
	private Integer realWorkLevelIIINum = 0;
	
	/**
	 * @fieldName: 未调入 工勤人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 工勤人员三级人数
	 */
	@Column(name = "NOT_INTO_WORK_III", length = 2)
	private Integer notIntoWorkLevelIIINum = 0;
	
	/**
	 * @fieldName: 未调出 工勤人员三级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 工勤人员三级人数
	 */
	@Column(name = "NOT_OUT_WORK_III", length = 2)
	private Integer notOutWorkLevelIIINum = 0;
	
	/**
	 * @fieldName: 定编 工勤人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 工勤人员四级人数总数。
	 */
	@Column(name = "WORK_IV_A", length = 2)
	private Integer approveWorkLevelIVNum = 0;
	
	/**
	 * @fieldName: 实有 工勤人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 工勤人员四级人数总数。
	 */
	@Column(name = "WORK_IV_B", length = 2)
	private Integer realWorkLevelIVNum = 0;
	
	/**
	 * @fieldName: 未调入 工勤人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 工勤人员四级人数
	 */
	@Column(name = "NOT_INTO_WORK_IV", length = 2)
	private Integer notIntoWorkLevelIVNum = 0;
	
	/**
	 * @fieldName: 未调出 工勤人员四级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 工勤人员四级人数
	 */
	@Column(name = "NOT_OUT_WORK_IV", length = 2)
	private Integer notOutWorkLevelIVNum = 0;
	
	/**
	 * @fieldName: 定编 工勤人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 定编 工勤人员五级人数总数。
	 */
	@Column(name = "WORK_V_A", length = 2)
	private Integer approveWorkLevelVNum = 0;
	
	/**
	 * @fieldName: 实有 工勤人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description: 实有 工勤人员五级人数总数。
	 */
	@Column(name = "WORK_V_B", length = 2)
	private Integer realWorkLevelVNum = 0;
	
	/**
	 * @fieldName: 未调入 工勤人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调入 工勤人员五级人数
	 */
	@Column(name = "NOT_INTO_WORK_V", length = 2)
	private Integer notIntoWorkLevelVNum = 0;
	
	/**
	 * @fieldName: 未调出 工勤人员五级人数
	 * @fieldType: java.lang.Integer
	 * @Description:未调出 工勤人员五级人数
	 */
	@Column(name = "NOT_OUT_WORK_V", length = 2)
	private Integer notOutWorkLevelVNum = 0;

	
	public OrgInfo getOrgInfo() {
		
		return orgInfo;
	}

	
	public void setOrgInfo(OrgInfo orgInfo) {
		
		this.orgInfo = orgInfo;
	}

	
	public CodeInfo getOptionType() {
		
		return optionType;
	}

	
	public void setOptionType(CodeInfo optionType) {
		
		this.optionType = optionType;
	}

	
	public FlowRecord getFlowRecord() {
		
		return flowRecord;
	}

	
	public void setFlowRecord(FlowRecord flowRecord) {
		
		this.flowRecord = flowRecord;
	}

	
	public Integer getStatus() {
		
		return status;
	}

	
	public void setStatus(Integer status) {
		
		this.status = status;
	}

	
	public String getPlanPath() {
		
		return planPath;
	}

	
	public void setPlanPath(String planPath) {
		
		this.planPath = planPath;
	}

	
	public Integer getApproveMgrLevelINum() {
		
		return approveMgrLevelINum;
	}

	
	public void setApproveMgrLevelINum(Integer approveMgrLevelINum) {
		
		this.approveMgrLevelINum = approveMgrLevelINum;
	}

	
	public Integer getRealMgrLevelINum() {
		
		return realMgrLevelINum;
	}

	
	public void setRealMgrLevelINum(Integer realMgrLevelINum) {
		
		this.realMgrLevelINum = realMgrLevelINum;
	}

	
	public Integer getNotIntoMgrLevelINum() {
		
		return notIntoMgrLevelINum;
	}

	
	public void setNotIntoMgrLevelINum(Integer notIntoMgrLevelINum) {
		
		this.notIntoMgrLevelINum = notIntoMgrLevelINum;
	}

	
	public Integer getNotOutMgrLevelINum() {
		
		return notOutMgrLevelINum;
	}

	
	public void setNotOutMgrLevelINum(Integer notOutMgrLevelINum) {
		
		this.notOutMgrLevelINum = notOutMgrLevelINum;
	}

	
	public Integer getApproveMgrLevelIINum() {
		
		return approveMgrLevelIINum;
	}

	
	public void setApproveMgrLevelIINum(Integer approveMgrLevelIINum) {
		
		this.approveMgrLevelIINum = approveMgrLevelIINum;
	}

	
	public Integer getRealMgrLevelIINum() {
		
		return realMgrLevelIINum;
	}

	
	public void setRealMgrLevelIINum(Integer realMgrLevelIINum) {
		
		this.realMgrLevelIINum = realMgrLevelIINum;
	}

	
	public Integer getNotIntoMgrLevelIINum() {
		
		return notIntoMgrLevelIINum;
	}

	
	public void setNotIntoMgrLevelIINum(Integer notIntoMgrLevelIINum) {
		
		this.notIntoMgrLevelIINum = notIntoMgrLevelIINum;
	}

	
	public Integer getNotOutMgrLevelIINum() {
		
		return notOutMgrLevelIINum;
	}

	
	public void setNotOutMgrLevelIINum(Integer notOutMgrLevelIINum) {
		
		this.notOutMgrLevelIINum = notOutMgrLevelIINum;
	}

	
	public Integer getApproveMgrLevelIIINum() {
		
		return approveMgrLevelIIINum;
	}

	
	public void setApproveMgrLevelIIINum(Integer approveMgrLevelIIINum) {
		
		this.approveMgrLevelIIINum = approveMgrLevelIIINum;
	}

	
	public Integer getRealMgrLevelIIINum() {
		
		return realMgrLevelIIINum;
	}

	
	public void setRealMgrLevelIIINum(Integer realMgrLevelIIINum) {
		
		this.realMgrLevelIIINum = realMgrLevelIIINum;
	}

	
	public Integer getNotIntoMgrLevelIIINum() {
		
		return notIntoMgrLevelIIINum;
	}

	
	public void setNotIntoMgrLevelIIINum(Integer notIntoMgrLevelIIINum) {
		
		this.notIntoMgrLevelIIINum = notIntoMgrLevelIIINum;
	}

	
	public Integer getNotOutMgrLevelIIINum() {
		
		return notOutMgrLevelIIINum;
	}

	
	public void setNotOutMgrLevelIIINum(Integer notOutMgrLevelIIINum) {
		
		this.notOutMgrLevelIIINum = notOutMgrLevelIIINum;
	}

	
	public Integer getApproveMgrLevelIVNum() {
		
		return approveMgrLevelIVNum;
	}

	
	public void setApproveMgrLevelIVNum(Integer approveMgrLevelIVNum) {
		
		this.approveMgrLevelIVNum = approveMgrLevelIVNum;
	}

	
	public Integer getRealMgrLevelIVNum() {
		
		return realMgrLevelIVNum;
	}

	
	public void setRealMgrLevelIVNum(Integer realMgrLevelIVNum) {
		
		this.realMgrLevelIVNum = realMgrLevelIVNum;
	}

	
	public Integer getNotIntoMgrLevelIVNum() {
		
		return notIntoMgrLevelIVNum;
	}

	
	public void setNotIntoMgrLevelIVNum(Integer notIntoMgrLevelIVNum) {
		
		this.notIntoMgrLevelIVNum = notIntoMgrLevelIVNum;
	}

	
	public Integer getNotOutMgrLevelIVNum() {
		
		return notOutMgrLevelIVNum;
	}

	
	public void setNotOutMgrLevelIVNum(Integer notOutMgrLevelIVNum) {
		
		this.notOutMgrLevelIVNum = notOutMgrLevelIVNum;
	}

	
	public Integer getApproveMgrLevelVNum() {
		
		return approveMgrLevelVNum;
	}

	
	public void setApproveMgrLevelVNum(Integer approveMgrLevelVNum) {
		
		this.approveMgrLevelVNum = approveMgrLevelVNum;
	}

	
	public Integer getRealMgrLevelVNum() {
		
		return realMgrLevelVNum;
	}

	
	public void setRealMgrLevelVNum(Integer realMgrLevelVNum) {
		
		this.realMgrLevelVNum = realMgrLevelVNum;
	}

	
	public Integer getNotIntoMgrLevelVNum() {
		
		return notIntoMgrLevelVNum;
	}

	
	public void setNotIntoMgrLevelVNum(Integer notIntoMgrLevelVNum) {
		
		this.notIntoMgrLevelVNum = notIntoMgrLevelVNum;
	}

	
	public Integer getNotOutMgrLevelVNum() {
		
		return notOutMgrLevelVNum;
	}

	
	public void setNotOutMgrLevelVNum(Integer notOutMgrLevelVNum) {
		
		this.notOutMgrLevelVNum = notOutMgrLevelVNum;
	}

	
	public Integer getApproveMgrLevelVINum() {
		
		return approveMgrLevelVINum;
	}

	
	public void setApproveMgrLevelVINum(Integer approveMgrLevelVINum) {
		
		this.approveMgrLevelVINum = approveMgrLevelVINum;
	}

	
	public Integer getRealMgrLevelVINum() {
		
		return realMgrLevelVINum;
	}

	
	public void setRealMgrLevelVINum(Integer realMgrLevelVINum) {
		
		this.realMgrLevelVINum = realMgrLevelVINum;
	}

	
	public Integer getNotIntoMgrLevelVINum() {
		
		return notIntoMgrLevelVINum;
	}

	
	public void setNotIntoMgrLevelVINum(Integer notIntoMgrLevelVINum) {
		
		this.notIntoMgrLevelVINum = notIntoMgrLevelVINum;
	}

	
	public Integer getNotOutMgrLevelVINum() {
		
		return notOutMgrLevelVINum;
	}

	
	public void setNotOutMgrLevelVINum(Integer notOutMgrLevelVINum) {
		
		this.notOutMgrLevelVINum = notOutMgrLevelVINum;
	}

	
	public Integer getApproveMgrLevelVIINum() {
		
		return approveMgrLevelVIINum;
	}

	
	public void setApproveMgrLevelVIINum(Integer approveMgrLevelVIINum) {
		
		this.approveMgrLevelVIINum = approveMgrLevelVIINum;
	}

	
	public Integer getRealMgrLevelVIINum() {
		
		return realMgrLevelVIINum;
	}

	
	public void setRealMgrLevelVIINum(Integer realMgrLevelVIINum) {
		
		this.realMgrLevelVIINum = realMgrLevelVIINum;
	}

	
	public Integer getNotIntoMgrLevelVIINum() {
		
		return notIntoMgrLevelVIINum;
	}

	
	public void setNotIntoMgrLevelVIINum(Integer notIntoMgrLevelVIINum) {
		
		this.notIntoMgrLevelVIINum = notIntoMgrLevelVIINum;
	}

	
	public Integer getNotOutMgrLevelVIINum() {
		
		return notOutMgrLevelVIINum;
	}

	
	public void setNotOutMgrLevelVIINum(Integer notOutMgrLevelVIINum) {
		
		this.notOutMgrLevelVIINum = notOutMgrLevelVIINum;
	}

	
	public Integer getApproveMgrLevelVIIINum() {
		
		return approveMgrLevelVIIINum;
	}

	
	public void setApproveMgrLevelVIIINum(Integer approveMgrLevelVIIINum) {
		
		this.approveMgrLevelVIIINum = approveMgrLevelVIIINum;
	}

	
	public Integer getRealMgrLevelVIIINum() {
		
		return realMgrLevelVIIINum;
	}

	
	public void setRealMgrLevelVIIINum(Integer realMgrLevelVIIINum) {
		
		this.realMgrLevelVIIINum = realMgrLevelVIIINum;
	}

	
	public Integer getNotIntoMgrLevelVIIINum() {
		
		return notIntoMgrLevelVIIINum;
	}

	
	public void setNotIntoMgrLevelVIIINum(Integer notIntoMgrLevelVIIINum) {
		
		this.notIntoMgrLevelVIIINum = notIntoMgrLevelVIIINum;
	}

	
	public Integer getNotOutMgrLevelVIIINum() {
		
		return notOutMgrLevelVIIINum;
	}

	
	public void setNotOutMgrLevelVIIINum(Integer notOutMgrLevelVIIINum) {
		
		this.notOutMgrLevelVIIINum = notOutMgrLevelVIIINum;
	}

	
	public Integer getApproveMgrLevelIXNum() {
		
		return approveMgrLevelIXNum;
	}

	
	public void setApproveMgrLevelIXNum(Integer approveMgrLevelIXNum) {
		
		this.approveMgrLevelIXNum = approveMgrLevelIXNum;
	}

	
	public Integer getRealMgrLevelIXNum() {
		
		return realMgrLevelIXNum;
	}

	
	public void setRealMgrLevelIXNum(Integer realMgrLevelIXNum) {
		
		this.realMgrLevelIXNum = realMgrLevelIXNum;
	}

	
	public Integer getNotIntoMgrLevelIXNum() {
		
		return notIntoMgrLevelIXNum;
	}

	
	public void setNotIntoMgrLevelIXNum(Integer notIntoMgrLevelIXNum) {
		
		this.notIntoMgrLevelIXNum = notIntoMgrLevelIXNum;
	}

	
	public Integer getNotOutMgrLevelIXNum() {
		
		return notOutMgrLevelIXNum;
	}

	
	public void setNotOutMgrLevelIXNum(Integer notOutMgrLevelIXNum) {
		
		this.notOutMgrLevelIXNum = notOutMgrLevelIXNum;
	}

	
	public Integer getApproveMgrLevelXNum() {
		
		return approveMgrLevelXNum;
	}

	
	public void setApproveMgrLevelXNum(Integer approveMgrLevelXNum) {
		
		this.approveMgrLevelXNum = approveMgrLevelXNum;
	}

	
	public Integer getRealMgrLevelXNum() {
		
		return realMgrLevelXNum;
	}

	
	public void setRealMgrLevelXNum(Integer realMgrLevelXNum) {
		
		this.realMgrLevelXNum = realMgrLevelXNum;
	}

	
	public Integer getNotIntoMgrLevelXNum() {
		
		return notIntoMgrLevelXNum;
	}

	
	public void setNotIntoMgrLevelXNum(Integer notIntoMgrLevelXNum) {
		
		this.notIntoMgrLevelXNum = notIntoMgrLevelXNum;
	}

	
	public Integer getNotOutMgrLevelXNum() {
		
		return notOutMgrLevelXNum;
	}

	
	public void setNotOutMgrLevelXNum(Integer notOutMgrLevelXNum) {
		
		this.notOutMgrLevelXNum = notOutMgrLevelXNum;
	}

	
	public Integer getApproveTechLevelINum() {
		
		return approveTechLevelINum;
	}

	
	public void setApproveTechLevelINum(Integer approveTechLevelINum) {
		
		this.approveTechLevelINum = approveTechLevelINum;
	}

	
	public Integer getRealTechLevelINum() {
		
		return realTechLevelINum;
	}

	
	public void setRealTechLevelINum(Integer realTechLevelINum) {
		
		this.realTechLevelINum = realTechLevelINum;
	}

	
	public Integer getNotIntoTechLevelINum() {
		
		return notIntoTechLevelINum;
	}

	
	public void setNotIntoTechLevelINum(Integer notIntoTechLevelINum) {
		
		this.notIntoTechLevelINum = notIntoTechLevelINum;
	}

	
	public Integer getNotOutTechLevelINum() {
		
		return notOutTechLevelINum;
	}

	
	public void setNotOutTechLevelINum(Integer notOutTechLevelINum) {
		
		this.notOutTechLevelINum = notOutTechLevelINum;
	}

	
	public Integer getApproveTechLevelIINum() {
		
		return approveTechLevelIINum;
	}

	
	public void setApproveTechLevelIINum(Integer approveTechLevelIINum) {
		
		this.approveTechLevelIINum = approveTechLevelIINum;
	}

	
	public Integer getRealTechLevelIINum() {
		
		return realTechLevelIINum;
	}

	
	public void setRealTechLevelIINum(Integer realTechLevelIINum) {
		
		this.realTechLevelIINum = realTechLevelIINum;
	}

	
	public Integer getNotIntoTechLevelIINum() {
		
		return notIntoTechLevelIINum;
	}

	
	public void setNotIntoTechLevelIINum(Integer notIntoTechLevelIINum) {
		
		this.notIntoTechLevelIINum = notIntoTechLevelIINum;
	}

	
	public Integer getNotOutTechLevelIINum() {
		
		return notOutTechLevelIINum;
	}

	
	public void setNotOutTechLevelIINum(Integer notOutTechLevelIINum) {
		
		this.notOutTechLevelIINum = notOutTechLevelIINum;
	}

	
	public Integer getApproveTechLevelIIINum() {
		
		return approveTechLevelIIINum;
	}

	
	public void setApproveTechLevelIIINum(Integer approveTechLevelIIINum) {
		
		this.approveTechLevelIIINum = approveTechLevelIIINum;
	}

	
	public Integer getRealTechLevelIIINum() {
		
		return realTechLevelIIINum;
	}

	
	public void setRealTechLevelIIINum(Integer realTechLevelIIINum) {
		
		this.realTechLevelIIINum = realTechLevelIIINum;
	}

	
	public Integer getNotIntoTechLevelIIINum() {
		
		return notIntoTechLevelIIINum;
	}

	
	public void setNotIntoTechLevelIIINum(Integer notIntoTechLevelIIINum) {
		
		this.notIntoTechLevelIIINum = notIntoTechLevelIIINum;
	}

	
	public Integer getNotOutTechLevelIIINum() {
		
		return notOutTechLevelIIINum;
	}

	
	public void setNotOutTechLevelIIINum(Integer notOutTechLevelIIINum) {
		
		this.notOutTechLevelIIINum = notOutTechLevelIIINum;
	}

	
	public Integer getApproveTechLevelIVNum() {
		
		return approveTechLevelIVNum;
	}

	
	public void setApproveTechLevelIVNum(Integer approveTechLevelIVNum) {
		
		this.approveTechLevelIVNum = approveTechLevelIVNum;
	}

	
	public Integer getRealTechLevelIVNum() {
		
		return realTechLevelIVNum;
	}

	
	public void setRealTechLevelIVNum(Integer realTechLevelIVNum) {
		
		this.realTechLevelIVNum = realTechLevelIVNum;
	}

	
	public Integer getNotIntoTechLevelIVNum() {
		
		return notIntoTechLevelIVNum;
	}

	
	public void setNotIntoTechLevelIVNum(Integer notIntoTechLevelIVNum) {
		
		this.notIntoTechLevelIVNum = notIntoTechLevelIVNum;
	}

	
	public Integer getNotOutTechLevelIVNum() {
		
		return notOutTechLevelIVNum;
	}

	
	public void setNotOutTechLevelIVNum(Integer notOutTechLevelIVNum) {
		
		this.notOutTechLevelIVNum = notOutTechLevelIVNum;
	}

	
	public Integer getApproveTechLevelVNum() {
		
		return approveTechLevelVNum;
	}

	
	public void setApproveTechLevelVNum(Integer approveTechLevelVNum) {
		
		this.approveTechLevelVNum = approveTechLevelVNum;
	}

	
	public Integer getRealTechLevelVNum() {
		
		return realTechLevelVNum;
	}

	
	public void setRealTechLevelVNum(Integer realTechLevelVNum) {
		
		this.realTechLevelVNum = realTechLevelVNum;
	}

	
	public Integer getNotIntoTechLevelVNum() {
		
		return notIntoTechLevelVNum;
	}

	
	public void setNotIntoTechLevelVNum(Integer notIntoTechLevelVNum) {
		
		this.notIntoTechLevelVNum = notIntoTechLevelVNum;
	}

	
	public Integer getNotOutTechLevelVNum() {
		
		return notOutTechLevelVNum;
	}

	
	public void setNotOutTechLevelVNum(Integer notOutTechLevelVNum) {
		
		this.notOutTechLevelVNum = notOutTechLevelVNum;
	}

	
	public Integer getApproveTechLevelVINum() {
		
		return approveTechLevelVINum;
	}

	
	public void setApproveTechLevelVINum(Integer approveTechLevelVINum) {
		
		this.approveTechLevelVINum = approveTechLevelVINum;
	}

	
	public Integer getRealTechLevelVINum() {
		
		return realTechLevelVINum;
	}

	
	public void setRealTechLevelVINum(Integer realTechLevelVINum) {
		
		this.realTechLevelVINum = realTechLevelVINum;
	}

	
	public Integer getNotIntoTechLevelVINum() {
		
		return notIntoTechLevelVINum;
	}

	
	public void setNotIntoTechLevelVINum(Integer notIntoTechLevelVINum) {
		
		this.notIntoTechLevelVINum = notIntoTechLevelVINum;
	}

	
	public Integer getNotOutTechLevelVINum() {
		
		return notOutTechLevelVINum;
	}

	
	public void setNotOutTechLevelVINum(Integer notOutTechLevelVINum) {
		
		this.notOutTechLevelVINum = notOutTechLevelVINum;
	}

	
	public Integer getApproveTechLevelVIINum() {
		
		return approveTechLevelVIINum;
	}

	
	public void setApproveTechLevelVIINum(Integer approveTechLevelVIINum) {
		
		this.approveTechLevelVIINum = approveTechLevelVIINum;
	}

	
	public Integer getRealTechLevelVIINum() {
		
		return realTechLevelVIINum;
	}

	
	public void setRealTechLevelVIINum(Integer realTechLevelVIINum) {
		
		this.realTechLevelVIINum = realTechLevelVIINum;
	}

	
	public Integer getNotIntoTechLevelVIINum() {
		
		return notIntoTechLevelVIINum;
	}

	
	public void setNotIntoTechLevelVIINum(Integer notIntoTechLevelVIINum) {
		
		this.notIntoTechLevelVIINum = notIntoTechLevelVIINum;
	}

	
	public Integer getNotOutTechLevelVIINum() {
		
		return notOutTechLevelVIINum;
	}

	
	public void setNotOutTechLevelVIINum(Integer notOutTechLevelVIINum) {
		
		this.notOutTechLevelVIINum = notOutTechLevelVIINum;
	}

	
	public Integer getApproveTechLevelVIIINum() {
		
		return approveTechLevelVIIINum;
	}

	
	public void setApproveTechLevelVIIINum(Integer approveTechLevelVIIINum) {
		
		this.approveTechLevelVIIINum = approveTechLevelVIIINum;
	}

	
	public Integer getRealTechLevelVIIINum() {
		
		return realTechLevelVIIINum;
	}

	
	public void setRealTechLevelVIIINum(Integer realTechLevelVIIINum) {
		
		this.realTechLevelVIIINum = realTechLevelVIIINum;
	}

	
	public Integer getNotIntoTechLevelVIIINum() {
		
		return notIntoTechLevelVIIINum;
	}

	
	public void setNotIntoTechLevelVIIINum(Integer notIntoTechLevelVIIINum) {
		
		this.notIntoTechLevelVIIINum = notIntoTechLevelVIIINum;
	}

	
	public Integer getNotOutTechLevelVIIINum() {
		
		return notOutTechLevelVIIINum;
	}

	
	public void setNotOutTechLevelVIIINum(Integer notOutTechLevelVIIINum) {
		
		this.notOutTechLevelVIIINum = notOutTechLevelVIIINum;
	}

	
	public Integer getApproveTechLevelIXNum() {
		
		return approveTechLevelIXNum;
	}

	
	public void setApproveTechLevelIXNum(Integer approveTechLevelIXNum) {
		
		this.approveTechLevelIXNum = approveTechLevelIXNum;
	}

	
	public Integer getRealTechLevelIXNum() {
		
		return realTechLevelIXNum;
	}

	
	public void setRealTechLevelIXNum(Integer realTechLevelIXNum) {
		
		this.realTechLevelIXNum = realTechLevelIXNum;
	}

	
	public Integer getNotIntoTechLevelIXNum() {
		
		return notIntoTechLevelIXNum;
	}

	
	public void setNotIntoTechLevelIXNum(Integer notIntoTechLevelIXNum) {
		
		this.notIntoTechLevelIXNum = notIntoTechLevelIXNum;
	}

	
	public Integer getNotOutTechLevelIXNum() {
		
		return notOutTechLevelIXNum;
	}

	
	public void setNotOutTechLevelIXNum(Integer notOutTechLevelIXNum) {
		
		this.notOutTechLevelIXNum = notOutTechLevelIXNum;
	}

	
	public Integer getApproveTechLevelXNum() {
		
		return approveTechLevelXNum;
	}

	
	public void setApproveTechLevelXNum(Integer approveTechLevelXNum) {
		
		this.approveTechLevelXNum = approveTechLevelXNum;
	}

	
	public Integer getRealTechLevelXNum() {
		
		return realTechLevelXNum;
	}

	
	public void setRealTechLevelXNum(Integer realTechLevelXNum) {
		
		this.realTechLevelXNum = realTechLevelXNum;
	}

	
	public Integer getNotIntoTechLevelXNum() {
		
		return notIntoTechLevelXNum;
	}

	
	public void setNotIntoTechLevelXNum(Integer notIntoTechLevelXNum) {
		
		this.notIntoTechLevelXNum = notIntoTechLevelXNum;
	}

	
	public Integer getNotOutTechLevelXNum() {
		
		return notOutTechLevelXNum;
	}

	
	public void setNotOutTechLevelXNum(Integer notOutTechLevelXNum) {
		
		this.notOutTechLevelXNum = notOutTechLevelXNum;
	}

	
	public Integer getApproveTechLevelXINum() {
		
		return approveTechLevelXINum;
	}

	
	public void setApproveTechLevelXINum(Integer approveTechLevelXINum) {
		
		this.approveTechLevelXINum = approveTechLevelXINum;
	}

	
	public Integer getRealTechLevelXINum() {
		
		return realTechLevelXINum;
	}

	
	public void setRealTechLevelXINum(Integer realTechLevelXINum) {
		
		this.realTechLevelXINum = realTechLevelXINum;
	}

	
	public Integer getNotIntoTechLevelXINum() {
		
		return notIntoTechLevelXINum;
	}

	
	public void setNotIntoTechLevelXINum(Integer notIntoTechLevelXINum) {
		
		this.notIntoTechLevelXINum = notIntoTechLevelXINum;
	}

	
	public Integer getNotOutTechLevelXINum() {
		
		return notOutTechLevelXINum;
	}

	
	public void setNotOutTechLevelXINum(Integer notOutTechLevelXINum) {
		
		this.notOutTechLevelXINum = notOutTechLevelXINum;
	}

	
	public Integer getApproveTechLevelXIINum() {
		
		return approveTechLevelXIINum;
	}

	
	public void setApproveTechLevelXIINum(Integer approveTechLevelXIINum) {
		
		this.approveTechLevelXIINum = approveTechLevelXIINum;
	}

	
	public Integer getRealTechLevelXIINum() {
		
		return realTechLevelXIINum;
	}

	
	public void setRealTechLevelXIINum(Integer realTechLevelXIINum) {
		
		this.realTechLevelXIINum = realTechLevelXIINum;
	}

	
	public Integer getNotIntoTechLevelXIINum() {
		
		return notIntoTechLevelXIINum;
	}

	
	public void setNotIntoTechLevelXIINum(Integer notIntoTechLevelXIINum) {
		
		this.notIntoTechLevelXIINum = notIntoTechLevelXIINum;
	}

	
	public Integer getNotOutTechLevelXIINum() {
		
		return notOutTechLevelXIINum;
	}

	
	public void setNotOutTechLevelXIINum(Integer notOutTechLevelXIINum) {
		
		this.notOutTechLevelXIINum = notOutTechLevelXIINum;
	}

	
	public Integer getApproveTechLevelXIIINum() {
		
		return approveTechLevelXIIINum;
	}

	
	public void setApproveTechLevelXIIINum(Integer approveTechLevelXIIINum) {
		
		this.approveTechLevelXIIINum = approveTechLevelXIIINum;
	}

	
	public Integer getRealTechLevelXIIINum() {
		
		return realTechLevelXIIINum;
	}

	
	public void setRealTechLevelXIIINum(Integer realTechLevelXIIINum) {
		
		this.realTechLevelXIIINum = realTechLevelXIIINum;
	}

	
	public Integer getNotIntoTechLevelXIIINum() {
		
		return notIntoTechLevelXIIINum;
	}

	
	public void setNotIntoTechLevelXIIINum(Integer notIntoTechLevelXIIINum) {
		
		this.notIntoTechLevelXIIINum = notIntoTechLevelXIIINum;
	}

	
	public Integer getNotOutTechLevelXIIINum() {
		
		return notOutTechLevelXIIINum;
	}

	
	public void setNotOutTechLevelXIIINum(Integer notOutTechLevelXIIINum) {
		
		this.notOutTechLevelXIIINum = notOutTechLevelXIIINum;
	}

	
	public Integer getApproveWorkLevelINum() {
		
		return approveWorkLevelINum;
	}

	
	public void setApproveWorkLevelINum(Integer approveWorkLevelINum) {
		
		this.approveWorkLevelINum = approveWorkLevelINum;
	}

	
	public Integer getRealWorkLevelINum() {
		
		return realWorkLevelINum;
	}

	
	public void setRealWorkLevelINum(Integer realWorkLevelINum) {
		
		this.realWorkLevelINum = realWorkLevelINum;
	}

	
	public Integer getNotIntoWorkLevelINum() {
		
		return notIntoWorkLevelINum;
	}

	
	public void setNotIntoWorkLevelINum(Integer notIntoWorkLevelINum) {
		
		this.notIntoWorkLevelINum = notIntoWorkLevelINum;
	}

	
	public Integer getNotOutWorkLevelINum() {
		
		return notOutWorkLevelINum;
	}

	
	public void setNotOutWorkLevelINum(Integer notOutWorkLevelINum) {
		
		this.notOutWorkLevelINum = notOutWorkLevelINum;
	}

	
	public Integer getApproveWorkLevelIINum() {
		
		return approveWorkLevelIINum;
	}

	
	public void setApproveWorkLevelIINum(Integer approveWorkLevelIINum) {
		
		this.approveWorkLevelIINum = approveWorkLevelIINum;
	}

	
	public Integer getRealWorkLevelIINum() {
		
		return realWorkLevelIINum;
	}

	
	public void setRealWorkLevelIINum(Integer realWorkLevelIINum) {
		
		this.realWorkLevelIINum = realWorkLevelIINum;
	}

	
	public Integer getNotIntoWorkLevelIINum() {
		
		return notIntoWorkLevelIINum;
	}

	
	public void setNotIntoWorkLevelIINum(Integer notIntoWorkLevelIINum) {
		
		this.notIntoWorkLevelIINum = notIntoWorkLevelIINum;
	}

	
	public Integer getNotOutWorkLevelIINum() {
		
		return notOutWorkLevelIINum;
	}

	
	public void setNotOutWorkLevelIINum(Integer notOutWorkLevelIINum) {
		
		this.notOutWorkLevelIINum = notOutWorkLevelIINum;
	}

	
	public Integer getApproveWorkLevelIIINum() {
		
		return approveWorkLevelIIINum;
	}

	
	public void setApproveWorkLevelIIINum(Integer approveWorkLevelIIINum) {
		
		this.approveWorkLevelIIINum = approveWorkLevelIIINum;
	}

	
	public Integer getRealWorkLevelIIINum() {
		
		return realWorkLevelIIINum;
	}

	
	public void setRealWorkLevelIIINum(Integer realWorkLevelIIINum) {
		
		this.realWorkLevelIIINum = realWorkLevelIIINum;
	}

	
	public Integer getNotIntoWorkLevelIIINum() {
		
		return notIntoWorkLevelIIINum;
	}

	
	public void setNotIntoWorkLevelIIINum(Integer notIntoWorkLevelIIINum) {
		
		this.notIntoWorkLevelIIINum = notIntoWorkLevelIIINum;
	}

	
	public Integer getNotOutWorkLevelIIINum() {
		
		return notOutWorkLevelIIINum;
	}

	
	public void setNotOutWorkLevelIIINum(Integer notOutWorkLevelIIINum) {
		
		this.notOutWorkLevelIIINum = notOutWorkLevelIIINum;
	}

	
	public Integer getApproveWorkLevelIVNum() {
		
		return approveWorkLevelIVNum;
	}

	
	public void setApproveWorkLevelIVNum(Integer approveWorkLevelIVNum) {
		
		this.approveWorkLevelIVNum = approveWorkLevelIVNum;
	}

	
	public Integer getRealWorkLevelIVNum() {
		
		return realWorkLevelIVNum;
	}

	
	public void setRealWorkLevelIVNum(Integer realWorkLevelIVNum) {
		
		this.realWorkLevelIVNum = realWorkLevelIVNum;
	}

	
	public Integer getNotIntoWorkLevelIVNum() {
		
		return notIntoWorkLevelIVNum;
	}

	
	public void setNotIntoWorkLevelIVNum(Integer notIntoWorkLevelIVNum) {
		
		this.notIntoWorkLevelIVNum = notIntoWorkLevelIVNum;
	}

	
	public Integer getNotOutWorkLevelIVNum() {
		
		return notOutWorkLevelIVNum;
	}

	
	public void setNotOutWorkLevelIVNum(Integer notOutWorkLevelIVNum) {
		
		this.notOutWorkLevelIVNum = notOutWorkLevelIVNum;
	}

	
	public Integer getApproveWorkLevelVNum() {
		
		return approveWorkLevelVNum;
	}

	
	public void setApproveWorkLevelVNum(Integer approveWorkLevelVNum) {
		
		this.approveWorkLevelVNum = approveWorkLevelVNum;
	}

	
	public Integer getRealWorkLevelVNum() {
		
		return realWorkLevelVNum;
	}

	
	public void setRealWorkLevelVNum(Integer realWorkLevelVNum) {
		
		this.realWorkLevelVNum = realWorkLevelVNum;
	}

	
	public Integer getNotIntoWorkLevelVNum() {
		
		return notIntoWorkLevelVNum;
	}

	
	public void setNotIntoWorkLevelVNum(Integer notIntoWorkLevelVNum) {
		
		this.notIntoWorkLevelVNum = notIntoWorkLevelVNum;
	}

	
	public Integer getNotOutWorkLevelVNum() {
		
		return notOutWorkLevelVNum;
	}

	
	public void setNotOutWorkLevelVNum(Integer notOutWorkLevelVNum) {
		
		this.notOutWorkLevelVNum = notOutWorkLevelVNum;
	}
	
	
}
