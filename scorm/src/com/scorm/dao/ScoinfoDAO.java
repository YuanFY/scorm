package com.scorm.dao;

import java.util.List;

import com.scorm.vo.Scoinfo;


/**
 * 课件信息接口层
 * @author BFS Team 
 *
 */
public interface ScoinfoDAO {
	/**
	 * 保存课件信息
	 * @param scoinfo 课件信息
	 */
	public void saveScoinfo(Scoinfo scoinfo);
	
	/**
	 * 删除课件信息
	 * @param scoinfo 课件信息
	 */
	public void deleteScoinfo(Scoinfo scoinfo);
	
	/**
	 * 更新课件信息
	 * @param scoinfo 课件信息
	 */
	public void updateScoinfo(Scoinfo scoinfo);
	
	/**
	 * 查找所有的课件
	 * @return 返回所有课件信息的列表
	 */
	public List<Scoinfo> findAllScoinfo();
	
	/**
	 * 按照课程编号查找所有课件
	 * @param courseId 课程编号
	 * @return 返回所有课件信息的列表
	 */
	public List<Scoinfo> findByCourseId(Integer courseId);
	
	/**
	 * 按照课件编号查找课件 
	 * @param scoId 课件编号
	 * @return  返回所有课件信息的列表
	 */
	public List<Scoinfo> findByScoId(Integer scoId);
	
	/**
	 * 按照课件名字查找课件 
	 * @param scoName 课件名称
	 * @return 返回所有课件信息的列表
	 */
	public List<Scoinfo> findByScoName(String scoName);
	

	/**
	 * 按照分页查找课件
	 * @param pageStart 分页开始点
	 * @param pageSize  分页长度
	 * @return 返回所有课件信息的列表
	 */
	public List<Scoinfo> findScoinfoList(int pageStart, int pageSize);
	
	/**
	 * 根据上传作者去查找所有的记录
	 * @param uploadAuthor 作者
	 * @return 返回相应课件信息的列表
	 */
	public List<Scoinfo> findByUploadAuthor(String uploadAuthor);

}
