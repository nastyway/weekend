package com.nastyway.weekend.board.dao;

import java.util.List;

import com.nastyway.weekend.board.model.BoardItem;

public interface BoardItemDao {

	/**
	 * 게시글 목록 조회 
	 */
	public List<BoardItem> listBoardItem(String boardId);
	
	/**
	 * 게시글 상세 조회 
	 */
	public BoardItem getBoardItem(BoardItem param);
	
	/**
	 * 게시글 등록
	 */
	public void createBoardItem(BoardItem boardItem);
	
	/**
	 * 게시글 삭제
	 */
	public int deleteBoardItem(BoardItem boardItem);
	
	/**
	 * 검색 리스트 조회
	 */
	public List<BoardItem> listBoardSearchItem(BoardItem boardItem);
	
	/**
	 * 검색 리스트 조회 count
	 */
	public int countListBoardSearchItem(BoardItem boardItem);
}
