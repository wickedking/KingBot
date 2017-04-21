package com.wicked.king.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wicked.king.bean.Person;

public interface DBAccessorPerson extends MongoRepository<Person, String>{
	
	public Person findByName(String name);
	public List<Person> findByLevel(int level);

}
