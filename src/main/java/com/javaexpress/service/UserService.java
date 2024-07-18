package com.javaexpress.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.User;
import com.javaexpress.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		log.info("UserService :: createUser {} {}",user.getUsername(),user.getEmail());
		return userRepository.save(user);
	}
	
	public List<User> fetchAllUsers() {
		List<User> usersList = userRepository.findAll();
		List<User> result  = usersList.stream().sorted(Comparator.comparing(User::getUsername)).toList();
		return result;
	}
	
	public User findUserById(Integer userId) {
		return userRepository.findById(userId)
					.orElseThrow(() -> new RuntimeException("User Not Found"));
	}
	
	public User updateUser(Integer userId,User inputUser) {
		User dbUser = findUserById(userId);
		dbUser.setEmail(inputUser.getEmail());
		dbUser.setPassword(inputUser.getPassword());
		dbUser.setUsername(inputUser.getUsername());
		return userRepository.save(dbUser);
	}
	
	
	// hard delete
	public void deleteUser(Integer userId) {
		if(userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
		} else {
			throw new RuntimeException("User Not Found");
		}
	}
	
}
