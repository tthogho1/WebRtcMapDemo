package com.example.demo.task;

import java.util.Iterator;

import org.bson.Document;
import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import com.example.demo.Connector.MongoDbHelper;
import com.example.demo.cache.CacheFactory;
import com.example.demo.cache.CacheName;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RunIptWcmListFromDB {

	@Value("${mongo.username:webcam}")
	private String userName;

	@Value("${mongo.password:webam}")
	private String password;

	@Value("${mongo.hostname:localhost:27017}")
	private String hostName;

	@Value("${mongo.dbname:webcam}")
	private String dbName;


//	@Scheduled(initialDelay = 0, fixedDelay = 1000 )
	@Scheduled(initialDelay = 0, fixedDelay = 1000 * 60 * 60 * 24)
	public void execute() {

		MongoClient client = MongoDbHelper.getMongoClient(userName, password, hostName, dbName);

		Cache<String,Object> webComCache = CacheFactory.getCache(CacheName.WebCam.toString());

		MongoDatabase database = client.getDatabase(dbName);
		MongoCollection<Document> collection = database.getCollection("webcam");

		FindIterable<Document> iterDoc = collection.find();
		Iterator it = iterDoc.iterator();
		while (it.hasNext()) {
			Document doc = (Document)it.next();
			//webComCache.put(doc.getString("id"), doc);

			log.info(doc.toString());

		}

	}

}