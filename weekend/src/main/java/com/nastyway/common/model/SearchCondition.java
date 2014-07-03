package com.nastyway.common.model;

public class SearchCondition {
	
	private String searchWord;
	
	private int pageSize=10;
	
	private int pageIndex=1;

	private int totalPageSize;
	
	private int startPageIndex;
	
	private int endPageIndex;
	
	private int totalItemCount;
	
	private int paginationBlock = 10;

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPageIndex() {
		return startPageIndex;
	}

	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public int getPaginationBlock() {
		return paginationBlock;
	}

	public void setPaginationBlock(int paginationBlock) {
		this.paginationBlock = paginationBlock;
	}
}
