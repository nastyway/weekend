package com.nastyway.weekend.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyway.weekend.board.dao.BoardDao;
import com.nastyway.weekend.board.model.Board;
import com.nastyway.weekend.board.service.BoardService;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public Board getBoard(String boardId) {
		return boardDao.getBoard(boardId);
	}

}
