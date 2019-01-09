/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: DraftServantImportRecordServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofcflow.impl 
 * 描述: 
 * 创建人: GP   
 * 创建时间: 2018年6月26日 上午10:20:56 
 * 版本号: V1.0
 * 修改人：GP 
 * 修改时间：2018年6月26日 上午10:20:56 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofcflow.impl;

import com.wondersgroup.common.contant.CommonConst;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.utils.ImportExcelUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.dao.support.Predicate.Operator;
import com.wondersgroup.framework.core.dao.support.QueryParameter;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.framework.util.DateUtils;
import com.wondersgroup.framework.util.SecurityUtils;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.bo.ofcflow.DraftServant;
import com.wondersgroup.human.bo.ofcflow.DraftServantEduInfo;
import com.wondersgroup.human.bo.ofcflow.DraftServantImportRecord;
import com.wondersgroup.human.bo.organization.OrgInfo;
import com.wondersgroup.human.repository.ofcflow.DraftServantImportRecordDAO;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.service.ofcflow.DraftServantEduInfoService;
import com.wondersgroup.human.service.ofcflow.DraftServantImportRecordService;
import com.wondersgroup.human.service.ofcflow.DraftServantService;
import com.wondersgroup.human.service.organization.OrgInfoService;
import com.wondersgroup.human.vo.ofcflow.DraftServantImportRecordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/** 
 * @ClassName: DraftServantImportRecordServiceImpl 
 * @Description: 
 * @author: GP
 * @date: 2018年6月26日 上午10:20:56
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class DraftServantImportRecordServiceImpl extends GenericServiceImpl<DraftServantImportRecord> implements DraftServantImportRecordService{

    protected final static Logger logger = LoggerFactory.getLogger(DraftServantImportRecordServiceImpl.class);

	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private DraftServantService draftServantService;
	
	@Autowired
	private DraftServantEduInfoService draftServantEduInfoService;
	
	@Autowired
	private OrgInfoService unitBasciInfoService;
	
	@Resource
	private DraftServantImportRecordDAO draftServantImportRecordDAO;

	@Autowired
	private ServantService servantService;
	/** (non Javadoc) 
	 * @Title: saveImportRecord
	 * @Description: 
	 * @param file
	 * @return
	 * @throws BusinessException 
	 */
	@Override
	public DraftServantImportRecord saveImportRecord(MultipartFile file,int year,int servantType) throws BusinessException {
		InputStream in = null;
		if (file.isEmpty()) {
			throw new BusinessException("文件不存在！");
		}

		// 保存导入记录对象
		DraftServantImportRecord record = new DraftServantImportRecord();
//		record.setServantType(servantType);
		record.setFileName(file.getOriginalFilename());
		record.setCreateUserName(SecurityUtils.getPrincipal().getLoginName());
		record.setYearTip(year);
		record.setImportDate(new Date());
		record.setServantType(servantType+"");
		record.setPublish(DraftServant.EMPLOY_NO_PUBLISH);
		save(record);

		// 读取excel文件 保存拟录用公务员名单
		List<List<Object>> listObj = null;
		List<List<Object>> edulistObj = null;
		try {
			in = file.getInputStream();
			listObj = new ImportExcelUtil().getListByExcel(in, file.getOriginalFilename(), 3);
			if (listObj.size() <= 1)
				throw new BusinessException("文件中人员信息基本集数据不存在！");
			List<DraftServant> dsList = new ArrayList<>();
			Map<Integer, String> noEntryMap=new LinkedHashMap<>();
			for (int i = 2; i < listObj.size(); i++) {
				if ((listObj.get(i)).size() <= 1)
					continue;
				DraftServant ds = new DraftServant();
				ds.setYearTip(year);
				ds.setImportRecordId(record.getId());
				ds.setServantType(servantType);
				//设置状态为汇总状态
				ds.setStatus(DraftServant.STATUS_EMPLOY_COLLECT);
				String noEntry = installDraftServant(listObj, i, ds);
				dsList.add(ds);
				if(noEntry!=null){
					noEntryMap.put(i,noEntry);
				}
				
			}
			record.setImportNum(dsList.size());
			record.setFailStr(noEntryMap.toString());
			save(record);
			draftServantService.saveBatchDraftServant(dsList);

			// 读取公务员学历子集，以身份证匹配
/*			in = file.getInputStream();
			edulistObj = new ImportExcelUtil().getListByExcel(in, file.getOriginalFilename(), 1);
			if (edulistObj.size() <= 1)
				throw new BusinessException("文件中学历学位信息集数据不存在！");
			List<DraftServantEduInfo> dseList = new ArrayList<>();
			for (int i = 1; i < edulistObj.size(); i++) {
				if ((edulistObj.get(i)).size() <= 1)
					continue;
				// 首先判断学历中身份证信息是否有匹配的公务员人员 + 人员导入标识“YYYYMMDD”
				String cardNo = (String) edulistObj.get(i).get(2);
				String importFlag = DateUtils.format(new Date(), "yyyyMMdd");
				HashMap<String, Object> condMap = new HashMap();
				condMap.put("cardNo", cardNo);
				condMap.put("yearTip", year);
				condMap.put("importFlag", importFlag);
				List<DraftServant> dslist = draftServantService.findByCondMap(condMap);
				if (dslist.isEmpty())
					throw new BusinessException("学历信息无法匹配到人员信息，请检查身份证号：" + cardNo + "的信息");
				DraftServant d = dslist.get(0);

				// 装载学历信息
				DraftServantEduInfo dse = new DraftServantEduInfo();
				dse.setDraftServantId(d.getId());
				installDraftServantEduInfo(edulistObj, i, dse);
				dseList.add(dse);
			}*/
//			draftServantEduInfoService.saveBatch(dseList);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("文件读取错误，请联系管理员！");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return record;
	}
	
	/**
	 * 
	 * @Title: installDraftServant 
	 * @Description: 	长宁区版本导入,只导入人的姓名，性别，身份证号，招录单位,招录单位采用模糊匹配,未匹配到的记录返回
	 * @param listObj
	 * @param i
	 * @param ds
	 * @return
	 * @return: Map<Integer,String>
	 */
	private String installDraftServant(List<List<Object>> listObj, int i, DraftServant ds) {
		String noEntryRecord=null;
		ds.setRegisterId((String) listObj.get(i).get(0));

		//姓名
		String name=(String) listObj.get(i).get(1);
		ds.setName(name);

		//身份证
		String cardNo=((String) listObj.get(i).get(2)).replaceAll("'", "");
		ds.setCardNo(cardNo);
		
		//性别
		CodeInfo tempcodeinfo=dictableService.findCodeInfoByName(DictTypeCodeContant.CODE_TYPE_SEX, (String) listObj.get(i).get(19)).get(0);
		ds.setSex(tempcodeinfo);

		//出生日期
        try{
            ds.setBirthDate(DateUtils.parse((String)listObj.get(i).get(20), "yyyy-mm-dd"));
        }catch (Exception e){
            logger.info("导入模板出生日期数据类型错误:"+"第"+i+"行"+name);
        }


		String examineeStatus=(String)listObj.get(i).get(27);
		//考生身份
		ds.setExamineeStatus(examineeStatus);

		//是否应届毕业生
		if(examineeStatus.equals("应届生")){
            CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
			ds.setIsGraduating(yesCodeInfo);
		}else{
            CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
            ds.setIsGraduating(noCodeInfo);
        }

        //学历
        String[] degreeArray={"专科","本科","硕士","博士"};
        String[] systemDegreeArray = {"大学专科毕业", "大学本科毕业","硕士研究生毕业","博士研究生毕业"};
        List<String> degreeList= Arrays.asList(degreeArray);
        String degree = (String) listObj.get(i).get(28);
        int index = degreeList.indexOf(degree);
        //假如包含以上职位则设置对应职位
        if(index!=-1){
            CodeInfo degreeCodeInfo=dictableService.findCodeInfoByName(DictTypeCodeContant.CODE_TYPE_EDUCATION, systemDegreeArray[index]).get(0);
            ds.setDegree(degreeCodeInfo);
        }



		//申论成绩
		ds.setExplainingScore(Float.parseFloat((String) listObj.get(i).get(10)));

		//行政职业能力测试
		ds.setWrittenExamTestScore(Float.parseFloat((String) listObj.get(i).get(11)));

		//民族
		List<CodeInfo> nationCodeinfos=dictableService.findCodeInfoByName(DictTypeCodeContant.CODE_TYPE_NATION, (String) listObj.get(i).get(24));
		if(nationCodeinfos.size()>0){
            ds.setNation(nationCodeinfos.get(0));
        }


		//本科毕业院校
		ds.setUndergraduateName((String)listObj.get(i).get(31));

		//硕士毕业院校
		ds.setMastergraduateName((String)listObj.get(i).get(34));

		//博士毕业院校
		ds.setDoctorgraduateName((String)listObj.get(i).get(37));

		//通讯地址
		ds.setHomeAddress((String)listObj.get(i).get(48));

		//手机号码
		ds.setPhoneNumber((String)listObj.get(i).get(50));

		//户籍所在地
		ds.setResidencePlace((String)listObj.get(i).get(55));

		
		//招录单位采用模糊匹配,未匹配到的记录放到map中
		OrgInfo unitBasicInfo = unitBasciInfoService.findLikeSimpleName("%"+(String) listObj.get(i).get(5)+"%");

		//查询servant表中是否有身份证相同的人员,如果有则放入到map中
		List<Servant> servants=servantService.getServantByCardNo(cardNo);
		ds.setImportStatus(CommonConst.YES);
        ds.setImportStatusStr("导入成功");
		if((servants!=null&&servants.size()>0)){
			noEntryRecord="(已经存在)"+(String) listObj.get(i).get(1);
			ds.setImportStatusStr("已经存在");
			ds.setImportStatus(CommonConst.NO);
		}
		if(unitBasicInfo==null){
			noEntryRecord="(单位匹配失败)"+(String) listObj.get(i).get(1);
			ds.setImportStatusStr("单位匹配失败");
			ds.setImportStatus(CommonConst.NO);
		}else{
			ds.setDraftUnit(unitBasicInfo);
			ds.setDraftUnitName(unitBasicInfo.getUnitBasicName());
		}

		// 录用情况统一设置为 拟录用
		tempcodeinfo=dictableService.getCodeInfoByCode(DraftServant.EMPLOY_RESULT_EMPLOY_ID, DraftServant.EMPLOY_TYPE);
		ds.setEmploySituation(tempcodeinfo);
		// 状态 设置为 未报送
		ds.setStatus(DraftServant.STATUS_EMPLOY_NO_COLLECT);
		// 导入标识“YYYYMMDD”
		ds.setImportFlag(DateUtils.format(new Date(), "yyyyMMdd"));
		ds.setPublish(DraftServant.EMPLOY_NO_PUBLISH);
		return noEntryRecord;
	}
	
	
	//老版本导入
/*	private void installDraftServant(List<List<Object>> listObj, int i, DraftServant ds) {
		ds.setRegisterId((String) listObj.get(i).get(0));
		ds.setName((String) listObj.get(i).get(1));
		ds.setCardNo((String) listObj.get(i).get(2));
		
		CodeInfo tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(3));
		
		ds.setSex(tempcodeinfo);
		ds.setBirthDate(DateUtils.parseDate((String) listObj.get(i).get(4)));
		// 出生地
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(5));
		ds.setBirthPlace(tempcodeinfo);
		ds.setBirthPlaceName((String) listObj.get(i).get(6));
		// 籍贯
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(7));
		ds.setNativePlace(tempcodeinfo);
		ds.setNativePlaceName((String) listObj.get(i).get(8));
		
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(9));
		ds.setNation(tempcodeinfo);
		
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(10));
		ds.setPolitics(tempcodeinfo);
		
		// 是否退役士兵
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(11));
		ds.setIsRetiredSoldier(tempcodeinfo);
		// 是否退役大学生士兵
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(12));
		ds.setIsRetiredCollegeStudentSoldier(tempcodeinfo);
		// 是否残疾人
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(13));
		ds.setIsdisabled(tempcodeinfo);
		// 是否有海外留学经历
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(14));
		ds.setIsStudyAbroad(tempcodeinfo);
		// 留学年限
		ds.setStudyAbroadTime(Integer.parseInt((String) listObj.get(i).get(15)));
		// 是否有海外工作经历
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(16));
		ds.setIsWorkAbroad(tempcodeinfo);
		// 海外工作年限
		ds.setWorkAbroadTime(Integer.parseInt((String) listObj.get(i).get(17)));
		
		tempcodeinfo=dictableService.loadCodeInfoById((String) listObj.get(i).get(18));
		ds.setSource(tempcodeinfo);
		ds.setDraftUnitName((String) listObj.get(i).get(19));
		ds.setDraftUnitId((String) listObj.get(i).get(20));
		ds.setDraftDeptName((String) listObj.get(i).get(21));
		ds.setDraftDeptId((String) listObj.get(i).get(22));
		ds.setEmployJobName((String) listObj.get(i).get(23));
		ds.setTicketId((String) listObj.get(i).get(24));
		
		// 专业能力测试成绩
		ds.setAptitudeTestScore(Float.parseFloat((String) listObj.get(i).get(25)));
		// 公共科目笔试成绩
		ds.setPublicSubjectTestScore(Float.parseFloat((String) listObj.get(i).get(26)));
		// 笔试（行政职业能力测试）成绩
		ds.setWrittenExamTestScore(Float.parseFloat((String) listObj.get(i).get(27)));
		// 笔试（申论）成绩
		ds.setExplainingScore(Float.parseFloat((String) listObj.get(i).get(28)));
		// 笔试（专业科目）成绩
		ds.setProfessionalSubjectScore(Float.parseFloat((String) listObj.get(i).get(29)));
		// 面试成绩
		ds.setInterviewScore(Float.parseFloat((String) listObj.get(i).get(30)));
		// 其他科目成绩
		ds.setOtherSubjectScore(Float.parseFloat((String) listObj.get(i).get(31)));
		// 体检
		ds.setPhysicalEXAM((String) listObj.get(i).get(32));
		// 政审考核
		ds.setPoliticalExam((String) listObj.get(i).get(33));
		
		// 录用情况统一设置为 拟录用
		tempcodeinfo=dictableService.getCodeInfoByCode(DraftServant.EMPLOY_TYPE_DRAFT, DraftServant.EMPLOY_TYPE);
		ds.setEmploySituation(tempcodeinfo);
		// 状态 设置为 未报送
		ds.setStatus(DraftServant.STATUS_EMPLOY_NO_COLLECT);
		// 导入标识“YYYYMMDD”
		ds.setImportFlag(DateUtils.format(new Date(), "yyyyMMdd"));
		ds.setPublish(DraftServant.EMPLOY_NO_PUBLISH);
	}
*/	
	private void installDraftServantEduInfo(List<List<Object>> edulistObj, int i, DraftServantEduInfo dse) {
		dse.setRegisterId((String) edulistObj.get(i).get(0));
		dse.setName((String) edulistObj.get(i).get(1));
		dse.setCardNo((String) edulistObj.get(i).get(2));
		CodeInfo tempcodeinfo=dictableService.loadCodeInfoById((String) edulistObj.get(i).get(3));
		dse.setEducationCode(tempcodeinfo);
		dse.setEducationName((String) edulistObj.get(i).get(4));
		
		tempcodeinfo=dictableService.loadCodeInfoById((String) edulistObj.get(i).get(5));
		dse.setDegreeCode(tempcodeinfo);
		dse.setDegreeName((String) edulistObj.get(i).get(6));
		tempcodeinfo=dictableService.loadCodeInfoById((String) edulistObj.get(i).get(7));
		dse.setStudyForm(tempcodeinfo);
		dse.setEductionalSystem(Long.parseLong((String) edulistObj.get(i).get(8)));
		dse.setGraduateDate(DateUtils.parseDate((String) edulistObj.get(i).get(9)));
		dse.setSchoolName((String) edulistObj.get(i).get(10));
		tempcodeinfo=dictableService.loadCodeInfoById((String) edulistObj.get(i).get(11));
		dse.setProfession(tempcodeinfo);
		dse.setProfessionName((String) edulistObj.get(i).get(12));
	}

	/** (non Javadoc) 
	 * @Title: getdraftServantImportRecordVOPage
	 * @Description: TODO
	 * @param hql
	 * @param listqueryparameter
	 * @param pageNo
	 * @param pagesize
	 * @return 
	 */
	@Override
	public Page<DraftServantImportRecordVO> getdraftServantImportRecordVOPage(String hql,
	        List<QueryParameter> listqueryparameter, Integer pageNo, Integer pagesize) {
		
		Page<DraftServantImportRecord> temppage=this.findByHQL(hql, listqueryparameter,pageNo, pagesize);
		List<DraftServantImportRecordVO> voList = new ArrayList<>();
		for(DraftServantImportRecord s:temppage.getResult()){
			DraftServantImportRecordVO vo = new DraftServantImportRecordVO(s);
			voList.add(vo);
		}
		Page<DraftServantImportRecordVO> page = new Page<>(temppage.getStart(), temppage.getCurrentPageSize(), temppage.getTotalSize(), temppage.getPageSize(), voList);
		return page;
	}

	/** (non Javadoc) 
	 * @Title: updaterecordandServantInfo
	 * @Description: TODO
	 * @param importId 
	 */
	@Override
	public void updaterecordandServantInfo(String importId,Integer recordYear) {
		DraftServantImportRecord draftServantImportRecord=load(importId);
		draftServantImportRecord.setYearTip(recordYear);
		draftServantImportRecord.setPublish(DraftServant.EMPLOY_PUBLISH);
		update(draftServantImportRecord);
		
		Map condMap=new HashMap();
		condMap.put("importRecordId", importId);
		List<DraftServant> dslist = draftServantService.findByCondMap(condMap);
		for (DraftServant draftServant : dslist) {
			draftServant.setYearTip(recordYear);
			draftServantService.update(draftServant);
		}
	}
	/**
	 * (non Javadoc) 
	 * @Title: removeDraftServantImportRecord
	 * @Description: 删除导入记录数据
	 * @param id 
	 * @see com.wondersgroup.human.service.ofcflow.DraftServantImportRecordService#removeDraftServantImportRecord(java.lang.String)
	 */
	public void removeDraftServantImportRecord(String id) {
		// 首先判断该记录是否发布
		DraftServantImportRecord record = load(id);
		if (record.getPublish() == 1)
			throw new BusinessException("该记录已发布，无法删除！");

		// 删除需要同学历子集和基础信息子集一同删除，首先获取基础信息集
		HashMap<String, Object> condMap = new HashMap();
		condMap.put("importRecordId", id);
		List<DraftServant> dslist = draftServantService.findByCondMap(condMap);
		if (!dslist.isEmpty()) {
			for (DraftServant draftServant : dslist) {
				// 根据基础信息ID 获取学历学位子集
				List<Predicate> filter = new ArrayList<>();// 查询条件
				Predicate p = new Predicate("draftServantId", Operator.EQ, draftServant.getId(), "");
				filter.add(p);
				List<DraftServantEduInfo> dselist = draftServantEduInfoService.findByFilter(filter);
				for (DraftServantEduInfo draftServantEduInfo : dselist) {
					draftServantEduInfoService.delete(draftServantEduInfo);
				}
				draftServantService.delete(draftServant);
			}
		}
		delete(record);
	}
	/**
	 * @Title: savePublish 
	 * @Description: 发布
	 * @param id
	 * @return: void
	 */
	public void savePublish(String id){
		DraftServantImportRecord record = load(id);
		if (record == null)
			throw new BusinessException("该记录ID不存在");
		draftServantService.savePublishByRecordId(id);
		record.setPublish(DraftServant.EMPLOY_PUBLISH);
		update(record);
		//修改此导入记录中人员的汇总状态
		this.draftServantImportRecordDAO.updateCollectStatusByImportId(id);
	}

	@Override
	public void updateCollectStatusByImportId(String id) {
		
	}
}
