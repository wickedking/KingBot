package com.wicked.king.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wicked.king.bean.UtilString;


public interface DBAccessorUtils extends MongoRepository<UtilString, String>{
	
	
	public List<UtilString> findByListType(String listType);

}
