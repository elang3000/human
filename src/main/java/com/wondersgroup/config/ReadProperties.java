/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: ReadPropertice.java 
 * 工程名: human
 * 包名: com.wondersgroup.config 
 * 描述: 读取配置文件
 * 创建人: tzy   
 * 创建时间: 2018年5月8日 上午11:20:34 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年5月8日 上午11:20:34 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/** 
 * @ClassName: ReadPropertice 
 * @Description: 读取配置文件
 * @author: tzy
 * @date: 2018年5月8日 上午11:20:34
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Configuration
public class ReadProperties {
	
	static ReadProperties properties = null;

	public static ReadProperties getInstance() {

		if (properties == null) {
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			properties = wac.getBean(ReadProperties.class);
		}
		return properties;
	}
	
	@Value("#{mvc['ftp.url']}")
	public String FTP_URL;
	
	@Value("#{mvc['ftp.port']}")
	public String FTP_PORT;
	
	@Value("#{mvc['ftp.username']}")
	public String FTP_USERNAME;
	
	@Value("#{mvc['ftp.password']}")
	public String FTP_PASSWORD;
	
	@Value("#{mvc['ftp.dirName.photo']}")
	public String FTP_DIR_NAME_PHOTO;
	
	@Value("#{mvc['ftp.dirName.material']}")
	public String FTP_DIR_NAME_MATERIAL;
	
}
