package com.wondersgroup.widget.dao.impl;

import org.springframework.stereotype.Repository;

import com.wondersgroup.framework.core.dao.impl.GenericTreeRepositoryImpl;
import com.wondersgroup.widget.bo.TreeA;
import com.wondersgroup.widget.bo.TreeB;
import com.wondersgroup.widget.dao.TreeBDAO;

@Repository
public class TreeBDAOImpl extends GenericTreeRepositoryImpl<TreeB> implements TreeBDAO {
	
	public Class<TreeB> getEntityClass() {
		
		return TreeB.class;
	}
}
