package com.nastyway.weekend.board.model;

import java.util.List;

import com.nastyway.common.model.SearchCondition;

public class SearchBoardCondition extends SearchCondition {
	
	private String itemId = "";
	private String boardId = "";
	private String title = "";
	private String contents = "";
	private String hitCount = "";
	private String startDate = "";
	private String endDate = "";
	private String registerId = "";
	private String registerName = "";
	private String registerDate = "";
	
	// 검색할 게시판이 여러개일때.
	private List<Integer> boardIdList;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getHitCount() {
		return hitCount;
	}
	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public List<Integer> getBoardIdList() {
		return boardIdList;
	}
	public void setBoardIdList(List<Integer> boardIdList) {
		this.boardIdList = boardIdList;
	}

}
