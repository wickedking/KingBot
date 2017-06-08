package com.wicked.king.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wicked.king.bean.ServerInfo;

/**
 * Proxy class used for mongodb accessing
 * @author King
 *
 */
public interface DBAccessorServerInfo extends MongoRepository<ServerInfo, String>{


}
