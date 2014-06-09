package com.nastyway.weekend.board.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nastyway.weekend.board.model.BoardItem;
import com.nastyway.weekend.board.service.BoardItemService;

@Service("BoardItemService")
public class BoardItemServiceImpl implements BoardItemService {

	@Override
	public List<BoardItem> listBoardItem(String boardId) {
		return null;
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
