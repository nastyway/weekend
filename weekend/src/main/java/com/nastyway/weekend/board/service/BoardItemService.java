package com.nastyway.weekend.board.service;

import java.util.List;
import java.util.Map;

import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;

public interface BoardItemService {

	/**
	 * �Խñ� ��� ��ȸ 
	 */
	public List<BoardItem> listBoardItem(SearchBoardCondition searchBoardCondition);
	
	/**
	 * �Խñ� �� ��ȸ 
	 */
	public BoardItem getBoardItem(BoardItem param);
	
	/**
	 * �Խñ� ���
	 */
	public void createBoardItem(Map<String,String> params, List<String> fileIdList);
	
	/**
	 * �Խñ� ����
	 */
	public int deleteBoardItem(BoardItem boardItem);
	
	/**
	 * �˻� ����Ʈ ��ȸ
	 */
	public List<BoardItem> listBoardSearchItem(BoardItem boardItem);
	
	/**
	 * �˻� ����Ʈ ��ȸ count
	 */
	public int countListBoardSearchItem(BoardItem boardItem);
	
	/**
	 * �Խñ� ���
	 */
	public void updateBoardItem(Map<String,String> params, List<String> fileIdList);
}
