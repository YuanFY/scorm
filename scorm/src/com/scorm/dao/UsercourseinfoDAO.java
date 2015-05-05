package com.scorm.dao;

import java.util.List;

import com.scorm.vo.Usercourseinfo;

/**
 * 学员学习信息接口
 * @author BFS Team 
 *
 */
public interface UsercourseinfoDAO {
	
	/**
	 * 保存学员学习信息
	 * @param usercourseinfo 用户课程信息
	 */
	public void saveUsercourseinfo(Usercourseinfo usercourseinfo);

	/**
	 * 删除学员学习信息
	 * @param usercourseinfo 用户课程信息
	 */
	public void deleteUsercourseinfo(Usercourseinfo usercourseinfo);

	/**
	 * 更新学员学习信息
	 * @param usercourseinfo 用户课程信息
	 */
	public void updateUsercourseinfo(Usercourseinfo usercourseinfo);
	
	/**
	 * 返回该学员学习该课件的对象
	 * @param usercourseinfo 用户课程信息
	 * @return 返回该学员学习该课件的list集合
	 */
	public List<Usercourseinfo> returnUsercourseinfo(Integer userId, Integer courseId, Integer scoId);

	/**
	 * 返回该学员学习该课件的对象
	 * @param usercourseinfo
	 * @return  返回该学员学习该课件的list集合
	 */
	public List<Usercourseinfo> returnUsercourseinfo1(Integer userId, Integer courseId);

	
	/**
	 * 返回某个学员所有的学习对象
	 * @param usercourseinfo
	 * @return 返回该学员学习该课件的list集合
	 */
	public List<Usercourseinfo> returnAllUsercourseinfo(Integer userId);
	
	/**
	 * 查找所有记录
	 * @return 返回所有的学习对象
	 */
	public List<Usercourseinfo> allinfo();
	/**
	 * 
	 * 返回用户所选课程
	 */
	public List<Usercourseinfo> findUsercourseinfoById(int userId);
	public List<Usercourseinfo> findUsercourseinfoByCourseName(int userId,String courseName);
	public List<Usercourseinfo> findUsercourseinfoByAll(int userId,String courseName,String courseType);
	public List<Usercourseinfo> findUsercourseinfoByCourseType(int userId,String courseType);
	
	/**
	 * 得到学生在该课程的学习信息	
	 * @param userId
	 * @param courseId
	 * @return
	 */
	public List<Usercourseinfo> getCourseStudy(int userId, int courseId);
	
	/**
	 * 查找学生在该课件的学习信息
	 * @param userId 用户编号	
	 * @param courseId	课程编号
	 * @param scoId		课件编号
	 * @return	 返回学习记录列表
	 */
	public List<Usercourseinfo> findByUserCourseScoId(int userId, int courseId, int scoId);
	
	/**
	 * 根据课件编号查找记录
	 * @param scoId 课件编号
	 * @return   返回学习记录列表
	 */
	public List<Usercourseinfo> findByScoId(Integer scoId);
}
