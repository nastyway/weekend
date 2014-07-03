package com.nastyway.weekend.board.model;

import java.util.List;

import com.nastyway.weekend.fileupload.model.FileMapping;

public class BoardItem {
	
	private int	num;
	private String itemId = "";
	private String boardId = "";
	private String title = "";
	private String contents = "";
	private String hitCount = "";
	private String attachedFileCount = "";
	private String startDate = "";
	private String endDate = "";
	private String registerId = "";
	private String registerName = "";
	private String registerDate = "";
	
	private List<String> uploadedImagesUrl;
	private List<String> uploadedThumbnailUrl;
	
	private String fileId = "";
	private List<FileMapping> attachFiles;
	
	private String searchWord = "";
	private int startIndex = 0;
	private int endIndex = 0;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getHitCount() {
		return hitCount;
	}
	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}
	public String getAttachedFileCount() {
		return attachedFileCount;
	}
	public void setAttachedFileCount(String attachedFileCount) {
		this.attachedFileCount = attachedFileCount;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public List<FileMapping> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(List<FileMapping> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public List<String> getUploadedImagesUrl() {
		return uploadedImagesUrl;
	}
	public void setUploadedImagesUrl(List<String> uploadedImagesUrl) {
		this.uploadedImagesUrl = uploadedImagesUrl;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public List<String> getUploadedThumbnailUrl() {
		return uploadedThumbnailUrl;
	}
	public void setUploadedThumbnailUrl(List<String> uploadedThumbnailUrl) {
		this.uploadedThumbnailUrl = uploadedThumbnailUrl;
	}
	

}
