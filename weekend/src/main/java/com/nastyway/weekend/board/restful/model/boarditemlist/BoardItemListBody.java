package com.nastyway.weekend.board.restful.model.boarditemlist;

import java.util.List;

public class BoardItemListBody {
	
	public List<BoardItemListReturnData> BoardItemList = null;
	
	public BoardItemListReturnData1 PageInfo = null;
	
	
	public BoardItemListBody() {
	}
	
	public BoardItemListBody(List<BoardItemListReturnData> BoardItemList, BoardItemListReturnData1 PageInfo) {
		this.BoardItemList = BoardItemList;
		this.PageInfo = PageInfo;
	}

}
