package com.scorm.service;

import java.util.List;

import com.scorm.vo.Coursereg;

/**
 * 课程注册接口层
 * @author BFS Team 
 *
 */
public interface CourseregService {
	
	/**
	 * 保存课程注册信息
	 * @param coursereg 课程注册对象
	 */
	public void saveCoursereg(Coursereg coursereg);
	
	/**
	 * 删除课程注册信息
	 * @param coursereg 课程注册对象
	 */
	public void deleteCoursereg(Coursereg coursereg);
	
	/**
	 * 通过userId查找属于自己已注册的课程
	 * @param userId 用户编号
	 * @return 课程注册对象
	 */
	public List<Coursereg> findByUserId(Integer userId);
	
	/**
	 * 通过courseId查找该课程的注册人数
	 * @param courseId 课程编号
	 * @return  课程注册对象 相应的记录
	 */
	public int findByCourseId(Integer courseId);
	/**
	 * 通过userId,courseId查找记录
	 */
	public List<Coursereg> findById(Integer userId,Integer courseId);
	
	/**
	 * 更新课程注册对象 
	 * @param coursereg 课程注册对象
	 */
	public void update(Coursereg coursereg);
	
	/**
	 * 自定义sql语句查询
	 * @param sql  自定义sql
	 * @return  课程注册对象 相应的记录
	 */
	public List<Coursereg> findSql(String sql);
}
