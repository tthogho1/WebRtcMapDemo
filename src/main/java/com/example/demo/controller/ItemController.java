package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.User;
//import com.example.domain.Item;
import com.example.demo.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;

	/**
	 *
	 * @param item
	 * @return item
	 */
	@RequestMapping(value = "/postItem", method = RequestMethod.POST)
	public String postItem(@ModelAttribute User user, Model model) {

		itemService.createUser(user);
		// itemService.findAllByJson();
		model.addAttribute("user", user);

		return "map";
	}

	@RequestMapping(value = "/getMap", method = RequestMethod.GET)
	public String map(@RequestParam(name="TEST",required=false) String test,@ModelAttribute User user, Model model) {

		model.addAttribute("TEST",test);
		return "map";
	}

}