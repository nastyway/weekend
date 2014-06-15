package com.nastyway.weekend.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyway.weekend.user.dao.UserDao;
import com.nastyway.weekend.user.model.User;
import com.nastyway.weekend.user.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUserInfo(String userId) {
		return userDao.getUserInfo(userId);
	}

}
