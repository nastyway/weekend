package com.nastyway.weekend.fileupload.service;

import java.util.List;

import com.nastyway.weekend.fileupload.model.FileUpload;

public interface FileUploadService {

	public void createFile(FileUpload file);
	
	public FileUpload getFileInfo(String fileId);
	
	public void deleteFile(String fileId);
	
	public List<FileUpload> retrieveFileList(String itemId);
}
