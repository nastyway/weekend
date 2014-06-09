package com.nastyway.weekend.fileupload.model;

public class FileUpload {
	
	private String file_id;
	private String file_path;
	private String file_stored_name;
	private String file_original_name;
	private String file_content_type;
	private String file_size;
	private String register_id;
	private String register_name;
	private String register_date;
	
	private String item_id;
	private String sort_order;
	
	public String getFile_id() {
		return file_id;
	}
	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_original_name() {
		return file_original_name;
	}
	public void setFile_original_name(String file_original_name) {
		this.file_original_name = file_original_name;
	}
	public String getRegister_id() {
		return register_id;
	}
	public void setRegister_id(String register_id) {
		this.register_id = register_id;
	}
	public String getRegister_name() {
		return register_name;
	}
	public void setRegister_name(String register_name) {
		this.register_name = register_name;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public String getFile_stored_name() {
		return file_stored_name;
	}
	public void setFile_stored_name(String file_stored_name) {
		this.file_stored_name = file_stored_name;
	}
	public String getFile_content_type() {
		return file_content_type;
	}
	public void setFile_content_type(String file_content_type) {
		this.file_content_type = file_content_type;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getSort_order() {
		return sort_order;
	}
	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}

}
