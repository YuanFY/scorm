package com.scorm.action.user;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.NoticeinfoService;
import com.scorm.vo.Noticeinfo;

/**
 * 系统公告Action
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="noticeAction")
public class NoticeAction extends ActionSupport{
	
	@Resource(name="noticeinfoService")
	private NoticeinfoService noticeinfoService;

	private int noticeId ;
	private String noticeTitle;
	private String noticeContent;
	private String noticeTime;
	
	// 信息长度
	private int noticeSize;
	/**
	 * 得到公告列表
	 */
	public String execute() {
		System.out.println("wellcome 系统公告");
		
		List<Noticeinfo> noticeinfo = noticeinfoService.findAllNoticeinfo();
		noticeSize = noticeinfo.size();
		ServletActionContext.getRequest().setAttribute("noticeinfo", noticeinfo);
		return "success";
	}
	
	/**
	 * 课程搜索
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String noticeSearchAction() throws UnsupportedEncodingException {
		System.out.println("wellcome 系统公告搜索： noticeTitle="+noticeTitle);
		
		List<Noticeinfo> noticeinfo = noticeinfoService.findByNoticeTitle(noticeTitle);
		noticeSize = noticeinfo.size();
		ServletActionContext.getRequest().setAttribute("noticeinfo", noticeinfo);
		return "success";
	}
	
	public String getNoticeInfoAction() {
		System.out.println("wellcome 公告内容： noticeId="+noticeId);
		
		Noticeinfo noticeinfo = noticeinfoService.findByNoticeId(noticeId).get(0);
		ServletActionContext.getRequest().setAttribute("noticeinfo", noticeinfo);
		return "success";
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

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}

	public int getNoticeSize() {
		return noticeSize;
	}

	public void setNoticeSize(int noticeSize) {
		this.noticeSize = noticeSize;
	}
	
}
