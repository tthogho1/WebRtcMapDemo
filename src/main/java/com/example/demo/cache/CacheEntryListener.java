package com.example.demo.cache;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryExpired;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryExpiredEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;

import org.infinispan.Cache;

import com.example.demo.bean.User;

@Listener
public class CacheEntryListener {

	  @CacheEntryCreated
	  public void printCreate(CacheEntryCreatedEvent event) {
	    System.out.println("New entry " + event.getKey() + " created in the cache");
	  }

	  @CacheEntryExpired
	  public void printExpired(CacheEntryExpiredEvent event) {
		  System.out.println("Expired entry " + event.getKey() + " created in the cache");

		  String expiredUserName = (String)event.getKey();
		  User expiredUser = (User)event.getValue();
		  String connectedUserName = null;

		  if (expiredUser != null && !expiredUser.equals("")) {
			  connectedUserName = expiredUser.getConnectingId();
		  }else {
			  return;
		  }

		  if (connectedUserName == null || connectedUserName.equals("")) {
			  return;
		  }

		  Cache userCache = CacheFactory.getCache(CacheName.User.toString());
		  User connectedUser = (User)userCache.get(connectedUserName);

		  if (connectedUser != null && connectedUser.getConnectingId() != null
				  && !connectedUser.getConnectingId().equals("")
				  && connectedUser.getConnectingId().equals(expiredUserName)) {

			  connectedUser.setConnectingId("");
			  userCache.put(connectedUserName, connectedUser);
		  }
	  }
}
