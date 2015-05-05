package com.scorm.action;


import java.util.List;

import javax.annotation.Resource;
 
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
 
import com.scorm.vo.Usercourseinfo;
import com.scorm.service.UsercourseinfoService;

/**
 * 用户action层
 * @author BFS Team 
 *
 */
@SuppressWarnings("serial")
@Scope(value="prototype")
@Component(value="usercourseinfoAction")
public class UsercourseinfoAction extends ActionSupport{
	@Resource(name="usercourseinfoService")
	private UsercourseinfoService uc;
	
	private int userId;
	private int courseId;
	private int scoId;
	private String scoNote;
	
	/**
	 * 更新用户课程信息
	 * @return
	 */
	public String updateUsercourseinfo(){
		
		List<Usercourseinfo> list = uc.returnUsercourseinfo(userId, courseId, scoId); 
		if(list==null||list.size()==0){
			return null;
		}
		Usercourseinfo u = list.get(0);
		u.setScoNote(scoNote);
		
		uc.updateUsercourseinfo(u);
		
		return null;
	}

	public UsercourseinfoService getUc() {
		return uc;
	}

	public void setUc(UsercourseinfoService uc) {
		this.uc = uc;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getScoId() {
		return scoId;
	}

	public void setScoId(int scoId) {
		this.scoId = scoId;
	}

	public String getScoNote() {
		return scoNote;
	}

	public void setScoNote(String scoNote) {
		this.scoNote = scoNote;
	}
}
