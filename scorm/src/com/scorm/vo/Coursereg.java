package com.scorm.vo;

import java.sql.Timestamp;

/**
 * Coursereg entity. @author MyEclipse Persistence Tools
 */

public class Coursereg implements java.io.Serializable {

	// Fields

	private CourseregId id;
	private Timestamp registerTime;
	private Integer examScore;
	private Integer courseScore;

	// Constructors

	/** default constructor */
	public Coursereg() {
	}

	/** minimal constructor */
	public Coursereg(CourseregId id) {
		this.id = id;
	}

	/** full constructor */
	public Coursereg(CourseregId id, Timestamp registerTime, Integer examScore,
			Integer courseScore) {
		this.id = id;
		this.registerTime = registerTime;
		this.examScore = examScore;
		this.courseScore = courseScore;
	}

	// Property accessors

	public CourseregId getId() {
		return this.id;
	}

	public void setId(CourseregId id) {
		this.id = id;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getExamScore() {
		return this.examScore;
	}

	public void setExamScore(Integer examScore) {
		this.examScore = examScore;
	}

	public Integer getCourseScore() {
		return this.courseScore;
	}

	public void setCourseScore(Integer courseScore) {
		this.courseScore = courseScore;
	}

}