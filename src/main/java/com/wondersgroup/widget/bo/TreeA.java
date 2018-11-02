package com.wondersgroup.widget.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wondersgroup.framework.controller.viewobject.GenericTreeVO;
import com.wondersgroup.framework.core.bo.GenericTreeEntity;

@Entity
@Table(name = "TREE_A")
public class TreeA extends GenericTreeEntity<TreeA> {
	
	@Override
	public GenericTreeVO getTarget() {
		
		return com.wondersgroup.widget.util.mapper.PropertyMapper.toMapper(this);
	}
	
}
