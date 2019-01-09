/**
 * Copyright © 2017 . All rights reserved.万达信息股份有限公司
 * 文件名: FtpTool.java
 * 工程名: FtpDemo
 * 包名: com.wondersgroup.gwy.common.util
 * 描述: FTP使用工具，主要是完成已FILE、BYTE等方式的上传、下载任务
 * 创建人: Wonder-Rain
 * 创建时间: 2017年8月25日 下午9:44:45
 * 版本号: V1.0
 * 修改人：Wonder-Rain
 * 修改时间：2017年8月25日 下午9:44:45
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wondersgroup.config.ReadProperties;

/**
 * @ClassName: FtpTool
 * @Description: FTP工具类
 * @author: Wonder-Rain
 * @date: 2017年8月25日 下午9:44:45
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class FtpTool {
	
	private static Logger logger = LoggerFactory.getLogger(FtpTool.class);
	
	private static FTPClient generateFtpClient() throws Exception {
		
		logger.debug("开始创建FTP服务器！");
		FTPClient ftp = new FTPClient();
		ftp.setConnectTimeout(1000 * 10);
		ftp.setCharset(Charset.defaultCharset());
		
		ftp.connect(ReadProperties.getInstance().FTP_URL,
				Integer.parseInt(ReadProperties.getInstance().FTP_PORT));
		ftp.login(ReadProperties.getInstance().FTP_USERNAME,
				ReadProperties.getInstance().FTP_PASSWORD);
		
		/*
		 * ftp.connect("www.tigeryy.com", 22);
		 * ftp.login("tempuser", "wonders,1");
		 */
		int reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			logger.error("FTP服务器响应错误！错误代码：" + reply);
			throw new Exception("FTP服务器响应错误！错误代码：" + reply);
		}
		logger.debug("创建FTP服务器成功！");
		return ftp;
	}
	
	/**
	 * @Title: ftpDownload
	 * @Description: ftp文件下载方法
	 * @param path ftp文件所在路径
	 * @param filename ftp服务器中的文件名称
	 * @param newfilename 新文件路径
	 * @throws Exception
	 * @return: Boolean 下载执行是否成功
	 */
	public static Boolean ftpDownload(String path, String filename, String newfilename) {
		
		FTPClient ftp = null;
		FileOutputStream fos = null;
		Boolean result = false;
		try {
			ftp = generateFtpClient();
			ftp.setControlEncoding("UTF-8");
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.changeWorkingDirectory(path);
			
			fos = new FileOutputStream(newfilename);
			
			logger.debug("开始下载文件：" + filename + ",下载后文件为：" + newfilename);
			
			result = ftp.retrieveFile(filename, fos);
			
		} catch (Exception ex) {
			logger.error("从FTP下载文件失败：", ex);
		} finally {
			cleanFtp(ftp, null, null, null, fos);
		}
		return result;
	}
	
	/**
	 * @Title: ftpDownloadInFile
	 * @Description: 从FTP下载文件，并以文件对象方式返回
	 * @param path FTP中文件路径
	 * @param filename ftp中的文件名称
	 * @param newfilename 新下载后的ftp下载名称
	 * @return 下载成功后的文件对象
	 * @return: File
	 */
	public static File ftpDownloadInFile(String path, String filename, String newfilename) {
		
		File downloadFile = null;
		FTPClient ftp = null;
		FileOutputStream fos = null;
		try {
			ftp = generateFtpClient();
			ftp.setControlEncoding("UTF-8");
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.changeWorkingDirectory(path);
			
			fos = new FileOutputStream(newfilename);
			logger.debug("开始下载文件：" + filename + ",下载后文件为：" + newfilename);
			if (ftp.retrieveFile(filename, fos)) {
				downloadFile = new File(newfilename);
			}
		} catch (Exception ex) {
			logger.error("FTP文件下载异常：" + ex);
		} finally {
			cleanFtp(ftp, null, null, null, fos);
		}
		return downloadFile;
	}
	
	/**
	 * @Title: ftpDownloadInByte
	 * @Description: 从FTP服务器中下载文件，并已BYTE返回
	 * @param path 所下载的FTP文件路径
	 * @param filename 下载的文件名称
	 * @return
	 * @return: byte[]
	 */
	public static byte[] ftpDownloadInByte(String path, String filename) {
		
		FTPClient ftp = null;
		byte[] buffer = null;
		ByteArrayOutputStream bos = null;
		try {
			ftp = generateFtpClient();
			ftp.setControlEncoding("UTF-8");
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.changeWorkingDirectory(path);
			
			bos = new ByteArrayOutputStream();
			logger.debug("开始下载文件:" + filename);
			if (ftp.retrieveFile(filename, bos)) {
				buffer = bos.toByteArray();
				logger.debug("开始完成:BYTE大小：" + buffer.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("从FTP下载文件失败：", e);
		} finally {
			cleanFtp(ftp, null, bos, null, null);
		}
		return buffer;
	}
	
	/**
	 * @Title: ftpUpload
	 * @Description: 已FILE对象方式上传文件至FTP服务器
	 * @param path 上传至FTP路径
	 * @param file 用于上传的FTP文件对象
	 * @param newfilename 在FTP保存的文件名称
	 * @return: Boolean 上传操作是否成功
	 */
	public static Boolean ftpUpload(String path, File file, String newfilename) {
		
		FTPClient ftp = null;
		FileInputStream fis = null;
		Boolean result = false;
		try {
			ftp = generateFtpClient();
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(path);
			fis = new FileInputStream(file);
			logger.debug("开始保存文件:" + newfilename);
			result = ftp.storeFile(newfilename, fis);
			logger.debug("保存文件:" + (result ? "成功" : "失败"));
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("向FTP服务器上传文件失败：", ex);
		} finally {
			cleanFtp(ftp, null, null, fis, null);
		}
		return result;
	}
	
	/**
	 * @Title: ftpUpload
	 * @Description: 已BYTE对象方式上传文件至FTP服务器
	 * @param path 上传至FTP路径
	 * @param blob 用于上传的FTP文件BYTE
	 * @param newfilename 在FTP保存的文件名称
	 * @return: Boolean 上传操作是否成功
	 */
	public static Boolean ftpUpload(String path, byte[] blob, String newfilename) {
		
		FTPClient ftp = null;
		ByteArrayInputStream bis = null;
		Boolean result = false;
		try {
			ftp = generateFtpClient();
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory(path);
			bis = new ByteArrayInputStream(blob);
			logger.debug("开始保存文件：" + newfilename);
			result = ftp.storeFile(newfilename, bis);
			logger.debug("保存文件:" + (result ? "成功" : "失败"));
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("向FTP服务器上传文件失败：", ex);
		} finally {
			cleanFtp(ftp, bis, null, null, null);
		}
		return result;
	}
	/**
	 * @Title: ftpUpload
	 * @Description: 根据文件路径删除ftp中文件
	 * @param path 上传至FTP路径
	 * @return: Boolean 删除操作是否成功
	 */
	public static Boolean ftpDelete(String path) {
		
		FTPClient ftp = null;
		Boolean result = false;
		try {
			ftp = generateFtpClient();
			logger.debug("开始删除文件：" + path);
			result = ftp.deleteFile(path);
			logger.info("删除文件:" + (result ? "成功" : "失败"));
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("向FTP服务器删除文件失败：", ex);
		} finally {
			cleanFtp(ftp, null, null, null, null);
		}
		return result;
	}
	
	/**
	 * @Title: getBytes
	 * @Description: FILT TO BYTE[] 转换
	 * @param file
	 * @return
	 * @return: byte[]
	 */
	public static byte[] getBytes(File file) {
		
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			buffer = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer;
	}
	
	/**
	 * @Title: cleanFtp
	 * @Description: 清理在FTP使用过程当中的客户端和IO流
	 * @param ftp FTP客户端
	 * @param bis ByteArrayInputStream
	 * @param bos ByteArrayOutputStream
	 * @param fis FileInputStream
	 * @param fos FileOutputStream
	 * @return: void
	 */
	private static void cleanFtp(FTPClient ftp, ByteArrayInputStream bis, ByteArrayOutputStream bos,
			FileInputStream fis, FileOutputStream fos) {
		
		if (bis != null) {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (bos != null) {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (ftp != null) {
			try {
				ftp.logout();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (ftp != null) {
			try {
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
