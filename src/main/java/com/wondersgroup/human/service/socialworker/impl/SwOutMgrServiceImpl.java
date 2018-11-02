/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: SocialWorkerServiceImpl.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.service.impl 
 * 描述: 人员信息维护服务接口实现类
 * 创建人: lyf   
 * 创建时间: 2018年8月22日 上午11:05:33 
 * 版本号: V1.0
 * 修改人：lyf
 * 修改时间：2018年8月22日 上午11:05:33 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.socialworker.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.common.utils.ImportExcelUtil;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.CodeInfoService;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.socialworker.SrOutMgr;
import com.wondersgroup.human.bo.socialworker.SocialWorker;
import com.wondersgroup.human.repository.socialworker.SwOutMgrRepository;
import com.wondersgroup.human.service.socialworker.SocialWorkerService;
import com.wondersgroup.human.service.socialworker.SwOutMgrService;
import com.wondersgroup.human.util.DateFormat;
import com.wondersgroup.human.vo.socialworker.OutMgrVO;

/** 
 * @ClassName: SocialWorkerServiceImpl 
 * @Description: 人员信息维护服务接口实现类
 * @author: lyf
 * @date: 2018年5月21日 上午11:05:33
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Service
public class SwOutMgrServiceImpl extends GenericServiceImpl<SrOutMgr> implements SwOutMgrService{
	
	@Autowired
	private SwOutMgrRepository swOutMgrRepository;
	
	@Autowired
	private SocialWorkerService socialWorkerService;
	
	@Autowired
	private CodeInfoService  codeInfoService;
	
	@Autowired
	private DictableService dictableService;
	
	
	
	/**
	 * (non Javadoc) 
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * @return 
	 * @see com.wondersgroup.human.service.ofc.SocialWorkerService#getPage(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<OutMgrVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3){
		Page<SrOutMgr> socialWorkerPage = swOutMgrRepository.findByFilter(arg0,arg1,arg2,arg3);
		List<OutMgrVO> voList = new ArrayList<>();
		for(SrOutMgr p:socialWorkerPage.getResult()){
			OutMgrVO vo = new OutMgrVO(p);
			voList.add(vo);
		}
		Page<OutMgrVO> page = new Page<>(socialWorkerPage.getStart(), socialWorkerPage.getCurrentPageSize(), socialWorkerPage.getTotalSize(), socialWorkerPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public void saveBatchDraftSocialWorker(List<SocialWorker> list) {
		for (SocialWorker socialWorker : list) {
			socialWorkerService.save(socialWorker);
		}
	}

	@Override
	public String saveImportRecord(MultipartFile file, int year) throws Exception {
		InputStream in = null;
		if (file.isEmpty()) {
			throw new BusinessException("文件不存在！");
		}

		// 读取excel文件 保存拟录用公务员名单
		List<List<Object>> listObj = null;
		List<List<Object>> edulistObj = null;
		try {
			in = file.getInputStream();
			listObj = new ImportExcelUtil().getListByExcel(in, file.getOriginalFilename(), 1);
			if (listObj.size() <= 1)
				throw new BusinessException("文件中人员信息基本集数据不存在！");
			List<SocialWorker> dsList = new ArrayList<>();
			Map<Integer, String> noEntryMap=new LinkedHashMap<>();
			for (int i = 2; i < listObj.size(); i++) {
				if ((listObj.get(i)).size() <= 1)
					continue;
				SocialWorker socialWorker = new SocialWorker();
				//设置值
				socialWorker.setName(listObj.get(i).get(1).toString());
				
				//性別
				List<CodeInfo> sex = dictableService.findCodeInfoByName(DictTypeCodeContant.CODE_TYPE_SEX, listObj.get(i).get(2).toString().trim()); 
				if (sex != null && sex.size() > 0) {
					socialWorker.setSex(sex.get(0));
				}
				
				//出生日期
				Date birthTime = DateFormat.parseToDateFormat(listObj.get(i).get(3).toString(), "yyyy-MM-dd");
				socialWorker.setBirthDate(birthTime);
				
				dictableService.findCodeInfoByAlias("", "");
				//在职
				CodeInfo isOnHold = dictableService.getCodeInfoByCode("1", "DM200");//在职CODE
				socialWorker.setIsOnHold(isOnHold);
				
				
				//政治面貌
				CodeInfo typePart = dictableService.findCodeInfoByAlias(DictTypeCodeContant.CODE_TYPE_PARTY, 
						(String) listObj.get(i).get(5));
				if (typePart != null) {
					//System.out.println(typePart.getName());
					socialWorker.setPolitics(typePart);
				}
				
				//学历
				socialWorker.setTopEducation(listObj.get(i).get(6).toString());
				
				//学位
				socialWorker.setTopDegree((String) listObj.get(i).get(7));
				
				//单位
				socialWorker.setDepartName((String)listObj.get(i).get(8));
				
				//地址
				
				//现任职务  nowPostName nowPostCode
				socialWorker.setNowPostName((String) listObj.get(i).get(10));
				
				//类型  用人员类别区分  自己维护
				if (StringUtils.isNoneBlank((String) listObj.get(i).get(11))) {
					String personType = isAttruInfo((String) listObj.get(i).get(11));
					CodeInfo typemember = dictableService.findCodeInfoByAlias(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, personType);
					if (typemember != null) {
						socialWorker.setPersonType(typemember);
					}
				}
				
				dsList.add(socialWorker);
			}
			
			this.saveBatchDraftSocialWorker(dsList);

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
		
		return "success";
	}
	
	
	public String isAttruInfo(String info){
		if (StringUtils.isNotBlank(info) && "社区工作者".equals(info.trim())) {
			return "社工人员";
		}
		return "";
	}

}
