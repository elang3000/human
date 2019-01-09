/**
 * Copyright © 2016 . All rights reserved.万达信息股份有限公司
 * 文件名: ExcelUtil.java
 * 工程名: corporation
 * 包名: com.wondersgroup.human.util
 * 描述: excel 工具类
 * 创建人: AngerYman
 * 创建时间: 2016年8月12日 上午11:23:14
 * 版本号: V1.0
 * 修改人：AngerYman
 * 修改时间：2016年8月12日 上午11:23:14
 * 修改任务号
 * 修改内容：
 */
package com.wondersgroup.human.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @ClassName: ExcelUtil
 * @Description: tzy
 * @author: AngerYman
 * @date: 2016年8月12日 上午11:23:14
 * 		@version v1.0
 *         @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ExcelUtilsPOI {
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
	/***
	 * excel文件下载
	 * @param tempurl缓存目录
	 * @param mark 下载名
	 */
	public static void exceldown(String tempurl,String mark,HttpServletRequest request,HttpServletResponse response){
		File file =  new File(tempurl);
		//输出下载
		if(file!=null){
			 BufferedInputStream buff=null;
			 OutputStream myout=null;
			 try {
				 	response.setContentType("text/html; charset=UTF-8");
			        response.setContentType("application/x-msdownload");//设置response的编码方式
			        response.setContentLength((int)file.length());//写明要下载的文件的大小
			        //处理ie乱码 
					// 解决中文文件名乱码问题---兼容IE
			        String fileName =getSbyDT(new Date(),"yyyyMMdd-HHmmss")+"-"+mark;
			        String agent = request.getHeader("User-Agent");
					boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
					
					//response.addHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "iso-8859-1"));
					response.setHeader("Content-Disposition", "attachment;filename=" + toUtf8String(isMSIE, fileName) ); 
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
	/**
	 * 
	 * @param bfile
	 * @param filePath
	 * @param fileName
	 */
	public static String getFile(byte[] bfile, String filePath, String fileDir, String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath+"\\"+fileDir);  
            if(!dir.exists()||dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileDir+"\\"+fileName);  
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
        return fileDir+"/"+fileName;
    } 
	/**
	 * 文件中文下载时候使用的转码类
	 * @param isMSIE
	 * @param s
	 * @return
	 */
	public static String toUtf8String(boolean isMSIE, String s) {
		if ( isMSIE ) {
			try {
				s = java.net.URLEncoder.encode(s, "UTF8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			try {
				s = new String(s.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return s;
	}
	
	/**
	 * 日期时间格式 转换成  参数设定或系统特定格式的字符串 
	 * @param sdate 传入的日期对象
	 * @param type 传入的转换成的日期格式   , DateConverUtil.TimeType中有一部分日期时间格式
	 * @return 字符串类型
	 * 
	 * 示例：DateConverUtil.dd2(new Date(),DateConverUtil.TimeType.type2.getValue()) 或者 DateConverUtil.dd2(new Date(),"yyyy-MM-dd H:m:s") ;
	 */
	public static String getSbyDT(Date date,String type){
		try {
			return getDateFormat(type).format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 设为严格格式
	 * @param type 传入的格式
	 * @return SimpleDateFormat的对象
	 */
	public static DateFormat getDateFormat(String type){
		DateFormat dateFormat=new SimpleDateFormat(type);
		dateFormat.setLenient(false);
		return dateFormat;
	}

	/** 
     * 根据传入map替换Excel模板文件内容 返回excel文件类 
     * @param item 替换数据内容
     * @param sourceFilePath Excel模板文件路径
     * @param targetFilePath Excel生成文件路径
     * @param index sheet表数量
     * @param bus 定制业务类型  1：职务简章-职位导出
     */  
    public static boolean replaceModel(Map<String,Object> item, String sourceFilePath,String targetFilePath,int index,String bus){
    	boolean bool = true;  
        try {
	        Workbook wb = getWorkbook(new FileInputStream(sourceFilePath),sourceFilePath);
	        CellStyle style = wb.createCellStyle();
	        style.setBorderBottom(CellStyle.BORDER_THIN);
	        style.setBorderLeft(CellStyle.BORDER_THIN);
	        style.setBorderRight(CellStyle.BORDER_THIN);
	        style.setBorderTop(CellStyle.BORDER_THIN);
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        for(int i=0;i<index;i++){//替换每张sheet数据
	        	Sheet sheet = wb.getSheetAt(i);
	        	replaceSheet(sheet, item,style,bus);
	        }
	        // 输出文件  
            FileOutputStream fileOut = new FileOutputStream(targetFilePath);  
            wb.write(fileOut);  
            fileOut.close();  
        } catch (Exception e) {
            bool = false;
            e.printStackTrace();
        }
        return bool;
    }
    /**
     * 替换sheet表中的数据
     * @Title: replaceSheet 
     * @return: void
     */
    public static void replaceSheet(Sheet sheet,Map<String,Object> item,CellStyle style,String bus){
//    	Iterator rows = sheet.rowIterator();  
//        while(rows.hasNext()){//迭代器 中增加了sheet的Row，会抛出异常java.util.ConcurrentModificationException
//          Row row = (Row) rows.next();
    	int n = sheet.getLastRowNum();
        for(int z=0;z<n;z++){
          Row row =sheet.getRow(z);
          if(row!=null){
              int num = row.getLastCellNum();
              for(int i=0;i<num;i++){
                  Cell cell=  row.getCell(i);
                  if(cell==null || cell.getStringCellValue()==null){
                	  continue;
                  }
//                  //System.out.println("行号："+z+"_________列号："+i+"______数据："+cell.getStringCellValue());
                  if(cell!=null){
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                  }
                  String value= cell.getStringCellValue();
                  if(!"".equals(value)){
	            	if(value.startsWith("${list")){//如果是list标签，循环生成list数据
	            		List<String[]> list = (List<String[]>)item.get(value.substring(value.indexOf("{")+1, value.lastIndexOf("}")));
	            		int colNumber = cell.getColumnIndex();//列号
	            		int rowNumber = cell.getRowIndex();//行号
	            		int flag = 0;//业务1 需要，计算合并多少行
	            		for(int k=rowNumber;k<list.size()+rowNumber;k++){
	            			//业务代码
	            			if("1".equals(bus)){
	            				if(list.get(k-rowNumber)[0]==null){
	            					flag++;
	            				}else{
	            					flag=0;
	            				}
	            				if((k!=rowNumber&&list.get(k-rowNumber)[0]!=null)||(k-rowNumber+1)==list.size()){
	            					sheet.addMergedRegion(new CellRangeAddress(k-flag,k,0,0));
	            					sheet.addMergedRegion(new CellRangeAddress(k-flag,k,1,1));
	            					sheet.addMergedRegion(new CellRangeAddress(k-flag,k,2,2));
	            					sheet.addMergedRegion(new CellRangeAddress(k-flag,k,3,3));
	            					sheet.addMergedRegion(new CellRangeAddress(k-flag,k,4,4));
	            					sheet.addMergedRegion(new CellRangeAddress(k-flag,k,5,5));
	            				}
	            			}
	            			
	            			Row rowTemp = sheet.createRow(k);//生成的行
	            			z++;
	            			for(int l=colNumber;l<list.get(k-rowNumber).length+colNumber;l++){
	            				Cell cellTemp = rowTemp.createCell(l);//生成的列
            					cellTemp.setCellStyle(style);
	            				cellTemp.setCellValue(list.get(k-rowNumber)[l-colNumber]);
	            			}
	            		}
	            		break;
	                }else{
	                	Set<String> keySet = item.keySet();
	                	Iterator<String> it = keySet.iterator();
	                	while (it.hasNext()) {
	                		String text = it.next();
	                		if(value.contains("${"+text+"}")){//map参数的key是 year 能匹配上 excel中的 ${year}年度，替换之后 变成 2017年度
	                			value = value.replace("${"+text+"}", String.valueOf(item.get(text)));//替换匹配的数据
	                			cell.setCellValue(value);
	                			break;
	                		}
	                	}
	                }
                  }else{
                  	cell.setCellValue("");
                  }
              }
            }  
        }
    }
	/**
	 * 描述：根据文件后缀，自适应上传文件的版本
	 * 
	 * @param inStr,fileName
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static Workbook getWorkbook(InputStream inStr, String fileName) throws  IOException {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new RuntimeException("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 描述：对表格中数值进行格式化
	 * 
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				value = df.format(cell.getNumericCellValue());
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		return value;
	}
}
