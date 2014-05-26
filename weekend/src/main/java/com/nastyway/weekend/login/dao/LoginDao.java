package com.nastyway.weekend.login.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

public interface LoginDao {
	
	public int getLoginResult(Map<String, String> map);

}
