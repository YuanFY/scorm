package com.scorm.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.scorm.dao.NoticeinfoDAO;
import com.scorm.service.NoticeinfoService;
import com.scorm.vo.Noticeinfo;
@Service(value="noticeinfoService")
public class NoticeinfoServiceImpl implements NoticeinfoService {
	@Resource(name="noticeinfoDAO")
	private NoticeinfoDAO noticeinfoDAO;
	
	public void saveNoticeinfo(Noticeinfo noticeinfo) {
		this.noticeinfoDAO.saveNoticeinfo(noticeinfo);
	}

	public void deleteNoticeinfo(Noticeinfo noticeinfo) {
		this.noticeinfoDAO.deleteNoticeinfo(noticeinfo);
	}

	public void updateNoticeinfo(Noticeinfo noticeinfo) {
		this.noticeinfoDAO.updateNoticeinfo(noticeinfo);
	}

	public List<Noticeinfo> findAllNoticeinfo() {
		return this.noticeinfoDAO.findAllNoticeinfo();
	}

	public List<Noticeinfo> findByNoticeId(Integer noticeId) {
		return this.noticeinfoDAO.findByNoticeId(noticeId);
	}

	public List<Noticeinfo> findByNoticeTitle(String noticeTitle) {
		return this.noticeinfoDAO.findByNoticeTitle(noticeTitle);
	}

	public List<Noticeinfo> findByNoticeContent(String noticeContent) {
		return this.noticeinfoDAO.findByNoticeContent(noticeContent);
	}
	@Override
	public List<Noticeinfo> getNoticeInfoList(int pageStart, int pageSize) {
		List<Noticeinfo> list = new ArrayList<Noticeinfo>();
		list = noticeinfoDAO.findNoticeinfoList(pageStart, pageSize);
		return list;
	}
}
