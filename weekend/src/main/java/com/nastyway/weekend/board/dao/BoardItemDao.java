package com.nastyway.weekend.board.dao;

import java.util.List;

import com.nastyway.weekend.board.model.BoardItem;

public interface BoardItemDao {

	/**
	 * �Խñ� ��� ��ȸ 
	 */
	public List<BoardItem> listBoardItem(String boardId);
	
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
