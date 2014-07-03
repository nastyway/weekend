package com.nastyway.weekend.board.dao;

import java.util.List;
import java.util.Map;

import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;
import com.nastyway.weekend.fileupload.model.FileMapping;

public interface BoardItemDao {

	/**
	 * 게시글 목록 갯수 조회 
	 */
	public int countBoardItem(SearchBoardCondition searchBoardCondition);
	
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
	public void createBoardItem(Map<String,String> params);
	
	/**
	 * 게시글 삭제
	 */
	public int deleteBoardItem(BoardItem boardItem);
	
	/**
	 * 첨부된 파일 목록 조회
	 */
	public List<FileMapping> getMappedFileInfo(String itemId);
	
	/**
	 * 검색 리스트 조회
	 */
	public List<BoardItem> listBoardSearchItem(BoardItem boardItem);
	
	/**
	 * 검색 리스트 조회 count
	 */
	public int countListBoardSearchItem(BoardItem boardItem);
	
	/**
	 * 게시글 수정
	 */
	public void updateBoardItem(Map<String,String> params);
}
