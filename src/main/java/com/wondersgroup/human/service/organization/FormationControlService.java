/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: FormationControlService.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service
 * 描述: 编控 维护服务接口
 * 创建人: jiang
 * 创建时间: 2018年10月25日14:36:26
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年10月25日14:36:28
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.organization;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.organization.FormationControl;
import com.wondersgroup.human.vo.organization.FormationControlVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/**
 * @ClassName: FormationControlService
 * @Description: 编控 维护服务接口
 * @author: jiang
 * @date: 2018年10月25日14:36:14
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface FormationControlService extends GenericService<FormationControl> {
	
	Page<FormationControlVO> getPage(DetachedCriteria detachedCriteria, Integer pageNo, Integer limit);
	
	/**
	 * @Title: queryJudgeFormationNum
	 * @Description: 检验编制数
	 * @param organId 组织节点ID
	 * @return
	 * @throws BusinessException
	 * @return: boolean
	 */
	boolean queryJudgeFormationNum(String organId) throws BusinessException;
	
	boolean queryJudgeFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: queryJudgePostNum
	 * @Description: 检验职级人数
	 * @param organId 组织节点ID
	 * @param postLvlCode 职级编码
	 * @return
	 * @throws BusinessException
	 * @return: boolean
	 */
	JudgePostResult queryJudgePostNum(String organId, String postLvlCode, Integer isLeader) throws BusinessException;
	
	JudgePostResult queryJudgePostNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockIntoFormationNum
	 * @Description: 锁未调入人数编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockIntoFormationNum(String organId) throws BusinessException;
	
	void executeLockIntoFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeUnlockIntoFormationNum
	 * @Description: 解锁未调入人数编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockIntoFormationNum(String organId) throws BusinessException;
	
	void executeUnlockIntoFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockOutFormationNum
	 * @Description: 锁未调出人数编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockOutFormationNum(String organId) throws BusinessException;
	
	void executeLockOutFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeUnlockOutFormationNum
	 * @Description: 解锁未调出人数编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockOutFormationNum(String organId) throws BusinessException;
	
	void executeUnlockOutFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockPostIntoNum
	 * @Description: 锁定职级调入数
	 * @param organId 组织ID
	 * @param postLvlCode 职级编码
	 * @param isLeader 是否领导
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockPostIntoNum(String organId, String postLvlCode, Integer isLeader) throws BusinessException;
	
	void executeLockPostIntoNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException;
	
	/**
	 * @Title: executeUnlockPostIntoNum
	 * @Description: 解锁职级调入数
	 * @param organId
	 * @param postLvlCode
	 * @param isLeader 是否领导
	 * @throws BusinessException
	 * @return: void
	 */
	
	void executeUnlockPostIntoNum(String organId, String postLvlCode, Integer isLeader) throws BusinessException;
	
	void executeUnlockPostIntoNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException;
	
	/**
	 * @Title: executeLockPostOutNum
	 * @Description: 锁职级调出数
	 * @param organId
	 * @param postLvlCode
	 * @param isLeader 是否领导
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockPostOutNum(String organId, String postLvlCode, Integer isLeader) throws BusinessException;
	
	void executeLockPostOutNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException;
	
	/**
	 * @Title: executeUnlockPostOutNum
	 * @Description: 解锁职级调出数
	 * @param organId
	 * @param postLvlCode
	 * @param isLeader 是否领导
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockPostOutNum(String organId, String postLvlCode, Integer isLeader) throws BusinessException;
	
	void executeUnlockPostOutNum(String organId, String postLvlCode, Integer isLeader, Integer humanNum)
			throws BusinessException;
	
	/**
	 * @Title: executeIntoFormation
	 * @Description: 真实进入编制
	 * @param organId
	 * @return: void
	 */
	void executeIntoFormation(String organId);
	
	void executeIntoFormation(String organId, Integer humanNum);
	
	/**
	 * @Title: executeOutFormation
	 * @Description: 真实退出编制
	 * @param organId
	 * @return: void
	 */
	void executeOutFormation(String organId);
	
	void executeOutFormation(String organId, Integer humanNum);
	
	/**
	 * @Title: executeIntoPost
	 * @Description: 真实进入职级编制
	 * @param organId
	 * @param postLvlCode
	 * @param isLeader
	 * @return: void
	 */
	void executeIntoPost(String organId, String postLvlCode, Integer isLeader);
	
	void executeIntoPost(String organId, String postLvlCode, Integer isLeader, Integer humanNum);
	
	/**
	 * @Title: executeOutPost
	 * @Description: 真实退出职级编制
	 * @param organId
	 * @param postLvlCode
	 * @param isLowToHigh
	 * @return: void
	 */
	void executeOutPost(String organId, String postLvlCode, Integer isLeader);
	
	void executeOutPost(String organId, String postLvlCode, Integer isLeader, Integer humanNum);
	
	/**
	 * @Title: queryJudgeInstFormationNum
	 * @Description: 检验事业单位编制数
	 * @param organId 组织节点ID
	 * @return
	 * @throws BusinessException
	 * @return: boolean
	 */
	boolean queryJudgeInstFormationNum(String organId) throws BusinessException;
	
	boolean queryJudgeInstFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: queryJudgePostNum
	 * @Description: 检验事业单位职级人数
	 * @param organId 组织节点ID
	 * @param postLvlCode 职级编码
	 * @return
	 * @throws BusinessException
	 * @return: boolean
	 */
	boolean queryJudgeInstPostNum(String organId, String postLvlCode) throws BusinessException;
	
	boolean queryJudgeInstPostNum(String organId, String postLvlCode, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockIntoInstFormationNum
	 * @Description: 锁未调入事业单位人数编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockIntoInstFormationNum(String organId) throws BusinessException;
	
	void executeLockIntoInstFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeUnlockIntoFormationNum
	 * @Description: 解锁未调入事业单位人数编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockIntoInstFormationNum(String organId) throws BusinessException;
	
	void executeUnlockIntoInstFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockOutInstFormationNum
	 * @Description: 锁未调出事业单位人数编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockOutInstFormationNum(String organId) throws BusinessException;
	
	void executeLockOutInstFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeUnlockOutInstFormationNum
	 * @Description: 解锁未调出事业单位人数编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockOutInstFormationNum(String organId) throws BusinessException;
	
	void executeUnlockOutInstFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockInstPostIntoNum
	 * @Description: 锁定事业单位职级未调入数
	 * @param organId 组织ID
	 * @param postLvlCode 职级编码
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockInstPostIntoNum(String organId, String postLvlCode) throws BusinessException;
	
	void executeLockInstPostIntoNum(String organId, String postLvlCode, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeUnlockInstPostIntoNum
	 * @Description: 解锁事业单位职级未调入数
	 * @param organId
	 * @param postLvlCode
	 * @throws BusinessException
	 * @return: void
	 */
	
	void executeUnlockInstPostIntoNum(String organId, String postLvlCode) throws BusinessException;
	
	void executeUnlockInstPostIntoNum(String organId, String postLvlCode, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockInstPostOutNum
	 * @Description: 锁事业单位职级未调出数
	 * @param organId
	 * @param postLvlCode
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockInstPostOutNum(String organId, String postLvlCode) throws BusinessException;
	
	void executeLockInstPostOutNum(String organId, String postLvlCode, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeUnlockInstPostOutNum
	 * @Description: 解锁事业单位职级未调出数
	 * @param organId
	 * @param postLvlCode
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockInstPostOutNum(String organId, String postLvlCode) throws BusinessException;
	
	void executeUnlockInstPostOutNum(String organId, String postLvlCode, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeIntoInstFormation
	 * @Description: 真实进入事业单位编制数
	 * @param organId
	 * @return: void
	 */
	void executeIntoInstFormation(String organId);
	
	void executeIntoInstFormation(String organId, Integer humanNum);
	
	/**
	 * @Title: executeOutFormation
	 * @Description: 真实退出事业单位编制
	 * @param organId
	 * @return: void
	 */
	void executeOutInstFormation(String organId);
	
	void executeOutInstFormation(String organId, Integer humanNum);
	
	/**
	 * @Title: executeIntoInstPost
	 * @Description: 真实进入事业单位职级编制数
	 * @param organId
	 * @param postLvlCode
	 * @param isLowToHigh
	 * @return: void
	 */
	void executeIntoInstPost(String organId, String postLvlCode);
	
	void executeIntoInstPost(String organId, String postLvlCode, Integer humanNum);
	
	/**
	 * @Title: executeOutInstPost
	 * @Description: 真实退出事业单位职级编制数
	 * @param organId
	 * @param postLvlCode
	 * @param isLowToHigh
	 * @return: void
	 */
	void executeOutInstPost(String organId, String postLvlCode);
	
	void executeOutInstPost(String organId, String postLvlCode, Integer humanNum);
	
	/**
	 * 
	 * @Title: queryValidateFormationAndPostLvlNum 
	 * @Description: 行政编制数职数初步验证
	 * @param targetList 验证目标数组
	 * @return void
	 */
	void queryValidateFormationAndPostLvlNum(String organId,List<JudgePostResult> sourceList) throws BusinessException;
	
	/**
	 * 
	 * @Title: executeValidateAndLockFormation 
	 * @Description: 行政编制验证与占编锁定未调入
	 * @param organId
	 * @param targetList
	 * @return
	 * @throws BusinessException
	 * @return: List<JudgePostResult>
	 */
	List<JudgePostResult> executeValidateAndLockFormation(String organId,List<JudgePostResult> sourceList) throws BusinessException;
	
	
	/**
	 * 
	 * @Title: executeUnLockFormationAndPost 
	 * @Description: 行政编制 占编 未调出
	 * @param organId
	 * @param sourceList 编制数集合
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockOutFormationAndPost(String organId,List<JudgePostResult> sourceList) throws BusinessException;
	
	/**
	 * 
	 * @Title: executeUnLockFormationAndPost 
	 * @Description: 行政编制 返编 未调入
	 * @param organId
	 * @param sourceList 编制数集合
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnLockIntoFormationAndPost(String organId,List<JudgePostResult> sourceList) throws BusinessException;
	
	/**
	 * 
	 * @Title: executeUnLockOutFormationAndPost 
	 * @Description: 行政编制返编 未调出
	 * @param organId
	 * @param sourceList 编制数集合
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnLockOutFormationAndPost(String organId,List<JudgePostResult> sourceList) throws BusinessException;
}
