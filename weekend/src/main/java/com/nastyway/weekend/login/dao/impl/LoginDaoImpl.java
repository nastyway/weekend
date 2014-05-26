package com.nastyway.weekend.login.dao.impl;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nastyway.weekend.login.dao.LoginDao;

@Repository
public class LoginDaoImpl extends SqlSessionDaoSupport implements LoginDao {
	
	private static String NAMESPACE = "com.nastyway.weekend.login.";

    @Override
    public int getLoginResult(Map<String, String> map) {
        return getSqlSession().selectOne(NAMESPACE+"getLoginResult",map); 
    }
    
}