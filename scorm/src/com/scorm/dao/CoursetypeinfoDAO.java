package com.scorm.dao;

import java.util.List;

import com.scorm.vo.Coursetypeinfo;
/**
 * 课程类型信息
 * @author YuanFY
 * @Description:TODO
 * @version V1.0
 */
public interface CoursetypeinfoDAO {
	
	/**
	 * 保存课程类型信息
	 * @param cTypeInfo 课程信息类型
	 */
	public void saveCoursetype(Coursetypeinfo cTypeInfo);
	
	/**
	 * 删除课程类型信息
	 * @param cTypeInfo 课程信息类型
	 */
	public void deleteCoursetypeinfo(Coursetypeinfo cTypeInfo);
	
	/**
	 * 
	 * 返回所有的课程类型信息
	 * @return 课程信息类型集合
	 */
	public List<Coursetypeinfo> findAllType();
	
	/**
	 * 根据名字查找
	 * @param ctype
	 * @return 课程信息类型集合
	 */
	public List<Coursetypeinfo> findByTypeName(String ctype);
	
	/**
	 * 根据课程类型Id查找
	 * @param courseTypeId
	 * @return 课程信息类型集合
	 */
	public List<Coursetypeinfo> findByCourseTypeId(int courseTypeId);
	
	/**
	 * 更新课程类型
	 * @param coursetypeinfo 课程信息类型
	 */
	public void updateCoursetypeinfo(Coursetypeinfo coursetypeinfo);
	
	/**
	 * 按照分页查找课程类型
	 * @param pageStart 分页开始点
	 * @param pageCount 分页大小
	 * @return 课程信息类型集合
	 */
	public List<Coursetypeinfo> findCoursetypeinfoList(int pageStart, int pageSize);
}
