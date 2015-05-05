package com.scorm.vo;

import java.sql.Timestamp;

/**
 * Viewcourse entity. @author MyEclipse Persistence Tools
 */

public class Viewcourse implements java.io.Serializable {

	// Fields

	private Integer viewId;
	private Integer userId;
	private Integer courseId;
	private Integer scoId;
	private String launch;
	private Timestamp viewTime;

	// Constructors

	/** default constructor */
	public Viewcourse() {
	}

	/** full constructor */
	public Viewcourse(Integer userId, Integer courseId, Integer scoId,
			String launch, Timestamp viewTime) {
		this.userId = userId;
		this.courseId = courseId;
		this.scoId = scoId;
		this.launch = launch;
		this.viewTime = viewTime;
	}

	// Property accessors

	public Integer getViewId() {
		return this.viewId;
	}

	public void setViewId(Integer viewId) {
		this.viewId = viewId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getLaunch() {
		return this.launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	public Timestamp getViewTime() {
		return this.viewTime;
	}

	public void setViewTime(Timestamp viewTime) {
		this.viewTime = viewTime;
	}

}