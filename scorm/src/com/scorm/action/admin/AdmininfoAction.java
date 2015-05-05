package com.scorm.action.admin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.UserinfoService;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Userinfo;

/**
 * 管理员action层
 * @author BFS Team 
 *
 */


@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="admininfoAction")
public class AdmininfoAction extends ActionSupport{
	@Resource(name="userinfoService")
	private UserinfoService userinfoService;
	
	
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService;
	
	
	private String userName;
	private String userPwd;
	private String result;
	private List<Courseinfo> typeList;
	
	/**
	 * 登录验证
	 * @return
	 */
	public String loginValidate(){
		System.out.println("进入AdmininfoAction中的loginValidate方法，传过来的参数有：");
		System.out.println("userName="+userName+"  userPwd="+userPwd);
		
		boolean flag = userinfoService.validateAdmin(userName, userPwd);

		if(flag){
			List<Userinfo> list = userinfoService.findByUserName(userName);
			if(list!=null && list.size()>0){
				//更改登录时间
				Userinfo u = list.get(0);
				u.setLoginTime(new Timestamp(System.currentTimeMillis()));
				userinfoService.updateUserinfo(u);
				this.setSessionUser(u);
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		JSONObject jsonObject = JSONObject.fromObject(map);
		result = jsonObject.toString();
		System.out.println("result="+result);
		
		return "success";
	}
	/**
	 * 退出
	 * @return
	 */
	public String adminLogout(){
		System.out.println("退出");
		this.logoutSessionUser();
		return "success";
	}
	
	public String courseTypeLoad(){
		
		
		typeList = new ArrayList<Courseinfo>();
		typeList = courseinfoService.findAllCourseinfo();
		
		return "uploadSuccess";
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public List<Courseinfo> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<Courseinfo> typeList) {
		this.typeList = typeList;
	}
	
	
}
