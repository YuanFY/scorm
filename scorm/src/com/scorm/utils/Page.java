package com.scorm.utils;

public class Page {
	private int pageCount;//总页面数
	private int currentPage = 1;//当前页面
	private int dataCount;//数据总量
	private int pagestartIndex;//每页数据开始索引值
	private int pageendIndex;
	private int pageRecordNum = 10; //每页数据量
	private int startPage;//开始页码
	private int endPage;//结束页面
	
	
	
	public Page(int dataCount,int currentPage){
		this.dataCount = dataCount;
		this.currentPage = currentPage;
		pageCount = this.dataCount%pageRecordNum==0? this.dataCount/pageRecordNum:(this.dataCount/pageRecordNum+1);
		pagestartIndex = (currentPage-1)*pageRecordNum;
		startPage = currentPage - 5;
		if(startPage<1){
			startPage = 1;
		}
		endPage = startPage + 9;
		if(endPage > pageCount){
			endPage = pageCount;
		}
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	 
	public int getPagestartIndex() {
		return pagestartIndex;
	}
	public void setPagestartIndex(int pagestartIndex) {
		this.pagestartIndex = pagestartIndex;
	}
	public int getPageendIndex() {
		return pageendIndex;
	}
	public void setPageendIndex(int pageendIndex) {
		this.pageendIndex = pageendIndex;
	}
	public int getPageRecordNum() {
		return pageRecordNum;
	}
	public void setPageRecordNum(int pageRecordNum) {
		this.pageRecordNum = pageRecordNum;
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
