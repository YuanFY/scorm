package com.scorm.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.CoursetypeinfoService;
import com.scorm.service.UsercourseinfoService;
import com.scorm.utils.Page;
import com.scorm.vo.Coursetypeinfo;
import com.scorm.vo.Studyrecordinfo;
import com.scorm.vo.Userinfo;
/**
 * 学习管理 
 * @author BFS
 *
 */
@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="endcourseAction")
public class endCourseAction extends ActionSupport{
	/**分页
	 * 
	 */
	private int dataCount;//总记录数
	private Integer pageStart;//当前页吗
	private Page page;
	/**
	 * 档案信息
	 */
	private int userId;
	private String courseName;
	private String courseType;
	private String registerTime;
	private String endTime;
//	String studyStatus;
	private String studyTime;
	private int examRecord;
	private int studyStatus;
	private String lastTime;
	private List<Studyrecordinfo> infolist ;
	private int all;
	@Resource(name="usercourseinfoService")
	private UsercourseinfoService usercourseinfoService;
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService;
	@Resource(name="coursetypeinfoService")
	private CoursetypeinfoService coursetypeinfoService;
	
	@SuppressWarnings({ "static-access", "unused" })
	public String execute(){
		Userinfo userinfo = this.getSessionUser();
		if(userinfo==null)
			return "success";
		userId = userinfo.getUserId();
		List<Studyrecordinfo> list = usercourseinfoService.getendCourse(userId);
		System.out.println("-----"+list.size());
		if(list!=null){
			dataCount = list.size();
		}else{
			dataCount = 0;
			infolist= null;
		}
			all = dataCount;
			System.out.println(all+"ssss");
			//System.out.println(":::"+usercourseinfoService.getStudyRecord(userId).get(0).getCourseName());
			if( pageStart == null || pageStart <= 0 ) {
				pageStart = 1;
			}
			page = new Page(dataCount, pageStart);
			if( pageStart > page.getPageCount() ) {
				pageStart = page.getPageCount();
				page = new Page(dataCount, pageStart);
			}
			int lenth=((pageStart -1 ) * page.getPageRecordNum()+page.getPageRecordNum());
			if(dataCount==0)
			{
				infolist= null;
			}
			else{
				if(dataCount>lenth)
				{
			//		System.out.println((pageStart -1 ) * page.getPageRecordNum()+","+ ((pageStart -1 ) * page.getPageRecordNum()+page.getPageRecordNum())+":::");
					infolist =  list.subList( (pageStart -1 ) * page.getPageRecordNum(), lenth);
				}else
				infolist = list.subList(  (pageStart -1 ) * page.getPageRecordNum(), dataCount);
				System.out.println(infolist.get(0).getCourseName());
				if(dataCount%10==0)
					dataCount = dataCount/10;
				else 
					dataCount = dataCount/10+1;
				
			}
			//类型	
			List<Coursetypeinfo> typeList = coursetypeinfoService.findAllType();
			ServletActionContext.getRequest().setAttribute("typeList", typeList);
			return "success";
		}
	@SuppressWarnings({ "static-access", "unused" })
	public String search(){
		//类型	
		List<Coursetypeinfo> typeList = coursetypeinfoService.findAllType();
		ServletActionContext.getRequest().setAttribute("typeList", typeList);
		Userinfo userinfo = this.getSessionUser();
		if(userinfo==null)
			return "success";
		userId = userinfo.getUserId();
		List<Studyrecordinfo> list = new ArrayList<Studyrecordinfo>();
		if(courseType!=null && !"".equals(courseType.trim()))
			courseType = coursetypeinfoService.findByCourseTypeId(Integer.parseInt(courseType)).get(0).getCourseType();
	//	System.out.println(courseType+"::"+courseName);
		if(courseName.equals("")&&courseType.equals(""))
		{
			list = usercourseinfoService.getendCourse(userId);
		}
		else if(courseName.equals("")&&!courseType.equals("")){
			list = usercourseinfoService.getendCourseByCourseType(courseType, userId);
		}
		else if(!courseName.equals("")&&courseType.equals("")){
			
			list = usercourseinfoService.getendCourseByCourseName(courseName, userId);
		}else{
			list = usercourseinfoService.getendCourseByAll(courseName, courseType, userId);
		}
		List<Studyrecordinfo> end = new ArrayList<Studyrecordinfo>();
		if(list!=null){
			
			dataCount = list.size();
		}else{
			dataCount = 0;
			infolist= null;
			end = null;
		}
		all = dataCount;
		//System.out.println(":::"+usercourseinfoService.getStudyRecord(userId).get(0).getCourseName());
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		}
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			page = new Page(dataCount, pageStart);
		}
		int lenth=((pageStart -1 ) * page.getPageRecordNum()+page.getPageRecordNum());
		if(dataCount==0)
		{
			infolist= null;
		}
		else{
			if(dataCount>lenth)
			{
		//		System.out.println((pageStart -1 ) * page.getPageRecordNum()+","+ ((pageStart -1 ) * page.getPageRecordNum()+page.getPageRecordNum())+":::");
				infolist =  list.subList( (pageStart -1 ) * page.getPageRecordNum(), lenth);
			}else
			infolist = list.subList(  (pageStart -1 ) * page.getPageRecordNum(), dataCount);
		//	System.out.println(infolist.get(0).getCourseName());
			if(dataCount%10==0)
				dataCount = dataCount/10;
			else 
				dataCount = dataCount/10+1;
			
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public int getExamRecord() {
		return examRecord;
	}

	public void setExamRecord(int examRecord) {
		this.examRecord = examRecord;
	}

	public int getStudyStatus() {
		return studyStatus;
	}

	public void setStudyStatus(int studyStatus) {
		this.studyStatus = studyStatus;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public List<Studyrecordinfo> getInfolist() {
		return infolist;
	}

	public void setInfolist(List<Studyrecordinfo> infolist) {
		this.infolist = infolist;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public UsercourseinfoService getUsercourseinfoService() {
		return usercourseinfoService;
	}

	public void setUsercourseinfoService(UsercourseinfoService usercourseinfoService) {
		this.usercourseinfoService = usercourseinfoService;
	}

	public CourseinfoService getCourseinfoService() {
		return courseinfoService;
	}

	public void setCourseinfoService(CourseinfoService courseinfoService) {
		this.courseinfoService = courseinfoService;
	}
	
	
		
}
