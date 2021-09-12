package com.example.demo.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.infinispan.Cache;
import org.infinispan.CacheCollection;
import org.springframework.stereotype.Service;

import com.example.demo.bean.LocationItem;
import com.example.demo.bean.User;
import com.example.demo.cache.CacheFactory;

@Service
public class ItemService {

	private Cache<String, Object> userCache;
	private Cache<String, Object> locationCache;

	public ItemService() {

		this.userCache = CacheFactory.getCache("User");
		this.locationCache = CacheFactory.getCache("Location");
	}

	public CacheCollection<Object> findAllUserByJson() {

		CacheCollection<Object> cc = userCache.values();

		return cc;
	}

	public CacheCollection<Object> findAllItemsByJson() {

		CacheCollection<Object> cc = locationCache.values();

		return cc;
	}

	public void createUser(User user) {

		userCache.put(user.getId(), user,1000*45,TimeUnit.MILLISECONDS);

	}


	public void createItem(LocationItem locItem) {

		locationCache.put(UUID.randomUUID().toString(), locItem);

	}

}