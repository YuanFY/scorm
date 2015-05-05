package com.scorm.action.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.vo.Courseinfo;
/**
 * 导出excl表
 * @author YuanFY
 * @date 2014-8-6上午9:59:00
 * @Description:TODO
 * @version V1.0
 */
@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="exportExcelAction")
public class ExportUserAction extends ActionSupport{
	
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService;
	
	
	/**
	 * 课程信息导出
	 * @return
	 */
	public String export(){
        try {
        	//先缓存数据
    		String url = "courseinfo.xls";
    		HSSFWorkbook wb = UserinfoExport(courseinfoService.findAllCourseinfo());
    		FileOutputStream fos = new FileOutputStream(url);
    		wb.write(fos); 
            fos.close(); 
            
            //下载
            String filePath = url;
            File fdown = new File(filePath);
            int filelength = Integer.parseInt(String.valueOf(fdown.length()));
            
            HttpServletResponse response = ServletActionContext.getResponse();
            // 缓存清除
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
        	// 设置响应体属性，文件下载操作的关键就在这儿
			response.setHeader("Content-disposition",
			  "attachment;filename=\""
			    + new String(url.getBytes("ISO-8859-1"),
			      "UTF-8") + "\"");
	        // 文件头属性设置
	        response.setHeader("Content-length", url.length() + "");
	        // 响应体内容设置
	        response.setContentType("application/octet-stream;charset=UTF-8");
	        response.setContentLength(filelength);
	        byte b[]=new byte[filelength];
	        FileInputStream fi=new FileInputStream(fdown);       
	        OutputStream o=response.getOutputStream();
	        int n = 0;
	        while((n=fi.read(b))!=-1) {
	            o.write(b,0,n);
	        }
	        fi.close();
	        o.close();
        } catch (Exception e) {
			//e.printStackTrace();
		}
        return "success";
	}
	
	/**
	 * 将数据库数据导出Excel表格的工具
	 * @param userlist 数据库中取出的数据集
	 * @return 表格对象
	 */
	public static HSSFWorkbook UserinfoExport(List<Courseinfo> userlist){
		//第一步、创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb  = new HSSFWorkbook();
		//第二步、在webbook中添加一个sheet，对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("课程信息表");
		//第三步、在sheet中添加表头第0行，注意老版本poi对EXCEL的行数列数有限制short
		HSSFRow row = sheet.createRow((int)0);
		//第四步、创建单元格，并设置表头，设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//创建一个居中样式
		
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("课程编号");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("课程");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("课程类型");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("注册人数");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("创建时间");
		cell.setCellStyle(style);
		
		// 第五步，写入实体数据 数据从外部传入list，
		for(int i = 0;i < userlist.size();i ++){
			row = sheet.createRow((int) i + 1);
			Courseinfo user = userlist.get(i);
			//创建单元格 并且设置单元格的值
			row.createCell(0).setCellValue(user.getCourseId()+"");
			row.createCell(1).setCellValue(user.getCourseName()+"");
			row.createCell(2).setCellValue(user.getCourseType()+"");
			row.createCell(3).setCellValue(user.getRegisterNumber()+"");
			row.createCell(4).setCellValue(user.getUploadTime()+"");
		}
		return wb;
	}
}
