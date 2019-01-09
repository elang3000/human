/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: AbroadPeopleService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: TODO
 * 创建人: lihao   
 * 创建时间: 2018年12月11日 下午4:50:20 
 * 版本号: V1.0
 * 修改人：lihao 
 * 修改时间：2018年12月11日 下午4:50:20 
 * 修改任务号
 * 修改内容：TODO
 */
package com.wondersgroup.human.service.ofcflow;

import java.util.List;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.AbroadPeople;
import com.wondersgroup.human.vo.ofcflow.AbroadPeopleVO;

/** 
 * @ClassName: AbroadPeopleService 
 * @Description: 因公出国人员服务层接口
 * @author: lihao
 * @date: 2018年12月11日 下午4:50:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface AbroadPeopleService extends GenericService<AbroadPeople>{

	/** 
	 * @Title: getServantIdsByAbroadId 
	 * @Description: 查询因公出国事项人员列表
	 * @param abroadId
	 * @return
	 * @return: List<AbroadPeople>
	 */
	List<AbroadPeople> getServantIdsByAbroadId(String abroadId);

	/** 
	 * @Title: getByServantIds 
	 * @Description: 根据传过来的id显示人员列表
	 * @param servantIds
	 * @param limit
	 * @param page
	 * @return
	 * @return: Page<AbroadPeopleVO>
	 */
	Page<AbroadPeopleVO> getByServantIds(String abroadId,String abroadYearId, Integer limit, Integer page);
}
