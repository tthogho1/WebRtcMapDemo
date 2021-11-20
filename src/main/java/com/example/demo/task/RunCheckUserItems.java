package com.example.demo.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.infinispan.Cache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.infinispan.query.Search;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

import com.example.demo.bean.User;
import com.example.demo.cache.CacheFactory;
import com.example.demo.cache.CacheName;

@Component
public class RunCheckUserItems {

	private int i = 0;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(initialDelay = 0, fixedDelay = 1000 * 60)
	public void execute() {

		Cache<String,Object> userCache = CacheFactory.getCache(CacheName.User.toString());

		String compareTime = Long.toString(System.currentTimeMillis() - 60*1000);
		QueryFactory qf = Search.getQueryFactory(userCache);
		Query query = qf.from(User.class).build();

		List<User> matches = query.list();
	      // List the results
		String br= System.getProperty("line.separator");
	    matches.forEach(user -> System.out.printf("Match: %s " + br , user));

		System.out.println("count: " + matches.size() + ",time: " + sdf.format(new Date()));
	}

}