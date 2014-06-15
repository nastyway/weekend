package com.nastyway.weekend.user.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nastyway.weekend.user.dao.UserDao;
import com.nastyway.weekend.user.model.User;

@Repository("UserDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	private static String NAMESPACE = "com.nastyway.weekend.user.User.";
	
	@Override
	public User getUserInfo(String userId) {
		return getSqlSession().selectOne(NAMESPACE+"getUserInfo",userId);
	}

}
