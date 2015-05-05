package com.scorm.service;

import java.util.List;
import com.scorm.vo.Noticeinfo;

/**
 * 公告信息服务层
 * @author BFS Team 
 *
 */
public interface NoticeinfoService {
	/**
	 * 保存公告信息
	 * @param noticeinfo 公告信息
	 */
	public void saveNoticeinfo(Noticeinfo noticeinfo);
	
	/**
	 * 删除公告信息
	 * @param noticeinfo 公告信息
	 */
	public void deleteNoticeinfo(Noticeinfo noticeinfo);
	
	/**
	 * 更新公告信息
	 * @param noticeinfo 公告信息
	 */
	public void updateNoticeinfo(Noticeinfo noticeinfo);
	
	/**
	 * 查找所有的公告信息
	 * @return 公告信息列表
	 */
	public List<Noticeinfo> findAllNoticeinfo();
	
	/**
	 * 按照noticeId查找公告
	 * @param noticeId 公告编号
	 * @return 返回相应的公告的信息
	 */
	public List<Noticeinfo> findByNoticeId(Integer noticeId);
	
	/**
	 * 按照noticeTitle查找公告
	 * @param noticeTitle 公告标题
	 * @return 返回相应的公告记录
	 */
	public List<Noticeinfo> findByNoticeTitle(String noticeTitle);
	
	/**
	 * 按照noticeContent查找公告
	 * @param noticeContent 公告内容
	 * @return  返回相应的公告记录
	 */
	public List<Noticeinfo> findByNoticeContent(String noticeContent);
	
	/**
	 * 分页显示公告信息
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public List<Noticeinfo> getNoticeInfoList(int pageStart, int pageSize);

}
