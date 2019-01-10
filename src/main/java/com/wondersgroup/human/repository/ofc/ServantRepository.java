/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ServantDao.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.repository 
 * 描述: 人员信息维护知识库接口
 * 创建人: tzy   
 * 创建时间: 2018年5月21日 上午11:20:27 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月21日 上午11:20:27 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.repository.ofc;

import java.util.List;
import java.util.Map;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.GenericRepository;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.dto.analysis.ServantParam;
import com.wondersgroup.human.dto.analysis.ServantQueryParam;
import com.wondersgroup.human.vo.ofc.PeopleVO;
import com.wondersgroup.human.vo.ofc.ServantVO;

/** 
 * @ClassName: ServantDao 
 * @Description: 人员信息维护知识库接口
 * @author: tzy
 * @date: 2018年5月21日 上午11:20:27
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface ServantRepository extends GenericRepository<Servant>{
	
	
	Map<String, Integer> statistServantTopEducation(List<String> organNodeIds,String isHold);

	/** 
	 * @param m 
	 * @Title: queryServantInfoBySeniorCondation 
	 * @Description: 综合查询
	 * @param filter
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<ServantVO>
	 */
	Page<ServantVO> queryServantInfoBySeniorCondation(List<ServantParam> spList, Map<String, String> m, Integer page, Integer limit);

	/**
	 * 获取性别echarts图形数据
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> getSexMapData(String orgId);
	/**
	 * 获取性别echarts图形数据
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> getNationMapData(String orgId);

	/**
	 * 获取最高学历echarts图形数据
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> getEducationMapData(String orgId);

	/**
	 * 获取最高学位echarts图形数据
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> getDegreeMapData(String orgId);
	/**
	 * 获取最高职级echarts图形数据
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> getJobLevelMapData(String orgId);

	/**
	 * 获取职务echarts图形数据
	 * @param orgId
	 * @return
	 */
	public List<Map<String, Object>> getPostMapData(String orgId);
	
	
	/**
	 * 统计学校属性
	 * @Title: statistServantSchoolNature 
	 * @Description: TODO
	 * @param isHold
	 * @param isflag
	 * @param organNodeIds
	 * @return
	 * @return: Map<String,Integer>
	 */
	public Map<String, Integer> statistServantSchoolNature(String isHold,String isflag,List<String> organNodeIds);
	
	
	/**
	 * 统计是否310开头
	 * @Title: statistServantisshanghailocal 
	 * @Description: TODO
	 * @param isHold
	 * @param organNodeIds
	 * @return
	 * @return: Map<String,Integer>
	 */
	public Map<String, Integer> statistServantisshanghailocal(String isHold,List<String> organNodeIds);
	
	
	public Page<PeopleVO> queryPeopleInfo(ServantQueryParam spList,String itype, Integer page, Integer limit);
	

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
	Page<ServantVO> queryServantInfoBySeniorCondation(List<String> pList, List<String> l, Integer page, Integer limit);

}
