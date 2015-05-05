package com.scorm.action.user;

import java.sql.Timestamp;

import javax.annotation.Resource;

import com.scorm.action.ActionSupport;
import com.scorm.service.DiscussinfoService;
import com.scorm.vo.Discussinfo;
/**
 * 在线讨论基本信息的操作
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */

@SuppressWarnings("serial")
public class DiscussinfoAction extends ActionSupport{
	@Resource(name="discussinfoService")
	DiscussinfoService discussinfoService;
	
	private Integer discussId;
	private Integer courseId;
	private Integer scoId;
	private Integer userId;
	private String userName;
	private String discussContent;
	private Timestamp discussTime;
	private Integer replyUserId;
	private String replyUserName;
	
	private String result;

	/**
	 * 保存讨论信息
	 * @return
	 */
	public String save(){
		Discussinfo discussinfo = new Discussinfo();
		discussinfo.setCourseId(courseId);
		discussinfo.setScoId(scoId);
		discussinfo.setUserId(getSessionUser().getUserId());
		discussinfo.setUserName(getSessionUser().getUserName());
		discussinfo.setDiscussContent(discussContent);
		discussinfo.setDiscussTime(new Timestamp(System.currentTimeMillis()));
		discussinfoService.save(discussinfo);
		
		try {
			this.setResult(json(discussinfoService.findSql("from Discussinfo order by discussId desc")));
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	 
	public Integer getDiscussId() {
		return discussId;
	}

	public void setDiscussId(Integer discussId) {
		this.discussId = discussId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getScoId() {
		return scoId;
	}

	public void setScoId(Integer scoId) {
		this.scoId = scoId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDiscussContent() {
		return discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	public Timestamp getDiscussTime() {
		return discussTime;
	}

	public void setDiscussTime(Timestamp discussTime) {
		this.discussTime = discussTime;
	}

	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
