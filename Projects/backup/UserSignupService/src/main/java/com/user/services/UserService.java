package com.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.*;
import com.user.model.*;
import com.user.repository.*;

@Service
public class UserService {

	@Autowired
	private UserInterface userInterface;

	public void saveUser(User user) {
		userInterface.save(user);
	}

	public List<User> getUserList() {
		return userInterface.findAll();
	}

	public Optional<User> getUserById(String id) {
		return userInterface.findById(id);
	}

	public User updateUser(String id, User u) {
		User user;
		user = userInterface.findById(id).orElse(null);
		user = u;
		user.setId(id);
		userInterface.save(user);
		return user;
	}
	public User getUserByEmail(String Email) {
		return userInterface.getUserByEmail(Email);
	}


	public User deleteUser(String id) {
		User user;
		user = userInterface.findById(id).orElse(null);
		userInterface.delete(user);
		return user;
	}

}
