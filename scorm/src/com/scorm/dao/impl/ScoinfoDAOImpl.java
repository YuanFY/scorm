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

import com.scorm.dao.ScoinfoDAO;
import com.scorm.vo.Scoinfo;
/**
 * 实现接口ScoinfoDAO
 * @author BFS Team 
 *
 */

@Repository(value="scoinfoDAO")
public class ScoinfoDAOImpl implements ScoinfoDAO {
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	public void saveScoinfo(Scoinfo scoinfo) {
		this.hibernateTemplate.save(scoinfo);
	}

	@Transactional(readOnly=false)
	public void deleteScoinfo(Scoinfo scoinfo) {
		this.hibernateTemplate.delete(scoinfo);
	}

	@Transactional(readOnly=false)
	public void updateScoinfo(Scoinfo scoinfo) {
		this.hibernateTemplate.update(scoinfo);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Scoinfo> findAllScoinfo() {
		return this.hibernateTemplate.find("from Scoinfo");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Scoinfo> findByCourseId(Integer courseId) {
		return this.hibernateTemplate.find("from Scoinfo where courseId=?",courseId);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Scoinfo> findByScoId(Integer scoId) {
		return this.hibernateTemplate.find("from Scoinfo where scoId=?",scoId);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Scoinfo> findByScoName(String scoName) {
		return this.hibernateTemplate.find("from Scoinfo where scoName=?",scoName);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Scoinfo> findScoinfoList(int pageStart, int pageSize) {
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		List<Scoinfo> list = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Scoinfo");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Scoinfo> list = query.list();
						session.close();
						return list;
					}
				});

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Scoinfo> findByUploadAuthor(String uploadAuthor) {
		return this.hibernateTemplate.find("from Scoinfo where uploadAuthor = ?", uploadAuthor);
	}


}
