package com.scorm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scorm.dao.ViewcourseDAO;
import com.scorm.vo.Viewcourse;

@Repository(value="viewcourseDAO")
public class ViewcourseDAOImpl implements ViewcourseDAO{

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	@Override
	public void save(Viewcourse viewcourse) {
		this.hibernateTemplate.save(viewcourse);
	}
	@Transactional(readOnly=false)
	@Override
	public void update(Viewcourse viewcourse) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(viewcourse);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Viewcourse> findBysql(String sql) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find(sql);
	}
	@Transactional(readOnly=false)
	@Override
	public void delete(Viewcourse viewcourse) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.delete(viewcourse);
	}

}
