package com.scorm.vo;

import java.sql.Timestamp;

/**
 * Scoinfo entity. @author MyEclipse Persistence Tools
 */

public class Scoinfo implements java.io.Serializable {

	// Fields

	private Integer scoId;
	private Courseinfo courseinfo;
	private String scoName;
	private Timestamp uploadTime;
	private String uploadAuthor;
	private String launch;
	private Integer clickNum;
	private Integer isTest;

	// Constructors

	/** default constructor */
	public Scoinfo() {
	}

	/** minimal constructor */
	public Scoinfo(Courseinfo courseinfo) {
		this.courseinfo = courseinfo;
	}

	/** full constructor */
	public Scoinfo(Courseinfo courseinfo, String scoName, Timestamp uploadTime,
			String uploadAuthor, String launch, Integer clickNum, Integer isTest) {
		this.courseinfo = courseinfo;
		this.scoName = scoName;
		this.uploadTime = uploadTime;
		this.uploadAuthor = uploadAuthor;
		this.launch = launch;
		this.clickNum = clickNum;
		this.isTest = isTest;
	}

	// Property accessors

	public Integer getScoId() {
		return this.scoId;
	}

	public void setScoId(Integer scoId) {
		this.scoId = scoId;
	}

	public Courseinfo getCourseinfo() {
		return this.courseinfo;
	}

	public void setCourseinfo(Courseinfo courseinfo) {
		this.courseinfo = courseinfo;
	}

	public String getScoName() {
		return this.scoName;
	}

	public void setScoName(String scoName) {
		this.scoName = scoName;
	}

	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadAuthor() {
		return this.uploadAuthor;
	}

	public void setUploadAuthor(String uploadAuthor) {
		this.uploadAuthor = uploadAuthor;
	}

	public String getLaunch() {
		return this.launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	public Integer getClickNum() {
		return this.clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getIsTest() {
		return this.isTest;
	}

	public void setIsTest(Integer isTest) {
		this.isTest = isTest;
	}

}