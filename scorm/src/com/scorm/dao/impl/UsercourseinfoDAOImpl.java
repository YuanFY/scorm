package com.scorm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scorm.dao.UsercourseinfoDAO;
import com.scorm.vo.Usercourseinfo;
/**
 * 学员信息实现层
 * @author BFS Team 
 *
 */
@SuppressWarnings("unchecked")
@Repository(value="usercourseinfoDAO")
public class UsercourseinfoDAOImpl implements UsercourseinfoDAO {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	public void saveUsercourseinfo(Usercourseinfo usercourseinfo) {
		this.hibernateTemplate.save(usercourseinfo);
	}

	@Transactional(readOnly=false)
	public void deleteUsercourseinfo(Usercourseinfo usercourseinfo) {
		this.hibernateTemplate.delete(usercourseinfo);
	}

	@Transactional(readOnly=false)
	public void updateUsercourseinfo(Usercourseinfo usercourseinfo) {
		this.hibernateTemplate.update(usercourseinfo);
	}

	@Transactional(readOnly=true)
	public List<Usercourseinfo> returnUsercourseinfo(Integer userId, Integer courseId, Integer scoId) {
		Object[] values = new Object[3];
		values[0] = userId;
		values[1] = courseId;
		values[2] = scoId;
		return this.hibernateTemplate.find("from Usercourseinfo where userId=? and courseId=? and scoId=?", values);
	}

	@Transactional(readOnly=true)
	public List<Usercourseinfo> returnUsercourseinfo1(Integer userId, Integer scoId) {
		Object[] values = new Object[2];
		values[0] = userId;
		values[1] = scoId;
		return this.hibernateTemplate.find("from Usercourseinfo where userId=? and scoId=?", values);
	}
	
	@Transactional(readOnly=true)
	public List<Usercourseinfo> returnAllUsercourseinfo(Integer userId) {
		return this.hibernateTemplate.find("from Usercourseinfo where userId=? ", userId);
	}
	
	@Transactional(readOnly=true)
	public List<Usercourseinfo> findUsercourseinfoById(int userId) {
		return this.hibernateTemplate.find("from Usercourseinfo where userId=?", userId);
	
	}

	@Transactional(readOnly=true)
	public List<Usercourseinfo> allinfo() {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Usercourseinfo");
	}
	
	@Transactional(readOnly=true)
	public List<Usercourseinfo> findUsercourseinfoByCourseName(int userId,
			String courseName) {
		Object[] values = new Object[2];
		values[0] = courseName;
		values[1] = userId;
		return this.hibernateTemplate.find("from Usercourseinfo where courseId in(select courseId from Courseinfo where courseName like '%"+courseName+"%')");
	}

	@Transactional(readOnly=true)
	public List<Usercourseinfo> findUsercourseinfoByAll(int userId,
			String courseName, String courseType) {
		return this.hibernateTemplate.find("from Usercourseinfo where courseId in(select courseId from Courseinfo where courseName like '%"+courseName+"%' and courseType='"+courseType+"')");
	}

	@Transactional(readOnly=true)
	public List<Usercourseinfo> findUsercourseinfoByCourseType(int userId,
			String courseType) {
//		System.out.println(courseType+"dao层1");
//		try {
//			courseType = new String(courseType.getBytes("iso8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(courseType+"dao层");
		return this.hibernateTemplate.find("from Usercourseinfo where userId="+userId+" and courseId in (select courseId from Courseinfo where courseType='"+ courseType +"') ");
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usercourseinfo> getCourseStudy(int userId, int courseId) {
		Object[] value = new Object[2];
		value[0] = userId;
		value[1] = courseId;
		List<Usercourseinfo> list = this.hibernateTemplate.find("from Usercourseinfo where userId=? and courseId=?", value);
		System.out.println("----list="+list.size());
		return list;
	}

	@Override
	public List<Usercourseinfo> findByUserCourseScoId(int userId, int courseId,
			int scoId) {
		Object[] values = new Object[3];
		values[0] = userId;
		values[1] = courseId;
		values[2] = scoId;
		return this.hibernateTemplate.find("from Usercourseinfo where userId=? and courseId=? and scoId=?", values);
	}

	@Override
	public List<Usercourseinfo> findByScoId(Integer scoId) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from Usercourseinfo where scoId = "+scoId);
	}
}
