package com.scorm.vo;

/**
 * CourseregId entity. @author MyEclipse Persistence Tools
 */

public class CourseregId implements java.io.Serializable {

	// Fields

	private Userinfo userinfo;
	private Courseinfo courseinfo;

	// Constructors

	/** default constructor */
	public CourseregId() {
	}

	/** full constructor */
	public CourseregId(Userinfo userinfo, Courseinfo courseinfo) {
		this.userinfo = userinfo;
		this.courseinfo = courseinfo;
	}

	// Property accessors

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Courseinfo getCourseinfo() {
		return this.courseinfo;
	}

	public void setCourseinfo(Courseinfo courseinfo) {
		this.courseinfo = courseinfo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CourseregId))
			return false;
		CourseregId castOther = (CourseregId) other;

		return ((this.getUserinfo() == castOther.getUserinfo()) || (this
				.getUserinfo() != null && castOther.getUserinfo() != null && this
				.getUserinfo().equals(castOther.getUserinfo())))
				&& ((this.getCourseinfo() == castOther.getCourseinfo()) || (this
						.getCourseinfo() != null
						&& castOther.getCourseinfo() != null && this
						.getCourseinfo().equals(castOther.getCourseinfo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserinfo() == null ? 0 : this.getUserinfo().hashCode());
		result = 37
				* result
				+ (getCourseinfo() == null ? 0 : this.getCourseinfo()
						.hashCode());
		return result;
	}

}