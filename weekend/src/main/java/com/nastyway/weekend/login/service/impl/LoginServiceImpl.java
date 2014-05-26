package com.nastyway.weekend.login.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyway.weekend.login.dao.LoginDao;
import com.nastyway.weekend.login.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public int getLoginResult(Map<String, String> map) {
		return loginDao.getLoginResult(map);
	}

}
