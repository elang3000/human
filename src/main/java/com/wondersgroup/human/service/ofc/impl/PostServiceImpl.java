/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: PostServiceImpl.java
 * 工程名: human
 * 包名: com.wondersgroup.human.service.ofc.impl
 * 描述: 人员信息子表-职务 服务实现类
 * 创建人: tzy
 * 创建时间: 2018年5月30日 下午5:37:47
 * 版本号: V1.0
 * 修改人：tzy
 * 修改时间：2018年5月30日 下午5:37:47
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.service.ofc.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wondersgroup.common.contant.DictTypeCodeContant;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.Sorts;
import com.wondersgroup.framework.core.dao.support.Predicate;
import com.wondersgroup.framework.core.service.impl.GenericServiceImpl;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.framework.dict.service.DictableService;
import com.wondersgroup.human.bo.ofc.Post;
import com.wondersgroup.human.bo.ofc.Servant;
import com.wondersgroup.human.repository.ofc.PostRepository;
import com.wondersgroup.human.service.ofc.PostService;
import com.wondersgroup.human.service.ofc.ServantService;
import com.wondersgroup.human.vo.ofc.PostVO;

/**
 * @ClassName: PostServiceImpl
 * @Description: 人员信息子表-职务 服务实现类
 * @author: tzy
 * @date: 2018年5月30日 下午5:37:47
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Service
public class PostServiceImpl extends GenericServiceImpl<Post> implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private DictableService dictableService;
	
	@Autowired
	private ServantService servantService;
	
	/**
	 * (non Javadoc)
	 * @Title: getPage
	 * @Description: 数据转换为VO的分页查询
	 * @param arg0 查询条件
	 * @param arg1 排序规则
	 * @param arg2 页码
	 * @param arg3 页大小
	 * @return
	 * @see com.wondersgroup.human.service.ofc.ServantService#getPage(java.util.List,
	 *      com.wondersgroup.framework.core.bo.Sorts, java.lang.Integer, java.lang.Integer)
	 */
	public Page<PostVO> getPage(List<Predicate> arg0, Sorts arg1, Integer arg2, Integer arg3) {
		
		Page<Post> postPage = postRepository.findByFilter(arg0, arg1, arg2, arg3);
		List<PostVO> voList = new ArrayList<>();
		for (Post s : postPage.getResult()) {
			PostVO vo = new PostVO(s);
			voList.add(vo);
		}
		Page<PostVO> page = new Page<>(postPage.getStart(), postPage.getCurrentPageSize(), postPage.getTotalSize(),
				postPage.getPageSize(), voList);
		return page;
	}
	
	@Override
	public void saveOrUpdate(Post entity) {
		
		// 若最高职务标识为1，则需要更新公务员中最高职务属性显示
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		CodeInfo noCodeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		Servant servant = servantService.get(entity.getServant().getId());
		
		if (entity.getHighestPostSign().getId().equals(yesCodeInfo.getId())) {
			// 重置该公务员下其他所有现任最高职务标识设为0
			this.executeRestAllTopPostFlag(entity.getServant().getId());
		} else if (entity.getHighestPostSign().getId().equals(noCodeInfo.getId())) {
			if(entity.getNowPostSign().getId().equals(yesCodeInfo.getId())&&StringUtils.isNotBlank(servant.getNowPostName()) && servant.getNowPostName().equals(entity.getPostName())){
				servant.setNowPostName(null);
				servant.setNowPostCode(null);
				servant.setNowPostAttribute(null);
				servantService.update(servant);
			}
		}
		
		if (entity.getNowPostSign().getId().equals(yesCodeInfo.getId())) {
			//如果既是现任职务，又是最高职务，则输出到A01的NowPost字段。现任最高职务。
			if (entity.getHighestPostSign().getId().equals(yesCodeInfo.getId())){
				// 修改公务员A01表中现任最高职务的输出显示
				servant.setNowPostName(entity.getPostName());
				servant.setNowPostCode(entity.getPostCode());
				servant.setNowPostAttribute(entity.getAttribute());
				servantService.update(servant);
			}
		} else if (entity.getNowPostSign().getId().equals(noCodeInfo.getId())) {
			// 如果该职务和公务员最高现任职务名称一致，选择否后，公务员信息表中现任最高职务设置为null
			if (entity.getHighestPostSign().getId().equals(yesCodeInfo.getId())&&StringUtils.isNotBlank(servant.getNowPostName()) && servant.getNowPostName().equals(entity.getPostName())) {
				servant.setNowPostName(null);
				servant.setNowPostCode(null);
				servant.setNowPostAttribute(null);
				servantService.update(servant);
			}
		}
		
		super.saveOrUpdate(entity);
	}
	
	@Override
	public void executeRestAllTopPostFlag(String servantId) {
		
		CodeInfo codeInfo = dictableService.getCodeInfoByCode("0", DictTypeCodeContant.CODE_TYPE_YESNO);
		postRepository.updateServantAllPostTopTipBySid(servantId, codeInfo);
	}
	
	@Override
	public void delete(Post entity) {
		
		// 如果删除的是现任最高职位的标识，更新公务员信息表中最高职位字段
		CodeInfo yesCodeInfo = dictableService.getCodeInfoByCode("1", DictTypeCodeContant.CODE_TYPE_YESNO);
		// 如果删除的是现任最高职位的标识，更新公务员信息表中现任最高职位字段
		if (entity.getNowPostSign().getId().equals(yesCodeInfo.getId())&&entity.getHighestPostSign().getId().equals(yesCodeInfo.getId())) {
			Servant servant = servantService.get(entity.getServant().getId());
			servant.setNowPostName(null);
			servant.setNowPostCode(null);
			servant.setNowPostAttribute(null);
			servantService.update(servant);
		}
		super.delete(entity);
	}

	/** 
	 * @see com.wondersgroup.human.service.ofc.PostService#getAllPost(java.lang.String) 
	 */
	@Override
	public List<Post> getAllPost(String id) {
		CodeInfo tenureCode = dictableService.getCodeInfoByCode("2", "DM007");
		
		DetachedCriteria detachedcriteria = DetachedCriteria.forClass(Post.class);
		DetachedCriteria s = detachedcriteria.createAlias("servant", "s");
		s.add(Restrictions.eq("s.id", id));
		detachedcriteria.add(Restrictions.eq("removed", false));
		detachedcriteria.add(Restrictions.eq("tenureStatus.id", tenureCode.getId()));
		
		List<Post> list = this.findByCriteria(detachedcriteria);
		
		return list;
	}
}
