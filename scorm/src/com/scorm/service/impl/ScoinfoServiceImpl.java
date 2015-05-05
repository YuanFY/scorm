package com.scorm.service.impl;

import java.util.ArrayList;
import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scorm.dao.ScoinfoDAO;
import com.scorm.service.ScoinfoService;
import com.scorm.vo.Scoinfo;
@Service(value="scoinfoService")
public class ScoinfoServiceImpl implements ScoinfoService {
	@Resource(name="scoinfoDAO")
	private ScoinfoDAO scoinfoDAO;

	public void saveScoinfo(Scoinfo scoinfo) {
		this.scoinfoDAO.saveScoinfo(scoinfo);
	}

	public void deleteScoinfo(Scoinfo scoinfo) {
		this.scoinfoDAO.deleteScoinfo(scoinfo);
	}

	public void updateScoinfo(Scoinfo scoinfo) {
		this.scoinfoDAO.updateScoinfo(scoinfo);
	}

	public List<Scoinfo> findAllScoinfo() {
		return this.scoinfoDAO.findAllScoinfo();
	}

	public List<Scoinfo> findByScoName(String scoName) {
		return this.scoinfoDAO.findByScoName(scoName);
	}

	public List<Scoinfo> findByScoId(Integer scoId) {
		return this.scoinfoDAO.findByScoId(scoId);
	}

	public List<Scoinfo> findByCourseId(Integer courseId) {
		return this.scoinfoDAO.findByCourseId(courseId);
	}


	@Override
	public List<Scoinfo> getScoinfoList(int pageStart, int pageSize) {
		List<Scoinfo> list = new ArrayList<Scoinfo>();
		list = scoinfoDAO.findScoinfoList(pageStart, pageSize);
		return list;
	}

	@Override
	public List<Scoinfo> findByUploadAuthor(String uploadAuthor) {
		return this.scoinfoDAO.findByUploadAuthor(uploadAuthor);
	}
}
