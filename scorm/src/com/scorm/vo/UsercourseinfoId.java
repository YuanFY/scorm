package com.scorm.vo;

/**
 * UsercourseinfoId entity. @author MyEclipse Persistence Tools
 */

public class UsercourseinfoId implements java.io.Serializable {

	// Fields

	private Userinfo userinfo;
	private Courseinfo courseinfo;
	private Integer scoId;

	// Constructors

	/** default constructor */
	public UsercourseinfoId() {
	}

	/** full constructor */
	public UsercourseinfoId(Userinfo userinfo, Courseinfo courseinfo,
			Integer scoId) {
		this.userinfo = userinfo;
		this.courseinfo = courseinfo;
		this.scoId = scoId;
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

	public Integer getScoId() {
		return this.scoId;
	}

	public void setScoId(Integer scoId) {
		this.scoId = scoId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsercourseinfoId))
			return false;
		UsercourseinfoId castOther = (UsercourseinfoId) other;

		return ((this.getUserinfo() == castOther.getUserinfo()) || (this
				.getUserinfo() != null && castOther.getUserinfo() != null && this
				.getUserinfo().equals(castOther.getUserinfo())))
				&& ((this.getCourseinfo() == castOther.getCourseinfo()) || (this
						.getCourseinfo() != null
						&& castOther.getCourseinfo() != null && this
						.getCourseinfo().equals(castOther.getCourseinfo())))
				&& ((this.getScoId() == castOther.getScoId()) || (this
						.getScoId() != null && castOther.getScoId() != null && this
						.getScoId().equals(castOther.getScoId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserinfo() == null ? 0 : this.getUserinfo().hashCode());
		result = 37
				* result
				+ (getCourseinfo() == null ? 0 : this.getCourseinfo()
						.hashCode());
		result = 37 * result
				+ (getScoId() == null ? 0 : this.getScoId().hashCode());
		return result;
	}

}