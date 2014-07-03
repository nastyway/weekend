package com.nastyway.weekend.board.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nastyway.weekend.board.dao.BoardItemDao;
import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;
import com.nastyway.weekend.fileupload.model.FileMapping;

@Repository("BoardItemDao")
public class BoardItemDaoImpl extends SqlSessionDaoSupport implements BoardItemDao {

	private static String NAMESPACE = "com.nastyway.weekend.board.BoardItem.";
	
	@Override
	public int countBoardItem(SearchBoardCondition searchBoardCondition) {
		return (int)getSqlSession().selectOne(NAMESPACE+"countBoardItem",searchBoardCondition);
	}
	
	@Override
	public List<BoardItem> listBoardItem(SearchBoardCondition searchBoardCondition) {
		return getSqlSession().selectList(NAMESPACE+"listBoardItem",searchBoardCondition);
	}

	/**
	 * 게시글 상세 조회 
	 */
	@Override
	public BoardItem getBoardItem(BoardItem param) {
		return (BoardItem) getSqlSession().selectOne(NAMESPACE+"getBoardItem",param); 
	}

	@Override
	public void createBoardItem(Map<String,String> params) {
		getSqlSession().insert(NAMESPACE+"createBoardItem", params);
	}

	@Override
	public int deleteBoardItem(BoardItem boardItem) {
		return getSqlSession().delete(NAMESPACE+"deleteBoardItem", boardItem);
	}
	
	/**
	 * 첨부된 파일 목록 조회
	 */
	@Override
	public List<FileMapping> getMappedFileInfo(String itemId) {
		return getSqlSession().selectList(NAMESPACE+"getMappedFileInfo", itemId);
	}

	@Override
	public List<BoardItem> listBoardSearchItem(BoardItem boardItem) {
		return null;
	}

	@Override
	public int countListBoardSearchItem(BoardItem boardItem) {
		return 0;
	}

	@Override
	public void updateBoardItem(Map<String, String> params) {
		getSqlSession().update(NAMESPACE+"updateBoardItem", params);
	}
	
}
