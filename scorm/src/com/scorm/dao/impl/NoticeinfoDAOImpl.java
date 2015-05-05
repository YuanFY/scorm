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

import com.scorm.dao.NoticeinfoDAO;
import com.scorm.vo.Noticeinfo;
/**
 * 实现接口NoticeinfoDAO
 * @author BFS Team 
 *
 */

@Repository(value="noticeinfoDAO")
public class NoticeinfoDAOImpl implements NoticeinfoDAO {
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	public void saveNoticeinfo(Noticeinfo noticeinfo) {
		this.hibernateTemplate.save(noticeinfo);
	}

	@Transactional(readOnly=false)
	public void deleteNoticeinfo(Noticeinfo noticeinfo) {
		this.hibernateTemplate.delete(noticeinfo);
	}

	@Transactional(readOnly=false)
	public void updateNoticeinfo(Noticeinfo noticeinfo) {
		this.hibernateTemplate.update(noticeinfo);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Noticeinfo> findAllNoticeinfo() {
		return this.hibernateTemplate.find("from Noticeinfo");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Noticeinfo> findByNoticeId(Integer noticeId) {
		return this.hibernateTemplate.find("from Noticeinfo where noticeId=?",noticeId);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Noticeinfo> findByNoticeTitle(String noticeTitle) {
		return this.hibernateTemplate.find("from Noticeinfo where noticeTitle like ?","%"+noticeTitle+"%");
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Noticeinfo> findByNoticeContent(String noticeContent) {
		return  this.hibernateTemplate.find("from Noticeinfo where noticeContent like ?", "%"+noticeContent+"%");
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Noticeinfo> findNoticeinfoList(int pageStart, int pageSize) {
		final int offset = (pageStart -1 ) * pageSize;
		final int length = pageSize;
		List<Noticeinfo> list = hibernateTemplate.executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("from Noticeinfo");
						query.setFirstResult(offset);
						query.setMaxResults(length);
						List<Noticeinfo> list = query.list();
						session.close();
						return list;
					}
				});

		return list;
	}

}
