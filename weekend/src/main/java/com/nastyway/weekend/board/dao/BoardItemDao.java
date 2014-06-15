package com.nastyway.weekend.board.dao;

import java.util.List;

import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;

public interface BoardItemDao {

	/**
	 * �Խñ� ��� ���� ��ȸ 
	 */
	public int countBoardItem(SearchBoardCondition searchBoardCondition);
	
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
	public void createBoardItem(BoardItem boardItem);
	
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
}
