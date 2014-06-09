package com.nastyway.weekend.fileupload.service;

import com.nastyway.weekend.fileupload.model.FileUpload;

public interface FileUploadService {

	public void createFile(FileUpload file);
	
	public FileUpload getFileInfo(String fileId);
}
