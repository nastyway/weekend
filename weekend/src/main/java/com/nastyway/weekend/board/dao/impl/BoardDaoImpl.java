package com.nastyway.weekend.board.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nastyway.weekend.board.dao.BoardDao;
import com.nastyway.weekend.board.model.Board;

@Repository("BoardDao")
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao{

	private static String NAMESPACE = "com.nastyway.weekend.board.Board.";
	
	@Override
	public Board getBoard(String boardId) {
		return null;
	}

}
