package com.nastyway.weekend.fileupload.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyway.weekend.fileupload.dao.FileUploadDao;
import com.nastyway.weekend.fileupload.model.FileUpload;
import com.nastyway.weekend.fileupload.service.FileUploadService;

@Service("FileUploadService")
public class FileUploadServiceImpl implements FileUploadService{

	@Autowired
	private FileUploadDao fileUploadDao;
	
	@Override
	public void createFile(FileUpload file) {
		// DB에 파일정보를 넣고
		fileUploadDao.createFile(file);
	}

	@Override
	public FileUpload getFileInfo(String fileId) {
		
		return fileUploadDao.getFileInfo(fileId);
	}

}