package com.wicked.king.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wicked.king.bean.ServerStats;

/**
 * 
 * @author King
 *
 */
public interface DBAccessorStats extends MongoRepository<ServerStats, String>{

}

