/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: AssessmentServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-考核 服务实现类
 * 创建人: jiang
 * 创建时间: 2018年7月2日10:33:13
 * 版本号: V1.0
 * 修改人：jiang
 * 修改时间：2018年7月2日10:33:15
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.pubinst.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.human.bo.pubinst.PtAppointDismissDuty;
import com.wondersgroup.human.bo.pubinst.PtAssessment;
import com.wondersgroup.human.repository.pubinst.PtAssessmentRepository;
import com.wondersgroup.human.service.pubinst.PtAppointDismissDutyService;
import com.wondersgroup.human.vo.pubinst.PtAssessmentVO;

/**
 * @ClassName: PtAppointDissmissDutyServiceImpl
 * @Description: 拟任拟免职务 服务实现类
 * @author: jiang
 * @date: 2018年7月2日10:33:20
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PtAppointDissmissDutyServiceImpl extends GenericServiceImpl<PtAppointDismissDuty> implements PtAppointDismissDutyService {
	
}
