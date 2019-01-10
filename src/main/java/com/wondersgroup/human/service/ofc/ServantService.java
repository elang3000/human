/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service 
 * 描述: 人员信息维护服务接口
 * 创建人: tzy   
 * 创建时间: 2018年5月21日 上午11:01:25 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月21日 上午11:01:25 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.dto.analysis.ServantParam;
import com.wondersgroup.human.dto.analysis.ServantQueryParam;
import com.wondersgroup.human.vo.ofc.PeopleVO;
import com.wondersgroup.human.vo.ofc.ServantVO;

/** 
 * @ClassName: ServantService 
 * @Description: 人员信息维护服务接口
 * @author: tzy
 * @date: 2018年5月21日 上午11:01:25
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ServantService extends GenericService<Servant>{
	
	public Page<ServantVO> getPage(DetachedCriteria detachedCriteria, Integer page, Integer limit);
	
	public Page<ServantVO> getPage(DetachedCriteria detachedCriteria, Integer page, Integer limit,String ids);
	
	/**
	 * 
	 * @Title: getAllActiveServant 
	 * @Description: 获取所有在职人员
	 * @return
	 * @return: List<Servant>
	 */
	List<Servant> getAllActiveServant();

	/** 
	 * @param m 
	 * @Title: queryServantInfoBySeniorCondation 
	 * @Description: 综合查询
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ServantVO>
	 */
	Page<ServantVO> queryServantInfoBySeniorCondation(List<ServantParam> spList, Map<String, String> m, Integer page, Integer limit);
	/**
	 * 通过姓名和身份证号查询对应的servant
	 * @param cardNo
	 * @return
	 */
	public List<Servant> getServantByCardNo(String cardNo);

	/** 
	 * @Title: queryServantInfoBySeniorCondation 
	 * @Description: TODO
	 * @param pList
	 * @param l
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ServantVO>
	 */
	public Page<ServantVO> queryServantInfoBySeniorCondation(List<String> pList, List<String> l, Integer page,
			Integer limit);
	
	/** 
	 * @param m 
	 * @Title: queryServantInfoBySeniorCondation 
	 * @Description: 综合查询
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ServantVO>
	 */
	public Page<PeopleVO> queryPeopleInfo(ServantQueryParam spList,String itype, Integer page, Integer limit);
}
