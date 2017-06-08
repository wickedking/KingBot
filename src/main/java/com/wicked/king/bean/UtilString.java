package com.wicked.king.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "utils")
public class UtilString {
	
	private String id;
	
	private String listType;
	
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
