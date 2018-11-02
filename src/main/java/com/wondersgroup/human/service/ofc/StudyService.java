/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: StudyService.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc 
 * 描述: 人员信息子表-学习（培训、进修） 服务接口
 * 创建人: jiang   
 * 创建时间: 2018年8月21日15:10:00
 * 版本号: V1.0
 * 修改人：jiang 
 * 修改时间：2018年8月21日15:10:03
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc;

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.GenericService;
import com.wondersgroup.human.bo.ofc.Study;
import com.wondersgroup.human.vo.ofc.StudyVO;

/** 
 * @ClassName: DegreeService 
 * @Description: 人员信息子表-学习（培训、进修）服务接口
 * @author: jiang
 * @date: 2018年8月21日15:10:12
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public interface StudyService extends GenericService<Study>{

	Page<StudyVO> getPage(List<Predicate> filter, Sorts sort, Integer page, Integer limit);

}
