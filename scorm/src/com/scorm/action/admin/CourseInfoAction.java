package com.scorm.action.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.CoursetypeinfoService;
import com.scorm.utils.Page;
import com.scorm.utils.UUIDUtil;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Coursetypeinfo;

/**
 *  课程信息的基本操作
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */
@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="courseInfoAction")
public class CourseInfoAction extends ActionSupport{
	
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService ;
	
	@Resource(name="coursetypeinfoService")
	private CoursetypeinfoService coursetypeinfoService;

	/**
	 * 分页
	 */
	private int dataCount ;
	private Integer pageStart ;
	private Page page;
	
	/**
	 * 课程信息
	 */
	private int courseId;
	private String courseName ;
	private String courseType ;
	private String courseContent ;
	private Integer registerNumber ;
	private String uploadTime ;
	private String coursePicture;
	private String coursePictureFileName;
	
	/**
	 * 得到所有的课程信息
	 */
	public String execute(){
		System.out.println("welcome 课程信息管理后台");
		dataCount = courseinfoService.findAllCourseinfo().size();
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		}
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			page = new Page(dataCount, pageStart);
		}
		
		List<Courseinfo> list = courseinfoService.getCourseList(pageStart, page.getPageRecordNum()) ;
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("courseList", list);
		request.getSession().setAttribute("coursetypeinfoList", coursetypeinfoService.findAllType());
		return "success";
	}
	/**
	 * 根据courseId得到课程信息
	 * @return
	 */
	public String getCourseInfoAction() {
		System.out.println("wellcome getCourseInfo: courseId="+courseId);
		Courseinfo courseinfo = courseinfoService.findByCourseId(courseId).get(0);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("courseinfo", courseinfo);
		System.out.println("coursePicture="+courseinfo.getCoursePicture());
		
		//获取所有课程
		System.out.println("coursetypeinfo:"+coursetypeinfoService.findAllType());
		request.getSession().setAttribute("coursetypeinfoList", coursetypeinfoService.findAllType());
		return "success";
	}
	/**
	 * 更新课程信息
	 * @return
	 * @throws Exception
	 */
	public String updateCourseInfoAction() throws Exception {
		System.out.println("wellcome updateCourseInfoAction: courseId="+courseId+",coursePicture="+coursePicture);
		
		String picturePath = null;
		if( coursePicture != null ) {
			//System.out.println(repairsApplyPicture);
			String now = "" + UUIDUtil.getUUId();
			String path = ServletActionContext.getServletContext().getRealPath(
					"\\upload\\coursePicture");
			String newFileName = null;
			System.out.println("-path="+path);
			File dir = new File(path);
			if( !dir.exists() ) {
				System.out.println("创建文件夹："+dir);
				System.out.println("dir.mkdirs:"+dir.mkdirs());
			}
			 
			int index = coursePictureFileName.lastIndexOf('.');
			System.out.println("格式："+coursePictureFileName.substring(index));
			if( index != -1 ) {
				newFileName = now + coursePictureFileName.substring(index);
			} else {
				newFileName = now;
			}
			 
			BufferedOutputStream bos = null;
			BufferedInputStream bis = null;
			FileInputStream fis = new FileInputStream(new File(coursePicture));
			bis = new BufferedInputStream(fis);
			FileOutputStream fos = null;
			 
			System.out.println("new File="+new File(dir+File.separator+newFileName));
			fos = new FileOutputStream(new File(dir+File.separator+newFileName));
			bos = new BufferedOutputStream(fos);
			 
			byte[] buf = new byte[1024*1024*10];
			int len = -1 ;
			while( (len = bis.read(buf)) != -1 ) {
				bos.write(buf, 0, len);
			}
			picturePath = "upload/coursePicture/"+newFileName;
			System.out.println("picture="+picturePath);
			System.out.println("coursePictureFileName:"+coursePictureFileName);
		}
		
		Courseinfo courseinfo = courseinfoService.findByCourseId(courseId).get(0);
		Coursetypeinfo coursetypeinfo = coursetypeinfoService.findByCourseTypeId(Integer.parseInt(courseType)).get(0);
		courseinfo.setCourseName(courseName);
		courseinfo.setCourseContent(courseContent);
		courseinfo.setCourseType(coursetypeinfo.getCourseType());
		if( picturePath != null )
			courseinfo.setCoursePicture(picturePath);
		boolean flag = courseinfoService.updateCourseinfo(courseinfo);
		
		if(flag) {
			ServletActionContext.getRequest().getSession().setAttribute("updateResult", 1);
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("updateResult", -1);
		}
		return "success";
	}
	
	/**
	 * 添加课程信息
	 * @return
	 */
	public String addCourseInfoAction() throws Exception{
		System.out.println("wellcome addCourseInfoAction");
		
		String picturePath = null;
		if( coursePicture != null ) {
			//System.out.println(repairsApplyPicture);
			String now = "" + courseId;
			String path = ServletActionContext.getServletContext().getRealPath(
					"\\upload\\coursePicture");
			String newFileName = null;
			System.out.println("-path="+path);
			File dir = new File(path);
			if( !dir.exists() ) {
				dir.mkdirs();
			}
			 
			int index = coursePictureFileName.lastIndexOf('.');
			System.out.println("格式："+coursePictureFileName.substring(index));
			if( index != -1 ) {
				newFileName = now + coursePictureFileName.substring(index);
			} else {
				newFileName = now;
			}
			 
			BufferedOutputStream bos = null;
			BufferedInputStream bis = null;
			FileInputStream fis = new FileInputStream(new File(coursePicture));
			bis = new BufferedInputStream(fis);
			FileOutputStream fos = null;
			 
			fos = new FileOutputStream(new File(dir+File.separator+newFileName));
			bos = new BufferedOutputStream(fos);
			 
			byte[] buf = new byte[1024*1024*10];
			int len = -1 ;
			while( (len = bis.read(buf)) != -1 ) {
				bos.write(buf, 0, len);
			}
			picturePath = "upload/coursePicture/"+newFileName;
			System.out.println("picture="+picturePath);
			System.out.println("coursePictureFileName:"+coursePictureFileName);
		}
		
		Coursetypeinfo coursetypeinfo = coursetypeinfoService.findByCourseTypeId(Integer.parseInt(courseType)).get(0);
		Courseinfo courseinfo = new Courseinfo(courseName, coursetypeinfo.getCourseType(), courseContent, 0, new Timestamp(System.currentTimeMillis()), picturePath, null, null, null);
		boolean flag = courseinfoService.saveCourseinfo(courseinfo);
		
		if(flag) {
			ServletActionContext.getRequest().getSession().setAttribute("addCourse", 1) ;
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("addCourse", -1) ;
		}
		return "success";
	}

	public CourseinfoService getCourseinfoService() {
		return courseinfoService;
	}

	public void setCourseinfoService(CourseinfoService courseinfoService) {
		this.courseinfoService = courseinfoService;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public Integer getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(Integer registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getCoursePicture() {
		return coursePicture;
	}

	public void setCoursePicture(String coursePicture) {
		this.coursePicture = coursePicture;
	}

	public String getCoursePictureFileName() {
		return coursePictureFileName;
	}

	public void setCoursePictureFileName(String coursePictureFileName) {
		this.coursePictureFileName = coursePictureFileName;
	}
	
}
