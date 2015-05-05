package com.scorm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.DiscussinfoDAO;
import com.scorm.vo.Discussinfo;

@Service(value="discussinfoService")
public class DiscussinfoServiceImpl implements DiscussinfoService{

	@Resource(name="discussinfoDAO")
	private DiscussinfoDAO discussinfoDAO;
	
	@Override
	public void save(Discussinfo discussinfo) {
		// TODO Auto-generated method stub
		this.discussinfoDAO.save(discussinfo);
	}

	@Override
	public void update(Discussinfo discussinfo) {
		// TODO Auto-generated method stub
		this.discussinfoDAO.update(discussinfo);
	}

	@Override
	public List<Discussinfo> findSql(String sql) {
		// TODO Auto-generated method stub
		return this.discussinfoDAO.findSql(sql);
	}

}
