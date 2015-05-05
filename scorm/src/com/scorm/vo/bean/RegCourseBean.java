package com.scorm.vo.bean;
 

import com.scorm.vo.Courseinfo;

public class RegCourseBean {
	
	private boolean regFlag;
	private Courseinfo courseinfo;
	public boolean isRegFlag() {
		return regFlag;
	}
	public void setRegFlag(boolean regFlag) {
		this.regFlag = regFlag;
	}
	public Courseinfo getCourseinfo() {
		return courseinfo;
	}
	public void setCourseinfo(Courseinfo courseinfo) {
		this.courseinfo = courseinfo;
	}
	

	 
}
