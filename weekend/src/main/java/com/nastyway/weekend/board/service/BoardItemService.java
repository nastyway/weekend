package com.nastyway.weekend.board.service;

import java.util.List;
import java.util.Map;

import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;

public interface BoardItemService {

	/**
	 * 게시글 목록 조회 
	 */
	public List<BoardItem> listBoardItem(SearchBoardCondition searchBoardCondition);
	
	/**
	 * 게시글 상세 조회 
	 */
	public BoardItem getBoardItem(BoardItem param);
	
	/**
	 * 게시글 등록
	 */
	public void createBoardItem(Map<String,String> params, List<String> fileIdList);
	
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
	
	/**
	 * 게시글 등록
	 */
	public void updateBoardItem(Map<String,String> params, List<String> fileIdList);
}
