package com.hackerrank.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.model.User;
import com.hackerrank.sample.repository.UserRepository;
import com.hackerrank.sample.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public void create(User user) {
		if (user.getUsername() == null) {
			throw new BadResourceRequestException("Username is a mandatory field ");
		}
		if (userRepository.findByUsername(user.getUsername()) != null) {
			throw new BadResourceRequestException("Trying to create an existing user");
		}
		if (user.getPassword() == null) {
			throw new BadResourceRequestException("Password is a mandatory field ");
		}
		if (user.getId() == null) {
			throw new BadResourceRequestException("ID is a mandatory field ");
		}
		userRepository.save(user);
	}

	@Override
	public void update(String id, User user) {
		User dbUser = userRepository.findOne(id);
		if (dbUser == null) {
			throw new BadResourceRequestException("Trying to update a non existing user");
		}
		if (user.getUsername() == null) {
			throw new BadResourceRequestException("Username is a mandatory field ");
		}
		if (user.getPassword() == null) {
			throw new BadResourceRequestException("Password is a mandatory field ");
		}
		dbUser.setUsername(user.getUsername());
		dbUser.setPassword(user.getPassword());
		userRepository.save(user);
	}

	@Override
	public User getUserById(String id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
}
