package com.example.demo.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.infinispan.Cache;
import org.infinispan.CacheCollection;
import org.springframework.stereotype.Service;

import com.example.demo.bean.LocationItem;
import com.example.demo.bean.ResultStatus;
import com.example.demo.bean.Status;
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

	// must lock
	public synchronized ResultStatus deleteUserInfor4Close(String me,String remote) {

		ResultStatus status = new ResultStatus();
		status.setStatus(Status.OK.toString());

		User uMe = (User)userCache.get(me);
		User uRemote = (User)userCache.get(remote);

		if (uRemote != null && me.equals(uRemote.getConnectingId())) {
			uRemote.setConnectingId("");
		}

		if (uMe != null ) {
			uMe.setConnectingId("");
		}

		userCache.put(remote,uRemote);
		userCache.put(me,uMe);

		return status;
	}

	// must lock
	public synchronized ResultStatus setUserInfor4Connection(String from,String to) {

			ResultStatus status = new ResultStatus();
			status.setStatus(Status.OK.toString());

			User uFrom = (User)userCache.get(from);
			User uTo = (User)userCache.get(to);

			if (uFrom == null || uTo == null) {
				status.setStatus(Status.ERROR.toString());
				return status;
			}

			if ((uFrom.getConnectingId() != null && !uFrom.getConnectingId().isEmpty()) ||
					(uTo.getConnectingId() != null && !uTo.getConnectingId().isEmpty())) {
				status.setStatus(Status.ERROR.toString());
				return status;
			}

			uFrom.setConnectingId(to);
			uTo.setConnectingId(from);

			userCache.put(from,uFrom);
			userCache.put(to,uTo);

			return status;
		}



}