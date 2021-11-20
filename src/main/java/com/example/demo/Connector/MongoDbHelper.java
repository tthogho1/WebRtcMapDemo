package com.example.demo.Connector;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoDbHelper {

	private static MongoClient mongoClient = null;


	public static MongoClient getMongoClient(String userName,String password, String hostName ,String dbName) {

		if (mongoClient == null) {
			String uriString = "mongodb://"+ userName + ":" + password + "@" + hostName  + "/?authSource=" + dbName ;

			log.info(uriString);
			MongoClientURI uri = new MongoClientURI(uriString);
			mongoClient = new MongoClient(uri);
		}

		return mongoClient ;
	}

}
