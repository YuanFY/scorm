package com.scorm.vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String userPwd;
	private String userSex;
	private String userEmail;
	private String userPhone;
	private Integer userActive;
	private Integer isAdmin;
	private Timestamp loginTime;
	private Set usercourseinfos = new HashSet(0);
	private Set courseregs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** full constructor */
	public Userinfo(String userName, String userPwd, String userSex,
			String userEmail, String userPhone, Integer userActive,
			Integer isAdmin, Timestamp loginTime) {
		this.userName = userName;
		this.userPwd = userPwd;
		this.userSex = userSex;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userActive = userActive;
		this.isAdmin = isAdmin;
		this.loginTime = loginTime;
	}
	/** full constructor */
	public Userinfo(String userName, String userPwd, String userSex,
			String userEmail, String userPhone, Integer userActive,
			Integer isAdmin, Timestamp loginTime, Set usercourseinfos,
			Set courseregs) {
		this.userName = userName;
		this.userPwd = userPwd;
		this.userSex = userSex;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userActive = userActive;
		this.isAdmin = isAdmin;
		this.loginTime = loginTime;
		this.usercourseinfos = usercourseinfos;
		this.courseregs = courseregs;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getUserActive() {
		return this.userActive;
	}

	public void setUserActive(Integer userActive) {
		this.userActive = userActive;
	}

	public Integer getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
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