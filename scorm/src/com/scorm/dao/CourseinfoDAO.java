package com.scorm.dao;

import java.util.List;

import com.scorm.vo.Courseinfo;

/**
 * 课程信息接口层
 * @author BFS Team 
 *
 */
public interface CourseinfoDAO {
	
	/**
	 * 保存课程信息
	 * @param courseinfo 课程信息
	 */
	public void saveCourseinfo(Courseinfo courseinfo);
	
	/**
	 * 删除课程信息
	 * @param courseinfo 课程信息
	 */
	public void deleteCourseinfo(Courseinfo courseinfo);
	
	/**
	 * 更新课程信息
	 * @param courseinfo 课程信息
	 */
	public void updateCourseinfo(Courseinfo courseinfo);
	
	/**
	 * 查找所有的课程
	 * @return 返回所有的课程信息
	 */
	public List<Courseinfo> findAllCourseinfo();
	
	/**
	 * 按照类型查找所有课程
	 * @param courseType 课程类型
	 * @return 返回相对应的课程信息集合
	 */
	public List<Courseinfo> findByCourseType(String courseType);
	
	/**
	 * 按照编号查找所有课程
	 * @param courseId 课程编号
	 * @return  返回相对应的课程信息集合
	 */
	public List<Courseinfo> findByCourseId(Integer courseId);
	
	/**
	 * 模糊查找所有有关的课程
	 * @param courseName 课程名称
	 * @return  返回相对应的课程信息集合
	 */
	public List<Courseinfo> findByCourseName(String courseName);
	/**
	 * 按名字查找课程
	 * @param courseName 课程名称
	 * @return   返回相对应的课程信息集合
	 */
	public List<Courseinfo> findCourseName(String courseName);
	
	/**
	 * 按照分页查找课程
	 * @param pageStart 分页开始码
	 * @param pageSize 分页大小
	 * @return  返回相对应的课程信息集合
	 */
	public List<Courseinfo> findCourseinfoList(int pageStart, int pageSize);

	/**
	 * 课程搜索
	 * @param courseName 课程名称
	 * @param pageStart 分页开始码
	 * @param pageSize 分页大小
	 * @return  返回相对应的课程信息集合
	 */
	public List<Courseinfo> courseSearch(String courseName, int pageStart, int pageSize);

	/**
	 * 自定义sql语句查询
	 * @param sql 自定义sql语句
	 * @return 返回相对应的课程信息集合
	 */
	public List<Courseinfo> findSql(String sql);

}
