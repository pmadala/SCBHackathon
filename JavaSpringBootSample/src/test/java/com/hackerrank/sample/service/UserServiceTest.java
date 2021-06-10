
package com.hackerrank.sample.service;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hackerrank.sample.BaseTest;
import com.hackerrank.sample.model.User;

public class UserServiceTest extends BaseTest {

	@Autowired
	private UserService userService;

	@Before
	public void cleanStart() {
		for (User user : userService.getAllUsers()) {
			userService.deleteById(user.getId());
		}

		for (int i = 0; i < 3; i++) {
			User user = new User("username" + i, "password" + i);
			user.setDateCreated(new Date().getTime());
			user.setId("id"+i);
			userService.create(user);
		}
	}

	@Test
	public void getAllUsers() {
		List<User> allUsers = userService.getAllUsers();
		Assert.assertEquals(allUsers.size(), 3);
	}

	@Test
	public void getUserById() {
		User user = userService.getUserById("id1");
		Assert.assertEquals(user.getUsername(), "username1");
	}

	@Test
	public void deleteById() {
		User user = userService.getUserById("id1");
		Assert.assertNotNull(user);
		userService.deleteById("id1");
		user = userService.getUserById("id1");
		Assert.assertNull(user);
	}

	@Test
	public void create() {
		List<User> allUsers = userService.getAllUsers();
		Assert.assertEquals(allUsers.size(), 3);
	}

}
