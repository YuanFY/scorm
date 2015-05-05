package com.scorm.dao;

import java.util.List;

import com.scorm.vo.Userinfo;

/**
 * 用户信息接口层
 * @author BFS Team 
 *
 */
public interface UserinfoDAO {
	
	/**
	 * 保存用户信息
	 * @param userinfo  用户信息对象
	 */
	public void saveUserinfo(Userinfo userinfo);
	
	/**
	 * 删除用户信息
	 * @param userinfo 用户信息对象
	 */
	public void deleteUserinfo(Userinfo userinfo);
	
	/**
	 * 更新用户信息
	 * @param userinfo 用户信息对象
	 */
	public void updateUserinfo(Userinfo userinfo);
	
	/**
	 * 查找所有的学员信息 
	 * @return  Userinfo对象的list集合
	 */
	public List<Userinfo> findAllUserinfo();
	/**
	 * 查找所有的教师信息
	 * @return Userinfo对象的list集合
	 */
	public List<Userinfo> findAllTeacherinfo();
	/**
	 * 查找所有的管理员信息 
	 * @return Userinfo对象的list集合
	 */
	public List<Userinfo> findAllAdmininfo();
	/**
	 * 按照userId查找用户
	 * @param userId 用户编号
	 * @return Userinfo对象的list集合
	 */
	public List<Userinfo> findByUserId(Integer userId);
	
	/**
	 * 按照userName查找用户
	 * @param userName 用户名称
	 * @return Userinfo对象的list集合
	 */
	public List<Userinfo> findByUserName(String userName);
	/**
	 * 按照分页查找用户
	 * @param pageStart pageSize
	 * @return Userinfo对象的list集合
	 */
	public List<Userinfo>  findUserList(int pageStart, int pageSize) ;
	public List<Userinfo>  findTeacherList(int pageStart, int pageSize) ;
	public List<Userinfo>  findAdminList(int pageStart, int pageSize) ;
	
	/**
	 * 查找所有权限的用户
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public List<Userinfo> findUserInfoList(int pageStart, int pageSize) ;
	
	/**
	 * 自定义sql语句查询
	 * @param sql
	 * @return Userinfo列表
	 */
	public List<Userinfo> findSql(String sql);
}
