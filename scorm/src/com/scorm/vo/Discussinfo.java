package com.scorm.vo;

import java.sql.Timestamp;

/**
 * Discussinfo entity. @author MyEclipse Persistence Tools
 */

public class Discussinfo implements java.io.Serializable {

	// Fields

	private Integer discussId;
	private Integer courseId;
	private Integer scoId;
	private Integer userId;
	private String userName;
	private String discussContent;
	private Timestamp discussTime;
	private Integer replyUserId;
	private String replyUserName;

	// Constructors

	/** default constructor */
	public Discussinfo() {
	}

	/** full constructor */
	public Discussinfo(Integer courseId, Integer scoId, Integer userId,
			String userName, String discussContent, Timestamp discussTime,
			Integer replyUserId, String replyUserName) {
		this.courseId = courseId;
		this.scoId = scoId;
		this.userId = userId;
		this.userName = userName;
		this.discussContent = discussContent;
		this.discussTime = discussTime;
		this.replyUserId = replyUserId;
		this.replyUserName = replyUserName;
	}

	// Property accessors

	public Integer getDiscussId() {
		return this.discussId;
	}

	public void setDiscussId(Integer discussId) {
		this.discussId = discussId;
	}

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getScoId() {
		return this.scoId;
	}

	public void setScoId(Integer scoId) {
		this.scoId = scoId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDiscussContent() {
		return this.discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	public Timestamp getDiscussTime() {
		return this.discussTime;
	}

	public void setDiscussTime(Timestamp discussTime) {
		this.discussTime = discussTime;
	}

	public Integer getReplyUserId() {
		return this.replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getReplyUserName() {
		return this.replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

}