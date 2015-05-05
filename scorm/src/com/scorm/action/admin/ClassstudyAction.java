package com.scorm.action.admin;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.UsercourseinfoService;
import com.scorm.utils.Page;
import com.scorm.vo.Classstatisticinfo;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.Userinfo;

/**
 * 
 * @author YuanFY
 * @date 2014-8-15下午5:23:49
 * @Description:TODO
 * @version V1.0
 */
@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="classstudyAction")
public class ClassstudyAction extends ActionSupport{
	/**分页
	 * 
	 */
	private int dataCount;//总记录数
	private Integer pageStart;//当前页吗
	private Page page;
	/**
	 * 课程信息
	 */
	private String xmlData;
	private List<Classstatisticinfo> infolist ;
	private Userinfo userinfo;
	private Courseinfo courseinfo;
	private Scoinfo scoinfo;
	private int scoScore;
	private String courseName;
	private String studyTime;
	private int courseId;
	@Resource(name="usercourseinfoService")
	private UsercourseinfoService usercourseinfoService;
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService;
	public String execute(){
		if( usercourseinfoService.getCourseStatistic()!=null){
			dataCount = usercourseinfoService.getCourseStatistic().size();
		}
		else dataCount = 0;
	//	System.out.println(dataCount);
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
				infolist =  usercourseinfoService.getCourseStatistic().subList( (pageStart -1 ) * page.getPageRecordNum(), lenth);
			}else
			infolist =  usercourseinfoService.getCourseStatistic().subList(  (pageStart -1 ) * page.getPageRecordNum(), dataCount);
		   
		    xmlData = "<chart caption='按课程 观看时长统计' xAxisName='课程名' yAxisName='观看时长' showLabels='1' showvalues='0' decimals='0' numberPrefix=''>";
		    xmlData += "<categories>";
			for (Classstatisticinfo info : infolist) {
				xmlData += "<category label='"+info.getCourseName()+"' />";
			}
		    xmlData += "</categories>";
		   xmlData +="<dataset seriesName=''  showValues='0'>";
		   for (Classstatisticinfo info : infolist) {
			   String s1[] = info.getStudyTime().split(":");
			   int s = Integer.parseInt(s1[0]);
				xmlData+="<set value='"+s+"' />";
				
		   }
			xmlData += "</dataset>";
			xmlData += "</chart>";
			if(dataCount%10==0)
				dataCount = dataCount/10;
			else 
				dataCount = dataCount/10+1;
		}
		return "success";
	}
	public String findSco(){
		courseName = courseinfoService.findByCourseId(courseId).get(0).getCourseName();
		dataCount = usercourseinfoService.getSco(courseName).size();
	//	System.out.println(dataCount);
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		}
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			
			page = new Page(dataCount, pageStart);
		}
		int lenth=((pageStart -1 ) * page.getPageRecordNum()+page.getPageRecordNum());
		if(dataCount>lenth)
		{
	//		System.out.println((pageStart -1 ) * page.getPageRecordNum()+","+ ((pageStart -1 ) * page.getPageRecordNum()+page.getPageRecordNum())+":::");
			infolist = usercourseinfoService.getSco(courseName).subList( (pageStart -1 ) * page.getPageRecordNum(), lenth);
		}else
		infolist = usercourseinfoService.getSco(courseName).subList(  (pageStart -1 ) * page.getPageRecordNum(), dataCount);
	   
	    xmlData = "<chart caption='按课程观看时长统计' xAxisName='课件名' yAxisName='观看时长' showLabels='1' showvalues='0' decimals='0' numberPrefix=''>";
	    xmlData += "<categories>";
		for (Classstatisticinfo info : infolist) {
			xmlData += "<category label='"+info.getScoName()+"' />";
		}
	    xmlData += "</categories>";

	   xmlData +="<dataset seriesName=''  showValues='0'>";
	   for (Classstatisticinfo info : infolist) {
		   String s1[] = info.getStudyTime().split(":");
		   int s = Integer.parseInt(s1[0]);
			xmlData+="<set value='"+s+"' />";
	   }
		xmlData += "</dataset>";
		xmlData += "</chart>";
		if(dataCount%10==0)
			dataCount = dataCount/10;
		else 
			dataCount = dataCount/10+1;
		return "success";
	}
	
	public String getXmlData() {
		return xmlData;
	}
	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}
	public List<Classstatisticinfo> getInfolist() {
		return infolist;
	}
	public void setInfolist(List<Classstatisticinfo> infolist) {
		this.infolist = infolist;
	}
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public Courseinfo getCourseinfo() {
		return courseinfo;
	}
	public void setCourseinfo(Courseinfo courseinfo) {
		this.courseinfo = courseinfo;
	}
	public Scoinfo getScoinfo() {
		return scoinfo;
	}
	public void setScoinfo(Scoinfo scoinfo) {
		this.scoinfo = scoinfo;
	}
	public UsercourseinfoService getUsercourseinfoService() {
		return usercourseinfoService;
	}
	public void setUsercourseinfoService(UsercourseinfoService usercourseinfoService) {
		this.usercourseinfoService = usercourseinfoService;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getScoScore() {
		return scoScore;
	}
	public void setScoScore(int scoScore) {
		this.scoScore = scoScore;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
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
	public CourseinfoService getCourseinfoService() {
		return courseinfoService;
	}
	public void setCourseinfoService(CourseinfoService courseinfoService) {
		this.courseinfoService = courseinfoService;
	}
	

}
