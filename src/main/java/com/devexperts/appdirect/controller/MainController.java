package com.devexperts.appdirect.controller;

import com.devexperts.appdirect.storage.UserStorage;
import com.devexperts.appdirect.to.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	private UserStorage userStorage;

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/create")
	public String createProfile(Model model) {
		model.addAttribute("user", new UserData());
		return "user_add";
	}

	@RequestMapping("/edit")
	public String editProfile(Model model, String userOpenId) {
		UserData userData = userStorage.findByOpenId(userOpenId);
		model.addAttribute("user", userData);
		return "user_edit";
	}

	@RequestMapping("/spring_security_login")
	public String security() {
		return "index";
	}

	@RequestMapping("/list")
	public String listProfile(Model model) {
		model.addAttribute("userList", userStorage.list());
		return "user_list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") UserData userData) {
		userStorage.add(userData);
		return "index";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute("user") UserData userData) {
		UserData userDataToUpdate = userStorage.findById(userData.getId());
		if (userDataToUpdate != null) {
			userDataToUpdate.setOpenId(userData.getOpenId());
			userDataToUpdate.setFirstName(userData.getFirstName());
			userDataToUpdate.setLastName(userData.getLastName());
			userStorage.update(userDataToUpdate);
		}
		return "index";
	}

	@RequestMapping("/delete/{userId}")
	public String deleteProfile(@PathVariable("userId") Integer userId) {
		userStorage.removeProfile(userId);
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
}
