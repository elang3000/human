package com.wondersgroup.human.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @ClassName: ExcelUtils 
 * @Description: 通过ftl模板,生成excel文件工具
 * @author: youyd
 * @date: 2018年9月1日 上午11:20:06
 * @version [版本号, YYYY-MM-DD]
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class ExcelUtils {
     //配置信息,代码本身写的还是很可读的,就不过多注解了  
    private static Configuration configuration = null;  
    //这里注意的是利用ExcelUtils的类加载器动态获得模板文件的位置  
    private static final String templateFolder = WordUtils.class.getClassLoader().getResource("../../").getPath() + "WEB-INF/templates/";   
//    private static final String templateFolder = "d:/aaa";  
    static {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
        try {  
            configuration.setDirectoryForTemplateLoading(new File(templateFolder));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
   }  
  
    private ExcelUtils() {  
        throw new AssertionError();  
    }  
  
    public static void exportMillCertificateExcel(HttpServletRequest request, HttpServletResponse response, Map map,String title,String ftlFile) throws IOException {  
        Template freemarkerTemplate = configuration.getTemplate(ftlFile);  
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // 调用工具类的createDoc方法生成Excel文档  
            file = createXls(map,freemarkerTemplate);  
            fin = new FileInputStream(file);  
  
            response.setCharacterEncoding("utf-8");  
            response.setContentType("application/vnd.ms-excel");  
            // 设置浏览器以下载的方式处理该文件名  
            String fileName = title+new Date().toString() + ".xls";  
            response.setHeader("Content-Disposition", "attachment;filename="  
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));  
  
            out = response.getOutputStream();  
            byte[] buffer = new byte[512];  // 缓冲区  
            int bytesToRead = -1;  
            // 通过循环将读入的Excel文件的内容输出到浏览器中  
            while((bytesToRead = fin.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);  
            }  
        } finally {  
            if(fin != null) fin.close();  
            if(out != null) out.close();  
            if(file != null) file.delete(); // 删除临时文件  
        }  
    }  
    
    /**
     * 
     * @Title: createMillCertificateExcel 
     * @Description: 通过ftl模板,生成相应的xls文件
     * @param map 模板中的参数
     * @param ftlFile 模板文件名称
     * @return
     * @throws IOException
     * @return: File
     */
    public static File createMillCertificateExcel(Map map,String ftlFile) throws IOException {  
        Template freemarkerTemplate = configuration.getTemplate(ftlFile);  
        File file = null;  
        // 调用工具类的createDoc方法生成Excel文档  
        file = createXls(map,freemarkerTemplate);  
        return file;
  
    }  
  
    private static File createXls(Map<?, ?> dataMap, Template template) {  
        String name =  "sellPlan.xls";  
        File f = new File(name);  
        Template t = template;  
        try {  
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Excel文档会因为有无法识别的编码而无法打开  
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");  
            t.process(dataMap, w);  
            w.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw new RuntimeException(ex);  
        }  
        return f;  
    }  
}