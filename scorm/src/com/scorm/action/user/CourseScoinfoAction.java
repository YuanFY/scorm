package com.scorm.action.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.CourseregService;
import com.scorm.service.NoteinfoService;
import com.scorm.service.ScoinfoService;
import com.scorm.service.UsercourseinfoService;
import com.scorm.utils.UserUtils;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Coursereg;
import com.scorm.vo.CourseregId;
import com.scorm.vo.Noteinfo;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.Usercourseinfo;
import com.scorm.vo.Userinfo;

/**
 * 查看课程课件及笔记
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="courseScoinfoAction")
public class CourseScoinfoAction extends ActionSupport{

	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService;
	
	@Resource(name="courseregService")
	private CourseregService courseregService;
	
	@Resource(name="scoinfoService")
	private ScoinfoService scoinfoService;
	
	@Resource(name="noteinfoService")
	private NoteinfoService noteinfoService;
	
	@Resource(name="usercourseinfoService")
	private UsercourseinfoService usercourseinfoService;
	
	// 课程信息
	private int courseId;
	private String courseName;
	private String courseType;
	private String courseContent;
	private int registerNumber;
	private String uploadTime;
	private Integer noteId;
	private String noteContent;
	
	// 课件信息
	private int scoId;
	private String scoName;
	private String uploadAuthor;
	private int clickNum;
	
	// 用户学习信息
	private int userId;
	private String courseTime;
	private int scoScore;
	private String scoNote;
	private String viewTime;
	private int isFinish;
	private String studyTime;
	private String scoLoad;
	
	private int result;
	
	public String execute() {
		System.out.println("wellcome 课程课件： courseId="+courseId+",userId="+userId);
		
		//推荐课程
		UserUtils utils = new UserUtils();
		@SuppressWarnings("static-access")
		List<Courseinfo> recommendList = utils.recommend();
		ServletActionContext.getRequest().setAttribute("recommendList", recommendList);
		
		 //注册课程
		List<Coursereg> list = courseregService.findById(userId, courseId);
		if(list==null||list.size()==0){
			Userinfo cU = new Userinfo();
			cU.setUserId(userId);
			Courseinfo cc = new Courseinfo();
			cc.setCourseId(courseId);
			CourseregId cID = new CourseregId(cU,cc);
			Coursereg cr = new Coursereg(cID,new Timestamp(System.currentTimeMillis()),0,-1);
			courseregService.saveCoursereg(cr);
		}
		
		// 课程课件信息
		Courseinfo courseinfo = courseinfoService.findByCourseId(courseId).get(0);
		List<Scoinfo> scoinfo = scoinfoService.findByCourseId(courseId);
		Coursereg coursereg = courseregService.findById(userId, courseId).get(0);
		List<Usercourseinfo> usercourseinfo = usercourseinfoService.getCourseStudy(userId, courseId);
		
		System.out.println(scoinfo.size());
		
		ServletActionContext.getRequest().setAttribute("courseinfo", courseinfo);
		ServletActionContext.getRequest().setAttribute("scoinfo", scoinfo);
		ServletActionContext.getRequest().setAttribute("coursereg", coursereg);
		ServletActionContext.getRequest().setAttribute("usercourseinfo", usercourseinfo);
		
		// 相关课程
		System.out.println("wellcome 相关课程: courseType="+courseinfo.getCourseType());
		List<Courseinfo> reCourseinfo = courseinfoService.findByCourseType(courseinfo.getCourseType());
		System.out.println("reCourseinfo="+reCourseinfo);
		reCourseinfo.remove(courseinfo);
		ServletActionContext.getRequest().setAttribute("reCourseinfo", reCourseinfo);
		return "success";
	}
	
	public String updateUserCouse(){
		
		System.out.println("更新课件评分：scoScore="+scoScore+",userId="+userId+",courseId="+courseId+",scoId=scoId");
		List<Coursereg> list = courseregService.findById(userId, courseId);
		if(list!=null && list.size()>0){
			Coursereg u = list.get(0);
			u.setCourseScore(scoScore);
			courseregService.update(u);
			ServletActionContext.getRequest().setAttribute("coursereg", u);
			result = 1;
		}else{
			result = 0;
		}
		// 课程课件信息
		Courseinfo courseinfo = courseinfoService.findByCourseId(courseId).get(0);
		List<Scoinfo> scoinfo = scoinfoService.findByCourseId(courseId);
		List<Usercourseinfo> usercourseinfo = usercourseinfoService.getCourseStudy(userId, courseId);
		
		System.out.println(usercourseinfo.size());
		
		ServletActionContext.getRequest().setAttribute("courseinfo", courseinfo);
		ServletActionContext.getRequest().setAttribute("scoinfo", scoinfo);
		ServletActionContext.getRequest().setAttribute("usercourseinfo", usercourseinfo);
		
		// 相关课程
		System.out.println("wellcome 相关课程: courseType="+courseinfo.getCourseType());
		List<Courseinfo> reCourseinfo = courseinfoService.findByCourseType(courseinfo.getCourseType());
		System.out.println("reCourseinfo="+reCourseinfo);
		reCourseinfo.remove(courseinfo);
		ServletActionContext.getRequest().setAttribute("reCourseinfo", reCourseinfo);
		
		//推荐课程
		UserUtils utils = new UserUtils();
		@SuppressWarnings("static-access")
		List<Courseinfo> recommendList = utils.recommend();
		ServletActionContext.getRequest().setAttribute("recommendList", recommendList);
		
		return "success";
	}
	
	/**
	 * 获取课程学习笔记列表
	 * @return
	 */
	public String getCourseNoteAction() {
		System.out.println("wellcome 课程笔记: courseId="+courseId+",userId="+userId);
		
		// 获得课程笔记
		List<Noteinfo> noteinfo = noteinfoService.findSql("from Noteinfo where userId="+userId+" and courseId="+courseId + " order by noteId desc");
		 
		courseName = courseinfoService.findByCourseId(courseId).get(0).getCourseName();
		ServletActionContext.getRequest().setAttribute("noteinfo", noteinfo);
		return "success";
	}
	
	public String getScoNoteAction() {
		
		Noteinfo noteinfo = noteinfoService.findSql("from Noteinfo where noteId="+noteId).get(0);
		ServletActionContext.getRequest().setAttribute("noteinfo", noteinfo);
		System.out.println("具体课件笔记："+noteinfo.getNoteContent());
		return "success";
	}
	
	public String saveScoNoteAction() {
		System.out.println("wellcome 保存课件笔记： courseId="+courseId+",userId="+userId+",scoId="+scoId);
		
		Noteinfo noteinfo = noteinfoService.findSql("from Noteinfo where noteId="+noteId).get(0);
		noteinfo.setNoteContent(noteContent);
		noteinfo.setNoteTime(new Timestamp(System.currentTimeMillis()));
		noteinfoService.update(noteinfo);
		
		ServletActionContext.getRequest().setAttribute("noteinfo", noteinfo);
		ServletActionContext.getRequest().setAttribute("updateResult", 1);
		return "success";
	}
	
	@SuppressWarnings("deprecation")
	public String  lookCourseinfo(){
		System.out.println("wellcome 课程课件： courseId="+courseId+",userId="+userId);
		
		// 课程课件信息
		Courseinfo courseinfo = courseinfoService.findByCourseId(courseId).get(0);
		courseinfo.getUploadTime().setYear(courseinfo.getUploadTime().getYear()+1);
		ServletActionContext.getRequest().setAttribute("courseinfo", courseinfo);
		
		//查看是否已注册
		List<Coursereg> regList = courseregService.findById(userId, courseId);
		boolean flag = true;
		if(regList != null && regList.size()>0){
			flag = !flag;
		}
		ServletActionContext.getRequest().setAttribute("flag", flag);
	
		//推荐课程
		UserUtils utils = new UserUtils();
		@SuppressWarnings("static-access")
		List<Courseinfo> recommendList = utils.recommend();
		ServletActionContext.getRequest().setAttribute("recommendList", recommendList);
		
		// 相关课程
		System.out.println("wellcome 相关课程: courseType="+courseinfo.getCourseType());
		List<Courseinfo> reCourseinfo = courseinfoService.findByCourseType(courseinfo.getCourseType());
		List<Courseinfo> list = new ArrayList<Courseinfo>();
		int l = reCourseinfo.size();
		for(int i=0; i<l&&l!=1; i++)
			if(reCourseinfo.get(i).getCourseId()!=courseinfo.getCourseId())
				list.add(reCourseinfo.get(i));
		reCourseinfo = list;
		System.out.println(reCourseinfo.size());
		ServletActionContext.getRequest().setAttribute("list", reCourseinfo);
		
		return "success";
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

	public int getScoId() {
		return scoId;
	}

	public void setScoId(int scoId) {
		this.scoId = scoId;
	}

	public String getScoName() {
		return scoName;
	}

	public void setScoName(String scoName) {
		this.scoName = scoName;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public int getScoScore() {
		return scoScore;
	}

	public void setScoScore(int scoScore) {
		this.scoScore = scoScore;
	}

	public String getScoNote() {
		return scoNote;
	}

	public void setScoNote(String scoNote) {
		this.scoNote = scoNote;
	}

	public String getViewTime() {
		return viewTime;
	}

	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}

	public int getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}

	public String getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public String getScoLoad() {
		return scoLoad;
	}

	public void setScoLoad(String scoLoad) {
		this.scoLoad = scoLoad;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	
}
