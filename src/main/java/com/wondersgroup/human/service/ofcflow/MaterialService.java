/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: MaterialService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow 
 * 描述: 材料 服务接口
 * 创建人: tzy   
 * 创建时间: 2018年12月25日 下午5:11:47 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月25日 下午5:11:47 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofcflow.Material;
import com.wondersgroup.human.vo.ofcflow.MaterialVO;

/** 
 * @ClassName: MaterialService 
 * @Description: 材料 服务接口
 * @author: tzy
 * @date: 2018年12月25日 下午5:11:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface MaterialService extends GenericService<Material>{

	/** 
	 * @Title: getPage 
	 * @Description: 材料vo列表
	 * @param material
	 * @param page
	 * @param limit
	 * @return
	 * @return: Page<MaterialVO>
	 */
	Page<MaterialVO> getPage(Material material, Integer page, Integer limit);
	
}
