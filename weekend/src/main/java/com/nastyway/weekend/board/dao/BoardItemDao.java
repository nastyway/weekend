package com.nastyway.weekend.board.dao;

import java.util.List;
import java.util.Map;

import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;
import com.nastyway.weekend.fileupload.model.FileMapping;

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
	public void createBoardItem(Map<String,String> params);
	
	/**
	 * �Խñ� ����
	 */
	public int deleteBoardItem(BoardItem boardItem);
	
	/**
	 * ÷�ε� ���� ��� ��ȸ
	 */
	public List<FileMapping> getMappedFileInfo(String itemId);
	
	/**
	 * �˻� ����Ʈ ��ȸ
	 */
	public List<BoardItem> listBoardSearchItem(BoardItem boardItem);
	
	/**
	 * �˻� ����Ʈ ��ȸ count
	 */
	public int countListBoardSearchItem(BoardItem boardItem);
	
	/**
	 * �Խñ� ����
	 */
	public void updateBoardItem(Map<String,String> params);
}
