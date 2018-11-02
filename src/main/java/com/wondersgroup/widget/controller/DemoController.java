/**
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 文件名: DemoController.java
 * 工程名: human
 * 包名: com.wondersgroup.widget.controller
 * 描述: TODO
 * 创建人: Wonders-Rain
 * 创建时间: 2018年4月23日 下午5:34:02
 * 版本号: V1.0
 * 修改人：Wonders-Rain
 * 修改时间：2018年4月23日 下午5:34:02
 * 修改任务号
 * 修改内容：TODO
 */

package com.wondersgroup.widget.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.config.ReadProperties;
import com.wondersgroup.framework.controller.AjaxResult;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.security.service.UserService;

import net.sf.json.JSONArray;

/**
 * @ClassName: DemoController
 * @Description: TODO
 * @author: Wonders-Rain
 * @date: 2018年4月23日 下午5:34:02
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
@Controller
public class DemoController extends GenericController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("demo")
	public String demo() {
		
		return "demo";
	}
	
	@RequestMapping("demoForm")
	public String demoForm(Model model) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("path", "ftp://10.10.10.42/document/017b8b7a-55a3-488f-8c3a-7d12f1fc321e.png");
		map.put("name", "wKgBs1cplHaAbGZMAAXNP1-FO3s068.png");
		list.add(map);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("path", "ftp://10.10.10.42/document/049a57bc-310d-4331-8ea6-12f2adb56367.png");
		map2.put("name", "wKgBs1csntSAPRONAAjlWSGelMs800.png");
		list.add(map2);
		model.addAttribute("uploadData", JSONArray.fromObject(list).toString());
		List<Map<String,Object>> dict = new ArrayList<>();
		for(int i=0;i<5;i++){
			Map<String,Object> map3 = new HashMap<>();
			map3.put("key", i);
			map3.put("value", "数据"+i);
			dict.add(map3);
		}
		model.addAttribute("selectData", JSONArray.fromObject(dict).toString());
		
		return "demoForm";
	}
	
	@RequestMapping("table")
	public String table() {
		
		return "table";
	}
	/**
	 * @Title: upload 
	 * @Description: 文件上传 保存到ftp服务器 
	 * @param multipartRequest
	 * @return 文件ftp路径（path）和真实文件名（name）
	 * @return: Map<String,String>
	 */
	@RequestMapping("upload")
	@ResponseBody
	public Map<String,String> upload(MultipartHttpServletRequest multipartRequest) {
		Map<String,String> data = new HashMap<String,String>();//存放文件在ftp服务器的路径和文件名称，code为1时文件保存成功
		try{
			String field = multipartRequest.getParameter("field");//数据input的name值，用以获取文件
			String folder = multipartRequest.getParameter("folder");//上传到ftp服务器的文件夹名称
			if(field==null||"".equals(field)||"undefined".equals(field)){//layui的默认值file
				field="file";
			}
			if(folder==null||"".equals(folder)||"undefined".equals(folder)){//默认值document
				folder="document";
			}
			MultipartFile file = multipartRequest.getFile(field);
			if(file!=null && file.getSize()>0){
				//保存文件到ftp服务器
				String uuid = UUID.randomUUID().toString();
				String fileName = uuid+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				FtpTool.ftpUpload(folder, file.getBytes(), fileName);
				data.put("path", "ftp://"+ReadProperties.getInstance().FTP_URL+"/"+folder+"/"+fileName);
				data.put("name", file.getOriginalFilename());
				data.put("code", "1");
			}
		}catch(Exception e){
			data.put("code", "0");
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * @Title: ftpdelete 
	 * @Description: 根据path删除ftp服务器上的文件
	 * @param multipartRequest
	 * @return 文件ftp路径（path）和真实文件名（name）
	 * @return: String
	 */
	@RequestMapping("ftpdelete")
	@ResponseBody
	public AjaxResult ftpdelete(String path) {
		AjaxResult data = new AjaxResult(false);//(false,AjaxResult.MESSAGE_ERROR_TYPE,"删除失败！");//存放文件在ftp服务器的路径和文件名称，success=true 删除成功
		try{
			if(path!=null&&!"".equals(path)){
				boolean r = FtpTool.ftpDelete(path.substring(path.indexOf("/", 6)));
				if(r){
					data.setSuccess(true);
					data.setMessage("删除成功！");
				}
			}
		}catch(Exception e){
			data.setSuccess(false);
			data.setMessage("删除失败！");
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * 
	 * @Title: downloadAmachment 
	 * @Description: ftp文件下载
	 * @param downloadUrl 文件ftp路径
	 * @param realFileName	文件真实名称
	 * @return 文件
	 */
	@RequestMapping("download")  
    public String downloadAmachment(String downloadUrl, String realFileName, HttpServletRequest request,  
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String folder = request.getParameter("folder");//上传到ftp服务器的文件夹名称
		if(folder==null||"".equals(folder)||"undefined".equals(folder)){//默认值document
			folder="document";
		}
        try {  
        	String irealFileName=URLDecoder.decode(realFileName, "UTF-8");
			byte[] ifile = FtpTool.ftpDownloadInByte(folder, downloadUrl.substring(downloadUrl.lastIndexOf("/")+1));
			if (ifile != null) {
				request.setCharacterEncoding("UTF-8");
				BufferedOutputStream bos = null;
				response.setContentType("application/octet-stream");
				response.reset();// 清除response中的缓存
				response.setHeader("Content-disposition",
				        "attachment; filename=" + new String(irealFileName.getBytes("utf-8"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(ifile.length));
				bos = new BufferedOutputStream(response.getOutputStream());// 构造输出流
				bos.write(ifile, 0, ifile.length);
				response.flushBuffer();// 将所有的读取的流返回给客户端
				bos.close();
			}
        } catch (IOException e) {
        	e.printStackTrace();
            return "";  
        }  
        return null;  
    }
	/**
	 * 
	 * @Title: previewImg 
	 * @Description: ftp服务器图片回显
	 * @param downloadUrl 文件ftp路径
	 * @param realFileName	文件真实名称
	 * @return 文件
	 */
	@RequestMapping("previewImg")
	public String previewImg(String downloadUrl,HttpServletRequest request, HttpServletResponse response) {
		String folder = request.getParameter("folder");//上传到ftp服务器的文件夹名称
		if(folder==null||"".equals(folder)||"undefined".equals(folder)){//默认值document
			folder="document";
		}
		try {
			byte[] ifile = FtpTool.ftpDownloadInByte(folder, downloadUrl.substring(downloadUrl.lastIndexOf("/")+1));
			if (ifile != null) {
				request.setCharacterEncoding("UTF-8");
				BufferedOutputStream bos = null;
				response.setHeader("Content-Type","image/jped");
				bos = new BufferedOutputStream(response.getOutputStream());// 构造输出流
				bos.write(ifile, 0, ifile.length);
				response.flushBuffer();// 将所有的读取的流返回给客户端
				bos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "";  
		}  
		return null;  
	}
	
	/*@RequestMapping("user/query")
	@ResponseBody
	public ResultCollection<SecurityUser> userQuery(SecurityUser securityUser, Integer page, Integer limit) {
		
		Map<String, Object> filter = new HashMap<String, Object>();
		Map<String, String> sort = new HashMap<String, String>();
		
		Page<SecurityUser> user = userService.getAllUsersByPage(filter, sort, page, limit);
		
		return collection(user, null, null);
	}*/
}
