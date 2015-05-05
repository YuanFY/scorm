package com.scorm.dao;

import java.util.List;

import com.scorm.vo.Discussinfo;

/**
 * 讨论
 * @author YuanFY
 * @date 2014-8-13下午4:15:09
 * @Description:TODO
 * @version V1.0
 */
public interface DiscussinfoDAO {
	
	/**
	 * 保存讨论信息
	 * @param discussinfo 讨论信息对象
	 */
	public void save(Discussinfo discussinfo);
	/**
	 * 更新讨论信息对象
	 * @param discussinfo 讨论信息对象
	 */
	public void update(Discussinfo discussinfo);
	
	/**
	 * 自定义sql 查询  
	 * @param sql 自定义sql
	 * @return 返回相对应的讨论信息记录
	 */
	public List<Discussinfo> findSql( String sql ); 
}
