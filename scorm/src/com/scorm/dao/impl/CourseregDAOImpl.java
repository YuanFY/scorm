package com.scorm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scorm.dao.CourseregDAO;
import com.scorm.vo.Coursereg;
/**
 * 实现课程注册信息方法的类
 * @author BFS Team 
 *
 */
@SuppressWarnings("unchecked")
@Repository(value="courseregDAO")
public class CourseregDAOImpl implements CourseregDAO {
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Transactional(readOnly=false)
	public void saveCoursereg(Coursereg coursereg) {
		this.hibernateTemplate.save(coursereg);
	}

	@Transactional(readOnly=false)
	public void deleteCoursereg(Coursereg coursereg) {
		this.hibernateTemplate.delete(coursereg);
	}

	@Transactional(readOnly=true)
	public List<Coursereg> findByUserId(Integer userId) {
		return this.hibernateTemplate.find("from Coursereg where userId=?", userId);
	}

	@Transactional(readOnly=false)
	public int findByCourseId(Integer courseId) {		
		List<Coursereg> list = this.hibernateTemplate.find("from Coursereg where courseId=?", courseId);
		if(list!=null)
			return list.size();
		return 0;
	}
	@Transactional(readOnly=false)
	public List<Coursereg> findById(Integer userId, Integer courseId) {
		Object[] values = new Object[2];
		values[0] =userId;
		values[1] = courseId;
		return this.hibernateTemplate.find("from Coursereg where userId=? and courseId=?", values);

	}
	@Transactional(readOnly=false)
	@Override
	public void update(Coursereg coursereg) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(coursereg);
	}

	@Override
	public List<Coursereg> findSql(String sql) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find(sql);
	}
	
}
