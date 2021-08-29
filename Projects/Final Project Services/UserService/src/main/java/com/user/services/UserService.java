package com.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.*;
import com.user.repository.*;

@Service
public class UserService {

	@Autowired
	private UserInterface userInterface;

	public void saveUser(User user) {
		userInterface.insert(user);
	}

	public List<User> getUserList() {
		return userInterface.findAll();
	}

	public Optional<User> getUserById(Integer id) {
		return userInterface.findById(id);
	}

	public User updateUser(Integer id, User u) {
		User user;
		user = userInterface.findById(id).orElse(null);
		user = u;
		user.setUserId(id);
		userInterface.save(user);
		return user;
	}

	public User deleteUser(Integer id) {
		User user;
		user = userInterface.findById(id).orElse(null);
		userInterface.delete(user);
		return user;
	}

}
