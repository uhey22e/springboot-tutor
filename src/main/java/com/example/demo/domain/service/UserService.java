package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User findOneById(int id) {
		return userRepository.findOneById(id);
	}
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
