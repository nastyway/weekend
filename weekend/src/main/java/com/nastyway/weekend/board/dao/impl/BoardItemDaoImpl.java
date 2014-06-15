package com.nastyway.weekend.board.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nastyway.weekend.board.dao.BoardItemDao;
import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;

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

	@Override
	public BoardItem getBoardItem(BoardItem param) {
		return null;
	}

	@Override
	public void createBoardItem(BoardItem boardItem) {
		
	}

	@Override
	public int deleteBoardItem(BoardItem boardItem) {
		return 0;
	}

	@Override
	public List<BoardItem> listBoardSearchItem(BoardItem boardItem) {
		return null;
	}

	@Override
	public int countListBoardSearchItem(BoardItem boardItem) {
		return 0;
	}
	
}
