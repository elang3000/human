/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ReferenceExchangeOutBatchServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl
 * 描述: TODO
 * 创建人: GP
 * 创建时间: 2018年12月20日 下午4:48:01
 * 版本号: V1.0
 * 修改人：GP
 * 修改时间：2018年12月20日 下午4:48:01
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.human.service.ofcflow.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.config.ReadProperties;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.provider.OrganCacheProvider;
import com.wondersgroup.framework.resource.bo.AppNode;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;
import com.wondersgroup.framework.util.EventManager;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.framework.workflow.bo.FlowRecord;
import com.wondersgroup.framework.workflow.service.WorkflowService;
import com.wondersgroup.human.bo.ofc.Experience;
import com.wondersgroup.human.bo.ofc.JobLevel;
import com.wondersgroup.human.bo.ofc.ManagerRecord;
import com.wondersgroup.human.bo.ofc.OutMgr;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.Material;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchange;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOut;
import com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOutBatch;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOut;
import com.wondersgroup.human.bo.ofcflow.ZhuanRenTLBOutBatch;
import com.wondersgroup.human.bo.record.HumanKeepRecord;
import com.wondersgroup.human.dto.ofc.ManagerRecordDTO;
import com.wondersgroup.human.dto.record.HumankeepRecordDTO;
import com.wondersgroup.human.event.ofc.ManagerOutRecordEvent;
import com.wondersgroup.human.event.record.ServantHumamKeepRecordEvent;
import com.wondersgroup.human.service.ofc.ExperienceService;
import com.wondersgroup.human.service.ofc.JobLevelService;
import com.wondersgroup.human.service.ofc.OutMgrService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.MaterialService;
import com.wondersgroup.human.service.ofcflow.OfcFlowNumberService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutBatchService;
import com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutService;
import com.wondersgroup.human.service.organization.FormationControlService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.Number2CN;
import com.wondersgroup.human.vo.ofcflow.ReferenceExchangeOutBatchVO;
import com.wondersgroup.human.vo.ofcflow.ZhuanRenTLBOutBatchVO;
import com.wondersgroup.human.vo.organization.JudgePostResult;

