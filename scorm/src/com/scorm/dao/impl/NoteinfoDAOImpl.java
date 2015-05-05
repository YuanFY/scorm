package com.scorm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scorm.dao.NoteinfoDAO;
import com.scorm.vo.Noteinfo;
@SuppressWarnings("unchecked")
@Repository(value="noteinfoDAO")
public class NoteinfoDAOImpl implements NoteinfoDAO{

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	@Override
	public void save(Noteinfo noteinfo) {
		this.hibernateTemplate.save(noteinfo);
	}

	@Transactional(readOnly=false)
	@Override
	public void update(Noteinfo noteinfo) {
		this.hibernateTemplate.update(noteinfo);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Noteinfo> findSql(String sql) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find(sql);
	}
	@Transactional(readOnly=false)
	@Override
	public void delete(Noteinfo noteinfo) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.delete(noteinfo);
	}
	 
}
