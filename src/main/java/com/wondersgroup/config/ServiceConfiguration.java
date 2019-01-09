/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: ServiceConfiguration.java
 * 工程名: smart-core
 * 包名: com.wondersgroup.config
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年6月3日 下午11:21:03
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年6月3日 下午11:21:03
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wondersgroup.system.log.config.LogConfig;

/**
 * @ClassName: ServiceConfiguration
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年6月3日 下午11:21:03
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Configuration
@ComponentScan(basePackages = {
        "com.wondersgroup.framework.bpms", "com.wondersgroup.framework", "com.wondersgroup.**.human",
        "com.wondersgroup.**.system", "com.wondersgroup.**.listener", "com.activiti.service", "com.activiti.repository"
}, includeFilters = {
        @Filter(value = Repository.class), @Filter(value = Service.class), @Filter(value = Component.class)
}, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = Controller.class),
        @Filter(type = FilterType.ANNOTATION, value = RestController.class),
        @Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class),
        @Filter(type = FilterType.ANNOTATION, value = RestControllerAdvice.class),
        @Filter(type = FilterType.ANNOTATION, value = LogConfig.class)
})
public class ServiceConfiguration {
	
	@Bean()
	public ObjectMapper objectMapper() {
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}
}
