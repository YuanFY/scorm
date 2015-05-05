package com.scorm.service;

import java.util.List;

import com.scorm.vo.Viewcourse;

public interface ViewcourseService {

	/**
	 * 保存用户信息
	 * @param viewcourse 观看课程对象
	 */
	public void save(Viewcourse viewcourse);
	
	/**
	 * 更新
	 * @param viewcourse 观看课程对象
	 */
	public void update(Viewcourse viewcourse);
	
	/**
	 * 根据自定义sql查询
	 * @param sql 自定义sql语句
	 * @return Viewcourse列表
	 */
	public List<Viewcourse> findBysql(String sql);
	
	/**
	 * 删除信息
	 * @param viewcourse 观看课程对象
	 */
	public void delete(Viewcourse viewcourse);
}
