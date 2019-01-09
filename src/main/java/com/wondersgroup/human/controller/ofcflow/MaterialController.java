/**   
 * Copyright © 2018 . All rights reserved.万达信息股份有限公司
 * 
 * 文件名: MaterialController.java 
 * 工程名: human
 * 包名: com.wondersgroup.human.controller.ofcflow 
 * 描述: 材料 控制器
 * 创建人: tzy   
 * 创建时间: 2018年12月26日 上午10:49:00 
 * 版本号: V1.0
 * 修改人：tzy 
 * 修改时间：2018年12月26日 上午10:49:00 
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.controller.ofcflow;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.config.ReadProperties;
import com.wondersgroup.framework.controller.GenericController;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.util.StringUtils;
import com.wondersgroup.human.bo.ofcflow.Material;
import com.wondersgroup.human.service.ofcflow.MaterialService;
import com.wondersgroup.human.util.ExcelUtilsPOI;
import com.wondersgroup.human.util.ZipUtil;
import com.wondersgroup.human.vo.ofcflow.MaterialVO;
import com.wondersgroup.system.log.annotation.Log;
import com.wondersgroup.system.log.conts.BusinessType;
import com.wondersgroup.system.log.conts.OperatorType;

/** 
 * @ClassName: MaterialController 
 * @Description: 材料 控制器
 * @author: tzy
 * @date: 2018年12月26日 上午10:49:00
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
@Controller
@RequestMapping("ofcflow/material")
public class MaterialController extends GenericController{
	
	private final static String MATERIAL_INDEX = "models/ofcflow/material/index";
			
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping("index")
	public String getMaterialIndex(String busId, Model model) {
		StringBuffer sb = new StringBuffer("[");
		sb.append("{'value':'同类别转入','key':'ZhuanRenTLBInto'}");
		sb.append(",{'value':'职务变动','key':'JobShift'}");
		sb.append(",{'value':'辞职','key':'ResignServant'}");
		sb.append(",{'value':'参公交流转入','key':'ReferenceExchangeInto'}");
		sb.append(",{'value':'参公交流转出','key':'ReferenceExchangeOUT'}");
		sb.append("]");
		
		model.addAttribute("busTypeList", sb.toString());//业务类型
		return MATERIAL_INDEX;
	}
	
	/**
	 * @Title: pageList
	 * @Description: 文档下载列表
	 * @param servant 查询条件
	 * @param limit 页大小
	 * @param page 页码
	 * @return: Page<MaterialVO>
	 */
	@Log(title = "查询文档下载列表", operatorType = OperatorType.BUSINESS, businessType = BusinessType.QUERY,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/pageList")
	public Page<MaterialVO> pageList(Material material, Integer limit, Integer page,String type) {
		Page<MaterialVO> pageInfo = materialService.getPage(material, page, limit);
		return pageInfo;
	}
	
	/**
	 * @Title: downLoad
	 * @Description: 下载材料
	 * @param request
	 * @return
	 */
	@Log(title = "导出文档", operatorType = OperatorType.BUSINESS, businessType = BusinessType.EXPORT,
		     isSaveRequestData = true)
	@ResponseBody
	@RequestMapping("/downLoad")
	public void downLoad(String id,String name,HttpServletRequest request, HttpServletResponse response) {
		if(StringUtils.isBlank(id)){
			throw new BusinessException("请选择下载的材料！");
		}
		if(StringUtils.isBlank(name)){
			throw new BusinessException("文件名称不能为空！");
		}
		String savePath = request.getSession().getServletContext().getRealPath("/");//项目根路径
		String materialPath = savePath+"\\WEB-INF\\"+ReadProperties.getInstance().FTP_DIR_NAME_MATERIAL;//材料路径，判断是否存在，不存在则生成，材料根目录生成之后不删除
		File materialFolder = new File(materialPath);
		if(!materialFolder.exists()){
			materialFolder.mkdirs();
		}
		
		String tempPath = materialPath+"\\"+name;//待压缩文件夹，用于此次下载存放数据，压缩下载成功后删除
		File folder = new File(tempPath);
		if(!folder.exists()){
			folder.mkdirs();
		}
		try {
			List<String> idList = new ArrayList<>();
			for(String i : id.split(",")){
				idList.add(i);
			}
			DetachedCriteria d = DetachedCriteria.forClass(Material.class);
			d.add(Restrictions.in("busId", idList));
			d.add(Restrictions.eq("removed", false));
			List<Material> list = materialService.findByCriteria(d);//查询出需要下载的材料
			if(list==null||list.size()==0){
				throw new BusinessException("没有找到材料！");
			}
			for(Material m : list){
				String fileName = m.getFtpFileName();
				if(fileName.contains(":")){
					String personPath = tempPath+"\\"+m.getFolderName();//个人文件夹  用文件夹名称字段作为文件夹名称
					File personFolder = new File(personPath);
					if(!personFolder.exists()){
						personFolder.mkdirs();
					}
					String[] fileNameArr = fileName.split(":");
					for(int i=0;i<fileNameArr.length;i++){//生成个人文件夹 下的文件
						String f = fileNameArr[i];
						byte[] bytes = FtpTool.ftpDownloadInByte(m.getFtpFilefolder(), f);
						getFile(bytes, personPath, m.getName().split(":")[i]);
					}
				}else{//生成个人文件
					byte[] bytes = FtpTool.ftpDownloadInByte(m.getFtpFilefolder(), fileName);
					getFile(bytes, tempPath, m.getName());
				}
			}
			ZipUtil.zip(tempPath+".zip", tempPath, null);//将文件打包
			zipdown(tempPath+".zip", name+".zip", request, response);//写出压缩包并删除
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(folder.exists()){
				try {
					FileUtils.deleteDirectory(folder);//删除非空文件夹
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	 /** 
     * 根据byte数组，生成文件 
     */  
    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    }
    /***
	 * zip文件下载
	 * @param tempurl缓存目录
	 * @param fileName 下载名
	 */
	public static void zipdown(String tempurl,String fileName,HttpServletRequest request,HttpServletResponse response){
		File file =  new File(tempurl);
		//输出下载
		if(file!=null){
			 BufferedInputStream buff=null;
			 OutputStream myout=null;
			 try {
				 	response.setContentType("text/html; charset=UTF-8");
			        response.setContentType("application/x-zip-compressed");//设置response的编码方式
			        response.setContentLength((int)file.length());//写明要下载的文件的大小
			        //处理ie乱码 
					// 解决中文文件名乱码问题---兼容IE
			        String agent = request.getHeader("User-Agent");
					boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
					
					response.setHeader("Content-Disposition", "attachment;filename=" + ExcelUtilsPOI.toUtf8String(isMSIE, fileName) ); 
			        FileInputStream fis=new FileInputStream(file); //读出文件到i/o流
			        buff=new BufferedInputStream(fis);
			        byte [] b=new byte[1024];//相当于我们的缓存
			        long k=0;//该值用于计算当前实际下载了多少字节
			        myout=response.getOutputStream();//从response对象中得到输出流,准备下载
			        while(k<file.length()){
			            int j=buff.read(b,0,1024);
			            k+=j;
			            myout.write(b,0,j);//将b中的数据写到客户端的内存
			        }
			        myout.flush();//将写入到客户端的内存的数据,刷新到磁盘
			        
					//jsonData = "{success:true,msg:\"生成成功!\"}";
			} catch (FileNotFoundException e) {
				//System.out.println("-------------->要下载的文件没有找到！");
			} catch (IOException e) {
				//System.out.println("-------------->下载异常！");
			}finally{
				try {
					if(myout!=null)
						myout.close();
					if(buff!=null)
						buff.close();
					if(file.delete()){
						//System.out.println("-------------->文件删除成功！");
					}
				} catch (IOException e) {
					//System.out.println("-------------->关闭流异常！");
				}
			}
		}
	}
}
