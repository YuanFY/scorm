package com.scorm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.ViewcourseDAO;
import com.scorm.service.ViewcourseService;
import com.scorm.vo.Viewcourse;

@Service(value="viewcourseService")
public class ViewcourseServiceImpl implements ViewcourseService{

	@Resource(name="viewcourseDAO")
	private ViewcourseDAO viewcourseDAO;
	
	@Override
	public void save(Viewcourse viewcourse) {
		// TODO Auto-generated method stub
		this.viewcourseDAO.save(viewcourse);
	}

	@Override
	public void update(Viewcourse viewcourse) {
		// TODO Auto-generated method stub
		this.viewcourseDAO.update(viewcourse);
	}

	@Override
	public List<Viewcourse> findBysql(String sql) {
		// TODO Auto-generated method stub
		return this.viewcourseDAO.findBysql(sql);
	}

	@Override
	public void delete(Viewcourse viewcourse) {
		// TODO Auto-generated method stub
		this.viewcourseDAO.delete(viewcourse);
	}

}
