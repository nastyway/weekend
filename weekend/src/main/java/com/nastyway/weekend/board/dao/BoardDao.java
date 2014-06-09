package com.nastyway.weekend.board.dao;

import com.nastyway.weekend.board.model.Board;

public interface BoardDao {

	/**
	 * 게시판 정보 조회
	 */
	public Board getBoard(String boardId);
}
