package com.scorm.action.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.service.UserinfoService;
import com.scorm.utils.Page;
import com.scorm.vo.Userinfo;
/**
 * 
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */
@Scope(value="prototype")
@Component(value="userInfoAction")
public class UserInfoAction {

	@Resource(name="userinfoService")
	private UserinfoService userinfoService ;
	
	/**
	 * 分页
	 */
	private int dataCount ;
	private Integer pageStart ;
	private Page page ;
	
	/**
	 * 用户信息
	 */
	private int userId ;
	private String userName ;
	private String userPwd ;
	private String userSex ;
	private String userEmail ;
	private String userPhone ;
	private String loginTime ;
	private int isAdmin ;
	
	/**
	 * 获取所有UserInfo对象
	 * @return
	 */
	public String execute() {
		System.out.println("welcome 个人信息后台");
		dataCount = userinfoService.findAllUserinfo().size();
		if( pageStart == null || pageStart <= 0 ) {
			pageStart = 1;
		}
		page = new Page(dataCount, pageStart);
		if( pageStart > page.getPageCount() ) {
			pageStart = page.getPageCount();
			page = new Page(dataCount, pageStart);
		}
		
		List<Userinfo> list = userinfoService.getUserInfoList(pageStart, page.getPageRecordNum());
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("userinfo", list);
		return "success";
	}
	
	/**
	 * 通过userId获取UserInfo信息
	 * @return
	 */
	public String getUserInfoAction() {
		System.out.println("wellcome getUserInfoAction: userId="+userId);
		
		Userinfo userinfo = userinfoService.findByUserId(userId).get(0);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("userinfo", userinfo);
		System.out.println(userinfo.getUserSex()+","+userinfo.getUserName());
		return "success";
	}
	
	/**
	 * 修改Id为userId的user对象
	 * @return
	 */
	public String updateUserInfoAction() {
		System.out.println("wellcome updateUserInfoAction: userId="+userId+","+userSex);
		
		Userinfo userinfo = userinfoService.findByUserId(userId).get(0);
		userinfo.setUserSex(userSex);
		userinfo.setUserEmail(userEmail);
		userinfo.setUserPhone(userPhone);
		userinfo.setIsAdmin(isAdmin);
		userinfoService.updateUserinfo(userinfo);
		
		ServletActionContext.getRequest().getSession().setAttribute("updateResult", 1);
		
		return "success";
	}

	public UserinfoService getUserinfoService() {
		return userinfoService;
	}

	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
