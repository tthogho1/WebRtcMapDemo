package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.infinispan.CacheCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ResultStatus;
import com.example.demo.bean.User;
//import com.example.domain.Item;
import com.example.demo.service.ItemService;

@RestController
public class ItemRestController {
	@Autowired
	ItemService itemService;

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public CacheCollection<Object> getUsers() {

		CacheCollection<Object> cc = itemService.findAllUserByJson();

		return cc ;
	}

	@RequestMapping(value = "/getItems", method = RequestMethod.GET)
	public CacheCollection<Object> getItems() {

		CacheCollection<Object> cc = itemService.findAllItemsByJson();

		return cc ;
	}

	@RequestMapping(value = "/ajaxPostUser", method = RequestMethod.POST)
	public CacheCollection<Object> ajaxPostUser(HttpServletRequest request) {

		User user = new User();

		user.setId(request.getParameter("id"));
		user.setLatitude(request.getParameter("latitude"));
		user.setLongitude(request.getParameter("longitude"));

		String datetimeS = Long.toString(System.currentTimeMillis());
		user.setDatetime(datetimeS);

		itemService.createUser(user);
	//	model.addAttribute("user", user);
		CacheCollection<Object> cc = itemService.findAllUserByJson();

		// return customers;
		return cc ;
	}

	@RequestMapping(value = "/setUserInfo", method = RequestMethod.POST)
	public ResultStatus setUserInfo4Connection(HttpServletRequest request) {

		String from = request.getParameter("from");
		String to = request.getParameter("to");

		ResultStatus rs = itemService.setUserInfor4Connection(from,to);

		return rs ;
	}

	@RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST)
	public ResultStatus deleteUserInfo4Close(HttpServletRequest request) {

		String me = request.getParameter("me");
		String remote = request.getParameter("remote");

		ResultStatus rs = itemService.deleteUserInfor4Close(me,remote);

		return rs ;
	}



}