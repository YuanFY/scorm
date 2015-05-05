package com.scorm.vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Courseinfo entity. @author MyEclipse Persistence Tools
 */

public class Courseinfo implements java.io.Serializable {

	// Fields

	private Integer courseId;
	private String courseName;
	private String courseType;
	private String courseContent;
	private Integer registerNumber;
	private Timestamp uploadTime;
	private String coursePicture;
	private Set scoinfos = new HashSet(0);
	private Set usercourseinfos = new HashSet(0);
	private Set courseregs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Courseinfo() {
	}

	/** full constructor */
	public Courseinfo(String courseName, String courseType,
			String courseContent, Integer registerNumber, Timestamp uploadTime,
			String coursePicture, Set scoinfos, Set usercourseinfos,
			Set courseregs) {
		this.courseName = courseName;
		this.courseType = courseType;
		this.courseContent = courseContent;
		this.registerNumber = registerNumber;
		this.uploadTime = uploadTime;
		this.coursePicture = coursePicture;
		this.scoinfos = scoinfos;
		this.usercourseinfos = usercourseinfos;
		this.courseregs = courseregs;
	}

	// Property accessors

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseType() {
		return this.courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseContent() {
		return this.courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public Integer getRegisterNumber() {
		return this.registerNumber;
	}

	public void setRegisterNumber(Integer registerNumber) {
		this.registerNumber = registerNumber;
	}

	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getCoursePicture() {
		return this.coursePicture;
	}

	public void setCoursePicture(String coursePicture) {
		this.coursePicture = coursePicture;
	}

	public Set getScoinfos() {
		return this.scoinfos;
	}

	public void setScoinfos(Set scoinfos) {
		this.scoinfos = scoinfos;
	}

	public Set getUsercourseinfos() {
		return this.usercourseinfos;
	}

	public void setUsercourseinfos(Set usercourseinfos) {
		this.usercourseinfos = usercourseinfos;
	}

	public Set getCourseregs() {
		return this.courseregs;
	}

	public void setCourseregs(Set courseregs) {
		this.courseregs = courseregs;
	}

}