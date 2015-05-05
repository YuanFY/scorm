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

import com.scorm.dao.UserinfoDAO;
import com.scorm.vo.Userinfo;

/**
 * 实现接口UserinfoDAO
 * @author BFS Team 
 *
 */
@Repository(value="userinfoDAO")
public class UserinfoDAOImpl implements UserinfoDAO {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	public void saveUserinfo(Userinfo userinfo) {
		this.hibernateTemplate.save(userinfo);
	}

	@Transactional(readOnly=false)
	public void deleteUserinfo(Userinfo userinfo) {
		this.hibernateTemplate.delete(userinfo);
	}

	@Transactional(readOnly=false)
	public void updateUserinfo(Userinfo userinfo) {
		this.hibernateTemplate.update(userinfo);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Userinfo> findAllUserinfo() {
		return this.hibernateTemplate.find("from Userinfo where isAdmin = 0");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Userinfo> findAllTeacherinfo() {
		return this.hibernateTemplate.find("from Userinfo where isAdmin = 1");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Userinfo> findAllAdmininfo() {
		return this.hibernateTemplate.find("from Userinfo where isAdmin = 2");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Userinfo> findByUserId(Integer userId) {
		List<Userinfo> list = this.hibernateTemplate.find("from Userinfo where userId=?", userId);

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Userinfo> findByUserName(String userName) {
		System.out.println("----");
		List<Userinfo> list = this.hibernateTemplate.find("from Userinfo where userName=?", userName);
		System.out.println(list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Userinfo>  findUserList(int pageStart, int pageSize) {
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		List<Userinfo> mq = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Userinfo where isAdmin = 0");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Userinfo> list = query.list();
						session.close();
						return list;
					}
				});
		return mq;
	}
	@SuppressWarnings("unchecked")
	public List<Userinfo>  findTeacherList(int pageStart, int pageSize) {
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		List<Userinfo> mq = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Userinfo where isAdmin = 1");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Userinfo> list = query.list();
						session.close();
						return list;
					}
				});

		return mq;
	}
	@SuppressWarnings("unchecked")
	public List<Userinfo>  findAdminList(int pageStart, int pageSize) {
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		List<Userinfo> mq = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Userinfo where isAdmin = 2");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Userinfo> list = query.list();
						session.close();
						return list;
					}
				});
		return mq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Userinfo> findUserInfoList(int pageStart, int pageSize) {
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		List<Userinfo> mq = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Userinfo");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Userinfo> list = query.list();
						session.close();
						return list;
					}
				});

		return mq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Userinfo> findSql(String sql) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find(sql);
	}
}
