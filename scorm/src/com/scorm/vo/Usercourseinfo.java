package com.scorm.vo;

import java.sql.Timestamp;

/**
 * Usercourseinfo entity. @author MyEclipse Persistence Tools
 */

public class Usercourseinfo implements java.io.Serializable {

	// Fields

	private UsercourseinfoId id;
	private String courseTime;
	private Integer scoScore;
	private String scoNote;
	private Timestamp viewTime;
	private Integer isFinish;
	private String studyTime;
	private String launch;
	private String scoLoad;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Usercourseinfo() {
	}

	/** minimal constructor */
	public Usercourseinfo(UsercourseinfoId id) {
		this.id = id;
	}

	/** full constructor */
	public Usercourseinfo(UsercourseinfoId id, String courseTime,
			Integer scoScore, String scoNote, Timestamp viewTime,
			Integer isFinish, String studyTime, String launch, String scoLoad,
			Integer isDelete) {
		this.id = id;
		this.courseTime = courseTime;
		this.scoScore = scoScore;
		this.scoNote = scoNote;
		this.viewTime = viewTime;
		this.isFinish = isFinish;
		this.studyTime = studyTime;
		this.launch = launch;
		this.scoLoad = scoLoad;
		this.isDelete = isDelete;
	}

	// Property accessors

	public UsercourseinfoId getId() {
		return this.id;
	}

	public void setId(UsercourseinfoId id) {
		this.id = id;
	}

	public String getCourseTime() {
		return this.courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public Integer getScoScore() {
		return this.scoScore;
	}

	public void setScoScore(Integer scoScore) {
		this.scoScore = scoScore;
	}

	public String getScoNote() {
		return this.scoNote;
	}

	public void setScoNote(String scoNote) {
		this.scoNote = scoNote;
	}

	public Timestamp getViewTime() {
		return this.viewTime;
	}

	public void setViewTime(Timestamp viewTime) {
		this.viewTime = viewTime;
	}

	public Integer getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public String getStudyTime() {
		return this.studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public String getLaunch() {
		return this.launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	public String getScoLoad() {
		return this.scoLoad;
	}

	public void setScoLoad(String scoLoad) {
		this.scoLoad = scoLoad;
	}

	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}