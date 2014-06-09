package com.nastyway.weekend.fileupload.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.nastyway.weekend.fileupload.dao.FileUploadDao;
import com.nastyway.weekend.fileupload.model.FileUpload;

@Repository
public class FileUploadDaoImpl extends SqlSessionDaoSupport implements FileUploadDao{
	
	private String namespace = "com.weekend.mobile.fileupload.";

	@Override
	public void createFile(FileUpload file) {
		getSqlSession().insert(namespace+"createFile", file);
	}

	@Override
	public void createMapping(FileUpload file) {
		getSqlSession().insert(namespace+"createMapping", file);
	}

	@Override
	public FileUpload getFileInfo(String fileId) {
		return (FileUpload)getSqlSession().selectOne(namespace+"getFileInfo",fileId);
	}

}
