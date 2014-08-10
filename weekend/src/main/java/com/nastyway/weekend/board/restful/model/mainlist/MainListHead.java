package com.nastyway.weekend.board.restful.model.mainlist;

import com.nastyway.common.model.Head;

public class MainListHead {
	
	public Head header = null;
	public MainListBody body = null;
	
	public MainListHead() {
	}
	
	public MainListHead(Head header, MainListBody body) {
		this.header = header;
		this.body = body;
	}

}
