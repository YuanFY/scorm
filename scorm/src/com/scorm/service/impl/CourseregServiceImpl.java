package com.scorm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.CourseregDAO;
import com.scorm.service.CourseregService;
import com.scorm.vo.Coursereg;

/**
 * 课程注册服务层
 * @author BFS Team 
 *
 */
@Service(value="courseregService")
public class CourseregServiceImpl implements CourseregService {

	@Resource(name="courseregDAO")
	private CourseregDAO courseregDAO;
	
	@Override
	public void saveCoursereg(Coursereg coursereg) {
		this.courseregDAO.saveCoursereg(coursereg);
	}

	@Override
	public void deleteCoursereg(Coursereg coursereg) {
		this.courseregDAO.deleteCoursereg(coursereg);
	}

	@Override
	public List<Coursereg> findByUserId(Integer userId) {
		return this.courseregDAO.findByUserId(userId);
	}

	@Override
	public int findByCourseId(Integer courseId) {
		return this.courseregDAO.findByCourseId(courseId);
	}

	@Override
	public List<Coursereg> findById(Integer userId, Integer courseId) {
		// TODO Auto-generated method stub
		return this.courseregDAO.findById(userId, courseId);
	}

	@Override
	public void update(Coursereg coursereg) {
		// TODO Auto-generated method stub
		this.courseregDAO.update(coursereg);
	}

	@Override
	public List<Coursereg> findSql(String sql) {
		// TODO Auto-generated method stub
		return this.courseregDAO.findSql(sql);
	}

}
