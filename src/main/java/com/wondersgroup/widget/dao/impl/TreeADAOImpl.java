package com.wondersgroup.widget.dao.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.announcement.bo.Announcement;
import com.wondersgroup.framework.core.dao.impl.GenericTreeRepositoryImpl;
import com.wondersgroup.widget.bo.TreeA;
import com.wondersgroup.widget.dao.TreeADAO;

@Repository
public class TreeADAOImpl extends GenericTreeRepositoryImpl<TreeA> implements TreeADAO {
	
	public Class<TreeA> getEntityClass() {
		
		return TreeA.class;
	}
}
