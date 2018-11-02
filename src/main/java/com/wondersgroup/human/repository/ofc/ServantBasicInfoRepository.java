/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServatBasicInfoRepository.java
 * 工程名: human
 * 包名: com.wondersgroup.human.repository.ofc
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年9月24日 下午3:50:32
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年9月24日 下午3:50:32
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.repository.ofc;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.bo.ofc.ServantBasicInfo;

/**
 * @ClassName: ServatBasicInfoRepository
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年9月24日 下午3:50:32
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface ServantBasicInfoRepository {
	
	Class<ServantBasicInfo> getEntityClass();
	
	/**
	 * @Title: load
	 * @Description: 根据实例对象主键ID，立即从数据表中获取实例对象内容。
	 * @param id 实例对象主键ID
	 * @return 获取到实例对象
	 * @return: ServantBasicInfo
	 */
	ServantBasicInfo load(Serializable id);
	
	/**
	 * @Title: get
	 * @Description: 根据实例对象主键ID，在SESSION当中生成数据查询记录，当中引用对象中非ID属性产生使用时，则向数据表中读取数据。
	 * @param id 实例对象主键ID
	 * @return 获取到实例对象应用
	 * @return: ServantBasicInfo
	 */
	ServantBasicInfo get(Serializable id);
	
	/**
	 * @Title: findByCriteria
	 * @Description: 根据DetachedCriteria查询对象，查询满足对象条件的所有数据记录集合
	 * @param detachedCriteria 查询条件对象
	 * @return 所有满足条件的查询结果集合
	 * @return: List<ServantBasicInfo>
	 */
	List<ServantBasicInfo> findByCriteria(final DetachedCriteria detachedCriteria);
	
	/**
	 * @Title: findByCriteria
	 * @Description: 根据DetachedCriteria查询对象，查询满足对象条件的所有数据记录集合，并根据分页条件进行分页
	 * @param detachedCriteria 查询条件对象
	 * @param pageNumber 页码
	 * @param pageSize 记录量
	 * @return 所有满足条件的查询分页结果集合
	 * @return: Page<ServantBasicInfo>
	 */
	Page<ServantBasicInfo> findByCriteria(final DetachedCriteria detachedCriteria, Integer pageNumber,
	        Integer pageSize);
	
}
