package com.scorm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scorm.dao.DiscussinfoDAO;
import com.scorm.vo.Discussinfo;

/**
 * 讨论
 * @author YuanFY
 * @date 2014-8-13下午4:15:09
 * @Description:TODO
 * @version V1.0
 */

@Repository(value="discussinfoDAO")
public class DiscussinfoDAOImpl  implements DiscussinfoDAO {
 
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Transactional(readOnly=false)
	@Override
	public void save(Discussinfo discussinfo) {
		this.hibernateTemplate.save(discussinfo);
	}
	@Transactional(readOnly=false)
	@Override
	public void update(Discussinfo discussinfo) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(discussinfo);
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Discussinfo> findSql(String sql) {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find(sql);
	}
	
	 
}
