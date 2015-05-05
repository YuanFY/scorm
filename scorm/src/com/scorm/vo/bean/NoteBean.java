package com.scorm.vo.bean;
 

import java.sql.Timestamp;


public class NoteBean {
	
	private Integer noteId;
	private Integer userId;
	private Integer courseId;
	private String noteTitle;
	private String noteContent;
	private Timestamp noteTime;
	private String userName;
	public Integer getNoteId() {
		return noteId;
	}
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public Timestamp getNoteTime() {
		return noteTime;
	}
	public void setNoteTime(Timestamp noteTime) {
		this.noteTime = noteTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	 
}
