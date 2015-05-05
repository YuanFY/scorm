package com.scorm.service.impl;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.UserinfoDAO;
import com.scorm.service.UserinfoService;
import com.scorm.vo.Userinfo;

@Service(value="userinfoService")
public class UserinfoServiceImpl implements UserinfoService{

	@Resource(name="userinfoDAO")
	private UserinfoDAO userinfoDAO;
	
	public void saveUserinfo(Userinfo userinfo) {
		this.userinfoDAO.saveUserinfo(userinfo);
	}

	public void deleteUserinfo(Userinfo userinfo) {
		this.userinfoDAO.deleteUserinfo(userinfo);
	}

	public void updateUserinfo(Userinfo userinfo) {
		this.userinfoDAO.updateUserinfo(userinfo);
	}

	public List<Userinfo> findAllUserinfo() {
		return this.userinfoDAO.findAllUserinfo();
	}

	public List<Userinfo> findByUserId(Integer userId) {
		return this.userinfoDAO.findByUserId(userId);
	}

	public List<Userinfo> findByUserName(String userName) {
		return this.userinfoDAO.findByUserName(userName);
	}

	@Override
	public boolean findByUserNameAndUserName(String userName, String userPwd) {
		List<Userinfo> list  = null;
		System.out.println("userinfoDAO="+userinfoDAO);
		try{
			list = this.userinfoDAO.findByUserName(userName);
		}
		catch (Exception w){
			w.printStackTrace();
		}
		System.out.println("验证过程--list="+list.size());
		boolean flag = (list!=null)&&(list.size()>0)&&((list.get(0).getUserPwd()).equals(userPwd));
		System.out.println("验证过程--flag="+flag);
		return flag;
	}

	

	@Override
	public List<Userinfo> getUserList(int pageStart, int pageSize) {
		List<Userinfo> list = new ArrayList<Userinfo>();
		list = userinfoDAO.findUserList(pageStart, pageSize);
		return list;
	}
	@Override
	public List<Userinfo> getTeacherList(int pageStart, int pageSize) {
		List<Userinfo> list = new ArrayList<Userinfo>();
		list = userinfoDAO.findTeacherList(pageStart, pageSize);
		return list;
	}

	@Override
	public List<Userinfo> findAllTeacherinfo() {
		return this.userinfoDAO.findAllTeacherinfo();
	}

	@Override
	public List<Userinfo> findAllAdmininfo() {
		return this.userinfoDAO.findAllAdmininfo();
	}

	@Override
	public List<Userinfo> getAdminList(int pageStart, int pageSize) {
		List<Userinfo> list = new ArrayList<Userinfo>();
		list = userinfoDAO.findAdminList(pageStart, pageSize);
		return list;
	}
	
	@Override
	public boolean validateAdmin(String userName, String userPwd) {
		List<Userinfo> list  = null;
		try{
			list = this.userinfoDAO.findByUserName(userName);
		}
		catch (Exception w){
			w.printStackTrace();
		}
		boolean flag = false;
		if((list!=null)&&(list.size()>0)&&((list.get(0).getUserPwd()).equals(userPwd))&&(list.get(0).getIsAdmin()==2))
			flag = true;
		return flag;
	}
	
	@Override
	public List<Userinfo> getUserInfoList(int pageStart, int pageSize) {
		List<Userinfo> list = new ArrayList<Userinfo>();
		list = userinfoDAO.findUserInfoList(pageStart, pageSize);
		return list;
	}

	@Override
	public List<Userinfo> findSql(String sql) {
		return this.userinfoDAO.findSql(sql);
	}


}
