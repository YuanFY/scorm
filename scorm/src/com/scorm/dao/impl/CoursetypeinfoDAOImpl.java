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

import com.scorm.dao.CoursetypeinfoDAO;
import com.scorm.vo.Coursetypeinfo;

@SuppressWarnings("unchecked")
@Repository(value="coursetypeinfoDAO")
public class CoursetypeinfoDAOImpl implements CoursetypeinfoDAO{

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	public void saveCoursetype(Coursetypeinfo cTypeInfo) {
		this.hibernateTemplate.save(cTypeInfo);
	}

	@Transactional(readOnly=false)
	public void updateCoursetypeinfo(Coursetypeinfo coursetypeinfo) {
		this.hibernateTemplate.update(coursetypeinfo);
	}
	
	@Transactional(readOnly=false)
	public void deleteCoursetypeinfo(Coursetypeinfo cTypeInfo) {
		this.hibernateTemplate.delete(cTypeInfo);
	}

	@Transactional(readOnly=true)
	public List<Coursetypeinfo> findAllType() {
		return this.hibernateTemplate.find("from Coursetypeinfo");
	}

	@Transactional(readOnly=true)
	public List<Coursetypeinfo> findByTypeName(String ctype) {
		return this.hibernateTemplate.find("from Coursetypeinfo where courseType=?", ctype);
	}

	@Override
	public List<Coursetypeinfo> findCoursetypeinfoList(int pageStart,
			int pageSize) {
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		List<Coursetypeinfo> list = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Coursetypeinfo");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Coursetypeinfo> list = query.list();
						session.close();
						return list;
					}
				});

		return list;
	}

	@Override
	public List<Coursetypeinfo> findByCourseTypeId(int courseTypeId) {
		return this.hibernateTemplate.find("from Coursetypeinfo where courseTypeId=?", courseTypeId);
	}

}
