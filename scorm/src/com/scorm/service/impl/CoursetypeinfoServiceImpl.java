package com.scorm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.CoursetypeinfoDAO;
import com.scorm.service.CoursetypeinfoService;
import com.scorm.vo.Coursetypeinfo;

@Service(value="coursetypeinfoService")
public class CoursetypeinfoServiceImpl implements CoursetypeinfoService {

	@Resource(name="coursetypeinfoDAO")
	private CoursetypeinfoDAO coursetypeinfoDAO;
	
	public boolean saveCoursetype(Coursetypeinfo cTypeInfo) {
		List<Coursetypeinfo> list = coursetypeinfoDAO.findAllType();
		for( Coursetypeinfo coursetypeinfo : list ) {
			if( coursetypeinfo.getCourseTypeId() != cTypeInfo.getCourseTypeId() && coursetypeinfo.getCourseType().equals(cTypeInfo.getCourseType()) ) {
				System.out.println("存在同名的课程类型");
				return false;
			}
		}
		this.coursetypeinfoDAO.saveCoursetype(cTypeInfo);
		return true;
	}
	
	public boolean updateCoursetypeinfo(Coursetypeinfo coursetypeinfo) {
		List<Coursetypeinfo> list = coursetypeinfoDAO.findAllType();
		for( Coursetypeinfo coursetype : list ) {
			if( coursetype.getCourseTypeId() != coursetypeinfo.getCourseTypeId() && coursetype.getCourseType().equals(coursetypeinfo.getCourseType()) ) {
				System.out.println("存在同名的课程类型");
				return false;
			}
		}
		this.coursetypeinfoDAO.updateCoursetypeinfo(coursetypeinfo);
		return true;
	}

	public void deleteCoursetypeinfo(Coursetypeinfo cTypeInfo) {
		this.coursetypeinfoDAO.deleteCoursetypeinfo(cTypeInfo);
	}

	public List<Coursetypeinfo> findAllType() {
		return this.coursetypeinfoDAO.findAllType();
	}

	public List<Coursetypeinfo> findByTypeName(String ctype) {
		return this.coursetypeinfoDAO.findByTypeName(ctype);
	}

	@Override
	public List<Coursetypeinfo> getCoursetypeinfoList(int pageStart,
			int pageSize) {
		List<Coursetypeinfo> list = new ArrayList<Coursetypeinfo>();
		list = coursetypeinfoDAO.findCoursetypeinfoList(pageStart, pageSize);
		return list;
	}

	@Override
	public List<Coursetypeinfo> findByCourseTypeId(int courseTypeId) {
		return this.coursetypeinfoDAO.findByCourseTypeId(courseTypeId);
	}

}
