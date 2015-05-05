package com.scorm.vo;

import java.sql.Timestamp;

/**
 * Noticeinfo entity. @author MyEclipse Persistence Tools
 */

public class Noticeinfo implements java.io.Serializable {

	// Fields

	private Integer noticeId;
	private String noticeTitle;
	private String noticeContent;
	private Timestamp noticeTime;

	// Constructors

	/** default constructor */
	public Noticeinfo() {
	}

	/** full constructor */
	public Noticeinfo(String noticeTitle, String noticeContent,
			Timestamp noticeTime) {
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeTime = noticeTime;
	}

	// Property accessors

	public Integer getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return this.noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Timestamp getNoticeTime() {
		return this.noticeTime;
	}

	public void setNoticeTime(Timestamp noticeTime) {
		this.noticeTime = noticeTime;
	}

}