package com.scorm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.CourseinfoDAO;
import com.scorm.service.CourseinfoService;
import com.scorm.vo.Courseinfo;
@Service(value="courseinfoService")
public class CourseinfoServiceImpl implements CourseinfoService {
	@Resource(name="courseinfoDAO")
	private CourseinfoDAO courseinfoDAO;
	
	public boolean saveCourseinfo(Courseinfo courseinfo) {
		List<Courseinfo> list = courseinfoDAO.findAllCourseinfo();
		for( Courseinfo course : list ) {
			if( course.getCourseId() != courseinfo.getCourseId() && course.getCourseName().equals(courseinfo.getCourseName()) ) {
				System.out.println("存在同名的课程");
				return false;
			}
		}
		this.courseinfoDAO.saveCourseinfo(courseinfo);
		return true ;
	}

	public void deleteCourseinfo(Courseinfo courseinfo) {
		this.courseinfoDAO.deleteCourseinfo(courseinfo);
	}

	public boolean updateCourseinfo(Courseinfo courseinfo) {
		List<Courseinfo> list = courseinfoDAO.findAllCourseinfo();
		for( Courseinfo course : list ) {
			if( course.getCourseId() != courseinfo.getCourseId() && course.getCourseName().equals(courseinfo.getCourseName()) ) {
				System.out.println("存在同名的课程");
				return false;
			}
		}
		this.courseinfoDAO.updateCourseinfo(courseinfo);
		return true;
	}

	public List<Courseinfo> findAllCourseinfo() {
		return this.courseinfoDAO.findAllCourseinfo();
	}

	public List<Courseinfo> findByCourseType(String courseType) {
		return this.courseinfoDAO.findByCourseType(courseType);
	}

	public List<Courseinfo> findByCourseName(String courseName) {
		return this.courseinfoDAO.findByCourseName(courseName);
	}

	@Override
	public List<Courseinfo> findByCourseId(Integer courseId) {
		return this.courseinfoDAO.findByCourseId(courseId);
	}

	@Override
	public List<Courseinfo> findCourseName(String courseName) {
		List<Courseinfo> list = null;
		try{
			System.out.println("scoinfoServiceImpl");
			System.out.println(courseinfoDAO);
			list = this.courseinfoDAO.findCourseName(courseName);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Courseinfo> getCourseList(int pageStart, int pageSize) {
		List<Courseinfo> list = new ArrayList<Courseinfo>();
		list = courseinfoDAO.findCourseinfoList(pageStart, pageSize);
		return list;
	}

	@Override
	public List<Courseinfo> courseSearch(String courseName, int pageStart,
			int pageSize) {
		System.out.println("wellcome courseinfoService-courseSearch");
		return courseinfoDAO.courseSearch(courseName, pageStart, pageSize);
	}

	@Override
	public List<Courseinfo> findSql(String sql) {
		// TODO Auto-generated method stub
		return this.courseinfoDAO.findSql(sql);
	}
}
