package com.scorm.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.CoursetypeinfoService;
import com.scorm.utils.Page;
import com.scorm.vo.Coursetypeinfo;

/**
 * 课程类型管理
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="courseTypeAction")
public class CourseTypeAction extends ActionSupport{

	@Resource(name="coursetypeinfoService")
	private CoursetypeinfoService coursetypeinfoService ;
	
	/**
	 * 分页
	 */
	private int dataCount ;
	private Integer pageStart ;
	private Page page ;
	
	/**
	 * 课程类型信息
	 */
	private Integer courseTypeId;
	private String courseType;

	/**
	 * 获取课程类型的所有对象
	 */
	public String execute(){
		System.out.println("welcome 课程类型管理后台");
		dataCount = coursetypeinfoService.findAllType().size();
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		}
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			page = new Page(dataCount, pageStart);
		}
		List<Coursetypeinfo> list = coursetypeinfoService.getCoursetypeinfoList(pageStart, page.getPageRecordNum()) ;
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("courseTypeList", list);
		return "success";
	}
	
	/**
	 * 获取单个课程类型信息
	 * @return
	 */
	public String getCourseTypeAction() {
		System.out.println("wellcome getCourseTypeAction: courseTypeId="+courseTypeId);
		Coursetypeinfo coursetypeinfo = coursetypeinfoService.findByCourseTypeId(courseTypeId).get(0);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("courseTypeinfo", coursetypeinfo);
		
		return "success";
	}
	
	/**
	 * 更新课程类型信息
	 * @return
	 */
	public String updateCourseTypeAction() {
		System.out.println("wellcome updateCourseType: courseId"+courseTypeId+", "+courseType);
		Coursetypeinfo coursetypeinfo = coursetypeinfoService.findByCourseTypeId(courseTypeId).get(0);
		coursetypeinfo.setCourseType(courseType);
		boolean flag = coursetypeinfoService.updateCoursetypeinfo(coursetypeinfo);
		
		if(flag) {
			ServletActionContext.getRequest().getSession().setAttribute("updateResult", 1);
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("updateResult", -1);
		}
		return "success";
	}
	
	public String addCourseTypeAction() {
		System.out.println("wellcome addCourseType: courseType="+courseType);
		Coursetypeinfo coursetypeinfo = new Coursetypeinfo(courseType);
		boolean flag = coursetypeinfoService.saveCoursetype(coursetypeinfo);
		
		if(flag) {
			ServletActionContext.getRequest().getSession().setAttribute("addResult", 1);
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("addResult", -1);
		}
		return "success";
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

	public Integer getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(Integer courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
	
}
