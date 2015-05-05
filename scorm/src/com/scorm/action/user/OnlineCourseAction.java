package com.scorm.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.CourseregService;
import com.scorm.service.CoursetypeinfoService;
import com.scorm.service.ScoinfoService;
import com.scorm.utils.Page;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Coursereg;
import com.scorm.vo.Coursetypeinfo;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.bean.RegCourseBean;

/**
 * 
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */
@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="onlineCourseAction")
public class OnlineCourseAction extends ActionSupport{
	
	@Resource(name="coursetypeinfoService")
	private CoursetypeinfoService coursetypeService;
	
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService;
	
	@Resource(name="courseregService")
	private CourseregService courseregService;
	
	@Resource(name="scoinfoService")
	private ScoinfoService scoinfoService;
	
	// 分页信息
	private int dataCount;
	private Integer pageStart;
	private Page page;
	
	// 课程类型信息
	private int courseTypeId;
	private String courseType;
	
	// 课程信息
	private int courseId;
	private String courseName;
	private String courseContent;
	private int registerNumber;
	private String uploadTime;
	private String coursePicture;
	
	@SuppressWarnings("static-access")
	public String execute() {
		System.out.println("wellcome 网上选课");
		dataCount = courseinfoService.findAllCourseinfo().size();
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		}
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			page = new Page(dataCount, pageStart);
		}
		
		// 左边导航条
		System.out.println("wellcome 网上选课-左边导航条");
		List<Coursetypeinfo> coursetypeinfo = coursetypeService.findAllType();
		List<Courseinfo> courseinfo = courseinfoService.findAllCourseinfo();
		List<Scoinfo> scoinfo = scoinfoService.findAllScoinfo();
		System.out.println(coursetypeinfo+" "+courseinfo+" "+scoinfo);
		ServletActionContext.getRequest().setAttribute("coursetypeinfo", coursetypeinfo);
		ServletActionContext.getRequest().setAttribute("courseinfo", courseinfo);
		ServletActionContext.getRequest().setAttribute("scoinfo", scoinfo);
		
		// 右边课程
		List<Courseinfo> rlist = courseinfoService.getCourseList(pageStart, page.getPageRecordNum());
		List<RegCourseBean> rCourseinfo = new ArrayList<RegCourseBean>();
		for(Courseinfo c : rlist){
			RegCourseBean bean = new RegCourseBean();
			bean.setCourseinfo(c);
			System.out.println("this.getSessionUser().getUserId()="+this.getSessionUser().getUserId());
			List<Coursereg> list =courseregService.findById(this.getSessionUser().getUserId(), c.getCourseId());
			System.out.println("list="+list.size());
			if(list!=null && list.size()>0){
				bean.setRegFlag(true);
			}else{
				bean.setRegFlag(false);
			}
			rCourseinfo.add(bean);
		}
		ServletActionContext.getRequest().setAttribute("rCourseinfo", rCourseinfo);
		
		return "success";
	}
	
	public String courseSearchAction() {
		System.out.println("wellcome 网上选课搜索: courseName="+courseName);
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		} 
		dataCount = courseinfoService.courseSearch(courseName, pageStart, 10).size();
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			page = new Page(dataCount, pageStart);
		}
		
		// 左边导航条
		System.out.println("wellcome 网上选课-左边导航条");
		List<Coursetypeinfo> coursetypeinfo = coursetypeService.findAllType();
		List<Courseinfo> courseinfo = courseinfoService.findAllCourseinfo();
		List<Scoinfo> scoinfo = scoinfoService.findAllScoinfo();
		System.out.println(coursetypeinfo+" "+courseinfo+" "+scoinfo);
		ServletActionContext.getRequest().setAttribute("coursetypeinfo", coursetypeinfo);
		ServletActionContext.getRequest().setAttribute("courseinfo", courseinfo);
		ServletActionContext.getRequest().setAttribute("scoinfo", scoinfo);
		
		// 右边搜索课程
		System.out.println("wellcome 网上选课-右边搜索课程");
		List<Courseinfo> rlist = courseinfoService.courseSearch(courseName, pageStart, page.getPageRecordNum()) ;
		List<RegCourseBean> rCourseinfo = new ArrayList<RegCourseBean>();
		for(Courseinfo c : rlist){
			RegCourseBean bean = new RegCourseBean();
			bean.setCourseinfo(c);
			System.out.println("this.getSessionUser().getUserId()="+this.getSessionUser().getUserId());
			List<Coursereg> list =courseregService.findById(this.getSessionUser().getUserId(), c.getCourseId());
			System.out.println("list="+list.size());
			if(list!=null && list.size()>0){
				bean.setRegFlag(true);
			}else{
				bean.setRegFlag(false);
			}
			rCourseinfo.add(bean);
		}
		ServletActionContext.getRequest().setAttribute("rCourseinfo", rCourseinfo);
		System.out.println("rCourseinfo="+rCourseinfo);
		return "success";
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(int courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
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

	public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public int getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber) {
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
	
}
