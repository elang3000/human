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
	
	/**
	 * @Title: queryJudgeFormationNum
	 * @Description: 检验编制数
	 * @param organId 组织节点ID
	 * @param humanNum 人数
	 * @return
	 * @throws BusinessException
	 * @return: boolean
	 */
	
	boolean queryJudgeFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: queryJudgePostNum
	 * @Description: 检验职务人数
	 * @param organId 组织节点ID
	 * @param postLvlCode 职级编码
	 * @return
	 * @throws BusinessException
	 * @return: boolean
	 */
	JudgePostResult queryJudgePostNum(String organId, String postLvlCode) throws BusinessException;
	
	/**
	 * @Title: judgePostNum
	 * @Description: 检验职务人数
	 * @param organId 组织节点ID
	 * @param postLvlCode 职级编码
	 * @param humanNum 人数
	 * @return
	 * @throws BusinessException
	 * @return: boolean
	 */
	JudgePostResult queryJudgePostNum(String organId, String postLvlCode, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockIntoFormationNum
	 * @Description: 锁未调入编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockIntoFormationNum(String organId) throws BusinessException;
	
	/**
	 * @Title: executeLockIntoFormationNum
	 * @Description: 锁未调入编制
	 * @param organId 组织节点ID
	 * @param humanNum 人数
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockIntoFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeUnlockIntoFormationNum
	 * @Description: 解锁未调入编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockIntoFormationNum(String organId) throws BusinessException;
	
	/**
	 * @Title: unlockIntoFormationNum
	 * @Description: 解锁未调入编制
	 * @param organId 组织节点ID
	 * @param humanNum 人数
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockIntoFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeLockOutFormationNum
	 * @Description: 锁未调出编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockOutFormationNum(String organId) throws BusinessException;
	
	/**
	 * @Title: executeLockOutFormationNum
	 * @Description: 锁未调出编制
	 * @param organId 组织节点ID
	 * @param humanNum 人数
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockOutFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * @Title: executeUnlockOutFormationNum
	 * @Description: 解锁未调出编制
	 * @param organId 组织节点ID
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockOutFormationNum(String organId) throws BusinessException;
	
	/**
	 * @Title: executeUnlockOutFormationNum
	 * @Description: 解锁未调出编制
	 * @param organId 组织节点ID
	 * @param humanNum 人数
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockOutFormationNum(String organId, Integer humanNum) throws BusinessException;
	
	/**
	 * 
	 * @Title: executeLockPostIntoNum 
	 * @Description: 锁定职务调入数
	 * @param organId 组织ID
	 * @param postLvlCode 职级编码
	 * @param isLowToHigh 是否高职低配
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockPostIntoNum(String organId, String postLvlCode, Boolean isLowToHigh) throws BusinessException;
	
	/**
	 * 
	 * @Title: executeLockPostIntoNum 
	 * @Description: 锁定职务调入数
	 * @param organId 组织ID
	 * @param postLvlCode 职级编码
	 * @param humanNum 人数
	 * @param isLowToHigh 是否高职低配
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockPostIntoNum(String organId, String postLvlCode, Integer humanNum, Boolean isLowToHigh)
			throws BusinessException;
	
	/**
	 * 
	 * @Title: executeUnlockPostIntoNum 
	 * @Description: 解锁职务调入数
	 * @param organId
	 * @param postLvlCode
	 * @param isLowToHigh
	 * @throws BusinessException
	 * @return: void
	 */
	
	void executeUnlockPostIntoNum(String organId, String postLvlCode, Boolean isLowToHigh) throws BusinessException;
	
	/**
	 * 
	 * @Title: executeUnlockPostIntoNum 
	 * @Description: 解锁职务调入数
	 * @param organId
	 * @param postLvlCode
	 * @param humanNum
	 * @param isLowToHigh
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockPostIntoNum(String organId, String postLvlCode, Integer humanNum, Boolean isLowToHigh)
			throws BusinessException;
	
	/**
	 * 
	 * @Title: executeLockPostOutNum 
	 * @Description: 锁职务调出数
	 * @param organId
	 * @param postLvlCode
	 * @param isLowToHigh
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockPostOutNum(String organId, String postLvlCode, Boolean isLowToHigh) throws BusinessException;
	
	/**
	 * 
	 * @Title: executeLockPostOutNum 
	 * @Description: 锁职务调出数
	 * @param organId
	 * @param postLvlCode
	 * @param humanNum
	 * @param isLowToHigh
	 * @throws BusinessException
	 * @return: void
	 */
	void executeLockPostOutNum(String organId, String postLvlCode, Integer humanNum, Boolean isLowToHigh)
			throws BusinessException;
	
	/**
	 * 
	 * @Title: executeUnlockPostOutNum 
	 * @Description: 解锁职务调出数
	 * @param organId
	 * @param postLvlCode
	 * @param isLowToHigh
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockPostOutNum(String organId, String postLvlCode, Boolean isLowToHigh) throws BusinessException;
	
	/**
	 * 
	 * @Title: executeUnlockPostOutNum 
	 * @Description: 解锁职务调出数
	 * @param organId
	 * @param postLvlCode
	 * @param humanNum
	 * @param isLowToHigh
	 * @throws BusinessException
	 * @return: void
	 */
	void executeUnlockPostOutNum(String organId, String postLvlCode, Integer humanNum, Boolean isLowToHigh)
			throws BusinessException;
	
	/*入职编*/
	void executeIntoFormation(String organId);
	void executeIntoFormation(String organId, Integer humanNum);
	/*出职编*/
	void executeOutFormation(String organId);
	void executeOutFormation(String organId, Integer humanNum);
	
	/*入职编*/
	void executeIntoPost(String organId, String postLvlCode, Boolean isLowToHigh);
	void executeIntoPost(String organId, String postLvlCode, Boolean isLowToHigh, Integer humanNum);
	/*出职编*/
	void executeOutPost(String organId, String postLvlCode, Boolean isLowToHigh);
	void executeOutPost(String organId, String postLvlCode, Boolean isLowToHigh, Integer humanNum);
}
