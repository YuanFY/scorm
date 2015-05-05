package com.scorm.service;

import java.util.List;

import com.scorm.vo.Classstatisticinfo;
import com.scorm.vo.Studyrecordinfo;
import com.scorm.vo.Usercourseinfo;

/**
 * 学员学习信息服务接口层
 * @author BFS Team 
 *
 */
public interface UsercourseinfoService {
	

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
	 * 得到学生在该课程的学习信息	
	 * @param userId
	 * @param courseId
	 * @return
	 */
	public List<Usercourseinfo> getCourseStudy(int userId, int courseId);
	
	
	/**
	 * 得到课程统计数量
	 * @return
	 */
	public List<Classstatisticinfo> getCourseStatistic();
	public List<Classstatisticinfo> getSco(String course);
	
	/**
	 * 得到档案信息
	 * @return
	 */
	public List<Studyrecordinfo> getStudyRecord(int id);
	/**
	 * 搜索得到档案信息
	 * @param courseType
	 * @param userId
	 * @return
	 */
	public List<Studyrecordinfo> getStudyRecordByCourseType(String courseType,int userId);
	public List<Studyrecordinfo> getStudyRecordByCourseName(String courseName,int userId);
	public List<Studyrecordinfo> getStudyRecordByAll(String courseName,String courseType,int userId);
	
	/**
	 * 得到学习中课程信息
	 * @return
	 */
	public List<Studyrecordinfo> getStudyCourse(int id);
	public List<Studyrecordinfo> getStudyCourseByCourseType(String courseType,int userId);
	public List<Studyrecordinfo> getStudyCourseByCourseName(String courseName,int userId);
	public List<Studyrecordinfo> getStudyCourseByAll(String courseName,String courseType,int userId);
	/**
	 * 得到学习完的课程信息
	 * @return
	 */
	public List<Studyrecordinfo> getendCourse(int id);
	public List<Studyrecordinfo> getendCourseByCourseType(String courseType,int userId);
	public List<Studyrecordinfo> getendCourseByCourseName(String courseName,int userId);
	public List<Studyrecordinfo> getendCourseByAll(String courseName,String courseType,int userId);
	/**
	 * 得到过期学习的课程信息
	 * @return
	 */
	public List<Studyrecordinfo> getlastCourse(int id);
	public List<Studyrecordinfo> getlastCourseByCourseType(String courseType,int userId);
	public List<Studyrecordinfo> getlastCourseByCourseName(String courseName,int userId);
	public List<Studyrecordinfo> getlastCourseByAll(String courseName,String courseType,int userId);
	
	/**
	 * 查找用户在课件的学习信息
	 * @param userId
	 * @param courseId
	 * @param scoId
	 * @return
	 */
	public List<Usercourseinfo> getByUserCourseScoId(int userId, int courseId, int scoId);

	/**
	 * 根据课件编号查找记录
	 * @param scoId 课件编号
	 * @return   返回学习记录列表
	 */
	public List<Usercourseinfo> findByScoId(Integer scoId);
}
