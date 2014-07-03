package com.nastyway.weekend.fileupload.dao;

import java.util.List;

import com.nastyway.weekend.fileupload.model.FileUpload;

public interface FileUploadDao {
	
	public void createFile(FileUpload file);
	
	public void createMapping(FileUpload file);
	
	public FileUpload getFileInfo(String fileId);
	
	public void deleteFile(String fileId);
	
	public List<FileUpload> retrieveFileList(String itemId);
}


