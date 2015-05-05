package com.scorm.service;

import java.util.List;

import com.scorm.vo.Coursetypeinfo;

public interface CoursetypeinfoService {
	/**
	 * 保存课程类型信息
	 * @param cTypeInfo 课程信息类型
	 */
	public boolean saveCoursetype(Coursetypeinfo cTypeInfo);
	
	/**
	 * 更新课程类型
	 * @param coursetypeinfo 课程信息类型
	 * @return
	 */
	public boolean updateCoursetypeinfo(Coursetypeinfo coursetypeinfo);
	
	/**
	 * 删除课程类型信息
	 * @param cTypeInfo 课程信息类型
	 */
	public void deleteCoursetypeinfo(Coursetypeinfo cTypeInfo);
	
	/**
	 * 
	 * 返回所有的课程类型信息
	 * @return
	 */
	public List<Coursetypeinfo> findAllType();
	
	/**
	 * 根据名字查找
	 * @param ctype 课程类型
	 * @return
	 */
	public List<Coursetypeinfo> findByTypeName(String ctype);
	
	/**
	 * 根据Id查找
	 * @param courseTypeId 课程类型编号
	 * @return 课程信息类型集合
	 */
	public List<Coursetypeinfo> findByCourseTypeId(int courseTypeId);
	
	/**
	 * 按照分页查找课程类型
	 * @param pageStart 分页开始点
	 * @param pageCount 分页大小
	 * @return 课程信息类型集合
	 */
	public List<Coursetypeinfo> getCoursetypeinfoList(int pageStart, int pageSize);
}
