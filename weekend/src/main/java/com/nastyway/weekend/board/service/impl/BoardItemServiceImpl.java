package com.nastyway.weekend.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyway.weekend.board.dao.BoardItemDao;
import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.model.SearchBoardCondition;
import com.nastyway.weekend.board.service.BoardItemService;

@Service("BoardItemService")
public class BoardItemServiceImpl implements BoardItemService {
	
	@Autowired
	private BoardItemDao boardItemDao;

	@Override
	public List<BoardItem> listBoardItem(SearchBoardCondition searchBoardCondition) {
		
		//����Ʈ�� ������ ���Ѵ�.
		int count = boardItemDao.countBoardItem(searchBoardCondition);
		searchBoardCondition.setTotalItemCount(count);
		//�� ������ ������ ���Ѵ�.
		searchBoardCondition.setTotalPageSize((int)Math.ceil(count/(float)searchBoardCondition.getPageSize()));
		//������ ù��° ������ ���Ѵ�.
		searchBoardCondition.setStartPageIndex(searchBoardCondition.getPageSize()*(searchBoardCondition.getPageIndex()-1));
		//������ ������ ������ ���Ѵ�.
		searchBoardCondition.setEndPageIndex(searchBoardCondition.getPageSize()*(searchBoardCondition.getPageIndex()));
		
		List<BoardItem> listBoardItem = boardItemDao.listBoardItem(searchBoardCondition);
		
		return listBoardItem;
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
