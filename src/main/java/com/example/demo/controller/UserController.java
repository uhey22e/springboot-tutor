package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") int id) {
		return userService.findOneById(id);
	}
	
	@PostMapping()
	public String createUser(@RequestBody User user) {
		return userService.saveOne(user).toString();
	}
}
