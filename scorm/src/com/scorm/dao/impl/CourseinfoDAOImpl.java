package com.scorm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scorm.dao.CourseinfoDAO;
import com.scorm.vo.Courseinfo;
/**
 * 实现接口CourseinfoDAO
 * @author BFS Team 
 *
 */
@SuppressWarnings("unchecked")
@Repository(value="courseinfoDAO")
public class CourseinfoDAOImpl implements CourseinfoDAO{
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	public void saveCourseinfo(Courseinfo courseinfo) {
		this.hibernateTemplate.save(courseinfo);
	}
	
	@Transactional(readOnly=false)
	public void deleteCourseinfo(Courseinfo courseinfo) {
		this.hibernateTemplate.delete(courseinfo);
	}
	
	@Transactional(readOnly=false)
	public void updateCourseinfo(Courseinfo courseinfo) {
		this.hibernateTemplate.update(courseinfo);
	}
	
	@Transactional(readOnly=true)
	public List<Courseinfo> findAllCourseinfo() {
		return this.hibernateTemplate.find("from Courseinfo");
	}
	
	@Transactional(readOnly=true)
	public List<Courseinfo> findByCourseType(String courseType) {
		// TODO Auto-generated method stub
		return  this.hibernateTemplate.find("from Courseinfo where courseType=?", courseType);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Courseinfo> findByCourseName(String courseName) {
		// TODO Auto-generated method stub
		return  this.hibernateTemplate.find("from Courseinfo where courseName like ?", "%"+courseName+"%");
	}

	@Override
	@Transactional(readOnly=true)
	public List<Courseinfo> findByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		return  this.hibernateTemplate.find("from Courseinfo where courseId=?", courseId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Courseinfo> findCourseName(String courseName) {
		List<Courseinfo> list = this.hibernateTemplate.find("from Courseinfo where courseName=?", courseName);
		System.out.println(list);
		return list;
	}
	@Override
	@Transactional(readOnly=true)
	public List<Courseinfo> findCourseinfoList(int pageStart, int pageSize) {
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		List<Courseinfo> list = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Courseinfo");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Courseinfo> list = query.list();
						session.close();
						return list;
					}
				});

		return list;
	}
	@Override
	@Transactional(readOnly=true)
	public List<Courseinfo> courseSearch(String courseName, int pageStart,
			int pageSize) {
		System.out.println("wellcome courseinfoDAO-courseSeach");
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		final String falCourseName = courseName ;
		System.out.println("ok,courseName="+falCourseName);
		List<Courseinfo> list = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Courseinfo where courseName like '%"+falCourseName+"%'");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Courseinfo> list = query.list();
						session.close();
						return list;
					}
				});

		System.out.println(list);
		return list;
	}

	@Override
	public List<Courseinfo> findSql(String sql) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find(sql);
	}
}
