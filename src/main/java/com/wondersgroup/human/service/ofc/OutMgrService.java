/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: OutMgrService.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc
 * 描述: 人员信息子表-调出情况 服务接口
 * 创建人: tzy
 * 创建时间: 2018年7月30日 上午10:39:23
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年7月30日 上午10:39:23
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.vo.ofc.DegreeVO;
import com.wondersgroup.human.vo.ofc.OutMgrVO;

/**
 * @ClassName: OutMgrService
 * @Description: 人员信息子表-调出情况 服务接口
 * @author: tzy
 * @date: 2018年7月30日 上午10:39:23
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public interface OutMgrService extends GenericService<OutMgr> {
	
	Page<OutMgrVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit);
}
