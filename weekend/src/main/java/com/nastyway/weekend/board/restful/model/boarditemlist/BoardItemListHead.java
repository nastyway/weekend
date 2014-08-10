package com.nastyway.weekend.board.restful.model.boarditemlist;

import com.nastyway.common.model.Head;

public class BoardItemListHead {
	public Head header = null;
	public BoardItemListBody body = null;
	
	public BoardItemListHead() {
	}
	
	public BoardItemListHead(Head header, BoardItemListBody body) {
		this.header = header;
		this.body = body;
	}
}
