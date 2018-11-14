package com.wondersgroup.framework.controller.main;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.common.utils.FtpTool;
import com.wondersgroup.config.ReadProperties;
import com.wondersgroup.framework.controller.GenericController;

@Controller
@RequestMapping("ftp")
public class FtpController extends GenericController {
	
	@RequestMapping("/getImg")
	@ResponseBody
	public String getImg(String imgName) throws IOException{
		ServletOutputStream out = null;
		try {
			this.getResponse().setContentType("multipart/form-data");
			out = this.getResponse().getOutputStream();
			byte[] imgByte = FtpTool.ftpDownloadInByte(ReadProperties.getInstance().FTP_DIR_NAME_PHOTO, imgName);
			if(imgByte!=null){
				out.write(imgByte);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			out.close();
		}
		return null;
	}

//	@RequestMapping("download")  
//    public String downloadAmachment(String downloadUrl, String realFileName, HttpServletRequest request,  
//            HttpServletResponse response) {  
//        response.setContentType("text/html;charset=UTF-8");  
//        try {  
//        	String irealFileName=URLDecoder.decode(realFileName, "UTF-8");
//        	String filename=downloadUrl.replace(ReadProperties.getInstance().OFC_FILE_FTP_BASEURL, "");
//			byte[] ifile = FtpTool.ftpDownloadInByte(ReadProperties.getInstance().OFC_FILE_DIR_NAME, filename);
//			if (ifile != null) {
//				request.setCharacterEncoding("UTF-8");
//				BufferedOutputStream bos = null;
//				response.setContentType("application/octet-stream");
//				response.reset();// 清除response中的缓存
//				response.setHeader("Content-disposition",
//				        "attachment; filename=" + new String(irealFileName.getBytes("utf-8"), "ISO8859-1"));
//				response.setHeader("Content-Length", String.valueOf(ifile.length));
//				bos = new BufferedOutputStream(response.getOutputStream());// 构造输出流
//				byte[] buff = new byte[1024];
//				int bytesRead;
//				bos.write(ifile, 0, ifile.length);
//				response.flushBuffer();// 将所有的读取的流返回给客户端
//				bos.close();
//			}
//        } catch (IOException e) {  
//            return "";  
//        }  
//        return null;  
//    }  
}
