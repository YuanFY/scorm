package com.scorm.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.ScoinfoService;
import com.scorm.service.UsercourseinfoService;
import com.scorm.service.UserinfoService;
import com.scorm.service.ViewcourseService;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.Usercourseinfo;
import com.scorm.vo.Userinfo;
import com.scorm.vo.Viewcourse;
import com.scorm.vo.bean.ScoBean;

/**
 * 个人信息
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="myCenterAction")
public class MyCenterAction extends ActionSupport {

	@Resource(name="userinfoService")
	private UserinfoService userinfoService;
	
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService;
	
	@Resource(name="scoinfoService")
	private ScoinfoService scoinfoService;
	
	@Resource(name="usercourseinfoService")
	private UsercourseinfoService usercourseinfoService;
	
	@Resource(name="viewcourseService")
	private ViewcourseService viewcourseService;
	
	/**
	 * 个人资料
	 */
	private int userId;
	private String userName;
	private String userPwd;
	private String userSex;
	private String userEmail;
	private String userPhone;
	private int isAdmin;
	private String loginTime;
	private Integer scoId;
	
	// 修改密码
	private String userNewPwd;
	private String userNewPwdCheck;
	
	public String execute() {
		System.out.println("wellcome myCenter-Userinfo: userId:"+userId);
		Userinfo userinfo = userinfoService.findByUserId(userId).get(0);
		System.out.println(userinfo.getIsAdmin()+" "+userinfo.getLoginTime());
		ServletActionContext.getRequest().getSession().setAttribute("userinfo", userinfo);
		return "success";
	}
	/**
	 * 更新自己的个人信息
	 * @return
	 */
	public String updateMyUserinfoAction() {
		System.out.println("wellcome updateMyUserinfo: userId:"+userId);
		System.out.println(userPhone+" "+userEmail+" "+userSex);
		Userinfo userinfo = userinfoService.findByUserId(userId).get(0);
		userinfo.setUserEmail(userEmail);
		userinfo.setUserPhone(userPhone);
		userinfo.setUserSex(userSex);
		userinfoService.updateUserinfo(userinfo);
		
		ServletActionContext.getRequest().setAttribute("updateResult", 1);
		return "success";
	}
	
	/**
	 * 个人中心修改密码
	 * @return
	 */
	public String myCenterPwdUpdateAction() {
		System.out.println("wellcome myCenterPwdUpdate: userId"+userId);
		
		Userinfo userinfo = userinfoService.findByUserId(userId).get(0);
		System.out.println("isAdmin="+userinfo.getIsAdmin());
		ServletActionContext.getRequest().setAttribute("userinfo", userinfo);
		return "success";
	}
	
	public String updateMyUserPwdAction() {
		System.out.println("wellcome updateMyUserPwd: userId:"+userId);
		System.out.println(userPwd+" "+userNewPwd+" "+userNewPwdCheck);
		
		Userinfo userinfo = userinfoService.findByUserId(userId).get(0);
		if( ! userinfo.getUserPwd().equals(userPwd) ) {
			// 旧密码错误
			ServletActionContext.getRequest().setAttribute("updateResult", 0) ;
		} else if( ! userNewPwd.equals(userNewPwdCheck) ){
			// 新密码与重复新密码一致
			ServletActionContext.getRequest().setAttribute("updateResult", -1) ;
		} else {
			// 正确
			userinfo.setUserPwd(userNewPwd);
			userinfoService.updateUserinfo(userinfo);
			ServletActionContext.getRequest().setAttribute("updateResult", 1) ;
		}
		return "success";
	}
	/**
	 * 上传课件
	 * @return
	 */
	public String myCenterScoUploadAction() {
		System.out.println("wellcome myCenterScoUpload: ");
		List<Courseinfo> courseinfo = courseinfoService.findAllCourseinfo();
		ServletActionContext.getRequest().setAttribute("courseinfo", courseinfo) ;
		return "success";
	}

	/**
	 * 得到课件相关信息
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String myCenterSco(){
		System.out.println("教师管理中心-课件管理:");
		List<Scoinfo> list = scoinfoService.findByUploadAuthor(this.getSessionUser().getUserName());
		List<ScoBean> scoList = new ArrayList<ScoBean>();
		if(list!=null && list.size()>0)
		for(int i=list.size()-1; i>=0 ; i--){
			ScoBean bean = new ScoBean();
			bean.setCourseId(list.get(i).getCourseinfo().getCourseId());
			bean.setScoId(list.get(i).getScoId());
			bean.setScoName(list.get(i).getScoName());
			bean.setUploadTime(list.get(i).getUploadTime());
			bean.setUploadAuthor(list.get(i).getUploadAuthor());
			
			List<Courseinfo> cList = courseinfoService.findByCourseId(list.get(i).getCourseinfo().getCourseId());
			bean.setCourseType(cList.get(0).getCourseType());
			
			System.out.println("courseName="+list.get(i).getCourseinfo().getCourseName());
			List<Usercourseinfo> l = usercourseinfoService.findByScoId(list.get(i).getScoId());
			int likeNum = 0;
			if(l!=null)
				likeNum = l.size();
			bean.setLikeNum(likeNum);
			bean.setCourseName(cList.get(0).getCourseName());
			scoList.add(bean);
		}
		ServletActionContext.getRequest().setAttribute("scoList", scoList);
 		return "success";
	}
	
	/**
	 * 删除课件
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String delSco(){
		System.out.println(scoId);
		
		System.out.println("wellcome delScoAction: scoId="+scoId);
		Scoinfo scoinfo = scoinfoService.findByScoId(scoId).get(0);
		List<Usercourseinfo> ucList = usercourseinfoService.findByScoId(scoId);
		for(Usercourseinfo u : ucList){
			if(u!=null){
				usercourseinfoService.deleteUsercourseinfo(u);
			}
		}
		List<Viewcourse> viewList = viewcourseService.findBysql("from Viewcourse where scoId = "+scoId);
		for(Viewcourse u : viewList){
			if(u!=null){
				viewcourseService.delete(u);
			}
		}
		scoinfoService.deleteScoinfo(scoinfo);
		
		List<Scoinfo> list = scoinfoService.findByUploadAuthor(this.getSessionUser().getUserName());
		List<ScoBean> scoList = new ArrayList<ScoBean>();
		for(Scoinfo sco : list){
			ScoBean bean = new ScoBean();
			bean.setCourseId(sco.getCourseinfo().getCourseId());
			bean.setScoId(sco.getScoId());
			bean.setScoName(sco.getScoName());
			bean.setUploadTime(sco.getUploadTime());
			bean.setUploadAuthor(sco.getUploadAuthor());
			
			List<Courseinfo> cList = courseinfoService.findByCourseId(sco.getCourseinfo().getCourseId());
			bean.setCourseType(cList.get(0).getCourseType());
			
			System.out.println("courseName="+sco.getCourseinfo().getCourseName());
			List<Usercourseinfo> l = usercourseinfoService.findByScoId(sco.getScoId());
			int likeNum = 0;
			if(l!=null)
				likeNum = l.size();
			bean.setLikeNum(likeNum);
			bean.setCourseName(cList.get(0).getCourseName());
			scoList.add(bean);
		}
		ServletActionContext.getRequest().setAttribute("scoList", scoList);
		return "success";
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

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getUserNewPwd() {
		return userNewPwd;
	}

	public void setUserNewPwd(String userNewPwd) {
		this.userNewPwd = userNewPwd;
	}

	public String getUserNewPwdCheck() {
		return userNewPwdCheck;
	}

	public void setUserNewPwdCheck(String userNewPwdCheck) {
		this.userNewPwdCheck = userNewPwdCheck;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Integer getScoId() {
		return scoId;
	}
	public void setScoId(Integer scoId) {
		this.scoId = scoId;
	}
	
}
