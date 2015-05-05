package com.scorm.vo.bean;

import java.sql.Timestamp;

public class ScoBean {
	private Integer scoId;
	private String scoName;
	private Integer courseId;
	private String courseName;
	private Timestamp uploadTime;
	private String uploadAuthor;
	private Integer likeNum;
	private String courseType;
	
	public Integer getScoId() {
		return scoId;
	}
	public void setScoId(Integer scoId) {
		this.scoId = scoId;
	}
	public String getScoName() {
		return scoName;
	}
	public void setScoName(String scoName) {
		this.scoName = scoName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getUploadAuthor() {
		return uploadAuthor;
	}
	public void setUploadAuthor(String uploadAuthor) {
		this.uploadAuthor = uploadAuthor;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
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
	
}
