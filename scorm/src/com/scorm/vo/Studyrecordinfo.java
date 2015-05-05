package com.scorm.vo;

public class Studyrecordinfo {
	String courseName;
	String courseType;
	String registerTime;
	String endTime;
//	String studyStatus;
	String studyTime;
	int examRecord;
	int studyStatus;
	String lastTime;
	int isEnd;
	int courseId;
	
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
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
	
	public int getExamRecord() {
		return examRecord;
	}
	public void setExamRecord(int examRecord) {
		this.examRecord = examRecord;
	}
	
	public int isStudyStatus() {
		return studyStatus;
	}
	public void setStudyStatus(int studyStatus) {
		this.studyStatus = studyStatus;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public int getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}
	public int getStudyStatus() {
		return studyStatus;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
}
	