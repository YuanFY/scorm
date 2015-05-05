package com.scorm.service;

import java.util.List;

import com.scorm.vo.Noteinfo;

public interface NoteinfoService {
	/**
	 * 保存信息
	 * @param noteinfo 笔记信息对象
	 */
	public void save(Noteinfo noteinfo);
	
	/**
	 * 更新笔记信息
	 * @param noteinfo	笔记信息对象
	 */
	public void update(Noteinfo noteinfo);
	
	/**
	 * 删除笔记信息
	 * @param noteinfo 笔记信息对象
	 */
	public void delete(Noteinfo noteinfo);
	
	/**
	 * 自定义sql语句查询
	 * @param sql	自定义sql语句
	 * @return 返回相对应的记录记录
	 */
	public List<Noteinfo> findSql(String sql);
}
