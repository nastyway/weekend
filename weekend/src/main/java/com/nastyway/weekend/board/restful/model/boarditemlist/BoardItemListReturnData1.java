package com.nastyway.weekend.board.restful.model.boarditemlist;

public class BoardItemListReturnData1 {

	private int pageIndex = 1;
	
	private int pageSize = 10;
	
	private int totalItemCount;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

}
