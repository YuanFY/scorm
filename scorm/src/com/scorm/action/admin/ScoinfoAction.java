package com.scorm.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.ScoinfoService;
import com.scorm.service.UsercourseinfoService;
import com.scorm.service.ViewcourseService;
import com.scorm.utils.Page;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.Usercourseinfo;
import com.scorm.vo.Viewcourse;

/**
 * 对课件信息的基本操作
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */
@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="scoinfofoAction")
public class ScoinfoAction extends ActionSupport{

	@Resource(name="scoinfoService")
	private ScoinfoService scoinfoService ;
	
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService ;
	
	@Resource(name="usercourseinfoService")
	private UsercourseinfoService usercourseinfoService;
	
	@Resource(name="viewcourseService")
	private ViewcourseService viewcourseService;
	
	/**
	 * 分页
	 */
	private int dataCount ;
	private Integer pageStart ;
	private Page page ;
	
	/**
	 * 课件信息
	 */
	private int scoId ;
	private Courseinfo courseinfo ;
	private String scoName ;
	private String uploadTime ;
	private String uploadAuthor ;
	private int clickNum ;
	
	private int courseinfoId;
	
	/**
	 * 获取所有的课件信息  并分页
	 */
	public String execute() {
		System.out.println("welcome 课件信息管理");
		dataCount = scoinfoService.findAllScoinfo().size();
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		}
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			page = new Page(dataCount, pageStart);
		}

		List<Scoinfo> list = scoinfoService.getScoinfoList(pageStart, page.getPageRecordNum()) ;
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("scoinfoList", list);
		return "success";
	}
	
	/**
	 * 删除课件信息
	 * @return
	 */
	public String delScoAction() {
		System.out.println("wellcome delScoAction: scoId="+scoId);
		Scoinfo scoinfo = scoinfoService.findByScoId(scoId).get(0);
		List<Usercourseinfo> ucList = usercourseinfoService.findByScoId(scoId);
		for(Usercourseinfo u : ucList){
			if(u!=null){
				usercourseinfoService.deleteUsercourseinfo(u);
			}
		}
		List<Viewcourse> viewList = viewcourseService.findBysql("from Viewcourse where scoId = "+scoId);
		for(Viewcourse u : viewList){
			if(u!=null){
				viewcourseService.delete(u);
			}
		}
		scoinfoService.deleteScoinfo(scoinfo);
		
		ServletActionContext.getRequest().getSession().setAttribute("delResult", 1);
		return "success";
	}
	
	/**
	 * 得到所有的课件信息  并得到课程类型
	 * @return
	 */
	public String getScoinfoAction() {
		System.out.println("wellcome getScoinfoAction: scoId="+scoId+","+courseinfoService.findAllCourseinfo());
		Scoinfo scoinfo = scoinfoService.findByScoId(scoId).get(0);
		System.out.println(scoinfo.getUploadTime()+","+scoinfo.getUploadAuthor()+","+scoinfo.getClickNum());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("scoinfo", scoinfo);
		
		// 获取课程类型列表
		request.getSession().setAttribute("courseinfoList", courseinfoService.findAllCourseinfo());
		return "success";
	}
	
	public String updateScoinfoAction() {
		System.out.println("wellcome updateScoinfoAction: scoId="+scoId);
		Scoinfo scoinfo = scoinfoService.findByScoId(scoId).get(0);
		scoinfo.setScoName(scoName);
		courseinfo = courseinfoService.findByCourseId(courseinfoId).get(0);
		scoinfo.setCourseinfo(courseinfo);
		scoinfoService.updateScoinfo(scoinfo);
		
		ServletActionContext.getRequest().getSession().setAttribute("updateResult", 1);
		return "success";
	}

	public ScoinfoService getScoinfoService() {
		return scoinfoService;
	}

	public void setScoinfoService(ScoinfoService scoinfoService) {
		this.scoinfoService = scoinfoService;
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

	public int getScoId() {
		return scoId;
	}

	public void setScoId(int scoId) {
		this.scoId = scoId;
	}

	public Courseinfo getCourseinfo() {
		return courseinfo;
	}

	public void setCourseinfo(Courseinfo courseinfo) {
		this.courseinfo = courseinfo;
	}

	public String getScoName() {
		return scoName;
	}

	public void setScoName(String scoName) {
		this.scoName = scoName;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadAuthor() {
		return uploadAuthor;
	}

	public void setUploadAuthor(String uploadAuthor) {
		this.uploadAuthor = uploadAuthor;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getCourseinfoId() {
		return courseinfoId;
	}

	public void setCourseinfoId(int courseinfoId) {
		this.courseinfoId = courseinfoId;
	}
}