/**
 * @ClassName: ReferenceExchangeOutBatchServiceImpl
 * @Description: TODO
 * @author: GP
 * @date: 2018年12月20日 下午4:48:01
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class ReferenceExchangeOutBatchServiceImpl extends GenericServiceImpl<ReferenceExchangeOutBatch>
        implements ReferenceExchangeOutBatchService {
	
	@Autowired
	private ServantService servantService;
	
	@Autowired
	private ReferenceExchangeOutService referenceExchangeOutService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private OutMgrService outMgrService;
	
	@Autowired
	private ExperienceService experienceService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FormationControlService formationControlService;
	
	@Autowired
	private JobLevelService jobLevelService;
	
	@Autowired
	private OfcFlowNumberService ofcFlowNumberService;
	
	@Autowired
	private MaterialService materialService;
	
	/**
	 * (non Javadoc)
	 * @Title: findbyHQLforVO
	 * @Description: TODO
	 * @param hql
	 * @param listqueryparametry
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @see com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutBatchService#findbyHQLforVO(java.lang.String,
	 *      java.util.List, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Page<ReferenceExchangeOutBatchVO> findbyHQLforVO(String hql, List<QueryParameter> listqueryparametry,
	        Integer pageNo, Integer pagesize) {
		
		Page<ReferenceExchangeOutBatch> temppage = this.findByHQL(hql, listqueryparametry, pageNo, pagesize);
		List<ReferenceExchangeOutBatchVO> voList = new ArrayList<>();
		for (ReferenceExchangeOutBatch s : temppage.getResult()) {
			ReferenceExchangeOutBatchVO vo = new ReferenceExchangeOutBatchVO(s);
			voList.add(vo);
		}
		Page<ReferenceExchangeOutBatchVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(),
		        temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}
	
	/**
	 * (non Javadoc)
	 * @Title: remove
	 * @Description: TODO
	 * @param id
	 * @see com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutBatchService#remove(java.lang.String)
	 */
	@Override
	public void remove(String id) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(ReferenceExchangeOut.class);
		criteria.add(Restrictions.eq("referenceExchangeOutBatch.id", id));
		criteria.add(Restrictions.eq("removed", false));
		List<ReferenceExchangeOut> list = referenceExchangeOutService.findByCriteria(criteria);
		for (ReferenceExchangeOut t : list) {
			referenceExchangeOutService.delete(t);
		}
		ReferenceExchangeOutBatch z = get(id);
		delete(z);
	}
	
	/**
	 * (non Javadoc)
	 * @Title: savePeople
	 * @Description: TODO
	 * @param z
	 * @param servantIds
	 * @see com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutBatchService#savePeople(com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOutBatch,
	 *      java.lang.String)
	 */
	@Override
	public void savePeople(ReferenceExchangeOutBatch z, String servantIds) {
		
		if (StringUtils.isBlank(z.getId())) {// 如果没有保存批次信息，先保存
			z.setStatus(ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE);// 流程状态，待提交
			save(z);
		} else {
			z = get(z.getId());
		}
		String[] idArr = servantIds.split(",");
		for (String i : idArr) {
			Servant servant = servantService.get(i);
			ReferenceExchangeOut zz = new ReferenceExchangeOut();
			zz.setReferenceExchangeOutBatch(z);// 关联批次信息
			zz.setServant(servant);// 关联人员信息
			zz.setStatus(z.getStatus());
			zz.setSourceOrgan(z.getSourceOrgan());
			JobLevel tempJ = jobLevelService.getJobLevelByServantId(servant.getId());//查询当前人员的现行职级
			zz.setJobLevelName(tempJ.getName());
			if(CommonConst.YES==tempJ.getIsLeader()){
				zz.setIsLeaderView("领导");
			}else{
				zz.setIsLeaderView("非领导");
			}
			zz.setRealJobLevelCode(tempJ.getRealJobLevelCode());
			zz.setRealLeader(tempJ.getRealLeader());
			zz.setServant(servant);//关联人员信息
			referenceExchangeOutService.save(zz);
		}
		
	}
	
	/**
	 * (non Javadoc)
	 * @Title: saveFlow
	 * @Description: TODO
	 * @param temp
	 * @param opinion
	 * @param r
	 * @see com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutBatchService#saveFlow(com.wondersgroup.human.bo.ofcflow.ReferenceExchangeOutBatch,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void saveFlow(ReferenceExchangeOutBatch temp, String opinion, String r) {
		
		SecurityUser user = userService.load(SecurityUtils.getUserId());// 当前登录人
		OrganNode userOrg = OrganCacheProvider.getOrganNodeInGovNode(SecurityUtils.getUserId());// 当前登录所在单位
		AppNode appNode = (AppNode) SecurityUtils.getSession().getAttribute("appNode");
		if (StringUtils.isBlank(temp.getId())) {
			saveOrUpdate(temp);// 保存业务数据
		}
		if(ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE==temp.getStatus()){//提交环节，锁编
			executeLockOutFormationAndPost(temp);
		}
		if (ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_TRIAL == temp.getStatus()) {// （调出单位确认）环节，1.校验数据是否填写，2.锁编
			// 1.校验数据是否填写（修改为转出单位确认时填写转出数据）
			DetachedCriteria criteria = DetachedCriteria.forClass(ReferenceExchangeOut.class);
			criteria.add(Restrictions.eq("referenceExchangeOutBatch.id", temp.getId()));
			criteria.add(Restrictions.eq("removed", false));
			List<ReferenceExchangeOut> list = referenceExchangeOutService.findByCriteria(criteria);// 该批次下所有参公交流人员信息，判断各个职级人数是否和批次中人数一致
			for (ReferenceExchangeOut i : list) {
				if (StringUtils.isBlank(i.getGotoUnitName())) { throw new BusinessException("请先编辑所有人员的参公交流信息！"); }
			}
		}
		
		FlowRecord flow;
		if (ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE == temp.getStatus() && temp.getFlowRecord() == null) {// 提交环节，生成流程数据
			
			flow = new FlowRecord();
			flow.setAppNodeId(appNode.getId());// 流程业务所在系统
			flow.setBusId(temp.getId());// 流程业务ID
			flow.setBusName("参公交流转出");// 流程业务名称
			flow.setBusType("ReferenceExchange_OutMgr");// 流程业务类型(参公交流)
			flow.setTargetOrganNode(userOrg);// 流程业务目标组织
			flow.setTargetSecurityUser(user);
			// 流程业务目标人员
			flow = workflowService.createFlowRecord(flow, "STATUS_EXCHANGE_OUT_STATE",temp.getSourceOrgan());//初始节点
		} else {
			flow = temp.getFlowRecord();
			flow.setOpinion(opinion);
			flow.setResult(r);
			if(ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE==temp.getStatus()){
				flow.setResult(FlowRecord.PASS);
				flow = workflowService.completeFlowRecordByAppoint(flow,temp.getSourceOrgan());//提交下个节点
			}else{
				if(ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_TRIAL_4==temp.getStatus()){
					createIntroduction(temp.getId());
				}
				flow = workflowService.completeWorkItem(flow);//提交下个节点
			}
		}
		if (flow == null && FlowRecord.PASS.equals(r)) {// 流程最后环节
			updateServant(temp);
			temp.setStatus(ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_FINISH);
			temp.setFlowRecord(null);// 修改当前业务的流程节点
		} else {
			temp.setStatus(ReferenceExchangeOutBatch.power.get(flow.getOperationCode()));// 实际有权限的操作节点
			temp.setFlowRecord(flow);// 修改当前业务的流程节点
			
//			if (ReferenceExchangeOutBatch.STATUS_EXCHANGE_OUT_STATE == temp.getStatus() && FlowRecord.NOPASS.equals(r)) {// 如果流程退回到最初节点，解锁编制，每次提交重新锁编
//			}
		}
		update(temp);
		DetachedCriteria criteria = DetachedCriteria.forClass(ReferenceExchangeOut.class);
		criteria.add(Restrictions.eq("referenceExchangeOutBatch.id", temp.getId()));
		criteria.add(Restrictions.eq("removed", false));
		List<ReferenceExchangeOut> list = referenceExchangeOutService.findByCriteria(criteria);
		for (ReferenceExchangeOut temp1 : list) {
			temp1.setStatus(temp.getStatus());
			referenceExchangeOutService.update(temp1);	//更新子表状态
		}
		
	}
	
	/**
	 * @Title: executeLockOutFormationAndPost 
	 * @Description: 锁未调出编制
	 * @param temp
	 * @return: List<JudgePostResult> 原单位职级信息
	 */
	public void executeLockOutFormationAndPost(ReferenceExchangeOutBatch temp){
		List<JudgePostResult> list = new ArrayList<>();
		DetachedCriteria criteria = DetachedCriteria.forClass(ReferenceExchangeOut.class);
		criteria.add(Restrictions.eq("referenceExchangeOutBatch.id", temp.getId()));
		criteria.add(Restrictions.eq("removed", false));
		List<ReferenceExchangeOut> intoList = referenceExchangeOutService.findByCriteria(criteria);
		for (ReferenceExchangeOut z : intoList){
				if(StringUtils.isBlank(z.getRealJobLevelCode())){
					throw new BusinessException("占编职级信息异常，请联系管理员！");
				}
				JudgePostResult j = new JudgePostResult(z.getRealJobLevelCode(), z.getRealLeader(),1);
				list.add(j);
		}
		formationControlService.executeLockOutFormationAndPost(temp.getSourceOrgan().getId(), list);//锁未调出
	}
	
	
	/**
	 * @Title: updateServant
	 * @Description: 更新人员信息
	 * @return: void
	 */
	private void updateServant(ReferenceExchangeOutBatch z) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(ReferenceExchangeOut.class);
		criteria.add(Restrictions.eq("referenceExchangeOutBatch.id", z.getId()));
		criteria.add(Restrictions.eq("removed", false));
		List<ReferenceExchangeOut> list = referenceExchangeOutService.findByCriteria(criteria);
		for (ReferenceExchangeOut temp : list) {
			// 修改原数据状态为调出
			Servant oldServant = servantService.get(temp.getServant().getId());
			CodeInfo outer = dictableService.getCodeInfoByCode("3", DictTypeCodeContant.CODE_HUMAN_STATUS);// 调出CODE
			oldServant.setIsOnHold(outer);// 调出状态
			servantService.update(oldServant);
			// 转出子集信息
			OutMgr out = new OutMgr();
			out.setServant(oldServant);// 人员基本信息
			out.setCategory(temp.getCategory());// 调出本单位类别
			out.setReasonType(temp.getReasonType());// 调动原因
			out.setOutDate(temp.getOutDate());// 调出本单位日期
			out.setGotoUnitName(temp.getGotoUnitName());// 调往单位名称
			out.setProposeType(temp.getProposeType());// 提出调动类型
			out.setRemark(temp.getRemark());// 调出备注
			outMgrService.save(out);
			// 更新简历
			Experience e = experienceService.getLatestExperienceByServantId(oldServant.getId());
			e.setEndDate(new Date());
			experienceService.update(e);
			// 进出管理
			ManagerRecordDTO dto = new ManagerRecordDTO(temp.getServant().getId(), ManagerRecord.HUMAN_TLZC);
			ManagerOutRecordEvent event = new ManagerOutRecordEvent(dto);
			EventManager.send(event);
			// 备案管理
			HumankeepRecordDTO dto2 = new HumankeepRecordDTO(temp.getServant().getId(), HumanKeepRecord.KEEP_TLZC);
			ServantHumamKeepRecordEvent event2 = new ServantHumamKeepRecordEvent(dto2);
			EventManager.send(event2);
		}
	}
	
	/**
	 * @Title: createIntroduction 
	 * @Description: 生成该批次的介绍信  每个人员一个介绍信，一个编号
	 * @param id		批次id
	 * @return: void
	 */
	private void createIntroduction(String id){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String templet = savePath+"\\static\\templates\\introduce.xls";//模板路径
		String folder = ReadProperties.getInstance().FTP_DIR_NAME_MATERIAL;//ftp文件路径
		
		Map<String,Object> params = new HashMap<>();//所有参数
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ReferenceExchangeOutBatch batch = get(id);
		
		DetachedCriteria criteria = DetachedCriteria.forClass(ReferenceExchangeOut.class);
		criteria.add(Restrictions.eq("referenceExchangeOutBatch.id", batch.getId()));
		criteria.add(Restrictions.eq("removed", false));
		List<ReferenceExchangeOut> list = referenceExchangeOutService.findByCriteria(criteria);
		params.put("sumNumber", "壹");//转任人数
		params.put("now", sdf.format(new Date()));//打印介绍信时间
		params.put("busType", "参公交流转出");//转移原因
		params.put("sourceOrgan", batch.getSourceOrgan().getName());//源单位名称
		//当前操作人
		params.put("userName", SecurityUtils.getPrincipal().getName());
		for(int i =0;i<list.size();i++){
			ReferenceExchangeOut into = list.get(i);
			Material m = new Material();//材料信息 一定要生成
			try {
				m.setBusId(into.getId());
				m.setBusType("ReferenceExchangeOUT");
				m.setBusName("参公交流转出");
				
				List<String[]> dataList = new ArrayList<>();//所有人员信息
				params.put("enterTheUnitDate", "");//报道日期
				String[] p = new String[6];
				params.put("name", into.getServant().getName());
				params.put("targetOrgan", into.getGotoUnitName());//目标单位名称
				p[0] = into.getServant().getName();//转任人员名称
				p[1] = into.getServant().getSex()==null?"":into.getServant().getSex().getName();//调任人员性别
				p[2] = batch.getSourceOrgan().getName();//源单位名称
				p[4] = into.getServant().getNowPostName()==null?"":into.getServant().getNowPostName();//原职务
				p[5] = into.getServant().getNowJobLevel()==null?"":into.getServant().getNowJobLevel().getName();//原级别
				dataList.add(p);
				params.put("listData", dataList);//list数据需要用list标签
				
				Map<String,String> number = ofcFlowNumberService.executeNumber("ExchangeOUT", into.getId());//介绍信编号
				params.put("number", number.get("year")+"字第"+number.get("number"));//介绍信编号
				//编号转换为大写
				String cnYear = Number2CN.convert(number.get("year"));
				String cnNumber = Number2CN.convert(number.get("number"));
				params.put("cnNumber", cnYear+"字第"+cnNumber+"号");//介绍信编号大写
				String uuid = UUID.randomUUID().toString();
				String path = savePath+"\\static\\templates\\"+uuid+".xls";//生成excel文件路径，临时存放，上传到ftp服务器之后会删除
				ExcelUtilsPOI.replaceModel(params, templet,path, 2,null);//替换模板数据 生成excel到tomcat服务器
				
				//保存文件到ftp服务器
				String fileName = uuid+".xls";
				
				File file = new File(path);
				if(FtpTool.ftpUpload(folder, file, fileName)){//文件成功上传到ftp，删除本地文件并保存材料表数据
					m.setFlowNumber(number.get("number"));
					m.setName(dataList.get(0)[0]+"参公交流介绍信"+".xls");
					m.setFtpFilefolder(folder);
					m.setFtpFileName(fileName);
					file.delete();//删除文件
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			materialService.save(m);
		}
	}

	/** (non Javadoc) 
	 * @Title: itembatchItems
	 * @Description: TODO
	 * @param batchId
	 * @return 
	 * @see com.wondersgroup.human.service.ofcflow.ReferenceExchangeOutBatchService#itembatchItems(java.lang.String) 
	 */
	@Override
	public List<ReferenceExchangeOut> itembatchItems(String batchId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ReferenceExchangeOut.class);
		criteria.add(Restrictions.eq("referenceExchangeOutBatch.id", batchId));
		criteria.add(Restrictions.eq("removed", false));
		List<ReferenceExchangeOut> list = referenceExchangeOutService.findByCriteria(criteria);
		
		return list;
	}
	
}
