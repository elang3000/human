package com.wondersgroup.human.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.unzip.UnzipUtil;
import net.lingala.zip4j.util.Zip4jConstants;


/**
 * zip文件解压与压缩
 * @author lys
 */
public class ZipUtil {
	
	static Logger logger = LoggerFactory.getLogger(ZipUtil.class);
	
	public static void main(String[] args) {
//		unzipByIS("G:/temp1/temp1.zip", "G:/temp1/22","123");
//		Set<String> test = new HashSet<>();
//		test.add("G:/temp1/我");
//		test.add("G:/temp1/123.xlsx");
//		zipBysplit("G:/temp1/temp/你1.zip", test, "123", 65536l); 
//		zipBysplit("G:/temp1/temp/你1.zip", "G:/temp1/我", "123", 65536l); 
//		unzip("G:/temp1/temp/你1.zip", "G:/temp1/temp/", "123");
	}
	
	/**
	 * 解压文件
	 * @param zipurl zip绝对路径
	 * @param tourl 解压到目录 的 绝对路径
	 */
	public static void unzip(String zipurl,String tourl){
		unzip(zipurl, tourl,null,null);
	}
	/**
	 * 解压文件
	 * @param zipurl zip绝对路径
	 * @param tourl 解压到目录 的 绝对路径
	 */
	public static void unzip(String zipurl,String tourl,String password){
		unzip(zipurl, tourl,password,null);
	}
	/**
	 * 解压文件
	 * @param zipurl zip绝对路径
	 * @param tourl 解压到目录 的 绝对路径
	 * @param password 文件密码
	 * @param chaset 文件编码
	 */
	public static void unzip(String zipurl,String tourl,String password,String chaset){
		logger.debug("解压的文件："+zipurl+"，解压到："+tourl);
		try {
			ZipFile zipFile =getUnZipFile(zipurl, password, chaset);
			zipFile.extractAll(tourl);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取压缩文件ZipFile对象
	 * @param zipurl  zip绝对路径
	 * @param password 文件密码
	 * @param chaset 文件编码
	 * @return
	 */
	private static ZipFile getUnZipFile(String zipurl ,String password,String chaset){
		ZipFile zipFile =null;
		try {
			zipFile = new ZipFile(zipurl);
			if(chaset!=null&&!"".equals(chaset)){
				zipFile.setFileNameCharset(chaset);//设置编码
			}else{
				zipFile.setFileNameCharset("GBK");//设置编码
			}
			if(zipFile.isEncrypted()&&password!=null){
				zipFile.setPassword(password);//设置密码
			}
			// 验证.zip文件是否合法，包括文件是否存在、是否为zip文件、是否被损坏等
			if (!zipFile.isValidZipFile()) { 
				throw new ZipException("压缩文件不合法,可能被损坏.");
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
		return zipFile;
	}
	
	/***
	 * 解压文件，通过流的方式
	 * @param zipurl zip绝对路径
	 * @param tourl 解压到目录 的 绝对路径
	 */
	public static void unzipByIS(String zipurl,String tourl ){
		unzipByIS(zipurl, tourl, null,null);
	}
	/***
	 * 解压文件，通过流的方式
	 * @param zipurl zip绝对路径
	 * @param tourl 解压到目录 的 绝对路径
	 * @param password 文件密码
	 */
	public static void unzipByIS(String zipurl,String tourl,String password){
		unzipByIS(zipurl, tourl, password, null);
	}
	/***
	 * 解压文件，通过流的方式
	 * @param zipurl zip绝对路径
	 * @param tourl 解压到目录 的 绝对路径
	 * @param password 文件密码
	 * @param chaset 文件编码
	 */
	@SuppressWarnings("rawtypes")
	public static void unzipByIS(String zipurl,String tourl,String password,String chaset){
		logger.debug("通过 InputStream 解压的文件："+zipurl+"，解压到："+tourl);
		ZipInputStream is = null;
		OutputStream os = null;
		try {
			ZipFile zipFile =getUnZipFile(zipurl, password, chaset);// 也可以直接解压单个文件 zipFile.extractFile("FolderToAdd/myvideo.avi", "c:\\ZipTest\\");
//			zipFile.isSplitArchive()//判断文件是否为分割压缩 
			List fileHeaderList = zipFile.getFileHeaders(); 
			for (int i = 0; i < fileHeaderList.size(); i++) {
				FileHeader fileHeader = (FileHeader)fileHeaderList.get(i);//zipFile.getFileHeader("我/正.xlsx");  也可以 解压 某一个文件
				//解压方式也可以是这种 zipFile.extractFile(fileHeader, "G:/temp1/temp"); 
//				System.out.println("Name: " + fileHeader.getFileName());//文件相对路径
//				System.out.println("Compressed Size: " + fileHeader.getCompressedSize());//文件压缩后大小
//				System.out.println("Uncompressed Size: " + fileHeader.getUncompressedSize());//文件不压缩的大小
//				System.out.println("CRC: " + fileHeader.getCrc32());
				if (fileHeader != null) {
					String outFilePath = tourl + System.getProperty("file.separator") + fileHeader.getFileName();
					File outFile = new File(outFilePath);
					//如果是目录， 且没有 则创建目录
					if (fileHeader.isDirectory()) {
						outFile.mkdirs();
						continue;
					}
					File parentDir = outFile.getParentFile();
					if (!parentDir.exists()) {
						parentDir.mkdirs();
					}
					//从zip文件中获取输入流
					is = zipFile.getInputStream(fileHeader);
					os = new FileOutputStream(outFile);
					int readLen = -1;
					byte[] buff = new byte[4096];
					while ((readLen = is.read(buff)) != -1) {
						os.write(buff, 0, readLen);
					}
					closeFileHandlers(is, os);
					UnzipUtil.applyFileAttributes(fileHeader, outFile);
					logger.debug("解压完成 ："+fileHeader.getFileName());
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeFileHandlers(is, os);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/***
	 * 关闭解压流
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	private static void closeFileHandlers(ZipInputStream is, OutputStream os) throws IOException{
		if (os != null) {
			os.close();
			os = null;
		}
		if (is != null) {
			is.close();
			is = null;
		}
	}
	/**
	 * 从一个压缩包移除一个文件
	 * @param zipurl 压缩包绝对路径
	 * @param xdurl 移除文件的相对路径
	 * @param password 密码
	 * @param chaset 编码
	 */
	public static void removeFile(String zipurl,String xdurl,String password,String chaset){
		logger.debug("移除压缩文件："+zipurl+"中的："+xdurl);
		try {
			ZipFile zipFile =getUnZipFile(zipurl, password, chaset);
			zipFile.removeFile(xdurl);
			//也可以自己遍历，移除zipFile中的 FileHeader 对象
//			if (zipFile.getFileHeaders() != null && zipFile.getFileHeaders().size() > 0) {
//				zipFile.removeFile((FileHeader)zipFile.getFileHeaders().get(0));
//			} else {
//				System.out.println("This cannot be demonstrated as zip file does not have any files left");
//			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 从一个压缩包移除批量文件
	 * @param zipurl 压缩包绝对路径
	 * @param list 移除文件的相对路径的集合
	 * @param password 密码
	 * @param chaset 编码
	 */
	public static void removeFile(String zipurl,List<String> list,String password,String chaset){
		logger.debug("移除压缩文件："+zipurl+"中的list对象："+list);
		try {
			if(list!=null&&list.size()>0){
				ZipFile zipFile =getUnZipFile(zipurl, password, chaset);
				for(String s:list){
					zipFile.removeFile(s);
				}
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 压缩单个文件或文件夹
	 * @param zipurl zip生成后的绝对路径
	 * @param aurl 要压缩的的文件绝对路径
	 * @param password 文件密码 ，没有密码则设置为null
	 */
	public static void zip(String zipurl, String aurl, String password) {
		logger.debug("压缩后的文件路径：" + zipurl + "，要压缩的文件/文件夹：" + aurl);
		try {
			String yatype=null;
			File srcFile = new File(aurl);
			// 是文件且不是文件夹
			if (srcFile.exists() && !srcFile.isDirectory()){
				yatype="1";
			} else {
				if (srcFile.isDirectory()) {//文件夹
					yatype="2";
				} else {
					logger.error("压缩后的文件路径：" + zipurl + "，要压缩的文件/文件夹：" + aurl+"不存在");
				}
			}
			//要压缩的文件或文件夹 路径正确才去设置或者准备构建文件之类的。。。
			if(yatype!=null){
				// 构建压缩文件
				ZipFile zip = getZipFile(zipurl);
				// 设置参数
				ZipParameters para = getzipParam(password);
				if("1".equals(yatype)){
					zip.addFile(srcFile, para);
				}else if("2".equals(yatype)){
					zip.addFolder(srcFile, para);
				}
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 压缩多个文件或多个文件夹
	 * @param zipurl zip生成后的绝对路径
	 * @param fileurls 要压缩的的文件绝对路径路集合
	 * @param password 文件密码 ，没有密码则设置为null
	 */
	public static void zip(String zipurl, Set<String> fileurls, String password) {
		logger.debug("压缩后的文件路径：" + zipurl + "，要压缩的文件/文件夹的集合：" + fileurls);
		try {
			if (fileurls != null && fileurls.size() > 0) {
				// 构建压缩文件
				ZipFile zip = getZipFile(zipurl);
				// 设置参数
				ZipParameters para = getzipParam(password);
				for (String s : fileurls) {
					File srcFile = new File(s);
					// 是文件且不是文件夹
					if (srcFile.exists() && !srcFile.isDirectory()) {
						zip.addFile(srcFile, para);
					} else {
						if (srcFile.isDirectory()) {// 文件夹
							zip.addFolder(srcFile, para);
						} else {
							logger.error("压缩后的文件路径：" + zipurl + "，要压缩的文件/文件夹：" + s + "不存在");
							continue;
						}
					}
				}
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 分割压缩多个文件 ，不能包含文件夹
	 * @param zipurl zip生成后的绝对路径
	 * @param fileurls 要压缩的的文件绝对路径路集合
	 * @param password 文件密码 ，没有密码则设置为null
	 * @param splitnum 分割大小 ，单位 b
	 */
	public static void zipBysplit(String zipurl, Set<String> fileurls, String password,long splitnum) {
		logger.debug("压缩后的文件路径：" + zipurl + "，要压缩的文件的集合：" + fileurls);
		try {
			if (fileurls != null && fileurls.size() > 0) {
				// 构建压缩文件
				ZipFile zip = getZipFile(zipurl);
				// 设置参数
				ZipParameters para = getzipParam(password);
				ArrayList<File> filesToAdd = new ArrayList<File>();
				for (String s : fileurls) {
					filesToAdd.add(new File(s));
				}
				zip.createZipFile(filesToAdd, para, true, splitnum);
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 分割压缩单个文件夹 ，不能包含文件
	 * @param zipurl zip生成后的绝对路径
	 * @param fileurl 要压缩的的文件夹 绝对路径路 
	 * @param password 文件密码 ，没有密码则设置为null
	 * @param splitnum 分割大小 ，单位 b
	 */
	public static void zipBysplit(String zipurl, String fileurl, String password,long splitnum) {
		logger.debug("压缩后的文件路径：" + zipurl + "，要压缩的文件夹：" + fileurl);
		try {
			if (fileurl != null && !"".equals(fileurl)) {
				// 构建压缩文件
				ZipFile zip = getZipFile(zipurl);
				// 设置参数
				ZipParameters para = getzipParam(password);
				zip.createZipFileFromFolder(fileurl, para, true, splitnum);
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	
	private static ZipFile getZipFile(String zipurl) throws ZipException{
		ZipFile zip = new ZipFile(zipurl);
		// 需要判断压缩父路径是否存在
		File file = zip.getFile();
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		 //为了不被原有文件干扰,保证每次重新生成
	    if (file.exists()) {
	        file.delete();
	    }
	    zip.setFileNameCharset("GBK");//设置压缩编码，以免乱码
	    return zip;
	}
	private static ZipParameters getzipParam(String password){
		// 设置参数
		ZipParameters para = new ZipParameters();
		// 设置压缩方式,默认是COMP_DEFLATE
//		COMP_STORE = 0;（仅打包，不压缩） （对应好压的存储） 
//		COMP_DEFLATE = 8;（默认） （对应好压的标准） 
//		COMP_AES_ENC = 99;
		para.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		// 设置压缩级别,默认为0（即不压缩）
		//DEFLATE_LEVEL_FASTEST=1 最低压缩水平，但更高的压缩速度
		//DEFLATE_LEVEL_FAST=3 压缩水平低，但压缩速度快
		//DEFLATE_LEVEL_NORMAL=5 压缩水平/速度之间的最佳平衡
		//DEFLATE_LEVEL_MAXIMUM=7 高压缩等级与速度的折衷
		//DEFLATE_LEVEL_ULTRA=9 最高压缩等级，但低速
		para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		if(password!=null&&!"".equals(password)){
			// 设置压缩密码（至少3步）
			para.setEncryptFiles(true);// 设置加密文件
			// 设置加密方式（必须要有加密算法）
//			ENC_NO_ENCRYPTION = -1;（默认,没有加密方法，如果采用此字段，会报错”没有提供加密算法”） 
//			ENC_METHOD_STANDARD = 0; 
//			ENC_METHOD_AES = 99; 
			//普通加密
			para.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
			/*AES加密
			//设置AES加密方式
		    para.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		    //必须设置长度
		    para.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256); 
			*/
			para.setPassword(password);//设置密码
		}
		//压缩得时候 可以多建立几层文件夹
//		para.setRootFolderInZip("test2/1/2");
		
		return para;
	}
	
	
}