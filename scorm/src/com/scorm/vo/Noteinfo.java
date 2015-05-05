package com.scorm.vo;

import java.sql.Timestamp;

/**
 * Noteinfo entity. @author MyEclipse Persistence Tools
 */

public class Noteinfo implements java.io.Serializable {

	// Fields

	private Integer noteId;
	private Integer userId;
	private Integer courseId;
	private String noteTitle;
	private String noteContent;
	private Timestamp noteTime;

	// Constructors

	/** default constructor */
	public Noteinfo() {
	}

	/** minimal constructor */
	public Noteinfo(Integer userId, Integer courseId) {
		this.userId = userId;
		this.courseId = courseId;
	}

	/** full constructor */
	public Noteinfo(Integer userId, Integer courseId, String noteTitle,
			String noteContent, Timestamp noteTime) {
		this.userId = userId;
		this.courseId = courseId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteTime = noteTime;
	}

	// Property accessors

	public Integer getNoteId() {
		return this.noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
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

	public String getNoteTitle() {
		return this.noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return this.noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public Timestamp getNoteTime() {
		return this.noteTime;
	}

	public void setNoteTime(Timestamp noteTime) {
		this.noteTime = noteTime;
	}

}