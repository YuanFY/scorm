package com.scorm.vo;

/**
 * Coursetypeinfo entity. @author MyEclipse Persistence Tools
 */

public class Coursetypeinfo implements java.io.Serializable {

	// Fields

	private Integer courseTypeId;
	private String courseType;

	// Constructors

	/** default constructor */
	public Coursetypeinfo() {
	}

	/** full constructor */
	public Coursetypeinfo(String courseType) {
		this.courseType = courseType;
	}

	// Property accessors

	public Integer getCourseTypeId() {
		return this.courseTypeId;
	}

	public void setCourseTypeId(Integer courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public String getCourseType() {
		return this.courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

}