package com.nastyway.weekend.board.service;

import com.nastyway.weekend.board.model.Board;

public interface BoardService {

	/**
	 * 게시판 정보 조회
	 */
	public Board getBoard(String boardId);
	
}
