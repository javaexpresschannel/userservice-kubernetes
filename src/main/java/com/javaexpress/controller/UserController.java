package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.User;
import com.javaexpress.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping(value="/create1",produces = {"application/json"},consumes = {"application/json"})
	public User createUser(@RequestBody User user) {
		log.info("UserController :: createUser {} ",user);
		return userService.createUser(user);
	}
	
	@GetMapping(value="{userId}",produces = {"application/json"})
	public User getUserById(@PathVariable Integer userId) {
		log.info("UserController :: getUserById {} ",userId);
		return userService.findUserById(userId);
	}
	
	@PutMapping(value="{userId}",produces = {"application/json"},consumes = {"application/json"})
	public User updateUser(@PathVariable Integer userId,@RequestBody User user) {
		return userService.updateUser(userId, user);
	}
	
	@DeleteMapping(value="{userId}",produces = "application/json")
	public void deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
	}
	
}
