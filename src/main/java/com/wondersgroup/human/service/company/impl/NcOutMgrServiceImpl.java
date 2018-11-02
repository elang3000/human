package com.wondersgroup.human.service.company.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.DepartmentService;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.human.bo.company.CyOutMgr;
import com.wondersgroup.human.bo.company.NationalCompany;
import com.wondersgroup.human.bo.socialworker.SrRewardAndPunish;
import com.wondersgroup.human.repository.company.NcOutMgrRepository;
import com.wondersgroup.human.service.company.NationalCompanyService;
import com.wondersgroup.human.service.company.NcOutMgrService;
import com.wondersgroup.human.util.DateFormat;
import com.wondersgroup.human.vo.company.NcOutMgrVo;



@Service
public class NcOutMgrServiceImpl extends GenericServiceImpl<CyOutMgr> implements NcOutMgrService{

	@Autowired
	private NationalCompanyService nctionalCompanyService;
	
	@Autowired
	private NcOutMgrRepository ncOutMgrRepository;
	
	@Autowired
	private CodeInfoService codeInfoService;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private OrganNodeService organNodeService;
	
	
	/*
	 *  @Description: 数据转换为VO的分页查询
	 * @param arg0	查询条件
	 * @param arg1	排序规则
	 * @param arg2	页码
	 * @param arg3	页大小
	 * (non-Javadoc)
	 * @see com.wondersgroup.human.service.company.NcOutMgrService#getPage(java.util.List, com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	
	@Override
	public Page<NcOutMgrVo> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		Page<CyOutMgr> cyPage=ncOutMgrRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<NcOutMgrVo> voList=new ArrayList<>();
		for (CyOutMgr cy : cyPage.getResult()) {
			NcOutMgrVo vo= new NcOutMgrVo(cy);
			voList.add(vo);
		}
		
		Page<NcOutMgrVo> page = new Page<>(cyPage.getStart(), cyPage.getCurrentPageSize(), cyPage.getTotalSize(), cyPage.getPageSize(), voList);
		return page;
	}

	@Override
	public void saveBatchDraftSocialWorker(List<NationalCompany> list) {

		for (NationalCompany nationalCompany : list) {
			nctionalCompanyService.save(nationalCompany);
		}
	}


	@Override
	public String saveImportRecord(MultipartFile file, int year) throws Exception {
		InputStream in=null;
		
		if(file.isEmpty()){
			throw new BusinessException("文件不存在");
		}
		
		List<List<Object>> listObj=null;
		List<List<Object>> edulistObj=null;
		 try {
			in=file.getInputStream();
			listObj=new ImportExcelUtil().getListByExcel(in, file.getOriginalFilename(), 1);
			if(listObj.size() <= 1)
				throw new BusinessException("文件中人员信息基本集数据不存在!");
			
			
			List<NationalCompany> dsList = new ArrayList<>();
			Map<Integer, String> noEntryMap =new LinkedHashMap<>();
			for (int i = 2; i < listObj.size(); i++) {
				if((listObj.get(i)).size() <= 1)
					continue;
				NationalCompany nationalCompany = new NationalCompany();
				//设置值
				nationalCompany.setName(listObj.get(i).get(1).toString());
				//性别
				List<CodeInfo> sex = dictableService.findCodeInfoByName(DictTypeCodeContant.CODE_TYPE_SEX, listObj.get(i).get(2).toString().trim());
				if(sex != null && sex.size() >0){
					nationalCompany.setSex(sex.get(0));
				}
				
				//出生日期
				Date birthTime =DateFormat.parseToDateFormat(listObj.get(i).get(3).toString(), "yyyy-MM-dd");
				nationalCompany.setBirthDate(birthTime);
				//在职
				CodeInfo isOnHold =dictableService.getCodeInfoByCode("1", "DM200");
				nationalCompany.setIsOnHold(isOnHold);
				
				//政治面貌
				CodeInfo typePart = dictableService.findCodeInfoByAlias(DictTypeCodeContant.CODE_TYPE_PARTY, 
						(String) listObj.get(i).get(5));
				if(typePart != null){
					nationalCompany.setPolitics(typePart);
				}
				
				//学历
				nationalCompany.setTopEducation(listObj.get(i).get(6).toString());
				//学位
				nationalCompany.setTopDegree((String) listObj.get(i).get(7));
				//单位
				String orgName = (String) listObj.get(i).get(8);
				if (StringUtils.isNotBlank(orgName)) {
					orgName = orgName.trim();
				}
				nationalCompany.setDepartName(orgName);
				//现任职务
				nationalCompany.setNowPostName((String) listObj.get(i).get(10));
				//类型,用人员类别区分
				if(StringUtils.isNotBlank((String) listObj.get(i).get(11))){
					CodeInfo typemember = dictableService.findCodeInfoByAlias(DictTypeCodeContant.CODE_TYPE_MEMBER_TYPE, listObj.get(i).get(11).toString().trim());
					if(typemember != null){
						CodeInfo parentTypemember = typemember.getParent();
						if (parentTypemember != null) {
							nationalCompany.setPersonType(parentTypemember);
						}else {
							nationalCompany.setPersonType(typemember);
						}
					}
				}
				//身份证
				nationalCompany.setCardNo((String) listObj.get(i).get(12));
				/*//国企职工单位
				DetachedCriteria detachedCriteria =DetachedCriteria.forClass(OrganNode.class);
				detachedCriteria.add(Restrictions.eq("name", orgName));
				List<OrganNode> organNodeList = organNodeService.findByCriteria(detachedCriteria);
                if (organNodeList != null && organNodeList.size() > 0) {
					nationalCompany.setDepartId(organNodeList.get(0).getId());
				}*/
				
				dsList.add(nationalCompany);
			}	
			this.saveBatchDraftSocialWorker(dsList);
		}catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("文件读取错误,请联系管理员!");

		}finally {
			if(in != null){
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
			
		 return "success";
		
	}
	
	}
		 

