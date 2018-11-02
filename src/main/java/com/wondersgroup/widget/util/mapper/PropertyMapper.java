/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: PropertyMapper.java
 * 工程名: smart-core
 * 包名: com.wondersgroup.framework.util.mapper
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年5月8日 下午6:58:48
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年5月8日 下午6:58:48
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.widget.util.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.wondersgroup.framework.controller.viewobject.GenericTreeVO;
import com.wondersgroup.framework.dict.bo.CodeInfo;
import com.wondersgroup.widget.bo.TreeA;
import com.wondersgroup.widget.bo.TreeB;

/**
 * @ClassName: PropertyMapper
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年5月8日 下午6:58:48
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PropertyMapper {
	
	public static GenericTreeVO toMapper(TreeA object) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		PropertyMap<TreeA, GenericTreeVO> objectMap = new PropertyMap<TreeA, GenericTreeVO>() {
			
			@Override
			protected void configure() {
				
				map(source.getParent().getId(), destination.parentId);
			}
		};
		
		modelMapper.addMappings(objectMap);
		return modelMapper.map(object, GenericTreeVO.class);
	}
	
	public static GenericTreeVO toMapper(TreeB object) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		PropertyMap<TreeB, GenericTreeVO> objectMap = new PropertyMap<TreeB, GenericTreeVO>() {
			
			@Override
			protected void configure() {
				
				map(source.getParent().getId(), destination.parentId);
			}
		};
		
		modelMapper.addMappings(objectMap);
		return modelMapper.map(object, GenericTreeVO.class);
	}
}
