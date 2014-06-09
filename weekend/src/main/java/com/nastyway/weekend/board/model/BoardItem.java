package com.nastyway.weekend.board.model;

import java.util.List;

import com.nastyway.weekend.fileupload.model.FileMapping;

public class BoardItem {
	
	private String itemId = "";
	private String boardId = "";
	private String boardId1 = "";
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
	
	private String fileId = "";
	private List<FileMapping> attachFiles;
	
	private String genre = "";
	private String place = "";
	private String mc = "";
	private String dj = "";
	private String judge = "";
	private String guest = "";
	private String tutor = "";
	private String tel = "";
	private String email = "";
	private String kakaotalk = "";
	private String facebook = "";
	private String twitter = "";
	private String instagram = "";
	private String date = "";
	private String dateTime = "";
	private String coordinate = "";
	private String address1 = "";
	private String address2 = "";
	
	private String searchWord = "";
	private int startIndex = 0;
	private int endIndex = 0;
	
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
	public String getBoardId1() {
		return boardId1;
	}
	public void setBoardId1(String boardId1) {
		this.boardId1 = boardId1;
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
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getTutor() {
		return tutor;
	}
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKakaotalk() {
		return kakaotalk;
	}
	public void setKakaotalk(String kakaotalk) {
		this.kakaotalk = kakaotalk;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getInstagram() {
		return instagram;
	}
	public String getDj() {
		return dj;
	}
	public void setDj(String dj) {
		this.dj = dj;
	}
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	

}
