package com.scorm.action.admin;

import java.sql.Timestamp;
import java.util.List;

/**
 * 公告信息
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.service.NoticeinfoService;
import com.scorm.utils.Page;
import com.scorm.vo.Noticeinfo;

/**
 * 通告信息的基本操作 
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */
@Scope(value="prototype")
@Component(value="noticeInfoAction")
public class NoticeInfoAction {

	@Resource(name="noticeinfoService")
	private NoticeinfoService noticeinfoService ;
	
	/**
	 * 分页
	 */
	private int dataCount ;
	private Integer pageStart ;
	private Page page ;
	
	/**
	 * 公告信息
	 */
	private int noticeId ;
	private String noticeTitle ;
	private String noticeContent ;
	private String noticeTime ;
	
	/**
	 * 获取所有的公告对象
	 * @return
	 */
	public String execute(){
		System.out.println("welcome 公告管理后台: pageStart="+pageStart);
		
		dataCount = noticeinfoService.findAllNoticeinfo().size();
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		}
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			page = new Page(dataCount, pageStart);
		}
		
		List<Noticeinfo> list = noticeinfoService.getNoticeInfoList(pageStart, page.getPageRecordNum()) ;
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("noticeinfo", list) ;
		return "success";
	}
	
	/**
	 * 通过noticeId查找notice对象 Action
	 * @return
	 */
	public String getNoticeInfoAction() {
		System.out.println("wellcome getNoticeInfoAction: noticeId="+noticeId);
		Noticeinfo noticeinfo = noticeinfoService.findByNoticeId(noticeId).get(0);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("noticeinfo", noticeinfo);
		
		return "success";
	}
	
	/**
	 * 修改公告信息 Action
	 * @return
	 */
	public String updateNoticeInfoAction() {
		System.out.println("wellcome updateNoticeInfoAction: noticeId="+noticeId);
		Noticeinfo noticeinfo = noticeinfoService.findByNoticeId(noticeId).get(0);
		
		// 修改公告信息
		noticeinfo.setNoticeTitle(noticeTitle);
		noticeinfo.setNoticeContent(noticeContent);
		
		ServletActionContext.getRequest().getSession().setAttribute("updateResult", 1) ;
		return "success";
	}
	
	/**
	 * 通过noticeId删除该Id的对象
	 * @return
	 */
	public String delNoticeInfoAction() {
		System.out.println("wellcome delNoticeInfoAction: noticeId="+noticeId);
		
		Noticeinfo noticeinfo = noticeinfoService.findByNoticeId(noticeId).get(0);
		noticeinfoService.deleteNoticeinfo(noticeinfo);
		
		ServletActionContext.getRequest().getSession().setAttribute("delResult", 1);
		return "success";
	}
	
	public String addNoticeAction() {
		System.out.println("wellcome 添加公告： noticeContent="+noticeContent);
		
		Noticeinfo noticeinfo = new Noticeinfo(noticeTitle, noticeContent, new Timestamp(System.currentTimeMillis()));
		noticeinfoService.saveNoticeinfo(noticeinfo);
		return "success";
	}

	public NoticeinfoService getNoticeinfoService() {
		return noticeinfoService;
	}

	public void setNoticeinfoService(NoticeinfoService noticeinfoService) {
		this.noticeinfoService = noticeinfoService;
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

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContetn) {
		this.noticeContent = noticeContetn;
	}

	public String getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}
	
}
