package com.scorm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.NoteinfoDAO;
import com.scorm.service.NoteinfoService;
import com.scorm.vo.Noteinfo;

@Service(value="noteinfoService")
public class NoteinfoServiceImpl implements NoteinfoService {

	@Resource(name="noteinfoDAO")
	private NoteinfoDAO noteinfoDAO;
	@Override
	public void save(Noteinfo noteinfo) {
		// TODO Auto-generated method stub
		this.noteinfoDAO.save(noteinfo);
	}

	@Override
	public void update(Noteinfo noteinfo) {
		// TODO Auto-generated method stub
		this.noteinfoDAO.update(noteinfo);
	}

	@Override
	public List<Noteinfo> findSql(String sql) {
		System.out.println("sql="+sql );
		// TODO Auto-generated method stub
		return this.noteinfoDAO.findSql(sql);
	}

	@Override
	public void delete(Noteinfo noteinfo) {
		// TODO Auto-generated method stub
		this.noteinfoDAO.delete(noteinfo);
	}

}
