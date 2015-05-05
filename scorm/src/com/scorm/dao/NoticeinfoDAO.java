package com.scorm.dao;

import java.util.List;

import com.scorm.vo.Noticeinfo;


/**
 * 公告信息接口层
 * @author BFS Team 
 *
 */
public interface NoticeinfoDAO {
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
	 * 分页查找公告信息
	 * @param pageStart 分页开始码
	 * @param pageSize   分页大小
	 * @return  返回相应的公告记录
	 */
	public List<Noticeinfo> findNoticeinfoList(int pageStart, int pageSize);

}
