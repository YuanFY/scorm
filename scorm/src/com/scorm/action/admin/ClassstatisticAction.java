package com.scorm.action.admin;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.UsercourseinfoService;
import com.scorm.utils.Page;
import com.scorm.vo.Classstatisticinfo;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.Userinfo;

/**
 * 
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */
@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="classstatisticAction")
public class ClassstatisticAction extends ActionSupport{
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
	
	@Resource(name="usercourseinfoService")
	private UsercourseinfoService usercourseinfoService;
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
				infolist = usercourseinfoService.getCourseStatistic().subList( (pageStart -1 ) * page.getPageRecordNum(), lenth);
			}else
			infolist =  usercourseinfoService.getCourseStatistic().subList(  (pageStart -1 ) * page.getPageRecordNum(), dataCount);
		   xmlData = " <graph caption='按课程 注册总数统计' xAxisName='课程名' yAxisName='注册数' showNames='1' decimalPrecision='2' formatNumberScale='0'>";
		   for (Classstatisticinfo info : infolist) {
				xmlData+="<set name = '"+info.getCourseName()+"' value='"+info.getNumber()+"'  />";
		   }
			xmlData += "</graph>";
			if(dataCount%10==0)
				dataCount = dataCount/10;
			else 
				dataCount = dataCount/10+1;
		}
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
	

}
